package modelo;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Laboratorios {

    private int idLaboratorio;
    private String nombre;
    private String codigo;
    private String descripcion;
    private int bloqueado;

    public Laboratorios(int idLaboratorio, String nombre, String codigo, String descripcion,int bloqueado) {
        this.idLaboratorio = idLaboratorio;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.bloqueado = bloqueado;
    }

    public int getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(int bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Laboratorios(String nombre, String codigo, String descripcion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Laboratorios() {
    }

    public int getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setIdLaboratorio(int idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
