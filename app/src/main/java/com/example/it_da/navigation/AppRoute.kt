package com.example.it_da.navigation

// Defines app routes in one place so screens do not depend on raw route strings.
sealed class AppRoute(val path: String) {
    data object Login : AppRoute("login")
    data object SignUpAccount : AppRoute("sign_up_account")
    data object SignUpAdditionalInfo : AppRoute("sign_up_additional_info")
    data object Home : AppRoute("home")
    data object Profile : AppRoute("profile")
    data object PersonalInfo : AppRoute("personal_info")
    data object SelfIntroduction : AppRoute("self_introduction")
    data object ProjectStatus : AppRoute("project_status")
    data object NotificationSettings : AppRoute("notification_settings")
    data object VersionInfo : AppRoute("version_info")
}
