package com.example.domain.usecases

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.service.MarvelService
import com.example.domain.mappers.Mapper
import com.example.domain.models.Character
import java.io.IOException
import javax.inject.Inject

class GetPaginatedCharactersSource @Inject constructor(
    private val marvelService: MarvelService,
    private val mapper: Mapper
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: 0

        return try {
            val response = marvelService.getCharacters(position)
            val mappedResponse = response.characterDataContainer.characters.map { mapper.map(it) }

            val nextKey = if (mappedResponse.isEmpty()) {
                null
            } else {
                position + response.characterDataContainer.count
            }

            LoadResult.Page(data = mappedResponse, prevKey = if (position == 0) null else position, nextKey = nextKey)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(3)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(3)
        }
    }

}