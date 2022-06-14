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

        setUpTryAgainButton(view)

        //check what items the player has
        var haveKey = FrontDoorArgs.fromBundle(requireArguments()).haveKey
        var haveHammer = FrontDoorArgs.fromBundle(requireArguments()).haveHammer
        var haveLadder = FrontDoorArgs.fromBundle(requireArguments()).haveLadder

        //display in a toast all the items the player has
        itemDisplay(haveKey, haveHammer, haveLadder)

        //send the message to action_frontDoor_to_backYard using findNavController
        var toBackYard = FrontDoorDirections.actionFrontDoorToBackYard(haveKey, haveHammer, haveLadder)
        binding.btnFrontDoorToYard.setOnClickListener {
            view.findNavController().navigate(toBackYard)
        }

        var toToolShed = FrontDoorDirections.actionFrontDoorToToolShed(haveKey, haveHammer, haveLadder)
        binding.btnFrontDoorToToolShed.setOnClickListener {
            view.findNavController().navigate(toToolShed)
        }

        binding.btnEnter.setOnClickListener {
            var userInput = binding.userInput.text.toString()

            if (userInput.equals("open door", true)) {
                if (haveKey) {
                    binding.txtTextView.text = "With a resounding \"click\" you unlock and open the door. " +
                            "With a satisfying sigh, you resign yourself to a day of gaming.\n\n You Win!"
                    endGame()
                } else {
                    binding.txtTextView.text = "With a few knob jiggles you confirm that the door is indeed locked. \n Great."
                }
            }
            else if ( userInput.equals("open mat", true)) {
                if (haveKey) {
                    binding.txtTextView.text = "You already found the key there."
                }
                else {
                    binding.txtTextView.text = "Bingo! You lift the door mat to reveal a key, mom always leaves a spare key here! \n\n You happily take the key."
                    //save it key in the bundles
                    haveKey = true
                    toBackYard = FrontDoorDirections.actionFrontDoorToBackYard(haveKey, haveHammer, haveLadder)
                    toToolShed = FrontDoorDirections.actionFrontDoorToToolShed(haveKey, haveHammer, haveLadder)
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
            binding.btnFrontDoorToYard.isVisible = false
            binding.btnFrontDoorToToolShed.isVisible = false
            view.findNavController().navigate(R.id.action_frontDoor_to_frontYard)
        }
    }

    //give visual that game is over
    private fun endGame() {
        binding.btnTryAgain.isVisible = true
        binding.btnEnter.isVisible = false
        binding.btnFrontDoorToYard.isVisible = false
        binding.btnFrontDoorToToolShed.isVisible = false
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