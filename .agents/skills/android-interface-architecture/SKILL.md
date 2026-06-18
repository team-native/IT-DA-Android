---
name: android-interface-architecture
description: Defines when and how to use interfaces in Android ViewModel, Repository, DataSource, Retrofit, and Hilt-based architecture. This skill follows Android recommended architecture principles and explains each rule in English with Korean guidance.
---

# Android Interface Architecture Skill

## Purpose

Use interfaces in Android code when they create a clear boundary between layers, make dependencies easier to replace, or improve testability.

> 한글 설명:  
> 인터페이스는 무조건 많이 만드는 것이 목적이 아니다.  
> ViewModel, Repository, API, DataSource 사이의 의존성을 느슨하게 만들고, 테스트용 Fake 구현체나 다른 구현체로 바꾸기 쉽게 만드는 것이 목적이다.

---

## Official Android Architecture Basis

This skill follows Android's recommended architecture ideas:

- Separate the UI layer and data layer.
- Keep business logic and data handling outside Composable UI code.
- Let ViewModels expose UI state and handle user actions.
- Let ViewModels depend on repositories or use cases instead of directly depending on data sources.
- Let repositories act as the entry point to the data layer.
- Use suspend functions for one-shot operations.
- Use Flow or StateFlow for observable data streams.
- Use dependency injection when creating classes with dependencies.
- Use Hilt `@Binds` when an interface needs a concrete implementation.

> 한글 설명:  
> Android 공식 아키텍처의 핵심은 “화면 코드가 서버, DB, DataStore 같은 데이터 소스를 직접 알면 안 된다”는 것이다.  
> 화면은 ViewModel을 보고, ViewModel은 Repository 또는 UseCase를 보고, Repository가 실제 데이터 소스를 다룬다.

---

## Core Rule

Prefer depending on abstractions at layer boundaries.

Good:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel()
```

Avoid depending directly on concrete implementation classes when the dependency belongs to another layer.

Bad:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepositoryImpl
) : ViewModel()
```

> 한글 설명:  
> ViewModel에서 `AuthRepositoryImpl`을 직접 받으면 ViewModel이 특정 구현체에 묶인다.  
> 반대로 `AuthRepository` 인터페이스를 받으면 실제 서버 Repository, 테스트용 Fake Repository, 로컬 Repository 등으로 바꿔 끼우기 쉽다.

---

## When To Use Interfaces

Use an interface when:

1. A ViewModel depends on a Repository.
2. A UseCase depends on a Repository.
3. A Repository depends on a DataSource that may change.
4. There can be multiple implementations.
5. You need a Fake implementation for testing.
6. Hilt or manual dependency injection needs to bind an abstraction to an implementation.
7. Retrofit API definitions are needed.

> 한글 설명:  
> 인터페이스는 “나중에 갈아끼울 가능성이 있는 곳”에 쓰는 것이 좋다.  
> 예를 들어 실제 서버 통신 Repository와 테스트용 Fake Repository는 같은 기능을 하지만 내부 구현이 다르다.

---

## When Not To Use Interfaces

Do not create interfaces for everything.

Avoid interfaces when:

1. The class is a simple DTO.
2. The class is a UI state data class.
3. The class is a mapper with no expected replacement.
4. The class is a small utility with one stable implementation.
5. The project is a very small practice app and testability is not important yet.

Good:

```kotlin
data class LoginRequestDto(
    val id: String,
    val password: String
)
```

Bad:

```kotlin
interface LoginRequestDtoInterface
```

> 한글 설명:  
> DTO, UI 상태, 단순 데이터 클래스는 보통 인터페이스로 만들 필요가 없다.  
> 인터페이스를 너무 많이 만들면 파일만 많아지고 구조가 복잡해진다.

---

## Recommended Package Structure

