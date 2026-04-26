# 🍅 Focus — Pomodoro Timer App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android" />
  <img src="https://img.shields.io/badge/Language-Kotlin-purple?style=for-the-badge&logo=kotlin" />
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue?style=for-the-badge&logo=jetpackcompose" />
  <img src="https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Min%20SDK-26-red?style=for-the-badge" />
</p>

<p align="center">
  A clean and minimal <strong>Pomodoro Timer</strong> app built with Jetpack Compose, MVVM architecture, Room database, and Kotlin Coroutines.
  Stay focused, track your sessions, and get notified when it's time to take a break.
</p>

---

## ✨ Features

- 🎯 **Focus & Break Timer** — 25/5 min and 50/10 min presets
- 🔔 **Notifications** — Alerts when focus or break session ends
- 📳 **Vibration + Sound** — Haptic and audio feedback on phase completion
- 💾 **Session History** — Saves every completed session in a local Room database
- 🔵 **Animated Timer Ring** — Smooth circular progress animation with Compose Canvas
- 🟣 **Lottie Splash Screen** — Beautiful animated splash with Lottie
- ⏯️ **Pause / Resume / Reset / Skip** — Full timer control
- 🌗 **Phase Tabs** — Visual indicator for Focus vs Break mode
- 🔴 **Session Dots** — Track up to 4 completed focus rounds

---

## 📸 Screenshots

> *(Add your screenshots here)*

| Splash | Focus Timer | Break Timer |
|--------|------------|-------------|
| ![splash]() | ![focus]() | ![break]() |

---

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** clean architecture:

```
com.agamy.focus
│
├── data/
│   └── local/
│       ├── PomodoroDatabase.kt     # Room Database
│       ├── SessionDao.kt           # DAO interface
│       └── SessionEntity.kt        # Room Entity
│
├── domain/
│   ├── Routes.kt                   # Navigation routes (sealed class)
│   └── TimerState.kt               # UI state data class
│
├── navigation/
│   └── NavGraph.kt                 # Compose Navigation graph
│
├── presentation/
│   ├── PomodoroViewModel.kt        # ViewModel with StateFlow
│   ├── components/
│   │   ├── TimerRing.kt            # Animated circular timer
│   │   ├── TimerControls.kt        # Start / Pause / Reset / Skip
│   │   ├── PhaseTabs.kt            # Focus / Break tabs
│   │   ├── PresetSelector.kt       # 25/5 and 50/10 preset buttons
│   │   ├── SessionDots.kt          # Session progress dots
│   │   └── TimerService.kt         # Foreground service for notifications
│   └── screen/
│       ├── PomodoroScreen.kt       # Main timer screen
│       └── Splash.kt               # Splash screen with Lottie
│
└── utils/
    └── Phase.kt                    # Enum: FOCUS / BREAK
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|-----------|---------|
| **Kotlin** | Primary language |
| **Jetpack Compose** | Declarative UI |
| **Compose Navigation** | Screen navigation |
| **ViewModel + StateFlow** | State management |
| **Room** | Local database for session history |
| **Kotlin Coroutines** | Async timer logic |
| **Flow** | Reactive data streams |
| **Foreground Service** | Background timer + notifications |
| **Lottie** | Splash screen animation |
| **KSP** | Kotlin Symbol Processing for Room |

---

## ⚙️ How It Works

```
App Launch
    │
    ▼
Splash Screen (Lottie animation, 2s delay)
    │
    ▼
Home Screen — PomodoroScreen
    │
    ├── Select Preset (25/5 or 50/10)
    ├── Press Start → ViewModel launches coroutine
    ├── Timer counts down via StateFlow
    ├── Pause / Resume / Reset / Skip at any time
    │
    ▼
Phase Complete
    │
    ├── Session saved to Room DB
    ├── Foreground Service fires notification + sound + vibration
    └── Switches to next phase (Focus ↔ Break)
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android device or emulator with API 26+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/focus-pomodoro.git
cd focus-pomodoro
```

2. **Open in Android Studio**
```
File → Open → Select the project folder
```

3. **Add Lottie animation file**

Place your Lottie `.json` file in:
```
app/src/main/res/raw/clocktimer.json
```

4. **Add sound file**

Place your sound file in:
```
app/src/main/res/raw/lithe_fall_back.mp3
```

5. **Build and Run**
```
Click ▶ Run or press Shift + F10
```

---

## 📦 Dependencies

```kotlin
// Jetpack Compose BOM
implementation(platform("androidx.compose:compose-bom:2024.05.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose:1.9.0")

// Navigation
implementation("androidx.navigation:navigation-compose:2.7.7")

// ViewModel + Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

// Room
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Lottie
implementation("com.airbnb.android:lottie-compose:6.3.0")
```

---

## 🔑 Key Concepts Used

- **`StateFlow`** — Reactive UI state that survives configuration changes
- **`LaunchedEffect`** — Side effects in Compose (timer, navigation)
- **`viewModelScope`** — Coroutine scope tied to ViewModel lifecycle
- **`popUpTo + inclusive`** — Clears Splash from back stack after navigation
- **`Canvas`** — Custom animated timer ring drawn with `drawArc`
- **`Foreground Service`** — Keeps notifications working when app is in background
- **`Room + Flow`** — Auto-updating session count from database

---

## 🤝 Contributing

Contributions are welcome! Feel free to:

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

```
MIT License

Copyright (c) 2024 Agamy

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

---

## 👨‍💻 Author

**Agamy**

<p>
  <a href="https://github.com/MohammedAgamy">
    <img src="https://img.shields.io/badge/GitHub-YOUR_USERNAME-black?style=flat-square&logo=github" />
  </a>
  <a href="https://linkedin.com/in/mohamed-agamy-742b36389/">
    <img src="https://img.shields.io/badge/LinkedIn-Connect-blue?style=flat-square&logo=linkedin" />
  </a>
</p>

---

<p align="center">Made with ❤️ and lots of ☕ — Stay Focused!</p>
