import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Configures the plugin dependencies for this project.
// GradleのVersionが7系だとmbGeneratorが認識されない。
plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("com.arenagod.gradle.MybatisGenerator") version "1.4" // ADD: Mybatis Gradle Generator Plugin
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.book.manager"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

// Configures the dependencies for this project.
dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.2.2")
	implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.4.1") // ADD: MyBatis Dynamic SQL
	implementation("com.mysql:mysql-connector-j:8.0.31") // ADD: JDBC Type 4 driver for MySQL
	mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.1") // ADD: MyBatis Generator
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Configures the mybatisGenerator extension.
mybatisGenerator {
	verbose = true
	configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
}