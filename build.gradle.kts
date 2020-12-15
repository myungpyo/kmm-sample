buildscript {

    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.BuildScriptPlugin.kotlinGradlePlugin)
        classpath(Dependencies.BuildScriptPlugin.gradlePlugin)
        classpath(Dependencies.BuildScriptPlugin.kotlinSerialization)
        classpath(Dependencies.BuildScriptPlugin.sqlDelight)
        classpath(Dependencies.BuildScriptPlugin.hilt)
    }
}

group = "com.smp"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/ekito/koin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}