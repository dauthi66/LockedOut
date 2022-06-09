package com.example.lockedout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.lockedout.databinding.FragmentFrontDoorBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FrontDoor.newInstance] factory method to
 * create an instance of this fragment.
 */
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

//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "database-name"
//        ).build()
//
//        val application = requireNotNull(this.activity).application
//        val dao = GameStateDatabase.getInstance(application).gameStateDao
//
//        val userDao = db.userDao()
//        val users: List<User> = userDao.getAll()

        // Set the lifecycleOwner to the lifecycle of the view
//        val application = requireNotNull(this.activity).application
//        // Get the ViewModel
//        val dao = GameStateDatabase.getInstance(application).gameStateDao
//        val viewModelFactory = GameStateViewModelFactory(dao)
//        val viewModel = ViewModelProvider(
//           this, viewModelFactory).get(GameStateViewModel::class.java)
//
//        // print the gameState to the console
//        viewModel.gameState.value?.let { println(it) }

        return view
    }

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