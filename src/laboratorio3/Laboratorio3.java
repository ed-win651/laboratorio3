/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package laboratorio3;

import java.util.Scanner;

/**
 *
 * @author ed
 */
public class Laboratorio3 {
    static Scanner scanner = new Scanner(System.in);
    static int stock = 0; // para llevar el control de las existencias

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int eleccionUsuario;
        do {
            eleccionUsuario = opcionMenuPrincipal(); // corregido el nombre
            if (eleccionUsuario >= 1 && eleccionUsuario <= 3) {
                posesoARealizar(eleccionUsuario);
            } else if (eleccionUsuario != 0) {
                System.out.println("Opción no válida.");
            }
        } while (eleccionUsuario != 0);

        System.out.println("Programa finalizado.");

        // TODO code application logic here
    }

    public static int opcionMenuPrincipal() {
        System.out.println("\n******** MENÚ PRINCIPAL ********");
        System.out.println("[1] Registrar computadoras");
        System.out.println("[2] Salida de computadoras");
        System.out.println("[3] Stock de existencias");
        System.out.println("[0] Salir");
        System.out.print("Elige una opción: ");
        return scanner.nextInt();
    }

    public static void posesoARealizar(int opcion) {
        switch (opcion) {
            case 1:
                int ingreso = registrarComputadora();
                stock += ingreso;
                System.out.println("Se registraron " + ingreso + " computadoras.");
                break;
            case 2:
                int salida = salidaComputadora();
                try {
                    ValidarStock(salida); // Lanza la excepción si no hay stock suficiente
                    stock -= salida;
                    System.out.println("Se retiraron " + salida + " computadoras.");
                } catch (NoHayStockException ex) {
                    System.err.println(ex.getMessage());
                }

                break;
            case 3:
                System.out.println("Stock actual de computadoras: " + stock);
                break;
        }
    }

    public static void ValidarStock(int cantidadRetirar) throws NoHayStockException {
        if (cantidadRetirar > stock) {
            throw new NoHayStockException();
        }
    }

    public static int solicitarNumeroEnteroPositivo(String titulo, String mensajePrompt) {
        System.out.println("************** " + titulo + " **************");
        int numero;
        while (true) {
            System.out.print(mensajePrompt);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero > 0) {
                    break;
                } else {
                    System.out.println("Debe ser un número mayor que 0.");
                }
            } else {
                System.out.println("¡Error! Eso no es un número entero.");
                scanner.next();
            }
        }
        return numero;
    }

    public static int registrarComputadora() {
        return solicitarNumeroEnteroPositivo("REGISTRAR", "Ingresa el número de computadoras: ");
    }

    public static int salidaComputadora() {
        return solicitarNumeroEnteroPositivo("SALIDA", "Ingresa el número de computadoras a retirar: ");
    }
}
