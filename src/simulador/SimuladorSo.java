/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulador;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import simulador.model.ColaListo;
import simulador.model.ColaNuevo;
import simulador.model.Memoria;
import simulador.model.Particion;
import simulador.model.Proceso;

/**
 *
 * @author Amadeo
 */
public class SimuladorSo {

    private static Proceso pro1;
    private static Proceso pro2;
    private static Proceso pro3;
    private static Particion part1;
    private static Particion part2;
    private static Particion part3;
    private static Particion part4;
    private static ColaNuevo colaNuevo;
    private static Memoria memoria;

    private static void initialProcesses() {
        pro1 = new Proceso("p1", 0, 3);
        pro2 = new Proceso("p2", 1, 6);
        pro3 = new Proceso("p3", 2, 2);

    }

    private static void initialPartition() {
        part1 = new Particion(2);
        part2 = new Particion(7);
        part3 = new Particion(5);
        part4 = new Particion(4);
    }

//    private static void setUpColaNuevo() {
//        colaNuevo = new ColaNuevo();
//        colaNuevo.addProceso(pro1);
//        colaNuevo.addProceso(pro2);
//        colaNuevo.addProceso(pro3);
//    }
    
    private static void addProcessInMemory(){
        memoria.addProceso(pro1);
        memoria.addProceso(pro2);
        memoria.addProceso(pro3);
    }

    private static void setUpMemoria() {
        memoria = new Memoria(20);
        memoria.addParticion(part1);
        memoria.addParticion(part2);
        memoria.addParticion(part3);
        memoria.addParticion(part4);
    }

    private static void executeMethodInOrder() {
        initialProcesses();
        initialPartition();
        setUpMemoria();
        addProcessInMemory();
        
        
    }

    public static void main(String[] args) {
        executeMethodInOrder();
        memoria.runBestFit(memoria.getListProceso());
        
        
        

    }
}
