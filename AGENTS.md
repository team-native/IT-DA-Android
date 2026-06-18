# Project Purpose

This project is an Android application.

All code must follow the official Android Developers guidance as closely as possible.

This project should use a clean, maintainable, and scalable Android architecture.

가장 중요한 원칙은 다음과 같다.

> 하나의 파일, 하나의 클래스, 하나의 함수에 너무 많은 역할을 주지 않는다.

Each file should have one clear purpose.  
Each class should have one clear responsibility.  
Each function should do one clear job.

파일은 무조건 많이 나누는 것이 목적이 아니다.  
역할이 달라지는 순간 분리하는 것이 목적이다.

---

# Main Technology Stack

- Kotlin
- Jetpack Compose
- Material 3
- Android Architecture Components
- ViewModel
- StateFlow
- Repository pattern
- Retrofit for network communication when server communication is required

---

# Core Development Rules

- Build UI with Jetpack Compose.
- Do not create XML layouts unless explicitly requested.
- Follow the official Android app architecture guidance.
- Use layered architecture.
- Use separation of concerns.
- Use unidirectional data flow.
- Use a single source of truth for UI state.
- Each file must have one clear responsibility.
- Each class must have one clear responsibility.
- Each function must have one clear responsibility.
- Do not mix UI, ViewModel logic, Repository logic, network code, DTO definitions, and mapper logic in the same file.
- Do not create large files that handle everything.
- Split code by responsibility when the role of the code becomes different.
- Prefer small, focused files over one large file with mixed responsibilities.

## Korean Explanation

이 프로젝트에서는 하나의 파일이 여러 일을 동시에 하지 않도록 한다.

예를 들어 `LoginScreen.kt`가 화면 UI도 만들고, 서버 통신도 하고, 로그인 상태도 직접 관리하면 안 된다.

역할은 다음처럼 나누는 것을 원칙으로 한다.

- UI는 UI만 담당한다.
- ViewModel은 화면 상태와 이벤트 처리를 담당한다.
- Repository는 데이터 처리 흐름을 담당한다.
- ApiService는 서버 API 정의만 담당한다.
- DTO는 서버 요청/응답 데이터만 담당한다.
- Domain Model은 앱 내부에서 사용할 데이터만 담당한다.
- Mapper는 DTO와 Domain Model 변환만 담당한다.

---

# Responsibility Separation Rule

Each file should do only one main job.

A file should be easy to understand just by looking at its name.

## Bad Example

```text
LoginScreen.kt
- Draws UI
- Holds login state
- Calls Retrofit directly
- Parses server response
- Saves token
```

이 방식은 좋지 않다.  
하나의 파일이 UI, 상태 관리, 서버 통신, 데이터 처리까지 모두 담당하기 때문이다.

## Good Example

```text
LoginScreen.kt
- Draws UI only

LoginViewModel.kt
- Holds UI state
- Handles login button click
- Calls Repository

AuthRepository.kt
- Handles login data flow
- Calls remote data source

AuthApiService.kt
- Defines Retrofit API functions

LoginRequestDto.kt
- Defines login request body

LoginResponseDto.kt
- Defines login response body

LoginMapper.kt
- Converts DTO to domain model
```

이 방식은 좋다.  
각 파일의 역할이 명확해서 수정하기 쉽고, 에러가 발생했을 때 원인을 찾기 쉽다.

---

# When to Split a File

Split a file when:

- The file contains UI code and network code together.
- The file contains UI code and business logic together.
- The file contains ViewModel logic and Repository logic together.
- The file contains Repository logic and Retrofit setup together.
- The file contains DTOs and domain models together.
- The file contains screen UI and reusable components together.
- The file contains too many unrelated functions.
- The file becomes hard to understand at a glance.
- The file has more than one clear reason to change.
- A class or function starts doing more than one job.

## Korean Explanation

다음 상황이면 파일을 분리해야 한다.

- 화면 코드와 서버 통신 코드가 같은 파일에 있을 때
- ViewModel 코드와 Repository 코드가 같은 파일에 있을 때
- DTO와 Domain Model이 같은 파일에 있을 때
- 화면 전용 UI와 재사용 컴포넌트가 같은 파일에 섞여 있을 때
- 파일을 한눈에 이해하기 어려울 때
- 하나의 파일을 수정해야 하는 이유가 여러 개일 때

