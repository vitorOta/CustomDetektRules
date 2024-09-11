plugins {
    alias(libs.plugins.detektGradlePlugin)
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.detekt.api)
    testImplementation(libs.junit)
    testImplementation(libs.detekt.test)
}