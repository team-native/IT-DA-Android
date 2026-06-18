package com.example.it_da.ui.screen.notification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.card.ItdaCard
import com.example.it_da.ui.screen.notification.state.NotificationUiModel
import com.example.it_da.ui.theme.ItdaPrimaryTextColor
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val NotificationTitleDescriptionSpacing = 8.dp
private val NotificationDescriptionMetadataSpacing = 22.dp
private val NotificationCardMinHeight = 112.dp
private val NotificationCardHorizontalPadding = 16.dp
private val NotificationCardVerticalPadding = 15.dp
private val NotificationReadIconSize = 14.dp
private const val NotificationMetadataFlexibleSpacingWeight = 1f

// Displays one notification with its read state image and elapsed time.
@Composable
fun NotificationCard(
    notification: NotificationUiModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ItdaCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = NotificationCardMinHeight)
            .clickable {
                onClick(notification.id)
            }
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = NotificationCardHorizontalPadding,
                vertical = NotificationCardVerticalPadding
            )
        ) {
            Text(
                text = notification.title,
                color = ItdaPrimaryTextColor,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(NotificationTitleDescriptionSpacing))

            Text(
                text = notification.description,
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(NotificationDescriptionMetadataSpacing))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (notification.isRead) {
                            R.drawable.check
                        } else {
                            R.drawable.not_check
                        }
                    ),
                    contentDescription = stringResource(
                        id = if (notification.isRead) {
                            R.string.notification_read_description
                        } else {
                            R.string.notification_unread_description
                        }
                    ),
                    modifier = Modifier.size(NotificationReadIconSize)
                )

                Spacer(modifier = Modifier.weight(NotificationMetadataFlexibleSpacingWeight))

                Text(
                    text = notification.elapsedTime,
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
