plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.compose") version "1.6.10"
    kotlin("plugin.serialization") version "1.9.10"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(compose.desktop.currentOs)
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}

compose.desktop {
    application {
        mainClass = "MainKt"
    }
}