핵심은 **파일 하나당 하나의 책임**이다.

---

# Layer Responsibility Rules

The project should be separated into clear layers.

```text
UI Layer
↓
ViewModel Layer
↓
Repository Layer
↓
Data Layer
↓
Remote / Local Source
```

Each layer should only know what it needs to know.

## Korean Explanation

앱 구조는 계층별로 나눈다.

UI는 ViewModel만 알고,  
ViewModel은 Repository만 알고,  
Repository는 Remote 또는 Local 데이터 소스를 사용한다.

UI가 Retrofit을 직접 알면 안 된다.  
ViewModel이 Retrofit을 직접 호출하면 안 된다.  
Repository가 화면 UI를 알면 안 된다.

---

# UI Layer Rules

The UI layer should:

- Display data.
- Read UI state.
- Send user events upward through callbacks.
- Use Jetpack Compose.
- Use Material 3 components when possible.

The UI layer should not:

- Call Retrofit directly.
- Create Repository directly.
- Contain business logic.
- Contain DTO mapping logic.
- Decide how server communication works.

## Korean Explanation

UI 계층은 화면을 그리는 역할만 한다.

버튼을 눌렀을 때 직접 서버 통신을 하는 것이 아니라,  
`onLoginClick()` 같은 콜백을 통해 ViewModel에게 이벤트를 전달해야 한다.

---

# ViewModel Layer Rules

The ViewModel layer should:

- Hold UI state.
- Expose UI state using StateFlow.
- Handle UI events.
- Call Repository.
- Convert Repository results into UI state.

The ViewModel layer should not:

- Contain Compose UI code.
- Call Retrofit API services directly.
- Create Retrofit instances directly.
- Know detailed network implementation.
- Contain DTO classes.

## Korean Explanation

ViewModel은 화면 상태를 관리한다.

예를 들어 로그인 화면이라면 다음 상태를 관리할 수 있다.

- 이메일 입력값
- 비밀번호 입력값
- 로딩 상태
- 에러 메시지
- 로그인 성공 여부

하지만 ViewModel이 Retrofit을 직접 호출하면 안 된다.  
서버 통신은 Repository를 통해 요청해야 한다.

---

# Repository Layer Rules

The Repository layer should:

- Coordinate data operations.
- Call remote or local data sources.
- Hide data source implementation details from the ViewModel.
- Convert raw data into app-level result models when needed.
- Provide clean functions for the ViewModel.

The Repository layer should not:

- Contain Compose UI code.
- Hold UI state directly.
- Know screen layout details.
- Own navigation logic.

## Korean Explanation

Repository는 데이터 처리 흐름을 담당한다.

ViewModel은 `authRepository.login()`처럼 간단하게 호출하고,  
Repository 내부에서 Retrofit API 호출, DTO 변환, 에러 처리 등을 담당한다.

---

# Data Layer Rules

The data layer should:

- Define DTOs.
- Define Retrofit API services.
- Define Retrofit instance creation.
- Handle network request and response structures.
- Handle mapping between DTOs and domain models when needed.

The data layer should not:

- Contain Compose UI code.
- Contain screen state.
- Contain navigation code.
- Directly control UI behavior.

## Korean Explanation

Data 계층은 서버 통신, DTO, Retrofit, Mapper 같은 데이터 관련 코드를 담당한다.

화면을 어떻게 보여줄지는 Data 계층이 알면 안 된다.

---

# File Responsibility Rule

Each file should have one clear role.

---

## `MainActivity.kt`

- App entry point only.
- Sets the Compose content.
- Applies the app theme.
- Starts the app navigation.
- Should not contain business logic.
- Should not contain network logic.
- Should not contain screen implementation details.

## Korean Explanation

`MainActivity.kt`는 앱의 시작점 역할만 한다.

여기서 로그인 로직, 서버 통신, 화면 세부 UI를 직접 작성하지 않는다.

---

## `Navigation.kt` or `AppNavigation.kt`

