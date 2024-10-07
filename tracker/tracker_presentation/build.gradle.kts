plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.plcoding.tracker_presentation"
}

dependencies{
    implementation(project(Modules.trackerDomain))
    implementation(project(Modules.core))
}

