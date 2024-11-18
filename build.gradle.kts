plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.plugin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dokka) apply true
    alias(libs.plugins.firebase) apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}

apply(from = "docs/deps-graph.gradle")