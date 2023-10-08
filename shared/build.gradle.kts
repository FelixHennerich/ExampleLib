plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
}

group = "com.github.examplelib"
version = "0.0.1"


val GITHUB_USER: String = "felixhennerich"
val GITHUB_TOKEN: String = "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"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            setUrl("https://maven.pkg.github.com/FelixHennerich/ExampleLib")
            credentials {
                username = GITHUB_USER
                password = GITHUB_TOKEN
            }
        }
    }
}


android {
    namespace = "de.hennerich.examplelib"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}