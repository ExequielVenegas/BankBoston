# Proyecto Bank Boston

Este proyecto es una aplicación Java de gestión de cuentas bancarias para Bank Boston, una institución financiera en crecimiento en la Región Metropolitana. La aplicación permite a los clientes realizar diversas operaciones bancarias y gestionar sus datos personales.

## Funcionalidades

El sistema ofrece las siguientes funcionalidades principales a través de un menú interactivo:

* **Registrar Cliente**: Permite registrar los datos de una nueva persona, incluyendo la creación y asignación de una cuentaBancaria corriente. Se valida que el RUT tenga entre 11 y 12 caracteres (incluyendo puntos y guion). Las cuentas corrientes se inicializan en 0 pesos.
* **Ver Datos Cliente**: Permite visualizar todos los datos personales de un cliente registrado, incluyendo la información de su cuentaBancaria.
* **Depositar**: Permite abonar un monto a la cuentaBancaria del cliente, aumentando su saldo. No se permiten montos menores o iguales a cero.
* **Girar**: Permite retirar dinero de una cuentaBancaria, lo que provoca un descuento en el saldo. Se requiere que el cliente tenga un saldo mayor que cero para realizar el giro, y no se permiten montos menores o iguales a cero ni giros que excedan el saldo actual de la cuentaBancaria.
* **Consultar Saldo**: Muestra el saldo actual de la cuentaBancaria del cliente.
* **Salir**: Finaliza la aplicación.

## Cómo Ejecutar el Proyecto

Para ejecutar este proyecto, necesitarás un entorno de desarrollo Java (JDK) y Apache NetBeans (o cualquier IDE de Java).

1.  **Clonar/Descargar el Repositorio**: Si está en un repositorio, clónalo. De lo contrario, asegúrate de tener todos los archivos `.java` en la estructura de paquetes correcta.
2.  **Abrir en NetBeans (o tu IDE preferido)**:
    * Abre NetBeans.
    * Selecciona `File` -> `Open Project...` y navega hasta la carpeta raíz del proyecto.
    * NetBeans debería reconocerlo como un proyecto Java.
3.  **Compilar y Ejecutar**:
    * Haz clic derecho en la clase `Main.java` y selecciona `Run File` (o `Run Project` si quieres ejecutar todo el proyecto).
    * La aplicación se ejecutará en la consola de NetBeans (o la terminal).

## Requerimientos

* Java Development Kit (JDK) 17 o superior.
* Apache NetBeans IDE (recomendado por el enunciado del proyecto).

Este proyecto fue desarrollado por:

* **FRANCIS ALVAREZ** (`@francisAlvarezFigueroa` en GitHub)
* **EXEQUIEL VENEGAS** (`@ExequielVenegas` en GitHub)