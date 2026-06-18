---
name: android-compose-scaffold
description: Use when creating or modifying top-level Jetpack Compose screens. Enforce Material 3 Scaffold as the default screen root, slot-based app bars and actions, and correct innerPadding and WindowInsets handling for edge-to-edge layouts.
---

# Android Compose Scaffold Skill

## Purpose

Use `androidx.compose.material3.Scaffold` as the default root of each top-level Compose screen.

Place screen chrome in Scaffold slots and consume the provided `innerPadding` exactly once.

Korean explanation:
top-level `*Screen`은 기본적으로 Material 3 `Scaffold`를 루트로 사용한다.

## Main Rule

- Use `Scaffold` for top-level screen layout.
- Put top app bars in `topBar`.
- Put bottom app bars or navigation bars in `bottomBar`.
- Put snackbars in `snackbarHost`.
- Put the primary floating action in `floatingActionButton`.
- Apply `innerPadding` to the content root with both `padding` and `consumeWindowInsets`.
- Do not add duplicate `statusBarsPadding()` or `navigationBarsPadding()` to Scaffold content.

```kotlin
Scaffold(
    topBar = { AppTopBar() },
    bottomBar = { AppBottomNavigationBar() }
) { innerPadding ->
    ScreenContent(
        modifier = Modifier
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)
    )
}
```

## Scrollable Content Rule

When using `verticalScroll`, apply the Scaffold padding to a child inside the scroll container, not to the scroll container itself.

```kotlin
Scaffold { innerPadding ->
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            // Screen content
        }
    }
}
```

For `LazyColumn`, use `contentPadding = innerPadding` and consume the insets on the list modifier.

```kotlin
LazyColumn(
    modifier = Modifier.consumeWindowInsets(innerPadding),
    contentPadding = innerPadding
) {
    // Items
}
```

## Custom Bar Rule

Material 3 app bars handle their own insets. Custom app bars do not.

When a custom top or bottom bar is placed in a Scaffold slot, handle the corresponding inset inside the custom bar:

- Apply `statusBarsPadding()` inside a custom top bar.
- Apply `navigationBarsPadding()` inside a custom bottom bar.
- Keep inset handling out of each screen to avoid duplicated padding.

## Exceptions

Do not add `Scaffold` to reusable components, dialogs, sheets, or intentionally fullscreen content unless the layout needs Scaffold slots.

## Official Documentation

- [Scaffold guide](https://developer.android.com/jetpack/compose/components/scaffold)
- [Material 3 inset handling](https://developer.android.com/develop/ui/compose/system/material-insets)
- [Material 3 Scaffold API](https://developer.android.com/reference/kotlin/androidx/compose/material3/Scaffold.composable)
