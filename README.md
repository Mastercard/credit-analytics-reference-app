# Small Business Credit Analytics Reference App

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)


## Table of Contents
- [Overview](#overview)
- [Requirements](#requirements)
- [Frameworks/Libraries](#frameworks)
- [Integrating with OpenAPI Generator](#OpenAPI_Generator)
- [Configuration](#configuration)
- [Use-Cases](#use-cases)
- [Execute the Use-Cases](#execute-the-use-cases)
- [Service Documentation](#documentation)
- [API Reference](#api-reference)
- [Support](#support)
- [License](#license)

## Overview  <a name="overview"></a>
This is a reference application to demonstrate how the Small Business Credit Analytics(SBCA) API is used.
To call the API, a consumer key and .p12 file are required - they can be obtained by creating a project
on https://developer.mastercard.com - see the Configuration section below for detailed instructions.

## Requirements  <a name="requirements"></a>

- Java 11
- IntelliJ IDEA (or any other IDE)

## Frameworks/Libraries <a name="frameworks"></a>
- Apache Maven
- OpenAPI Generator

## Integrating with OpenAPI Generator <a name="OpenAPI_Generator"></a>

OpenAPI Generator generates API client libraries from OpenAPI Specs. It provides generators and library templates for supporting multiple languages and frameworks.
Check [Generating and Configuring a Mastercard API Client](https://developer.mastercard.com/platform/documentation/security-and-authentication/generating-and-configuring-a-mastercard-api-client/) to learn more about how to generate a simple API client for consuming APIs.

## Configuration <a name="configuration"></a>
1. Create an account on [Mastercard Developers](https://developer.mastercard.com/) if you don't have one already.
2. Once logged in, create a new project, add the ***Small Business Credit Analytics API*** to it, and click continue.
3. Click the button to generate and download your private signing key - a ```.p12``` file will be downloaded.
5. Copy the downloaded ```.p12``` file to the ```src/main/resources``` folder in your code.
6. Open ```src/main/java/com/mastercard/creditanalytics/api/ApiExamples.java``` and configure the default ApiClient creation logic:
  - ```apiBasePath```- This is the URL Path to the API endpoint, one of:
    - For the sandbox environment, https://sandbox.api.mastercard.com/small-business/credit-analytics/locations
    - For the production environment, https://api.mastercard.com/small-business/credit-analytics/locations
  - ```signingKeyPath```- Path to the key (*.p12) file on your machine. If you placed the file in your `src/main/resources` directory, your path might look like `./src/main/resources/*.p12` to run locally.
  - ```signingKeyAlias``` - The alias of your key. The default value for sandbox keys is ```keyalias```.
  - ```signingKeyPassword``` -  The password for your Keystore. The default value for sandbox projects is ```keystorepassword```.
  - ```consumerKey``` - This is your consumer key from the Sandbox Keys or Production Keys section of your project page.

## Use-Cases <a name="use-cases"></a>
1. `/matches` **Match a merchant's details to Mastercard's locations database** <br/>
   Use this endpoint to retrieve potential matches to the merchant's provided name and address metadata in Mastercard's database.

2. `/metrics/{location_id}` **Get performance metrics for a merchant** <br/>
   Use this endpoint to retrieve performance metrics for a matched merchant returned from /matches

More details can be found [here](https://developer.mastercard.com/small-business-credit-analytics/documentation/use-cases).


## Execute the Use-Cases   <a name="execute-the-use-cases"></a>
1. Run ```mvn clean install``` from the root of the project directory.
2. When the project builds successfully, you can run the following command to start the project: `java -jar target/creditanalytics-referenceapp-1.0.0.jar`
3. This will start the application and run through a set of examples exercising all the use cases mentioned above

## Service Documentation <a name="documentation"></a>

Small Business Credit Analytics documentation can be found [here](https://developer.mastercard.com/small-business-credit-analytics/documentation/).


## API Reference <a name="api-reference"></a>
The OpenAPI specification can be found [here](https://developer.mastercard.com/small-business-credit-analytics/documentation/api-reference/).

## Support <a name="support"></a>
Please email **advisors.help@mastercard.com** with any questions or feedback you may have.

## License <a name="license"></a>
<p>Copyright 2021 Mastercard</p>
<p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
the License. You may obtain a copy of the License at:</p>
<pre><code>   http://www.apache.org/licenses/LICENSE-2.0
</code></pre>
<p>Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
specific language governing permissions and limitations under the License.</p>
