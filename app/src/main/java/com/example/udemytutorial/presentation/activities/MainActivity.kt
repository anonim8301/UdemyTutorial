package com.example.udemytutorial.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.udemytutorial.R
import com.example.udemytutorial.common.CONSTANTS.MAX_POOL_SIZE
import com.example.udemytutorial.common.CONSTANTS.VIEW_TYPE_DISABLED
import com.example.udemytutorial.common.CONSTANTS.VIEW_TYPE_ENABLED
import com.example.udemytutorial.presentation.MainViewModel
import com.example.udemytutorial.presentation.ShopListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun setupRecycleView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_ENABLED, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_TYPE_DISABLED, MAX_POOL_SIZE)
        }
        setOnLongClickListener()
        setClickListener()
        setSwipeListener(rvShopList)
    }

    private fun setSwipeListener(rvShopList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setClickListener() {
        shopListAdapter.onShopItemClickListener = {
            viewModel.editShopItemUseCase(it)
        }
    }

    private fun setOnLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeIsActiveState(it)
        }
    }
}