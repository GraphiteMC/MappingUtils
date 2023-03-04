plugins {
    kotlin("jvm") version "1.8.10"
}

group = "net.graphitemc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ow2.asm:asm:9.4")
}

kotlin {
    jvmToolchain(17)
}