- Defines app navigation only.
- Owns `NavHost`, routes, and screen destinations.
- Connects screens together.
- Should not contain detailed screen UI layout code.
- Should not contain business logic.
- Should not call Retrofit.
- Should not contain Repository logic.

## Korean Explanation

`AppNavigation.kt`는 화면 이동 구조만 담당한다.

어떤 화면에서 어떤 화면으로 이동하는지만 정의하고,  
각 화면의 자세한 UI 코드는 `*Screen.kt`에서 작성한다.

---

## `Route.kt` or `ScreenRoute.kt`

- Defines navigation route names only.
- Prefer sealed class or sealed interface for routes.
- Should not contain UI code.
- Should not contain navigation UI layout.

Example:

```kotlin
sealed class Route(val route: String) {
    data object Login : Route("login")
    data object Home : Route("home")
}
```

## Korean Explanation

`Route.kt`는 화면 경로 이름을 안전하게 관리하는 파일이다.

문자열을 여기저기 직접 쓰는 것보다 `Route.Login.route`처럼 관리하는 것이 좋다.

---

## `*Screen.kt` or `*Layout.kt`

- Defines one screen's Compose UI.
- Reads UI state.
- Sends user events upward through callbacks.
- Calls reusable components.
- Should not call Retrofit directly.
- Should not create Repository directly.
- Should not contain DTO classes.
- Should not contain network request logic.
- Should not contain large reusable components if they can be separated.

Example responsibility:

```text
LoginScreen.kt
- Shows email text field
- Shows password text field
- Shows login button
- Reads LoginUiState
- Sends onLoginClick event to ViewModel
```

## Korean Explanation

`*Screen.kt`는 한 화면의 UI를 담당한다.

서버 통신을 직접 하지 않고,  
버튼 클릭 같은 이벤트는 ViewModel로 전달한다.

---

## `*Component.kt`

- Defines reusable Compose UI components only.
- Should be stateless when possible.
- Receives data through parameters.
- Receives events through callback parameters.
- Should not know about ViewModel directly unless there is a strong reason.
- Should not call Repository.
- Should not call Retrofit.
- Should not contain screen-level logic.

Example responsibility:

```text
LoginButton.kt
- Displays a button
- Receives text
- Receives onClick callback
```

## Korean Explanation

`*Component.kt`는 재사용 가능한 UI 조각을 담당한다.

예를 들어 버튼, 카드, 입력창 같은 UI를 분리할 수 있다.

가능하면 Component는 직접 상태를 가지지 않고,  
필요한 값과 이벤트를 파라미터로 받는다.

---

## `*ViewModel.kt`

- Holds UI state.
- Exposes UI state as `StateFlow`.
- Handles UI events.
- Calls Repository.
- Updates UI state based on Repository result.
- Should not contain Compose UI code.
- Should not call Retrofit API services directly.
- Should not create Retrofit instances.
- Should not contain DTO definitions.
- Should not handle detailed UI layout logic.

Example responsibility:

```text
LoginViewModel.kt
- Stores LoginUiState
- Handles email/password changes
- Handles login button click
- Calls AuthRepository.login()
```

## Korean Explanation

`*ViewModel.kt`는 화면 상태와 이벤트 처리를 담당한다.

Composable 화면에서 발생한 이벤트를 ViewModel이 받고,  
필요하면 Repository를 호출해서 데이터를 가져온다.

---

## `*UiState.kt`

- Defines UI state data only.
- Should usually be a data class.
- Represents what the screen needs to display.
- Should not contain network request code.
- Should not call Repository.
- Should not call Retrofit.
- Should not contain business logic.

Example:

```kotlin
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
```

## Korean Explanation

`*UiState.kt`는 화면에 필요한 상태값만 가진다.

예를 들어 로그인 화면이라면 이메일, 비밀번호, 로딩 상태, 에러 메시지 등을 담는다.

---

## `*Repository.kt`

- Coordinates data operations.
- Calls remote or local data sources.
- Hides data implementation details from ViewModel.
- Converts raw data into app-level result or domain models when needed.
- Should not contain Compose UI code.
- Should not contain screen layout logic.
- Should not own UI state directly.
- Should not create UI events.

