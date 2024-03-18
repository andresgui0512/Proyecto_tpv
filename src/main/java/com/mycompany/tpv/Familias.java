/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import static com.mycompany.tpv.PantallaPrincipal.relojStopWatch;
import com.stopwatch.IStopWatch;
import com.stopwatch.StopWatch;
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
 * Muestra todas las familias y gestiona su funcionamiento.
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
    static IStopWatch relojStopWatch;

    /**
     * Recibe el contenedor que almacena los productos, y el hashmap que enlaza
     * las familias con los prouductos.
     *
     * @param contenedorProductos
     * @param productosHM
     */
    public Familias(Container contenedorProductos, HashMap<String, Productos> productosHM) {
        relojStopWatch = StopWatch.create();
        relojStopWatch.start();
        this.productosHM = productosHM;
        this.contenedorProductos = contenedorProductos;
        familias = new ArrayList();
        creaPaneles();
        botonesPasaPaginaFamilia();
        System.out.println("Tiempo de ejecución de la clase Familias: " + relojStopWatch.elapsedMillis() + " milisegundos.");
        relojStopWatch.stop();
    }

    /**
     * Devuelve la lista de familias
     *
     * @return List(String)
     */
    public List getFamilias() {
        return familias;
    }

    /**
     * Permite modificar la lista de familias.
     *
     * @param familias List(String)
     */
    public void setFamilias(List familias) {
        this.familias = familias;
    }

    /**
     * Devuelve el número de páginas
     *
     * @return int
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Permite modificar el número de páginas
     *
     * @param numeroPaginas int
     */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Devuelve el número de familias por página
     *
     * @return
     */
    public int getFAMILIASxPAGINA() {
        return FAMILIASxPAGINA;
    }

    /**
     * Crea el panel que contendrá las familias, y define la clase la cual es un
     * panel que contiene el panelFamilias y los botones de desplazamiento.
     */
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

    /**
     * Crea los botones de desplazamiento de izquierda y derecha
     */
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

    /**
     * Actualiza las familias que se muestran en el panelFamilias de acuerdo al
     * número de familias y la página actual.
     */
    public void muestraPaginaFamilias() {
        panelFamilias.removeAll();//Elimina todas las familias del panel

        for (int i = paginaActual * FAMILIASxPAGINA; i < paginaActual * FAMILIASxPAGINA + FAMILIASxPAGINA && i < familias.size(); i++) {

            //Crea los labels que contendrán el nombre de la familia
            //y la imagen de la misma y el panel que contendrá estos labels
            JPanel panel = new JPanel();
            panel.setBackground(new Color(255, 153, 102));
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            //Los añado al panelFamilias
            panel.setLayout(null);
            panelFamilias.add(panel);
            panel.add(imagen);
            panel.add(texto);

            //Defino las características de los componentes
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

            //Al presionar una familia se eliminan todos los compenentes del panelProductos
            //y de acuerdo a la página actual se crean los correpondientes
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Component[] componentes = contenedorProductos.getComponents();
                    for (Component componente : componentes) {
                        if (componente instanceof JLabel) {
                            contenedorProductos.remove(componente);
                        }
                    }
                    productosHM.get(texto.getText()).crearBotonBorrar();
                    productosHM.get(texto.getText()).setPaginaActual(0);
                    productosHM.get(texto.getText()).muestraPaginaProductos();
                    productosHM.get(texto.getText()).botonesPasaPaginaProducto();
                }
            });
        }
    }
}
