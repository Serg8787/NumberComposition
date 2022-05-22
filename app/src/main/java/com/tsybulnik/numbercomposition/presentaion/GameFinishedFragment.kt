package com.tsybulnik.numbercomposition.presentaion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.databinding.FragmentGameFinishedBinding
import com.tsybulnik.numbercomposition.domain.entitities.GameResult
import java.lang.RuntimeException



class GameFinishedFragment : Fragment() {
    private val args by navArgs<GameFinishedFragmentArgs>()

    private  var _binding : FragmentGameFinishedBinding? = null
    private val binding : FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding = null")





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        binding.gameResult = args.gameResult

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun retryGame(){
        findNavController().popBackStack()

    }


//    }
//    private fun getSmileResId(): Int {
//        return if (args.gameResult.winner) {
//            R.drawable.icons8_smile
//        } else {
//            R.drawable.icons8_sad
//        }
//    }
//    private fun getPercentOfRightAnswers() = with(args.gameResult) {
//        val countOfQuestions = args.gameResult.countOfQustion
//        if (countOfQuestions == 0) {
//            0
//        } else {
//            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
//        }
//    }
    private fun setupClickListeners() {

        binding.buttonRetry.setOnClickListener {
            retryGame()
            Log.d("finish","papa")

        }
    }

}