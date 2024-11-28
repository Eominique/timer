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

    // Compose UI Util
  //  implementation ("androidx.compose.ui:ui-util:1.7.5")

    // Compose Navigation
  //  implementation ("androidx.navigation:navigation-compose:2.8.4")

    // Compose Extended Icons
    implementation ("androidx.compose.material:material-icons-extended:1.0.0")

    // ViewModel + LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
  //  implementation("androidx.compose.runtime:runtime-livedata:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7")
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











    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}