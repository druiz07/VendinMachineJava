/**
 * Esta clase representa a un usuario con un nombre de usuario, contraseña, correo electrónico y dinero extra.
 * Permite acceder y modificar estos atributos, así como obtener una representación en forma de cadena.
 */
public class Usuario {
    private String nombreUsuario; // Nombre de usuario único
    private String contrasena;    // Contraseña del usuario
    private String correo;        // Correo electrónico del usuario
    private double moneyExtra;    // Dinero adicional asociado al usuario

    /**
     * Constructor para crear un nuevo objeto Usuario con los datos proporcionados.
     * @param nombreUsuario El nombre de usuario del nuevo usuario.
     * @param contrasena La contraseña del nuevo usuario.
     * @param correo El correo electrónico del nuevo usuario.
     * @param moneyExtra El dinero adicional que el usuario tiene.
     */
    public Usuario(String nombreUsuario, String contrasena, String correo, double moneyExtra) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.moneyExtra = moneyExtra;  // Inicializa el dinero extra
    }

    // Getters y Setters

    /**
     * Obtiene el dinero extra asociado al usuario.
     * @return El dinero extra del usuario.
     */
    public double getMoneyExtra() {
        return moneyExtra;
    }

    /**
     * Establece el dinero extra del usuario.
     * @param moneyExtra El nuevo valor del dinero extra.
     */
    public void setMoneyExtra(double moneyExtra) {
        this.moneyExtra = moneyExtra;
    }

    /**
     * Obtiene el nombre de usuario.
     * @return El nombre de usuario del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario.
     * @param nombreUsuario El nuevo nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     * @param contrasena La nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param correo El nuevo correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto Usuario.
     * @return Una cadena que describe al usuario.
     */
    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Correo: " + correo + ", MoneyExtra: " + moneyExtra;
    }
}
