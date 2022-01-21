package com.afrakhteh.sweetlandapp.model.useCase.sweets

import com.afrakhteh.sweetlandapp.di.scope.UseCaseScope
import com.afrakhteh.sweetlandapp.model.entities.SweetsEntity
import com.afrakhteh.sweetlandapp.model.entities.dto.SweetsDto
import com.afrakhteh.sweetlandapp.model.repository.db.DbRepository
import com.afrakhteh.sweetlandapp.model.repository.network.MainRepository
import com.afrakhteh.sweetlandapp.util.Mapper
import com.afrakhteh.sweetlandapp.util.NetworkResponse
import io.reactivex.Observable
import javax.inject.Inject

@UseCaseScope
class GetAllSweetsUseCase @Inject constructor(
    private val repository: MainRepository,
    private val mapper: Mapper<SweetsEntity, SweetsDto>
) {

    operator fun invoke(): Observable<NetworkResponse<SweetsEntity>> {
        return repository.getAllSweets()
            .toObservable()
            .map {
                NetworkResponse.Success(
                    it.map { dto ->
                        mapper.convertSecondObjectToFirst(dto)
                    }

                ) as NetworkResponse<SweetsEntity>
            }
            .onErrorReturn {
                NetworkResponse.Error<SweetsEntity>(message = it.toString())
            }
            .startWith(NetworkResponse.Loading<SweetsEntity>())
    }
}