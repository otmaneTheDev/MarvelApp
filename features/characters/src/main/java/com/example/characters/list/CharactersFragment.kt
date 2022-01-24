package com.example.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.characters.R
import com.example.characters.databinding.FragmentCharactersBinding
import com.example.characters.detail.DetailFragment.Companion.KEY_ARG_CHARACTER_ID
import com.example.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var binding: FragmentCharactersBinding
    private var adapter = CharactersRvAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {
        binding.rvCharacters.layoutManager = GridLayoutManager(context, 3)
        binding.rvCharacters.adapter = adapter

        adapter.itemClickListener = {
            val bundle = bundleOf(KEY_ARG_CHARACTER_ID to it.id)
            findNavController().navigate(R.id.detailFragment, bundle)
        }

        adapter.addLoadStateListener {
            if (it.refresh is LoadState.Loading) showLoading() else showSuccess()
            if (it.refresh is LoadState.Error) showError() else showSuccess()
        }

        binding.btnRetry.setOnClickListener {
            adapter.retry()
        }
    }

    private fun setUpObservers() {
        viewModel.characters.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }

    private fun showSuccess() {
        binding.charactersLoading.visibility = View.GONE
        binding.charactersError.visibility = View.GONE
        binding.rvCharacters.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.charactersLoading.visibility = View.VISIBLE
        binding.charactersError.visibility = View.GONE
        binding.rvCharacters.visibility = View.GONE
    }

    private fun showError() {
        binding.charactersError.visibility = View.VISIBLE
        binding.charactersLoading.visibility = View.GONE
        binding.rvCharacters.visibility = View.VISIBLE
        (activity as BaseActivity).showAlertDialog {}
    }
}