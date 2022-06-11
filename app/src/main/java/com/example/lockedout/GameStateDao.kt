package com.example.lockedout

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.*
import java.util.*

@Dao
interface GameStateDao {
//    @Query("SELECT action_list FROM game_state WHERE gameId = 1")
//    fun actionList(): LiveData<String>
    @Query("UPDATE game_state SET have_key =:haveKey WHERE gameId = 0")
    fun updateHaveKey(haveKey : Boolean)
    //get gameState
    @Query("SELECT * FROM game_state WHERE gameId = 0")
    fun getGameState(): GameState

    //get haveKey from game_state where id = 1
    @Query("SELECT have_key FROM game_state WHERE gameId = 0")
    fun haveKey(): Boolean

    //get haveLadder from game_state where id = 1
    @Query("SELECT have_ladder FROM game_state WHERE gameId = 0")
    fun haveLadder(): Boolean

    //get haveHammer from game_state where id = 1
    @Query("SELECT have_hammer FROM game_state WHERE gameId = 0")
    fun haveHammer(): Boolean

    @Update
    fun update(gameState: GameState)

    @Insert
    fun insert(gameState: GameState)

    //get haveKey from liveData, convert it to a boolean and return it
//    fun getHaveKey(): Boolean {
//        var haveKey = false;
//        //remove haveHammer from liveData wrapper
//        val haveHammerLiveData = Transformations.map(haveKey()) {
//            haveKey = it
//        }
//        return haveKey
//    }
//
//    fun getHaveLadder(): Boolean {
//        var haveLadder = false;
//        //remove haveHammer from liveData wrapper
//        val haveHammerLiveData = Transformations.map(haveLadder()) {
//            haveLadder = it
//        }
//        return haveLadder
//    }
//
//    fun getHaveHammer(): Boolean {
//        var haveHammer = false;
//        //remove haveHammer from liveData wrapper
//        val haveHammerLiveData = Transformations.map(haveHammer()) {
//            haveHammer = it
//        }
//        return haveHammer
//    }

//    fun getActionList(): ArrayList<String> {
//        //create an array list of strings
//        val actionList = ArrayList<String>()
//        var action = ""
//
//        Transformations.map(actionList()) {
//            action = it
//        }
//
//        for (word in action.split(" ")) {
//            actionList.add(word)
//        }
//
//        return actionList
//    }
}
