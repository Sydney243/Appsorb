package com.example.appsorbv6.di

import android.content.Context
import com.example.appsorbv6.data.Topic
import com.example.appsorbv6.data.TopicDatabase
import com.example.appsorbv6.data.TopicsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

class DBModule {

    @Singleton
    @Provides
    fun provideTopicDatabase(@ApplicationContext context: Context): TopicDatabase {
        return TopicDatabase.getInstance(context)
    }

    @Provides
    fun provideTopicDao(topicDatabase: TopicDatabase): TopicsDao {
        return topicDatabase.getTopicsDao()
    }
}