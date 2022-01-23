package com.example.characters.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.characters.databinding.FragmentDetailBinding
import com.example.domain.models.Character
import com.example.utils.BaseActivity
import com.example.utils.DataStatus
import com.example.utils.buildImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    companion object {
        const val KEY_ARG_CHARACTER_ID = "characterId"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments?.getInt(KEY_ARG_CHARACTER_ID) ?: return
        viewModel.getCharacterById(characterId)

        viewModel.character.observe(viewLifecycleOwner) {
            when (it.status) {
                DataStatus.Status.LOADING -> showLoading()
                DataStatus.Status.SUCCESS -> showSuccess(it.data)
                DataStatus.Status.ERROR -> showError()
            }
        }
    }

    private fun showSuccess(data: Character?) {
        data?.let {
            val imageUrl = buildImageUrl(it.thumbnail.path, it.thumbnail.extension)

            if (it.description.isNotEmpty()) binding.characterDescription.text = it.description
            context?.let { ctx -> Glide.with(ctx).load(imageUrl).into(binding.characterImage) }
            (activity as BaseActivity).setToolbarTitle(it.name)
            binding.characterName.text = it.name

            binding.characterDetailLoading.visibility = View.GONE
            binding.characterDetailInfo.visibility = View.VISIBLE
        }
    }

    private fun showLoading() {
        binding.characterDetailLoading.visibility = View.VISIBLE
        binding.characterDetailInfo.visibility = View.GONE
    }

    private fun showError() {
        binding.characterDetailInfo.visibility = View.VISIBLE
    }
}