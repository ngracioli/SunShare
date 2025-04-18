plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    // Jackson
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.3")

    // Jansi
    implementation("org.fusesource.jansi:jansi:2.4.1")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "sunshare.app.App"
}
