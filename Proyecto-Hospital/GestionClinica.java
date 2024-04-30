/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 package com.mycompany.gestionclinica;

 /**
  *
  * @author 3nder
  */
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Scanner;
 import java.io.File;
 
 
 public class GestionClinica {
     private static ArrayList<Paciente> pacientes = new ArrayList<>();
     private static ArrayList<Medico> medicos = new ArrayList<>();
     private static ArrayList<Cita> citas = new ArrayList<>();
 
     public static void main(String[] args) {
         try {
             cargarDatos(); // Cargar datos al iniciar la aplicación
         } catch (IOException e) {
             System.out.println("Error al cargar datos: " + e.getMessage());
             return;
         }
         mostrarMenu();
     }
 
     private static void cargarDatos() throws IOException {
     File archivo = new File("C:/Users/LENOVO/OneDrive/Escritorio/DatosPar/Pacientes.txt");
     FileReader lectorArchivo = new FileReader(archivo);
     BufferedReader lectorBuf = new BufferedReader(lectorArchivo);
 
     String linea;
     while ((linea = lectorBuf.readLine()) != null) {
         String[] partes = linea.split(",");
         String nombre = partes[0];
         int edad = Integer.parseInt(partes[1]);
         String numeroSeguridadSocial = partes[2];
         String nombreMedico = partes[3];
         String fecha = partes[4];
         String cedula = partes[5].substring(partes[5].indexOf(":") + 1);
 
         // Crear paciente
         Paciente paciente = new Paciente(nombre, edad, numeroSeguridadSocial);
         pacientes.add(paciente);
 
         // Crear médico
         Medico medico = new Medico(nombreMedico, cedula);
         medicos.add(medico);
 
         // Crear cita
         Cita cita = new Cita(fecha, medico, paciente);
         citas.add(cita);
         
     }
     lectorBuf.close();
 }
 
     
 
     private static void mostrarMenu() {
         Scanner scanner = new Scanner(System.in);
         int opcion;
         do {
             System.out.println("\nMenu:");
             System.out.println("1. Listar pacientes por rango de edad");
             System.out.println("2. Mostrar los nombres de los pacientes que tuvieron una cita en un día especifico");
             System.out.println("3. Mostrar el numero de seguridad social");
             System.out.println("4. Listar todos los pacientes asignados a un medico específico");
             System.out.println("0. Salir");
             System.out.print("Seleccione una opción: ");
             opcion = scanner.nextInt();
             scanner.nextLine(); // Limpiar el buffer del scanner
 
             switch (opcion) {
                 case 1:
                     listarPacientesPorRangoDeEdad();
                     break;
                 case 2:
                     mostrarPacientesPorFechaDeCita();
                     break;
                 case 3:
                     mostrarInformacionPaciente();
                     break;
                 case 4:
                     listarPacientesPorMedico();
                     break;
                 case 0:
                     System.out.println("Saliendo del programa...");
                     break;
                 default:
                     System.out.println("Opción no valida. Intentelo de nuevo.");
             }
         } while (opcion != 0);
         scanner.close(); // Cerrar el scanner al salir del programa
     }
 
     private static void listarPacientesPorRangoDeEdad() {
     Scanner scanner = new Scanner(System.in);
     System.out.println("Seleccione un rango de edad:");
     System.out.println("1. Mayores de 10 años");
     System.out.println("2. Mayores de 18 años");
     System.out.println("3. Mayores de 40 años");
     System.out.println("4. Mayores de 60 años");
     System.out.print("Opción: ");
     String opcion = scanner.nextLine();
 
     switch (opcion) {
         case "1":
             listarPacientesPorEdad(10, 18);
             break;
         case "2":
             listarPacientesPorEdad(18, 40);
             break;
         case "3":
             listarPacientesPorEdad(40, 60);
             break;
         case "4":
             listarPacientesPorEdad(60, 100);
             break;
         default:
             System.out.println("Opción no válida.");
     }
 }
 
 
     private static void listarPacientesPorEdad(int edadMinima, int edadMaxima) {
         System.out.println("Pacientes en el rango de edad " + edadMinima + " a " + edadMaxima + " años:");
         for (Paciente paciente : pacientes) {
             if (paciente.getEdad() >= edadMinima && paciente.getEdad() <= edadMaxima) {
                 System.out.println("- " + paciente.getNombre());
             }
         }
     }
 
     private static void mostrarPacientesPorFechaDeCita() {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Ingrese la fecha de la cita (formato dd/mm/aaaa): ");
         String fecha = scanner.nextLine();
 
         System.out.println("Pacientes que tuvieron cita el " + fecha + ":");
         for (Cita cita : citas) {
             if (cita.getFecha().equals(fecha)) {
                 System.out.println("- " + cita.getPaciente().getNombre());
             }
         }
     }
 
     private static void mostrarInformacionPaciente() {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Ingrese el nombre del paciente: ");
         String nombre = scanner.nextLine();
 
         for (Paciente paciente : pacientes) {
             if (paciente.getNombre().equalsIgnoreCase(nombre)) {
                 System.out.println("Numero de Seguridad Social: " + paciente.getNumeroSeguridadSocial());
                 return;
             }
         }
         System.out.println("No se encontró al paciente con ese nombre.");
     }
 
   private static void listarPacientesPorMedico() {
     Scanner scanner = new Scanner(System.in);
     System.out.print("Ingrese el nombre del medico: ");
     String nombreMedico = scanner.nextLine();
 
     boolean medicoEncontrado = false;
     for (Medico medico : medicos) {
         if (medico.getNombre().equalsIgnoreCase(nombreMedico)) {
             medicoEncontrado = true;
             System.out.println("Nombre del medico: " + medico.getNombre());
             System.out.println("Cédula del medico: " + medico.getCedula());
             break;
         }
     }
 
     if (!medicoEncontrado) {
         System.out.println("No se encontró al médico con ese nombre.");
     } else {
         System.out.println("Pacientes asignados al médico " + nombreMedico + ":");
         for (Cita cita : citas) {
             if (cita.getMedico().getNombre().equalsIgnoreCase(nombreMedico)) {
                 System.out.println("- " + cita.getPaciente().getNombre());
             }
         }
     }
 }
 
 }