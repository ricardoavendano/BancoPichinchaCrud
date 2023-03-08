# pruebaBancoPichincha
Prueba back crud

Ricardo Avendaño Casas

## Generalidades tecnológicas
1. Java 11
2. Maven
3. JUnit
4. Base de datos H2
5. Swagger
6. SpringBoot
7. Manejo de errores y excepciones
8. Uso Docker compose

## Para ejecutar el proyecto localmente (sin Docker)se deben realizar los siguientes pasos
1. Descargar fuente de github: git clone git clone https://github.com/ricardoavendano/BancoPichinchaCrud.git
2. Ir al directorio donde se encuentra el fuente y crear jar: mvn clean install (se crea la carpeta target)
3. Ir al directorio donde se encuentra el fuente y dirigirse a la carpeta target; por línea de comandos ejecutar jar: java -jar prueba-0.0.1-SNAPSHOT.jar

## Para ejecutar el proyecto con Docker compuse se deben realizar los siguientes pasos
1. Descargar fuente de github: git clone git clone https://github.com/ricardoavendano/BancoPichinchaCrud.git
2. Ir al directorio donde se encuentra el fuente y crear jar: mvn clean install (se crea la carpeta target)
3. Ejecutar para levantar la imagen Docker
```bash
docker-compose up
```
4. Ejecutar para bajar la imagen Docker
```bash
docker-compose down
```

