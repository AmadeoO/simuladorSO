/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador.model;

import java.util.ArrayList;
import java.util.List;
import simulador.model.Particion;
import simulador.model.Proceso;
import simulador.model.ColaListo;

/**
 *
 * @author Amadeo
 */
public class Memoria {

    private List<Proceso> listProceso;
    private List<Particion> listParticion;
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

        this.listParticion = new ArrayList<Particion>();
        this.listProceso = new ArrayList<Proceso>();
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

    public void addParticion(Particion particion) {
        if (particion.getTamParticion() > calcularMemoriaLibre() && calcularMemoriaLibre() != 0) {
            particion.setTamParticion(calcularMemoriaLibre());
        }

        this.listParticion.add(particion);
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

    public void runFirstFit(ColaListo colaListo) {
        //la lista debe estar ordenada
        int indiceColaListo = 0;
        int clock = 0;
        int instance = 0;

        while (!colaListo.isEmpty()) {
            if (indiceColaListo > colaListo.getListProceso().size() - 1) {
                indiceColaListo = 0;
            }
            System.out.println("GENERAL CLOCK = " + clock);
            this.listParticion.forEach((particion) -> {
                if (particion.procesoIsNull()) {
                    System.out.println(particion.getTamParticion() + " = [ ]");
                } else {
                    System.out.println(particion.getTamParticion() + " = " + particion.getProceso().getNombreProceso());
                }
            });

            for (Particion particion : this.listParticion) {
                final boolean procesoEnColaListo = colaListo.getProceso(indiceColaListo).getEstadoProceso().equalsIgnoreCase("listo");
                if (procesoEnColaListo) {
                    final Integer tamProceso = colaListo.getProceso(indiceColaListo).getTamProceso();
                    final Integer tamParticion = particion.getTamParticion();
                    final boolean paticionLibre = particion.getEstado().equalsIgnoreCase("libre");

                    if (paticionLibre) {
                        if (tamProceso <= tamParticion) {
                            colaListo.getProceso(indiceColaListo).setEstadoProceso("corriendo");
                            particion.setEstado("ocupado");
                            particion.addProceso(colaListo.getProceso(indiceColaListo));
                            

                        }
                    }
                }
            }
            //Actualiza el tiempo de Arribo descontando en 1
            if (clock >= 2) {
                this.listParticion.forEach((particion) -> {
                    particion.getProceso().upgradeTiempoArribo();
                });

            }

            clock += 1;
            indiceColaListo += 1;
        }

    }

}
