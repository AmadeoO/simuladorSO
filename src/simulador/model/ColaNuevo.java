/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amadeo
 */
public class ColaNuevo {
     private List<Proceso> listProceso;

    public ColaNuevo() {
        this.listProceso = new ArrayList<Proceso>();
    }

    public void cargarProceso(String nombreProceso, Integer tamProceso, Integer tiempoArribo) {
        Proceso proceso = new Proceso(nombreProceso, tamProceso, tiempoArribo);
        this.listProceso.add(proceso);
    }

    public void addProceso(Proceso proceso) {
        System.out.println(proceso.getNombreProceso());
        this.listProceso.add(proceso);
    }

    //DEBEría DEVOLVER UNA LISTA ORDENADA
    public List<Proceso> getListProceso() {
        return listProceso;
    }

    public Proceso getProceso(int index) {
        if(index>this.listProceso.size()){
            return this.listProceso.get(this.listProceso.size()-1);
        }else{
        return this.listProceso.get(index);
        }
        
    }

    public Boolean isEmpty() {
        return this.listProceso.isEmpty();
    }
    
    public int cantidadProcesos(){
        return this.listProceso.size();
    }
}
