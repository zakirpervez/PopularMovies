plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.husqvarna.popularmovies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.husqvarna.popularmovies"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "beta_1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_KEY", project.properties["API_KEY"].toString())
            buildConfigField("String", "ACCESS_TOKEN", project.properties["ACCESS_TOKEN"].toString())
            buildConfigField("String", "BASE_URL", project.properties["BASE_URL"].toString())
            buildConfigField("String", "IMAGES_URL", project.properties["IMAGES_URL"].toString())
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "API_KEY", project.properties["API_KEY"].toString())
            buildConfigField("String", "ACCESS_TOKEN", project.properties["ACCESS_TOKEN"].toString())
            buildConfigField("String", "BASE_URL", project.properties["BASE_URL"].toString())
            buildConfigField("String", "IMAGES_URL", project.properties["IMAGES_URL"].toString())
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Production
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0-alpha13")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.databinding:databinding-runtime:8.1.2")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.paging:paging-runtime:3.3.0-alpha02")

    // Unit Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("io.mockk:mockk:1.13.8")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // Instrumentation Tests
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.4")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.12")
}
kapt {
    correctErrorTypes = true
}

