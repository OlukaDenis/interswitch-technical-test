/*
 * Copyright (c) 2022 Denis Oluka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.Gradle.KOTLIN_STDLIB)

//    implementation("androidx.core:core-ktx:1.7.0")
//    implementation("androidx.appcompat:appcompat:1.4.1")
//    implementation("com.google.android.material:material:1.5.0")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.GSON_CONVERTER)
    implementation(Dependencies.Network.LOGGING_INTERCEPTOR)
    testImplementation(Dependencies.Network.MOCK_WEB_SERVER)
    androidTestImplementation(Dependencies.Network.MOCK_WEB_SERVER)

    testImplementation(Dependencies.Kotlin.COROUTINE_TEST)
    implementation(Dependencies.Kotlin.COROUTINE_ANDROID)

    implementation(Dependencies.Hilt.HILT_WORKER)
    implementation(Dependencies.Hilt.HILT_ANDROID)
    implementation(Dependencies.Hilt.HILT_VIEWMODEL)
    kapt(Dependencies.Hilt.HILT_ANDROID_COMPILER)
    kapt(Dependencies.Hilt.HILT_COMPILER)
    testImplementation(Dependencies.Hilt.HILT_TEST)
    androidTestImplementation(Dependencies.Hilt.HILT_TEST)
    kaptAndroidTest(Dependencies.Hilt.HILT_ANDROID_COMPILER)

    implementation(Dependencies.Room.RUNTIME)
    implementation(Dependencies.Room.KTX)
    kapt(Dependencies.Room.COMPILER)

}