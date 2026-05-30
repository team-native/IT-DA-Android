package com.example.it_da.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.component.ItdaOutlinedBadge
import com.example.it_da.ui.screen.home.component.HomeBottomNavigationBar
import com.example.it_da.ui.screen.signup.component.SignUpTopBar
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

private val OtherProfileHorizontalPadding = 24.dp
private val OtherProfileOuterSpacing = 20.dp
private val OtherProfileTitleContentSpacing = 10.dp
private val OtherProfileChipSpacing = 6.dp
private val OtherProfileCardSpacing = 12.dp
private val OtherProfileHeaderSpacing = 20.dp
private val OtherProfileHeaderTextSpacing = 2.dp

@Composable
fun ProfileScreen(
    onHomeTabClick: () -> Unit,
    onExploreTabClick: () -> Unit,
    onCreateProjectClick: () -> Unit,
    onNotificationTabClick: () -> Unit,
    onProfileTabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SignUpTopBar(title = "Project")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = OtherProfileHorizontalPadding)
                    .padding(top = 22.dp, bottom = 84.dp),
                verticalArrangement = Arrangement.spacedBy(OtherProfileOuterSpacing)
            ) {
                ProfileHeader()
                Section("기술 스택") {
                    Row(horizontalArrangement = Arrangement.spacedBy(OtherProfileChipSpacing)) {
                        ItdaOutlinedBadge(text = "Back-end")
                        ItdaOutlinedBadge(text = "Java")
                        ItdaOutlinedBadge(text = "JavaScript")
                        ItdaOutlinedBadge(text = "Design")
                    }
                }
                Section("경력") { EmptyProfileBox(height = 86.dp) }
                Section("자기 소개") { EmptyProfileBox(height = 90.dp) }
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    ProfileSectionTitle(text = "활동 요약")
                    ActivitySummaryRow()
                    Row(horizontalArrangement = Arrangement.spacedBy(OtherProfileCardSpacing)) {
                        ActionButton("프로젝트 전체 보기", modifier = Modifier.weight(1.35f))
                        ActionButton("설정", modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        HomeBottomNavigationBar(
            onHomeClick = onHomeTabClick,
            onExploreClick = onExploreTabClick,
            onCreateProjectClick = onCreateProjectClick,
            onNotificationClick = onNotificationTabClick,
            onProfileClick = onProfileTabClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(OtherProfileTitleContentSpacing)) {
        ProfileSectionTitle(text = title)
        content()
    }
}

@Composable
private fun ProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(OtherProfileHeaderSpacing)
    ) {
        Surface(modifier = Modifier.size(92.dp), shape = CircleShape, color = Color(0xFFE3E3E3)) {
            Box(contentAlignment = Alignment.Center) {
                Surface(modifier = Modifier.size(38.dp), shape = CircleShape, color = Color(0xFF8F8F8F)) {}
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(OtherProfileHeaderTextSpacing)) {
            Text("메타몽", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 35.sp, lineHeight = 40.sp)
            Text("SW개발과 · 1학년", fontFamily = DotSans, fontSize = 23.sp, lineHeight = 26.sp)
            Text("가입일 2025년 1월", fontFamily = DotSans, fontSize = 23.sp, lineHeight = 26.sp)
        }
    }
}

@Composable
private fun ProfileSectionTitle(text: String) {
    Text(text, fontFamily = DotSans, fontSize = 27.sp, lineHeight = 31.sp)
}

@Composable
private fun EmptyProfileBox(height: Dp) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(height),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {}
}

@Composable
private fun ActivitySummaryRow() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(OtherProfileCardSpacing)) {
        ActivitySummaryCard("2", "참여 프로젝트", modifier = Modifier.weight(1f))
        ActivitySummaryCard("3", "지원 내역", modifier = Modifier.weight(1f))
        ActivitySummaryCard("1", "받은 제안", modifier = Modifier.weight(1f))
    }
}

@Composable
private fun ActivitySummaryCard(value: String, label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(72.dp),
        shape = RoundedCornerShape(12.dp),
        color = Color.Transparent,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text(value, fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 17.sp)
            Text(label, fontFamily = DotSans, fontSize = 11.sp, color = ItdaSecondaryTextColor)
        }
    }
}

@Composable
private fun ActionButton(text: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.2.dp, ItdaGuideGray)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text, fontFamily = DotSans, fontSize = 14.sp, color = ItdaSecondaryTextColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    ITDATheme {
        ProfileScreen(
            onHomeTabClick = {},
            onExploreTabClick = {},
            onCreateProjectClick = {},
            onNotificationTabClick = {},
            onProfileTabClick = {}
        )
    }
}

