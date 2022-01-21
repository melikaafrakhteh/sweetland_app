package com.afrakhteh.sweetlandapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afrakhteh.sweetlandapp.constants.Strings


@Entity(tableName = Strings.TABLE_NAME)
data class FavoriteEntity(

        @ColumnInfo(name = Strings.ID_COL)
        var id: Int,

        @ColumnInfo(name = Strings.DESC_COL)
        var description: String,

        @ColumnInfo(name = Strings.IMG_COL)
        var image: String,

        @ColumnInfo(name = Strings.NAME_COL)
        var name: String,

        @ColumnInfo(name = Strings.REC_COL)
        var recipe: String,

        @ColumnInfo(name = Strings.TIME_COL)
        var time: String,

        @ColumnInfo(name = Strings.FAVE_COL)
        var isFave: Boolean = false
        )
{

    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0

}