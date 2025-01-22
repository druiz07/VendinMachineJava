import java.util.Scanner;
import java.lang.InterruptedException;

/**
 * Esta clase representa el menú de una máquina expendedora que gestiona la interacción
 * con los usuarios registrados, permitiendo realizar operaciones como registro, inicio de sesión
 * y compra de productos.
 */
public class Menu {
    private Maquina maquina;            // Instancia de la máquina expendedora
    private Scanner scanner;            // Scanner para leer las entradas del usuario
    private UsuariosManager usuariosManager; // Instancia del gestor de usuarios
    private Usuario usuarioActivo;      // Usuario actualmente autenticado

    /**
     * Constructor de la clase Menu. Inicializa la máquina expendedora, el scanner y el gestor de usuarios.
     */
    public Menu() {
        this.maquina = new Maquina();
        this.scanner = new Scanner(System.in);
        this.usuariosManager = new UsuariosManager();
        this.usuarioActivo = null; // Ningún usuario está activo inicialmente
    }

    /**
     * Muestra un mensaje de bienvenida al usuario.
     */
    public void mostrarBienvenida() {
        System.out.println("¡Bienvenido a la máquina expendedora!");
    }

    /**
     * Muestra todos los productos disponibles en la máquina.
     */
    public void mostrarProductos() {
        Producto[][] productos = maquina.getProductos();
        System.out.println("\n==================================");
        System.out.println("       MÁQUINA EXPENDEDORA       ");
        System.out.println("==================================");

        int filaNum = 1;
        for (Producto[] fila : productos) {
            System.out.println("\n--- " + fila[0].getCategoria().toUpperCase() + " ---");
            System.out.println("+----+----------------+--------+------+------+");
            System.out.println("| #  | Producto       | Precio | Stock|  \"  |");
            System.out.println("+----+----------------+--------+------+------+");
            int colNum = 1;
            for (Producto producto : fila) {
                System.out.printf("| %d%d | %-14s | %6.2f€ | %4d | [ ]  |",
                        filaNum, colNum, producto.getNombre(), producto.getPrecio(), producto.getStockRestante());
                System.out.println();  // Imprime una nueva línea
                colNum++;
            }


            System.out.println("+----+----------------+--------+------+------+");
            filaNum++;
        }
        System.out.println("\nSeleccione el producto ingresando su número de fila y columna (ej. 12 para fila 1, columna 2).");
    }

    /**
     * Procesa la compra de un producto por parte del usuario.
     * Verifica si el usuario tiene suficiente dinero, y si el producto está disponible.
     */
    public void procesarCompra() {
        if (usuarioActivo == null) {
            System.out.println("¡Debes iniciar sesión para comprar productos!");
            return;
        }

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        if (!maquina.verificarNombre(nombre)) {
            System.out.println("El producto no existe. Intente nuevamente.");
            return;
        }

        // Verificar si el usuario tiene dinero extra
        double dineroExtra = usuarioActivo.getMoneyExtra(); // Obtenemos el dinero extra disponible
        double dineroIngresado = 0;

        // Si el dinero extra no es suficiente, pedimos dinero
        if (dineroExtra == 0) {
            System.out.print("Ingrese el dinero: ");
            if (scanner.hasNextDouble()) {
                dineroIngresado = scanner.nextDouble();
                scanner.nextLine(); // Consumir la línea pendiente
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un valor numérico.");
                scanner.nextLine(); // Consumimos la línea por si hay entrada inválida
                return;
            }
        }

        // Creamos un contenedor para manejar el cambio
        Contenedor contenedor;
        if (dineroExtra == 0) contenedor = new Contenedor(dineroIngresado);
        else contenedor = new Contenedor(dineroExtra);

        // Realizamos la compra
        String resultado = maquina.comprarProducto(nombre, contenedor);

        // Procesamos el resultado de la compra
        String[] partes = resultado.split(",");
        usuarioActivo.setMoneyExtra(contenedor.valor); // Actualizamos el dinero extra del usuario

        if (partes[0].equals("COMPRA_EXITOSA")) {
            usuariosManager.guardarUsuarios();  // Guardamos los usuarios con el dinero extra actualizado
            System.out.println("¡Compra exitosa! Preparando el producto...");
            mostrarProductoEnLaMaquina(nombre);
            System.out.println("\n¡Producto entregado! " + partes[1]);
            System.out.println("Cambio: " + partes[2] + "€");
        } else {
            switch (partes[0]) {
                case "DINERO_INSUFICIENTE":
                    System.out.println("Dinero insuficiente. Falta: " + partes[1] + "€");
                    break;
                case "PRODUCTO_AGOTADO":
                    System.out.println("El producto está agotado.");
                    break;
                case "PRODUCTO_NO_ENCONTRADO":
                    System.out.println("Producto no encontrado.");
                    break;
            }
        }
    }

