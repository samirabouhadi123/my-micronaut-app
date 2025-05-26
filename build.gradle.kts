plugins {
    id("io.micronaut.application") version "4.5.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.aot") version "4.5.3"
    id("maven-publish")
}

version = "0.1"
group = "com.example"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    testAnnotationProcessor ("io.micronaut:micronaut-inject-java")
    annotationProcessor("io.micronaut.data:micronaut-data-processor")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation(project(":api"))
    implementation(project(":core"))
    implementation("com.example:mavendemo:0.1")
    developmentOnly("io.micronaut.development:io.micronaut.development:0.1")
    implementation("example.com:Micronaut:0.1")
}


application {
    mainClass = "com.example.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
    aot {

        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}


