package com.example.it_da.ui.screen.home.component.card

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.commonComponent.ItdaCard
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val HomeNotificationImageContentSpacing = 16.dp
private val HomeNotificationMessageTimeSpacing = 8.dp

// Displays one notification summary row with state-provided image, message, and elapsed time.
@Composable
fun HomeNotificationCard(
    notification: HomeNotificationUiModel,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ItdaCard(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 54.dp)
            .clickable {
                onClick(notification.id)
        }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = notification.imageResId),
                contentDescription = notification.imageDescription,
                modifier = Modifier.size(31.dp)
            )

            Spacer(modifier = Modifier.width(HomeNotificationImageContentSpacing))

            Column {
                Text(
                    text = notification.message,
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(HomeNotificationMessageTimeSpacing))

                Text(
                    text = notification.elapsedTime,
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
