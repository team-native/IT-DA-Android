package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.theme.ItdaLoginButtonDisabledBlack
import com.example.it_da.ui.theme.ItdaButtonTextColor

// Shows a primary action and lets Button enforce the enabled click rule.
@Composable
fun ItdaPrimaryButton(
    enabled: Boolean,
    onClick: () -> Unit,
    text: String? = null,
    containerColor: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
    val buttonText = text ?: stringResource(id = R.string.common_next)

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            width = 1.dp,
            color = ItdaButtonTextColor
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = ItdaButtonTextColor,
            disabledContainerColor = ItdaLoginButtonDisabledBlack,
            disabledContentColor = ItdaButtonTextColor
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}
