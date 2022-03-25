package com.example.udemytutorial.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.udemytutorial.R
import com.example.udemytutorial.common.CONSTANTS
import com.example.udemytutorial.presentation.fragments.ShopItemFragment

class ShopItemActivity : AppCompatActivity() , ShopItemFragment.OnEditingFinishedListener {

    private var screenMode = UNKNOWN_MODE
    private var shopItemId = CONSTANTS.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDITING -> ShopItemFragment.newInstanceEditItem(shopItemId)
            MODE_ADDING -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode: $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_MODE)
        if (mode != MODE_EDITING && mode != MODE_ADDING) {
            throw RuntimeException("Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDITING) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, CONSTANTS.UNDEFINED_ID)
        }
    }


    companion object {
        private const val EXTRA_MODE = "extra_mode"
        private const val MODE_ADDING = "mode_adding"
        private const val MODE_EDITING = "mode_editing"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val UNKNOWN_MODE = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_ADDING)
            return intent
        }

        fun newIntentEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_MODE, MODE_EDITING)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }

    override fun onEditingFinished() {
        finish()
    }
}