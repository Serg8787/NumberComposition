package com.tsybulnik.numbercomposition.presentaion

import android.os.Bundle
import android.security.keystore.KeyNotYetValidException
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.databinding.FragmentChooseLevelBinding
import com.tsybulnik.numbercomposition.databinding.FragmentGameFinishedBinding
import com.tsybulnik.numbercomposition.databinding.FragmentWelcomeBinding
import com.tsybulnik.numbercomposition.domain.entitities.GameResult
import java.lang.RuntimeException


/**
 * A simple [Fragment] subclass.
 * Use the [GameFinishedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFinishedFragment : Fragment() {
    private lateinit var gameResult: GameResult
    private  var _binding : FragmentGameFinishedBinding? = null
    private val binding : FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               retryGame()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArguments(){
        gameResult = requireArguments().getSerializable(KEY_RESULT) as GameResult
    }

    private fun retryGame(){
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,0)

    }


    companion object{
        private const val KEY_RESULT = "key_result"
        fun newInstance(gameResult: GameResult):GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_RESULT,gameResult)
                }
            }

        }
    }


}