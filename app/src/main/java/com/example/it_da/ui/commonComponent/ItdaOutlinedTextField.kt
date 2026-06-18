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

private val ItdaOutlinedTextFieldDefaultHeight = 40.dp
private val ItdaOutlinedTextFieldBorderWidth = 1.2.dp
private val ItdaOutlinedTextFieldCornerRadius = 10.dp
private val ItdaOutlinedTextFieldHorizontalPadding = 11.dp
private val ItdaOutlinedTextFieldSingleLineVerticalPadding = 0.dp
private val ItdaOutlinedTextFieldMultilineVerticalPadding = 11.dp
private val ItdaFieldLabelBottomPadding = 12.dp

// Draws a labeled rounded input that hides its example text while focused or filled.
@Composable
fun ItdaOutlinedTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    inputHeight: Dp = ItdaOutlinedTextFieldDefaultHeight,
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
                    width = ItdaOutlinedTextFieldBorderWidth,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(ItdaOutlinedTextFieldCornerRadius)
                )
                .padding(
                    horizontal = ItdaOutlinedTextFieldHorizontalPadding,
                    vertical = if (singleLine) {
                        ItdaOutlinedTextFieldSingleLineVerticalPadding
                    } else {
                        ItdaOutlinedTextFieldMultilineVerticalPadding
                    }
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
        modifier = modifier.padding(bottom = ItdaFieldLabelBottomPadding),
        color = ItdaSectionTextColor,
        style = MaterialTheme.typography.titleMedium
    )
}
