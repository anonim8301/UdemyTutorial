package com.example.udemytutorial.domain.repository

import androidx.lifecycle.LiveData
import com.example.udemytutorial.domain.model.ShopItem

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}