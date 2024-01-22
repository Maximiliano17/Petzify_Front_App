plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")//injexion de dependencias con dagger, parte 1
    id("com.google.dagger.hilt.android")//injexion de dependencias con dagger, parte 2
    id("androidx.navigation.safeargs.kotlin")//navegacion segura entre fragments
}

android {
    namespace = "com.maxi.petzify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.maxi.petzify"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release"){
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:5000/\"")
        }

        getByName("debug"){
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:5000/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("androidx.core:core-splashscreen:1.0.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val retrofitVersion = "2.9.0"
    val daggerVersion = "2.48"
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofitVersion}")

    //interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.3.1")


    //injexion de dependencias con dagger
    implementation("com.google.dagger:hilt-android:${daggerVersion}")
    kapt("com.google.dagger:hilt-compiler:${daggerVersion}")

    //picasso
    implementation("com.squareup.picasso:picasso:2.8")
}