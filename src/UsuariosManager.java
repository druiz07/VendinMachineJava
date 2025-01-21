import java.io.*;
import java.util.*;

/**
 * Esta clase gestiona las operaciones relacionadas con los usuarios, como el registro, inicio de sesión y almacenamiento de la información en un archivo.
 * Además, mantiene un seguimiento del usuario activo.
 */
public class UsuariosManager {

    private List<Usuario> usuarios; // Lista de usuarios registrados
    private Usuario usuarioActivo;  // Usuario actualmente autenticado
    private final String archivoUsuarios = "usuarios.txt"; // Archivo donde se almacenan los usuarios

    /**
     * Constructor de la clase UsuariosManager. Inicializa la lista de usuarios y carga los usuarios desde el archivo.
     */
    public UsuariosManager() {
        usuarios = new ArrayList<>();
        usuarioActivo = null; // Ningún usuario está activo al inicio
        cargarUsuarios(); // Carga los usuarios desde el archivo
    }

    /**
     * Carga los usuarios desde el archivo "usuarios.txt".
     * Lee cada línea del archivo y crea un objeto Usuario para cada usuario registrado.
     */
    private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            // Leer línea por línea el archivo
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) { // Asegurarse de que haya 4 campos
                    String nombreUsuario = datos[0];
                    String contrasena = datos[1];
                    String correo = datos[2];
                    double moneyExtra = Double.parseDouble(datos[3]); // Convertir moneyExtra a un valor numérico
                    Usuario usuario = new Usuario(nombreUsuario, contrasena, correo, moneyExtra);
                    usuarios.add(usuario); // Agregar el usuario a la lista
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios.");
        }
    }

    /**
     * Guarda todos los usuarios registrados en el archivo "usuarios.txt".
     * Cada usuario se guarda en una línea con su nombre, contraseña, correo y dinero extra.
     */
    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            // Escribir cada usuario en el archivo
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getNombreUsuario() + "," + usuario.getContrasena() + "," + usuario.getCorreo() + "," + usuario.getMoneyExtra());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios.");
        }
    }

    /**
     * Registra un nuevo usuario.
     * Verifica si ya existe un usuario con el mismo nombre. Si no, crea un nuevo usuario con dinero extra inicializado en 0.
     * @param nombreUsuario El nombre del nuevo usuario.
     * @param contrasena La contraseña del nuevo usuario.
     * @param correo El correo del nuevo usuario.
     * @return true si el registro es exitoso, false si el nombre de usuario ya está en uso.
     */
    public boolean registrarUsuario(String nombreUsuario, String contrasena, String correo) {
        // Verificar si ya existe un usuario con el mismo nombre
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                System.out.println("El nombre de usuario ya está en uso.");
                return false; // Si el nombre ya está en uso, no se registra
            }
        }

        // Crear un nuevo usuario con dinero extra inicializado en 0
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, correo, 0);
        usuarios.add(nuevoUsuario); // Agregar el nuevo usuario a la lista
        guardarUsuarios();  // Guardar cambios en el archivo
        System.out.println("Usuario registrado exitosamente.");
        return true;
    }

    /**
     * Inicia sesión con un usuario registrado.
     * Verifica que el nombre de usuario y la contraseña coincidan con los datos almacenados.
     * @param nombreUsuario El nombre de usuario para iniciar sesión.
     * @param contrasena La contraseña para iniciar sesión.
     * @return true si el inicio de sesión es exitoso, false si los datos son incorrectos.
     */
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        // Buscar al usuario por nombre y contraseña
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) &&
                    usuario.getContrasena().equals(contrasena)) {
                usuarioActivo = usuario; // Establecer al usuario como activo
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false; // Si no se encuentra el usuario o la contraseña no es correcta
    }

    /**
     * Obtiene el usuario que está actualmente autenticado.
     * @return El usuario activo.
     */
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    /**
     * Muestra todos los usuarios registrados en el sistema. (Función solo para pruebas)
     */
    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario); // Mostrar cada usuario en la lista
        }
    }
}
