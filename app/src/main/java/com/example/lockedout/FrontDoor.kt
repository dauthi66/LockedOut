package com.example.lockedout

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.lockedout.databinding.FragmentFrontDoorBinding




// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FrontDoor.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FrontDoor : Fragment() {
    //create binding
    private var _binding: FragmentFrontDoorBinding? = null
    // _variable!! means it has to not be null to continue
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFrontDoorBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnFrontDoorToYard.setOnClickListener {
            view.findNavController().navigate(R.id.action_frontDoor_to_backYard)
        }
        binding.btnFrontDoorToToolShed.setOnClickListener {
            view.findNavController().navigate(R.id.action_frontDoor_to_toolShed)
        }

        //instantiate database
        val application = requireNotNull(this.activity).application
        val dao = GameStateDatabase.getInstance(application).gameStateDao

        val gameState = GameState(haveKey = true)
        //add to database if it doesn't exist
        saveGameState(gameState)

        //Toast.makeText(activity,"working", Toast.LENGTH_LONG).show()
//        insertGameState()
//        dao.update(gameState)
//        dao.updateHaveKey(true)
            dao.update(gameState)


//        var haveKey = dao.haveKey()
//
//        binding.btnEnter.setOnClickListener {
//            var userInput = binding.userInput.text
//
//            if (userInput.equals("open door")) {
//                if (haveKey) {
//                    binding.txtTextView.text = "You have unlocked the door"
//                } else {
//                    binding.txtTextView.text = "With a few knob jiggles, you confirm, the door is indeed locked. Great"
//                }
//            }
//            else {
//                binding.txtTextView.text = "I'm not sure how that would help"
//            }
//
//            binding.userInput.clearFocus()
//        }

        binding.txtTextView.setOnClickListener() {
            if (!binding.txtTextView.text.equals(getString(R.string.front_door))) {
                binding.txtTextView.text = getString(R.string.front_door)
            }

        }
        return view
    }

    private fun saveGameState (gameState: GameState) {
        class SaveGameState : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                GameStateDatabase.getInstance(requireNotNull(activity).application)
                    .gameStateDao.insert(gameState)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                Toast.makeText(activity, "GameState saved", Toast.LENGTH_LONG).show()
            }
        }
        SaveGameState()
    }

    //NEEDS TO BE OUT OF MAIN BRANCH!
//    private fun insertGameState() {
//        val application = requireNotNull(this.activity).application
//        val dao = GameStateDatabase.getInstance(application).gameStateDao
//        //change haveKey to true and update database
//        val gameState = GameState(haveKey = true)
//////        dao.update(gameState)
//////        dao.updateHaveKey(true)
////        dao.insert(gameState)
//
//        dao.insert(gameState)
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FrontDoor.
         */
        // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        FrontDoor().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}