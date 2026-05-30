package com.example.it_da.ui.screen.signup.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.commonComponent.ItdaSectionHeader
import com.example.it_da.ui.commonComponent.ItdaDropdownTextField
import com.example.it_da.ui.commonComponent.ItdaLayoutDefaults
import com.example.it_da.ui.commonComponent.ItdaOutlinedTextField
import com.example.it_da.ui.commonComponent.ItdaPrimaryButton
import com.example.it_da.ui.commonComponent.ItdaTopBar
import com.example.it_da.ui.screen.signup.state.SignUpAdditionalInfoUiState
import com.example.it_da.ui.theme.ITDATheme

private val SignUpAdditionalTopSpacing = 31.dp
private val SignUpAdditionalHeaderFieldSpacing = 23.dp
private val SignUpAdditionalButtonSpacing = 120.dp

// Assembles the second sign-up step for additional matching information.
@Composable
fun SignUpAdditionalInfoScreen(
    uiState: SignUpAdditionalInfoUiState,
    onNameChange: (String) -> Unit,
    onInterestFieldChange: (String) -> Unit,
    onTechStackChange: (String) -> Unit,
    onCohortChange: (String) -> Unit,
    onDepartmentChange: (String) -> Unit,
    onDropdownArrowClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        ItdaTopBar(title = "추가 정보 입력")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(SignUpAdditionalTopSpacing))

            ItdaSectionHeader(
                title = "추가 정보 입력",
                description = "매칭 품질을 높이기 위해 몇 가지 정보를 입력해 주세요"
            )

            Spacer(modifier = Modifier.height(SignUpAdditionalHeaderFieldSpacing))

            ItdaOutlinedTextField(
                label = "이름",
                value = uiState.name,
                onValueChange = onNameChange,
                placeholder = "예: 홍길동, 가나다, 하지와레"
            )

            Spacer(modifier = Modifier.height(ItdaLayoutDefaults.ShortVerticalSpacing))

            ItdaDropdownTextField(
                label = "관심 분야",
                value = uiState.interestField,
                onValueChange = onInterestFieldChange,
                placeholder = "Back-end",
                onArrowClick = onDropdownArrowClick
            )

            Spacer(modifier = Modifier.height(ItdaLayoutDefaults.ShortVerticalSpacing))

            ItdaOutlinedTextField(
                label = "기술 스택",
                value = uiState.techStack,
                onValueChange = onTechStackChange,
                placeholder = "예: Python, Figma, Swift"
            )

            Spacer(modifier = Modifier.height(ItdaLayoutDefaults.ShortVerticalSpacing))

            ItdaDropdownTextField(
                label = "기수",
                value = uiState.cohort,
                onValueChange = onCohortChange,
                placeholder = "10기",
                onArrowClick = onDropdownArrowClick
            )

            Spacer(modifier = Modifier.height(ItdaLayoutDefaults.ShortVerticalSpacing))

            ItdaDropdownTextField(
                label = "학과",
                value = uiState.department,
                onValueChange = onDepartmentChange,
                placeholder = "SW과",
                onArrowClick = onDropdownArrowClick
            )

            Spacer(modifier = Modifier.height(SignUpAdditionalButtonSpacing))

            ItdaPrimaryButton(
                enabled = uiState.isNextEnabled,
                onClick = onNextClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpAdditionalInfoScreenPreview() {
    ITDATheme {
        SignUpAdditionalInfoScreen(
            uiState = SignUpAdditionalInfoUiState(),
            onNameChange = {},
            onInterestFieldChange = {},
            onTechStackChange = {},
            onCohortChange = {},
            onDepartmentChange = {},
            onDropdownArrowClick = {},
            onNextClick = {}
        )
    }
}