Example responsibility:

```text
AuthRepository.kt
- Calls AuthApiService
- Receives LoginResponseDto
- Maps DTO to domain model
- Returns ResultState to ViewModel
```

## Korean Explanation

`*Repository.kt`는 데이터 흐름을 관리한다.

ViewModel은 Repository를 통해 데이터를 요청하고,  
Repository는 실제 서버 통신이나 데이터 변환을 처리한다.

---

## `*ApiService.kt`

- Defines Retrofit endpoint functions only.
- Contains Retrofit annotations such as:
  - `@GET`
  - `@POST`
  - `@Body`
  - `@Path`
  - `@Query`
  - `@Header`
- Should not contain business logic.
- Should not contain UI logic.
- Should not contain Repository logic.
- Should not create Retrofit instance.

Example:

```kotlin
interface AuthApiService {

    // Sends login request data to the server and receives login response data.
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): LoginResponseDto
}
```

## Korean Explanation

`*ApiService.kt`는 서버 API 명세를 코드로 정의하는 파일이다.

여기에는 `@POST`, `@GET`, `@Body`, `@Query` 같은 Retrofit 어노테이션만 작성한다.

비즈니스 로직은 작성하지 않는다.

---

## `RetrofitClient.kt` or `RetrofitInstance.kt`

- Creates and configures the Retrofit instance only.
- Separates Retrofit instance creation from API service creation.
- May configure:
  - Base URL
  - ConverterFactory
  - OkHttpClient
  - Interceptors
  - Logging
- Should not contain API endpoint functions.
- Should not contain Repository logic.
- Should not contain UI logic.
- Should not contain ViewModel logic.

Example responsibility:

```text
RetrofitClient.kt
- Creates Retrofit
- Adds Gson converter
- Adds OkHttpClient
```

## Korean Explanation

`RetrofitClient.kt`는 Retrofit 객체를 만들고 설정하는 역할만 한다.

API 함수 정의는 `ApiService.kt`에 작성하고,  
Retrofit 생성 코드는 `RetrofitClient.kt`에 작성한다.

---

## `*Dto.kt`

- Represents network request or response data only.
- Should match the server API contract.
- Should be used for Retrofit communication.
- Should not contain UI logic.
- Should not be used directly as screen UI state when domain models are needed.
- Should not contain Compose code.
- Should not contain Repository logic.

Example:

```kotlin
data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String
)
```

## Korean Explanation

DTO는 서버와 주고받는 데이터 형식을 의미한다.

서버 명세에 맞춰 작성하며,  
앱 내부에서 사용할 데이터 구조와 다를 수 있다.

---

## `*Model.kt` or Domain Model Files

- Represents app-level data used inside the app.
- Should be independent from the server API contract when possible.
- Should be used by ViewModel or UI when DTO should not be exposed directly.
- Should not contain Retrofit annotations.
- Should not contain Compose UI code.

Example:

```kotlin
data class User(
    val accessToken: String,
    val refreshToken: String
)
```

## Korean Explanation

Domain Model은 앱 내부에서 사용할 데이터 구조이다.

서버 응답 DTO를 그대로 화면에 쓰기보다,  
앱에서 필요한 형태로 바꿔서 사용하는 것이 좋다.

---

## `*Mapper.kt`

- Converts DTOs into domain models.
- Converts domain models into DTOs when needed.
- Should not contain UI logic.
- Should not call Retrofit.
- Should not hold UI state.
- Should have only conversion responsibility.

Example:

```kotlin
// Converts server login response data into app-level user data.
fun LoginResponseDto.toUser(): User {
    return User(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
```

## Korean Explanation

Mapper는 DTO와 Domain Model을 변환하는 역할만 한다.

예를 들어 서버에서 받은 `LoginResponseDto`를  
앱 내부에서 사용할 `User`로 바꿀 수 있다.

---

## `ResultState.kt` or `ApiResult.kt`

- Represents loading, success, and error states.
- Used to safely deliver operation results.
- Should not contain UI layout logic.
- Should not call Retrofit directly.
- Should not contain DTO definitions unless absolutely required.

Example:

