package com.example.gkjkudus.view.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gkjkudus.R
import com.example.gkjkudus.data.ItemRoom

@Composable
fun ItemList(item: ItemRoom) {
    Surface(modifier = Modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.itemCode!!,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(text = item.buyer!!,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.item!!,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Surface(shape = RoundedCornerShape(50),
                    color = if(item.statusItem == 2) MaterialTheme.colorScheme.primaryContainer else if(item.statusItem == 1) MaterialTheme.colorScheme.surfaceContainer else Color.Transparent)
                {
                    Text(modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp),
                        fontSize = MaterialTheme.typography.labelMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        text = if(item.statusItem == 2) stringResource(R.string.paid_off) else if(item.statusItem == 1) stringResource(R.string.waiting_for_payment) else "")
                }
            }

        }
    }
}