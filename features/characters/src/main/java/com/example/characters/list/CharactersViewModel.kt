package com.example.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Character
import com.example.domain.usecases.GetCharactersUseCase
import com.example.utils.LiveDataStatus
import com.example.utils.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _characters: MutableLiveDataStatus<List<Character>> by lazy { MutableLiveDataStatus() }
    val characters: LiveDataStatus<List<Character>> get() = _characters

    init {
        _characters.postLoading()

        viewModelScope.launch {
            getCharactersUseCase.getCharacters().catch { error ->
                _characters.postError(error)
            }.collect {
                _characters.postSuccess(it.characters)
            }
        }
    }
}