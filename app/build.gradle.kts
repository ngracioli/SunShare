plugins {
    application
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.18.3")
    implementation("org.fusesource.jansi:jansi:2.4.1")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "sunshare.App"
}

// tasks.named<Test>("test") {
//     // Use JUnit Platform for unit tests.
//     useJUnitPlatform()
// }
