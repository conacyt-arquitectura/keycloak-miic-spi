Keycloak MIIC SPI
=================

Proyecto basado en: Source: <https://github.com/keycloak/keycloak-quickstarts>  

Requerimientos del Sistema
--------------------------

- Se necesita que una instancia de <span>Keycloak</span> se encuentre en ejecución.
- Se requiere Java 8.0 (Java SDK 1.8) y Maven 3

Construcción y despliegue
-------------------------------


# Para instalar controlador de PostgreSQL:

```bash
mvn -Padd-postgresql-datasource install
```

```bash
mvn -Padd-xa-postgresql-datasource install
```

# Para instalar controlador de Oracle:

```bash
mvn -Padd-oracle-datasource install
```

#### Para instalar controlador de Oracle en modo XA:

```bash
mvn -Padd-xa-datasource install
```
 
# Para desplegar el componente:

```bash
mvn clean install wildfly:deploy
```

Para administrar el servidor wildfly:

```bash
http://localhost:9990
```

Para administrar el servidor keycloak:

```bash
http://localhost:9080
```


```bash
mvn -Padd-xa-postgresql-datasource install
```
