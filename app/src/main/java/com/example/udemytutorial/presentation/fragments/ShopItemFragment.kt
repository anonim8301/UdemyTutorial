package com.example.udemytutorial.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.udemytutorial.common.CONSTANTS.UNDEFINED_ID
import com.example.udemytutorial.databinding.FragmentShopItemBinding
import com.example.udemytutorial.presentation.view_models.ShopItemViewModel

class ShopItemFragment : Fragment() {
    private lateinit var viewModel: ShopItemViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var _binding: FragmentShopItemBinding? = null
    private val binding: FragmentShopItemBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    private var screenMode: String = UNKNOWN_MODE
    private var shopItemId: Int = UNDEFINED_ID

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentShopItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangedListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.screenCanBeFinished.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDITING -> launchEditMode()
            MODE_ADDING -> launchAddMode()
        }
    }

    private fun addTextChangedListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }
        })

        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputCount()
            }
        })
    }

    private fun launchEditMode() {
        viewModel.getShopItem(shopItemId)
        binding.saveButton.setOnClickListener {
            viewModel.editShopItem(binding.etName.text?.toString(),
                binding.etCount.text?.toString())
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            viewModel.addShopItem(binding.etName.text?.toString(), binding.etCount.text?.toString())
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDITING && mode != MODE_ADDING) {
            throw RuntimeException("From fragment Unknown screen mode: $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDITING) {
            if (!args.containsKey(SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(SHOP_ITEM_ID, UNDEFINED_ID)
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_ADDING = "mode_adding"
        private const val MODE_EDITING = "mode_editing"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val UNKNOWN_MODE = ""

        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADDING)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDITING)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }
}