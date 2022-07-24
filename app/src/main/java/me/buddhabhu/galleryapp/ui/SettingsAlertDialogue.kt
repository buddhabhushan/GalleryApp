package me.buddhabhu.galleryapp.ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun SettingsAlertDialogue(
    onAlertCancelled: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onAlertCancelled() },
        confirmButton = {
            TextButton(onClick = { onPositiveButtonClicked() } ) {
                Text(text = "Go To Settings")
            }
        },
        dismissButton = {
            TextButton(onClick = { onAlertCancelled() } ) {
                Text(text = "Cancel")
            }
        },
        title = { Text(text = "Attention!") },
        text = { Text(text = "You need give media permissions to run the app") },
    )
}