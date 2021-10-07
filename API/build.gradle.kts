plugins {
    id("RESTBasics.common-conventions")
    war
}

dependencies {
    implementation("org.springframework:spring-web:5.3.10")
    implementation("org.springframework:spring-webmvc:5.3.10")
    implementation(project(":service"))
    implementation(project(":DAL"))
}