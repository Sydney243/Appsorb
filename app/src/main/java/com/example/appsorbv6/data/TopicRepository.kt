// Repository class basically abstracts the access to multiple data sources such as
// getting the data from API or getting the data from Room database.
// A repository class will provides us a clean API for data access for the rest of the application.
// The repository will contain a logic that will be deciding whether we have to fetch the data
// from the network or we have to get the data from Database.

package com.example.appsorbv6.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopicRepository @Inject constructor(
    private val topicsDao: TopicsDao) {

    fun getTopics() = topicsDao.getAllTopics()

    fun getTopic(id: Int) = topicsDao.getTopic(id)

    suspend fun insert(topic: Topic) {
        topicsDao.insert(topic);
    }

    suspend fun insertAll(topics: List<Topic>) {
        topicsDao.insertAll(topics);
    }

    suspend fun update(topic: Topic) {
        //mTopicDao.update(topic);
    }

    suspend fun deleteAll() {
        topicsDao.deleteAll();
    }

    // Must run off main thread
    suspend fun deleteTopic(topic: Topic) {
        //mTopicDao.deleteTopic(topic);
    }






}