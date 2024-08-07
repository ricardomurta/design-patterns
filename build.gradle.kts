plugins {
    id("java")
}

group = "com.design-patterns"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.javatuples/javatuples
    implementation("org.javatuples:javatuples:1.2")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.3")



}

tasks.test {
    useJUnitPlatform()
}