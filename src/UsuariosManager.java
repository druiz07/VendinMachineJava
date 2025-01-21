import java.io.*;
import java.util.*;

public class UsuariosManager {

    private List<Usuario> usuarios;
    private Usuario usuarioActivo; // Variable para almacenar al usuario activo
    private final String archivoUsuarios = "usuarios.txt";

    public UsuariosManager() {
        usuarios = new ArrayList<>();
        usuarioActivo = null; // Ningún usuario está activo al inicio
        cargarUsuarios();
    }

    // Cargar los usuarios desde el archivo
    private void cargarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Usuario usuario = new Usuario(datos[0], datos[1], datos[2]);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios.");
        }
    }

    // Guardar los usuarios en el archivo
    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getNombreUsuario() + "," + usuario.getContrasena() + "," + usuario.getCorreo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios.");
        }
    }

    // Registrar un nuevo usuario
    public boolean registrarUsuario(String nombreUsuario, String contrasena, String correo) {
        // Verificar si ya existe un usuario con el mismo nombre
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                System.out.println("El nombre de usuario ya está en uso.");
                return false;
            }
        }

        // Crear un nuevo usuario
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena, correo);
        usuarios.add(nuevoUsuario);
        guardarUsuarios();  // Guardar cambios en el archivo
        System.out.println("Usuario registrado exitosamente.");
        return true;
    }

    // Iniciar sesión
    public boolean iniciarSesion(String nombreUsuario, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) &&
                    usuario.getContrasena().equals(contrasena)) {
                usuarioActivo = usuario; // Establecer al usuario como activo
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
    }

    // Obtener el usuario activo
    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    // Mostrar todos los usuarios (esto es solo para pruebas)
    public void mostrarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
