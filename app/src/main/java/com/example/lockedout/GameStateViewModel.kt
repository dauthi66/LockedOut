package com.example.lockedout

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameStateViewModel(val dao: GameStateDao) : ViewModel() {
    val gameState = dao.getGameState()
    //transforms the list of tasks to a list of task name strings
    val gameStateString = Transformations.map(gameState) {
            gameState -> formatGameState(gameState)
    }

    fun formatGameState(tasks: List<GameState>): String {
        return tasks.fold("") {
                str, item -> str + '\n' + formatGameState(item)
        }
    }
    fun formatGameState(gameState: GameState): String {
        var str = "ID: ${gameState.gameId}"
        str += '\n' + "Name: ${gameState.actionList}"
        str += '\n' + "HasHammer: ${gameState.haveHammer}" + '\n'
        return str
    }
}