package com.example.it_da.ui.screen.notification.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.button.ItdaImageButton
import com.example.it_da.ui.theme.ItdaButtonTextColor
import com.example.it_da.ui.theme.ItdaHomeExploreButtonGray
import com.example.it_da.ui.theme.ItdaPrimaryTextColor

private val NotificationBackButtonSize = 35.dp
private val NotificationBackButtonCornerRadius = 21.dp
private const val NotificationHeaderFlexibleSpacingWeight = 1f
private val NotificationReadAllButtonCornerRadius = 10.dp
private val NotificationReadAllButtonHorizontalPadding = 10.dp
private val NotificationReadAllButtonVerticalPadding = 9.dp

// Displays the notification screen back action.
@Composable
fun NotificationBackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ItdaImageButton(
        imageResId = R.drawable.backbutton,
        contentDescription = stringResource(id = R.string.notification_back_description),
        onClick = onClick,
        modifier = modifier
            .size(NotificationBackButtonSize),
        imageModifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(NotificationBackButtonCornerRadius)
    )
}

// Displays the notification title and the action that marks every item as read.
@Composable
fun NotificationHeaderSection(
    onReadAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.notification_screen_title),
            color = ItdaPrimaryTextColor,
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.weight(NotificationHeaderFlexibleSpacingWeight))

        Surface(
            modifier = Modifier.clickable(onClick = onReadAllClick),
            shape = RoundedCornerShape(NotificationReadAllButtonCornerRadius),
            color = ItdaHomeExploreButtonGray
        ) {
            Text(
                text = stringResource(id = R.string.notification_read_all),
                modifier = Modifier.padding(
                    horizontal = NotificationReadAllButtonHorizontalPadding,
                    vertical = NotificationReadAllButtonVerticalPadding
                ),
                color = ItdaButtonTextColor,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
