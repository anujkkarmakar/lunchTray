package com.example.lunchtray.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lunchtray.data.OrderUiState
import com.example.lunchtray.model.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState = _uiState.asStateFlow()

    /**
     * Reset the order when the user clicks Cancel or Finishes the Order
     */
    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    fun setEntreeItem(entreeItem: MenuItem.EntreeItem) {
        _uiState.update {
            it.copy(
                entreeItem = entreeItem
            )
        }
    }

    fun setSideDishItem(sideDishItem: MenuItem.SideDishItem) {
        _uiState.update {
            it.copy(
                sideDishItem = sideDishItem
            )
        }
    }

    fun setAccompanimentItem(accompanimentItem: MenuItem.AccompanimentItem) {
        _uiState.update {
            it.copy(
                accompanimentItem = accompanimentItem
            )
        }
    }
}