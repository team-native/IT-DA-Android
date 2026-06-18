package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaButtonTextColor
import com.example.it_da.ui.theme.ItdaLoginButtonDisabledBlack

private val LoginButtonWidth = 322.dp
private val LoginButtonHeight = 45.dp
private val LoginButtonCornerRadius = 10000.dp
private val LoginButtonBorderWidth = 1.dp

// Shows the primary login action and only enables it when required fields are filled.
@Composable
fun LoginButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .width(LoginButtonWidth)
            .height(LoginButtonHeight),
        shape = RoundedCornerShape(LoginButtonCornerRadius),
        border = BorderStroke(
            width = LoginButtonBorderWidth,
            color = ItdaButtonTextColor
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
            contentColor = ItdaButtonTextColor,
            disabledContainerColor = ItdaLoginButtonDisabledBlack,
            disabledContentColor = ItdaButtonTextColor
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = "로그인",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
