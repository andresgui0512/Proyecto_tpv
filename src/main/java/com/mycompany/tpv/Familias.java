/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author andre
 */
public class Familias extends JPanel {

    private JPanel panelFamilias;
    private List<String> familias;
    private final int FAMILIASxPAGINA = 6;
    private HashMap<String, Productos> productosHM = new HashMap<>();
    int paginaActual;
    int numeroPaginas;
    Container contenedorProductos;

    public Familias(Container contenedorProductos, HashMap<String, Productos> productosHM) {
        this.productosHM = productosHM;
        this.contenedorProductos = contenedorProductos;
        familias = new ArrayList();
        creaPaneles();
        botonesPasaPaginaFamilia();
    }

    public List getFamilias() {
        return familias;
    }

    public void setFamilias(List familias) {
        this.familias = familias;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getFAMILIASxPAGINA() {
        return FAMILIASxPAGINA;
    }

    private void creaPaneles() {

        setLayout(null);
        setVisible(true);
        setBackground(new Color(255, 77, 77));
        setVisible(true);

        panelFamilias = new JPanel();
        panelFamilias.setLayout(null);
        panelFamilias.setBounds(75, 0, 320, 240);
        panelFamilias.setOpaque(false);

        add(panelFamilias);
    }

    private void botonesPasaPaginaFamilia() {

        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(0, 100, 50, 50);
        add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (--paginaActual + numeroPaginas) % numeroPaginas;
                muestraPaginaFamilias();
            }
        });

        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(440, 100, 50, 50);
        add(flechaSiguiente);

        flechaSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (++paginaActual) % numeroPaginas;
                muestraPaginaFamilias();
            }
        });
    }

    public void muestraPaginaFamilias() {
        panelFamilias.removeAll();
        // Pogo las familias
        for (int i = paginaActual * FAMILIASxPAGINA; i < paginaActual * FAMILIASxPAGINA + FAMILIASxPAGINA && i < familias.size(); i++) {

            // Creo el panel con la familia
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            panel.setLayout(null);
            // Añado el panel al JFrame
            panelFamilias.add(panel);
            // Añado los labels al Panel
            panel.add(imagen);
            panel.add(texto);

            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * FAMILIASxPAGINA) % 3), 125 * ((i - paginaActual * FAMILIASxPAGINA) / 3), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\" + familias.get(i) + ".jpg"));
            texto.setText((String) familias.get(i));
            imagen.setVisible(true);
            texto.setVisible(true);
            panelFamilias.repaint();

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] componentes = contenedorProductos.getComponents();
                    for (Component componente : componentes) {
                        if (componente instanceof JLabel) {
                            contenedorProductos.remove(componente);
                        }
                    }
                    productosHM.get(texto.getText()).setPaginaActual(0);
                    productosHM.get(texto.getText()).muestraPaginaProductos();
                    productosHM.get(texto.getText()).botonesPasaPaginaProducto();
                }
            });
        }
    }
}
