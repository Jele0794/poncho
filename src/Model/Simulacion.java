package Model;

/**
 * @author Edmundo
 * @version 1.0
 * @package Model
 */

public class Simulacion {

    private String nombreHotel, empleado;
    private int idCircuito, precioCuarto, precioDesayuno, precioLugar;

    public Simulacion(int idCircuito, int precioCuarto, int precioDesayuno, int precioLugar, String nombreHotel){
        this.idCircuito = idCircuito;
        this.precioCuarto = precioCuarto;
        this.precioDesayuno = precioDesayuno;
        this.precioLugar = precioLugar;
        this.nombreHotel = nombreHotel;

    }

}
