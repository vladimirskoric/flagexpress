# DCTx FLAG Backend

The DCTx FLAG Backend is the application supporting the Mobile and the
Dashboard apps.

The projects uses Quarkus and follows the Explicit Architecture as a guideline. For details:

- [Quarkus](https://quarkus.io)
- [Explicit Architectrure](https://herbertograca.com/tag/explicit-architecture/)

The API is documented using OpenAPI v3.x:

- [OpenAPI Spec](src/main/resources/META-INF/openapi.yaml).

## Documents

The [WIKI](https://gitlab.com/dctx/flag/flag-user-stories/-/wikis) will contain all official documents of this project.

TODO: Add links for other resources for now

## Getting Started

### Prerequisite

- JDK 11+
- Maven 3.6.2+ (or use the maven wrapper included)
- Docker
- Docker Compose
- kubectl
- helm

### Cloning the repository

```
$ git clone git@gitlab.com:dctx/flag/flag-backend.git
```

### Build

```
$ ./mvnw quarkus:dev
```

### Configuration

Configuration is via the `application.properties` file. These can be overridden
using environment.

// TODO: Add env vars table here

### Starting the Services

The application depends on different external applications and are conveniently
added in a docker-compose file. To spin up the dependencies:

```
$ dos2unix docker-files/init-dbs.sh

$ docker-compose up -d
```

The containers can be stopped using:

```
$ docker-compose stop
```

Or completely removed using:

```
$ docker-compose down
```

Note that volumes are not configured yet in the docker-compose spec and bringing
down the containers will remove all data.

#### Keycloak

Keycloak is the IAM used by this application. On service start, the Keycloak
instance will not have a realm configured yet.

The [flag-realm.json](docker-files/flag-realm.json) should be imported by
creating a new realm inside keycloak's user interface.

Keycloak is accessible via: [http://localhost:8180/auth](http://localhost:8180/auth)

Default credentials are:

```
username: keycloak
password: keycloak
```

##### Uploading the sample frontend-dev Client ID and Secret
1. Navigate to __Configure > Clients__
2. Click __Create__
3. Click __Select file__
4. Browse and select `docker-files/frontend-dev.json`
4. Click __Save__

In the "Credentials" tab, the "Secret" is already pre-generated. 

##### Creating another Client ID and Secret 
    Configure > Clients > Create:
        Client ID: frontend-prod
        Client Protocol: openid-connect
        Access Type: confidential
        Standard Flow Enabled: Off
        Service Accounts Enabled: On
    Then, save.

In the "Credentials" tab, the "Secret" is already pre-generated. 

##### Environment Variables for FLAG.

Set these environment variables that will be used by FLAG.

For example in Linux,

    export KEYCLOAK_API = http://localhost:8180/auth
    export KEYCLOAK_CLIENT_ID = flag-backend
    export KEYCLOAK_CLIENT_SECRET = 214ebb80-fdc7-4f7f-85f1-1613787c146f
    export QUARKUS_OAUTH2_CLIENT_ID = flag-backend
    export QUARKUS_OAUTH2_CLIENT_SECRET = 214ebb80-fdc7-4f7f-85f1-1613787c146f
    export QUARKUS_OAUTH2_INTROSPECTION_URL = http://localhost:8180/auth/realms/flag/protocol/openid-connect/token/introspect

##### Using the Client ID and Secret

The app should call the endpoint {{base_url}}/auth/client/token to obtain the "access_token", which should then be supplied in the header as a Bearer Token for the other endpoints.
See Swagger for the updated usage structure. 

##### Enabling role-based access for basic users (username + password)

###### Creating the role

    Configure > Roles > Add Role:
        Role Name: reports_viewer
    Then, save.

###### Enabling roles in the generated access_token

    Configure > Clients > flag-backend > Mappers > Add Builtin:
        Search: groups
        Select checkbox
    Then, click "Add Selected".

###### Adding the basic user, and assigning the role

    Manage > Users > Add user:
        Username: test_user
        User Enabled
        Then, save.

        > Credentials:
        Fill in passwords & confirmation.
        Temporary: Off.
        Then, click "Set Password".

        > Role Mappings:
        Select "reports_viewer".
        Then, click "Add selected".

###### Using the username and password

The app should call the endpoint {{base_url}}/auth/login to obtain the "access-token", which should then be supplied in the header as a Bearer Token for the other endpoints such as the GET /reports endpoint.
See Swagger for the updated usage structure. 

#### Postgres

A postgres database is included in the docker-compose spec. This postgres
instance contains two databases with the following credentials:

Keycloak DB:

```
host: localhost
port: 5432
database: keycloak
username: keycloak
password: keycloak
```

FLAG DB:

```
host: localhost
port: 5432
database: flag
username: flag
password: flag
```

Environment Variables:

```
QUARKUS_DATASOURCE_USERNAME=flag
QUARKUS_DATASOURCE_PASSWORD=flag
QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/flag
```

#### Kafka and Zookeper

Kafka will be used to perform asynchronous notifications. It depends on
Zookeeper, which acts as a centralized service to provide synchronization
within distributed systems.

Kafka:

```
ALLOW_PLAINTEXT_LISTENER = yes
KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper = 2181
KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP = PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
KAFKA_CFG_LISTENERS = PLAINTEXT://:9092,PLAINTEXT_HOST://:29092
KAFKA_CFG_ADVERTISED_LISTENERS = PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
```

- `ALLOW_PLAINTEXT_LISTENER`: Allow to use the PLAINTEXT listener
- `KAFKA_CFG_ZOOKEEPER_CONNECT`: Comma separated host:port pairs,
  each corresponding to a Zookeeper Server
- `KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP`: Maps the security protocol
  of the listeners
- `KAFKA_CFG_LISTENERS`: Interfaces that Kafka binds to
- `KAFKA_CFG_ADVERTISED_LISTENERS`: How clients can connect

Zookeeper:

```
ALLOW_ANONYMOUS_LOGIN = yes
```

- `ALLOW_ANONYMOUS_LOGIN`: Allows anonymous users to connect

#### MinIO

For object storage, we will be using MinIO to easily plug other cloud object
storage systems such as Amazon S3, Azure Object Storage, etc.

To override MinIO's auto-generated keys, you may pass secret and access keys
explicitly as environment variables. MinIO server also allows regular strings as access and secret keys:

```
MINIO_ACCESS_KEY = minio
MINIO_SECRET_KEY = minio123
```

A directory gets created in the container filesystem at the time of container start
but all the data is lost after container exits. To create a MinIO container with persistent storage,
uncomment the following in the configuration:

```
- ./docker-files/minio/data:/data
```

##### Setting-up and using a bucket

Go to the [MinIO Browser](!http://localhost:9000/minio) and login with your configured credentials.
At the bottom right corner, there is a plus (+) button that will allow you to create a bucket, for example "testbucket".

Set the following environment variables based on your configurations:

    APP_OBJECT_STORAGE_ENDPOINT=http://localhost:9000
    APP_OBJECT_STORAGE_BUCKET_NAME=testbucket
    APP_OBJECT_STORAGE_ACCESS_KEY=minio
    APP_OBJECT_STORAGE_SECRET_KEY=minio123

#### Using AWS S3

Replace the above values with your S3 configurations. For example:

    APP_OBJECT_STORAGE_ENDPOINT=https://s3.amazonaws.com
    APP_OBJECT_STORAGE_BUCKET_NAME=flaguploads
    APP_OBJECT_STORAGE_ACCESS_KEY=YOURACCESSKEYID
    APP_OBJECT_STORAGE_SECRET_KEY=YOURSECRETACCESSKEY

### Semaphore API
Please set the environment variable for the key.
For example in Linux:
```
export SEMAPHORE_API_KEY = PLEASEASKFORTHISBUTDONTCOMMIT
```

## Development

Please check [CONTRIBUTING.md](CONTRIBUTING.md) first.

### Code Quality
Check if your code adheres to the project's coding standard:
```
$ ./mvnw checkstyle:check
$ ./mvnw spotbugs:spotbugs
$ ./mvnw verify
```

## Deployment

Set the environment variables described in this document with the "actual" values that you will use. 
Sample values can also be found `.env.sample`

To create an executable that will run in a container, use the following:

```
./mvnw package -Dnative -Dquarkus.native.container-build=true
```
A native executable will be present in `target/.`

The produced executable will be a 64 bit Linux executable, so depending on your operating system it may no longer be runnable. However, it’s not an issue as we are going to copy it to a Docker container. Note that in this case the build itself runs in a Docker container too, so you don’t need to have GraalVM installed locally.

By default, the native executable will be generated using the `quay.io/quarkus/ubi-quarkus-native-image:19.3.1-java11` Docker image.

For more details: https://quarkus.io/guides/building-native-image#creating-a-container

### Packaging

### Installation
