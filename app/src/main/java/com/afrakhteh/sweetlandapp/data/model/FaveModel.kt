package com.afrakhteh.sweetlandapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afrakhteh.sweetlandapp.util.Constants


@Entity(tableName = Constants.TABLE_NAME)
data class FaveModel(

        @ColumnInfo(name = Constants.ID_COL)
        var id: Int,

        @ColumnInfo(name = Constants.DESC_COL)
        var description: String,

        @ColumnInfo(name = Constants.IMG_COL)
        var image: String,

        @ColumnInfo(name = Constants.NAME_COL)
        var name: String,

        @ColumnInfo(name = Constants.REC_COL)
        var recipe: String,

        @ColumnInfo(name = Constants.TIME_COL)
        var time: String,

        @ColumnInfo(name = Constants.FAVE_COL)
        var isFave: Int
        )
{

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}