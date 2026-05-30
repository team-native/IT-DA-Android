package com.example.it_da

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ItdaApplication : Application() {
    // Initializes Kakao SDK once when the app process starts if a native app key is configured.
    override fun onCreate() {
        super.onCreate()
        initializeKakaoSdk()
    }

    // Keeps Kakao initialization isolated from Activity and Compose screen code.
    private fun initializeKakaoSdk() {
        if (BuildConfig.KAKAO_NATIVE_APP_KEY.isNotBlank()) {
            KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        }
    }
}
