[jenkins]: https://jenkins.abstr.net/job/JClaw/
[license]: https://github.com/refracta/jclaw/tree/main/LICENSE
[jenkins-shield]: https://img.shields.io/badge/Download-Jenkins-brightgreen.svg
[license-shield]: https://img.shields.io/badge/License-MIT-lightgrey.svg
[ ![jenkins-shield][] ][jenkins]
[ ![license-shield][] ][license]

<img align="right" src="https://user-images.githubusercontent.com/58779799/115257731-56840f80-a16b-11eb-9f7d-328b8aa85170.png" width="200" height="243">

# JClaw

JClaw is a Java-based macro framework for automated tasks. Includes features for OpenCV-based pixel search, overlay interface, clipboard utils, keyboard and mouse operation. JClaw was inspired by [sikuli-core](https://github.com/sikuli/sikuli-core).

## Examples
![](https://user-images.githubusercontent.com/58779799/115538576-0033df80-a2d7-11eb-94a7-1da9b53a0a8b.gif)

[src/test/java/JSPaint.java](https://github.com/refracta/jclaw/blob/main/src/test/java/JSPaint.java): Example of using [JS Paint](https://github.com/1j01/jspaint)


## Download
**Maven**
```xml
<repository>
    <id>refracta</id>
    <name>refracta-repositories</name>
    <url>https://nexus.abstr.net/repository/maven-releases/</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.refracta.jclaw</groupId>
    <artifactId>jclaw</artifactId>
    <version>0.1</version>
</dependency>
```
**Gradle**
```gradle
repositories {
    mavenCentral()
    maven {
      name 'refracta-repositories'
      url 'https://nexus.abstr.net/repository/maven-releases/'
    }
}
```
```gradle
dependencies {
    compile 'com.github.refracta.jclaw:jclaw:0.1'
}
```

## Dependencies
This project requires **Java 8+**.
All dependencies are managed automatically by Maven.
 * JNA Platform
   * Version: **5.8.0**
   * [Github](https://github.com/java-native-access/jna)
 * Apache Commons IO
   * Version: **2.8.0**
   * [Github](https://github.com/apache/commons-io)
 * OpenCV
   * Version: **4.5.1-2**
   * [Github](https://github.com/openpnp/opencv)

## How to Contribute
1. Clone repository from develop branch and create a new branch
```bash
git checkout https://github.com/refracta/jclaw -b name_for_new_branch
```
2. Make changes and test
3. Submit Pull Request with comprehensive description of changes

## Related Projects
 - [JAHK](https://github.com/refracta/jahk)
 - [sikuli-core](https://github.com/sikuli/sikuli-core)
