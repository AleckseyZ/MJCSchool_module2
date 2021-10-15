plugins {
    id("RESTBasics.common-conventions")
    war
}

dependencies {
    compileOnly("javax.servlet:javax.servlet-api:${Versions.javaxVer}")
    implementation("org.springframework:spring-web:${Versions.springVer}")
    implementation("org.springframework:spring-webmvc:${Versions.springVer}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jacksonVer}")
    implementation(project(":service"))
    implementation(project(":dataAccess"))
}