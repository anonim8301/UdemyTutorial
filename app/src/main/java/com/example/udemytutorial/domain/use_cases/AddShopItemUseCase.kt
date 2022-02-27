package com.example.udemytutorial.domain.use_cases

import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

class AddShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}