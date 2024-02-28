var rootGroupId = "com.github.AlphaFoxz"
var rootArtifactId = "oneboot-core"
var rootVersion = "0.0.1-alpha.0"
plugins {
    id("java-library")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("maven-publish")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
group = rootGroupId
version = rootVersion
repositories {
    mavenCentral()
}
tasks.bootJar {
    enabled = false
}
tasks.jar {
    enabled = true
    archiveClassifier = ""
}
configurations.all {
    resolutionStrategy.cacheChangingModulesFor(1, TimeUnit.SECONDS)
}
dependencyManagement {
    imports {
        org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
    }
    dependencies {
        /** 开发工具 */
        dependency("cn.hutool:hutool-all:5.8.25") // hutool工具包
        dependency("com.google.auto.service:auto-service:1.1.1") // spring自动注入
        dependency("com.google.code.findbugs:annotations:3.0.1") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
        dependency("com.squareup:javapoet:1.13.0") // java代码生成器（语法树）
        dependency("org.mapstruct:mapstruct:1.5.5.Final") // java代码生成器
        dependency("org.mapstruct:mapstruct-processor:1.5.5.Final") // java代码生成器
        dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0") // api文档
        /** 持久化组件 */
        dependency("com.mysql:mysql-connector-j:8.3.0") // mysql驱动
        dependency("mysql:mysql-connector-java:8.0.33") // mysql驱动 （停止更新）
        dependency("org.jooq:jooq-postgres-extensions:3.18.7") // jooq
        dependency("org.jooq:jooq-codegen:3.18.7") // jooq
        /** 文档处理 */
        dependency("org.apache.poi:poi-ooxml:5.2.5") // poi
        dependency("com.deepoove:poi-tl:1.12.1") // poi
    }
}
dependencies {
    compileOnly("com.google.code.findbugs:annotations") // 解决编译警告 找不到 javax.annotation.meta.When 的问题
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.mapstruct:mapstruct")
    annotationProcessor("org.mapstruct:mapstruct-processor")
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compileOnly("org.springframework.boot:spring-boot-starter")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
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