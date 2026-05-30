package com.example.it_da.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.it_da.ui.screen.home.HomeRoute
import com.example.it_da.ui.screen.login.LoginRoute
import com.example.it_da.ui.screen.profile.NotificationSettingsRoute
import com.example.it_da.ui.screen.profile.PersonalInfoRoute
import com.example.it_da.ui.screen.profile.ProfileRoute
import com.example.it_da.ui.screen.profile.ProjectStatusRoute
import com.example.it_da.ui.screen.profile.SelfIntroductionRoute
import com.example.it_da.ui.screen.profile.VersionInfoRoute
import com.example.it_da.ui.screen.signup.route.SignUpAccountRoute
import com.example.it_da.ui.screen.signup.route.SignUpAdditionalInfoRoute

// Owns the app navigation graph and connects screen-level navigation events.
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.Login.path
    ) {
        composable(AppRoute.Login.path) {
            LoginRoute(
                onSignUpClick = {
                    navController.navigate(AppRoute.SignUpAccount.path)
                },
                onLoginSuccess = {
                    navController.navigate(AppRoute.Home.path)
                },
                onSocialSignUpSuccess = {
                    navController.navigate(AppRoute.SignUpAdditionalInfo.path)
                }
            )
        }

        composable(AppRoute.SignUpAccount.path) {
            SignUpAccountRoute(
                onNextClick = {
                    navController.navigate(AppRoute.SignUpAdditionalInfo.path)
                }
            )
        }

        composable(AppRoute.SignUpAdditionalInfo.path) {
            SignUpAdditionalInfoRoute(
                onNextClick = {
                    navController.navigate(AppRoute.Home.path)
                }
            )
        }

        composable(AppRoute.Home.path) {
            HomeRoute(
                onProfileTabClick = {
                    navController.navigate(AppRoute.Profile.path)
                }
            )
        }

        composable(AppRoute.Profile.path) {
            ProfileRoute(
                onHomeTabClick = {
                    navController.navigate(AppRoute.Home.path)
                },
                onNotificationSettingsClick = {
                    navController.navigate(AppRoute.NotificationSettings.path)
                },
                onProjectStatusClick = {
                    navController.navigate(AppRoute.ProjectStatus.path)
                },
                onSelfIntroductionClick = {
                    navController.navigate(AppRoute.SelfIntroduction.path)
                },
                onPersonalInfoClick = {
                    navController.navigate(AppRoute.PersonalInfo.path)
                }
            )
        }

        composable(AppRoute.PersonalInfo.path) {
            PersonalInfoRoute(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppRoute.SelfIntroduction.path) {
            SelfIntroductionRoute(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(AppRoute.ProjectStatus.path) {
            ProjectStatusRoute(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoute.NotificationSettings.path) {
            NotificationSettingsRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onVersionInfoClick = {
                    navController.navigate(AppRoute.VersionInfo.path)
                },
                onSignOutClick = {
                    navController.navigate(AppRoute.Login.path) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.VersionInfo.path) {
            VersionInfoRoute(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
