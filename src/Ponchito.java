import Model.Model;
import Model.Cliente;

import java.sql.*;
import java.io.*;


public class Ponchito {

    private static BufferedReader in;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Model model = new Model();
            System.out.println("a) Empleado\nb) Cliente ");
            String s = br.readLine();

            if (s.equals("a")) {
                System.out.println("Ingresa tu nombre para verificar: ");
                String nombre = br.readLine();
                if (model.verificarAutorizacion(nombre)) {
                    System.out.println("Bienvenido " + nombre);
                    System.out.println("¿Cómo te podemos ayudar " + nombre + "?");
                    System.out.println("1) Circuitos \n2) Ciudades \n3) Hoteles");
                    model.consultaFolleto(Integer.parseInt(br.readLine()));
                } else System.out.println("Lo sentimos! No tienes autorización");
            } else if (s.equals("b")) {
                System.out.println("Bienvenido! Favor de proporcionarnos su nombre para una atención más personalizada!");
                String nombreCliente = br.readLine();
                Cliente cliente = new Cliente(nombreCliente);
                System.out.println("¿Cómo podemos ayudarle Sr/Sra " + nombreCliente + "?");
                System.out.println("a) Simulación \nb) Folleto");
                String opcionCliente = br.readLine();
                if (opcionCliente.equals("a")) {
                    model.crearSimulacion();

                } else if (opcionCliente.equals("b")) {
                    System.out.println("1) Circuitos \n2) Ciudades \n3) Hoteles");
                    model.consultaFolleto(Integer.parseInt(br.readLine()));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}