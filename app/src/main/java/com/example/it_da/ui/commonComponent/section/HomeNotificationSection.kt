package com.example.it_da.ui.commonComponent.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.commonComponent.ItdaSectionHeader
import com.example.it_da.ui.commonComponent.button.ItdaUnderlinedTextButton
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.screen.home.component.card.HomeNotificationCard
import com.example.it_da.ui.screen.home.state.HomeNotificationUiModel

private val HomeNotificationCardSpacing = 13.dp

// Shows the notification summary section and a separate all-notifications action.
@Composable
fun HomeNotificationSection(
    notifications: List<HomeNotificationUiModel>,
    onNotificationClick: (String) -> Unit,
    onViewAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ItdaSectionHeader(title = stringResource(id = R.string.home_notification_section_title))

        Spacer(modifier = Modifier.height(ItdaLayoutDefaults.SectionHeaderContentSpacing))

        Column(
            verticalArrangement = Arrangement.spacedBy(HomeNotificationCardSpacing),
            modifier = Modifier.fillMaxWidth()
        ) {
            notifications.forEach { notification ->
                HomeNotificationCard(
                    notification = notification,
                    onClick = onNotificationClick
                )
            }

            ItdaUnderlinedTextButton(
                text = stringResource(id = R.string.home_notification_view_all),
                onClick = onViewAllClick
            )
        }
    }
}
