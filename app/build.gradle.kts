plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)          // ✅ corregido
    alias(libs.plugins.hilt)         // ✅ corregido
    alias(libs.plugins.secrets.gradle)   // ✅ esto faltaba

}

android {
    namespace = "com.diego.demo.english"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.diego.demo.english"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"   // ✅ consistente con el bloque kotlin abajo
    }

    buildFeatures {
        compose = true
        buildConfig = true
        resValues = true
    }

    //noinspection WrongGradleMethod
    secrets {
        propertiesFileName = "local.properties"
        ignoreList.add("sdk.dir")
    }

    //noinspection WrongGradleMethod
    ksp {
        arg("hilt.disableModulesHaveInstallInCheck", "true")
    }

    //noinspection WrongGradleMethod
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21)
        }
    }

    flavorDimensions.add("environment")
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://api.dictionaryapi.dev/api/v2/\"")
            resValue("string", "app_name", "ApiDictionary (DEV)")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://api.dictionaryapi.dev/api/v2/\"")
            resValue("string", "app_name", "ApiDictionary Pro")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.com.squareup.retrofit2.retrofit)            // ✅ corregido
    implementation(libs.com.squareup.retrofit2.converter.json)      // ✅ corregido
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.io.coil.kt.coil.compose)                    // ✅ corregido
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)           // ✅ corregido
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    ksp(libs.hilt.compiler)
}