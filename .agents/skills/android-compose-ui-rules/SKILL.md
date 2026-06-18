---
name: android-compose-ui-rules
description: Use when creating Jetpack Compose UI screens. Enforce small responsibility-based composables, Screen -> Content -> Section -> Component structure, and make most UI views reusable components instead of one huge composable.
---

# Android Compose UI Rules Skill

## Purpose

Use this skill when creating or modifying Jetpack Compose UI.

Each screen must be divided into small composable components.

Do not create one huge composable containing the entire UI.

Most UI views should be created as reusable UI elements or components first, then assembled into screens.

Korean explanation:
이 스킬은 Jetpack Compose UI를 만들거나 수정할 때 사용한다.

하나의 화면을 하나의 거대한 Composable로 만들지 않는다.

대부분의 View는 작은 UI 요소 또는 Component로 먼저 만든 뒤, Screen에서 조립해서 사용한다.

---

## Main Rule

Separate UI by responsibility.

Each composable should have one clear UI responsibility.

Bad:

```kotlin
@Composable
fun HomeScreen() {
    Column {
        Text(...)
        Image(...)
        Button(...)
        Text(...)
        Row(...)
    }
}
```

Good:

```kotlin
@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
private fun HomeContent() {
    Column {
        HomeTitle()
        HomeDescription()
        HomeImage()
        HomeStartButton()
    }
}
```

Korean explanation:
UI는 역할별로 나눈다.

`HomeScreen` 안에 `Text`, `Image`, `Button` 등을 전부 직접 넣지 말고, 의미 있는 단위로 분리한다.

---

## Reusable View Rule

Most views should be made as reusable components.

Do not write the same UI code repeatedly inside multiple screens.

If a UI element has meaning, design value, or reuse potential, extract it into a composable component.

Examples of reusable UI components:

```text
AppButton
AppTextField
BookCard
UserProfileCard
EmptyStateView
LoadingContent
ErrorMessageText
TopNavigationBar
```

Korean explanation:
대부분의 View는 재사용 가능한 Component로 만든다.

같은 UI 코드를 여러 화면에 반복해서 작성하지 않는다.

의미가 있거나, 디자인 규칙이 있거나, 나중에 다시 사용할 가능성이 있는 UI는 Composable Component로 분리한다.

---

## Reuse Decision Rule

Create a reusable component when:

- The UI appears more than once.
- The UI has a clear responsibility.
- The UI has its own design rule.
- The UI makes the screen file too long.
- The UI may be used again later.
- The UI receives data and events from the parent.

Do not create a reusable component when:

- The UI is a single `Text` with no special meaning.
- The UI is a single `Spacer` with no special meaning.
- The component name would be unclear.
- Splitting it makes the code harder to read.

Korean explanation:
다음과 같은 경우 Component로 분리한다.

- 같은 UI가 두 번 이상 사용될 때
- UI의 역할이 명확할 때
- 해당 UI만의 디자인 규칙이 있을 때
- Screen 파일이 너무 길어질 때
- 나중에 다시 사용할 가능성이 있을 때
- 부모로부터 데이터와 이벤트를 받아서 표시할 때

하지만 단순한 `Text`, `Spacer` 하나까지 무조건 분리하지는 않는다.

---

## Recommended Screen Structure

Use this pattern:

```text
Screen
-> Content
-> Section
-> Small UI Components
-> Shared Reusable Components
```

Example:

```text
LoginScreen
-> LoginContent
-> LoginTitle
-> LoginTextFields
-> LoginButton
-> LoginErrorMessage
```

Another example:

```text
HomeScreen
-> HomeContent
-> HomeHeaderSection
-> HomeBookSection
-> BookCard
-> AppButton
```

Korean explanation:
화면은 다음 구조로 나누는 것을 권장한다.

`Screen`은 상태와 이벤트를 연결한다.

`Content`는 화면의 큰 레이아웃을 담당한다.

`Section`은 화면 안의 구역을 담당한다.

`Small UI Components`는 의미 있는 작은 UI 요소를 담당한다.

`Shared Reusable Components`는 여러 화면에서 재사용되는 공통 UI를 담당한다.

---

## Recommended Folder Structure

Use this kind of structure for UI files:

