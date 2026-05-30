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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

private val IntroHorizontalPadding = 24.dp
private val IntroSectionSpacing = 10.dp
private val IntroTopSpacing = 20.dp

@Composable
fun SelfIntroductionScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var introduction by rememberSaveable {
        mutableStateOf("안녕하세요. 사용자 경험을 중요하게 생각하는 안드로이드 개발자입니다.")
    }
    var draftIntroduction by rememberSaveable { mutableStateOf(introduction) }
    var showEditDialog by rememberSaveable { mutableStateOf(false) }

    if (showEditDialog) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = { Text("자기소개 수정", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
            text = {
                OutlinedTextField(
                    value = draftIntroduction,
                    onValueChange = { draftIntroduction = it },
                    modifier = Modifier.fillMaxWidth().height(170.dp),
                    placeholder = { Text("자기소개를 입력해 주세요.", fontFamily = DotSans, color = ItdaSecondaryTextColor) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = ItdaGuideGray,
                        unfocusedBorderColor = ItdaGuideGray
                    )
                )
            },
            confirmButton = {
                Text(
                    text = "저장",
                    modifier = Modifier.clickable {
                        introduction = draftIntroduction
                        showEditDialog = false
                    },
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = ItdaHomeExploreButtonGray
                )
            },
            dismissButton = {
                Text(
                    text = "취소",
                    modifier = Modifier.clickable { showEditDialog = false },
                    fontFamily = DotSans,
                    fontSize = 14.sp,
                    color = ItdaSecondaryTextColor
                )
            },
            containerColor = ItdaWhite
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = IntroHorizontalPadding),
        verticalArrangement = Arrangement.spacedBy(IntroSectionSpacing)
    ) {
        SelfIntroductionTopBar(onBackClick = onBackClick)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("자기소개", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 21.sp)
            Surface(
                modifier = Modifier.clickable {
                    draftIntroduction = introduction
                    showEditDialog = true
                },
                shape = RoundedCornerShape(10.dp),
                color = ItdaWhite,
                border = BorderStroke(1.dp, ItdaGuideGray)
            ) {
                Text(
                    text = "수정하기",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    fontFamily = DotSans,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = ItdaSecondaryTextColor
                )
            }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().height(220.dp),
            shape = RoundedCornerShape(12.dp),
            color = ItdaWhite,
            border = BorderStroke(1.dp, ItdaGuideGray)
        ) {
            Text(
                text = introduction,
                modifier = Modifier.padding(14.dp),
                fontFamily = DotSans,
                fontSize = 15.sp,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun SelfIntroductionTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clickable(onClick = onBackClick), contentAlignment = Alignment.Center) {
            Text("<", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Text(
            text = "자기소개 작성",
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelfIntroductionScreenPreview() {
    ITDATheme { SelfIntroductionScreen(onBackClick = {}) }
}

