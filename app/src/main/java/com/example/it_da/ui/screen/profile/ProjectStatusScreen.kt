package com.example.it_da.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaHomeExploreButtonGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

private val StatusHorizontalPadding = 24.dp
private val StatusSectionSpacing = 14.dp
private val StatusChipSpacing = 8.dp
private val StatusListSpacing = 10.dp
private val StatusCardInnerSpacing = 6.dp

private enum class ProjectFilter(val label: String) {
    Participating("참여 중"),
    Applied("지원 내역"),
    Finished("완료")
}

private data class ProjectStatusUiModel(
    val title: String,
    val description: String,
    val status: String
)

@Composable
fun ProjectStatusScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedFilter by rememberSaveable { mutableStateOf(ProjectFilter.Participating) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = StatusHorizontalPadding),
        verticalArrangement = Arrangement.spacedBy(StatusSectionSpacing)
    ) {
        ProjectStatusTopBar(onBackClick = onBackClick)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(StatusChipSpacing)
        ) {
            ProjectFilter.values().forEach { filter ->
                FilterChip(
                    text = filter.label,
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(StatusListSpacing)
        ) {
            items(statusItems(selectedFilter)) { item ->
                ProjectStatusCard(item = item)
            }
        }
    }
}

@Composable
private fun ProjectStatusTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clickable(onClick = onBackClick), contentAlignment = Alignment.Center) {
            Text("<", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Text(
            text = "프로젝트 현황",
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
    }
}

@Composable
private fun FilterChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.height(38.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(22.dp),
        color = if (selected) ItdaHomeExploreButtonGray else ItdaWhite,
        border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontFamily = DotSans,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = if (selected) ItdaWhite else ItdaSecondaryTextColor
            )
        }
    }
}

@Composable
private fun ProjectStatusCard(item: ProjectStatusUiModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = ItdaWhite,
        border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(StatusCardInnerSpacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(item.title, fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(item.status, fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = ItdaSecondaryTextColor)
            }
            Text(item.description, fontFamily = DotSans, fontSize = 13.sp, color = ItdaSecondaryTextColor)
        }
    }
}

private fun statusItems(filter: ProjectFilter): List<ProjectStatusUiModel> {
    return when (filter) {
        ProjectFilter.Participating -> listOf(
            ProjectStatusUiModel("IT-DA Android", "프로필/설정 화면 구현 진행 중", "진행중"),
            ProjectStatusUiModel("캡스톤 매칭 서비스", "백엔드 API 연동 작업 진행", "진행중")
        )
        ProjectFilter.Applied -> listOf(
            ProjectStatusUiModel("AI 일정 도우미", "서류 심사 결과 대기 중", "대기"),
            ProjectStatusUiModel("스터디 팀빌딩", "인터뷰 일정 조율 중", "검토중")
        )
        ProjectFilter.Finished -> listOf(
            ProjectStatusUiModel("학교 축제 웹", "프론트엔드 구현 담당 완료", "완료"),
            ProjectStatusUiModel("동아리 홈페이지", "유지보수 배포까지 완료", "완료")
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProjectStatusScreenPreview() {
    ITDATheme { ProjectStatusScreen(onBackClick = {}) }
}

