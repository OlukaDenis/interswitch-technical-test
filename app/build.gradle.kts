plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}


android {
//    compileSdk 31

//    defaultConfig {
//        applicationId "com.mcdenny.interswitchtechnicaltest"
//        minSdk 23
//        targetSdk 31
//        versionCode 1
//        versionName "1.0"
//
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }

    compileSdk = Dependencies.ProjectConstants.COMPILE_SDK
    buildToolsVersion = Dependencies.ProjectConstants.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = Dependencies.ProjectConstants.MINIMUM_SDK
        targetSdk = Dependencies.ProjectConstants.TARGET_SDK
        applicationId = Dependencies.ProjectConstants.BASE
        versionCode = Dependencies.ProjectConstants.VERSION_CODE
        versionName = Dependencies.ProjectConstants.VERSION_NAME
        multiDexEnabled = true
        testInstrumentationRunner = Dependencies.ProjectConstants.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        named("debug") {
            buildConfigField("String", "BASE_URL", "\"${Versions.BASE_URL}\"")
        }

        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )

            buildConfigField("String", "BASE_URL", "\"${Versions.BASE_URL}\"")
        }
    }

    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(Dependencies.Gradle.KOTLIN_STDLIB)

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.MATERIAL)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidX.FRAGMENT_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_KTX)
    implementation(Dependencies.AndroidX.LIVEDATA_KTX)
    implementation(Dependencies.AndroidX.VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.DATA_STORE)
    implementation(Dependencies.AndroidX.DATA_STORE_PREFERENCES)
    implementation(Dependencies.AndroidX.PAGING)
    implementation(Dependencies.AndroidX.ROOM_PAGING)
    implementation(Dependencies.AndroidX.ANNOTATION)
    implementation(Dependencies.AndroidX.LEGACY_SUPPPORT)
    implementation(Dependencies.AndroidX.WORK_MANAGER_KTX)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(Dependencies.AndroidX.LIFECYCLE_PROCESSOR)

    implementation(Dependencies.Room.RUNTIME)
    implementation(Dependencies.Room.KTX)
    kapt(Dependencies.Room.COMPILER)

    implementation(Dependencies.Navigation.NAV_UI)
    implementation(Dependencies.Navigation.NAV_FRAGMENT)
    implementation(Dependencies.Navigation.NAV_RUNTIME)

    implementation(Dependencies.Util.TIMBER)
    implementation(Dependencies.Util.JAVA_JWT)
    implementation(Dependencies.Util.CIRCLE_IMAGE_VIEW)

    implementation(Dependencies.Network.OKHTTP)
    implementation(Dependencies.Network.RETROFIT)
    implementation(Dependencies.Network.GSON_CONVERTER)
    implementation(Dependencies.Network.LOGGING_INTERCEPTOR)

    testImplementation(Dependencies.Kotlin.COROUTINE_TEST)
    implementation(Dependencies.Kotlin.COROUTINE_ANDROID)

    implementation(Dependencies.Hilt.HILT_WORKER)
    implementation(Dependencies.Hilt.ANDROID)
    kapt(Dependencies.Hilt.COMPILER)
    kapt(Dependencies.Hilt.HILT_COMPILER)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_EXT)
    androidTestImplementation(Dependencies.Test.ESPRESSO)
}