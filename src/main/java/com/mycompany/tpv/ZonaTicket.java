/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Panel que mostrará la tabla de productos en la ventana
 * @author andre
 */
public class ZonaTicket extends JPanel {

    JTable tablaTicket;
    int x, y, ancho, alto;

    /**
     * Recibe la posición y el tamaño que definirán el panel
     * @param x int
     * @param y int
     * @param ancho int
     * @param alto int
     */
    public ZonaTicket(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        
        //Características del panel
        setLayout(null);
        setBounds(x, y, ancho, alto);
        setVisible(true);
        ponTablaTicket();
    }

    /**
     * El método ponTablaTicket crea una tabla usando como modelo
     * el "modeloTablaTicket" de la clase VariablesGenerales y lo
     * agrega al panel "zonaTicket"
     */
    private void ponTablaTicket() {
        tablaTicket = new JTable();

        //El scrollPane permite desplazar los registros de la tabla
        JScrollPane scrollPane = new JScrollPane(tablaTicket);
        scrollPane.setBounds(0, 0, ancho, alto);
        scrollPane.setBackground(Color.white);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Su pedido"));

        //Campos que aparecen en la cabezera de la tabla
        Object[] header = new Object[]{"Nombre", "Precio", "Cantidad"};
        VariablesGenerales.modeloTablaTicket = new DefaultTableModel(header, 0);

        tablaTicket.setModel(VariablesGenerales.modeloTablaTicket);
        add(scrollPane);
    }
}
