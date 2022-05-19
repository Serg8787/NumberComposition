package com.tsybulnik.numbercomposition.presentaion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.databinding.FragmentChooseLevelBinding
import com.tsybulnik.numbercomposition.domain.entitities.Level
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseLevelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseLevelFragment : Fragment() {
    private  var _binding : FragmentChooseLevelBinding? = null
    private val binding : FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding = null")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelTest.setOnClickListener {
            launchGameFragment(Level.TEST)

        }
        binding.buttonLevelEasy.setOnClickListener {    launchGameFragment(Level.EASY) }
        binding.buttonLevelNormal.setOnClickListener {    launchGameFragment(Level.MIDDLE) }
        binding.buttonLevelHard.setOnClickListener {    launchGameFragment(Level.HARD) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchGameFragment(level: Level){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,GameFragment.newInstance(level))
            .addToBackStack(null).commit()
    }
    companion object{
        fun newInstance():ChooseLevelFragment{
            return ChooseLevelFragment()

        }
    }
}