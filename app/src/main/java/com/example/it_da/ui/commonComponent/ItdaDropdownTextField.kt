package com.example.it_da.ui.commonComponent

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.theme.ItdaInputBorderGray
import com.example.it_da.ui.theme.ItdaPlaceholderTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val ItdaDropdownTextFieldHeight = 40.dp
private val ItdaDropdownTextFieldBorderWidth = 1.2.dp
private val ItdaDropdownTextFieldCornerRadius = 10.dp
private val ItdaDropdownTextFieldStartPadding = 15.dp
private val ItdaDropdownTextFieldEndPadding = 4.dp
private const val ItdaDropdownTextFieldValueWeight = 1f
private val ItdaDropdownTextFieldArrowButtonSize = 44.dp
private val ItdaDropdownTextFieldArrowIconSize = 24.dp

// Draws a rounded input with a button arrow reserved for a later dropdown screen.
@Composable
fun ItdaDropdownTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    onArrowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val inputTextStyle = MaterialTheme.typography.labelLarge.copy(
        color = MaterialTheme.colorScheme.onBackground
    )

    Column(modifier = modifier.fillMaxWidth()) {
        ItdaFieldLabel(text = label)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ItdaDropdownTextFieldHeight)
                .border(
                    width = ItdaDropdownTextFieldBorderWidth,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(ItdaDropdownTextFieldCornerRadius)
                )
                .padding(
                    start = ItdaDropdownTextFieldStartPadding,
                    end = ItdaDropdownTextFieldEndPadding
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(ItdaDropdownTextFieldValueWeight),
                contentAlignment = Alignment.CenterStart
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = inputTextStyle,
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
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

            IconButton(
                onClick = onArrowClick,
                modifier = Modifier.size(ItdaDropdownTextFieldArrowButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_down_arrow),
                    contentDescription = stringResource(
                        id = R.string.common_dropdown_open_description
                    ),
                    tint = ItdaSecondaryTextColor,
                    modifier = Modifier.size(ItdaDropdownTextFieldArrowIconSize)
                )
            }
        }
    }
}
