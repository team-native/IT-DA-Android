package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val LoginSignUpTextHorizontalPadding = 2.dp

// Displays the sign-up entry point below the login button.
@Composable
fun LoginSignUpGuide(
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = "IT-DA가 처음이신가요?  ",
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "회원가입하기",
            modifier = Modifier
                .clickable(onClick = onSignUpClick)
                .padding(horizontal = LoginSignUpTextHorizontalPadding),
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.bodyMedium.copy(
                textDecoration = TextDecoration.Underline
            )
        )
    }
}
