/**
 * Clase que representa una máquina expendedora con productos organizados por categorías.
 * Permite realizar compras, verificar la existencia de productos y mostrar productos por categoría.
 */
public class Maquina {
    private Producto[][] productos;
    private final String[] CATEGORIAS = {
            "Bebidas", "Snacks", "Chocolates", "Gominolas",
            "Bocadillos", "Café y Té", "Saludables"
    };

    private final int NUM_CATEGORIAS = 7;
    private final int NUM_ESTANTES = 5;

    /**
     * Constructor de la clase Maquina. Inicializa los productos y categorías en la máquina expendedora.
     */
    public Maquina() {
        productos = new Producto[NUM_CATEGORIAS][NUM_ESTANTES];
        initializeProducts();
    }

    /**
     * Método privado que inicializa los productos dentro de la máquina expendedora.
     * Asigna productos a cada categoría con nombre, precio, stock y categoría.
     */
    private void initializeProducts() {
        // Bebidas
        productos[0][0] = new Producto("Coca-Cola", 2.5, 10, CATEGORIAS[0]);
        productos[0][1] = new Producto("Pepsi", 2.3, 8, CATEGORIAS[0]);
        productos[0][2] = new Producto("Fanta", 2.4, 12, CATEGORIAS[0]);
        productos[0][3] = new Producto("Sprite", 2.6, 1, CATEGORIAS[0]);
        productos[0][4] = new Producto("Agua Mineral", 1.5, 15, CATEGORIAS[0]);

        // Snacks
        productos[1][0] = new Producto("Patatas Fritas", 1.8, 20, CATEGORIAS[1]);
        productos[1][1] = new Producto("Palomitas", 2.0, 18, CATEGORIAS[1]);
        productos[1][2] = new Producto("Cacahuetes", 1.5, 22, CATEGORIAS[1]);
        productos[1][3] = new Producto("Pistachos", 2.2, 16, CATEGORIAS[1]);
        productos[1][4] = new Producto("Galletas", 1.9, 25, CATEGORIAS[1]);

        // Chocolates
        productos[2][0] = new Producto("Chocolate con Leche", 1.7, 14, CATEGORIAS[2]);
        productos[2][1] = new Producto("Chocolate Negro", 2.0, 10, CATEGORIAS[2]);
        productos[2][2] = new Producto("Chocolate Blanco", 1.6, 12, CATEGORIAS[2]);
        productos[2][3] = new Producto("Kinder Bueno", 2.5, 8, CATEGORIAS[2]);
        productos[2][4] = new Producto("Snickers", 2.3, 9, CATEGORIAS[2]);

        // Gominolas
        productos[3][0] = new Producto("Ositos de Goma", 1.2, 30, CATEGORIAS[3]);
        productos[3][1] = new Producto("Regaliz", 1.5, 25, CATEGORIAS[3]);
        productos[3][2] = new Producto("Caramelos Masticables", 1.3, 20, CATEGORIAS[3]);
        productos[3][3] = new Producto("Chicles", 1.0, 35, CATEGORIAS[3]);
        productos[3][4] = new Producto("Nubes", 1.8, 18, CATEGORIAS[3]);

        // Bocadillos
        productos[4][0] = new Producto("Bocadillo de Jamón", 3.5, 5, CATEGORIAS[4]);
        productos[4][1] = new Producto("Bocadillo de Queso", 3.0, 7, CATEGORIAS[4]);
        productos[4][2] = new Producto("Bocadillo Mixto", 3.8, 6, CATEGORIAS[4]);
        productos[4][3] = new Producto("Sandwich Vegetal", 4.0, 4, CATEGORIAS[4]);
        productos[4][4] = new Producto("Bocadillo de Atún", 3.7, 5, CATEGORIAS[4]);

        // Café y té
        productos[5][0] = new Producto("Café Solo", 1.5, 20, CATEGORIAS[5]);
        productos[5][1] = new Producto("Café con Leche", 1.8, 18, CATEGORIAS[5]);
        productos[5][2] = new Producto("Té Verde", 1.6, 15, CATEGORIAS[5]);
        productos[5][3] = new Producto("Té Negro", 1.7, 12, CATEGORIAS[5]);
        productos[5][4] = new Producto("Chocolate Caliente", 2.0, 10, CATEGORIAS[5]);

        // Productos saludables
        productos[6][0] = new Producto("Barrita de Cereales", 2.0, 15, CATEGORIAS[6]);
        productos[6][1] = new Producto("Fruta Deshidratada", 2.5, 10, CATEGORIAS[6]);
        productos[6][2] = new Producto("Batido Proteico", 3.0, 8, CATEGORIAS[6]);
        productos[6][3] = new Producto("Yogur Natural", 1.8, 12, CATEGORIAS[6]);
        productos[6][4] = new Producto("Ensalada Fresca", 4.5, 6, CATEGORIAS[6]);
    }

