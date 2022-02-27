package com.example.udemytutorial.domain.use_cases

import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

class GetShopListUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}