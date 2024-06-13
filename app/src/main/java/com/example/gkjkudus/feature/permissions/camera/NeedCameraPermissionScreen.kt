package com.example.gkjkudus.feature.permissions.camera

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NeedCameraPermissionScreen(
    requestPermission: () -> Unit,
    shouldShowRationale: Boolean,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textToShow = if (shouldShowRationale) {
            "The camera is important for this app. Please grant the permission."
        } else {
            "Camera permission required for this feature to be available.\\nPlease grant the permission"
        }
        Text(
            text = textToShow,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = requestPermission) {
            Text(text = "Request Permission")
        }
    }
}