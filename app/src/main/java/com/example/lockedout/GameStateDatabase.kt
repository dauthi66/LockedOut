package com.example.lockedout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//change version number to change database schema
@Database(entities = [GameState::class], version = 7, exportSchema = false)
abstract class GameStateDatabase : RoomDatabase() {
    abstract val gameStateDao: GameStateDao
    companion object {
        @Volatile
        private var INSTANCE: GameStateDatabase? = null
        //create instance of database if one doesn't exist
        fun getInstance(context: Context): GameStateDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GameStateDatabase::class.java,
                        "game_state_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}