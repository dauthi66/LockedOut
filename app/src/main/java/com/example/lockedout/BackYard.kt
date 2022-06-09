package com.example.lockedout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations
import androidx.navigation.findNavController
import com.example.lockedout.databinding.FragmentBackYardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BackYard.newInstance] factory method to
 * create an instance of this fragment.
 */
class BackYard : Fragment() {
    //create binding
    private var _binding: FragmentBackYardBinding? = null
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
        _binding = FragmentBackYardBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = GameStateDatabase.getInstance(application).gameStateDao
//
//        val gameState = dao.getGameState()
//
//        System.out.println("GameState: " + gameState)

        //print out the haveKey value







        //transforms the list of tasks to a list of task name strings
//        val gameStateString = Transformations.map(gameState) {
//
//        }

//        //print out the result of the query to the console
//        println("Have hammer: $gameStateString")
//
//        val haveHammerString = Transformations.map(haveHammer) {
//                tasks -> formatTasks(tasks)
//        }
//
//        System.out.println("haveHammer: $haveHammer")
//        //have hammer is not a boolean?
//        if(haveHammer){
//            binding.txtTextView.text = "You have the hammer"
//        }
//        else{
//            binding.txtTextView.text = "You do not have the hammer"
//        }

        //binding.txtTextView.text = haveHammer.toString()

        binding.btnToolShed.setOnClickListener {
            view.findNavController().navigate(R.id.action_backYard_to_frontDoor)
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BackYard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BackYard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}