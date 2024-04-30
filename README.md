# Gestión Clínica

Este proyecto consiste en una aplicación de gestión clínica desarrollada en Java. Proporciona funcionalidades para administrar pacientes, médicos y citas médicas.

## Contenido




- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Instrucciones de Compilación y Ejecución](#instrucciones-de-compilación-y-ejecución)
- [Formato de Datos de Entrada](#formato-de-datos-de-entrada)
- [Consideraciones Técnicas](#consideraciones-técnicas)

## Descripción del Proyecto

Este proyecto consiste en una aplicación de consola que permite gestionar datos relacionados con una clínica médica. Los datos se cargan desde un archivo de texto al inicio de la ejecución y se almacenan en estructuras de datos en memoria. La aplicación proporciona un menú interactivo que permite realizar diversas operaciones, como listar pacientes por rango de edad, mostrar pacientes que tuvieron cita en una fecha específica, mostrar el número de seguridad social de un paciente y listar todos los pacientes asignados a un médico específico.

## Estructura del Proyecto

El proyecto consta de los siguientes archivos:

- **GestionClinica.java**: Contiene la clase principal que controla la ejecución del programa y proporciona el menú de opciones.
- **Paciente.java**: Define la clase Paciente, que representa a un paciente con atributos como nombre, edad y número de seguridad social.
- **Medico.java**: Define la clase Medico, que representa a un médico con atributos como nombre y cédula.
- **Cita.java**: Define la clase Cita, que representa una cita médica con atributos como fecha, médico y paciente.

## Requisitos

- Java Development Kit (JDK) instalado en el sistema.

## Instrucciones de Compilación y Ejecución

1. Descarga los archivos del repositorio.
2. Abre una terminal y navega hasta el directorio donde se encuentran los archivos.
3. Compila el programa con el siguiente comando:
   ```
   javac GestionClinica.java
   ```
4. Ejecuta el programa con el siguiente comando:
   ```
   java GestionClinica
   ```

## Formato de Datos de Entrada

Los datos de pacientes, médicos y citas deben proporcionarse en un archivo de texto con el siguiente formato CSV (valores separados por comas):

```
NombrePaciente,Edad,NumeroSeguridadSocial,NombreMedico,Fecha,CedulaMedico
```

Por ejemplo:
```
Juan Perez,25,1234567890,Dr. Garcia,01/04/2024,1234567890
```

Asegúrate de proporcionar la ruta correcta del archivo en la línea 27 de la clase `GestionClinica`.

## Consideraciones Técnicas

- El proyecto utiliza estructuras de datos simples como ArrayList para almacenar los objetos de pacientes, médicos y citas.
- La lectura de datos desde un archivo se realiza utilizando las clases FileReader y BufferedReader de Java.
- Se han implementado métodos para realizar consultas y operaciones sobre los datos almacenados, siguiendo un enfoque de programación orientada a objetos.

---

### Clase Paciente

```java
/**
 * Clase que representa a un paciente en la gestión clínica.
 */
public class Paciente {

    private String nombre;
    private int edad;
    private String numeroSeguridadSocial;

    /**
     * Constructor de la clase Paciente.
     * @param nombre Nombre del paciente.
     * @param edad Edad del paciente.
     * @param numeroSeguridadSocial Número de seguridad social del paciente.
     */
    public Paciente(String nombre, int edad, String numeroSeguridadSocial) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    /**
     * Obtiene el nombre del paciente.
     * @return Nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la edad del paciente.
     * @return Edad del paciente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el número de seguridad social del paciente.
     * @return Número de seguridad social del paciente.
     */
    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }
}
```

### Clase Medico

```java
/**
 * Clase que representa a un médico en la gestión clínica.
 */
public class Medico {

    private String nombre;
    private String cedula;

    /**
     * Constructor de la clase Medico.
     * @param nombre Nombre del médico.
     * @param cedula Cédula profesional del médico.
     */
    public Medico(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    /**
     * Obtiene el nombre del médico.
     * @return Nombre del médico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la cédula profesional del médico.
     * @return Cédula profesional del médico.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece el nombre del médico.
     * @param nombre Nombre del médico.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la cédula profesional del médico.
     * @param cedula Cédula profesional del médico.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
```

### Clase Cita

```java
import java.util.Date;

/**
 * Clase que representa una cita médica en la gestión clínica.
 */
public class Cita {

    private String fecha;
    private Medico medico;
    private Paciente paciente;

    /**
     * Constructor de la clase Cita.
     * @param fecha Fecha de la cita.
     * @param medico Médico que atiende la cita.
     * @param paciente Paciente que tiene la cita.
     */
    public Cita(String fecha, Medico medico, Paciente paciente) {
        this.fecha = fecha;
        this.medico = medico;
        this.paciente = paciente;
    }

    /**
     * Obtiene la fecha de la cita.
     * @return Fecha de la cita.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene el médico que atiende la cita.
     * @return Médico que atiende la cita.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Obtiene el paciente que tiene la cita.
     * @return Paciente que tiene la cita.
     */
    public Paciente getPaciente() {
        return paciente;
    }
}
```
