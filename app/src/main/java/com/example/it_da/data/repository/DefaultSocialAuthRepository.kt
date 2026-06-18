package com.example.it_da.data.repository

import android.content.Context
import com.example.it_da.data.auth.SocialAuthClient
import com.example.it_da.domain.model.SocialAuthAccount
import com.example.it_da.domain.model.SocialAuthProvider
import javax.inject.Inject
import kotlin.jvm.JvmSuppressWildcards

class DefaultSocialAuthRepository @Inject constructor(
    private val socialAuthClients: List<@JvmSuppressWildcards SocialAuthClient>
) : SocialAuthRepository {
    // Delegates authentication to the SDK client that owns the requested provider.
    override suspend fun authenticate(
        context: Context,
        provider: SocialAuthProvider
    ): Result<SocialAuthAccount> {
        val socialAuthClient = socialAuthClients.firstOrNull { client ->
            client.provider == provider
        } ?: return Result.failure(
            IllegalArgumentException("지원하지 않는 소셜 회원가입 방식입니다.")
        )

        return socialAuthClient.authenticate(context)
    }
}
