/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Crea variables que son accesibles por 
 * todo el programa.
 * @author Moncho
 */
public class VariablesGenerales {
    
    //El precio total de todos los productos seleccionados.
    public static double totalTicket;
    
    //Lista que almacena las lineas del ticket las 
    //cuales se imprimirán en la factura.
    public static List<LineaTicket> lineasTicket = new ArrayList<>();
    
    //modelo de la tabla que almacena los productos.
    public static DefaultTableModel modeloTablaTicket;
    
    //Al presionar el botón de borrar esta variable se vuelve
    //true y se usa para eliminar productos de la lista
    public static boolean restar;
}
