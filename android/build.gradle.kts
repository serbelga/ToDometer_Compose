plugins {
    id("org.jetbrains.compose") version "0.4.0-build188"
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

group = "com.sergiobelda.todometer"
version = "1.0"

repositories {
    google()
}

dependencies {
    implementation(project(":common"))
    implementation(project(":compose-ui"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.material.components)

    implementation(libs.timber)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.sergiobelda.todometer.android"
        minSdk = 24
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
