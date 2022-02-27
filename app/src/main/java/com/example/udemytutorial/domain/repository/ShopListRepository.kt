package com.example.udemytutorial.domain.repository

import com.example.udemytutorial.domain.model.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItemById(shopItemId: Int): ShopItem

    suspend fun getShopList(): List<ShopItem>
}