var rootGroupId = "com.github.AlphaFoxz"
var rootArtifactId = "oneboot-core"
var rootVersion = "0.0.1-alpha.0"
plugins {
    id("java")
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("maven-publish")
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = true
    archiveClassifier.set("")
}
apply(plugin = "java")
apply(plugin = "java-library")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "maven-publish")
group = rootGroupId
version = rootVersion
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.SECONDS)
}
repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}
dependencyManagement {
    imports {
        org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
    }
    dependencies {
        dependency("org.mapstruct:mapstruct:1.5.5.Final")
        dependency("org.mapstruct:mapstruct-processor:1.5.5.Final")
        dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
        dependency("cn.hutool:hutool-all:5.8.25")
        dependency("org.jooq:jooq-postgres-extensions:3.18.7")
        dependency("org.jooq:jooq-codegen:3.18.7")
        dependency("com.google.code.findbugs:annotations:3.0.1")
        dependency("org.apache.poi:poi-ooxml:5.2.5")
        dependency("com.deepoove:poi-tl:1.12.1")
    }
}
dependencies {
    compileOnly("com.google.code.findbugs:annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compileOnly("org.springframework.boot:spring-boot-starter-jooq")
    api("cn.hutool:hutool-all")
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = rootGroupId
            artifactId = rootArtifactId
            version = rootVersion
            from(components["java"])

            dependencies {
                api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
                api("cn.hutool:hutool-all:5.8.25")
            }
        }
    }
}