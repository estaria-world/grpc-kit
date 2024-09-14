import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("plugin.serialization") version "1.9.0"
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "world.estaria"
version = "1.1.0"

repositories {
    mavenCentral()

    // jitpack repositories
    maven("https://jitpack.io/")

    // protobuf repositories
    maven {
        name = "buf"
        url = uri("https://buf.build/gen/maven")
    }
}

dependencies {
    compileOnly(kotlin("stdlib"))

    // avionik dependencies
    compileOnly("world.avionik:config-kit:1.0.2")

    // protobuf dependencies
    compileOnly("com.google.protobuf:protobuf-java:3.16.3")

    // grpc dependencies
    val grpcVersion = "1.61.0"
    api("io.grpc:grpc-stub:$grpcVersion") {
        exclude("org.jetbrains.kotlin")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-common")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-native")
    }
    api("io.grpc:grpc-netty-shaded:$grpcVersion") {
        exclude("org.jetbrains.kotlin")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-common")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-native")
    }
    api("io.grpc:grpc-kotlin-stub:1.4.1") {
        exclude("org.jetbrains.kotlin")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-common")
        exclude("org.jetbrains.kotlinx", "kotlinx-coroutines-core-native")
    }
}

tasks.named("shadowJar", ShadowJar::class) {
    mergeServiceFiles()
}


publishing {
    repositories {
        maven {
            name = "estaria"
            url = uri("https://repo.estaria.world/releases")
            credentials(PasswordCredentials::class.java)
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}