/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import simulador.model.Particion;
import simulador.model.Proceso;
import simulador.model.ColaListo;
import simulador.model.ColaNuevo;

/**
 *
 * @author Amadeo
 */
public class Memoria {

    private final List<Proceso> listProceso;
    private final List<Particion> listParticion;
    private Integer tamMemoria;
    private Boolean tipoParticion;
    private final int tamMaximo = 1000;
    private final int tamMinimo = 50;
    private final int tamanoSo;

    public Memoria(Integer tamaño) {

        if (tamaño > this.tamMaximo) {
            this.tamMemoria = this.tamMaximo;
        }

        if (tamaño < this.tamMinimo) {
            this.tamMemoria = this.tamMinimo;
        }

        this.tamMemoria = tamaño;

        this.tamanoSo = (int) (this.tamMemoria * 0.10);

        this.listParticion = new ArrayList<>();
        this.listProceso = new ArrayList<>();
    }

    //---------------------REALIZAR CONTROLES PARA LA CARGA PARTICIONES----------------//
    public void ingresarParticion(Integer tamProceso, Integer direccionInicial) {

        if (tamProceso > this.tamMemoria) {

        }
        Particion particion = new Particion(tamProceso, direccionInicial);

    }

    //POR EL MOMENTO LAS PARTICIONES NO TIENEN NOMBRE NI ID POR EL MOMENTO
    /*LOS ALGORITMOS DE CONTROL DE PARTICION LO QUE HACEN ES: 
    SI LA PARTICION NUEVA ES MAS GRANDE QUE EL TAMAÑO DE MEMORIA LIBRE Y EL TAMAÑO DE MEMORIA LIBRE
    ES DISTINTO DE CERO. ASIGNA UNA NUEVA PARTICION PERO CON EL TAMAÑO DE MEMORIA LIBRE
        
     */
    public void cargarParticion(int tamañoParticion) {
        int tamParticion;
        if (tamañoParticion > calcularMemoriaLibre() && calcularMemoriaLibre() != 0) {
            tamParticion = calcularMemoriaLibre();
            Particion particion = new Particion(tamParticion);
            this.listParticion.add(particion);
        } else {
            if (tamañoParticion < calcularMemoriaLibre()) {
                tamParticion = tamañoParticion;
                Particion particion = new Particion(tamParticion);
                this.listParticion.add(particion);
            }

        }
    }

    public void addProceso(Proceso proceso) {
        this.listProceso.add(proceso);
    }

    public List<Proceso> getListProceso() {
        return this.listProceso;
    }

    public void addParticion(Particion particion) {
        if (particion.getTamParticion() > calcularMemoriaLibre() && calcularMemoriaLibre() != 0) {
            particion.setTamParticion(calcularMemoriaLibre());
        }

        this.listParticion.add(particion);
    }

    public List<Particion> getListParticion() {
        return this.listParticion;
    }

    private int calcularMemoriaLibre() {
        int cantidadMemoriaLibre = 0;
        int acumuladorTamañoParticiones = 0;
        if (this.listParticion.isEmpty()) {
            cantidadMemoriaLibre += this.tamMemoria;
        } else {
            acumuladorTamañoParticiones = this.listParticion.stream().map((p) -> p.getTamParticion()).reduce(acumuladorTamañoParticiones, Integer::sum);
            cantidadMemoriaLibre += (this.tamMemoria - acumuladorTamañoParticiones);
        }
        return cantidadMemoriaLibre;
    }

    public void ordenarListaParticiones() {
        Collections.sort(listParticion);
    }

    public void runBestFit(List<Proceso> colaNuevo) {

        ordenarListaParticiones();
        int clock = 0;
        Iterator<Proceso> itProceso = colaNuevo.iterator();
        while(!colaNuevo.isEmpty()){
        while (itProceso.hasNext()) {
            imprimirProcesoPorConsola(clock);
            Proceso proceso = itProceso.next();
            int tamEfectivoLibre = 99999;
            int resguardoIndexParticion = 0;
            Boolean particionValida = false;

            for (Particion particion : this.listParticion) {
                if (particion.getEstado()) {
                    final int calculoTamParticion = particion.getTamParticion() - proceso.getTamProceso();
                    if (calculoTamParticion >= 0 && calculoTamParticion <= tamEfectivoLibre) {
                        tamEfectivoLibre = calculoTamParticion;
                        resguardoIndexParticion = this.listParticion.indexOf(particion);
                        particionValida = true;
                    }
                }
            }

            if (particionValida) {
                this.listParticion.get(resguardoIndexParticion).addProceso(proceso);
                itProceso.remove();
//                break;
            }

            clock += 1;
            liberarMemoria(clock);

        }
        }
        imprimirProcesoPorConsola(clock);
    }

    public void liberarMemoria(int clock) {
        for (Particion particion : this.listParticion) {
            if (particion.getEstado().equals(false)) {
                if (particion.getProceso().getTamProceso() == clock) {
                    particion.setEstado(true);
                }
            }
        }
    }

    private void imprimirProcesoPorConsola(int clock) {

        System.out.println("GENERAL CLOCK = " + clock);
        this.listParticion.forEach((particion) -> {
            if (particion.procesoIsNull()) {
                System.out.println(particion.getTamParticion() + " = [ ]");
            } else {
                System.out.println(particion.getTamParticion() + " = " + particion.getProceso().getNombreProceso());
            }
        });

    }
}
