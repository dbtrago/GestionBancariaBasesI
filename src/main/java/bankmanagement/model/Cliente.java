package bankmanagement.model;
import java.util.Date;

public class Cliente {
    private String cuenta;
    private String nombre;
    private String sucursal;
    private String cedula;
    private String balance;

    public Cliente(String cuenta, String nombre, String sucursal, String cedula, String balance) {
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.sucursal = sucursal;
        this.cedula = cedula;
        this.balance = balance;
    }

    // Getters y setters
    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSucursal() { return sucursal; }
    public void setSucursal(String sucursal) { this.sucursal = sucursal; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getBalance() { return balance; }
    public void setBalance(String balance) { this.balance = balance; }
}
