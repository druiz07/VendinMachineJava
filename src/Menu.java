import java.util.Scanner;
import java.lang.InterruptedException;


public class Menu {
    private Maquina maquina;
    private Scanner scanner;

    // Constructor
    public Menu() {
        this.maquina = new Maquina();
        this.scanner = new Scanner(System.in);
    }
