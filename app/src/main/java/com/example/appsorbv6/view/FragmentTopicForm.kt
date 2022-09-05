package com.example.appsorbv6.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.appsorbv6.R
import com.example.appsorbv6.custom.CustomAlertDialog
import com.example.appsorbv6.databinding.FragmentTopicFormBinding
import com.example.appsorbv6.viewmodel.TopicFormViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentTopicForm : Fragment() {



    companion object {
        private const val IMAGE_PERMISSION_CODE = 100
        private const val FILE_PERMISSION_CODE = 101
    }

    private var mBinding: FragmentTopicFormBinding? = null
    private val mViewModel: TopicFormViewModel by viewModels()


    private val mGetImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { result: Uri? -> mViewModel.setImageUrl(result.toString()) }

    private val mGetAudio: ActivityResultLauncher<Array<String>> =
        registerForActivityResult<Array<String>, Uri>(
            ActivityResultContracts.OpenDocument()
        ) { result ->
            if (result != null) mViewModel.setSoundUrl(result.toString())
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentTopicFormBinding.inflate(inflater, container, false)
            .apply {
                viewModel = mViewModel
                lifecycleOwner = viewLifecycleOwner

                if (mViewModel.topic != null) {
                    mViewModel.topic!!.observe(viewLifecycleOwner) { topic ->
                        if (topic != null) {
                            mViewModel.setName(topic.name)
                            mViewModel.setDescription(topic.description)
                            mViewModel.setDetails(topic.details)
                            mViewModel.setImageUrl(topic.imageUrl)
                            mViewModel.setSoundUrl(topic.audioUrl)
                        }
                    }
                }

                btnImage.setOnClickListener {
                    requestPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        IMAGE_PERMISSION_CODE
                    )
                }

                btnAudio.setOnClickListener {
                    requestPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        FILE_PERMISSION_CODE
                    )
                }
                BtnSubmit.setOnClickListener {

                    //insert text
                    var title = idEdtTopicName.text.toString()
                    var body = idEdtTopicDesc.text.toString()
                    var tags = "sample tag"


                    mViewModel.setName(title)
                    mViewModel.setDescription(body)
                    mViewModel.setDetails(tags)
                    Log.d("Title",title)

                    mViewModel.onSubmit()

                }

            }
        (activity as AppCompatActivity?)?.getSupportActionBar()
            ?.setHomeAsUpIndicator(R.drawable.ic_action_file)
        setHasOptionsMenu(true)
        return mBinding!!.root
    }


    private fun requestPermission(permission: String, requestCode: Int) {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if (requestCode == IMAGE_PERMISSION_CODE)
                mGetImage.launch("image/*")
            else if (requestCode == FILE_PERMISSION_CODE)
                mGetAudio.launch(arrayOf("*/*"))
        } else {
            requestPermissions(
                arrayOf(permission),
                requestCode
            )

        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            IMAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mGetImage.launch("image/*")
                } else {
                    showPermissionRationaleDialog(
                        resources.getString(R.string.no_read_storage_permission_title),
                        resources.getString(R.string.no_read_storage_permission_message)
                    )
                }
                return
            }
            FILE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mGetAudio.launch(arrayOf("*/*"))
                } else {
                    showPermissionRationaleDialog(
                        resources.getString(R.string.no_read_storage_permission_title),
                        resources.getString(R.string.no_read_storage_permission_message)
                    )
                }
                return
            }
            else -> {

            }
        }


    }

    private fun showPermissionRationaleDialog(title: String, message: String) {
        CustomAlertDialog.showDialog(
            context,
            title,
            message,
            resources.getString(R.string.ok_label),
            null
        )
    }



}