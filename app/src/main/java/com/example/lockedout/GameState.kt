package com.example.tasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "game_state")
//database class
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,
    @ColumnInfo(name = "action_list")
    var actionList: List<String> = listOf("take", "open", "use"),
    @ColumnInfo(name = "have_key")
    var haveKey: Boolean = false,
    @ColumnInfo(name = "have_hammer")
    var haveHammer: Boolean = false,
    @ColumnInfo(name = "have_ladder")
    var haveladder: Boolean = false
)