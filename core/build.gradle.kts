import java.util.Properties

val localProperties: Properties by lazy {
    Properties().also {
        it.load(project.rootProject.file("local.properties").inputStream())
    }
}

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.room")
}

android {
    namespace = "com.dicoding"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "IAC_BASE_URL",
            "\"https://api.artic.edu\""
        )
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    api(libs.androidx.navigation.compose)
    ksp(libs.androidx.room.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.okhttp)
    api(libs.androidx.room.ktx)
    api(libs.room.runtime)
    api(libs.androidx.room.paging)
    api(libs.androidx.paging.compose)
    api(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.webkit)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.hilt.android)

    api(libs.kotlin.reflect)
}

room {
    schemaDirectory("$projectDir/schemas")
}