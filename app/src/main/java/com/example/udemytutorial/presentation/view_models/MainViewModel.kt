package com.example.udemytutorial.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.udemytutorial.data.ShopListRepositoryImpl
import com.example.udemytutorial.domain.model.ShopItem
import com.example.udemytutorial.domain.use_cases.DeleteShopItemUseCase
import com.example.udemytutorial.domain.use_cases.EditShopItemUseCase
import com.example.udemytutorial.domain.use_cases.GetShopItemByIdUseCase
import com.example.udemytutorial.domain.use_cases.GetShopListUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()


    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeIsActiveState(shopItem: ShopItem) {
        val newItem = shopItem.copy(isActive = !shopItem.isActive)
        editShopItemUseCase.editShopItem(newItem)
    }

    fun editShopItemUseCase(shopItem: ShopItem){
        Log.d("editShopItem" , shopItem.toString())
    }
}