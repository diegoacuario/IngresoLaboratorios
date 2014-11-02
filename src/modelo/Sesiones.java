package modelo;

/**
 * @web http://www.diegoacuario.blogspot.com
 * @author diegoacuario
 */
public class Sesiones {

    private Integer idSesion;
    private short bloqueada;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private Equipos idEquipo;
    private Usuarios idUsuario;

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public short getBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(short bloqueada) {
        this.bloqueada = bloqueada;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }


    
    public Equipos getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipos idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
