package com.example.characters.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.domain.usecases.GetPaginatedCharactersSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getPaginatedCharactersSource: GetPaginatedCharactersSource
) : ViewModel() {

    val characters = Pager(config = PagingConfig(20, enablePlaceholders = false),
        pagingSourceFactory = { getPaginatedCharactersSource }
    ).liveData.cachedIn(viewModelScope)
}