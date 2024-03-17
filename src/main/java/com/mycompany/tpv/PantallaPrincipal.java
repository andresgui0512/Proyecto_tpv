/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.tpv;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import com.stopwatch.IStopWatch;
import com.stopwatch.StopWatch;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * PantallaPrincipal genera la ventana del programa y gestiona
 * la conexión con la base de datos.
 * @author andre
 */
public class PantallaPrincipal extends JFrame {

    Familias familias;
    public final HashMap<String, Productos> productosHM = new HashMap<>();
    JPanel panelProductos;
    JPanel panelGeneralProductos;
    static IStopWatch relojStopWatch;

    /**
     * El constructor llama a los métodos necesarios
     * para la ejecución del programa e instancia la clase Familias.
     */
    public PantallaPrincipal() {
        relojStopWatch =StopWatch.create();
        relojStopWatch.start();
        initComponents();
        creaPaneles();
        familias = new Familias(panelGeneralProductos,productosHM);
        familias.setBounds(75, 10, 490, 240);
        getContentPane().add(familias);
        conexionBD();
        familias.muestraPaginaFamilias();
        mostrarProductosPrimeraFamilia();
        getContentPane().add(new ZonaTicket(600, 0, 400, 500));
        crearBotonTicket();
        System.out.println("Tiempo transcurrido al iniciar el programa: "+relojStopWatch.elapsedMillis()+" milisegundos.");
        relojStopWatch.stop();
    }
    