```text
ui/
 ├── screen/
 │   ├── login/
 │   │   ├── LoginScreen.kt
 │   │   └── LoginUiState.kt
 │   │
 │   ├── home/
 │   │   ├── HomeScreen.kt
 │   │   └── HomeUiState.kt
 │   │
 │   └── profile/
 │       ├── ProfileScreen.kt
 │       └── ProfileUiState.kt
 │
 ├── component/
 │   ├── AppButton.kt
 │   ├── AppTextField.kt
 │   ├── BookCard.kt
 │   ├── LoadingContent.kt
 │   └── ErrorMessageText.kt
 │
 └── theme/
     ├── Color.kt
     ├── Dimens.kt
     ├── Theme.kt
     └── Type.kt
```

Korean explanation:
화면별 UI는 `screen` 폴더에 둔다.

여러 화면에서 재사용할 수 있는 공통 UI는 `component` 폴더에 둔다.

색상, 글꼴, 테마 설정은 `theme` 폴더에 둔다.

---

## Screen Rule

The screen composable connects state and events.

The screen should not contain all layout details directly.

Screen composables may know about UI state and event callbacks.

Small UI components should not know about ViewModel directly.

```kotlin
@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    LoginContent(
        loginUiState = loginUiState,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onLoginClick = onLoginClick
    )
}
```

Korean explanation:
`LoginScreen`은 화면의 상태와 이벤트를 연결하는 역할을 한다.

화면의 세부 UI를 전부 직접 구현하지 않는다.

ViewModel에서 받은 상태와 이벤트를 `LoginContent` 또는 작은 Component에 전달한다.

---

## Content Rule

Content composable contains the main layout structure.

Content composables arrange sections and components.

Content composables should avoid business logic.

```kotlin
private val LoginContentSectionSpacing = 24.dp

@Composable
private fun LoginContent(
    loginUiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTitle()

        Spacer(modifier = Modifier.height(LoginContentSectionSpacing))

        LoginTextFields(
            email = loginUiState.email,
            password = loginUiState.password,
            onEmailChange = onEmailChange,
            onPasswordChange = onPasswordChange
        )

        Spacer(modifier = Modifier.height(LoginContentSectionSpacing))

        LoginButton(
            isLoading = loginUiState.isLoading,
            onLoginClick = onLoginClick
        )

        LoginErrorMessage(
            errorMessage = loginUiState.errorMessage
        )
    }
}
```

Korean explanation:
`Content`는 화면의 전체 배치를 담당한다.

`Title`, `TextFields`, `Button`, `ErrorMessage` 같은 작은 UI를 조립한다.

서버 통신, 데이터 가공, 로그인 판단 같은 비즈니스 로직은 넣지 않는다.

---

## Section Rule

A section composable groups related UI components.

Use sections when a screen has multiple visual areas.

Example:

```kotlin
private val HomeSectionContentSpacing = 12.dp

@Composable
private fun HomeBookSection(
    books: List<Book>,
    onBookClick: (Book) -> Unit
) {
    Column {
        HomeSectionTitle(text = "Recommended Books")

        Spacer(modifier = Modifier.height(HomeSectionContentSpacing))

        LazyRow {
            items(books) { book ->
                BookCard(
                    book = book,
                    onBookClick = onBookClick
                )
            }
        }
    }
}
```

Korean explanation:
`Section`은 서로 관련 있는 UI 요소들을 하나로 묶는 역할을 한다.

예를 들어 홈 화면에 추천 책 영역, 인기 책 영역, 사용자 정보 영역이 있다면 각각 Section으로 분리한다.

---

## Small Component Rule

Each meaningful UI element should be separated into its own composable.

A small component should focus only on displaying UI.

A small component should receive required data through parameters.

A small component should receive user actions through lambda parameters.

```kotlin
/**
 * Displays the title text for the login screen.
 *
 * Korean explanation:
 * 로그인 화면의 제목 텍스트를 보여주는 Composable이다.
 */
@Composable
private fun LoginTitle() {
    Text(
        text = "Login",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
}
```

```kotlin
private val LoginTextFieldSpacing = 12.dp

/**
 * Displays email and password input fields.
 *
 * Korean explanation:
 * 이메일과 비밀번호 입력창을 보여주는 Composable이다.
 */
@Composable
private fun LoginTextFields(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = {
                Text(text = "Email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(LoginTextFieldSpacing))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

```kotlin
/**
 * Displays the login button.
 *
 * Korean explanation:
 * 로그인 버튼을 보여주는 Composable이다.
 */
