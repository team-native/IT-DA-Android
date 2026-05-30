package com.example.it_da.ui.screen.home.component

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.R
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaHomeDividerGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite

private val HomeBottomNavigationBarHeight = 52.5.dp
private val HomeBottomNavigationContainerHeight = 62.dp
private val HomeBottomNavigationItemHeight = 48.dp

@Composable
fun HomeBottomNavigationBar(
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
            .height(HomeBottomNavigationContainerHeight)
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(HomeBottomNavigationBarHeight),
            color = ItdaWhite,
            border = BorderStroke(1.dp, ItdaHomeDividerGray)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomeBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_home,
                    label = "홈",
                    contentDescription = "홈 탭",
                    iconSize = 25.dp,
                    onClick = onHomeClick
                )

                HomeBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_research,
                    label = "탐색",
                    contentDescription = "탐색 탭",
                    iconSize = 25.dp,
                    onClick = onExploreClick
                )

                Spacer(
                    modifier = Modifier
                        .width(52.dp)
                        .height(HomeBottomNavigationItemHeight)
                )

                HomeBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_bell,
                    label = "알림",
                    contentDescription = "알림 탭",
                    iconSize = 25.dp,
                    onClick = onNotificationClick
                )

                HomeBottomNavigationItem(
                    iconResId = R.drawable.bottom_bar_profile,
                    label = "My",
                    contentDescription = "내 프로필 탭",
                    iconSize = 25.dp,
                    onClick = onProfileClick
                )
            }
        }

        HomeCenterNavigationButton(
            onClick = onCreateProjectClick,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
private fun HomeBottomNavigationItem(
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
            .height(HomeBottomNavigationItemHeight)
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

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = label,
            color = ItdaSecondaryTextColor,
            fontFamily = DotSans,
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
            lineHeight = 9.sp
        )
    }
}

@Composable
private fun HomeCenterNavigationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(45.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bottom_bar_center),
            contentDescription = "프로젝트 추가",
            modifier = Modifier.size(45.dp)
        )
    }
}

