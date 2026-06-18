---
name: android-compose-navigation
description: Use when implementing Jetpack Compose Navigation. Enforce Kotlin Serialization based type-safe routes instead of raw route strings or sealed path holders, and keep NavHost logic separated from screen UI.
---

# Android Compose Navigation Skill

## Purpose

Use this skill when creating or modifying navigation in an Android Jetpack Compose project.

Navigation must use Jetpack Navigation Compose.

Do not use raw route strings directly inside `NavHost`, `composable()`, or `navigate()` calls.

Use Navigation Compose type-safe routes with Kotlin Serialization.

---

## Main Rule

Do not write routes like this:

```kotlin
navController.navigate("home")

composable("home") {
    HomeScreen()
}
```

Also do not use sealed path holder routes like this:

```kotlin
sealed class Route(val path: String) {
    data object Home : Route("home")
    data object Login : Route("login")
    data object Profile : Route("profile/{userId}")
}
```

Do not navigate with `.path`:

```kotlin
navController.navigate(Route.Home.path)

composable(Route.Home.path) {
    HomeScreen()
}
```

Use type-safe destination types instead:

```kotlin
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Login

@Serializable
data class Profile(
    val userId: String
)
```

Then use those destination types directly:

```kotlin
NavHost(
    navController = navController,
    startDestination = Home
) {
    composable<Home> {
        HomeScreen(
            onProfileClick = { userId ->
                navController.navigate(Profile(userId = userId))
            }
        )
    }

    composable<Profile> { backStackEntry ->
        val profile = backStackEntry.toRoute<Profile>()

        ProfileScreen(
            userId = profile.userId
        )
    }
}
```

---

## Recommended File Structure

```text
navigation/
  AppRoute.kt
  AppNavigation.kt
```

---

## AppRoute.kt Rule

`AppRoute.kt` must only define type-safe destination types.

Routes without arguments should be `@Serializable object`.

Routes with arguments should be `@Serializable data class`.

Do not store route paths as strings.

Do not define `sealed class Route(val path: String)`.

Do not expose `.path`, `.route`, or manually built route templates.

```kotlin
package com.example.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Login

@Serializable
object SignUp

@Serializable
data class ProjectDetail(
    val projectId: Long
)
```

If the project already has screen-level composables named `LoginRoute`, `HomeRoute`, or `ProjectRoute`, keep those files as state-connection composables.

In that case, the navigation destination types may still live in `AppRoute.kt`, but they should be type-safe serializable route types, not string path holders.

---

## AppNavigation.kt Rule

`AppNavigation.kt` must only define `NavHost` and screen connections.

```kotlin
package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.toRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.home.HomeScreen
import com.example.app.ui.login.LoginScreen
import com.example.app.ui.signup.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                onLoginClick = {
                    navController.navigate(Login)
                },
                onProjectClick = { projectId ->
                    navController.navigate(ProjectDetail(projectId = projectId))
                }
            )
        }

        composable<Login> {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate(SignUp)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<SignUp> {
            SignUpScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable<ProjectDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<ProjectDetail>()

            ProjectDetailScreen(
                projectId = route.projectId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
```

---

## Screen Rule

Screen composables should not directly create or own `NavController`.

Bad:

```kotlin
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
}
```

Good:

```kotlin
@Composable
fun HomeScreen(
    onLoginClick: () -> Unit
) {
    HomeContent(
        onLoginClick = onLoginClick
    )
}
```

The screen receives event functions from navigation.

---

## Event Flow Rule

Navigation event flow should be:

```text
Button click
-> onClick()
-> Screen event parameter
-> AppNavigation
-> navController.navigate(RouteType(...))
```

Do not make child UI components know route names directly.

---

## Argument Rule

Use route constructor parameters for navigation arguments.

Bad:

```kotlin
navController.navigate("project_detail/$projectId")
```

Good:

```kotlin
navController.navigate(ProjectDetail(projectId = projectId))
```

Read arguments with `toRoute<T>()` inside the matching `composable<T>()` destination.

```kotlin
composable<ProjectDetail> { backStackEntry ->
    val route = backStackEntry.toRoute<ProjectDetail>()

    ProjectDetailScreen(
        projectId = route.projectId
    )
}
```

For ViewModels that need route arguments, prefer `SavedStateHandle.toRoute<T>()` instead of manually reading string keys.

---

## Gradle Rule

Type-safe routes require Kotlin Serialization.

When adding type-safe routes to a module that is not already configured, add the Kotlin Serialization plugin and keep dependency versions aligned with the project convention.

Example:

```kotlin
plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
}
```

Do not introduce hard-coded versions if the project already uses a version catalog.

---

## Avoid

- Raw route strings inside screen files.
- `sealed class Route(val path: String)` for Navigation Compose destinations.
- `.path` or `.route` route string properties for normal app navigation.
- Manually building argument routes with string interpolation.
- Calling `rememberNavController()` inside every screen.
- Mixing `NavHost` and UI layout in the same file.
- Passing `NavController` deeply into small UI components.
- Duplicating route strings across files.