@Composable
private fun LoginButton(
    isLoading: Boolean,
    onLoginClick: () -> Unit
) {
    Button(
        onClick = onLoginClick,
        enabled = !isLoading,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = if (isLoading) "Loading..." else "Login"
        )
    }
}
```

```kotlin
/**
 * Displays an error message when login fails.
 *
 * Korean explanation:
 * 로그인 실패 시 에러 메시지를 보여주는 Composable이다.
 */
@Composable
private fun LoginErrorMessage(
    errorMessage: String?
) {
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error
        )
    }
}
```

Korean explanation:
작은 Component는 UI 표시만 담당한다.

데이터는 파라미터로 받고, 클릭 같은 이벤트는 람다로 받는다.

---

## Shared Component Rule

If a component can be reused in multiple screens, place it in the shared `ui/component` package.

Shared components should not depend on a specific screen name or specific ViewModel.

Good:

```kotlin
/**
 * Displays a reusable app button.
 *
 * Korean explanation:
 * 여러 화면에서 사용할 수 있는 공통 버튼 Composable이다.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}
```

Bad:

```kotlin
@Composable
fun AppButton(
    loginViewModel: LoginViewModel
) {
    Button(
        onClick = {
            loginViewModel.login()
        }
    ) {
        Text(text = "Login")
    }
}
```

Korean explanation:
여러 화면에서 사용할 수 있는 Component는 `ui/component`에 둔다.

공통 Component는 특정 Screen이나 특정 ViewModel에 의존하면 안 된다.

필요한 값과 이벤트만 파라미터로 받아야 한다.

---

## Component Parameter Rule

Components should not directly know ViewModel.

Components should receive only the data and callbacks they need.

Good:

```kotlin
AppButton(
    text = "Login",
    onClick = onLoginClick
)
```

Bad:

```kotlin
AppButton(
    viewModel = loginViewModel
)
```

Korean explanation:
Component가 ViewModel을 직접 알게 만들지 않는다.

Component는 필요한 값과 이벤트만 파라미터로 받는다.

ViewModel 연결은 Screen에서 처리한다.

---

## State Rule

Reusable components should usually be stateless.

State should be owned by the screen or ViewModel.

Good:

```kotlin
@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        modifier = modifier
    )
}
```

Bad:

```kotlin
@Composable
fun AppTextField() {
    var value by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
        }
    )
}
```

Korean explanation:
재사용 Component는 가능하면 상태를 직접 가지지 않는다.

상태는 Screen 또는 ViewModel이 관리한다.

Component는 상태 값을 받아서 보여주고, 변경 이벤트를 위로 전달한다.

---

## Event Rule

User actions should be passed as lambda parameters.

Examples:

```kotlin
onClick: () -> Unit
onValueChange: (String) -> Unit
onItemClick: (Book) -> Unit
onBackClick: () -> Unit
```

Korean explanation:
클릭, 입력 변경, 아이템 클릭, 뒤로가기 같은 사용자 동작은 람다 파라미터로 받는다.

이렇게 하면 Component가 특정 화면이나 ViewModel에 강하게 묶이지 않는다.

---

## Naming Rule

Composable names must describe their UI responsibility.

Good:

```kotlin
LoginTitle()
LoginButton()
BookCoverImage()
BookDescriptionText()
StartButton()
UserProfileCard()
LoadingContent()
ErrorMessageText()
```

Bad:

```kotlin
Text1()
Button1()
MyComposable()
BoxView()
Layout()
Component()
```

Korean explanation:
Composable 이름은 역할이 바로 드러나야 한다.

`Text1`, `Button1`, `MyComposable`처럼 의미가 불분명한 이름은 사용하지 않는다.

---

## Spacer and spacedBy Constant Rule

When using `Spacer` or `Arrangement.spacedBy()` for layout spacing, define named spacing constants before using them.

Do not pass raw `dp` values directly to `Spacer` or `Arrangement.spacedBy()`, even when a value is used only once.

Screen-only spacing values should usually be declared as `private val` near the top of the same Screen or Component file.

Spacing values shared by multiple screens should be moved to `ui/theme/Dimens.kt`.

Use the shared values in `Dimens.kt` for app-wide `Spacer` and `Arrangement.spacedBy()` spacing.

Keep screen-only or component-only spacing values as `private val` in the owning file.

Do not merge spacing values only because their current `dp` numbers are equal. Move a value to `Dimens.kt` when it represents the same reusable app-wide spacing rule.

Do not create a separate Composable file just for a meaningless single `Spacer`.

Good:

```kotlin
// ui/theme/Dimens.kt
object Dimens {
    val ShortVerticalSpacing = 8.dp
    val LongVerticalSpacing = 24.dp
}

// Screen or Component file
private val LoginLogoTopSpacing = 86.dp
private val LoginLogoBottomSpacing = 46.dp

Spacer(modifier = Modifier.height(LoginLogoTopSpacing))
Spacer(modifier = Modifier.height(LoginLogoBottomSpacing))

Column(
    verticalArrangement = Arrangement.spacedBy(Dimens.ShortVerticalSpacing)
) {
    // Content
}
```

Bad:

```kotlin
Spacer(modifier = Modifier.height(86.dp))
Spacer(modifier = Modifier.height(46.dp))
Spacer(modifier = Modifier.height(86.dp))

Column(
    verticalArrangement = Arrangement.spacedBy(12.dp)
) {
    // Content
}
```

Korean explanation:
`Spacer` 또는 `Arrangement.spacedBy()`로 간격을 줄 때는 `86.dp`, `12.dp`처럼 숫자를 직접 넣지 않는다.

간격값은 한 번만 사용하더라도 이름이 있는 상수로 먼저 분리하고 해당 상수값을 사용한다.

화면 안에서만 쓰는 간격은 해당 Screen 또는 Component 파일 상단에 `private val`로 둔다.

여러 화면의 `Spacer` 또는 `Arrangement.spacedBy()`에서 같은 디자인 의미로 공통 사용되는 간격은 `ui/theme/Dimens.kt`로 분리한다.

단순히 현재 `dp` 숫자가 같다는 이유만으로 공통값으로 합치지 않는다. 앱 전반에서 동일한 간격 규칙으로 재사용되는 값만 `Dimens.kt`에서 관리한다.

단, 의미 없는 `Spacer` 하나를 별도 Composable 파일로 만들지는 않는다.

---

## Modifier Rule

Pass `Modifier` from parent when the parent needs to control size, padding, or position.

Reusable components should expose `modifier: Modifier = Modifier`.

Apply the modifier to the outermost UI element of the component.

```kotlin
/**
 * Displays a reusable login button.
 *
 * Korean explanation:
 * 부모 Composable에서 크기와 위치를 조절할 수 있는 로그인 버튼이다.
 */
