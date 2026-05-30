package com.example.it_da.ui.screen.profile

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
import com.example.it_da.ui.theme.ItdaWhite
import com.example.it_da.ui.theme.ITDATheme

private val PersonalInfoHorizontalPadding = 24.dp
private val PersonalInfoSectionSpacing = 12.dp
private val PersonalInfoTopSpacing = 20.dp
private val PersonalInfoFieldSpacing = 6.dp

@Composable
fun PersonalInfoScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("메타몽") }
    var department by rememberSaveable { mutableStateOf("SW개발과") }
    var grade by rememberSaveable { mutableStateOf("1학년") }
    var joinDate by rememberSaveable { mutableStateOf("2025년 1월") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = PersonalInfoHorizontalPadding),
        verticalArrangement = Arrangement.spacedBy(PersonalInfoSectionSpacing)
    ) {
        PersonalInfoTopBar(onBackClick = onBackClick)

        Column(verticalArrangement = Arrangement.spacedBy(PersonalInfoSectionSpacing)) {
            PersonalField("이름", name) { name = it }
            PersonalField("학과", department) { department = it }
            PersonalField("학년", grade) { grade = it }
            PersonalField("가입일", joinDate) { joinDate = it }
        }

        Surface(
            modifier = Modifier.fillMaxWidth().height(48.dp).clickable(onClick = onBackClick),
            shape = RoundedCornerShape(12.dp),
            color = ItdaHomeExploreButtonGray
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("저장", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = ItdaWhite)
            }
        }
    }
}

@Composable
private fun PersonalField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(PersonalInfoFieldSpacing)) {
        Text(label, fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(fontFamily = DotSans),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ItdaGuideGray,
                unfocusedBorderColor = ItdaGuideGray,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                focusedTextColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Composable
private fun PersonalInfoTopBar(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(36.dp).clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
        ) {
            Text("<", fontFamily = DotSans, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Text(
            text = "개인 정보 입력",
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = DotSans,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalInfoScreenPreview() {
    ITDATheme { PersonalInfoScreen(onBackClick = {}) }
}

