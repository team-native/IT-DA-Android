package com.example.it_da.ui.commonComponent

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.it_da.R
import com.example.it_da.ui.theme.ItdaHomeDividerGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite

private val ItdaBottomNavigationBarHeight = 52.5.dp
private val ItdaBottomNavigationContainerHeight = 62.dp
private val ItdaBottomNavigationItemHeight = 48.dp
private val ItdaCenterNavigationClickSize = 52.dp
private val ItdaBottomNavigationIconLabelSpacing = 2.dp

// Shows the fixed bottom navigation bar and exposes each tab as a callback.
@Composable
fun ItdaBottomNavigationBar(
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(ItdaBottomNavigationContainerHeight)
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(ItdaBottomNavigationBarHeight),
            color = ItdaWhite,
            border = BorderStroke(1.dp, ItdaHomeDividerGray)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItdaBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_home,
                    label = stringResource(id = R.string.home_bottom_tab_home),
                    contentDescription = stringResource(id = R.string.home_bottom_tab_home),
                    iconSize = 25.dp,
                    onClick = onHomeClick
                )

                ItdaBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_research,
                    label = stringResource(id = R.string.home_bottom_tab_explore),
                    contentDescription = stringResource(id = R.string.home_bottom_tab_explore),
                    iconSize = 25.dp,
                    onClick = onExploreClick
                )

                Spacer(
                    modifier = Modifier
                        .width(ItdaCenterNavigationClickSize)
                        .height(ItdaBottomNavigationItemHeight)
                )

                ItdaBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_bell,
                    label = stringResource(id = R.string.home_bottom_tab_notification),
                    contentDescription = stringResource(
                        id = R.string.home_bottom_tab_notification
                    ),
                    iconSize = 25.dp,
                    onClick = onNotificationClick
                )

                ItdaBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_profile,
                    label = stringResource(id = R.string.home_bottom_tab_profile),
                    contentDescription = stringResource(id = R.string.home_bottom_tab_profile),
                    iconSize = 25.dp,
                    onClick = onProfileClick
                )
            }
        }

        ItdaCenterNavigationButton(
            onClick = onCreateProjectClick,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

// Shows a normal labeled bottom navigation item.
@Composable
private fun ItdaBottomNavigationItem(
    @DrawableRes iconResId: Int,
    label: String,
    contentDescription: String,
    iconSize: Dp,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(52.dp)
            .height(ItdaBottomNavigationItemHeight)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(iconSize)
        )

        Spacer(modifier = Modifier.height(ItdaBottomNavigationIconLabelSpacing))

        Text(
            text = label,
            color = ItdaSecondaryTextColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

// Shows the center add-project action as the prominent middle bottom button.
@Composable
private fun ItdaCenterNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(ItdaCenterNavigationClickSize)
            .zIndex(1f)
            .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bottom_bar_center),
            contentDescription = stringResource(
                id = R.string.home_bottom_create_project_description
            ),
            modifier = Modifier.size(45.dp)
        )
    }
}
