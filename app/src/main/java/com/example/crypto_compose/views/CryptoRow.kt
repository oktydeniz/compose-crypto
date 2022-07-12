package com.example.crypto_compose.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crypto_compose.model.CryptoListItem
import com.example.crypto_compose.viewmodel.ListViewModel


@Composable
fun CryptoList(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {

    val cryptoList  by remember { viewModel.cryptoList }
    val isLoading by remember {viewModel.isLoading}
    val error by remember {viewModel.error}
    
    CryptoListView(navController = navController, list = cryptoList)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }

        if (error.isNotEmpty()){
            RetryView(error = error) {
                viewModel.loadData()
            }
        }
    }
}

@Composable
fun CryptoListView(navController: NavController,list :List<CryptoListItem>){
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(list) { c ->
            CryptoRow(navController = navController, crypto =c )
        }
    }
}

@Composable
fun CryptoRow(
    navController: NavController,
    crypto: CryptoListItem
){
    Column(modifier = Modifier
        .padding()
        .fillMaxWidth()
        .background(color = MaterialTheme.colors.secondary)
        .clickable {
            navController.navigate("crypto_detail_screen/${crypto.currency}/${crypto.price}")
        }
    ) {
        Text(text = crypto.currency,
        style = MaterialTheme.typography.h4,
        modifier = Modifier.padding(2.dp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primary)

        Text(text = crypto.price,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(2.dp),
            color = MaterialTheme.colors.primaryVariant,
        )
    }
}

@Composable
fun RetryView (error:String, onRetry: () -> Unit){
    Row {

        Text(text = error, color = Color.Red, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = onRetry, modifier = Modifier.align(Alignment.CenterVertically).padding(start = 8.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)) {
            Text(text = "Retry")
        }
    }
}