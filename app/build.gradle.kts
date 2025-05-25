plugins {
    application
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

application {
    mainClass.set("sunshare.app.App")
}

tasks.register<Jar>("fatJar") {
    archiveBaseName.set("sunshare-app-fat")
    manifest {
        attributes["Main-Class"] = "sunshare.app.App"
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith(".jar") }.map { zipTree(it) }
    })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
