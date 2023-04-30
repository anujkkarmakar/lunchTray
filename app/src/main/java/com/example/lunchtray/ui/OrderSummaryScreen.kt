package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.data.OrderUiState
import com.example.lunchtray.datasource.DataSource

@Composable
fun OrderSummaryScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onCancelClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.order_summary),
            style = MaterialTheme.typography.titleMedium
        )
        //1.
        orderUiState.apply {

            entreeItem?.let { DisplayItemDetails(name = entreeItem.name, price = it.getFormattedPrice()) }
            sideDishItem?.let { DisplayItemDetails(name = sideDishItem.name, price = it.getFormattedPrice()) }
            accompanimentItem?.let {
                DisplayItemDetails(
                    name = it.name,
                    price = accompanimentItem.getFormattedPrice()
                )
            }

            Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
            //2.
            DisplayPriceDetails(
                name = stringResource(id = R.string.subtotal),
                price = itemTotalPrice.formatPrice()
            )
            DisplayPriceDetails(
                name = stringResource(id = R.string.tax),
                price = orderTax.formatPrice()
            )
            DisplayPriceDetails(
                name = stringResource(id = R.string.total),
                price = orderTotalPrice.formatPrice()
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        //3.
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelClicked
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextClicked
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun DisplayItemDetails(
    name: String,
    price: String
) {
    Row() {
        Text(text = name)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = price)
    }
}

@Composable
fun DisplayPriceDetails(
    name: String,
    price: String
) {
    Row() {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = name,
            style = if (name == "Total") MaterialTheme.typography.labelMedium else MaterialTheme.typography.labelSmall
        )
        Text(
            text = price,
            style = if (name == "Total") MaterialTheme.typography.labelMedium else MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Composable
fun CheckoutScreenPreview() {
    OrderSummaryScreen(
        orderUiState = OrderUiState(
            entreeItem = DataSource.entreeMenuItems[0],
            sideDishItem = DataSource.sideDishMenuItems[0],
            accompanimentItem = DataSource.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        onNextClicked = {},
        onCancelClicked = {}
    )
}