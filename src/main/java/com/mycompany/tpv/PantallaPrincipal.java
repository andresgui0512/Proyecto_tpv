/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Moncho
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    private final List familias= new ArrayList();
    private final HashMap<String,Productos> productosHM = new HashMap<>();
    final int FAMILIASxPAGINA=6;
    int paginaActual;
    int numeroPaginas;
    JPanel panelFamilias;
    JPanel panelProductos;
    JPanel pladur;
    /**
     * Creates new form Familias
     */
    public PantallaPrincipal() {
        initComponents();
        creaPaneles();
        leerFamilias();
        bontonesPasaPaginaFamilia();
        muestraPaginaFamilias();
        mostrarProductosPrimeraFamilia();
        getContentPane().add(new ZonaTicket(600,0,400,500));
        conexionBD();
    }

    private void conexionBD(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpv_db", "root", "");

            String sql = "SELECT * FROM productos";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            System.out.println("Resultado de la base de datos....................................");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Nombre")); 
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    private void bontonesPasaPaginaFamilia() {
        // Pongo flecha de antes y despues
        
        JLabel flechaAnterior = new JLabel();
        flechaAnterior.setIcon(new ImageIcon("recursos\\imagenes\\anterior.png"));
        flechaAnterior.setVisible(true);
        flechaAnterior.setBounds(75,110,50,50);
        getContentPane().add(flechaAnterior);
        
        flechaAnterior.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual=(--paginaActual+numeroPaginas)%numeroPaginas;
                muestraPaginaFamilias();
            }
            
        });
        
        JLabel flechaSiguiente = new JLabel();
        flechaSiguiente.setIcon(new ImageIcon("recursos\\imagenes\\siguiente.png"));
        flechaSiguiente.setVisible(true);
        flechaSiguiente.setBounds(490,110,50,50);
        getContentPane().add(flechaSiguiente);
        
        flechaSiguiente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                paginaActual=(++paginaActual)%numeroPaginas;
                muestraPaginaFamilias();
            }
            
        });
    }

    private void muestraPaginaFamilias() {
        panelFamilias.removeAll();
        // Pogo las familias
        for(int i=paginaActual*FAMILIASxPAGINA;i<paginaActual*FAMILIASxPAGINA+FAMILIASxPAGINA && i<familias.size();i++){
            
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
            panel.setBounds(110*((i-paginaActual*FAMILIASxPAGINA)%3), 125*((i-paginaActual*FAMILIASxPAGINA)/3), 100, 115);
            imagen.setOpaque(true);
            imagen.setBounds(0,0,100,100);
            texto.setBounds(0,100,100,15);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setVerticalAlignment(SwingConstants.CENTER);
            imagen.setIcon(new ImageIcon("recursos\\imagenes\\"+familias.get(i)+".jpg"));
            texto.setText((String)familias.get(i));
            imagen.setVisible(true);
            texto.setVisible(true);
            panelFamilias.repaint();
            
            panel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Component[] componentes =pladur.getComponents();
                for (Component componente : componentes) {
                    if (componente instanceof JLabel) {
                        pladur.remove(componente);
                    }
                }
                productosHM.get(texto.getText()).setPaginaActual(0);
         
                productosHM.get(texto.getText()).muestraPaginaProductos();
                productosHM.get(texto.getText()).botonesPasaPaginaProducto();
            }
            
        });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 652, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 343, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void leerFamilias() {
        File archivo = new File ("recursos\\ficheros\\familias.txt");
        FileReader fr;
        try {
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                String[] partes = linea.split(",");
                familias.add(partes[0].trim());
                Productos productos = new Productos(pladur,panelProductos);
                for(int i=1; i<partes.length;i+=2){
                    productos.getProductos().add(new Producto(partes[i],partes[i+1]));
                }
                productos.setNumeroPaginas(1+productos.getProductos().size()/productos.PRODUCTOSxPAGINA);
                productosHM.put(partes[0], productos);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PantallaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        numeroPaginas=1+familias.size()/FAMILIASxPAGINA;
    }

    private void creaPaneles() {
        panelFamilias=new JPanel();
        panelFamilias.setLayout(null);
        panelFamilias.setBounds(150, 10, 320, 240);
        panelFamilias.setVisible(true);
  
        getContentPane().add(panelFamilias);
        
        /// Creamos el panel para los productos
        pladur= new JPanel();
        pladur.setLayout(null);
        pladur.setBounds(75, 260, 490, 365);
        pladur.setVisible(true);
        pladur.setBackground(Color.pink);
        getContentPane().add(pladur);
        
        
        panelProductos=new JPanel();
        panelProductos.setLayout(null);
        panelProductos.setBounds(75,0 , 320, 365);
        panelProductos.setVisible(true);
        panelProductos.setBackground(Color.red);
  
        pladur.add(panelProductos);
    }

    private void mostrarProductosPrimeraFamilia() {
        
        productosHM.get(""+familias.get(0)).muestraPaginaProductos();
        //productosHM.get(""+familias.get(0)).bontonesPasaPaginaProducto();
    }
}
