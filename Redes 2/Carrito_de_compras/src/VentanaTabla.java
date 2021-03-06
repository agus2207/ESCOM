import Utilidades.GestionCeldas;
import Utilidades.GestionEncabezadoTabla;
import Utilidades.ModeloTabla;
import Utilidades.Utilidades;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaTabla extends JFrame implements MouseListener{
    
    //private JPanel contentPane;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton jButton1, jButton2, jButton3;
    ArrayList<Productos> listaProductos;//lista que simula la información de la BD
    private ImageIcon imagen = new ImageIcon(getClass().getResource("/Imagenes/logotipo.png"));
    private ImageIcon imagen1 = new ImageIcon(getClass().getResource("/Imagenes/logo.png"));
    private ImageIcon salir = new ImageIcon(getClass().getResource("/Imagenes/salir.png"));
    private ImageIcon comprar = new ImageIcon(getClass().getResource("/Imagenes/comprar.jpg"));
    private ImageIcon ticon = new ImageIcon(getClass().getResource("/Imagenes/ticket.png"));
    private ImageIcon eliminar = new ImageIcon(getClass().getResource("/Imagenes/eliminado.png"));
    private ImageIcon compracorrecta = new ImageIcon(getClass().getResource("/Imagenes/exitosa.png"));
    private ImageIcon disponible = new ImageIcon(getClass().getResource("/Imagenes/warning.jpg"));
    private ImageIcon agregado = new ImageIcon(getClass().getResource("/Imagenes/bien.png"));
    ModeloTabla modelo;//modelo definido en la clase ModeloTabla
    private int filasTabla;
    private int columnasTabla;

     /**
     * Create the frame.
     */
    public VentanaTabla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1142, 453);
		iniciarComponentes();
		setLocationRelativeTo(null);
		construirTabla();
    }

    private void iniciarComponentes() {
        
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Clonopolis");
        
        jTable1.setBackground(Color.WHITE);
        jTable1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        jTable1.addMouseListener(this);
        //tablaSeguimiento.addKeyListener(this);
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);
        
        jLabel2.setIcon(imagen);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Bienvenido a la tienda de peliculas");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Encuentre sus peliculas favoritas al mejor precio");
        
        jLabel5.setIcon(imagen1);

        jButton1.setIcon(comprar);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(ticon);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        jButton3.setIcon(salir);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(505, 505, 505)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1,javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2,javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3,javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );

        pack();
    }
	
    /**
     * Metodo que permite construir la tabla de personas se crean primero las
     * columnas y luego se asigna la información
     */
    private void construirTabla() {
        Cliente e = new Cliente();
        listaProductos = e.inventario();

        ArrayList<String> titulosList = new ArrayList<>();

        titulosList.add("Imagen");
        titulosList.add("Nombre");
        titulosList.add("Descripcion");
        titulosList.add("Codigo");
        titulosList.add("Precio");
        titulosList.add("Stock");
        titulosList.add("Unidades");
        titulosList.add("Agregar");
        titulosList.add("Total");
        titulosList.add("Vaciar");

        //se asignan las columnas al arreglo para enviarse al momento de construir la tabla
        String titulos[] = new String[titulosList.size()];
        for (int i = 0; i < titulos.length; i++) {
            titulos[i] = titulosList.get(i);
        }
        /*obtenemos los datos de la lista y los guardamos en la matriz
		 * que luego se manda a construir la tabla
         */
        Object[][] data = obtenerMatrizDatos(titulosList);
        construirTabla(titulos, data);

    }
    
    /**
     * Llena la información de la tabla usando la lista de personas trabajada
     * anteriormente, guardandola en una matriz que se retorna con toda la
     * información para luego ser asignada al modelo
     *
     * @param titulosList
     * @return
     */
    
    private Object[][] obtenerMatrizDatos(ArrayList<String> titulosList) {

        /*se crea la matriz donde las filas son dinamicas pues corresponde
		 * a todos los usuarios, mientras que las columnas son estaticas
		 * correspondiendo a las columnas definidas por defecto
         */
        String informacion[][] = new String[listaProductos.size()][titulosList.size()];

        for (int x = 0; x < informacion.length; x++) {

            informacion[x][Utilidades.IMAGEN] = listaProductos.get(x).getRuta();
            informacion[x][Utilidades.NOMBRE] = listaProductos.get(x).getNombre()+"";
            informacion[x][Utilidades.DESCRIPCION] = listaProductos.get(x).getDescripcion() + "";
            informacion[x][Utilidades.CODIGO] = listaProductos.get(x).getGenero() + "";
            informacion[x][Utilidades.PRECIO] = listaProductos.get(x).getPrecio() + "";
            informacion[x][Utilidades.CANTIDAD] = listaProductos.get(x).getCantidad()+ "";
            informacion[x][Utilidades.COMPRAR] = "0";
            informacion[x][Utilidades.BOTON] = "BOTON";
            informacion[x][Utilidades.TOTAL] = "0";
            informacion[x][Utilidades.ELIMINAR] = "ELIMINAR";
        }

        return informacion;
    }

    /**
     * Con los titulos y la información a mostrar se crea el modelo para poder
     * personalizar la tabla, asignando tamaño de celdas tanto en ancho como en
     * alto así como los tipos de datos que va a poder soportar.
     *
     * @param titulos
     * @param data
     **/
    
    private void construirTabla(String[] titulos, Object[][] data) {
        modelo = new ModeloTabla(data, titulos);
        //se asigna el modelo a la tabla
        jTable1.setModel(modelo);

        filasTabla = jTable1.getRowCount();
        columnasTabla = jTable1.getColumnCount();

        //se asigna el tipo de dato que tendrán las celdas de cada columna definida respectivamente para validar su personalización
        jTable1.getColumnModel().getColumn(Utilidades.CODIGO).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(Utilidades.PRECIO).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(Utilidades.CANTIDAD).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(Utilidades.COMPRAR).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(Utilidades.TOTAL).setCellRenderer(new GestionCeldas("numerico"));
        jTable1.getColumnModel().getColumn(Utilidades.NOMBRE).setCellRenderer(new GestionCeldas("texto"));
        jTable1.getColumnModel().getColumn(Utilidades.DESCRIPCION).setCellRenderer(new GestionCeldas("texto"));
        jTable1.getColumnModel().getColumn(Utilidades.IMAGEN).setCellRenderer(new GestionCeldas("imagen"));
        jTable1.getColumnModel().getColumn(Utilidades.BOTON).setCellRenderer(new GestionCeldas("icono"));
        jTable1.getColumnModel().getColumn(Utilidades.ELIMINAR).setCellRenderer(new GestionCeldas("icono"));

        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setRowHeight(100);//tamaño de las celdas
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        //Se define el tamaño de largo para cada columna y su contenido
        jTable1.getColumnModel().getColumn(Utilidades.IMAGEN).setPreferredWidth(100);//imagen
        jTable1.getColumnModel().getColumn(Utilidades.NOMBRE).setPreferredWidth(200);//nombre
        jTable1.getColumnModel().getColumn(Utilidades.DESCRIPCION).setPreferredWidth(350);//descripcion
        jTable1.getColumnModel().getColumn(Utilidades.CODIGO).setPreferredWidth(130);//codigo
        jTable1.getColumnModel().getColumn(Utilidades.PRECIO).setPreferredWidth(100);//precio
        jTable1.getColumnModel().getColumn(Utilidades.CANTIDAD).setPreferredWidth(80);//cantidad
        jTable1.getColumnModel().getColumn(Utilidades.COMPRAR).setPreferredWidth(100);//comprar
        jTable1.getColumnModel().getColumn(Utilidades.BOTON).setPreferredWidth(100);//boton
        jTable1.getColumnModel().getColumn(Utilidades.TOTAL).setPreferredWidth(100);//total
        jTable1.getColumnModel().getColumn(Utilidades.ELIMINAR).setPreferredWidth(100);//total

        //personaliza el encabezado
        JTableHeader jtableHeader = jTable1.getTableHeader();
        jtableHeader.setDefaultRenderer(new GestionEncabezadoTabla());
        jTable1.setTableHeader(jtableHeader);

        //se asigna la tabla al scrollPane
        jScrollPane1.setViewportView(jTable1);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //capturo fila o columna dependiendo de mi necesidad
        int fila = jTable1.rowAtPoint(e.getPoint());
        int columna = jTable1.columnAtPoint(e.getPoint());

        /*uso la columna para valiar si corresponde a la columna de perfil garantizando
            que solo se produzca algo si selecciono una fila de esa columna
         */
        if (columna == Utilidades.BOTON) {
            //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
            validarSeleccionMouse(fila);
        } else if (columna == Utilidades.ELIMINAR) {
            borrar(fila);
        }
    }

    /**
     * Este metodo simularia el proceso o la acción que se quiere realizar si se
     * presiona alguno de los botones o iconos de la tabla
     *
     * @param fila
     */
    private void validarSeleccionMouse(int fila) {
        Utilidades.filaSeleccionada = fila;
        String comprar = jTable1.getValueAt(fila, Utilidades.COMPRAR).toString();
        String precio = jTable1.getValueAt(fila, Utilidades.PRECIO).toString();
        int cant = Integer.parseInt(comprar)+1;
        int newcantidad = Integer.parseInt(listaProductos.get(fila).getCantidad()) - cant;
        if(newcantidad < 0)
            JOptionPane.showMessageDialog(null, "La cantidad que requiere excede la disponibilidad", "Warning",JOptionPane.INFORMATION_MESSAGE, disponible);
        else{
            int total = cant * Integer.parseInt(precio);
            String c = String.valueOf(newcantidad);
            String t = String.valueOf(total);
            String compra = String.valueOf(cant);
            jTable1.setValueAt(c, fila, Utilidades.CANTIDAD);
            jTable1.setValueAt(t, fila, Utilidades.TOTAL);
            jTable1.setValueAt(compra, fila, Utilidades.COMPRAR);
            JOptionPane.showMessageDialog(null, cant+" productos agregados correctamente\n$"+t+" a pagar", "",JOptionPane.INFORMATION_MESSAGE, agregado);
        }
    }
        
    private void borrar(int fila) {
        Utilidades.filaSeleccionada = fila;
        String c = listaProductos.get(fila).getCantidad();
        jTable1.setValueAt(c, fila, Utilidades.CANTIDAD);
        jTable1.setValueAt("0", fila, Utilidades.COMPRAR);
        jTable1.setValueAt("0", fila, Utilidades.TOTAL);
        JOptionPane.showMessageDialog(null, "Producto retirado correctamente", "",JOptionPane.INFORMATION_MESSAGE, eliminar);
    }
	
    //estos metododos pueden ser usados dependiendo de nuestra necesidad, por ejemplo para cambiar el tamaño del icono al ser presionado
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Cliente e = new Cliente();
        int tamaño = listaProductos.size();
        String[][] ticket = new String[tamaño][];
        int t = 0;
        for(int i = 0; i < tamaño; i++){
            ticket[i] = new String[4];
            String nombre = jTable1.getValueAt(i, Utilidades.NOMBRE).toString();
            String genero = jTable1.getValueAt(i, Utilidades.CODIGO).toString();
            String cantidad = jTable1.getValueAt(i, Utilidades.CANTIDAD).toString();
            String precio = jTable1.getValueAt(i, Utilidades.PRECIO).toString();
            String comprar = jTable1.getValueAt(i, Utilidades.COMPRAR).toString();
            String total = jTable1.getValueAt(i, Utilidades.TOTAL).toString();
            listaProductos.get(i).setCantidad(cantidad);
            if(Integer.parseInt(comprar)>0){
                ticket[i][0] = nombre;
                ticket[i][1] = genero;
                ticket[i][2] = comprar;
                ticket[i][3] = "$"+precio; 
            }
            t = t + Integer.parseInt(total);
        }
        e.comprar(listaProductos, ticket, t);
        JOptionPane.showMessageDialog(null, "Compra exitosa\n$"+t+" a pagar\nTicket Generado", "",JOptionPane.INFORMATION_MESSAGE, compracorrecta);
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        Cliente e = new Cliente();
        e.openfile("Ticket.pdf");
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       Cliente e = new Cliente();
       e.salir();
    } 
}
