package com.example.it_da.ui.screen.notification.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.it_da.R
import com.example.it_da.ui.screen.notification.state.NotificationFilter
import com.example.it_da.ui.theme.ItdaInputBorderGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val NotificationFilterDropdownTopPadding = 14.dp
private val NotificationFilterDropdownHeight = 48.dp
private val NotificationFilterDropdownBorderWidth = 1.dp
private val NotificationFilterDropdownCornerRadius = 10.dp
private val NotificationFilterDropdownHorizontalPadding = 16.dp
private const val NotificationFilterDropdownTextWeight = 1f
private val NotificationFilterDropdownIconSize = 24.dp

// Displays the notification filter label, selected category, and selectable dropdown options.
@Composable
fun NotificationFilterDropdown(
    selectedFilter: NotificationFilter,
    isExpanded: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onFilterSelected: (NotificationFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.notification_filter_label),
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.titleMedium
        )

        Row(
            modifier = Modifier
                .padding(top = NotificationFilterDropdownTopPadding)
                .fillMaxWidth()
                .height(NotificationFilterDropdownHeight)
                .border(
                    width = NotificationFilterDropdownBorderWidth,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(NotificationFilterDropdownCornerRadius)
                )
                .clickable(onClick = onClick)
                .padding(horizontal = NotificationFilterDropdownHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = selectedFilter.labelResId),
                modifier = Modifier.weight(NotificationFilterDropdownTextWeight),
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.titleMedium
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = stringResource(
                    id = R.string.notification_filter_open_description
                ),
                tint = ItdaSecondaryTextColor,
                modifier = Modifier.size(NotificationFilterDropdownIconSize)
            )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = onDismiss
        ) {
            NotificationFilter.entries.forEach { filter ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(id = filter.labelResId),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        onFilterSelected(filter)
                    }
                )
            }
        }
    }
}