5. La aplicación ya se encuentra desplegada localmente en la url (http://localhost:8080)
6. Ingreso a la BD H2
    Driver Class: org.h2.Driver
    url: http://localhost:8080/pruebaBPichincha/h2-console/login.jsp
    JDBC URL: jdbc:h2:mem:pruebaBPichincha
    User name: pruebaBPichincha
    Password: pruebaBPichincha
    
    Tablas: PERSONA, CLIENTE, CUENTA, MOVIMIENTOS
7. Documentacion endpoints con Swagger
    http://localhost:8080/pruebaBPichincha/swagger-ui.html#/


## Collección Postman

```bash
{
    "info": {
        "_postman_id": "1d8e251b-7bac-4b48-8eed-994014b832cf",
        "name": "BancoPichincha",
        "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
    },
    "item": [
        {
            "name": "Cuentas",
            "item": [
                {
                    "name": "cuentas/crearCuenta",
                    "request": {
                        "method": "POST",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"estadoCuenta\": true,\n    \"identificacionCliente\": 123456,\n    \"numeroCuenta\": 222222,\n    \"saldoInicialCuenta\": 500,\n    \"tipoCuenta\": \"Ahorros\"\n}"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/cuentas/crearCuenta",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "cuentas",
                                "crearCuenta"
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "cuentas/actualizarEstado",
                    "request": {
                        "method": "PATCH",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "888888"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/cuentas/actualizarEstado?estado=true",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "cuentas",
                                "actualizarEstado"
                            ],
                            "query": [
                                {
                                    "key": "estado",
                                    "value": "true"
                                }
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "cuentas/actualizarCuenta",
                    "request": {
                        "method": "PUT",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"estadoCuenta\": false,\n    \"identificacionCliente\": 234567,\n    \"numeroCuenta\": 888888,\n    \"saldoInicialCuenta\": 100,\n    \"tipoCuenta\": \"Ahorros\"\n}"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/cuentas/actualizarCuenta",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "cuentas",
                                "actualizarCuenta"
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "cuentas/eliminarCuenta",
                    "request": {
                        "method": "DELETE",
                        "header": [],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/cuentas/eliminarCuenta?numeroCuenta=888888",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "cuentas",
                                "eliminarCuenta"
                            ],
                            "query": [
                                {
                                    "key": "numeroCuenta",
                                    "value": "888888"
                                }
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "cuentas/consultarCuenta",
                    "request": {
                        "method": "GET",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            }
                        ],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/cuentas/consultarCuenta?numeroCuenta=888888",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "cuentas",
                                "consultarCuenta"
                            ],
                            "query": [
                                {
                                    "key": "numeroCuenta",
                                    "value": "888888"
                                }
                            ]
                        }
                    },
                    "response": []
                }
            ]
        },
        {
            "name": "Clientes",
            "item": [
                {
                    "name": "clientes/consultarCliente",
                    "request": {
                        "method": "GET",
                        "header": [],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/clientes/consultarCliente?identificacion=12345",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "clientes",
                                "consultarCliente"
                            ],
                            "query": [
                                {
                                    "key": "identificacion",
                                    "value": "12345"
                                }
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "clientes/crearCliente",
                    "request": {
                        "method": "POST",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"contrasena\": \"contrasena_nueva\",\n    \"direccion\": \"direccion 4\",\n    \"edad\": 32,\n    \"estado\": true,\n    \"genero\": \"Masculino\",\n    \"identificacion\": 456789,\n    \"nombre\": \"Ricardo Avendano\",\n    \"telefono\": 98765432\n}"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/clientes/crearCliente",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "clientes",
                                "crearCliente"
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "clientes/actualizarDireccion",
                    "request": {
                        "method": "PATCH",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "123456"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/clientes/actualizarDireccion?direccion=nueva direccion",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "clientes",
                                "actualizarDireccion"
                            ],
                            "query": [
                                {
                                    "key": "direccion",
                                    "value": "nueva direccion"
                                }
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "clientes/actualizarCliente",
                    "request": {
                        "method": "PUT",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"contrasena\": \"contrasena_nueva\",\n    \"direccion\": \"direccion 4\",\n    \"edad\": 32,\n    \"estado\": true,\n    \"genero\": \"Masculino\",\n    \"identificacion\": 123456,\n    \"nombre\": \"Ricardo Avendano\",\n    \"telefono\": 98765432\n}"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/clientes/actualizarCliente",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "clientes",
                                "actualizarCliente"
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "clientes/eliminarCliente",
                    "request": {
                        "method": "DELETE",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            }
                        ],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/clientes/eliminarCliente?identificacion=123456",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "clientes",
                                "eliminarCliente"
                            ],
                            "query": [
                                {
                                    "key": "identificacion",
                                    "value": "123456"
                                }
                            ]
                        }
                    },
                    "response": []
                }
            ]
        },
        {
            "name": "Movimientos",
            "item": [
                {
                    "name": "movimientos/consultarMovimiento",
                    "request": {
                        "method": "GET",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            }
                        ],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/movimientos/consultarMovimiento?numeroCuenta=0",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "movimientos",
                                "consultarMovimiento"
                            ],
                            "query": [
                                {
                                    "key": "numeroCuenta",
                                    "value": "0"
                                }
                            ]
                        }
                    },
                    "response": []
                },
                {
                    "name": "movimientos/crearMovimiento",
                    "request": {
                        "method": "POST",
                        "header": [
                            {
                                "key": "accept",
                                "value": "*/*"
                            },
                            {
                                "key": "Content-Type",
                                "value": "application/json"
                            }
                        ],
                        "body": {
                            "mode": "raw",
                            "raw": "{\n    \"numeroCuenta\": 0,\n    \"valor\": 0\n}"
                        },
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/movimientos/crearMovimiento",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "movimientos",
                                "crearMovimiento"
                            ]
                        }
                    },
                    "response": []
                }
            ]
        },
        {
            "name": "Reportes",
            "item": [
                {
                    "name": "reportes/consultarMovimientoFecha",
                    "request": {
                        "method": "GET",
                        "header": [],
                        "url": {
                            "raw": "http://localhost:8080/pruebaBPichincha/reportes/consultarMovimientoFecha?numeroCuenta=999999&fechaInicial=2023-03-01&fechaFinal=2023-03-07",
                            "protocol": "http",
                            "host": [
                                "localhost"
                            ],
                            "port": "8080",
                            "path": [
                                "pruebaBPichincha",
                                "reportes",
                                "consultarMovimientoFecha"
                            ],
                            "query": [
                                {
                                    "key": "numeroCuenta",
                                    "value": "999999"
                                },
                                {
                                    "key": "fechaInicial",
                                    "value": "2023-03-01"
                                },
                                {
                                    "key": "fechaFinal",
                                    "value": "2023-03-07"
                                }
                            ]
                        }
                    },
                    "response": []
                }
            ]
        }
    ]
}
```

