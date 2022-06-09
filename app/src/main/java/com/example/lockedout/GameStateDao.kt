package com.example.lockedout

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameStateDao {
    //select actionList from game_state where id = 1
    @Query("SELECT action_list FROM game_state WHERE gameId = 1")
    fun actionList(): LiveData<List<String>>

    @Query("SELECT * FROM game_state")
    fun getGameState(): LiveData<List<GameState>>

    //get haveKey from game_state where id = 1
    @Query("SELECT have_key FROM game_state WHERE gameId = 1")
    fun haveKey(): LiveData<Boolean>

    //get haveLadder from game_state where id = 1
    @Query("SELECT have_ladder FROM game_state WHERE gameId = 1")
    fun haveLadder(): LiveData<Boolean>

    //get haveHammer from game_state where id = 1
    @Query("SELECT have_hammer FROM game_state WHERE gameId = 1")
    fun haveHammer(): LiveData<Boolean>

    @Update
    fun update(gameState: GameState)

}
