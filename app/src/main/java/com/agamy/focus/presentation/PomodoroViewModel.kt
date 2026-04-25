package com.agamy.focus.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.agamy.focus.data.local.PomodoroDatabase
import com.agamy.focus.data.local.SessionEntity
import com.agamy.focus.domain.TimerState
import com.agamy.focus.utils.Phase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PomodoroViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = PomodoroDatabase.get(app).sessionDao()

    private val _state = MutableStateFlow(TimerState())
    val state: StateFlow<TimerState> = _state.asStateFlow()


    val focusCount: StateFlow<Int> = dao.getFocusCount()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)


    private var timerJob: Job? = null

    fun setPreset(focusMin: Int, breakMin: Int) {
        reset()
        _state.update {
            it.copy(
                focusMinutes = focusMin,
                breakMinutes = breakMin,
                remainingSeconds = focusMin * 60,
                phase = Phase.FOCUS
            )
        }
    }


    fun startPause() {
        if (_state.value.isRunning) pause() else start()
    }

    private fun start() {
        _state.update { it.copy(isRunning = true) }
        timerJob = viewModelScope.launch {
            while (_state.value.remainingSeconds > 0) {
                delay(1000L)
                _state.update { it.copy(remainingSeconds = it.remainingSeconds - 1) }
            }
            onPhaseComplete()
        }
    }

    private fun pause() {
        timerJob?.cancel()
        _state.update { it.copy(isRunning = false) }
    }

    fun reset() {
        timerJob?.cancel()
        _state.update {
            it.copy(
                isRunning = false,
                phase = Phase.FOCUS,
                sessionsCompleted = 0,
                remainingSeconds = it.focusMinutes * 60
            )
        }
    }

    fun skip() {
        timerJob?.cancel()
        viewModelScope.launch { onPhaseComplete() }
    }

    private suspend fun onPhaseComplete() {
        val s = _state.value
        dao.insert(
            SessionEntity(
                type = s.phase.name.lowercase(),
                durationMinutes = if (s.phase == Phase.FOCUS) s.focusMinutes else s.breakMinutes
            )
        )
        val nextPhase = if (s.phase == Phase.FOCUS) Phase.BREAK else Phase.FOCUS
        _state.update {
            it.copy(
                phase = nextPhase,
                isRunning = false,
                sessionsCompleted = if (s.phase == Phase.FOCUS) s.sessionsCompleted + 1 else s.sessionsCompleted,
                remainingSeconds = if (nextPhase == Phase.FOCUS) it.focusMinutes * 60 else it.breakMinutes * 60
            )
        }
    }
}