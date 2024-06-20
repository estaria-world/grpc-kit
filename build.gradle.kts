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
    compileOnly("io.grpc:grpc-stub:$grpcVersion")
    compileOnly("io.grpc:grpc-netty-shaded:$grpcVersion")
    compileOnly("io.grpc:grpc-kotlin-stub:1.4.1")
}

tasks.named("shadowJar", ShadowJar::class) {
    mergeServiceFiles()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/estaria-world/grpc-kit")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
