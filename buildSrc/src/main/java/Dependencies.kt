object Dependencies {
    object Gradle {
        const val GRADLE = "com.android.tools.build:gradle:${Versions.GRADLE}"
        const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KOTLIN_STDLIB}"
        const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE}"
    }

    object ProjectConstants {
        const val BASE = "com.mcdenny.interswitchtechnicaltest"
        const val MINIMUM_SDK = 21
        const val COMPILE_SDK = 31
        const val TARGET_SDK = 31
        const val VERSION_CODE = 1
        const val VERSION_NAME = "1.0.0"
        const val BUILD_TOOLS_VERSION = "31.0.0"
        const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
        const val proguardConsumerRules = "consumer-rules.pro"
    }

    object Kotlin {
        const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.COROUTINES}"
        const val COROUTINE_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.COROUTINES}"
    }

    object AndroidX {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.AndroidX.APP_COMPAT}"
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.AndroidX.CORE_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.AndroidX.FRAGMENT_KTX}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.AndroidX.ACTIVITY_KTX}"
        const val MATERIAL = "com.google.android.material:material:${Versions.AndroidX.MATERIAL}"
        const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.LIFECYCLE_KTX}"
        const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.LIFECYCLE_KTX}"
        const val LIFECYCLE_PROCESSOR = "androidx.lifecycle:lifecycle-compiler:${Versions.AndroidX.LIFECYCLE_KTX}"
        const val WORK_MANAGER_KTX = "androidx.work:work-runtime-ktx:${Versions.AndroidX.WORK_MANAGER_KTX}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.CONSTRAINT_LAYOUT}"
        const val VIEW_PAGER = "androidx.viewpager2:viewpager2:${Versions.AndroidX.VIEW_PAGER}"
        const val DATA_STORE = "androidx.datastore:datastore:${Versions.AndroidX.DATA_STORE}"
        const val DATA_STORE_PREFERENCES = "androidx.datastore:datastore-preferences:${Versions.AndroidX.DATA_STORE}"
        const val PAGING = "androidx.paging:paging-runtime:${Versions.AndroidX.PAGING}"
        const val ROOM_PAGING = "androidx.room:room-paging:${Versions.AndroidX.ROOM_PAGING}"
        const val ANNOTATION = "androidx.annotation:annotation:${Versions.AndroidX.ANNOTATION}"
        const val LEGACY_SUPPPORT = "androidx.legacy:legacy-support-v4:${Versions.AndroidX.LEGACY}"
        const val START_UP_X = "androidx.startup:startup-runtime:${Versions.AndroidX.START_UP}"
    }

    object Test {
        const val CORE = "androidx.test:core:${Versions.Test.CORE}"
        const val RULES = "androidx.test:rules:${Versions.Test.CORE}"
        const val JUNIT = "junit:junit:${Versions.Test.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.Test.JUNIT_EXT}"
        const val JACOCO = "org.jacoco:org.jacoco.core:${Versions.Test.JACOCO}"
        const val TRUTHY = "com.google.truth:truth:${Versions.Test.TRUTHY}"
        const val MOCKK = "io.mockk:mockk:${Versions.Test.MOCKK}"
        const val DISPATCHER_PROVIDER = "com.rickbusarow.dispatcherprovider:dispatcher-provider:1.0.0-beta08"
        const val DISPATCHER_PROVIDER_TEST = "com.rickbusarow.dispatcherprovider:dispatcher-provider-test:1.0.0-beta08"
        const val JACOCO_ANDROID = "com.dicedmelon.gradle:jacoco-android:${Versions.Test.JACOCO_ANDROID}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Test.ESPRESSO}"
    }

    object Navigation {
        const val NAV_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
        const val NAV_RUNTIME = "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION}"
        const val NAV_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val TESTING = "androidx.navigation:navigation-testing:${Versions.NAVIGATION}"
        const val CLASSPATH = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.NAVIGATION}"
    }

    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.Hilt.HILT_CORE}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.Hilt.HILT_CORE}"
        const val HILT_COMPILER = "androidx.hilt:hilt-compiler:${Versions.Hilt.HILT_COMPILER}"
        const val TEST = "com.google.dagger:hilt-android-testing:${Versions.Hilt.HILT_CORE}"
        const val CLASSPATH = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt.HILT_CORE}"
        const val HILT_WORKER = "androidx.hilt:hilt-work:${Versions.Hilt.HILT_COMPILER}"
    }

    object Room {
        const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
        const val RUNTIME = "androidx.room:room-runtime:${Versions.ROOM}"
        const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
        const val TEST = "androidx.room:room-testing:${Versions.ROOM}"
        const val COMMON = "androidx.room:room-common:${Versions.ROOM}"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.Network.RETROFIT}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Network.OKHTTP}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.OKHTTP}"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Network.RETROFIT}"
        const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:${Versions.Network.OKHTTP}"
    }

    object Util {
        const val TIMBER = "com.jakewharton.timber:timber:${Versions.Utils.TIMBER}"
        const val ALERTER = "com.github.tapadoo:alerter:${Versions.Utils.ALERTER}"
        const val CIRCLE_IMAGE_VIEW = "de.hdodenhof:circleimageview:${Versions.Utils.CIRCLE_IMAGE_VIEW}"
        const val JAVA_JWT = "com.auth0:java-jwt:${Versions.Utils.JWT}"
    }
}