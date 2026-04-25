package com.agamy.focus.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agamy.focus.presentation.PomodoroViewModel
import com.agamy.focus.presentation.components.PhaseTabs
import com.agamy.focus.presentation.components.PresetSelector
import com.agamy.focus.presentation.components.SessionDots
import com.agamy.focus.presentation.components.TimerControls
import com.agamy.focus.presentation.components.TimerRing

@Composable
fun PomodoroScreen(viewModel: PomodoroViewModel = viewModel()) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusCount by viewModel.focusCount.collectAsStateWithLifecycle()
    val context = LocalContext.current

    // Fire notification + sound when phase completes
    LaunchedEffect(state.phase, state.isRunning) {
        if (!state.isRunning && state.remainingSeconds == 0) {
            startTimerService(context, state.phase)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Preset selector
        PresetSelector(
            selectedFocus = state.focusMinutes,
            onSelect = { f, b -> viewModel.setPreset(f, b) }
        )

        Spacer(Modifier.height(32.dp))

        // Phase tabs
        PhaseTabs(phase = state.phase)

        Spacer(Modifier.height(32.dp))

        // Animated ring timer
        TimerRing(state = state)

        Spacer(Modifier.height(32.dp))

        // Session dots
        SessionDots(completed = state.sessionsCompleted)

        Spacer(Modifier.height(24.dp))

        // Controls
        TimerControls(
            isRunning = state.isRunning,
            onStartPause = viewModel::startPause,
            onReset = viewModel::reset,
            onSkip = viewModel::skip
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Total focus sessions: $focusCount",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

}

