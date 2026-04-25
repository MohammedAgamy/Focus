package com.agamy.focus.domain

import com.agamy.focus.utils.Phase

data class TimerState(
    val phase: Phase = Phase.FOCUS,
    val focusMinutes: Int = 25,
    val breakMinutes: Int = 5,
    val remainingSeconds: Int = 25 * 60,
    val isRunning: Boolean = false,
    val sessionsCompleted: Int = 0
)

{
    val totalSeconds get() =
        if (phase == Phase.FOCUS) focusMinutes * 60 else breakMinutes * 60

    val progress get() =
        remainingSeconds.toFloat() / totalSeconds.toFloat()

    val displayTime get(): String {
        val m = remainingSeconds / 60
        val s = remainingSeconds % 60
        return "%02d:%02d".format(m, s)
    }
}