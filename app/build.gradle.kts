
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.gymbroschedule"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gymbroschedule"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    implementation("androidx.fragment:fragment-ktx:1.8.4")
    implementation("androidx.compose.material3:material3-android:1.3.0")
    implementation("androidx.media3:media3-common-ktx:1.5.1")

    val nav_version = "2.7.5"
    val room = "2.6.0"

    // Room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    kapt("androidx.room:room-compiler:$room")

    implementation("androidx.navigation:navigation-compose:$nav_version")

    implementation("androidx.compose.animation:animation:1.5.0")
    implementation("androidx.compose.ui:ui-tooling:1.5.0")


    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.6.0")

    implementation("androidx.compose.ui:ui:1.7.0")
    implementation("androidx.compose.material:material-android:1.7.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.0")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.activity:activity-compose:1.9.2")

    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}