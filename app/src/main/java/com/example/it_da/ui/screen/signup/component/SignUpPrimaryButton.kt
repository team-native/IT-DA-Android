package com.example.it_da.ui.screen.signup.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.ItdaButtonTextColor
import com.example.it_da.ui.theme.ItdaLoginButtonDisabledBlack

private val SignUpPrimaryButtonTextWeight = FontWeight(600)

@Composable
fun SignUpPrimaryButton(
    enabled: Boolean,
    onClick: () -> Unit,
    text: String = "다음으로",
    containerColor: Color = MaterialTheme.colorScheme.onBackground,
    modifier: Modifier = Modifier
) {
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
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = SignUpPrimaryButtonTextWeight,
                    fontSize = 20.sp,
                    lineHeight = 20.sp
                )
            )
        }
    }
}

