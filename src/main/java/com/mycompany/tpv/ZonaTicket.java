/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Moncho
 */
public class ZonaTicket extends JPanel {

    JTable tablaTicket;
    int x, y, ancho, alto;
    

    public ZonaTicket(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        setLayout(null);
        setBounds(x, y, ancho, alto);
        setOpaque(true);
        setBackground(Color.red);
        setVisible(true);

        ponTablaTicket();

        JLabel jlabel = new JLabel();
        jlabel.setText("Hooooola");
        jlabel.setBounds(0, 0, 100, 10);
        jlabel.setVisible(true);

        add(jlabel);
    }

    private void ponTablaTicket() {
        tablaTicket = new JTable();
        tablaTicket.setBounds(0, 0, ancho, alto / 2);
        Object[] header = new Object[]{"Nombre", "Precio", "Cantidad"};
        VariablesGenerales.modeloTablaTicket = new DefaultTableModel(header, 0);
        
        tablaTicket.setModel(VariablesGenerales.modeloTablaTicket);
        tablaTicket.setVisible(true);
        add(tablaTicket);
    }
}


