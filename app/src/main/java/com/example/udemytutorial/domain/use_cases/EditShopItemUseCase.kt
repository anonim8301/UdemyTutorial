package com.example.udemytutorial.domain.use_cases

import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

class EditShopItemUseCase(
    private val shopListRepository: ShopListRepository
) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}