plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-core:5.3.10")
    implementation("org.springframework:spring-beans:5.3.10")
    implementation("org.springframework:spring-context:5.3.10")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}

tasks.test {
    useJUnitPlatform()
}