package com.example.characters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characters.databinding.ItemCharacterBinding
import com.example.domain.models.Character
import com.example.utils.buildImageUrl

class CharactersRvAdapter : PagingDataAdapter<Character, CharactersRvAdapter.CharacterViewHolder>(DIFF) {

    var itemClickListener: (Character) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class CharacterViewHolder(private val viewBinding: ItemCharacterBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(character: Character) {
            val context = viewBinding.root.context
            with(viewBinding) {
                val imageUrl = buildImageUrl(character.thumbnail.path, character.thumbnail.extension)

                Glide.with(context).load(imageUrl).into(viewBinding.characterImage)
                characterName.text = character.name

                viewBinding.root.setOnClickListener {
                    itemClickListener.invoke(character)
                }
            }
        }
    }

    object DIFF : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean = oldItem == newItem
    }

}
