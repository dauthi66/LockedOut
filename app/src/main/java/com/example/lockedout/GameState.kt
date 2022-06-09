package com.example.lockedout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_state")
//database class
data class GameState(
    @PrimaryKey(autoGenerate = true)
    var gameId: Long = 0L,
    @ColumnInfo(name = "action_list")
    var actionList: List<String> = listOf("take", "open", "use"),
    @ColumnInfo(name = "have_key")
    var haveKey: Boolean = false,
    @ColumnInfo(name = "have_hammer")
    var haveHammer: Boolean = false,
    @ColumnInfo(name = "have_ladder")
    var haveladder: Boolean = false
)