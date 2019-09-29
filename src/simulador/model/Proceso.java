package simulador.model;

import java.util.List;

/**
 *
 * @author Amadeo
 */
public class Proceso {

    private String nombreProceso; // luego reemplazar por id
    private Integer tamProceso;
    private Integer tiempoArribo;
    private String prioridad;
    private List<Integer> tiempoEs;
    private Integer tiempoAbandonoCpu;
    private String estadoProceso;

    public Proceso(Integer tamProceso, Integer tiempoArribo, String prioridad) {
        this.tamProceso = tamProceso;
        this.tiempoArribo = tiempoArribo;
        this.prioridad = prioridad;
        this.estadoProceso = "listo";
    }

    public Proceso(String nombreProceso, Integer tiempoArribo, Integer tamProceso) {
        this.nombreProceso = nombreProceso;
        this.tamProceso = tamProceso;
        this.tiempoArribo = tiempoArribo;
        this.estadoProceso = "listo";
    }
//******************************************************************************
    public String getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(String estadoProceso) {
        this.estadoProceso = estadoProceso;
    }
    
    

    public void upgradeTiempoArribo() {
        if (this.tiempoArribo != 0) {
            this.tiempoArribo -= 1;
        }
    }

    public Integer getTiempoArribo() {
        return tiempoArribo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public List<Integer> getTiempoEs() {
        return tiempoEs;
    }

    public Integer getTiempoAbandonoCpu() {
        return tiempoAbandonoCpu;
    }

    public void setTamProceso(Integer tamaño) {
        this.tamProceso = tamaño;
    }

    public void setTiempoArribo(Integer tiempoArribo) {
        this.tiempoArribo = tiempoArribo;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setTiempoEs(List<Integer> tiempoEs) {
        this.tiempoEs = tiempoEs;
    }

    public void setTiempoAbandonoCpu(Integer tiempoAbandonoCpu) {
        this.tiempoAbandonoCpu = tiempoAbandonoCpu;
    }

    public String getNombreProceso() {
        return this.nombreProceso;
    }

    public Integer getTamProceso() {
        return this.tamProceso;
    }

}
