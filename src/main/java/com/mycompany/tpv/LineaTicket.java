/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

/**
 * Esta clase recibe los datos de un producto como el nombre
 * el precio y la cantidad.
 * @author andre
 */
public class LineaTicket {
    String nombreProducto;
    double precioProducto;
    int cantidadProducto;

    /**
     * Recibe los datos de un producto.
     * 
     * @param nombreProducto String, representa el nombre de un producto.
     * @param precioProducto double, representa el precio de un producto.
     * @param cantidadProducto int, representa la cantidad de un producto.
     */
    public LineaTicket(String nombreProducto, double precioProducto, int cantidadProducto) {
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.cantidadProducto = cantidadProducto;
    }

    /**
     * Devuelve el nombre del producto.
     * @return String
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Permite modificar el nombre del producto.
     * @param nombreProducto String
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Devuelve el precio del producto.
     * @return double
     */
    public double getPrecioProducto() {
        return precioProducto;
    }
    
    /**
     * Permite modificar el precio del producto.
     * @param precioProducto double
     */
    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    /**
     * Devuelve la cantidad del producto.
     * @return int
     */    
    public int getCantidadProducto() {
        return cantidadProducto;
    }

    /**
     * Permite modificar la cantidad del producto.
     * @param cantidadProducto int
     */
    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    } 
}
