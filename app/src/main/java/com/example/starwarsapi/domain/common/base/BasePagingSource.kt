package com.example.starwarsapi.domain.common.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsapi.data.common.response.StarWarsResponse
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE = 1

abstract class BasePagingSource<ValueDto : Any, Value : Any>(
    private val request: suspend (position: Int) -> StarWarsResponse<ValueDto>,
    private val mappedData: (data: List<ValueDto>) -> List<Value>
) : PagingSource<Int, Value>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val position = params.key ?: START_PAGE
        return try {
            val response = request(position)
            val next = response.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(response.next).getQueryParameter("page")!!.toInt()
            }

            LoadResult.Page(
                data = mappedData(response.results),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}