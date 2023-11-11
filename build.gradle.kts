// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.6.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.1")
    }
}

apply(plugin = "org.jlleitschuh.gradle.ktlint")
apply(plugin = "io.gitlab.arturbosch.detekt")
