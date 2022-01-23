package com.example.characters.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.characters.R
import com.example.characters.databinding.FragmentMasterBinding
import com.example.characters.detail.DetailFragment.Companion.KEY_ARG_CHARACTER_ID
import com.example.domain.models.Character
import com.example.utils.DataStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private val viewModel: CharactersViewModel by viewModels()

    private lateinit var binding: FragmentMasterBinding
    private var adapter = CharactersRvAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMasterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCharacters.layoutManager = GridLayoutManager(context, 3)
        binding.rvCharacters.adapter = adapter

        viewModel.characters.observe(viewLifecycleOwner) {
            when (it.status) {
                DataStatus.Status.LOADING -> showLoading()
                DataStatus.Status.SUCCESS -> showSuccess(it.data)
                DataStatus.Status.ERROR -> showError()
            }
        }

        adapter.itemClickListener = {
            val bundle = bundleOf(KEY_ARG_CHARACTER_ID to it.id)
            findNavController().navigate(R.id.detailFragment, bundle)
        }
    }

    private fun showSuccess(data: List<Character>?) {
        data?.let {
            binding.charactersLoading.visibility = View.GONE
            binding.rvCharacters.visibility = View.VISIBLE
            adapter.items = it.toMutableList()
        }
    }

    private fun showLoading() {
        binding.charactersLoading.visibility = View.VISIBLE
        binding.rvCharacters.visibility = View.GONE
    }

    private fun showError() {
        binding.rvCharacters.visibility = View.VISIBLE
    }
}