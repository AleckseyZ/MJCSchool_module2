plugins {
    id("RESTBasics.common-conventions")
}

dependencies {
    implementation("org.springframework:spring-jdbc:${Versions.springVer}")
    implementation("org.postgresql:postgresql:${Versions.postgreVer}")
}