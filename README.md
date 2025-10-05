# Practica 04: RockBuster

## Equipo: SQLazo
- [César Becerra Valencia (322064287)](#César)
- [Victor Abraham Sánchez Morgado (322606003)](#Victor)
- [José Luis Cortes Nava (322115437)](#Luis)

Este proyecto es una solución a la práctica 04 de **Modelado y Programación** de la Facultad de Ciencias (UNAM). El objetivo es implementar un sistema para una empresa llamada RockBuster la cual ofrece películas para rentar a sus clientes, además de haber absorbido una cadena de tiendas llamada "Mixdown" la cual ofrece discos musicales a sus clientes. Los patrones de diseño trabajados en esta práctica son el patrón Adapter y el patrón Composite.

## Dos maneras de compilar y ejecutar este proyecto:
### Con JDK

Prerrequisitos:

Este proyecto se puede compilar y ejecutar si se tiene instalado JDK (Java Development Kit) versión 17 o superior.

Cómo compilar y ejecutar:

Puedes compilar y ejecutar el programa directamente desde la terminal usando los comandos `javac` y `java`.

**Asegúrate de estar en el directorio raíz del proyecto (`Practica03_SQLazo/`) antes de ejecutar los siguientes comandos.**

1. Compilación

El siguiente comando compilará todos los archivos `.java` que se encuentran en el directorio `src/` y dejará los archivos `.class` compilados en un nuevo directorio llamado `out/`.

```bash
javac -d out **/*.java
```

* **`javac`**: Es el compilador de Java.
* **`-d out`**: Le indica al compilador que coloque los archivos compilados (`.class`) en una carpeta llamada `out`.
* **`**/*.java`**: Indica que se deben compilar todos los archivos .java que se encuentren haciendo recursión en los subdirectorios.

2. Ejecución

Una vez compilado, puedes ejecutar el programa con el siguiente comando:

```bash
java -cp out mx.unam.ciencias.myp.rockbuster.catalogo.Main 
```

* **`java`**: Es la Máquina Virtual de Java (JVM) que ejecuta el código.
* **`-cp out`** (`-cp` es una abreviatura de `--class-path`): Le indica a la JVM que busque los archivos `.class` en el directorio `out`.
* **`java -cp out mx.unam.ciencias.myp.rockbuster.catalogo.Main`**: Es el nombre completamente calificado de la clase que contiene el método `main` que queremos ejecutar.

### Con Docker

Prerrequisitos:

Para ejecutar el programa de java con Docker es necesario tener instalado Docker Desktop y tener abierta la aplicación en todo momento.
Este es el link de Google para instalarlo en Ubuntu: https://docs.docker.com/desktop/setup/install/linux/ubuntu/

1. Descargar la imagen

El comando para descargar la imagen estando en el directorio raíz de la práctica es el siguiente:

```bash
docker build -t rockbuster .
```

* **`docker build`**: Indica a Docker que debe construir una imagen en base al dockerfile que se encuentra en la raíz de la práctica.
* **`-t rockbuster`**: Etiqueta la imagen con el nombre rockbuster para no tener que usar un ID predeterminado.
* **`.`**: Indica a Docker que los archivos a copiar y el dockerfile se encuentran en el directorio actual.

2. Ejecutar el contenedor

Este comando ejecuta un contenedor basado en la imagen que construimos en el paso anterior:

```bash
docker run --rm -it rockbuster
```

* **`docker run`**: Da la instrucción a Docker de crear y ejecutar un contenedor.
* **`rockbuster`**: El nombre de la imagen en la que se basa el contenedor.
* **`--rm`**: Borra el contenedor al terminar de ejecutarlo.
* **`-it`**: En realidad son dos banderas que indican que el contenedor debe ser interactivo con lo que ponemos en la terminal para que nuestro código acepte las entradas del Scanner y que debe crear un entorno TTY (un entorno como el de la terminal, con el que podamos interactuar en tiempo real).

## Anotaciones sobre la implementación de los patrones

Los patrones cumplen objetivos como extensibilidad de nuestro código y desacoplamiento de las clases involucradas. Estos son los patrones utilizados durante la implementación y en qué consisten:

Adapter: Dado que tenemos una interfaz Product que define el comportamiento que debe tomar un producto de la tienda rockbuster; al absorber la compañía Mixdown nos es conveniente utilizar este patrón para que los discos que esta compañía ofrecía ahora puedan ser productos de rockbuster, sin tener que cambiar las propiedades ni el comportamiento de estos discos. El patrón Adapter consiste en crear una clase distinta a una ya dada, pero que esta implemente una interfaz que busca definir un comportamiento. Esta nueva clase debe tener una instancia de la clase dada anteriormente, y así implementar los métodos marcados por la interfaz, mandando a llamar métodos de la instancia y agregando cosas en los métodos definidos por la interfaz si es necesario. Con esto, lo que se tenía anteriormente en una clase con funcionalidad distinta a lo que se busca por la interfaz, ya puede ser tratado de acuerdo con como se busca que funcione un objeto de una clase que implementa la interfaz. En el caso específico de nuestro código, la clase ya dada era la clase Album, y la nueva clase que implementa la interfaz es AlbumAdapter, la cual tiene dentro una instancia privada de la clase Album.

Composite: Este patrón lo que busca es tener objetos de un tipo que dentro puedan tener objetos del mismo tipo, y tratarlos a todos por igual. La estructura de Composite se puede ver como un árbol ya que las hojas son los objetos que dentro ya no tienen nada y los nodos compuestos son los que pueden tener dentro hojas o más nodos compuestos. En el patrón, las hojas y los nodos compuestos implementan una interfaz base, la cual tiene métodos que pueden realizar ambos para que el cliente pueda llamar a esos métodos sin tener que preocuparse por si está usando una hoja o un nodo compuesto. Este patrón nos permite recorrer y llamar métodos de estructuras jerárquicas de manera recursiva. En nuestro código lo utilizamos para tres tipos de objetos que implementan la interfaz Producto:
- Movie: Es un objeto película con propiedades como nombre, creador, género, duración, descripción y costo. Son las hojas de nuestro patrón.
- Saga: Es un conjunto de películas. Una saga es un nodo compuesto en nuestro patrón, el cual puede contener tanto películas como más sagas.
- AlbumAdapter: Convierte a los discos en nodos hoja de nuestro patrón.

