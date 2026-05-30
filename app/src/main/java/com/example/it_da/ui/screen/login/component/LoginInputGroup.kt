package com.example.it_da.ui.screen.login.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

private val LoginFieldSpacing = 20.dp

// Groups the id and password fields so their spacing stays consistent.
@Composable
fun LoginInputGroup(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LoginUnderlineTextField(
            value = id,
            onValueChange = onIdChange,
            placeholder = "아이디"
        )

        Spacer(modifier = Modifier.height(LoginFieldSpacing))

        LoginUnderlineTextField(
            value = password,
            onValueChange = onPasswordChange,
            placeholder = "비밀번호",
            visualTransformation = PasswordVisualTransformation()
        )
    }
}
