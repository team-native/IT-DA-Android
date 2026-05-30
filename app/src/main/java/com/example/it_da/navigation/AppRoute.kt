package com.example.it_da.navigation

import kotlinx.serialization.Serializable

// Defines type-safe app destinations so the navigation graph does not depend on raw route strings.
@Serializable
data object LoginLaunchDestination

@Serializable
data object LoginDestination

@Serializable
data object SignUpAccountDestination

@Serializable
data object SignUpAdditionalInfoDestination

@Serializable
data object HomeDestination
