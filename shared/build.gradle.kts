import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("com.github.gmazzo.buildconfig") version "2.0.2"
}

group = "com.smp"
version = "1.0-SNAPSHOT"

buildConfig {
    val prop = com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
    buildConfigField("String", "CLIENT_ID", "\"${prop.getProperty("naverapi.clientid")}\"")
    buildConfigField("String", "CLIENT_SECRET", "\"${prop.getProperty("naverapi.clientsecret")}\"")
}

kotlin {
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Arch.coroutineCore) {
                    version {
                        strictly(Dependencies.VersionMap.COROUTINE.version)
                    }
                }
                implementation(Dependencies.Serialization.kotlinSerialization)
                implementation(Dependencies.Network.ktorClientCore)
                implementation(Dependencies.Network.ktorClientSerialization)
                implementation(Dependencies.Storage.sqlDelightRuntime)
                implementation(Dependencies.Storage.sqlDelightCoroutineExt)
                implementation(Dependencies.Logging.kotlinLogging)
                implementation(Dependencies.Arch.koinCore)
                implementation(kotlin("stdlib-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Network.Android.ktorClient)
                implementation(Dependencies.Storage.Android.sqlDelightDriver)
                implementation(Dependencies.Logging.Android.kotlinLogging)
                implementation(Dependencies.Design.Android.material)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Dependencies.Network.IOS.ktorClient)
                implementation(Dependencies.Storage.IOS.sqlDelightDriver)
                implementation(Dependencies.Logging.IOS.kotlinLogging)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Dependencies.Test.junit)
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    dexOptions {
        javaMaxHeapSize = "4g"
    }
}

sqldelight {
    database("AppDB") {
        packageName = "com.smp.moviediary.shared.data.storage"
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>("ios").binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}
tasks.getByName("build").dependsOn(packForXcode)