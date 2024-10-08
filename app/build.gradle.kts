plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.detektGradlePlugin)
}

detekt {
    config.setFrom(file("../config/detekt/detekt.yml"))
    buildUponDefaultConfig = false
    // if true, enable all the default detekt configurations, so you can declare just the ones that want to update
}

android {
    namespace = "com.vitorota.customdetektrules"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vitorota.customdetektrules"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    detektPlugins(project(":detektrules"))
}