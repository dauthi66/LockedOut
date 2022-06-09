package com.example.lockedout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

////inherit from ViewModelProvider.Factory to be able to create ViewModel
//class GameStateViewModelFactory(private val dao: GameStateDao) : ViewModelProvider.Factory {
//    //create a new instance of the ViewModel when the app starts
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(GameStateViewModel::class.java)) {
//            //use dependency injection to pass the GameStateDao to the ViewModel
//            return GameStateViewModel(dao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel")
//    }
//}