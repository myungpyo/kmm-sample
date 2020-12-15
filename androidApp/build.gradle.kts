plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":shared"))
    implementation(Dependencies.Arch.coroutineCore)
    implementation(Dependencies.Arch.Android.coroutineAndroid)
    kapt(Dependencies.Arch.Android.hiltCompiler)
    implementation(Dependencies.Arch.Android.hilt)
    kapt(Dependencies.Support.Android.HiltX.compiler)
    implementation(Dependencies.Support.Android.HiltX.lifecycleViewModel)
    implementation(Dependencies.Image.Android.coil)
    implementation(Dependencies.Support.Android.Ktx.core)
    implementation(Dependencies.Support.Android.Ktx.lifecycleViewModel)
    implementation(Dependencies.Support.Android.Ktx.lifecycleViewModelSavedState)
    implementation(Dependencies.Support.Android.Ktx.lifecycleRuntime)
    implementation(Dependencies.Support.Android.Ktx.lifecycleLiveData)
    implementation(Dependencies.Support.Android.Ktx.fragment)
    implementation(Dependencies.Support.Android.constraintLayout)
    implementation(Dependencies.Support.Android.recyclerView)
    implementation(Dependencies.Support.Android.swipeRefreshLayout)
    implementation(Dependencies.Support.Android.cardView)
    implementation(Dependencies.Support.Android.viewPager2)
    implementation(Dependencies.Support.Android.appCompat)
    implementation(Dependencies.Design.Android.material)
}

android {
    compileSdkVersion(29)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.smp.moviediary.androidApp"
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
}