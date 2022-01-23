package com.example.characters.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Character
import com.example.domain.usecases.GetCharacterByIdUseCase
import com.example.utils.LiveDataStatus
import com.example.utils.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getCharacterByIdUseCaseUseCase: GetCharacterByIdUseCase) : ViewModel() {
    private val _character: MutableLiveDataStatus<Character> by lazy { MutableLiveDataStatus() }
    val character: LiveDataStatus<Character> get() = _character

    fun getCharacterById(charterId: Int) {
        _character.postLoading()

        viewModelScope.launch {
            getCharacterByIdUseCaseUseCase.getCharacterById(charterId).catch { error ->
                _character.postError(error)
            }.collect {
                _character.postSuccess(it.characters[0])
            }
        }
    }
}