package com.example.it_da.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.it_da.ui.screen.home.HomeRoute
import com.example.it_da.ui.screen.login.LoginLaunchRoute
import com.example.it_da.ui.screen.login.LoginRoute
import com.example.it_da.ui.screen.notification.route.NotificationRoute
import com.example.it_da.ui.screen.projectcreate.route.ProjectCreateRoute
import com.example.it_da.ui.screen.signup.route.SignUpAccountRoute
import com.example.it_da.ui.screen.signup.route.SignUpAdditionalInfoRoute

// Owns the app navigation graph and connects screen-level navigation events.
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navigateHome: () -> Unit = {
        navController.navigate(HomeDestination) {
            popUpTo<HomeDestination> {
                inclusive = false
            }
            launchSingleTop = true
        }
    }
    val navigateHomeFromLaunch: () -> Unit = {
        navController.navigate(HomeDestination) {
            popUpTo<LoginLaunchDestination> {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateHomeFromAuth: () -> Unit = {
        navController.navigate(HomeDestination) {
            popUpTo<LoginDestination> {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    val navigateProjectCreate: () -> Unit = {
        navController.navigate(ProjectCreateDestination) {
            launchSingleTop = true
        }
    }
    val navigateNotification: () -> Unit = {
        navController.navigate(NotificationDestination) {
            launchSingleTop = true
        }
    }
    NavHost(
        navController = navController,
        startDestination = LoginLaunchDestination
    ) {
        composable<LoginLaunchDestination> {
            LoginLaunchRoute(
                onNavigateToLogin = {
                    navController.navigate(LoginDestination) {
                        popUpTo<LoginLaunchDestination> {
                            inclusive = true
                        }
                    }
                },
                onNavigateToHome = navigateHomeFromLaunch
            )
        }

        composable<LoginDestination> {
            LoginRoute(
                onSignUpClick = {
                    navController.navigate(SignUpAccountDestination)
                },
                onLoginSuccess = navigateHomeFromAuth,
                onSocialSignUpSuccess = {
                    navController.navigate(SignUpAdditionalInfoDestination)
                }
            )
        }

        composable<SignUpAccountDestination> {
            SignUpAccountRoute(
                onNextClick = {
                    navController.navigate(SignUpAdditionalInfoDestination)
                }
            )
        }

        composable<SignUpAdditionalInfoDestination> {
            SignUpAdditionalInfoRoute(
                onSignUpSuccess = navigateHomeFromAuth
            )
        }

        composable<HomeDestination> {
            HomeRoute(
                onCreateProjectClick = navigateProjectCreate,
                onNotificationClick = navigateNotification
            )
        }

        composable<ProjectCreateDestination> {
            ProjectCreateRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onSubmitSuccess = navigateHome,
                onHomeTabClick = navigateHome,
                onExploreTabClick = {},
                onCreateProjectClick = navigateProjectCreate,
                onNotificationTabClick = navigateNotification,
                onProfileTabClick = {}
            )
        }

        composable<NotificationDestination> {
            NotificationRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onHomeTabClick = navigateHome,
                onExploreTabClick = {},
                onCreateProjectClick = navigateProjectCreate,
                onNotificationTabClick = navigateNotification,
                onProfileTabClick = {}
            )
        }
    }
}
