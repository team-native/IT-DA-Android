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
            .width(322.dp)
            .height(45.dp),
        shape = RoundedCornerShape(10000.dp),
        border = BorderStroke(
            width = 1.dp,
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
