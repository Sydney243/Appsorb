// DAO is a data access object which is used to specify SQL queries
// and then associate them with different method calls.
// DAO may be an abstract class or an interface.
// Inside the DAO class, we have to create different methods such as inserting,
// deleting the data, and reading the data from our database.
// So this class will basically interact with our database to add or
// delete data inside our database.
package com.example.appsorbv6.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

// annotation for dao class.
@Dao
interface TopicsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(topic: Topic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(topics: List<Topic>)

    @Query("DELETE FROM topic_table")
    suspend fun deleteAll()

    //    @Delete
//    suspend fun deleteTopic(topic: Topic)
//
    @Query("SELECT * FROM topic_table WHERE id = :topicId")
    fun getTopic(topicId: Int): Flow<Topic>

    @Query("SELECT * FROM topic_table ORDER BY name ASC")
    fun getAllTopics(): Flow<List<Topic>>


}