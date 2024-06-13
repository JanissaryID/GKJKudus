package com.example.gkjkudus.view.ui.page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gkjkudus.ITEM_CODE
import com.example.gkjkudus.R
import com.example.gkjkudus.STAT_SCAN
import com.example.gkjkudus.data.ItemViewModel
import com.example.gkjkudus.navigation.Pages
import com.example.gkjkudus.view.ui.component.ItemList
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageTransaction(navController: NavController, itemViewModel: ItemViewModel) {

    val items by itemViewModel.itemsTransaction.collectAsStateWithLifecycle()

    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )

    var indexItem by remember { mutableIntStateOf(0) }

    var buyer by remember { mutableStateOf("") }
    var auctionPrice by remember { mutableStateOf("") }

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ){
        itemsIndexed(items = items) { index, item ->
            Surface(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    indexItem = index
                    openBottomSheet = !openBottomSheet
                    buyer = items[indexItem].buyer.toString()
                    auctionPrice = items[indexItem].auctionPrice.toString()
                }) {
                ItemList(item = item)
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            )
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(
            onClick = {
                navController.navigate(route = Pages.Qr_Scanning.route){
                    popUpTo(Pages.Qr_Scanning.route) {
                        inclusive = true
                    }
                }
            },
        ) {
            Icon(painter = painterResource(id = R.drawable.twotone_qr_code_scanner_24), "Add Transaction")
        }
    }

    if(STAT_SCAN){

        STAT_SCAN = false
        val dataindexItem = items.indexOfFirst { it.itemCode == ITEM_CODE }

        if (dataindexItem != -1) {
            indexItem = dataindexItem
            buyer = items[indexItem].buyer.toString()
            auctionPrice = items[indexItem].auctionPrice.toString()
            openBottomSheet = true
        } else {
            Toast.makeText(context, stringResource(R.string.data_not_found), Toast.LENGTH_SHORT).show()
        }

    }

    // Sheet content
    if (openBottomSheet) {

        ModalBottomSheet(
            onDismissRequest = { openBottomSheet = false },
            sheetState = bottomSheetState,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = stringResource(R.string.item_details),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(R.string.item_code),
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(text = "${items[indexItem].itemCode}",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = stringResource(R.string.item),
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(text = "${items[indexItem].item}",
                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = buyer,
                singleLine = true,
                onValueChange = {
                    buyer = it.capitalize(Locale.ROOT)
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                label = { Text(stringResource(R.string.buyer)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = auctionPrice,
                singleLine = true,
                onValueChange = { auctionPrice = it },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                label = { Text(stringResource(R.string.auction_price)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
                shape = RoundedCornerShape(100),
                elevation = null,
                enabled = if(buyer.isNotEmpty() && auctionPrice.isNotEmpty()) true else false,
                onClick = {
                    itemViewModel.updateItems(idItem = items[indexItem].id, buyer = buyer, auctionPrice = auctionPrice) { responseCode ->
                        if (responseCode == 200) {
//                            Log.d("item_viewmodel", "Update successful with code $responseCode")
                            openBottomSheet = false
                        } else {
//                            Log.d("item_viewmodel", "Update failed with code $responseCode")
                        }
                    }
                }
            ) {
                Text(text = stringResource(R.string.save), fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(72.dp))
        }
    }
}