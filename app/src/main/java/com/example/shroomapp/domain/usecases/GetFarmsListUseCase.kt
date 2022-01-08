package com.example.shroomapp.domain.usecases

import com.example.shroomapp.domain.Repository

class GetFarmsListUseCase(private val repository: Repository) {

    operator fun invoke() = repository.getFarmsList()
}