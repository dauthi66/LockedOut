package com.example.lockedout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
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

        setUpTryAgainButton(view)

        //check what items the player has
        var haveKey = BackYardArgs.fromBundle(requireArguments()).haveKey
        var haveHammer = BackYardArgs.fromBundle(requireArguments()).haveHammer
        var haveLadder = BackYardArgs.fromBundle(requireArguments()).haveLadder

        //save those items for the next fragment
        var toFrontDoor = BackYardDirections.actionBackYardToFrontDoor(haveKey, haveHammer, haveLadder)

        //display in a toast all the items the player has
        itemDisplay(haveKey, haveHammer, haveLadder)

        //navigation for button
        binding.btnBackyardToFrontDoor.setOnClickListener {
            view.findNavController().navigate(toFrontDoor)
        }

        binding.btnEnter.setOnClickListener {
            var userInput = binding.userInput.text.toString()

            if (userInput.equals("take ladder", true)) {
                if (haveLadder) {
                    binding.txtTextView.text = "You already took the ladder... and you look suspicious carrying it everywhere too"
                } else {
                    binding.txtTextView.text = "You awkwardly grab the ladder with both hands, and haul it along side you"
                    //save hammer in the bundles
                    haveLadder = true
                    toFrontDoor = BackYardDirections.actionBackYardToFrontDoor(haveKey, haveHammer, haveLadder)
                }
            }
            else if ( userInput.equals("use hammer window", true)) {
                if (haveHammer) {
                    binding.txtTextView.text = "You pull out the hammer and cringe as you shatter it with the hammer." +
                            "Unfortunately a neighbor also notices, yells, and pulls out his cell to call the cops." +
                            "This probably wasn't a great idea... \n You Lose!"
                    endGame()

                }
            }
            else if ( userInput.equals("use hammer door", true)) {
                if (haveHammer) {
                    binding.txtTextView.text = getString(R.string.use_hammer_door_result)
                }
            }
            else {
                binding.txtTextView.text = getString(R.string.unknown_text_response)
            }
        }

        return view
    }

    private fun itemDisplay(
        haveKey: Boolean,
        haveHammer: Boolean,
        haveLadder: Boolean
    ) {
        var items = mutableListOf<String>()
        if (haveKey) {
            items.add("key")
        }
        if (haveHammer) {
            items.add("hammer")
        }
        if (haveLadder) {
            items.add("ladder")
        }
        //display list in a toast if the player has any items, add and between each item add a comma
        if (items.size > 0) {
            Toast.makeText(
                context,
                "You have: a " + items.joinToString(", and a "),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setUpTryAgainButton(view: ConstraintLayout) {
        binding.btnTryAgain.setOnClickListener {
            binding.btnTryAgain.isVisible = true
            binding.btnEnter.isVisible = false
            binding.btnBackyardToFrontDoor.isVisible = false
            view.findNavController().navigate(R.id.action_backYard_to_frontYard)
        }
    }

    //give visual that game is over
    private fun endGame() {
        binding.btnTryAgain.isVisible = true
        binding.btnEnter.isVisible = false
        binding.btnBackyardToFrontDoor.isVisible = false
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