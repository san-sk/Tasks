package com.san.canvas.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class CanvaDataBase : RoomDatabase() {



}
