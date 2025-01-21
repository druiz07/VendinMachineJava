public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private String correo;
    private double moneyExtra;

    // Constructor
    public Usuario(String nombreUsuario, String contrasena, String correo, double moneyExtra) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.correo = correo;
        this.moneyExtra = moneyExtra;  // Inicializamos moneyExtra
    }

    // Getters y Setters

    public double getMoneyExtra() {
        return moneyExtra;
    }

    public void setMoneyExtra(double moneyExtra) {
        this.moneyExtra = moneyExtra;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreUsuario + ", Correo: " + correo + ", MoneyExtra: " + moneyExtra;
    }
}
