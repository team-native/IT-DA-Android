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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.it_da.BuildConfig
import com.example.it_da.ui.theme.DotSans
import com.example.it_da.ui.theme.ItdaGuideGray
import com.example.it_da.ui.theme.ItdaSecondaryTextColor
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

private val VersionHorizontalPadding = 24.dp
private val VersionSectionSpacing = 26.dp
private val VersionCardSpacing = 10.dp

@Composable
fun VersionInfoScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = VersionHorizontalPadding),
        verticalArrangement = Arrangement.spacedBy(VersionSectionSpacing)
    ) {
        VersionTopBar(onBackClick = onBackClick)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            color = ItdaWhite,
            border = BorderStroke(1.dp, ItdaGuideGray.copy(alpha = 0.45f))
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(VersionCardSpacing)
            ) {
                VersionRow(label = "현재 버전", value = BuildConfig.VERSION_NAME)
                VersionRow(label = "버전 코드", value = BuildConfig.VERSION_CODE.toString())
            }
        }
    }
}

@Composable
private fun VersionTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clickable(onClick = onBackClick), contentAlignment = Alignment.Center) {
            Text("<", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Text(
            text = "버전 정보",
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
    }
}

@Composable
private fun VersionRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(value, fontFamily = DotSans, fontSize = 14.sp, color = ItdaSecondaryTextColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun VersionInfoScreenPreview() {
    ITDATheme { VersionInfoScreen(onBackClick = {}) }
}

