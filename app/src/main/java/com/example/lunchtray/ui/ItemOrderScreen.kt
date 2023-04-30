package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.model.MenuItem

@Composable
fun ItemOrderScreen(
    modifier: Modifier = Modifier,
    onCancelClicked: () -> Unit = {},
    onNextClicked: () -> Unit = {},
    onSelectionChanged: (MenuItem) -> Unit = {},
    list: List<MenuItem> = listOf()
) {
    Column(modifier = modifier) {
        var selectedValue by rememberSaveable {
            mutableStateOf("")
        }
        // TODO: Implement the List with the proper items placed
        list.forEach { item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedValue == item.name,
                    onClick = {
                        selectedValue = item.price.toString()
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == item.name,
                    onClick = {
                        selectedValue = item.price.toString()
                        onSelectionChanged(item)
                    }
                )
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        text = item.name,
                        modifier = modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = item.description,
                        modifier = modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = item.getFormattedPrice(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
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

@Preview(showBackground = true)
@Composable
fun ItemOrderScreenPreview() {
    ItemOrderScreen(
        list = DataSource.entreeMenuItems
    )
}
