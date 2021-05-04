package com.afrakhteh.sweetlandapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fave_table")
data class FaveModel(

        @ColumnInfo(name = "id_col")
        var id: Int,

        @ColumnInfo(name = "desc_col")
        val description: String,

        @ColumnInfo(name = "image_col")
        val image: String,

        @ColumnInfo(name = "name_col")
        val name: String,

        @ColumnInfo(name = "recipe_col")
        val recipe: String,

        @ColumnInfo(name = "time_col")
        val time: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}