    /**
     * Verifica si un producto con el nombre dado existe en la máquina expendedora.
     * @param productName El nombre del producto que se desea verificar.
     * @return true si el producto existe, false en caso contrario.
     */
    public boolean verificarNombre(String productName) {
        for (Producto[] fila : productos) {
            for (Producto producto : fila) {
                if (producto.getNombre().equalsIgnoreCase(productName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Permite comprar un producto de la máquina expendedora, verificando si hay stock
     * y si el dinero ingresado es suficiente.
     * @param nombre El nombre del producto que se desea comprar.
     * @param dinero El objeto Contenedor que representa el dinero del usuario.
     * @return Un mensaje que indica el resultado de la compra.
     */
    public String comprarProducto(String nombre, Contenedor dinero) {
        for (Producto[] fila : productos) {
            for (Producto producto : fila) {
                if (producto.getNombre().equalsIgnoreCase(nombre)) {
                    if (producto.getStockRestante() > 0) {
                        double precio = producto.getPrecio();

                        // Caso 1: Si el dinero extra cubre el precio
                        if (dinero.valor >= precio) {
                            dinero.valor -= precio;  // Se utiliza solo el dinero extra
                            producto.setStockRestante(producto.getStockRestante() - 1);
                            return "COMPRA_EXITOSA," + producto.getNombre() + "," + dinero.valor;
                        } else {
                            double falta = precio - dinero.valor;
                            return "DINERO_INSUFICIENTE," + falta;
                        }
                    } else {
                        return "PRODUCTO_AGOTADO";
                    }
                }
            }
        }
        return "PRODUCTO_NO_ENCONTRADO";
    }

    /**
     * Obtiene la lista de productos en la máquina expendedora.
     * @return Un array de productos organizados por categorías.
     */
    public Producto[][] getProductos() {
        return productos;
    }

    /**
     * Muestra los productos de la máquina expendedora que pertenecen a una categoría dada.
     * @param categoria La categoría de productos que se desea mostrar.
     */
    public void mostrarProductosPorCategoria(String categoria) {
        boolean categoriaEncontrada = false;
        for (Producto[] fila : productos) {
            for (Producto producto : fila) {
                if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                    if (!categoriaEncontrada) {
                        System.out.println("Productos en la categoría: " + categoria);
                        categoriaEncontrada = true;
                    }
                    System.out.println("- " + producto.getNombre() + " | Precio: " + producto.getPrecio() + "€ | Stock: " + producto.getStockRestante());
                }
            }
        }
        if (!categoriaEncontrada) {
            System.out.println("No se encontraron productos en la categoría: " + categoria);
        }
    }

    /**
     * Muestra todas las categorías disponibles en la máquina expendedora.
     */
    public void mostrarCategorias() {
        System.out.println();
        for (int i = 0; i < NUM_CATEGORIAS; i++) {
            System.out.println("- " + productos[i][0].getCategoria());
        }
    }
}
