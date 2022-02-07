# MobAPI


## Requirements

For building and running the MobAPI application, you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine.
One way is to execute the `main` method in the `src.main.java.com.api.hustle.admin.Application` class from your IDE.

The Application use the port 8080

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

Once the application runs you should see something like this

```
2022-02-07 16:08:56.223  INFO 27688 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2022-02-07 16:08:56.224  INFO 27688 --- [           main] com.rest.mobAPI.MobApiApplication        : Started Application in 13.559 seconds (JVM running for 14.583)
```

# - EndPoints MobAPI

## Get list of Objects

### Request

`GET /objeto/`

```shell
   http://localhost:8080/objeto/
```

### Response

```shell
  HTTP/1.1 200  
  GET /objeto/ HTTP/1.1
  Host: localhost:8080
  Content-Type: application/json
  Content-Length: 40

  [
    {
        "id": "ID_1643498765409-6746757307914139870",
        "nombre": "Luis",
        "accion": "Crear",
        "fechaCreacion": "29-01-2022 19:26"
    },
    {
        "id": "ID_1643498800456--1286265732584620131",
        "nombre": "Ana",
        "accion": "Crear",
        "fechaCreacion": "29-01-2022 19:26"
    },
    {
        "id": "ID_1643498828751--8709135764296025239",
        "nombre": "Daniel",
        "accion": "Crear",
        "fechaCreacion": "29-01-2022 19:27"
    }
]  

```

## Delete Object

### Request

`DELETE /objeto/{id}`

```shell    
    http://localhost:8080/objeto/{id}
```

### Response

```shell
DELETE /objeto/ID_1643498765409-6746757307914139870 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 40
```

## Create a new Object

### Request

`POST /objeto`

```shell
    http://localhost:8080/objeto/
```
```shell
{
"nombre":"Luis",
"accion":"Crear"
}

```

### Response

```shell
    POST /objeto HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    Content-Length: 40
{
    "id": "ID_1644266054589--7975186862850386780",
    "nombre": "Luis",
    "accion": "Crear",
    "fechaCreacion": "07-02-2022 16:34"
}

```

## Get Object by id

### Request

`GET /objeto/{id}`

```shell  
    http://localhost:8080/objeto/{id}
```

### Response

```shell
    GET /objeto/ID_1643498828751--8709135764296025239 HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    Content-Length: 40

[
    {
        "id": "ID_1643498828751--8709135764296025239",
        "nombre": "Daniel",
        "accion": "Crear",
        "fechaCreacion": "29-01-2022 19:27"
    }
]
```


## Replicar

## Restaurar

## Electronics References

- [Buenas-Practicas-Commits-Medium-Jorge Mendez Ortega](https://medium.com/@jmz12/buenas-pr%C3%A1cticas-para-commits-5eb4c86b9a47)

## Bibliographical References

- Tom Hombergs - Get Your Hands Dirty on Clean Architecture-Leanpub (2019).pdf

- (Robert C. Martin Series) Robert C. Martin - Clean Architecture_ A Craftsman’s Guide to Software Structure and Design-Prentice Hall (2017)-2.pdf


## MOBAPI Developers

* Ana Ibarra
* Daniel Jaspe
* Luis Aloisi