```text
com.example.app
├── data
│   ├── model
│   │   ├── LoginRequestDto.kt
│   │   └── LoginResponseDto.kt
│   ├── remote
│   │   ├── AuthApi.kt
│   │   └── RetrofitClient.kt
│   ├── mapper
│   │   └── AuthMapper.kt
│   └── repository
│       ├── AuthRepository.kt
│       └── AuthRepositoryImpl.kt
├── domain
│   ├── model
│   │   └── User.kt
│   └── usecase
│       └── LoginUseCase.kt
├── ui
│   └── login
│       ├── LoginScreen.kt
│       ├── LoginUiState.kt
│       └── LoginViewModel.kt
└── di
    └── RepositoryModule.kt
```

> 한글 설명:  
> `AuthRepository.kt`는 인터페이스이고, `AuthRepositoryImpl.kt`는 실제 구현 클래스다.  
> ViewModel은 `AuthRepositoryImpl`이 아니라 `AuthRepository`를 받는 것이 좋다.

---

## Repository Interface Rule

Repository interfaces should describe what the app needs, not how the server works.

Good:

```kotlin
interface AuthRepository {

    /**
     * Attempts to log in with the given id and password.
     *
     * 한글 설명:
     * 사용자가 입력한 아이디와 비밀번호로 로그인을 시도하는 메소드이다.
     * ViewModel은 이 메소드를 호출할 뿐, 내부에서 Retrofit을 쓰는지 DataStore를 쓰는지 알 필요가 없다.
     */
    suspend fun login(id: String, password: String): Result<User>
}
```

Bad:

```kotlin
interface AuthRepository {

    /**
     * Sends the raw DTO to the auth login endpoint.
     *
     * 한글 설명:
     * Repository 인터페이스가 서버 DTO와 endpoint 구조를 너무 직접적으로 드러내고 있다.
     * ViewModel이 서버 구조에 가까워질 수 있으므로 피한다.
     */
    suspend fun postAuthLogin(request: LoginRequestDto): LoginResponseDto
}
```

> 한글 설명:  
> Repository 인터페이스는 앱 입장에서 필요한 기능을 표현해야 한다.  
> `login(id, password)`처럼 앱 기능 중심으로 쓰고, 서버 DTO 처리는 구현체 안에서 처리하는 것이 좋다.

---

## Repository Implementation Rule

Repository implementation classes should handle concrete data operations.

```kotlin
class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {

    /**
     * Sends a login request to the remote API and maps the server response to a domain model.
     *
     * 한글 설명:
     * 실제 Retrofit API를 호출해서 로그인 요청을 보내고,
     * 서버 응답 DTO를 앱 내부에서 사용할 domain model로 변환하는 메소드이다.
     */
    override suspend fun login(id: String, password: String): Result<User> {
        return try {
            val request = LoginRequestDto(
                id = id,
                password = password
            )

            val response = authApi.login(request)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    Result.success(body.toDomain())
                } else {
                    Result.failure(IllegalStateException("Response body is null."))
                }
            } else {
                Result.failure(IllegalStateException("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

> 한글 설명:  
> `AuthRepositoryImpl`은 실제 서버 통신을 안다.  
> 하지만 ViewModel은 이 구현체 내부를 몰라도 된다.  
> ViewModel은 `AuthRepository.login()`만 호출하면 된다.

---

## Retrofit API Interface Rule

Retrofit API services should be interfaces.

```kotlin
interface AuthApi {

    /**
     * Sends the login request body to the server and returns the raw server response.
     *
     * 한글 설명:
     * 로그인 요청 DTO를 서버에 보내고 서버 응답 DTO를 받는 Retrofit API 메소드이다.
     * Retrofit이 이 인터페이스를 보고 실제 통신 구현체를 자동으로 생성한다.
     */
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequestDto
    ): Response<LoginResponseDto>
}
```

> 한글 설명:  
> Retrofit에서는 `interface AuthApi`를 쓰는 것이 일반적이다.  
> 개발자가 직접 `AuthApiImpl`을 만들지 않아도 Retrofit이 `retrofit.create(AuthApi::class.java)`로 실제 구현 객체를 만들어준다.

---

## ViewModel Rule

ViewModels should depend on repository interfaces or use cases, not concrete data source classes.

Good:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    /**
     * Handles the login button click event and updates the UI state based on the login result.
     *
     * 한글 설명:
     * 로그인 버튼을 눌렀을 때 실행되는 메소드이다.
     * ViewModel이 코루틴을 만들고 Repository에 로그인 요청을 맡긴다.
     */
    fun login(id: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val result = authRepository.login(id, password)

            _uiState.value = result.fold(
                onSuccess = { user ->
                    _uiState.value.copy(
                        isLoading = false,
                        userName = user.name,
                        errorMessage = null
                    )
                },
                onFailure = { throwable ->
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message
                    )
                }
            )
        }
    }
}
```

