package com.agamy.focus.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agamy.focus.domain.TimerState
import com.agamy.focus.utils.Phase

@Composable
fun TimerRing(state: TimerState) {
    val focusColor = Color(0xFF7F77DD)
    val breakColor = Color(0xFF1D9E75)
    val activeColor = if (state.phase == Phase.FOCUS) focusColor else breakColor

    val animatedProgress by animateFloatAsState(
        targetValue = state.progress,
        animationSpec = tween(durationMillis = 800, easing = LinearEasing),
        label = "timerProgress"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(240.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val stroke = 14.dp.toPx()
            val radius = (size.minDimension - stroke) / 2
            val center = this.center

            // Background ring
            drawCircle(
                color = Color.Gray.copy(alpha = 0.15f),
                radius = radius,
                style = Stroke(width = stroke)
            )

            // Progress ring
            drawArc(
                color = activeColor,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress,
                useCenter = false,
                style = Stroke(width = stroke, cap = StrokeCap.Round),
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = state.displayTime,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 48.sp
                )
            )
            Text(
                text = if (state.phase == Phase.FOCUS) "Focus" else "Break",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}