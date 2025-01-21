
# Vending Machine - Java Project

## Description

This project simulates a vending machine with product categories such as drinks, snacks, chocolates, gummies, sandwiches, coffee, tea, and healthy products. Users can interact with the machine by viewing categories, checking available products, and making purchases, all within a console-based interface.

## Features

- **Product Categories:** The vending machine contains 7 product categories.
- **Purchase Operation:** Users can purchase products if the stock is available and the money inserted is sufficient.
- **Product Verification:** Users can check if a product exists and if it is available in the machine.
- **Stock Management:** The machine automatically reduces the stock of a product after each purchase.
- **User Interface:** A simple console interface where users can navigate through categories and view products.

## Project Structure

- **Main.java**: The main class that initializes the menu and runs the application.
- **Maquina.java**: Represents the vending machine, containing the products, categories, and methods to interact with them.
- **Producto.java**: Represents individual products in the vending machine, with properties such as name, price, stock, and category.
- **Menu.java**: Contains the logic to interact with the user, displaying options, reading inputs, and executing actions.

## Requirements

- JDK 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse, etc.)

## Installation

1. Clone this repository or download the project.
2. Open the project in your preferred IDE.
3. Make sure the JDK is correctly configured.
4. Run the `Main.java` file to start the vending machine.

## Usage

When you run the application, a menu with the following options will be displayed:

1. **View Categories**: Displays the available product categories in the vending machine.
2. **View Products by Category**: Allows the user to choose a category and see all available products within it.
3. **Buy Product**: Lets the user select a product to purchase, specifying the money inserted.
4. **Exit**: Closes the application.


## Functionalities

### 1. View Categories
The vending machine has a total of 7 categories: Drinks, Snacks, Chocolates, Gummies, Sandwiches, Coffee and Tea, and Healthy Products. The user can check all available categories and choose the one they are interested in.

### 2. View Products by Category
After selecting a category, the user can see all the available products within that category along with their price and stock quantity.

### 3. Buy Product
The user can select a product and proceed with purchasing it if they have enough money. If the purchase is successful, the product's stock is automatically reduced. If the money is insufficient, the system will show the remaining amount. If the product is out of stock, the user will be informed.

### 4. Exit
Choosing this option will close the program and terminate the execution.

## Code Example

Here is a snippet of the `Maquina.java` class:

```java
public class Maquina {
    private Producto[][] productos;
    private final String[] CATEGORIAS = {
            "Drinks", "Snacks", "Chocolates", "Gummies",
            "Sandwiches", "Coffee and Tea", "Healthy Products"
    };

    public Maquina() {
        productos = new Producto[NUM_CATEGORIAS][NUM_ESTANTES];
        initializeProducts();
    }

    // Method to initialize the products
    private void initializeProducts() {
        productos[0][0] = new Producto("Coca-Cola", 2.5, 10, CATEGORIAS[0]);
        productos[1][0] = new Producto("Potato Chips", 1.8, 20, CATEGORIAS[1]);
        // Other products...
    }

    // Show the available categories
    public void showCategories() {
        for (String category : CATEGORIAS) {
            System.out.println("- " + category);
        }
    }
}


