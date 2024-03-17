/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

/**
 * Representa un producto recibiendo un nombre
 * y un precio.
 * @author andre
 */
class Producto {
    String nombre;
    String precio;

    /**
     * Recibe el nombre y el precio.
     * @param nombre String, representa el nombre del producto.
     * @param precio String, representa el precio del producto.
     */
    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Devuelve el nombre del producto.
     * @return String
     */    
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Permite modificar el nombre del producto.
     * @param nombreProducto String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el precio del producto.
     * @return String
     */    
    public String getPrecio() {
        return precio;
    }

    /**
     * Permite modificar el precio del producto.
     * @param precioProducto String
     */    
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Devuelve el nombre del producto.
     * @return String
     */
    @Override
    public String toString() {
        return nombre;
    }
    
    /**
     * Devuelve el precio del producto
     * como un double.
     * @return double
     */
    public double getPrecioDouble(){
        return Double.parseDouble(precio);
    }
}
