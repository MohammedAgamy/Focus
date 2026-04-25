package com.agamy.focus.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun PresetSelector(selectedFocus: Int, onSelect: (Int, Int) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        listOf(25 to 5, 50 to 10).forEach { (f, b) ->
            OutlinedButton(
                onClick = { onSelect(f, b) },
                border = if (selectedFocus == f)
                    BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                else
                    BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Text("$f / $b min")
            }
        }
    }
}