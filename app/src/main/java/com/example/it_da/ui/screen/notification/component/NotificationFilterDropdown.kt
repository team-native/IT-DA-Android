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
                .padding(top = 14.dp)
                .fillMaxWidth()
                .height(48.dp)
                .border(
                    width = 1.dp,
                    color = ItdaInputBorderGray,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(onClick = onClick)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = selectedFilter.labelResId),
                modifier = Modifier.weight(1f),
                color = ItdaSecondaryTextColor,
                style = MaterialTheme.typography.titleMedium
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = stringResource(
                    id = R.string.notification_filter_open_description
                ),
                tint = ItdaSecondaryTextColor,
                modifier = Modifier.size(24.dp)
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
