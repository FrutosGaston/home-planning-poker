# Home Planning Poker

Repositorio frontend:
https://github.com/FrutosGaston/home-poker-planning-ui

Esta basado en el principio de Clean Architecture.

![alt text](docs/clean.png)

## Estructura de paquetes

Se definió la siguiente taxonomía de paquetes:

   * **application:** Encapsula toda la lógica de negocio y el modelo del dominio.
        * **domain:** Contiene entidades del dominio. Representa el nucleo de toda la aplicación.
        * **usescases:** Abstracción de los casos de uso del sistema. Contiene además la definición de los puertos y excepciones.
   * **adapters:** Representa la capa de adaptadores (como su nombre indica) que se conectarán en los puertos expuestos por el sistema
   * **config:** Capa transversal a toda la aplicación que contendrá las distintas configuraciones y aspectos del bff.
        
![alt text](docs/packages.png)


## Java Version
La version que java que se va a utilizar es la 11.0.10 basada en el OpenJDK.

## Swagger
### Swagger json
http://localhost:8080/api/v2/api-docs

### Swagger UI
http://localhost:8080/swagger-ui.html

## Tasks
### Build docker y push al registry

Ejecutar el siguiente task haciendo uso del gradle wrapper.

```

```

### Jacoco
```
./gradlew jacocoTestReport
```