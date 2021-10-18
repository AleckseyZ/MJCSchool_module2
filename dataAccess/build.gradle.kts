plugins {
    id("RESTBasics.common-conventions")
}

dependencies {
    implementation("org.springframework:spring-jdbc:${Versions.springVer}")
    implementation("org.postgresql:postgresql:${Versions.postgreVer}")
    testImplementation("org.springframework:spring-test:${Versions.springVer}")
    testImplementation("io.zonky.test:embedded-postgres:${Versions.zonkyPostgres}")
}