plugins {
    kotlin("jvm") version "1.4.10"
    maven
}

repositories {
    jcenter()
    maven { setUrl("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.4-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin { kotlinOptions.jvmTarget = "1.8" }
    compileJava { options.encoding = "UTF-8" }
}
