package modelo;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Equipos {

    private String mac;
    private String ip;
    private int numero;
    private int estado;
    private int idEquipo;
    private int bloqueado;
    private Laboratorios idLaboratorio;

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Laboratorios getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(Laboratorios idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

}
