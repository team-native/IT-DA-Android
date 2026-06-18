package com.example.it_da.di

import com.example.it_da.data.local.AuthTokenLocalDataSource
import com.example.it_da.data.local.DefaultAuthTokenLocalDataSource
import com.example.it_da.data.repository.AuthSessionRepository
import com.example.it_da.data.repository.DefaultAuthSessionRepository
import com.example.it_da.data.repository.DefaultSocialAuthRepository
import com.example.it_da.data.repository.FakeHomeRepository
import com.example.it_da.data.repository.FakeNotificationRepository
import com.example.it_da.data.repository.FakeProjectCreateRepository
import com.example.it_da.data.repository.HomeRepository
import com.example.it_da.data.repository.NotificationRepository
import com.example.it_da.data.repository.ProjectCreateRepository
import com.example.it_da.data.repository.SocialAuthRepository
import com.example.it_da.data.store.DefaultNotificationStore
import com.example.it_da.data.store.DefaultProjectStore
import com.example.it_da.data.store.DefaultUserStore
import com.example.it_da.data.store.NotificationStore
import com.example.it_da.data.store.ProjectStore
import com.example.it_da.data.store.UserStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthTokenLocalDataSource(
        defaultAuthTokenLocalDataSource: DefaultAuthTokenLocalDataSource
    ): AuthTokenLocalDataSource

    @Binds
    @Singleton
    abstract fun bindAuthSessionRepository(
        defaultAuthSessionRepository: DefaultAuthSessionRepository
    ): AuthSessionRepository

    @Binds
    @Singleton
    abstract fun bindUserStore(defaultUserStore: DefaultUserStore): UserStore

    @Binds
    @Singleton
    abstract fun bindProjectStore(defaultProjectStore: DefaultProjectStore): ProjectStore

    @Binds
    @Singleton
    abstract fun bindNotificationStore(defaultNotificationStore: DefaultNotificationStore): NotificationStore

    @Binds
    @Singleton
    abstract fun bindHomeRepository(fakeHomeRepository: FakeHomeRepository): HomeRepository

    @Binds
    @Singleton
    abstract fun bindNotificationRepository(
        fakeNotificationRepository: FakeNotificationRepository
    ): NotificationRepository

    @Binds
    @Singleton
    abstract fun bindProjectCreateRepository(
        fakeProjectCreateRepository: FakeProjectCreateRepository
    ): ProjectCreateRepository

    @Binds
    @Singleton
    abstract fun bindSocialAuthRepository(
        defaultSocialAuthRepository: DefaultSocialAuthRepository
    ): SocialAuthRepository
}
