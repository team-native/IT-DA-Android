package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.theme.ItdaInputBorderGray
import com.example.it_da.ui.theme.ItdaPlaceholderTextColor
import com.example.it_da.ui.theme.ItdaSectionTextColor

// Draws a labeled rounded input that hides its example text while focused or filled.
@Composable
fun ItdaOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    inputHeight: Dp = 40.dp,
    singleLine: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val inputTextStyle = MaterialTheme.typography.labelLarge.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

    Column(modifier = modifier.fillMaxWidth()) {
        ItdaFieldLabel(text = label)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(inputHeight)
                .border(
                    width = 1.2.dp,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(
                    horizontal = 11.dp,
                    vertical = if (singleLine) 0.dp else 11.dp
                ),
            contentAlignment = if (singleLine) Alignment.CenterStart else Alignment.TopStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                textStyle = inputTextStyle,
                singleLine = singleLine,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = placeholder,
                            color = ItdaPlaceholderTextColor,
                            style = inputTextStyle.copy(color = ItdaPlaceholderTextColor)
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

// Draws the field label shared by reusable form inputs.
@Composable
fun ItdaFieldLabel(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(bottom = 12.dp),
        color = ItdaSectionTextColor,
        style = MaterialTheme.typography.titleMedium
    )
}
