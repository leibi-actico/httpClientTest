plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.graalvm.buildtools.native") version "0.9.28"
    id("org.openapi.generator") version "7.4.0"
    id("idea")
}

group = "com.actico.poc.httpClient"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

idea {
    module {
        sourceDirs.add(file("build/generated/src/main/java"))
    }
}

sourceSets {
    main {
        java {
            srcDir("build/generated/src/main/java")
        }
    }
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")

    implementation("io.github.openfeign:feign-okhttp")
    implementation("io.github.openfeign:feign-jackson")
    implementation("io.github.openfeign:feign-micrometer")

    implementation("io.micrometer:micrometer-tracing-bridge-otel")
    implementation("io.opentelemetry:opentelemetry-exporter-zipkin")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    implementation("org.openapitools:jackson-databind-nullable:0.2.6")


    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("buildJokesClient") {
    description = "Generates the openApi client code."
    group = "build"
    generatorName.set("java")
    inputSpec.set("${projectDir}/api/JokesApi.yaml")
    outputDir.set("${projectDir}/build/generated/")
    apiPackage = "com.actico.poc.httpclient.jokes.client.api"
    modelPackage = "com.actico.poc.httpclient.jokes.client.model"
    generateApiTests.set(true)
    generateApiDocumentation.set(true)
    generateModelTests.set(true)
    library.set("feign") //feign, native
    configOptions.set(
        mapOf(
            "swaggerAnnotations" to "false",
            "openApiNullable" to "true",
            "interfaceOnly" to "true",
            "hideGenerationTimestamp" to "true",
            "skipDefaultInterface" to "true",
            "useSwaggerUI" to "true",
            "reactive" to "false",
            "useSpringBoot3" to "true",
            "oas3" to "true",
            "generateSupportingFiles" to "false",
            "useJakartaEe" to "true",
        )
    )
}

tasks.register<org.openapitools.generator.gradle.plugin.tasks.GenerateTask>("buildJokesClientWithTemplate") {
    description = "Generates the openApi client code."
    group = "build"
    generatorName.set("java")
    inputSpec.set("${projectDir}/api/JokesApi.yaml")
    outputDir.set("${projectDir}/build/generated/")
    templateDir.set("${projectDir}/api/templates")
    apiPackage = "com.actico.poc.httpclient.jokes.templated.client.api"
    modelPackage = "com.actico.poc.httpclient.jokes.templated.client.model"
    generateApiTests.set(true)
    generateApiDocumentation.set(true)
    generateModelTests.set(true)
    library.set("feign") //feign, native
    configOptions.set(
        mapOf(
            "swaggerAnnotations" to "false",
            "openApiNullable" to "true",
            "interfaceOnly" to "true",
            "hideGenerationTimestamp" to "true",
            "skipDefaultInterface" to "true",
            "useSwaggerUI" to "true",
            "reactive" to "false",
            "useSpringBoot3" to "true",
            "oas3" to "true",
            "generateSupportingFiles" to "false",
            "useJakartaEe" to "true",
        )
    )
}

tasks.withType<JavaCompile> { dependsOn("buildJokesClient", "buildJokesClientWithTemplate") }
