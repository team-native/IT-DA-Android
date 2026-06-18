package com.example.it_da.ui.screen.signup.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.it_da.ui.commonComponent.ItdaOutlinedTextField
import com.example.it_da.ui.commonComponent.button.ItdaPrimaryButton
import com.example.it_da.ui.commonComponent.bar.ItdaTopBar
import com.example.it_da.ui.screen.signup.state.SignUpAccountUiState
import com.example.it_da.ui.theme.ITDATheme
import com.example.it_da.ui.theme.ItdaSecondaryTextColor

private val SignUpAccountTopSpacing = 37.dp
private val SignUpAccountTitleDescriptionSpacing = 16.dp
private val SignUpAccountDescriptionFieldSpacing = 29.dp
private val SignUpAccountFieldSpacing = 21.dp
private val SignUpAccountBottomSpacing = 45.dp
private val SignUpAccountHorizontalPadding = 32.dp
private const val SignUpAccountContentWeight = 1f
private const val SignUpAccountFlexibleSpacingWeight = 1f

// Assembles the first sign-up step from focused form components.
@Composable
fun SignUpAccountScreen(
    uiState: SignUpAccountUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmChange: (String) -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ItdaTopBar(title = "회원 가입")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(SignUpAccountContentWeight)
                    .padding(horizontal = SignUpAccountHorizontalPadding)
            ) {
                Spacer(modifier = Modifier.height(SignUpAccountTopSpacing))

                Text(
                    text = "계정 만들기",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(SignUpAccountTitleDescriptionSpacing))

                Text(
                    text = "서비스를 이용하기 위해 기본 정보를 입력해 주세요",
                    color = ItdaSecondaryTextColor,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(SignUpAccountDescriptionFieldSpacing))

                ItdaOutlinedTextField(
                    label = "아이디",
                    value = uiState.id,
                    onValueChange = onIdChange,
                    placeholder = "6~15글자"
                )

                Spacer(modifier = Modifier.height(SignUpAccountFieldSpacing))

                ItdaOutlinedTextField(
                    label = "비밀번호",
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    placeholder = "8~20글자",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(SignUpAccountFieldSpacing))

                ItdaOutlinedTextField(
                    label = "비밀번호 확인",
                    value = uiState.passwordConfirm,
                    onValueChange = onPasswordConfirmChange,
                    placeholder = "8~20글자",
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.weight(SignUpAccountFlexibleSpacingWeight))

                ItdaPrimaryButton(
                    enabled = uiState.isNextEnabled,
                    onClick = onNextClick
                )

                Spacer(modifier = Modifier.height(SignUpAccountBottomSpacing))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpAccountScreenPreview() {
    ITDATheme {
        SignUpAccountScreen(
            uiState = SignUpAccountUiState(),
            onIdChange = {},
            onPasswordChange = {},
            onPasswordConfirmChange = {},
            onNextClick = {}
        )
    }
}
