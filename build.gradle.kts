import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.3.8"
  id("xyz.jpenilla.run-paper") version "1.0.6"
  id("net.minecrell.plugin-yml.bukkit") version "0.5.1"


  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.example"
version = "1.0.0-SNAPSHOT"
description = "Example Plugin"

java {

  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


repositories {
  maven {
    url = uri("https://repo.aikar.co/content/groups/aikar/")
  }
}

dependencies {
  paperDevBundle("1.18.1-R0.1-SNAPSHOT")

  //Aikar depends
  //implementation("cloud.commandframework", "cloud-paper", "1.6.1")

  implementation("net.dv8tion:JDA:5.0.0-alpha.9")
  implementation("co.aikar:taskchain-bukkit:3.7.2")
  implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

  //Project Lombok
  compileOnly("org.projectlombok:lombok:1.18.22")
  annotationProcessor("org.projectlombok:lombok:1.18.22")

  testCompileOnly("org.projectlombok:lombok:1.18.22")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
}

tasks {

  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(17)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }
  processResources {
    filteringCharset = Charsets.UTF_8.name()
  }



  shadowJar {

    fun reloc(pkg: String) = relocate(pkg, "com.example.dependency.$pkg")


    //reloc("cloud.commandframework")
    reloc("io.leangen.geantyref")

    relocate("co.aikar.taskchain", "com.example.taskchain")
    relocate("co.aikar.commands", "com.example.acf")
    relocate("co.aikar.locales", "com.example.locales")
  }
}

bukkit {
  load = BukkitPluginDescription.PluginLoadOrder.STARTUP
  main = "com.example.ExamplePlugin"
  apiVersion = "1.18"
  authors = listOf("ITzFel17")
}
