/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author AYLEEN ROMERO PATIÑO
 */
public class Usuario {
    private String cedula;
    private String clave;
    private String nombres;
    private String apellidos;    
    private String correo;    
    private String celular;    
    private int rolUsuario;

    public Usuario() {
    }

    public Usuario(String cedula, String clave, String nombres, String apellidos, String correo, String celular, int rolUsuario) {
        this.cedula = cedula;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.celular = celular;
        this.rolUsuario = rolUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(int rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
    
    
    
}
