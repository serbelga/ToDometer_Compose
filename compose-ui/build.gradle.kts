import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.4.0-build188"
    id("com.android.library")
}

group = "com.sergiobelda.todometer.compose"
version = "1.0"

repositories {
    google()
}

// TODO Remove this block when https://youtrack.jetbrains.com/issue/KT-43944 resolved
android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                implementation(project(":common"))
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.corektx)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.junit)
            }
        }
        val desktopMain by getting
        val desktopTest by getting
    }
}

android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 30
    }
}
