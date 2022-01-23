package com.example.characters.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characters.databinding.ItemCharacterBinding
import com.example.domain.models.Character
import com.example.utils.buildImageUrl

class CharactersRvAdapter : RecyclerView.Adapter<CharactersRvAdapter.CharacterViewHolder>() {

    var items = mutableListOf<Character>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: (Character) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
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
}