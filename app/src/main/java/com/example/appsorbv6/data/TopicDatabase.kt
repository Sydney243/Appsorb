// Room is basically a database layer on top of the SQLite database.
// Room takes care of mundane tasks that you used to handle with an SQLite Open Helper.
// Room uses the DAO to issue queries to its database.
// Room provides compile-time checks of SQLite statements.

package com.example.appsorbv6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Topic::class), version = 1, exportSchema = false)
abstract class TopicDatabase : RoomDatabase() {

    abstract fun getTopicsDao(): TopicsDao

    companion object {
        @Volatile
        private var INSTANCE: TopicDatabase? = null

        fun getInstance(context: Context): TopicDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): TopicDatabase {
            return Room.databaseBuilder(context, TopicDatabase::class.java, "topic_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}