```kotlin
sealed interface ResultState<out T> {
    data object Loading : ResultState<Nothing>
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val message: String) : ResultState<Nothing>
}
```

## Korean Explanation

`ResultState.kt`는 요청 상태를 안전하게 표현하기 위한 파일이다.

예를 들어 서버 요청 중이면 `Loading`,  
성공하면 `Success`,  
실패하면 `Error`로 표현한다.

---

# Recommended Package Structure

Use this structure unless the existing project already has a clear structure.

```text
com.example.app
├── MainActivity.kt
├── navigation
│   ├── AppNavigation.kt
│   └── Route.kt
├── ui
│   ├── screen
│   │   └── ExampleScreen.kt
│   ├── component
│   │   └── ExampleButton.kt
│   ├── state
│   │   └── ExampleUiState.kt
│   └── theme
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
├── viewmodel
│   └── ExampleViewModel.kt
├── data
│   ├── model
│   │   ├── ExampleRequestDto.kt
│   │   └── ExampleResponseDto.kt
│   ├── remote
│   │   ├── ExampleApiService.kt
│   │   └── RetrofitClient.kt
│   ├── repository
│   │   └── ExampleRepository.kt
│   └── mapper
│       └── ExampleMapper.kt
├── domain
│   └── model
│       └── Example.kt
└── util
    └── ResultState.kt
```

## Korean Explanation

패키지는 역할별로 나눈다.

- `navigation`: 화면 이동
- `ui`: 화면과 컴포넌트
- `viewmodel`: 화면 상태 관리
- `data`: 서버 통신, DTO, Repository, Mapper
- `domain`: 앱 내부 모델
- `util`: 공통 유틸 클래스

---

# Naming Rules

Use names that clearly describe the responsibility of the file.

Prefer specific names:

```text
AuthRepository.kt
LoginRequestDto.kt
LoginResponseDto.kt
LoginMapper.kt
LoginViewModel.kt
LoginUiState.kt
LoginScreen.kt
LoginButton.kt
AuthApiService.kt
RetrofitClient.kt
```

Avoid vague names unless the role is very clear:

```text
Manager.kt
Helper.kt
Utils.kt
DataHandler.kt
Controller.kt
```

If a vague name is used, rename it to a more specific responsibility-based name.

## Korean Explanation

파일 이름은 역할이 바로 보이게 작성한다.

`Manager`, `Helper`, `Utils`처럼 애매한 이름은 가능한 피한다.  
대신 `AuthRepository`, `LoginViewModel`, `LoginMapper`처럼 역할이 드러나는 이름을 사용한다.

---

# Compose UI Rules

- Use Jetpack Compose for UI.
- Use Material 3 components when possible.
- Keep Composable functions small and focused.
- Separate reusable UI parts into `*Component.kt`.
- Screen Composables should receive state and event callbacks.
- Avoid putting business logic inside Composable functions.
- Avoid direct network calls inside Composable functions.
- Avoid creating Repository or Retrofit objects inside Composable functions.

Example screen pattern:

```kotlin
@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    // Draw login screen UI only.
}
```

## Korean Explanation

Compose 화면은 상태를 직접 만들기보다 ViewModel에서 받은 상태를 표시한다.

사용자가 버튼을 누르면 직접 서버 통신을 하지 않고,  
`onLoginClick()` 같은 콜백으로 ViewModel에게 전달한다.

---

# ViewModel State Rules

- Use `StateFlow` for screen UI state.
- Expose state as read-only using `asStateFlow()`.
- Keep mutable state private inside ViewModel.
- ViewModel should handle user events and update state.
- ViewModel should call Repository, not Retrofit directly.

