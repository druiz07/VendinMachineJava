/**
 * Esta clase representa un producto en un sistema de inventario. Un producto tiene un nombre,
 * un precio, un stock restante y una categoría a la que pertenece.
 */
public class Producto {
    private String nombre;          // Nombre del producto
    private double precio;          // Precio del producto
    private int stockRestante;      // Cantidad restante en stock
    private String categoria;       // Categoría del producto

    /**
     * Constructor para crear un nuevo objeto Producto con los datos proporcionados.
     * @param nombre El nombre del producto.
     * @param precio El precio del producto.
     * @param stockRestante La cantidad restante en stock del producto.
     * @param categoria La categoría del producto.
     */
    public Producto(String nombre, double precio, int stockRestante, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stockRestante = stockRestante;
        this.categoria = categoria;  // Inicializa la categoría del producto
    }

    // Getters

    /**
     * Obtiene el nombre del producto.
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la categoría del producto.
     * @return La categoría del producto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el precio del producto.
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene la cantidad restante en stock del producto.
     * @return La cantidad restante en stock.
     */
    public int getStockRestante() {
        return stockRestante;
    }

    // Setters

    /**
     * Establece el nombre del producto.
     * @param nombre El nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Establece la categoría del producto.
     * @param categoria La nueva categoría del producto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Establece la cantidad restante en stock del producto.
     * @param stockRestante La nueva cantidad de stock restante.
     */
    public void setStockRestante(int stockRestante) {
        this.stockRestante = stockRestante;
    }
}
