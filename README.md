# OpsGenie Client


The project includes:

* Java SDK
* Marid

## Java SDK For Maven and Gradle


### Maven

You can add OpsGenie Repository and SDK as dependency. Example:

```
<dependencies>
  	<dependency>
  		<groupId>com.opsgenie.integration</groupId>
  		<artifactId>sdk</artifactId>
  		<version>[2.0.0,)</version>
  	</dependency>
  </dependencies>
  
<repositories>
	<repository>
		<id>opsgenie-repo</id>
		<name>OpsGenie Maven Repository</name>
		<url>http://repo.opsgenie.com:9393/content/groups/public</url>
		<layout>default</layout>
	</repository>  	
</repositories>
```

### Gradle

You can add OpsGenie Repository and SDK as dependeny. Example:

```
repositories {
    maven{
		url "http://repo.opsgenie.com:9393/content/groups/public"
	}
}

dependencies {
	compile "com.opsgenie.integration:sdk:2+"
}
```


## Build

**Requires JDK 1.7** 

This is a gradle project so you can build by running `build` task

Unix:
``./gradlew build``

Windows:
``gradlew.bat build``

###Packaging RPM and DEB

####Tasks####

* **maridRpm:** Packages Marid rpm for Redhat based systems

* **maridDeb:** Packages Marid deb for Debian based systems

* **maridZip:** Packages Marid zip for other systems

* **maridAll:** Packages Marid all types

You can run the tasks:

For Unix systems: ``./gradlew maridAll``

For Windows systems: ``gradlew.bat maridAll``

###Testing###

If you want to run tests you must copy `Test.example.properties` as `Test.properties` and set your Pubnub account keys.

If you want to skip tests, just run commands:

``./gradlew maridAll -x test``


**Note:** If you use IntellijIdea you can edit run configurations for gradle task and add `-x test` to task input.
