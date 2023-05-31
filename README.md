# OpsGenie Client


The project includes:

* Java SDK
* Marid (Deprecated)

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
```

### Gradle

You can add OpsGenie Repository and SDK as dependeny. Example:

```
dependencies {
	compile "com.opsgenie.integration:sdk:2+"
}
```
#### Following APIs are not supported for now in this SDK. Their support will be provided soon.
GitHub issue for this : https://github.com/opsgenie/opsgenieclient/issues/34
* Integration API
* Policy API
* Notification Rule API
* Notification Rule Step API
* Team Routing Rule API
* Schedule API
* Schedule Override API
* Escalation API
* Who is On Call API


## Build

**Requires JDK 1.8** 

This is a gradle project so you can build by running `build` task

Unix:
``./gradlew build``

Windows:
``gradlew.bat build``

###Packaging RPM and DEB

####Tasks####

* **packageMaridRpm:** Packages Marid rpm for Redhat based systems

* **packageMaridDeb:** Packages Marid deb for Debian based systems

* **packageMaridZip:** Packages Marid zip for other systems

* **packageMarid:** Packages Marid all types

You can run the tasks:

For Unix systems: ``./gradlew packageMarid``

For Windows systems: ``gradlew.bat packageMarid``

###Testing###

If you want to run tests you must copy `Test.example.properties` as `Test.properties` and set your Pubnub account keys.

If you want to skip tests, just run commands:

``./gradlew packageMarid -x test``


**Note:** If you use IntellijIdea you can edit run configurations for gradle task and add `-x test` to task input.
