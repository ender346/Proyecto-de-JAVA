import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


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
            String nombrePaciente = partes[0];
            String sexoPaciente = partes[1].trim();
            int edadPaciente = Integer.parseInt(partes[2].trim());
            String numeroSeguridadSocial = partes[3].trim();
            String nombreMedico = partes[4].trim();
            String fechaCita = partes[5].trim();
            String generoMedico = sexoPaciente;
            String apellidoPaterno = partes[6];
            String apellidoMaterno = partes[7];
            String cedulaMedico = partes[8];
            String horaCita = partes[9];

            // Crear paciente
            // Crear médico
            Medico medico = new Medico(nombreMedico, cedulaMedico, generoMedico);
            medicos.add(medico);

            // Crear paciente
            Paciente paciente = new Paciente(nombrePaciente, apellidoPaterno, apellidoMaterno, sexoPaciente, edadPaciente, numeroSeguridadSocial);
            pacientes.add(paciente);

            // Crear cita
            Cita cita = new Cita(fechaCita, horaCita, medico, paciente);
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
        System.out.println("3. Mostrar el número de seguridad social");
        System.out.println("4. Listar todos los pacientes asignados a un médico específico");
        System.out.println("5. Listar los nombres de los médicos por género");
        System.out.println("6. Mostrar el nombre completo y la fecha/hora de la cita del paciente");
        System.out.println("7. Listar médicos cuyo nombre empiece con una letra específica");
        System.out.println("8. Buscar paciente por NSS");
        System.out.println("9. Encontrar el médico con más pacientes");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            System.out.print("Seleccione una opción: ");
            scanner.next(); // Consumir la entrada no válida
        }
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
            case 5:
                listarNombresMedicosPorGenero();
                break;
            case 6:
                mostrarNombreCompletoYFechaHoraCita();
                break;
            case 7:
                listarMedicosPorLetra();
                break;
            case 8:
                buscarPacientePorNSS();
                break;
            case 9:
                encontrarMedicoConMasPacientes();
                break;
            case 0:
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
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
    String fecha = scanner.nextLine().trim();

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
    String nombreMedico = scanner.nextLine().trim();

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

private static void listarNombresMedicosPorGenero() {
        System.out.println("Seleccione el género:");
        System.out.println("1. Hombres");
        System.out.println("2. Mujeres");
        System.out.print("Opción: ");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String generoBuscado = opcion == 1 ? "Masculino" : "Femenino";
        System.out.println("Nombres de los médicos " + (opcion == 1 ? "hombres:" : "mujeres:"));
        for (Medico medico : medicos) {
            if (medico.getGenero().equalsIgnoreCase(generoBuscado)) {
                System.out.println("- " + medico.getNombre());
            }
        }
    }

    private static void mostrarNombreCompletoYFechaHoraCita() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();

        for (Cita cita : citas) {
            if (cita.getPaciente().getNombre().equalsIgnoreCase(nombre)) {
                String nombreCompleto = cita.getPaciente().getNombreCompleto();
                String fechaHoraCita = cita.getFecha() + " " + cita.getHora();
                System.out.println("Nombre completo del paciente: " + nombreCompleto);
                System.out.println("Fecha y hora de la cita: " + fechaHoraCita);
                return;
            }
        }
        System.out.println("No se encontró al paciente con ese nombre.");
    }

    private static void listarMedicosPorLetra() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese la letra para buscar médicos: ");
    char letra = scanner.next().charAt(0);
    scanner.nextLine(); // Limpiar el buffer del scanner

    System.out.println("Médicos cuyo apellido empieza con '" + letra + "':");
    
   
    Set<String> nombresUnicos = new HashSet<>();

    for (Medico medico : medicos) {
       
        String[] partesNombre = medico.getNombre().split("\\s+");
        String apellido = partesNombre[partesNombre.length - 1]; 
        
        if (apellido.charAt(0) == letra) {
            nombresUnicos.add(medico.getNombre()); 
        }
    }

    for (String nombre : nombresUnicos) {
        System.out.println("- " + nombre);
    }
}



    private static void buscarPacientePorNSS() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de seguridad social (NSS) del paciente: ");
        String nss = scanner.nextLine();

        for (Paciente paciente : pacientes) {
            if (paciente.getNumeroSeguridadSocial().equals(nss)) {
                System.out.println("Paciente encontrado:");
                System.out.println("- Nombre: " + paciente.getNombreCompleto());
                System.out.println("- Edad: " + paciente.getEdad());
                return;
            }
        }
        System.out.println("No se encontró al paciente con ese número de seguridad social.");
    }

    private static void encontrarMedicoConMasPacientes() {
        Medico medicoMasPacientes = null;
        int maxPacientes = 0;
        for (Medico medico : medicos) {
            int pacientesAsignados = contarPacientesPorMedico(medico);
            if (pacientesAsignados > maxPacientes) {
                maxPacientes = pacientesAsignados;
                medicoMasPacientes = medico;
            }
        }
        if (medicoMasPacientes != null) {
            System.out.println("Médico con más pacientes:");
            System.out.println("- Nombre: " + medicoMasPacientes.getNombre());
            System.out.println("- Cédula: " + medicoMasPacientes.getCedula());
            System.out.println("- Cantidad de pacientes: " + maxPacientes);
        } else {
            System.out.println("No hay médicos registrados.");
        }
    }

    private static int contarPacientesPorMedico(Medico medico) {
    int count = 0;
    for (Cita cita : citas) {
        if (cita.getMedico().getNombre().equalsIgnoreCase(medico.getNombre())) {
            count++;
        }
    }
    return count;
}


}
