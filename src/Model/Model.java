package Model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Vector;

public class Model {

    Connection conn = null;
    Statement stmt = null;

    public Model() throws Exception {

        ConectarDataBase();
        stmt = conn.createStatement();

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

    public void consultaFolleto(int opcion) {

        Statement comando = null;
        try {
            comando = conn.createStatement();
            if (opcion == 1) {
                ResultSet consulta = comando.executeQuery("SELECT * FROM Ponchito.FECHACIRCUITO, Ponchito.CIRCUITO WHERE Ponchito.FECHACIRCUITO.IDCIRCUITO = Ponchito.CIRCUITO.IDCIRCUITO;");
                System.out.println("Descripción | Ciudad Salida | País Salida | Ciudad Destino | País Destino | Duración Circuito | Precio | Fecha de Salida");
                int i = 1;
                while (consulta.next()) {
                    System.out.println(i + ") " + consulta.getString(5) + "\t|" + consulta.getString(6) + "\t|" + consulta.getString(7) + "\t|" + consulta.getString(8) + "\t|" + consulta.getString(9) + "\t|" + consulta.getString(10) + "\t|" + consulta.getString(11) + "\t|" + consulta.getString(2));
                    i++;
                }//end while
            } else if (opcion == 2) {
                ResultSet consulta = comando.executeQuery("SELECT * FROM Ponchito.CIUDAD;");
                System.out.println("Ciudad \t|\t País");
                int i = 1;
                while (consulta.next()) {
                    System.out.println(i + ") " + consulta.getString(1) + "\t|" + consulta.getString(2));
                    i++;
                }//end while
            } else if (opcion == 3) {
                ResultSet consulta = comando.executeQuery("SELECT NOMHOTEL, NOMCIUDAD, DIRHOTEL, PRECIOCUARTO FROM Ponchito.HOTEL;");
                System.out.println("Nombre Hotel \t|\t Ciudad \t|\t Dirección Hotel \t|\t Precio p/Cuarto");
                while (consulta.next()) {
                    System.out.println(consulta.getString(1) + "\t|" + consulta.getString(2) + "\t|" + consulta.getString(3) + "\t|" + consulta.getString(4));
                }//end while
            } else if (opcion == 4) {

            } else if (opcion == 5) {

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void simulacion() {
        Statement comando = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<String> ciudades = new Vector<>();

        try {
            comando = conn.createStatement();
            ResultSet circuito = comando.executeQuery("SELECT * FROM Ponchito.FECHACIRCUITO, Ponchito.CIRCUITO WHERE Ponchito.FECHACIRCUITO.IDCIRCUITO = Ponchito.CIRCUITO.IDCIRCUITO;");
            System.out.println("Descripción | Ciudad Salida | País Salida | Ciudad Destino | País Destino | Duración Circuito | Precio | Fecha de Salida");
            int i = 1;
            while (circuito.next()) {
                System.out.println(i + ") " + circuito.getString(5) + "\t|" + circuito.getString(6) + "\t|" + circuito.getString(7) + "\t|" + circuito.getString(8) + "\t|" + circuito.getString(9) + "\t|" + circuito.getString(10) + "\t|" + circuito.getString(11) + "\t|" + circuito.getString(2));
                i++;
            }//end while
            System.out.println("Seleccione el circuito que desea recorrer: ");
            int ciudad = Integer.parseInt(br.readLine());


            ResultSet circuitoConsulta = comando.executeQuery("SELECT IDCIRCUITO, CIUDADLLEGADA  FROM Ponchito.CIRCUITO;");
            while (circuitoConsulta.next()) {
                String temp = circuitoConsulta.getString(1);
                ciudades.add(temp);
            }
            System.out.println(circuitoConsulta.getString(2));
            String ciudadSelect = ciudades.get(ciudad-1);

            ResultSet hotelConsulta = comando.executeQuery("SELECT * FROM Ponchito.HOTEL where NOMCIUDAD = '" + ciudadSelect + "';");
                System.out.println("Hotel | Ciudad | Pais | Numero Cuartos | Precio p/cuarto | Precio Desayuno");
            int j=1;
            while (hotelConsulta.next()){
                System.out.println(j + ") " + hotelConsulta.getString(1) + "\t|" + hotelConsulta.getString(2) + "\t|" + hotelConsulta.getString(3) + "\t|" + hotelConsulta.getString(4) + "\t|" + hotelConsulta.getString(5) + "\t|" + hotelConsulta.getString(6));
                j++;
            }
            System.out.println("Seleccione el hotel que guste:");
            int hotel = Integer.parseInt(br.readLine());

            ResultSet nombreHotelCons = comando.executeQuery("SELECT NOMHOTEL FROM Ponchito.HOTEL");
            ciudades = null;
            while (nombreHotelCons.next()){
                ciudades.add(nombreHotelCons.getString(1));
            }
            String hotelSelect = ciudades.get(hotel-1);






            System.out.println("Ciudad seleccionada: " + ciudadSelect);


        } catch (Exception e) {
            e.getMessage();
        }
    }



    public void crearSimulacion(){
        Statement comando = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<Integer> tempI = new Vector<>();
        Vector<Integer> tempI2 = new Vector<>();
        Vector<String> tempS = new Vector<>();
        try{
            comando = conn.createStatement();
            ResultSet circuito = comando.executeQuery("SELECT * FROM Ponchito.FECHACIRCUITO, Ponchito.CIRCUITO WHERE Ponchito.FECHACIRCUITO.IDCIRCUITO = Ponchito.CIRCUITO.IDCIRCUITO;");
            System.out.println("Descripción | Ciudad Salida | País Salida | Ciudad Destino | País Destino | Duración Circuito | Precio | Fecha de Salida");
            int i = 1;
            while (circuito.next()) {
                System.out.println(i + ") " + circuito.getString(5) + "\t|" + circuito.getString(6) + "\t|" + circuito.getString(7) + "\t|" + circuito.getString(8) + "\t|" + circuito.getString(9) + "\t|" + circuito.getString(10) + "\t|" + circuito.getString(11) + "\t|" + circuito.getString(2));
                tempI.add(circuito.getInt(1));
                tempS.add(circuito.getString(8));
                i++;
            }//end while
            System.out.println("Seleccione el circuito que desea recorrer: ");
            int circuitoElegido = Integer.parseInt(br.readLine());
            int idCircuito = tempI.get(circuitoElegido-1);
            String ciudadSelect = tempS.get(circuitoElegido-1);

            ResultSet hotelConsulta = comando.executeQuery("SELECT * FROM Ponchito.HOTEL where NOMCIUDAD = '" + ciudadSelect + "';");
            System.out.println("Hotel | Ciudad | Pais | Numero Cuartos | Precio p/cuarto | Precio Desayuno");
            int j=1;
            tempI = new Vector<>();
            tempS = new Vector<>();
            while (hotelConsulta.next()){
                System.out.println(j + ") " + hotelConsulta.getString(1) + "\t|" + hotelConsulta.getString(2) + "\t|" + hotelConsulta.getString(3) + "\t|" + hotelConsulta.getString(4) + "\t|" + hotelConsulta.getString(5) + "\t|" + hotelConsulta.getString(6));
                tempI.add(hotelConsulta.getInt(6));
                tempI2.add(hotelConsulta.getInt(7));
                tempS.add(hotelConsulta.getString(1));
                j++;
            }
            System.out.println("Seleccione el hotel que guste:");
            int hotelElegido = Integer.parseInt(br.readLine());
            String hotelSelect = tempS.get(hotelElegido-1);
            int precioHabita = tempI.get(hotelElegido-1);
            int precioDesayuno = tempI2.get(hotelElegido-1);

            ResultSet lugarConsulta = comando.executeQuery("SELECT * FROM Ponchito.LUGARAVISITAR WHERE NOMCIUDAD = '"+ciudadSelect+"';");
            System.out.println("Lugar | Ciudad | Pais | Dirección | Descripción | Precio");
            int k = 1;
            tempI = new Vector<>();
            tempS = new Vector<>();
            while (lugarConsulta.next()){
                System.out.println(k+") "+lugarConsulta.getString(1) + "\t|"+lugarConsulta.getString(2) + "\t|"+lugarConsulta.getString(3) + "\t|"+lugarConsulta.getString(4) + "\t|"+lugarConsulta.getString(5) + "\t|"+lugarConsulta.getString(6));
                tempI.add(lugarConsulta.getInt(6));
                tempS.add(lugarConsulta.getString(1));
            }
            System.out.println("Seleccione el lugar que prefiere visitar:");
            int lugarElegido = Integer.parseInt(br.readLine());

            String lugarSelect = tempS.get(lugarElegido-1);
            int precioLugar = tempI.get(lugarElegido-1);

            Simulacion simulacion = new Simulacion(idCircuito, precioHabita, precioDesayuno, precioLugar, hotelSelect);
            System.out.println(simulacion);

            String statement = "INSERT INTO Ponchito.SIMULACION VALUES (1,"+idCircuito+", '"+hotelSelect+"', '"+lugarSelect+"', "+precioHabita+", "+precioDesayuno+", "+precioLugar+", 'Hola' );";
            stmt.executeUpdate( statement );
            conn.commit();
        }catch(Exception e){
            e.printStackTrace();
        }




    }

    public String getCircuitoCiDestino(int opcion) {
        Statement comando = null;
        Vector<String> paises = new Vector<>();
        try {
            comando = conn.createStatement();
            ResultSet consulta = comando.executeQuery("SELECT PAISLLEGADA FROM Ponchito.CIRCUITO;");

            while (consulta.next()) {
                String temp = consulta.getString(1);
                paises.add(temp);
            }

            System.out.println(paises.get(opcion - 1) + " seleccionado.");


        } catch (Exception e) {
            e.getMessage();
        }
        return "hola";

    }

}
