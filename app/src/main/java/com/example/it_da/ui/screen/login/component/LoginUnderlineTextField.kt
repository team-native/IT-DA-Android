package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaInputCursorColor
import com.example.it_da.ui.theme.ItdaInputTextColor
import com.example.it_da.ui.theme.ItdaInputUnderlineColor

private val LoginTextFieldWidth = 295.dp

// Draws a lightweight text field with the design-specific underline.
@Composable
fun LoginUnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val textStyle = MaterialTheme.typography.labelLarge.copy(
        color = ItdaInputTextColor
    )

    Column(
        modifier = modifier.width(LoginTextFieldWidth)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = textStyle,
                singleLine = true,
                cursorBrush = SolidColor(ItdaInputCursorColor),
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = placeholder,
                            color = ItdaInputTextColor,
                            style = textStyle
                        )
                    }
                    innerTextField()
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ItdaInputUnderlineColor)
        )
    }
}
