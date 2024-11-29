plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.eominik.timer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.eominik.timer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.8.4")

    // Compose Extended Icons
    implementation ("androidx.compose.material:material-icons-extended:1.7.5")

    // ViewModel + LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
  //  implementation("androidx.compose.runtime:runtime-livedata:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7")
    implementation(libs.core.ktx)
    implementation(libs.androidx.runtime.livedata)
    kapt("androidx.lifecycle:lifecycle-common-java8:2.8.7")

    // Room
    implementation( "androidx.room:room-runtime:2.6.1")
    kapt( "androidx.room:room-compiler:2.6.1")
    implementation( "androidx.room:room-ktx:2.6.1")

    // Hilt
    implementation( "com.google.dagger:hilt-android:2.50")
    kapt( "com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Accompanist libraries
    implementation( "com.google.accompanist:accompanist-flowlayout:0.21.4-beta")

    // Logcat
    implementation("com.squareup.logcat:logcat:0.1")

    // Zhuinden flow-combinetuple
 //   implementation("com.github.Zhuinden:flow-combinetuple-kt:1.1.1")









// Test dependencies
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:4.8.0")
    testImplementation ("androidx.room:room-testing:2.6.1")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    testImplementation(libs.junit)

    androidTestImplementation ("androidx.room:room-testing:2.6.1")
    androidTestImplementation ("com.google.dagger:hilt-android-testing:2.46")
    kaptAndroidTest ("com.google.dagger:hilt-compiler:2.50")
    androidTestImplementation ("androidx.test.ext:junit:1.2.1")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.6.1")

    // Dependencies for local unit tests
    testImplementation( "junit:junit:4.13.2")
    testImplementation( "org.hamcrest:hamcrest-all:1.3")
    testImplementation( "androidx.arch.core:core-testing:2.2.0")
    testImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    testImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation( "org.robolectric:robolectric:4.2.1")
    testImplementation( "androidx.navigation:navigation-testing:2.8.4")
    testImplementation( "androidx.test.espresso:espresso-core:3.6.1")
    testImplementation( "androidx.test.espresso:espresso-contrib:3.6.1")
    testImplementation( "androidx.test.espresso:espresso-intents:3.6.1")
    testImplementation( "com.google.truth:truth:1.1.5")
    testImplementation( "androidx.compose.ui:ui-test-junit4:1.7.5")

    // Dependencies for Android unit tests
    androidTestImplementation( "junit:junit:4.13.2")
    androidTestImplementation( "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation( "androidx.compose.ui:ui-test-junit4:1.7.5")

    // AndroidX Test - JVM testing
    testImplementation( "androidx.test:core-ktx:1.6.1")
    testImplementation( "androidx.test.ext:junit-ktx:1.2.1")
    testImplementation( "androidx.test:rules:1.6.1")
    implementation ("androidx.test:core:1.6.1")

    // AndroidX Test - Instrumented testing
    androidTestImplementation( "androidx.test:core-ktx:1.6.1")
    androidTestImplementation( "androidx.test.ext:junit-ktx:1.2.1")
    androidTestImplementation( "androidx.test:rules:1.6.1")
    androidTestImplementation( "androidx.room:room-testing:2.6.1")
    androidTestImplementation( "androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation( "androidx.navigation:navigation-testing:2.8.4")
    androidTestImplementation( "androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation ("androidx.test.espresso:espresso-contrib:3.6.1")
    androidTestImplementation( "androidx.test.espresso:espresso-intents:3.6.1")
    androidTestImplementation( "androidx.test.espresso.idling:idling-concurrent:3.6.1")
    androidTestImplementation( "org.robolectric:annotations:4.7.3")
    implementation( "androidx.test.espresso:espresso-idling-resource:3.6.1")

    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.5")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.7.5")

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}