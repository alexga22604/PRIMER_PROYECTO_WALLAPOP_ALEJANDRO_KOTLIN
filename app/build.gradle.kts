plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.wallapopandroidalejandro"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.wallapopandroidalejandro"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // ---- Dependencies base de Android/Kotlin ----
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // ---- Compose: Usamos el BOM para gestionar las versiones ----
    implementation(platform(libs.androidx.compose.bom))

        // ... otras dependencias

    // ---- Componentes de UI de Compose ----

    // UI Tooling (para @Preview y gráficos)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Material 3 (Para Card y Text)
    implementation(libs.androidx.compose.material3)

    // Foundation (ESENCIAL para Layouts, Box, Column, y LazyVerticalGrid)
    // Usamos el alias para que el BOM le asigne la versión.
    // Si no tienes un alias definido en 'libs.versions.toml', reemplaza con la Opción 2.
    implementation("androidx.compose.foundation:foundation")
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.compose.material:material-icons-extended")
    // La dependencia que causaba el error, debe tomar su versión del BOM

    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation(libs.androidx.compose.runtime)
    // El 'foundation-layout' se incluye generalmente dentro de 'foundation', pero no está de más si lo necesitas.
    // implementation("androidx.compose.foundation:foundation-layout") // Generalmente no es necesaria si tienes 'foundation'

    // ---- Testing ----
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}