plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.10-1.0.13"
}

android {
    namespace = "com.example.videoplayer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.videoplayer"
        minSdk = 28
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        viewBinding = true
    }

}


dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.exoplayer)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    ksp(libs.room.compiler)
    implementation(libs.gson)
    implementation(libs.coil)
    implementation(libs.material)
    implementation(libs.logging.interceptor)

    testImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}