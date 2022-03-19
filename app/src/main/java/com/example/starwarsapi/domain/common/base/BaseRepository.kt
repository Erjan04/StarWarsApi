package com.example.starwarsapi.domain.common.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.starwarsapi.data.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <D> taskExecution(execution: suspend () -> D): Flow<Resource<D>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(data = execution()))
            } catch (exception: Exception) {
                emit(Resource.Error(data = null, message = exception.localizedMessage ?: "Error"))
            }
        }
    }

    protected fun <ValueDto : Any, Value : Any> executionPaging(
        pagingSource: BasePagingSource<ValueDto, Value>,
        pageSize: Int = 10,
        distance: Int = pageSize,
        maxSize: Int = Int.MAX_VALUE,
        loadSize: Int = pageSize * 3,
        jumping: Int = Int.MIN_VALUE,
        enableHolders: Boolean = true
    )
            : Flow<PagingData<Value>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                distance,
                enableHolders,
                loadSize,
                maxSize,
                jumping
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }

}