Bad:

```kotlin
class LoginViewModel(
    private val authApi: AuthApi
) : ViewModel()
```

> 한글 설명:  
> ViewModel이 `AuthApi`를 직접 알면 서버 통신 구조에 너무 가까워진다.  
> ViewModel은 화면 상태와 이벤트 처리를 담당하고, 실제 데이터 처리는 Repository에 맡기는 것이 좋다.

---

## UseCase Rule

Use cases can be classes. They do not always need interfaces.

```kotlin
class LoginUseCase(
    private val authRepository: AuthRepository
) {

    /**
     * Executes the login business rule.
     *
     * 한글 설명:
     * 로그인에 필요한 비즈니스 규칙을 실행하는 메소드이다.
     * 예를 들어 입력값 검사 후 Repository에 로그인을 요청할 수 있다.
     */
    suspend operator fun invoke(id: String, password: String): Result<User> {
        if (id.isBlank()) {
            return Result.failure(IllegalArgumentException("Id cannot be blank."))
        }

        if (password.isBlank()) {
            return Result.failure(IllegalArgumentException("Password cannot be blank."))
        }

        return authRepository.login(id, password)
    }
}
```

> 한글 설명:  
> UseCase는 보통 하나의 기능을 나타내는 클래스다.  
> 여러 구현체가 필요하거나 테스트에서 교체할 이유가 명확할 때만 UseCase 인터페이스를 따로 만든다.

---

## DTO And Domain Model Rule

DTOs represent server request and response formats. Domain models represent app-side business data.

```kotlin
data class LoginResponseDto(
    val accessToken: String,
    val refreshToken: String,
    val userName: String
)

data class User(
    val name: String,
    val accessToken: String,
    val refreshToken: String
)
```

> 한글 설명:  
> DTO는 서버와 주고받는 데이터 형식이다.  
> Domain model은 앱 내부에서 사용하는 데이터 형식이다.  
> 서버 응답 구조와 앱 내부 모델을 분리하면 서버 변경에 더 강한 구조를 만들 수 있다.

---

## Mapper Rule

DTO should be mapped to domain models before being used by ViewModel or UI.

```kotlin
/**
 * Converts LoginResponseDto from the server into the User domain model used inside the app.
 *
 * 한글 설명:
 * 서버에서 받은 LoginResponseDto를 앱 내부에서 사용할 User 도메인 모델로 변환하는 메소드이다.
 * ViewModel과 UI는 서버 DTO에 직접 의존하지 않게 된다.
 */
fun LoginResponseDto.toDomain(): User {
    return User(
        name = userName,
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}
```

> 한글 설명:  
> 서버 DTO를 ViewModel이나 UI에서 바로 쓰면 서버 응답 구조가 바뀔 때 화면 코드까지 흔들릴 수 있다.  
> Repository 구현체 안에서 DTO를 domain model로 바꾸고, ViewModel은 domain model을 받는 구조가 좋다.

---

## Fake Implementation Rule

Create fake implementations for testing or preview logic.

```kotlin
class FakeAuthRepository : AuthRepository {

    /**
     * Returns a fake login success result without calling a real server.
     *
     * 한글 설명:
     * 실제 서버 통신 없이 테스트용 로그인 성공 결과를 반환하는 메소드이다.
     * ViewModel 테스트나 UI Preview 상황에서 사용할 수 있다.
     */
    override suspend fun login(id: String, password: String): Result<User> {
        return Result.success(
            User(
                name = "Test User",
                accessToken = "fake-access-token",
                refreshToken = "fake-refresh-token"
            )
        )
    }
}
```

