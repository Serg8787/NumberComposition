package com.tsybulnik.numbercomposition.presentaion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tsybulnik.numbercomposition.R
import com.tsybulnik.numbercomposition.databinding.FragmentGameBinding
import com.tsybulnik.numbercomposition.databinding.FragmentGameFinishedBinding
import java.lang.RuntimeException
import java.util.logging.Level


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private  var _binding : FragmentGameBinding? = null
    private val binding : FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding = null")

    private lateinit var level:Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        purseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    private fun purseArgs (){
        level = requireArguments().getSerializable(KEY_LEVEL) as Level

    }

    companion object{
        const val KEY_LEVEL = "level"
        fun newInstance(level: com.tsybulnik.numbercomposition.domain.entitities.Level) :GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL,level)

                }
            }

        }
    }



}