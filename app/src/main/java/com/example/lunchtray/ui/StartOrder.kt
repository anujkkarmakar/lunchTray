package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.R

@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.widthIn(min = 250.dp),
            onClick = onButtonClick
        ) {
            Text(text = stringResource(id = R.string.start_order))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartOrderScreenPreview() {
    StartOrderScreen(

    )
}