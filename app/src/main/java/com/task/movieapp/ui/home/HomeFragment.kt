package com.task.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.task.movieapp.data.model.RequestState
import com.task.movieapp.databinding.FragmentHomeBinding
import com.task.movieapp.ui.MoviesViewModel
import com.task.movieapp.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromApi()
        onBackPressed()
    }

    private fun getDataFromApi() {
        moviesViewModel.getMoviesList()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.moviesList.collect { requestState ->
                    when (requestState) {
                        is RequestState.Loading -> {

                        }

                        is RequestState.Success -> {

                        }

                        is RequestState.Error -> {
                            showSnackBar(
                                message = requestState.exception.message ?: "General Error message",
                                duration = Snackbar.LENGTH_LONG
                            )
                        }
                    }
                }
            }
        }
    }


    private fun onBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}