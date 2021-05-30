package com.afrakhteh.sweetlandapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fave_table")
data class FaveModel(

        @ColumnInfo(name = "id_col")
        var id: Int,

        @ColumnInfo(name = "desc_col")
        var description: String,

        @ColumnInfo(name = "image_col")
        var image: String,

        @ColumnInfo(name = "name_col")
        var name: String,

        @ColumnInfo(name = "recipe_col")
        var recipe: String,

        @ColumnInfo(name = "time_col")
        var time: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}