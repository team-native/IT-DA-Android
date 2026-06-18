package com.example.it_da.ui.screen.login.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R

private val SocialLoginButtonSpacing = 12.dp
private val SocialLoginButtonSize = 43.dp

// Places the social login image buttons in the order shown by the design.
@Composable
fun SocialLoginButtonRow(
    onAppleLoginClick: () -> Unit,
    onGoogleLoginClick: () -> Unit,
    onKakaoLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(SocialLoginButtonSpacing)
    ) {
        SocialLoginButton(
            imageResId = R.drawable.ic_apple_login,
            contentDescription = "Apple login",
            onClick = onAppleLoginClick
        )

        SocialLoginButton(
            imageResId = R.drawable.ic_google_login,
            contentDescription = "Google login",
            onClick = onGoogleLoginClick
        )

        SocialLoginButton(
            imageResId = R.drawable.ic_kakao_login,
            contentDescription = "Kakao login",
            onClick = onKakaoLoginClick
        )
    }
}

// Displays one social login asset as a tappable image.
@Composable
private fun SocialLoginButton(
    @DrawableRes imageResId: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        modifier = modifier
            .size(SocialLoginButtonSize)
            .clickable(onClick = onClick)
    )
}