    /**
     * Simula la caída del producto dentro de la máquina expendedora y muestra un efecto visual.
     * @param nombre El nombre del producto que se está entregando.
     */
    private void mostrarProductoEnLaMaquina(String nombre) {
        String maquina =
                "+------------------------+\n" +
                        "|  _______              |\n" +
                        "| |       |             |\n" +
                        "| |       |             |\n" +
                        "| |       |             |\n" +
                        "| |_______|             |\n" +
                        "|        |              |\n" +
                        "+------------------------+\n" +
                        "        | \n" +
                        "        | \n" +
                        "     ----V---- \n";

        // Mostrar la máquina antes de la caída
        System.out.println(maquina);

        // Simulación de la caída del producto hacia el cajetín
        for (int i = 0; i < 10; i++) {
            // Limpiar la pantalla
            System.out.print("\033[H\033[2J");
            System.out.flush();

            // Mostrar el producto cayendo
            String conductoConCaida =
                    "+------------------------+\n" +
                            "|  _______              |\n" +
                            "| |       |             |\n" +
                            "| |       |             |\n" +
                            "| |       |             |\n" +
                            "| |_______|             |\n" +
                            "|        |              |\n" +
                            "+------------------------+\n" +
                            "        | \n" +
                            "        | \n" +
                            "     ----V---- \n" +
                            "      " + generarEspacios(i) + nombre;

            System.out.println(conductoConCaida);

            try {
                Thread.sleep(200); // Pausa para dar el efecto de caída
            } catch (InterruptedException e) {
                e.printStackTrace();  // Maneja la excepción de interrupción
            }
        }

        // Mostrar el producto aterrizando en el cajetín
        String cajetinConProducto =
                "+------------------------+\n" +
                        "|                        |\n" +
                        "|     [Producto: " + nombre + "]     |\n" +
                        "|                        |\n" +
                        "+------------------------+";

        // Limpiar la pantalla y mostrar el producto en el cajetín
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(cajetinConProducto);
    }

    /**
     * Genera un número específico de espacios en blanco para simular la caída del producto.
     * @param i El número de espacios a generar.
     * @return La cadena con los espacios generados.
     */
    private String generarEspacios(int i) {
        StringBuilder espacios = new StringBuilder();
        for (int j = 0; j < i; j++) {
            espacios.append("\n");
        }
        return espacios.toString();
    }

    /**
     * Ejecuta el menú principal que maneja las operaciones de usuarios y la máquina expendedora.
     */
    public void ejecutarMenu() {
        mostrarBienvenida();

        while (true) {
            if (usuarioActivo == null) {
                // Mostrar opciones de usuario si no hay uno activo
                mostrarMenuUsuarios();
            } else {
                // Si ya hay un usuario activo, mostrar el menú de la máquina expendedora
                mostrarMenuMaquina();
            }
        }
    }

    /**
     * Muestra las opciones disponibles para gestionar usuarios (registro e inicio de sesión).
     */
    private void mostrarMenuUsuarios() {
        System.out.println("\n1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea pendiente

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema. ¡Adiós!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Consumimos la línea en caso de error de entrada
        }
    }

    /**
     * Permite registrar un nuevo usuario.
     */
    private void registrarUsuario() {
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();
        System.out.print("Ingrese el correo: ");
        String correo = scanner.nextLine();
        boolean exito = usuariosManager.registrarUsuario(nombreUsuario, contrasena, correo);
        if (exito) {
            System.out.println("Usuario registrado exitosamente.");
        }
    }

    /**
     * Permite a un usuario iniciar sesión.
     */
    private void iniciarSesion() {
        System.out.print("Ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese la contraseña: ");
        String contrasena = scanner.nextLine();
        if (usuariosManager.iniciarSesion(nombreUsuario, contrasena)) {
            usuarioActivo = usuariosManager.getUsuarioActivo(); // Establecer el usuario activo
            System.out.println("Bienvenido, " + usuarioActivo.getNombreUsuario() + "!");
        }
    }

    /**
     * Muestra el menú de la máquina expendedora, con opciones de compra y visualización de productos.
     */
    private void mostrarMenuMaquina() {
        System.out.println("\n1. Ver todos los productos");
        System.out.println("2. Comprar producto");
        System.out.println("3. Ver todas las Categorias");
        System.out.println("4. Ver productos por categoria");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");

        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea pendiente

            switch (opcion) {
                case 1:
                    mostrarProductos();
                    break;
                case 2:
                    procesarCompra();
                    break;
                case 3:
                    System.out.print("Estas son las categorias existentes");
                    maquina.mostrarCategorias();
                    break;
                case 4:
                    System.out.println("Ingrese la categoría a buscar: ");
                    String categoria = scanner.nextLine();
                    maquina.mostrarProductosPorCategoria(categoria);
                    break;
                case 5:
                    System.out.println("Gracias por usar la máquina expendedora. ¡Adiós!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } else {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Consumimos la línea en caso de error de entrada
        }
    }
}
