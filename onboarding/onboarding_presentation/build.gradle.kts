plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.plcoding.onboarding_presentation"
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.onboardingDomain))
    implementation(project(Modules.coreUi))

    implementation(Coil.coilCompose)
    implementation("androidx.test.services:storage:1.4.2")
}