package com.example.udemytutorial.domain.use_cases

import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

class GetShopItemByIdUseCase(
    private val shopListRepository: ShopListRepository,
) {

    fun getShopItemById(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemById(shopItemId)
    }
}