package LAB011POOI_2310976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Libro> listaLibros = cargarLibrosDesdeArchivo("ListaLibros.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSeleccione una operacion:");
            System.out.println("a. Mostrar la lista de libros");
            System.out.println("b. Agregar un libro y asignar el autor del libro");
            System.out.println("c. Eliminar un libro");
            System.out.println("d. Editar un libro");
            System.out.println("e. Buscar un libro por titulo");
            System.out.println("f. Buscar el autor dado un libro");
            System.out.println("g. Ordenar los libros por autor");
            System.out.println("0. Salir");

            char opcion = scanner.nextLine().charAt(0);

            switch (opcion) {
                case 'a':
                    mostrarListaLibros(listaLibros);
                    break;
                case 'b':
                    agregarLibro(listaLibros);
                    break;
                case 'c':
                    eliminarLibro(listaLibros);
                    break;
                case 'd':
                    editarLibro(listaLibros);
                    break;
                case 'e':
                    buscarLibroPorTitulo(listaLibros);
                    break;
                case 'f':
                    buscarAutorPorLibro(listaLibros);
                    break;
                case 'g':
                    ordenarLibrosPorAutor(listaLibros);
                    break;
                case '0':
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione nuevamente.");
            }
        }
    }

    private static void mostrarListaLibros(List<Libro> listaLibros) {
        System.out.printf("%-5s%-60s%-40s%-15s%-15s\n", "Nro", "Nombre", "Autor", "Precio ($)", "Cantidad (qty)");

        for (int i = 0; i < listaLibros.size(); i++) {
            Libro libro = listaLibros.get(i);
            Autor autor = libro.getAutor();
            System.out.printf("%-5d%-60s%-40s%-15.2f%-15d\n",
                    i + 1, libro.getName(), autor.getName(), libro.getPrecio(), libro.getQty());
        }
        System.out.println();
    }

    private static void agregarLibro(List<Libro> listaLibros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese los datos del nuevo libro:");

        System.out.print("Titulo del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Nombre del autor: ");
        String nombreAutor = scanner.nextLine();

        System.out.print("Email del autor: ");
        String emailAutor = scanner.nextLine();

        System.out.print("Genero del autor (M/F): ");
        char generoAutor = scanner.nextLine().charAt(0);

        Autor autorNuevo = new Autor(nombreAutor, emailAutor, generoAutor);

        System.out.print("Precio del libro: ");
        double precio = scanner.nextDouble();

        System.out.print("Cantidad disponible: ");
        int cantidad = scanner.nextInt();

        Libro nuevoLibro = new Libro(titulo, autorNuevo, precio, cantidad);
        listaLibros.add(nuevoLibro);

        System.out.println("Libro agregado exitosamente.");
    }

    private static void eliminarLibro(List<Libro> listaLibros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el titulo del libro que desea eliminar:");
        String tituloEliminar = scanner.nextLine();

        listaLibros.removeIf(libro -> libro.getName().equalsIgnoreCase(tituloEliminar));

        System.out.println("Libro eliminado");
    }

    private static void editarLibro(List<Libro> listaLibros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el título del libro que desea editar:");
        String tituloEditar = scanner.nextLine();

        for (Libro libro : listaLibros) {
            if (libro.getName().equalsIgnoreCase(tituloEditar)) {
                System.out.println("Ingrese los nuevos datos para el libro:");

                System.out.print("Nuevo título del libro: ");
                libro.name = scanner.nextLine();

                System.out.print("Nuevo precio del libro: ");
                libro.precio = scanner.nextDouble();

                System.out.print("Nueva cantidad disponible: ");
                libro.qty = scanner.nextInt();

                System.out.println("Libro editado exitosamente.");
                return;
            }
        }

        System.out.println("Libro no encontrado. No se pudo editar.");
    }

    private static void buscarLibroPorTitulo(List<Libro> listaLibros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el título del libro que desea buscar:");
        String titulo = scanner.nextLine();

        Libro libroEncontrado = null;

        for (Libro libro : listaLibros) {
            if (libro.getName().equalsIgnoreCase(titulo)) {
                libroEncontrado = libro;
                break;
            }
        }

        if (libroEncontrado != null) {
            System.out.println("\nLibro encontrado:");
            System.out.println("Título: " + libroEncontrado.getName());
            System.out.println("Autor: " + libroEncontrado.getAutor().getName());
            System.out.println("Unidades Disponibles: " + libroEncontrado.getQty());
            System.out.println("Precio: " + libroEncontrado.getPrecio());
        } else {
            System.out.println("\nLibro no encontrado.");
        }
    }

    private static void buscarAutorPorLibro(List<Libro> listaLibros) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el titulo del libro para conocer a su autor:");
        String titulo = scanner.nextLine();

        Autor autorEncontrado = null;

        for (Libro libro : listaLibros) {
            if (libro.getName().equalsIgnoreCase(titulo)) {
                autorEncontrado = libro.getAutor();
                break;
            }
        }

        if (autorEncontrado != null) {
            System.out.println("\nAutor del libro:");
            System.out.println(autorEncontrado.toString());
        } else {
            System.out.println("\nLibro no encontrado.");
        }
    }

    private static void ordenarLibrosPorAutor(List<Libro> listaLibros) {
        System.out.println("\nOrdenando libros por autor...");
        listaLibros.sort(Comparator.comparing(libro -> libro.getAutor().getName()));
        mostrarListaLibros(listaLibros);
    }

    private static List<Libro> cargarLibrosDesdeArchivo(String nombreArchivo) {
        List<Libro> listaLibros = new ArrayList<>();

        try {
            InputStream inputStream = Main.class.getResourceAsStream(nombreArchivo);

            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    int id = Integer.parseInt(partes[0]);
                    String titulo = partes[1];
                    String nombreAutor = partes[2];
                    String emailAutor = partes[3];
                    char generoAutor = partes[4].charAt(0);
                    Autor autor = new Autor(nombreAutor, emailAutor, generoAutor);
                    double precio = Double.parseDouble(partes[3]);
                    int cantidad = Integer.parseInt(partes[4]);

                    Libro libro = new Libro(titulo, autor, precio, cantidad);
                    listaLibros.add(libro);
                }

                br.close();
            } else {
                System.out.println("No se pudo encontrar el archivo " + nombreArchivo + " en el directorio de recursos.");
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo " + nombreArchivo + ": " + e.getMessage());
        }

        return listaLibros;
    }
}
