package com.example.it_da.di

import com.example.it_da.BuildConfig
import com.example.it_da.data.auth.GoogleSocialAuthClient
import com.example.it_da.data.auth.KakaoSocialAuthClient
import com.example.it_da.data.auth.SocialAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialAuthModule {
    // Provides SDK-specific clients behind the shared SocialAuthClient boundary.
    @Provides
    @Singleton
    fun provideSocialAuthClients(): List<SocialAuthClient> {
        return listOf(
            GoogleSocialAuthClient(BuildConfig.GOOGLE_WEB_CLIENT_ID),
            KakaoSocialAuthClient(BuildConfig.KAKAO_NATIVE_APP_KEY)
        )
    }
}
