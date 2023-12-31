# Aplicación de Gestión de Precios

Esta es una aplicación realizada para una prueba técnica la cual implementa una arquitectura hexagonal (también conocida como puertos y adaptadores) en Java para gestionar precios.


# Prueba Técnica

La prueba consiste en crear una aplicacion que acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:


-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)

-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)

-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

# Tabla PRICES
```bash
- BRAND_ID            START_DATE                                 END_DATE                        PRICE_LIST               PRODUCT_ID        PRIORITY                     PRICE           CURR

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR

1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR

1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR

1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR
```


Campos:



BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).

START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.

PRICE_LIST: Identificador de la tarifa de precios aplicable.

PRODUCT_ID: Identificador código de producto.

PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

PRICE: precio final de venta.

CURR: iso de la moneda.


## Tech Stack

- Java OpenJDK 17
- Spring Boot 2.7.13
- Maven 4.0.0-alpha-7
- H2 - inmem DB
- JUnit
- Mockito

## Arquitectura Hexagonal

La arquitectura hexagonal se basa en el principio de la inversión de dependencias, En esta aplicación, las capas principales incluyen:

1. **Capa de Dominio**: Contiene las entidades, reglas de negocio y lógica central de la aplicación. En esta capa, encontrarás la entidad `Price` que representa un precio.

2. **Capa de Aplicación**: Contiene los casos de uso y la lógica de aplicación. En esta capa, se encuentra la clase `PriceByPriorityService`, que implementa la lógica para obtener precios por prioridad.

3. **Capa de Infraestructura**: Contiene implementaciones concretas de componentes como el acceso a la base de datos y controladores REST. Aquí, utilizamos Spring Boot y JPA para interactuar con la base de datos, y controladores REST para manejar las solicitudes HTTP.

    **Adaptadores**: En esta capa se adaptan los componentes de infraestructura a las interfaces definidas en la capa de aplicación. Aquí encontrarás el mapeo entre las entidades de la base de datos y las entidades del dominio.

## Base de datos
Se cuenta con una base de datos H2 embeded en memoria, la cual cuenta con archivos de creacion de tabla PRICE y la inicializacion de los datos de ejemplo.
Archivos `schema.sql` y `data.sql`



## Pruebas

La aplicación incluye pruebas unitarias y de integración para garantizar la calidad y la funcionalidad del código. Puedes ejecutar estas pruebas utilizando JUnit 5 y Mockito.

- Pruebas Unitarias: Se centran en probar componentes individuales de la aplicación.
- Pruebas de Integración: Verifican la interacción entre las diferentes capas de la aplicación. Estas las podra ejecutar en el modulo de `infrastruture`

## API
  Una vez ejecutada la aplicacion podras hacer pruebas en el endpoint `http://localhost:8080/price/`
  Incluyendo los QueryParams `startDate, productId, brandId`
  #Ejemplo GetRequest
  En postman http://localhost:8080/price?startDate=2020-06-14-19.00.00&productId=35455&brandId=1
  #Response
  ```
{
    "productId": 35455,
    "brandId": 1,
    "applicableRateCode": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.5
}

```

## Ejecución

Para ejecutar la aplicación, puedes usar Spring Boot. La configuración de la base de datos en el archivo `application.yml` y ejecuta la aplicación con:

```bash
mvn clean install ->> 
mvn run 
```
##Estructura de carpetas
```bash
.
├── README.md
├── application
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── com
│       │           └── edwindev08
│       │               └── price
│       │                   ├── mapper
│       │                   │   └── PriceDtoMapper.java
│       │                   └── query
│       │                       └── PriceByIdsAndDateHandler.java
│       └── test
│           └── java
│               └── com
│                   └── edwindev08
│                       └── price
│                           ├── builders
│                           │   ├── ChildObjectBuilder.java
│                           │   └── PriceAppBuilder.java
│                           ├── mapper
│                           └── query
│                               └── PriceByIdsAndDateHandlerTest.java
├── domain
│   ├── pom.xml
│   └── src
│       ├── main
│       │   └── java
│       │       └── com
│       │           └── edwindev08
│       │               └── price
│       │                   ├── model
│       │                   │   ├── dto
│       │                   │   │   └── PriceDto.java
│       │                   │   ├── entity
│       │                   │   │   └── Price.java
│       │                   │   └── exception
│       │                   │       └── PriceNotFoundException.java
│       │                   ├── port
│       │                   │   ├── dao
│       │                   │   │   └── PriceDao.java
│       │                   │   └── repository
│       │                   │       └── PriceRepositoryPort.java
│       │                   └── service
│       │                       └── PriceByPriorityService.java
│       └── test
│           └── java
│               └── com
│                   └── edwindev08
│                       └── price
│                           ├── builders
│                           │   ├── ChildObjectBuilder.java
│                           │   └── PriceDomainBuilder.java
│                           ├── dao
│                           ├── model
│                           │   └── entity
│                           │       └── PriceTest.java
│                           └── service
│                               └── PriceByPriorityServiceTest.java
├── infrastructure
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── edwindev08
│       │   │           └── price
│       │   │               ├── PriceQueryServiceApplication.java
│       │   │               ├── adapter
│       │   │               │   ├── entity
│       │   │               │   │   ├── ApiError.java
│       │   │               │   │   └── PriceEntity.java
│       │   │               │   ├── jpa
│       │   │               │   │   ├── dao
│       │   │               │   │   │   └── PriceH2Dao.java
│       │   │               │   │   └── repository
│       │   │               │   │       └── PriceJpaAdapterRepository.java
│       │   │               │   └── mapper
│       │   │               │       └── PriceDboMapper.java
│       │   │               ├── config
│       │   │               │   └── PriceBeans.java
│       │   │               └── rest
│       │   │                   ├── adviser
│       │   │                   │   └── ExceptionControllerAdvice.java
│       │   │                   └── controller
│       │   │                       └── PriceQueryController.java
│       │   └── resources
│       │       ├── application.yml
│       │       └── db
│       │           ├── data.sql
│       │           └── schema.sql
│       └── test
│           └── java
│               └── com
│                   └── edwindev08
│                       └── price
│                           ├── SharedTest.java
│                           ├── adapter
│                           │   ├── entity
│                           │   │   └── PriceEntityTest.java
│                           │   ├── jpa
│                           │   │   ├── dao
│                           │   │   │   └── PriceH2DaoTest.java
│                           │   │   └── repository
│                           │   └── mapper
│                           │       └── PriceDboMapperTest.java
│                           ├── builders
│                           │   ├── ChildObjectBuilder.java
│                           │   └── PriceDboBuilder.java
│                           ├── integrationTest
│                           │   └── PriceQueryControllerTest.java
│                           └── rest
│                               └── controller
│                                   └── PriceQueryControllerTest.java
└── pom.xml

```
