package com.agamy.focus.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.agamy.focus.utils.Phase

@Composable
fun PhaseTabs(phase: Phase) {
    val focusColor = Color(0xFF7F77DD)
    val breakColor = Color(0xFF1D9E75)

    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        listOf(Phase.FOCUS to "Focus", Phase.BREAK to "Break").forEach { (p, label) ->
            val selected = phase == p
            val color = if (p == Phase.FOCUS) focusColor else breakColor
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        if (selected) color.copy(alpha = 0.15f) else Color.Transparent,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (selected) FontWeight.Medium else FontWeight.Normal,
                    color = if (selected) color else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}