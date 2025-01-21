public class Producto {
    private String nombre;
    private double precio;
    private int stockRestante;
    private String categoria;

    // Constructor
    public Producto(String nombre, double precio, int stockRestante,String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stockRestante = stockRestante;
        this.categoria= categoria;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStockRestante() {
        return stockRestante;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setStockRestante(int stockRestante) {
        this.stockRestante = stockRestante;
    }
}

