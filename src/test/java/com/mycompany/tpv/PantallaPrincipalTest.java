/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.tpv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andre
 */
public class PantallaPrincipalTest {

    public PantallaPrincipalTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void PruebaConexionBD() {
        PantallaPrincipal pantallaprincipal = new PantallaPrincipal();
        List familias = pantallaprincipal.familias.getFamilias();

        List<String> ejemplo = Arrays.asList("Refrescos", "Cafés", "Bollería", "Cervezas",
                "Tapas", "Snacks", "Aguas", "Alcohol", "Infusiones", "Vinos");

        assertEquals(ejemplo, familias);
    }

    @Test
    public void pruebaCalculoNumeroPaginas() {

        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();

        for (int i = 0; i < 20; i++) {
            pantallaPrincipal.familias.getFamilias().add("familia");
        }

        int paginasEsperadas = 7;
        System.out.println("Número de páginas esperadas: " + paginasEsperadas);

        pantallaPrincipal.conexionBD();

        int familiasTotales = pantallaPrincipal.familias.getFamilias().size();
        System.out.println("Cantidad de familias: " + familiasTotales);

        int paginasCalculadas = pantallaPrincipal.familias.getNumeroPaginas();
        System.out.println("Número de páginas: " + paginasCalculadas);

        assertEquals(paginasEsperadas, paginasCalculadas);
    }
    
    @Test
    public void PruebaProductosAsociados() {

        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        
        String familiaSeleccionada = ""+pantallaPrincipal.familias.getFamilias().get(6);
        System.out.println(familiaSeleccionada);
        
        Productos productosAsociados = pantallaPrincipal.productosHM.get(familiaSeleccionada);
        
        assertEquals(false, productosAsociados.getProductos().isEmpty());
        assertEquals("Fontvella", productosAsociados.getProductos().get(1).getNombre());
        assertEquals("1.00", productosAsociados.getProductos().get(0).getPrecio());
    }    
    
    @Test
    public void PruebaLineaTicket() {

        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        Producto producto = new Producto("Bocata","3.01");
        Productos productos = new Productos(pantallaPrincipal.panelGeneralProductos, 
                                            pantallaPrincipal.panelProductos);
        productos.imprimePrecio(producto);
        productos.imprimePrecio(producto);
        productos.imprimePrecio(producto);
        
        assertEquals("Bocata", VariablesGenerales.lineasTicket.get(0).getNombreProducto());
        assertEquals(3.01, VariablesGenerales.lineasTicket.get(0).getPrecioProducto());
        assertEquals(1, VariablesGenerales.lineasTicket.get(0).getCantidadProducto());
    }    
    
    @Test
    public void PruebaPrecioTotal() {

        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        Producto producto = new Producto("Bocata","3.01");
        Producto producto1 = new Producto("Pizza","5.21");
        Producto producto2 = new Producto("Ensaladilla","4.00");
        Producto producto3 = new Producto("ColaCao","2.58");
        
        Productos productos = new Productos(pantallaPrincipal.panelGeneralProductos, 
                                            pantallaPrincipal.panelProductos);
        productos.imprimePrecio(producto);
        productos.imprimePrecio(producto);
        productos.imprimePrecio(producto);
        productos.imprimePrecio(producto1);
        productos.imprimePrecio(producto2);
        productos.imprimePrecio(producto2);
        productos.imprimePrecio(producto3);
        productos.imprimePrecio(producto3);
        productos.imprimePrecio(producto3);
        productos.imprimePrecio(producto3);
        
        assertEquals(32.56, VariablesGenerales.totalTicket);
    }    
}
