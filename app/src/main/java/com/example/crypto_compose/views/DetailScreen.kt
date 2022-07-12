package com.example.crypto_compose.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.crypto_compose.model.CryptoModel
import com.example.crypto_compose.util.Resource
import com.example.crypto_compose.viewmodel.DetailViewModel

@Composable
fun DetailScreen(navController: NavController, id:String, price:String, viewModel : DetailViewModel = hiltViewModel() ){

    // step -1 request always calling and is not stable it is coming null or data (change every second ? ) and
    // view always chancing and showing error text. This way is not current
    //  -- side effect --
    // Composable's should be side-effect free.
    // side-effect : mean is a change to the state of app that happens outside the scope of function
    // rememberCoroutineScope should be used buttons etc, we doing this request immediately so its effect flow
    //rememberCoroutineScope use this if user can see or effect with any event (click,drop,...)

    /*
    val scope = rememberCoroutineScope()

    var cryptoItem by remember {
        mutableStateOf<Resource<CryptoModel>>(Resource.Loading())
    }
    // error
    scope.launch {
        cryptoItem = viewModel.getCrypto(id)
        println(cryptoItem.data)
    }
     */

    //step -2 LaunchEffect with this app understand request will calling once. This is better than step 1

   /* var cryptoItem by remember {
        mutableStateOf<Resource<CryptoModel>>(Resource.Loading())
    }

    LaunchedEffect(key1 = Unit) {
        cryptoItem = viewModel.getCrypto(id)
        println(cryptoItem.data)
    }
    */
    //step -3 this way is better than step 1 and step 2

    val cryptoItem by produceState<Resource<CryptoModel>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.secondary)
        ,contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(cryptoItem) {
              is  Resource.Success -> {
                  val selected = cryptoItem.data!![0]

                  Text(text = selected.name,
                  style = MaterialTheme.typography.h3,
                  modifier = Modifier.padding(2.dp),
                  fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colors.primary,
                      textAlign = TextAlign.Center
                  )

                  Image(painter = rememberImagePainter(data = selected.logoURL),
                      contentDescription = selected.name,
                  modifier = Modifier
                      .size(200.dp, 200.dp)
                      .clip(CircleShape)
                      .border(2.dp, Color.Gray, CircleShape))

                  Text(text = selected.price,
                      style = MaterialTheme.typography.h4,
                      modifier = Modifier.padding(2.dp),
                      fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colors.primaryVariant,
                      textAlign = TextAlign.Center
                  )
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }

                is Resource.Error-> {
                    Text(text = cryptoItem.message!!)
                }

            }
        }
    }
}