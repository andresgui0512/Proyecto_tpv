/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Moncho
 */
class Productos {
    private List<Producto> productos= new ArrayList();
    final int PRODUCTOSxPAGINA=9;
    final int FILAS=3;
    final int COLUMNAS=3;
    private int paginaActual;
    private int numeroPaginas;
    JPanel panelProductos;
    Container contenedorGeneral;
    JPanel pladur; // Es el panel que contiene todo

    public Productos(Container contenedorGeneral,JPanel panelProductos) {
        
        this.panelProductos = panelProductos;
        this.contenedorGeneral=contenedorGeneral;
        botonesPasaPaginaProducto();
        crearPladur();
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    
    
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
    
    
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public void botonesPasaPaginaProducto() {
        
        JButton botonBorrar = new JButton();
        botonBorrar.setBounds(0,0,75,50);
        botonBorrar.setText("Borrar");
        botonBorrar.setVisible(true);
        contenedorGeneral.add(botonBorrar);
        botonBorrar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                VariablesGenerales.restar=true;
                System.out.println("Restar está en true");
            }
        
        });
        
        
        // Pongo flecha de antes y despues
        
        
        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(0,180,50,50);

        contenedorGeneral.add(flechaAnterior);
        
        flechaAnterior.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual=(--paginaActual+numeroPaginas)%numeroPaginas;
                muestraPaginaProductos();
            }
            
        });
        
        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(440,180,50,50);

        contenedorGeneral.add(flechaSiguiente);
        
        flechaSiguiente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual=(++paginaActual)%numeroPaginas;
                muestraPaginaProductos();
            }
            
        });
    }
    
    public void muestraPaginaProductos() {
        mostrarProductos();
        panelProductos.removeAll();
        // Pogo las familias
        for(int i=paginaActual*PRODUCTOSxPAGINA;i<paginaActual*PRODUCTOSxPAGINA+PRODUCTOSxPAGINA && i<productos.size();i++){
            final int PRODUCTOACTUAL=i;
            // Creo el panel con la familia
            JPanel panel = new JPanel();
            JLabel imagen = new JLabel();
            JLabel texto = new JLabel();
                
            panel.setLayout(null);
            // Añado el panel al JFrame
            panelProductos.add(panel);
            // Añado los labels al Panel
            panel.add(imagen);
            panel.add(texto);
                
            panel.setOpaque(true);
            panel.setBounds(110*((i-paginaActual*PRODUCTOSxPAGINA)%COLUMNAS), 125*((i-paginaActual*PRODUCTOSxPAGINA)/FILAS), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0,0,100,100);
            texto.setBounds(0,100,100,15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\"+productos.get(i)+".jpg"));
            texto.setText(productos.get(i).toString());
            imagen.setVisible(true);
            texto.setVisible(true);
            
            panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                imprimePrecio(productos.get(PRODUCTOACTUAL));  
                
            }

                
            
        });
            
            panelProductos.repaint();
        }
    }
    
    private void imprimePrecio(Producto producto) {
        
        
        boolean encontrado=false;
        for(int i=0;i<VariablesGenerales.lineasTicket.size();i++){
            if(VariablesGenerales.lineasTicket.get(i).getNombreProducto().equals(producto.getNombre())){
                if(VariablesGenerales.lineasTicket.get(i).getCantidadProducto()>1 && VariablesGenerales.restar){
                    VariablesGenerales.lineasTicket.get(i).setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto()-1);
                    // Modificamos la cantidad en el modelo que gestiona la tabla del ticket
                    VariablesGenerales.modeloTablaTicket.setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);
                }else if(VariablesGenerales.lineasTicket.get(i).getCantidadProducto()==1 && VariablesGenerales.restar){
                    VariablesGenerales.lineasTicket.remove(i);
                    VariablesGenerales.modeloTablaTicket.removeRow(i);
                }else{
                    VariablesGenerales.lineasTicket.get(i).setCantidadProducto(VariablesGenerales.lineasTicket.get(i).getCantidadProducto()+1);
                    VariablesGenerales.modeloTablaTicket.setValueAt(VariablesGenerales.lineasTicket.get(i).getCantidadProducto(), i, 2);
                
                }
                encontrado=true; 
                
                break;
            }
        }
        if(!encontrado && !VariablesGenerales.restar){
             VariablesGenerales.lineasTicket.add(new LineaTicket(producto.getNombre(),producto.getPrecioDouble(),1));
             VariablesGenerales.modeloTablaTicket.addRow(new Object[]{producto.getNombre(),producto.getPrecio(),"1"});
        }
        VariablesGenerales.restar=false;
        
        System.out.println("Producto selecionado: " + producto.getNombre() + " (" + producto.getPrecio() + ") ");
        VariablesGenerales.totalTicket += producto.getPrecioDouble();
        System.out.println("Total actual: " + VariablesGenerales.totalTicket);
    }
    
    private void crearPladur() {
        
    }
    
    
    private void mostrarProductos(){
    
        for(Producto producto: productos){
            System.out.println(producto.nombre);
        }
    
    
    }
}
