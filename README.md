# Swagger 2.x Demo

Since version 2.x, Swagger splits into many modules including the [core](https://github.com/swagger-api/swagger-core) and [Swagger UI](https://github.com/swagger-api/swagger-ui). 

The core part can be integrated into JAX-RS project such as RESTEasy 3.x provided with WildFly 11+. Note that, the core part can scan automatically JAX-RS resources but can also be configured with more fine-grained details. It will expose a JSON and YAML `servlet-context-path/openapi.json` or `servlet-context-path/openapi.yaml`.

## Swagger 2 Core Part

1. Declare to use the Maven dependencies of Swagger 2.2.0 (the latest version at the time of writing) in the POM as following:

```xml
<dependencies>
    <dependency>
      <groupId>io.swagger.core.v3</groupId>
      <artifactId>swagger-jaxrs2</artifactId>
      <version>2.2.0</version>
    </dependency>
    <dependency>
      <groupId>io.swagger.core.v3</groupId>
      <artifactId>swagger-jaxrs2-servlet-initializer-v2</artifactId>
      <version>2.2.0</version>
    </dependency>
</dependencies>
```

2. Create a YAML configuration `src/main/resources/openapi-configuration.yaml`

```yaml
resourcePackages:
  - au.com.infomedix.rest.swagger.resources
prettyPrint: true
cacheTTL: 0
openAPI:
  info:
    version: '1.0'
    title: Swagger Demo
    description: 'This is a demo Swagger'
    contact:
      email: 'admin@infomedix.com.au'
```

* The directive `resourcePackages` contains a list of all packages that contain JAX-RS resources to be scanned

## Swagger UI Part

Swagger UI is a HTML/CSS/JavaScript library that can parse the JSON/YAML outputs of the core part and display a nice UI to explore and interact with the JAX-RS resources.

1. Create an `swagger-initializer.js` adapted from [Swagger UI installation guide](https://github.com/swagger-api/swagger-ui/blob/master/docs/usage/installation.md) (see `src/main/webapp/swagger-initializer.js`). The current version of Swagger UI does not interpret correctly the URL of the JAX-RS server, hence we need [a hack](https://stackoverflow.com/a/71210264/339302) to adjust it to the correct URL.
2. Adjust the expected version of Swagger UI in `<version.org.swagger.ui>4.11.0</version.org.swagger.ui>`
3. The [maven-download-plugin](https://github.com/maven-download-plugin/maven-download-plugin) will download and unpack the Swagger UI release package with the specified version.
4. The Maven `maven-resources-plugin` will copy the Swagger prebuilt resources from `dist` excluding the `swagger-initializer.js` because we use our own custom version.

## Build and Deploy

Using normal Maven commands

```sh
# Just package, enough for local development and deploying from 'target'
mvn clean package

# Install into local Maven repo
mvn clean install

# Deploy to a certain Maven repo
mvn clean deploy
```

# References

* https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Getting-started
* https://github.com/swagger-api/swagger-ui/blob/master/docs/usage/installation.md