> 한글 설명:  
> ViewModel이 `AuthRepository` 인터페이스에 의존하면 `AuthRepositoryImpl` 대신 `FakeAuthRepository`를 넣을 수 있다.  
> 그래서 테스트가 쉬워진다.

---

## Hilt Binding Rule

When using Hilt, bind an interface to its implementation with `@Binds`.

```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds AuthRepositoryImpl as the implementation of AuthRepository.
     *
     * 한글 설명:
     * Hilt에게 AuthRepository가 필요할 때 AuthRepositoryImpl을 넣어주라고 알려주는 메소드이다.
     */
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
```

Implementation class:

```kotlin
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {

    /**
     * Sends a login request through AuthApi and returns the mapped login result.
     *
     * 한글 설명:
     * AuthApi를 통해 서버에 로그인 요청을 보내고 결과를 앱 내부 모델로 변환해서 반환하는 메소드이다.
     */
    override suspend fun login(id: String, password: String): Result<User> {
        return try {
            val response = authApi.login(LoginRequestDto(id, password))

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(IllegalStateException("Login failed."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

> 한글 설명:  
> 인터페이스는 생성자가 없기 때문에 Hilt가 그냥 만들 수 없다.  
> 그래서 `@Binds`를 사용해서 “이 인터페이스에는 이 구현체를 넣어라”라고 알려줘야 한다.

---

## Manual Dependency Injection Rule

If Hilt is not used, create dependencies manually in a container or factory.

```kotlin
class AppContainer {

    private val retrofit = RetrofitClient.retrofit

    private val authApi: AuthApi = retrofit.create(AuthApi::class.java)

    val authRepository: AuthRepository = AuthRepositoryImpl(authApi)

