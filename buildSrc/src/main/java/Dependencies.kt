/*
 * Copyright 2021 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Versions {
    const val activityKtx = "1.3.0-beta02"
    const val appCompat = "1.3.0"
    const val androidGradlePlugin = "7.1.0-alpha03"
    const val compose = "1.0.0-rc01"
    const val composeDesktop = "0.4.0"
    const val daggerHilt = "2.33-beta"
    const val dataStorePreferences = "1.0.0-beta02"
    const val espressoCore = "3.3.0"
    const val exposedSql = "0.31.1"
    const val extJunit = "1.1.2"
    const val junit = "4.12"
    const val junitKtx = "1.1.2"
    const val koin = "3.1.0"
    const val kotlin = "1.5.10"
    const val ktLint = "0.41.0"
    const val ktor = "1.6.1"
    const val ktxVersion = "1.6.0"
    const val lifecycle = "2.3.1"
    const val materialComponents = "1.3.0"
    const val navigationCompose = "2.4.0-alpha03"
    const val robolectric = "4.3.1"
    const val sqlDelight = "1.5.0"
    const val testCoreKtx = "1.3.0"
    const val timber = "4.7.1"
}

object Android {
    const val compileSdk = 30
    const val minSdk = 24
    const val targetSdk = 30
}

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"

    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

    const val ktLint = "com.pinterest:ktlint:${Versions.ktLint}"

    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val junit = "junit:junit:${Versions.junit}"

    const val h2Database = "com.h2database:h2:1.4.200"

    const val hikariCP = "com.zaxxer:HikariCP:4.0.3"

    const val logback = "ch.qos.logback:logback-classic:1.2.3"

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktxVersion}"
        const val junitKtx = "androidx.test.ext:junit-ktx:${Versions.junitKtx}"
        const val testCoreKtx = "androidx.test:core-ktx:${Versions.testCoreKtx}"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:${Versions.activityKtx}"
        }

        object Compose {
            const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        }

        object DataStore {
            const val preferences =
                "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"
        }

        object Lifecycle {
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        object Navigation {
            const val compose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
        }

        object Test {
            const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
            const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        }
    }

    object Google {

        object Dagger {
            const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
            const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
            const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
            const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.daggerHilt}"
        }

        object Material {
            const val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
        }
    }

    object Koin {
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val ktor = "io.insert-koin:koin-ktor:${Versions.koin}"
        const val logger = "io.insert-koin:koin-logger-slf4j:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
    }

    object Ktor {
        const val auth = "io.ktor:ktor-auth:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val clientApache = "io.ktor:ktor-client-apache:${Versions.ktor}"
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientCoreJvm = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
        const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val gson = "io.ktor:ktor-gson:${Versions.ktor}"
        const val locations = "io.ktor:ktor-locations:${Versions.ktor}"
        const val serialization = "io.ktor:ktor-serialization:${Versions.ktor}"
        const val serverCore = "io.ktor:ktor-server-core:${Versions.ktor}"
        const val serverNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val serverTests = "io.ktor:ktor-server-tests:${Versions.ktor}"
    }

    object SqlDelight {
        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
        const val jvmDriver = "com.squareup.sqldelight:sqlite-driver:${Versions.sqlDelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
        const val coroutines = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    }

    object Exposed {
        const val core = "org.jetbrains.exposed:exposed-core:${Versions.exposedSql}"
        const val jdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposedSql}"
    }
}
