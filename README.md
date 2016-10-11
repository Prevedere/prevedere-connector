# Prevedere Anypoint Connector
The Prevedere Connector can be utilized to access Prevedere's external global economic indicator repository as well as Prevedere's leading industry forecasts.  Current customers can access their internal indicators and models using their own Prevedere API key.  

Read through this user guide to understand how to set up and configure a basic flow using the connector. Track feature additions, compatibility, limitations and API version updates with each release of the connector using the [Prevedere Connector Release Notes](https://github.com/Prevedere/prevedere-connector/blob/master/doc/release-notes.adoc). Review the connector operations and functionality using the [Technical Reference](https://github.com/Prevedere/prevedere-connector) alongside the [Demo Applications](https://www.mulesoft.com/exchange#!/?filters=Prevedere&sortBy=rank).

## Prerequisites

This document assumes that you are familiar with Mule, Anypoint Connectors, and Anypoint Studio Essentials. To increase your familiarity with Studio, consider completing an Anypoint Studio Tutorial. Documentation can be found at https://docs.mulesoft.com/.


## Requirements
### Hardware and Software Requirements

For hardware and software requirements, please visit the [Hardware and Software Requirements](https://docs.mulesoft.com/mule-user-guide/v/3.8/hardware-and-software-requirements) page.

### Compatibility

* Anypoint Studio 6.1 +
* Mule Server 3.8.1 EE +
* Java Development Kit 1.7 +

## Installing the Connector

You can install the connector in Anypoint Studio using the instructions in
[Installing a Connector from Anypoint Exchange](https://docs.mulesoft.com/mule-fundamentals/v/3.8/anypoint-exchange#installing-a-connector-from-anypoint-exchange).

### Upgrading from an Older Version

If you’re currently using an older version of the connector, a small popup appears in the bottom right corner of Anypoint Studio with an "Updates Available" message.

* Click the popup and check for available updates.
* Click the Connector version checkbox and click *Next* and follow the instructions provided by the user interface.
* *Restart* Studio when prompted.
* After restarting, when creating a flow and using the connector, if you have several versions of the connector installed, you may be asked which version you would like to use. Choose the version you would like to use.

Additionally, we recommend that you keep Studio up to date with its latest version.

## Configuring the Connector Global Element

To use the Prevedere Connector in your Mule application, you must configure a global Prevedere:Configuration element that can be used by the Prevedere Connector (read more about [Global Elements](https://docs.mulesoft.com/mule-fundamentals/v/3.8/global-elements)). The Prevedere Connector offers the following global configuration(s), requiring the following credentials:

| Field | Description |
| ----- | ----------- |
|*API Key* |Enter the API key associated with your account or use default demo id provided.

## Using the Connector

Outbound, inbound, streaming outbound  +

[NOTE]
See a full list of operations for any version of the connector link:[here].

### Connector Namespace and Schema

When designing your application in Studio, the act of dragging the connector from the palette onto the Anypoint Studio canvas should automatically populate the XML code with the connector *namespace* and *schema location*.

*Namespace:* `http://www.mulesoft.org/schema/mule/prevedere`
*Schema Location:* `http://www.mulesoft.org/schema/mule/prevedere/current/mule-prevedere.xsd`

**TIP**
If you are manually coding the Mule application in Studio's XML editor or other text editor, define the namespace and schema location in the header of your *Configuration XML*, inside the `<mule>` tag.

```
<mule xmlns="http://www.mulesoft.org/schema/mule/core"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:connector="http://www.mulesoft.org/schema/mule/connector"
      xsi:schemaLocation="
               http://www.mulesoft.org/schema/mule/core
               http://www.mulesoft.org/schema/mule/core/current/mule.xsd
               http://www.mulesoft.org/schema/mule/prevedere
               http://www.mulesoft.org/schema/mule/prevedere/current/mule-prevedere.xsd">

      <!-- put your global configuration elements and flows here -->

</mule>
```


### Using the Connector in a Mavenized Mule App

If you are coding a Mavenized Mule application, this XML snippet must be included in your `pom.xml` file.

```
<dependency>
	<groupId>org.mule.modules</groupId>
	<artifactId>prevedere-java-connector</artifactId>
	<version>1.0.0</version>
</dependency>
```

**TIP**
Inside the `<version>` tags, put the desired version number, the word `RELEASE` for the latest release, or `SNAPSHOT` for the latest available version. The available versions to date are:

## Usage
For more information about usage visit our documentation at [Prevedere User Manual](https://github.com/Prevedere/prevedere-connector/blob/master/doc/user-manual.adoc)

## Reporting Issues

We use GitHub:Issues for tracking issues with this connector. You can report new issues at this link https://github.com/Prevedere/Prevedere-Connector/issues.