    /**
     * Creates LoginViewModel with the required repository dependency.
     *
     * 한글 설명:
     * LoginViewModel이 필요로 하는 AuthRepository를 넣어서 ViewModel을 생성하는 메소드이다.
     */
    fun createLoginViewModel(): LoginViewModel {
        return LoginViewModel(authRepository)
    }
}
```

> 한글 설명:  
> Hilt를 아직 배우지 않았다면 직접 객체를 만들어서 넣어도 된다.  
> 중요한 것은 ViewModel이 `AuthRepositoryImpl`이 아니라 `AuthRepository` 타입을 받도록 만드는 것이다.

---

## ViewModel Factory Rule

If a ViewModel needs constructor parameters and Hilt is not used, create a ViewModelFactory.

```kotlin
class LoginViewModelFactory(
    private val authRepository: AuthRepository
) : ViewModelProvider.Factory {

    /**
     * Creates a ViewModel instance with the required AuthRepository dependency.
     *
     * 한글 설명:
     * LoginViewModel을 만들 때 필요한 AuthRepository를 생성자로 넣어주는 메소드이다.
     * Android가 ViewModel을 만들 때 이 Factory를 통해 생성하게 된다.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(authRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class.")
    }
}
```

> 한글 설명:  
> ViewModel 생성자에 Repository가 필요하면 기본 방식으로는 바로 만들기 어렵다.  
> Hilt를 쓰지 않는다면 `ViewModelProvider.Factory`를 만들어서 의존성을 넣어준다.

---

## Naming Rules

Use these names consistently:

```text
Interface:
AuthRepository
ChatRepository
UserRepository

Implementation:
AuthRepositoryImpl
ChatRepositoryImpl
UserRepositoryImpl

Fake implementation:
FakeAuthRepository
FakeChatRepository
FakeUserRepository

Retrofit API:
AuthApi
ChatApi
UserApi
```

> 한글 설명:  
> 인터페이스 이름에는 보통 `Interface`를 붙이지 않는다.  
> `AuthRepositoryInterface`보다 `AuthRepository`가 자연스럽다.  
> 실제 구현체에는 `Impl`, 테스트용에는 `Fake`를 붙이면 구분하기 쉽다.

---

## Good Example

```kotlin
interface ChatRepository {

    /**
     * Sends a chat message and returns the server-generated chat response.
     *
     * 한글 설명:
     * 사용자의 채팅 메시지를 서버에 보내고 서버가 생성한 응답을 반환하는 메소드이다.
     */
    suspend fun sendMessage(message: String): Result<ChatMessage>
}
```

```kotlin
class ChatRepositoryImpl(
    private val chatApi: ChatApi
) : ChatRepository {

    /**
     * Sends the message to the remote chat API and maps the response DTO to a domain model.
     *
     * 한글 설명:
     * Retrofit API를 통해 메시지를 서버에 보내고,
     * 서버 응답 DTO를 앱 내부 ChatMessage 모델로 변환하는 메소드이다.
     */
    override suspend fun sendMessage(message: String): Result<ChatMessage> {
        return try {
            val response = chatApi.sendMessage(ChatRequestDto(message))

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(IllegalStateException("Failed to send message."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

```kotlin
class ChatViewModel(
    private val chatRepository: ChatRepository
) : ViewModel() {

    /**
     * Handles user message input and requests a chat response from the repository.
     *
     * 한글 설명:
     * 사용자가 입력한 메시지를 Repository로 보내고,
     * 결과에 따라 화면 상태를 변경하는 메소드이다.
     */
    fun sendMessage(message: String) {
        viewModelScope.launch {
            val result = chatRepository.sendMessage(message)

            result.onSuccess { chatMessage ->
                // Update UI state with the new chat message.
            }.onFailure { throwable ->
                // Update UI state with an error message.
            }
        }
    }
}
```

> 한글 설명:  
> 이 구조에서 ViewModel은 실제 Retrofit을 모른다.  
> ViewModel은 `ChatRepository`라는 기능만 알고, 실제 서버 통신은 `ChatRepositoryImpl`이 처리한다.

---

## Bad Example

```kotlin
class ChatViewModel(
    private val chatApi: ChatApi
) : ViewModel() {

    /**
     * Sends a message directly through Retrofit from the ViewModel.
     *
     * 한글 설명:
     * ViewModel이 Retrofit API를 직접 호출하고 있다.
     * 이러면 ViewModel이 서버 통신 구조에 직접 의존하게 되어 좋지 않다.
     */
    fun sendMessage(message: String) {
        viewModelScope.launch {
            val response = chatApi.sendMessage(ChatRequestDto(message))
        }
    }
}
```

> 한글 설명:  
> ViewModel이 `ChatApi`를 직접 쓰면 Repository 계층이 사라진다.  
> 작은 연습 코드에서는 가능하지만, 실무형 구조에서는 Repository를 두는 것이 좋다.

---

## Decision Checklist

Before creating an interface, ask:

1. Will this dependency be replaced later?
2. Do I need a fake implementation for tests?
3. Is this dependency crossing a layer boundary?
4. Is this a Retrofit API definition?
5. Will Hilt or manual dependency injection provide this dependency?
6. Would this interface make the code clearer?

If most answers are no, do not create an interface.

> 한글 설명:  
> 인터페이스는 “나중에 바꿔 끼울 필요가 있는가?”가 핵심 기준이다.  
> 필요 없는데 만들면 구조만 복잡해진다.

---

## Final Rule Summary

Use this:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel()
```

Instead of this:

```kotlin
class LoginViewModel(
    private val authRepository: AuthRepositoryImpl
) : ViewModel()
```

Because:

- ViewModel becomes easier to test.
- Repository implementation can be replaced.
- UI layer does not know concrete data layer details.
- The code follows layered architecture more cleanly.

> 한글 설명:  
> ViewModel에서는 구현 클래스보다 인터페이스를 받는 것이 좋다.  
> 단, 모든 클래스를 인터페이스로 만들 필요는 없다.  
> Repository, DataSource, Retrofit API처럼 계층 사이의 경계에 있는 것부터 인터페이스를 적용하면 된다.