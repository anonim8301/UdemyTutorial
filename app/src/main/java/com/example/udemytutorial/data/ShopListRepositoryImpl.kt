package com.example.udemytutorial.data

import com.example.udemytutorial.common.CONSTANTS.UNDEFINED_ID
import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.repository.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override suspend fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItemById(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override suspend fun getShopItemById(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId was not found")
    }

    override suspend fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}