Example:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    // Updates the email value inside the current UI state.
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    // Updates the password value inside the current UI state.
    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    // Requests login through Repository and updates UI state based on the result.
    fun login() {
        // Call Repository here.
    }
}
```

## Korean Explanation

ViewModel에서는 `MutableStateFlow`를 private으로 두고,  
외부에는 `asStateFlow()`로 읽기 전용 상태만 공개한다.

이렇게 하면 UI가 상태를 직접 바꾸지 못하고,  
ViewModel을 통해서만 상태가 변경된다.

---

# Retrofit Rules

- Define API endpoints in `*ApiService.kt`.
- Create Retrofit instance in `RetrofitClient.kt` or `RetrofitInstance.kt`.
- Do not create Retrofit directly inside ViewModel.
- Do not create Retrofit directly inside Composable functions.
- Keep API service creation separated from Retrofit instance creation.
- Use DTOs for request and response bodies.
- Use mapping when DTOs should not be exposed directly to the app layer.

Preferred pattern:

```text
RetrofitClient creates Retrofit instance.
ApiService defines server endpoints.
Repository receives ApiService and calls API functions.
ViewModel calls Repository.
UI calls ViewModel events.
```

## Korean Explanation

Retrofit은 다음 흐름으로 사용한다.

```text
UI
↓
ViewModel
↓
Repository
↓
ApiService
↓
Retrofit
↓
Server
```

UI나 ViewModel에서 Retrofit을 직접 호출하지 않는다.

---

# DTO and Domain Model Rules

- DTOs should match the server response/request structure.
- Domain models should represent data used inside the app.
- Do not force the UI to depend on server DTOs when the app needs a cleaner model.
- Use mapper functions to convert DTOs to domain models.

Preferred flow:

```text
Server
↓
DTO
↓
Mapper
↓
Domain Model
↓
Repository Result
↓
ViewModel UI State
↓
Compose UI
```

## Korean Explanation

DTO는 서버와 통신하기 위한 데이터이고,  
Domain Model은 앱 내부에서 사용하기 위한 데이터이다.

서버 응답 구조가 앱 화면에 바로 맞지 않을 수 있으므로,  
Mapper를 통해 앱에서 쓰기 좋은 형태로 변환한다.

---

# Code Comment Rule

When writing functions or methods, add comments that explain the purpose of the method.

Comments should explain why the method exists and what it does.

Example:

```kotlin
// Updates the email value in LoginUiState when the user types in the email text field.
fun onEmailChange(email: String) {
    _uiState.value = _uiState.value.copy(email = email)
}

// Sends login request through AuthRepository and updates the UI state based on success or failure.
fun login() {
    // Login logic here.
}
```

## Korean Explanation

메소드나 함수에는 가능한 주석으로 역할을 설명한다.

단순히 코드를 그대로 읽는 주석이 아니라,  
이 메소드가 왜 있고 어떤 책임을 가지는지 설명한다.

좋은 주석 예시:

```kotlin
// 로그인 버튼을 눌렀을 때 Repository를 통해 로그인 요청을 보내고 UI 상태를 갱신한다.
fun login() {
    // Login logic here.
}
```

---

# Code Modification Rule

When modifying code:

- Explain what code was created.
- Explain what code was deleted.
- Explain what code was changed.
- Keep the explanation short and clear.
- After code changes, write a short summary in about 3 lines.

Example:

```text
Created LoginViewModel.kt to manage login UI state.
Created AuthRepository.kt to separate login data handling from the ViewModel.
Removed direct Retrofit calls from LoginScreen.kt.
```

## Korean Explanation

코드를 수정한 뒤에는 다음 내용을 간단히 설명한다.

- 어떤 파일을 만들었는지
- 어떤 코드를 삭제했는지
- 어떤 코드를 변경했는지

설명은 길게 쓰지 말고 3줄 정도로 정리한다.

---

# Important Final Rule

Do not create files that do everything.

Do not put UI, state management, server communication, DTOs, and data conversion into one file.

Always separate code by responsibility.

A file should be easy to understand, easy to modify, and easy to replace.

When in doubt, choose clearer responsibility separation instead of putting more logic into an existing file.

## Korean Explanation

하나의 파일이 모든 일을 하게 만들지 않는다.

파일 하나가 너무 많은 책임을 가지면 다음 문제가 생긴다.

- 코드를 읽기 어려워진다.
- 수정하기 어려워진다.
- 에러 원인을 찾기 어려워진다.
- 다른 기능에 영향을 줄 가능성이 커진다.

따라서 역할이 다르면 파일을 분리한다.

최종 목표는 다음과 같다.

> 각 파일이 하나의 명확한 책임만 가지는 구조를 만든다.