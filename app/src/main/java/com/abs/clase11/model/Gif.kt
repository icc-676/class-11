package com.abs.clase11.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abs.clase11.model.Gif.Companion.TABLE_NAME
import com.google.gson.annotations.SerializedName
@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: Gif)

    @Query("SELECT * FROM ${TABLE_NAME}")
    fun getAllGifs(): List<Gif>

}

@Entity(tableName = TABLE_NAME)
data class Gif(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: String,
    @ColumnInfo(name = URL)
    val url: String
) {
    companion object {
        const val TABLE_NAME = "gif"
        const val ID = "id"
        const val URL = "url"

    }
}