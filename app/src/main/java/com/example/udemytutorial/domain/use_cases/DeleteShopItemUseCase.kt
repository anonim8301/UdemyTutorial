package com.example.udemytutorial.domain.use_cases

import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

class DeleteShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}