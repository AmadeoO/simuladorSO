/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.model;

/**
 *
 * @author Amadeo
 */
public class Particion {

    //VER LIBRERÍA PARA LOS Id dinamicos
    private Integer idParticion;
    private Integer tamParticion;
    private Integer direccionInicial;
    private String estado;
    private Proceso proceso;
    //FALTA el metodo de la fragmentación interna;
    private Integer fragmentacionInterna;

//-----------constructores
    public Particion(Integer tamParticion, Integer direccionInicial) {
        this.estado = "libre";
        this.tamParticion = tamParticion;
        this.direccionInicial = direccionInicial;
    }

    public Particion(Integer tamParticion) {
        this.tamParticion = tamParticion;
        this.estado = "libre";
        this.proceso=null;
    }

    public Particion() {
    }
//******************************
    
    public Boolean procesoIsNull(){
        if(this.proceso==null){
            return true;
        }else{return false;}
    }
      public Boolean procesoIsNotNull(){
        if(this.proceso==null){
            return false;
        }else{return true;}
    }
    public void addProceso(Proceso p) {
        this.proceso = p;
    }

    public void removeProceso() {
        this.proceso = null;
    }

    public Proceso getProceso() {
        return this.proceso;
    }

    public Integer getIdParticion() {
        return idParticion;
    }

    public Integer getTamParticion() {
        return tamParticion;
    }

    public void setIdParticion(Integer idParticion) {
        this.idParticion = idParticion;
    }

    public void setTamParticion(Integer tamParticion) {
        this.tamParticion = tamParticion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