    /**
     * Define las características de la ventana principal como el icono,
     * el tamaño y la posición.
     */
    private void initComponents() {

        ImageIcon icon = new ImageIcon("recursos\\imagenes\\tpv_icono.png");
        setIconImage(icon.getImage());
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1072, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );
        pack();
    } 
    
    /**
     * El método conexionBD realiza la conexión con la base de datos "tpv_db" y
     * extrae los nombres de las familias, los productos y sus precios.
     */
    public void conexionBD() {
        try {
            //Conexión con la base de datos.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lol", "root", "");

            //Sentencia para obtener las familias.
            String sql = "SELECT * FROM familias";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                
                //Añade todos los nombres de las familias a la lista familias.
                familias.getFamilias().add(resultSet.getString("Nombre"));
                //ejemplo.add(resultSet.getString("Nombre"));

                //La clase Productos se encarga de organizar los componentes dentro de
                //los paneles que pide como parámetros.
                Productos productos = new Productos(panelGeneralProductos, panelProductos);

                //Sentencia para obtener los productos
                String sqll = "SELECT * FROM productos";
                PreparedStatement statementt = conn.prepareStatement(sqll);
                ResultSet resultSett = statementt.executeQuery();

                while (resultSett.next()) {

                    //Si coincide que la clave foránea de un producto conincide con el id de una familia
                    //significa que forman parte de una misma familia, luego procede a crear ese producto
                    if (resultSet.getInt("ID") == resultSett.getInt("Familia_ID")) {
                        productos.getProductos().add(
                                new Producto(resultSett.getString("Nombre"), resultSett.getString("Precio")));
                    }
                }
                //De acuerdo al número de productos asociados a una familia el programa determinará
                //cuantas páginas debe poseer para ello divide la cantidad de productos con el número
                //que se pueden mostrar por página.
                productos.setNumeroPaginas(1 + productos.getProductos().size() / productos.PRODUCTOSxPAGINA);

                //Se asocia el nombre de la familia con el producto correspondiente
                productosHM.put(resultSet.getString("Nombre"), productos);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Aquí se determina el número de paginas tomando en cuenta la cantidad de familias
        //numeroPaginas = 1 + familias.size() / FAMILIASxPAGINA;
        familias.setNumeroPaginas(1+familias.getFamilias().size()/familias.getFAMILIASxPAGINA());
    }

    /**
     * Crea los paneles donde se mostrarán los productos, como el panelGeneral
     * que contiene las flechas de desplazamiento y el panelProductos, este último contiene
     * los productos.
     */
    private void creaPaneles() {

        panelGeneralProductos = new JPanel();
        panelGeneralProductos.setLayout(null);
        panelGeneralProductos.setBounds(75, 260, 490, 365);
        panelGeneralProductos.setVisible(true);
        panelGeneralProductos.setBackground(new Color(102, 179, 255));
        getContentPane().add(panelGeneralProductos);

        panelProductos = new JPanel();
        panelProductos.setLayout(null);
        panelProductos.setBounds(75, 0, 320, 365);
        panelProductos.setVisible(true);
        panelProductos.setOpaque(false);

        panelGeneralProductos.add(panelProductos);
    }

    /**
     * Muestra por la interfaz la primera familia de productos. 
     */
    private void mostrarProductosPrimeraFamilia() {
        productosHM.get("" + familias.getFamilias().get(0)).muestraPaginaProductos();
    }

    /**
     * El método crearBotonTicket crea un botón que llama al método
     * imprimirTicket y gestiona la cantidad de tickets a generar
     * si el pedido excede el límite de productos por ticket.
     */
    private void crearBotonTicket() {
        
        //Creación del botón
        JButton botoncrearTicket = new JButton("Finalizar pedido");
        botoncrearTicket.setBackground(new Color(71, 209, 71));
        botoncrearTicket.setForeground(Color.white);
        botoncrearTicket.setFont(new Font("Arial", Font.BOLD, 13));
        botoncrearTicket.setBounds(730, 530, 140, 60);
        add(botoncrearTicket);

        //Función del botón al presionarlo
        botoncrearTicket.addActionListener(ActionEvent -> {

            int numeroTicket = 0;
            int indice = 0; //Señala los productos agregados para evitar que se repitan
                            //al generar varios tickets
            
            //Si la cantidad de productos es mayor a 80 se crean otros tickets
            if (VariablesGenerales.lineasTicket.size() > 80) {
                while (VariablesGenerales.lineasTicket.size() > 80) {

                    //Si el indice es mayor o igual a la cantidad de productos significa que
                    //ya se han impreso todos los productos en los tickets por lo que se termina el bucle
                    if (indice >= VariablesGenerales.lineasTicket.size()) {
                        break;
                    }

                    //Si el indice + 80 es mayor que la cantidad de productos se imprimen los productos que quedan
                    if (indice + 80 > VariablesGenerales.lineasTicket.size()) {
                        imprimirTicket(VariablesGenerales.lineasTicket.size(), indice, "Ticket" + numeroTicket + ".txt");
                        
                    //Si el indice + 80 no es mayor que la cantidad de productos se imprimen otros 80 productos 
                    } else {
                        imprimirTicket(indice + 80, indice, "Ticket" + numeroTicket + ".txt");
                    }

                    numeroTicket++;//El número de ticket aumenta junto con el índice
                    indice += 80;
                }
            //Si hay 80 productos o menos solo se imprime un ticket
            } else {
                imprimirTicket(VariablesGenerales.lineasTicket.size(), indice, "Ticket.txt");
            }
        });
    }

    /**
     * El método imprimirTicket crea un documento de texto con todos los productos
     * escogidos en ese momento junto con el total y el IVA.
     * 
     * @param cantidadLineasTicket Representa la cantidad de productos a imprimir en el ticket.
     * @param indice Representa el número con el que se va a empezar a contar los productos.
     * @param nombreTicket Es el nombre del ticket a generar
     */
    private void imprimirTicket(int cantidadLineasTicket, int indice, String nombreTicket) {
        relojStopWatch.start();
        File ticket = new File(nombreTicket);

        try {   //Crea el ticket con el nombre que recibió como parámetro
            BufferedWriter escritor = new BufferedWriter(new FileWriter(ticket));

            //Escribe los nombres de los campos en el fichero con un formato parecido a una tabla
            escritor.write(String.format("%-20s %-10s %-10s %-15s\n", "Producto", "Cantidad", "Precio", "Total"));

            //Escribe todos los productos en el ticket manteniendo el mismo formato anterior
            for (int i = indice; i < cantidadLineasTicket; i++) {

                //Calcula el precio de un producto total de acuerdo a la cantidad del mismo
                double precioCantidadTotal = VariablesGenerales.lineasTicket.get(i).getPrecioProducto() * 
                                             VariablesGenerales.lineasTicket.get(i).getCantidadProducto();

                escritor.write(String.format("%-20s %-10d %-10.2f %-15.2f\n",
                        VariablesGenerales.lineasTicket.get(i).getNombreProducto(),
                        VariablesGenerales.lineasTicket.get(i).getCantidadProducto(),
                        VariablesGenerales.lineasTicket.get(i).getPrecioProducto(),
                        precioCantidadTotal));
            }

            //Escribe el precio total sin el IVA
            escritor.newLine();
            escritor.write(String.format("%-20s %-10.2f",
                    "Total sin IVA",
                    VariablesGenerales.totalTicket));

            double iva = VariablesGenerales.totalTicket * 0.1;
            double totalConIva = iva + VariablesGenerales.totalTicket;

            //Escribe el IVA correspondiente a la compra
            escritor.newLine();
            escritor.write(String.format("%-20s %-10.2f",
                    "IVA",
                    iva));

            //Escribe el precio total a pagar incluyendo el IVA
            escritor.newLine();
            escritor.write(String.format("%-20s %-10.2f",
                    "Total a pagar",
                    totalConIva));

            escritor.close();

        } catch (IOException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Tiempo transcurrido al imprimir el ticket: "+relojStopWatch.elapsedMillis()+" milisegundos.");
        relojStopWatch.stop();
    }
}
