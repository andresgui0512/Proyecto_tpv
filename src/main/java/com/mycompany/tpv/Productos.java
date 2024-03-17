/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Presenta por pantalla los productos y gestiona su funcionamiento.
 *
 * @author andre
 */
class Productos {

    private List<Producto> productos = new ArrayList();
    final int PRODUCTOSxPAGINA = 9;
    final int FILAS = 3;
    final int COLUMNAS = 3;
    private int paginaActual;
    private int numeroPaginas;
    JPanel panelProductos;
    Container contenedorGeneral;

    /**
     * Recibe un contenedorGeneral en donde se mostrarán las flechas de
     * desplazamiento y un panel en donde se mostrarán los productos.
     *
     * @param contenedorGeneral Container
     * @param panelProductos JPanel
     */
    public Productos(Container contenedorGeneral, JPanel panelProductos) {
        this.panelProductos = panelProductos;
        this.contenedorGeneral = contenedorGeneral;
        crearBotonBorrar();
        botonesPasaPaginaProducto();
    }

    /**
     * Devuelve la página actual.
     *
     * @return
     */
    public int getPaginaActual() {
        return paginaActual;
    }

    /**
     * Permite modificar la página actual.
     *
     * @param paginaActual int
     */
    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    /**
     * Devuelve el número de páginas.
     *
     * @return
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Permite modificar el número de páginas.
     *
     * @param numeroPaginas int
     */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Devuelve una lista con objetos Producto.
     *
     * @return List(Producto)
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * Permite modificar la lista de objetos Producto
     *
     * @param productos
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Crea el botón de borrar del contenedorGeneral que permite eliminar un
     * producto de la lista.
     */
    public void crearBotonBorrar() {

        JLabel botonBorrar = new JLabel();
        botonBorrar.setIcon(new ImageIcon("recursos\\imagenes\\iconoBorrar.png"));
        botonBorrar.setBounds(0, 0, 75, 50);
        botonBorrar.setVisible(true);

        contenedorGeneral.add(botonBorrar);//Agrego el botón al panel.

        //Función del botón
        botonBorrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Al estar la variable restar en true permite que al 
                //presionar un producto que ya había sido presionado antes, eliminarlo
                //de la lista.
                VariablesGenerales.restar = true;
            }
        });
    }

    /**
     * Crea los botones de desplazamiento de izquierda y derecha y les asigna
     * sus funciones.
     */
    public void botonesPasaPaginaProducto() {

        //Creación del botón de la izquierda y su función.
        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(0, 180, 50, 50);

        contenedorGeneral.add(flechaAnterior);

        flechaAnterior.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Al presionar el botón la página actual disminuye y se actualiza
                //la pantalla al llamar al método muestraPaginaProductos().
                paginaActual = (--paginaActual + numeroPaginas) % numeroPaginas;
                muestraPaginaProductos();
            }

        });
        //Creación del botón de la derecha.
        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(440, 180, 50, 50);

        contenedorGeneral.add(flechaSiguiente);

        flechaSiguiente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual = (++paginaActual) % numeroPaginas;//La página actual aumenta.
                muestraPaginaProductos();//Se actualiza la pantalla.
            }

        });
    }

    /**
     * Actualiza todos los componentes que se muestran en el panel de productos,
     * de acuerdo a la página actual mostrará los productos correspondientes.
     */
    public void muestraPaginaProductos() {
        panelProductos.removeAll();//Elimina todos los productos del panel

        //De acuerdo a la página actual y la cantidad de productos se mostrarán los productos correspondientes
        //en el panel.
        for (int i = paginaActual * PRODUCTOSxPAGINA; i < paginaActual * PRODUCTOSxPAGINA + PRODUCTOSxPAGINA && i < productos.size(); i++) {
            final int PRODUCTOACTUAL = i;

            //Se crean los labels que representan a cada producto
            //y se añaden a un panel.
            JPanel panel = new JPanel();
            panel.setBackground(new Color(0, 230, 230));
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();

            //Se añaden los compenentes
            panel.setLayout(null);
            panelProductos.add(panel);
            panel.add(imagen);
            panel.add(texto);

            //Se definen las características de los componentes como el tamaño,
            //posiciónn e imagen
            panel.setOpaque(true);
            panel.setBounds(110 * ((i - paginaActual * PRODUCTOSxPAGINA) % COLUMNAS), 125 * ((i - paginaActual * PRODUCTOSxPAGINA) / FILAS), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0, 0, 100, 100);
            texto.setBounds(0, 100, 100, 15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\" + productos.get(i) + ".jpg"));
            texto.setText(productos.get(i).toString());
            imagen.setVisible(true);
            texto.setVisible(true);

            //Al presionar un producto se llama al método imprimePrecio
            //el cual mostrará el producto en una tabla
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    imprimePrecio(productos.get(PRODUCTOACTUAL));
                }
            });
            panelProductos.repaint();//Se actualiza la interfaz para mostrar los cambios.
        }
    }

    /**
     * Recibe un producto como parámetro y lo imprime en la general.
     *
     * @param producto Producto a mostrar en la tabla.
     */
    public void imprimePrecio(Producto producto) {

        boolean encontrado = false;//Señala si se encontró un producto 

        for (int i = 0; i < VariablesGenerales.lineasTicket.size(); i++) {

            //Si el producto recibido ya está en la tabla
            if (VariablesGenerales.lineasTicket.get(i).getNombreProducto().equals(producto.getNombre())) {

                //Si hay mas de un producto y se ha presionado el botón borrar se resta en uno la cantidad
                if (VariablesGenerales.lineasTicket.get(i).getCantidadProducto() > 1 && VariablesGenerales.restar) {
                    VariablesGenerales.lineasTicket.get(i).setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto() - 1);

                    // Modificamos la cantidad en el modelo que gestiona la tabla del ticket
                    VariablesGenerales.modeloTablaTicket.setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);

                    //Si hay un solo producto y se ha presionado el botón borrar se elimina de la tabla
                } else if (VariablesGenerales.lineasTicket.get(i).getCantidadProducto() == 1 && VariablesGenerales.restar) {
                    VariablesGenerales.lineasTicket.remove(i);
                    VariablesGenerales.modeloTablaTicket.removeRow(i);

                    //Si hay un producto y no se ha presionado el botón borrar    
                } else {
                    //Si ya hay un producto y no se ha presionado el botón borrar aumenta la cantidad del mismo
                    VariablesGenerales.lineasTicket.get(i).setCantidadProducto(
                            VariablesGenerales.lineasTicket.get(i).getCantidadProducto() + 1);
                    VariablesGenerales.modeloTablaTicket.setValueAt(
                            VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);

                    //Aumenta el precio de acuerdo a la cantidad de productos
                    VariablesGenerales.modeloTablaTicket.setValueAt(VariablesGenerales.lineasTicket.get(i).getPrecioProducto()
                            * VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 1);
                }

                encontrado = true;//Se encontró el producto
                break;
            }
        }

        //Si no encontró el producto y no se ha presionado el botón de borrar
        //se agrega el producto a la tabla.
        if (!encontrado && !VariablesGenerales.restar) {

            VariablesGenerales.lineasTicket.add(new LineaTicket(
                    producto.getNombre(), producto.getPrecioDouble(), 1));
            VariablesGenerales.modeloTablaTicket.addRow(new Object[]{
                producto.getNombre(), producto.getPrecio(), "1"});
        }

        VariablesGenerales.restar = false;

        VariablesGenerales.totalTicket += producto.getPrecioDouble();//Aumenta el precio total.
        
        double factor = Math.pow(10, 2);
        VariablesGenerales.totalTicket = Math.round(VariablesGenerales.totalTicket* factor) / factor;
    }
}
