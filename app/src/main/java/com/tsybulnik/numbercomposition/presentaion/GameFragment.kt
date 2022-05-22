package com.tsybulnik.numbercomposition.presentaion

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.databinding.FragmentGameBinding
import com.tsybulnik.numbercomposition.domain.entitities.GameResult
import com.tsybulnik.numbercomposition.domain.entitities.Level


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private val args by navArgs<GameFragmentArgs>()


    private val viewModel: GameViewModel by lazy {

        ViewModelProvider(
            this,
            ViewModelFactory(args.level,requireActivity().application)
        )[GameViewModel::class.java]

    }



    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        observeViewModel()



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
//        viewModel.question.observe(viewLifecycleOwner) {
//            binding.tvSum.text = it.sum.toString()
//            binding.tvLeftNumber.text = it.visibleNumber.toString()
//            for (i in 0 until tvOptions.size) {
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }
//        viewModel.percentOfRightAnswer.observe(viewLifecycleOwner) {
//            binding.progressBar.setProgress(it, true)
//        }
//        viewModel.enoughCountOfRightAnswer.observe(viewLifecycleOwner) {
//            binding.tvAnswersProgress.setTextColor(getColorByState(it))
//        }
//        viewModel.enoughPercentOfRightAnswer.observe(viewLifecycleOwner) {
//            val color = getColorByState(it)
//            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }
//        viewModel.formattedTime.observe(viewLifecycleOwner){
//            Log.d("timer",it.toString())
//            binding.tvTimer.text = it.toString()
//        }

//        viewModel.minPercent.observe(viewLifecycleOwner){
//            binding.progressBar.secondaryProgress = it
//        }
        viewModel.gameResult.observe(viewLifecycleOwner){
            launchGameFinishedFragment(it)
        }
//        viewModel.progressAnswers.observe(viewLifecycleOwner){
//            binding.tvAnswersProgress.text = it
//        }

    }

//    private fun setOnClicksListeners(){
//        for (tvOptions in tvOptions){
//            tvOptions.setOnClickListener{
//                viewModel.chooseAnswer(tvOptions.text.toString().toInt())
//            }
//        }
//    }
    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }

}