package Model;


import java.sql.*;

public class Model {

    Connection conn = null;
    Statement stmt = null;

    public Model() throws Exception {

        ConectarDataBase();

    }//end constructor

    public void ConectarDataBase() throws Exception {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://127.0.0.1:3307/?user=root";
            String usuarioDB = "root";
            String passwordDB = "";

            conn = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
            System.out.println("Conexion Exitosa");

            conn.setAutoCommit(false);
            System.out.println("Autocomit FALSE");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar");
        }


    }

    public boolean verificarAutorizacion(String nombre) {


        Statement comando = null;
        try {
            comando = conn.createStatement();

            ResultSet empleados = comando.executeQuery("SELECT * FROM Ponchito.EMPLEADO;");


            while (empleados.next()) {
                if (nombre.equals(empleados.getString(1))) {
                    return true;
                }

            }//end while
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void consultaFolleto(int opcion){

        Statement comando = null;
        try {
            comando = conn.createStatement();
            if(opcion == 1) {
                ResultSet consulta = comando.executeQuery("SELECT * FROM Ponchito.FECHACIRCUITO, Ponchito.CIRCUITO WHERE Ponchito.FECHACIRCUITO.IDCIRCUITO = Ponchito.CIRCUITO.IDCIRCUITO;");
                System.out.println("Descripción | Ciudad Salida | País Salida | Ciudad Destino | País Destino | Duración Circuito | Precio | Fecha de Salida");
                while (consulta.next()) {
                    System.out.println(consulta.getString(5) +"\t|"+ consulta.getString(6) +"\t|"+ consulta.getString(7) +"\t|"+ consulta.getString(8) +"\t|"+ consulta.getString(9) +"\t|"+ consulta.getString(10) +"\t|"+ consulta.getString(11) +"\t|"+ consulta.getString(2));
                }//end while
            }else if(opcion == 2){
                ResultSet consulta = comando.executeQuery("SELECT * FROM Ponchito.CIUDAD;");
                System.out.println("Ciudad \t|\t País");
                while (consulta.next()) {
                    System.out.println(consulta.getString(1) +"\t|"+ consulta.getString(2));
                }//end while
            } else if (opcion == 3){
                ResultSet consulta = comando.executeQuery("SELECT NOMHOTEL, NOMCIUDAD, DIRHOTEL, PRECIOCUARTO FROM Ponchito.HOTEL;");
                System.out.println("Nombre Hotel \t|\t Ciudad \t|\t Dirección Hotel \t|\t Precio p/Cuarto");
                while (consulta.next()) {
                    System.out.println(consulta.getString(1) +"\t|"+ consulta.getString(2) +"\t|"+ consulta.getString(3) +"\t|"+ consulta.getString(4));
                }//end while
            }else if (opcion == 4){

            } else if (opcion == 5){

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
