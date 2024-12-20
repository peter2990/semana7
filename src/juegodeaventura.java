import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class JuegoAventura {
    private ArrayList<String> inventario;
    private int salud;
    private final Random random;
    private final Scanner scanner;

    public JuegoAventura() {
        this.inventario = new ArrayList<>();
        this.salud = 100;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarJuego() {
        System.out.println("Bienvenido a Juego Aventura!");
        while (salud > 0) {
            System.out.println("\nTu salud: " + salud);
            System.out.println("Inventario: " + inventario);
            System.out.println("1. Explorar");
            System.out.println("2. Ver inventario");
            System.out.println("3. Curar");
            System.out.println("4. Salir sin guardar");
            System.out.print("Elige una accion (1-3): ");
            try {
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> explorar();
                    case 2 -> verInventario();
                    case 3 -> curar();
                    case 4 -> System.exit(0);
                    default -> throw new IllegalArgumentException("Opcion Invalida!");
                }
            } catch (Exception e) {
                System.out.println("ERR::" + e.getMessage());
            }
        }
        System.out.println("Game Over! Has muerto en el calabozo.");
    }

    private void explorar() {
        int evento = random.nextInt(3);
        switch (evento) {
            case 0 -> buscarTesoro();
            case 1 -> encontrarMonstruo();
            case 2 -> System.out.println("El cuarto esta vacio. Avanza...");
        }
    }

    private void buscarTesoro() {
        String[] tesoros = {"Moneda de Oro", "Pocion", "Artefacto Antiguo", "Espada"};
        String tesoro = tesoros[random.nextInt(tesoros.length)];
        inventario.add(tesoro);
        System.out.println("Has encontrado un " + tesoro + "!");
    }

    private void encontrarMonstruo() {
        System.out.println("Un monstruo te ha atacado!");
        int damage = random.nextInt(20) + 1;
        salud -= damage;
        System.out.println("Has tomado " + damage + " de daño!");

        try {
            System.out.print("Quieres pelear de vuelta? (si/no): ");
            scanner.nextLine();
            String respuesta;
            respuesta = scanner.nextLine().toLowerCase();
            if (respuesta.equals("si")) {
                pelearMonstruo();
            } else if (respuesta.equals("no")) {
                System.out.println("Te has retirado de la pelea...");
            } else {
                throw new IllegalArgumentException("Respuesta invalida!");
            }
        } catch (Exception e) {
            System.out.println("ERR::" + e.getMessage());
        }
    }

    private void pelearMonstruo() {
        if (random.nextBoolean()) {
            System.out.println("Le has ganado al monstruo!");
        } else {
            int damage = random.nextInt(15) + 1;
            salud -= damage;
            System.out.println("El monstruo te cuerio! Tomaste " + damage + " de daño adicional.");
        }
    }

    private void verInventario() {
        if (inventario.isEmpty()) {
            System.out.println("Tu inventario esta vacio!");
        } else {
            System.out.println("Tu tienes: " + inventario);
        }
    }

    private void curar() {
        if (inventario.contains("Pocion")) {
            inventario.remove("Pocion");
            System.out.println("Has usado la pocion para curarte!");
            recuperarSalud(20);
        } else {
            System.out.println("No tienes pocion para curarte!");
        }
    }

    private void recuperarSalud(int cantidad) {
        if (salud >= 100) {
            System.out.println("Ya estas completamente recuperado!");
        } else {
            salud += 1;
            recuperarSalud(cantidad - 1);
        }
    }

    public static void main(String[] args) {
        JuegoAventura juego = new JuegoAventura();
        juego.iniciarJuego();
    }
}