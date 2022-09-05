// An Entity is basically a modal class or a structure of our database in which
// we will be used for creating a database structure.
// Inside our app, we will be having a simple table that will be only having
// two columns such as ID and a text. ID will be used for the identification of an entry
// and text is the column for the identification of text column.
// Below is the table structure for our database.
// Below is the image for the database.

package com.example.appsorbv6.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "topic_table")
class Topic {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0

    @ColumnInfo(name = "name")
    val name: String


    @ColumnInfo(name = "description")
    val description: String

    @ColumnInfo(name = "details")
    val details: String

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String

    @ColumnInfo(name = "audioUrl")
    val audioUrl: String

    constructor(name: String,
                description: String,
                details: String,
                imageUrl: String,
                audioUrl: String) {
        this.name = name
        this.description = description
        this.details = details
        this.imageUrl = imageUrl
        this.audioUrl = audioUrl
    }

    @Ignore
    constructor(id: Int,
                name: String,
                description: String,
                details: String,
                imageUrl: String,
                audioUrl: String) {
        this.id = id
        this.name = name
        this.description = description
        this.details = details
        this.imageUrl = imageUrl
        this.audioUrl = audioUrl
    }

    override fun equals(obj: Any?): Boolean {
        return (obj is Topic
                && id == obj.id)
    }
}