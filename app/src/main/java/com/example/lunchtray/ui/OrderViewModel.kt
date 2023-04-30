package com.example.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.example.lunchtray.data.OrderUiState
import com.example.lunchtray.model.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    private val taxRate = 0.98

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Reset the order when the user clicks Cancel or Finishes the Order
     */
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    fun updateEntree(entree: MenuItem.EntreeItem) {
        val previousEntree = _uiState.value.entreeItem
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: MenuItem.SideDishItem) {
        val previousSideDish = _uiState.value.sideDishItem
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompanimentItem
        updateItem(accompaniment, previousAccompaniment)
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entreeItem = if (newItem is MenuItem.EntreeItem) newItem else currentState.entreeItem,
                sideDishItem = if (newItem is MenuItem.SideDishItem) newItem else currentState.sideDishItem,
                accompanimentItem = if (newItem is MenuItem.AccompanimentItem) newItem else currentState.accompanimentItem
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}