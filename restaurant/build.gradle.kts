
plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("org.jetbrains.kotlin.kapt") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.21"

    id("com.github.johnrengelman.shadow") version "7.1.2"

    id("io.micronaut.application") version "3.6.2"
    id("io.micronaut.test-resources") version "3.6.2"
}

version = "0.1"
group = "io.resto"

val kotlinVersion= project.properties["kotlinVersion"]

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")

    // Minimal Micronaut dependencies
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("jakarta.annotation:jakarta.annotation-api")

    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Database connection
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    runtimeOnly("mysql:mysql-connector-java")
}

application {
    // Define the main class for the application.
    mainClass.set("io.resto.echo2.Echo2AppKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("io.resto.*")
    }
    testResources {
        additionalModules.add("jdbc-mysql")
    }
}

// See https://micronaut-projects.github.io/micronaut-gradle-plugin/snapshot/#_docker_support
tasks.named<io.micronaut.gradle.docker.MicronautDockerfile>("dockerfile") {
    // Unfortunately we can't use distroless because we need bash to wait for connection
    // baseImage.set("gcr.io/distroless/java11-debian11")

    baseImage.set("eclipse-temurin:11")
    entryPoint(
        "/home/app/resources/wait-for-it.sh",
        "mysql:3306",
        "--",
        "java",
        "-Xmx128m",
        "-jar",
        "/home/app/application.jar"
    )
}

tasks.named<com.bmuschko.gradle.docker.tasks.image.DockerBuildImage>("dockerBuild") {
    // TODO specify docker repo here
    // images.add("eu-frankfurt-1.ocir.io/xyzzyz/repo/my-image:$project.version")
}