package com.agamy.focus.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.agamy.focus.R
import com.agamy.focus.domain.Routes
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay


@Composable
fun Splash(navController: NavController) {

    //lottie animation
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.clocktimer)
    )

    // الـ delay وبعدين navigate
    LaunchedEffect(Unit) {
        delay(2000L) // 2 ثانية
        navController.navigate(Routes.Home.route) {
            // امسح الـ Splash من الـ back stack
            // عشان الـ user ميرجعش ليه بـ back button
            popUpTo(Routes.Splash.route) {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // display the lottie animation
        LottieAnimation(
            alignment = Alignment.Center,
            modifier = Modifier.size(400.dp),
            composition = composition
        )
    }
}

