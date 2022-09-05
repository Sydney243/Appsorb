package com.example.appsorbv6.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.appsorbv6.BR
import com.example.appsorbv6.data.Topic
import com.example.appsorbv6.data.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class TopicFormViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val mTopicRepository: TopicRepository)
    : ObservableViewModel() {

    val topicId: String = savedStateHandle.get<String>(TopicFormViewModel.TOPIC_ID_SAVED_STATE_KEY)!!

    val topic: LiveData<Topic>? = if (!topicId.isNullOrEmpty()) mTopicRepository.getTopic(topicId.toInt())
        ?.asLiveData() else null

    @get:Bindable
    var name: String? = null
        private set

    @get:Bindable
    var description: String? = null
        private set

    @get:Bindable
    var details: String? = null
        private set

    @get:Bindable
    var imageUrl: String? = null
        private set

    @get:Bindable
    var soundUrl: String? = null
        private set

    var formErrors: ObservableList<FormErrors>

    fun onReset() {
        name = ""
        description = ""
        details = ""
        imageUrl = ""
        soundUrl = ""
        formErrors.clear()
    }

    private fun addTopic() {
        viewModelScope.launch {
            mTopicRepository.insert(Topic(name!!,
                description!!,
                details!!,
                imageUrl!!,
                soundUrl!!))
        }
    }

    private fun updateTopic() {
        viewModelScope.launch {
            mTopicRepository.update(Topic(topicId.toInt(),
                name!!,
                description!!,
                details!!,
                imageUrl!!,
                soundUrl!!))
        }
    }

    val isFormValid: Boolean
        get() {
            formErrors.clear()
            if (name!!.isEmpty()) {
                formErrors.add(FormErrors.MISSING_NAME)
            }
            else if (!name!!.isEmpty() && name!!.trim { it <= ' ' }.length <= 0) {
                formErrors.add(FormErrors.INVALID_NAME)
            }
            if (description!!.isEmpty()) formErrors.add(FormErrors.MISSING_DESCRIPTION) else if (!description!!.isEmpty() && description!!.trim { it <= ' ' }.length <= 0) formErrors.add(FormErrors.INVALID_DESCRIPTION)
            return formErrors.isEmpty()
        }

    fun setName(name: String) {
        if (this.name != name) {
            this.name = name
            notifyPropertyChanged(BR.name)
        }
    }

    fun setDescription(description: String) {
        if (this.description != description) {
            this.description = description
            notifyPropertyChanged(BR.description)
        }
    }

    fun setDetails(details: String) {
        if (this.details != details) {
            this.details = details
            notifyPropertyChanged(BR.details)
        }
    }

    fun setImageUrl(imageUrl: String) {
        if (this.imageUrl != imageUrl) {
            this.imageUrl = imageUrl
            notifyPropertyChanged(BR.imageUrl)
        }
    }

    fun setSoundUrl(soundUrl: String) {
        if (this.soundUrl != soundUrl) {
            this.soundUrl = soundUrl
            notifyPropertyChanged(BR.soundUrl)
        }
    }

    fun onSubmit() {
        if (topic != null)
            updateTopic()
        else
            addTopic()
    }

    enum class FormErrors {
        MISSING_NAME, INVALID_NAME, INVALID_DESCRIPTION, MISSING_DESCRIPTION
    }

    init {
        formErrors = ObservableArrayList()
        onReset()
    }

    companion object {
        private const val TOPIC_ID_SAVED_STATE_KEY = "topicId"
    }
}

