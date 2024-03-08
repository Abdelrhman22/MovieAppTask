package com.task.movieapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.task.movieapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater)

        val clickedMovie = DetailsFragmentArgs.fromBundle(requireArguments()).movieInfoEntity

        binding.movieItem = clickedMovie

        binding.tvToolbarTitle.text = clickedMovie.title

        binding.ivDismiss.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }


}