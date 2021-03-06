package com.example.lockedout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.example.lockedout.databinding.FragmentFrontYardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FrontYard.newInstance] factory method to
 * create an instance of this fragment.
 */
class FrontYard : Fragment() {
    //create binding
    private var _binding: FragmentFrontYardBinding? = null
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

        _binding = FragmentFrontYardBinding.inflate(inflater, container, false)
        val view = binding.root

        setupTutorialButton()

        var haveKey = false
        var haveHammer = false
        var haveLadder = false

        var action = FrontYardDirections.actionFrontYardToFrontDoor(haveKey, haveHammer, haveLadder)
        binding.btnFrontYardToDoor.setOnClickListener {
            view.findNavController().navigate(action)
        }
        return view
    }

    private fun setupTutorialButton() {
        binding.btnTutorial.setOnClickListener {
            if (!binding.txtTextView.text.equals(getString(R.string.tutorial))){
                binding.txtTextView.text = getString(R.string.tutorial)
                binding.btnTutorial.text = getString(R.string.back)
            }
            else{
                binding.txtTextView.text = getString(R.string.front_yard)
                binding.btnTutorial.text = getString(R.string.tutorial_button)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FrontYard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FrontYard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}