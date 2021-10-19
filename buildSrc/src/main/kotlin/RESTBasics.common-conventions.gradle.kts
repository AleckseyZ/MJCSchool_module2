plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-core:${Versions.springVer}")
    implementation("org.springframework:spring-beans:${Versions.springVer}")
    implementation("org.springframework:spring-context:${Versions.springVer}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junitVer}")
}

tasks.test {
    useJUnitPlatform()
}