@Composable
private fun LoginButton(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit
) {
    Button(
        onClick = onLoginClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = R.string.login_button))
    }
}
```

Parent usage:

```kotlin
LoginButton(
    modifier = Modifier.fillMaxWidth(),
    onLoginClick = onLoginClick
)
```

Korean explanation:
재사용 Component는 `modifier`를 받을 수 있게 만든다.

부모가 크기, 패딩, 정렬, 위치를 조절할 수 있어야 한다.

`modifier`는 Component의 가장 바깥쪽 UI에 적용한다.

---

## String Resource Rule

Do not hard-code user-visible text inside Compose UI.

Android official guidance recommends externalizing app strings from code and using string resources so the app can support localization, reuse, and configuration-specific resources.

Bad:

```kotlin
Text(text = "Login")

Button(onClick = onLoginClick) {
    Text(text = "로그인")
}

Icon(
    imageVector = Icons.Default.ArrowBack,
    contentDescription = "뒤로가기"
)
```

Good:

```kotlin
Text(text = stringResource(id = R.string.login_title))

Button(onClick = onLoginClick) {
    Text(text = stringResource(id = R.string.login_button))
}

Icon(
    imageVector = Icons.Default.ArrowBack,
    contentDescription = stringResource(id = R.string.back_description)
)
```

`strings.xml`:

```xml
<string name="login_title">Login</string>
<string name="login_button">로그인</string>
<string name="back_description">뒤로가기</string>
```

When a reusable component receives a string resource id, annotate it with `@StringRes`.

```kotlin
@Composable
fun AppButton(
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(id = textRes))
    }
}
```

Do not move dynamic text into `strings.xml`.

Examples of dynamic text:

- User input
- Server response text
- ViewModel state text
- Formatted values that are created from runtime data

For formatted user-visible text, define the format string in `strings.xml` and pass runtime values through `stringResource`.

```xml
<string name="member_count">%1$d명 모집</string>
```

```kotlin
Text(text = stringResource(id = R.string.member_count, memberCount))
```

Korean explanation:
사용자에게 보이는 문자열은 Compose 코드에 직접 하드코딩하지 않는다.

`Text`, 버튼 문구, TextField label/placeholder, TopBar 제목, BottomNavigation label, `contentDescription`, Toast 메시지처럼 화면에 보이거나 접근성에 사용되는 문자열은 `strings.xml`에 정의하고 `stringResource()`로 사용한다.

이 규칙은 Android 공식 문서의 리소스 분리, Compose `stringResource` 사용, 앱 현지화 권장사항과 맞다.

다만 사용자 입력값, 서버 응답값, ViewModel 상태에서 전달되는 동적 문자열은 `strings.xml`로 옮기지 않는다.

---

## Theme Rule

Use `MaterialTheme` values instead of hard-coded colors when possible.

Good:

```kotlin
Text(
    text = stringResource(id = R.string.error_message),
    color = MaterialTheme.colorScheme.error
)
```

Bad:

```kotlin
Text(
    text = stringResource(id = R.string.error_message),
    color = Color.Red
)
```

Korean explanation:
가능하면 색상을 직접 하드코딩하지 말고 `MaterialTheme.colorScheme`을 사용한다.

그래야 다크 모드, 라이트 모드, 앱 전체 테마 변경에 대응하기 쉽다.

---

## Preview Rule

Create preview for screen-level UI when possible.

Also create previews for reusable shared components when useful.

```kotlin
@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            loginUiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onLoginClick = {}
        )
    }
}
```

```kotlin
@Preview(showBackground = true)
@Composable
private fun AppButtonPreview() {
    AppTheme {
        AppButton(
            text = "Start",
            onClick = {}
        )
    }
}
```

Korean explanation:
가능하면 Screen 단위 Preview를 만든다.

재사용되는 공통 Component도 Preview를 만들면 UI 확인이 쉬워진다.

---

## Avoid

Avoid these patterns:

- One giant composable with all UI.
- Mixing navigation logic inside small UI components.
- Mixing network logic inside UI.
- Passing ViewModel directly into reusable components.
- Using unclear composable names.
- Repeating the same UI code multiple times.
- Hard-coding user-visible text instead of using `strings.xml` and `stringResource`.
- Hard-coding colors instead of using theme colors.
- Creating a separate file for every meaningless `Text` or `Spacer`.
- Putting business logic inside UI components.

Korean explanation:
다음 방식은 피한다.

- 하나의 거대한 Composable에 모든 UI를 넣는 것
- 작은 UI Component 안에 Navigation 로직을 넣는 것
- UI 안에 네트워크 로직을 넣는 것
- 재사용 Component에 ViewModel을 직접 넘기는 것
- 의미 없는 이름을 사용하는 것
- 같은 UI 코드를 여러 번 반복하는 것
- 사용자에게 보이는 문자열을 `strings.xml`과 `stringResource` 대신 하드코딩하는 것
- 테마 색상 대신 색상을 하드코딩하는 것
- 의미 없는 `Text`, `Spacer`까지 전부 파일로 분리하는 것
- UI Component 안에 비즈니스 로직을 넣는 것

---

## Final Rule

Screens connect state and events.

Contents arrange the main layout.

Sections group related UI.

Components display small reusable UI.

ViewModels manage state and logic.

Repositories manage data.

Korean explanation:
Screen은 상태와 이벤트를 연결한다.

Content는 화면의 큰 레이아웃을 배치한다.

Section은 관련 있는 UI 묶음을 담당한다.

Component는 작고 재사용 가능한 UI를 보여준다.

ViewModel은 상태와 로직을 관리한다.

Repository는 데이터를 관리한다.

UI 코드는 이 역할 분리를 반드시 지켜야 한다.
