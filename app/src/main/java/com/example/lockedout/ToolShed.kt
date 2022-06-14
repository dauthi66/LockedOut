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
import com.example.lockedout.databinding.FragmentToolShedBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToolShed.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToolShed : Fragment() {
    //create binding
    private var _binding: FragmentToolShedBinding? = null
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
        _binding = FragmentToolShedBinding.inflate(inflater, container, false)
        val view = binding.root

        setUpTryAgainButton(view)

        //check what items the player has
        var haveKey = ToolShedArgs.fromBundle(requireArguments()).haveKey
        var haveHammer = ToolShedArgs.fromBundle(requireArguments()).haveHammer
        var haveLadder = ToolShedArgs.fromBundle(requireArguments()).haveLadder

        //display in a toast all the items the player has
        itemDisplay(haveKey, haveHammer, haveLadder)

        var toFrontDoor = ToolShedDirections.actionToolShedToFrontDoor(haveKey, haveHammer, haveLadder)
        binding.btnShedToFrontDoor.setOnClickListener {
            view.findNavController().navigate(toFrontDoor)
        }

        binding.btnEnter.setOnClickListener {
            val userInput = binding.userInput.text.toString()

            if (userInput.equals("open shed", true)) {
                if (haveHammer) {
                    binding.txtTextView.text = "You already grabbed a hammer, that should be good enough."
                } else {
                    binding.txtTextView.text = "It slides open easily, luckily it isn't locked. Dad must have forgot to lock it. \n\n You take a hammer that looks like it could be useful"
                    //save it hammer in the bundles
                    haveHammer = true
                    toFrontDoor = ToolShedDirections.actionToolShedToFrontDoor(haveKey, haveHammer, haveLadder)
                }
            }
            else if ( userInput.equals("use ladder window", true)) {
                if (haveLadder) {
                    binding.txtTextView.text = "You slide the old ladder up to the side of the house. " +
                            "Despite securing it, it still seems to be wobbly. You head up it as it creaks and moans, wobbling under your weight." +
                            "You suddenly lose balance, and end up on your back on the ground. Your arm hurts bad, it could be broken, what were you thinking? \n\n You Lose!"
                    endGame()
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
            binding.btnShedToFrontDoor.isVisible = false
            view.findNavController().navigate(R.id.action_toolShed_to_frontYard)
        }
    }

    //give visual that game is over
    private fun endGame() {
        binding.btnTryAgain.isVisible = true
        binding.btnEnter.isVisible = false
        binding.btnShedToFrontDoor.isVisible = false
    }

    companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToolShed.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
        ToolShed().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}