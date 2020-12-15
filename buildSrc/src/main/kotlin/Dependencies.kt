import Dependencies.VersionMap.*

object Dependencies {
    enum class VersionMap(val version: String) {
        // Language and script plugins
        KOTLIN("1.4.30-RC"),
        GRADLE("4.1.2"),

        // Arch
        COROUTINE("1.4.2-native-mt"),
        KOIN("3.0.0-alpha-4"),
        DAGGER("2.30.1"),
        HILT("2.30.1-alpha"),

        // Network
        KTOR("1.4.3"),
        KTOR_ANDROID("1.4.3"),
        KTOR_IOS("1.3.1"),

        // Storage
        SQL_DELIGHT("1.4.4"),

        // Serialization
        KOTLIN_SERIALIZATION("1.0.0-RC"),

        // Image loading
        COIL("1.1.0"),

        // Design
        MATERIAL("1.2.1"),

        // Framework Support
        APP_COMPAT("1.2.0"),
        CONSTRAINT_LAYOUT("2.0.4"),
        RECYCLER_VIEW("1.1.0"),
        SWIPE_REFRESH_LAYOUT("1.1.0"),
        CARD_VIEW("1.0.0"),
        VIEW_PAGER_2("1.0.0"),
        HILT_LIFECYCLE("1.0.0-alpha01"),
        KTX_CORE("1.3.2"),
        KTX_LIFECYCLE("2.3.0-rc01"),
        KTX_FRAGMENT("1.2.5"),

        // Test
        JUNIT("4.13"),
    }

    object BuildScriptPlugin {
        val gradlePlugin = "com.android.tools.build:gradle:${GRADLE.version}"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${KOTLIN.version}"
        val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${KOTLIN.version}"
        val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${SQL_DELIGHT.version}"
        val koin = "org.koin:koin-gradle-plugin:${KOIN.version}}"
        val hilt = "com.google.dagger:hilt-android-gradle-plugin:${HILT.version}"
    }

    object Arch {
        val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${COROUTINE.version}"

        val koinCore = "org.koin:koin-core:${KOIN.version}"

        object Android {
            val coroutineAndroid =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${COROUTINE.version}"

            val dagger = "com.google.dagger:dagger:${DAGGER.version}"
            val daggerCompiler = "com.google.dagger:dagger-compiler:${DAGGER.version}"
            val hilt = "com.google.dagger:hilt-android:${HILT.version}"
            val hiltCompiler = "com.google.dagger:hilt-android-compiler:${HILT.version}"
        }
    }

    object Network {
        val ktorClientCore = "io.ktor:ktor-client-core:${KTOR.version}"
        val ktorClientSerialization = "io.ktor:ktor-client-serialization:${KTOR.version}"

        object Android {
            val ktorClient = "io.ktor:ktor-client-android:${KTOR.version}"
        }

        object IOS {
            val ktorClient = "io.ktor:ktor-client-ios:${KTOR.version}"
        }
    }

    object Storage {
        val sqlDelightRuntime = "com.squareup.sqldelight:runtime:${SQL_DELIGHT.version}"
        val sqlDelightCoroutineExt =
            "com.squareup.sqldelight:coroutines-extensions:${SQL_DELIGHT.version}"

        object Android {
            val sqlDelightDriver =
                "com.squareup.sqldelight:android-driver:${SQL_DELIGHT.version}"
        }

        object IOS {
            val sqlDelightDriver =
                "com.squareup.sqldelight:native-driver:${SQL_DELIGHT.version}"
        }
    }

    object Serialization {
        val kotlinSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-core:${KOTLIN_SERIALIZATION.version}"
    }

    object Image {
        object Android {
            val coil = "io.coil-kt:coil:${COIL.version}"
        }
    }

    object Design {
        object Android {
            val material = "com.google.android.material:material:${MATERIAL.version}"
        }
    }

    object Support {
        object Android {
            val appCompat = "androidx.appcompat:appcompat:${APP_COMPAT.version}"
            val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${CONSTRAINT_LAYOUT.version}"
            val recyclerView =
                "androidx.recyclerview:recyclerview:${RECYCLER_VIEW.version}"
            val swipeRefreshLayout =
                "androidx.swiperefreshlayout:swiperefreshlayout:${SWIPE_REFRESH_LAYOUT.version}"
            val cardView =
                "androidx.cardview:cardview:${CARD_VIEW.version}"
            val viewPager2 =
                "androidx.viewpager2:viewpager2:${VIEW_PAGER_2.version}"

            object HiltX {
                val lifecycleViewModel =
                    "androidx.hilt:hilt-lifecycle-viewmodel:${HILT_LIFECYCLE.version}"
                val compiler = "androidx.hilt:hilt-compiler:${HILT_LIFECYCLE.version}"
            }

            object Ktx {
                val core =
                    "androidx.core:core-ktx:${KTX_CORE.version}"
                val lifecycleViewModel =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${KTX_LIFECYCLE.version}"
                val lifecycleRuntime =
                    "androidx.lifecycle:lifecycle-runtime-ktx:${KTX_LIFECYCLE.version}"
                val lifecycleLiveData =
                    "androidx.lifecycle:lifecycle-livedata-ktx:${KTX_LIFECYCLE.version}"
                val fragment =
                    "androidx.fragment:fragment-ktx:${KTX_FRAGMENT.version}"
                val lifecycleViewModelSavedState =
                    "androidx.lifecycle:lifecycle-viewmodel-savedstate:${KTX_LIFECYCLE.version}"
            }

        }
    }

    object Logging {
        val kotlinLogging = "io.ktor:ktor-client-logging:${KTOR.version}"

        object Android {
            val kotlinLogging = "io.ktor:ktor-client-logging-jvm:${KTOR_ANDROID.version}"
        }

        object IOS {
            val kotlinLogging = "io.ktor:ktor-client-logging-native:${KTOR_IOS.version}"
        }
    }

    object Test {
        val junit = "junit:junit:${JUNIT.version}"
    }
}