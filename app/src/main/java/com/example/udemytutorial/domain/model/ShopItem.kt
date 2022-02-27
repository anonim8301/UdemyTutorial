package com.example.udemytutorial.domain.model

import com.example.udemytutorial.common.CONSTANTS.UNDEFINED_ID

data class ShopItem(
    val name: String,
    val count: Int,
    val isActive: Boolean,
    var id: Int = UNDEFINED_ID,
)
