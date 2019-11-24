/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Modelo.Conexion;
import Modelo.Libro;
import Modelo.ReporteBalanceGeneral;
import Modelo.ReporteComprobacion;
import Modelo.ReporteDiario;
import Modelo.ReporteEstadoResultados;
import Modelo.ReporteMayor;
import interfas.Animacion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ruben
 */
public class Principal extends javax.swing.JFrame {

    Animacion anim = new Animacion();
    String[] Paneles = {"No hay nada aqui", "Libro Diario", "Libro Mayor", "Balance de Comprobacion", "Estado de Resultados", "Balance General", "No hay nada aqui"};

    int idPartida;//para la funcion eliminar
    Locale locale = new Locale("en", "UK");
    DecimalFormat formato = new DecimalFormat("#.00");
    int spin = 1;
    Libro l = new Libro();
    String PanelActual;

    int Contador;
    int cuantosLibrosHay = 0;
    int elLibro = 0;
    int menor = 0;

    /**
     * Creates new form FormPrincipal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);

        if (l.getAnterior() == 0) {
            Conexion c = new Conexion();
            ResultSet rs = c.Consulta("SELECT n_libro FROM `librodiario` ", c.getConexion());
            try {
                while (rs.next()) {

                    cuantosLibrosHay++;
                    elLibro = Integer.parseInt(rs.getString("n_libro"));
                    if (elLibro < menor) {
                        menor = elLibro;
                    } else if (menor == 0) {
                        menor = elLibro;
                    }
                }
                rs.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (cuantosLibrosHay == 1) {
                jSpinner1.setValue(elLibro);
            } else if (cuantosLibrosHay > 1) {
                jSpinner1.setValue(menor);
            }
        } else if (l.getAnterior() >= 1) {
            jSpinner1.setValue(l.getAnterior());
        }
        System.out.println("el numero menor es: " + menor);
        /*------------Todo este codigo ira en una funcion-------------*/
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnCerrar, "src/Imagenes/multiply.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnMinimizar, "src/Imagenes/subtraction.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnAnterior, "src/Imagenes/left-arrow.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnSiguiente, "src/Imagenes/right-arrow.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnAgregarPartida, "src/Imagenes/plus.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnEditar, "src/Imagenes/equal.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnEliminar, "src/Imagenes/eliminar.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnModificar, "src/Imagenes/writing.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnAjuste, "src/Imagenes/calculator.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnActualizarMyr, "src/Imagenes/update.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnActualizarComp, "src/Imagenes/update.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnActualizarEstRes, "src/Imagenes/update.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnActualizarBG, "src/Imagenes/update.png");
        rsscalelabel.RSScaleLabel.setScaleLabel(this.btnElimLibro, "src/Imagenes/eliminar.png");

        PanelActual = Paneles[1];
        Contador = 1;
        btnAnterior.setToolTipText(Paneles[Contador - 1]);
        btnSiguiente.setToolTipText(Paneles[Contador + 1]);

        CargandoPartidas(Integer.parseInt(jSpinner1.getValue().toString()));
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        lblModificar.setVisible(false);
        lblEliminar.setVisible(false);
        LibroMayor(Integer.parseInt(jSpinner1.getValue().toString()));
        BalanceComprobacion(Integer.parseInt(jSpinner1.getValue().toString()));
        sumar(TableMostrarPartidas, 3, 4, jTextField1, jTextField2);
        sumar(jTable2, 2, 3, jTextField3, jTextField4);
        EstadoResultados(Integer.parseInt(jSpinner1.getValue().toString()));
        BalanceGeneral(Integer.parseInt(jSpinner1.getValue().toString()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelNav = new javax.swing.JPanel();
        btnSiguiente = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JLabel();
        AbrirNav = new javax.swing.JLabel();
        PanelLibroDiario = new javax.swing.JPanel();
        PanelEditPartida = new javax.swing.JPanel();
        btnAjuste = new javax.swing.JLabel();
        btnAgregarPartida = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnEditar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        AbrirNav1 = new javax.swing.JLabel();
        lblEliminar = new javax.swing.JLabel();
        AbrirNav4 = new javax.swing.JLabel();
        lblModificar = new javax.swing.JLabel();
        AbrirNav6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableMostrarPartidas = new javax.swing.JTable();
        lblPeriodo = new javax.swing.JLabel();
        AbrirNav2 = new javax.swing.JLabel();
        lblPeriodo1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        btnReporteLibroDiario = new javax.swing.JButton();
        PanelLibroMayor = new javax.swing.JPanel();
        AbrirNav7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        AbrirNav8 = new javax.swing.JLabel();
        btnActualizarMyr = new javax.swing.JLabel();
        btnReporteMayor = new javax.swing.JButton();
        PanelBalanceComprobacion = new javax.swing.JPanel();
        AbrirNav9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        AbrirNav10 = new javax.swing.JLabel();
        btnActualizarComp = new javax.swing.JLabel();
        AbrirNav11 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        PanelEstadoResultados = new javax.swing.JPanel();
        AbrirNav12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        AbrirNav13 = new javax.swing.JLabel();
        btnActualizarEstRes = new javax.swing.JLabel();
        btnReporteResultado = new javax.swing.JButton();
        PanelBalanceGeneral = new javax.swing.JPanel();
        lblBG = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        EmpresaBG = new javax.swing.JLabel();
        btnActualizarBG = new javax.swing.JLabel();
        lblTOTALESBG = new javax.swing.JLabel();
        txtBG1 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jTextField9 = new javax.swing.JTextField();
        txtBG2 = new javax.swing.JTextField();
        txtBG3 = new javax.swing.JTextField();
        btnReporteGeneral = new javax.swing.JButton();
        PanelEncabezado = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JLabel();
        btnMinimizar = new javax.swing.JLabel();
        lblPanelActual = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        btnElimLibro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(81, 110, 128));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(81, 110, 128));
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(920, 570));
        setResizable(false);
        setSize(new java.awt.Dimension(920, 570));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PanelNavMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PanelNavMouseExited(evt);
            }
        });

        btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguienteMouseClicked(evt);
            }
        });

        btnAnterior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnteriorMouseClicked(evt);
            }
        });

        AbrirNav.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav.setText("Navegacion");
        AbrirNav.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AbrirNav.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AbrirNavMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelNavLayout = new javax.swing.GroupLayout(PanelNav);
        PanelNav.setLayout(PanelNavLayout);
        PanelNavLayout.setHorizontalGroup(
            PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNavLayout.createSequentialGroup()
                .addGroup(PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelNavLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelNavLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(AbrirNav)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PanelNavLayout.setVerticalGroup(
            PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelNavLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(PanelNavLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(AbrirNav, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(PanelNav, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, -100, 180, -1));

        PanelLibroDiario.setBackground(new java.awt.Color(81, 110, 128));
        PanelLibroDiario.setPreferredSize(new java.awt.Dimension(920, 540));
        PanelLibroDiario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelEditPartida.setBackground(new java.awt.Color(102, 137, 203));

        btnAjuste.setBackground(new java.awt.Color(241, 237, 225));
        btnAjuste.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnAjuste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAjuste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAjuste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAjusteMouseClicked(evt);
            }
        });

        btnAgregarPartida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarPartida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarPartidaMouseClicked(evt);
            }
        });

        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });

        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        AbrirNav1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        AbrirNav1.setText("Agregar Partida");
        AbrirNav1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblEliminar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEliminar.setText("Eliminar Partida");
        lblEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        AbrirNav4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav4.setText("Menù");
        AbrirNav4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        lblModificar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblModificar.setText("Modificar Partiida");
        lblModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        AbrirNav6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        AbrirNav6.setText("Ajuste de IVA");
        AbrirNav6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout PanelEditPartidaLayout = new javax.swing.GroupLayout(PanelEditPartida);
        PanelEditPartida.setLayout(PanelEditPartidaLayout);
        PanelEditPartidaLayout.setHorizontalGroup(
            PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEditPartidaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                        .addComponent(AbrirNav1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                        .addComponent(lblModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                        .addComponent(lblEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                        .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                                .addComponent(AbrirNav6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(35, 35, 35))
                            .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                                .addComponent(AbrirNav4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAjuste, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
        PanelEditPartidaLayout.setVerticalGroup(
            PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEditPartidaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AbrirNav4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAjuste, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AbrirNav6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AbrirNav1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelEditPartidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        PanelLibroDiario.add(PanelEditPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 90, 280, 330));

        TableMostrarPartidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Codigo", "Descripcion", "Debe", "Haber"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableMostrarPartidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMostrarPartidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableMostrarPartidas);

        PanelLibroDiario.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 750, 360));

        lblPeriodo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblPeriodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPeriodo.setText("Libro Diario, del 1 de enero al 31 de diciembre de 2018");
        lblPeriodo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelLibroDiario.add(lblPeriodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 710, -1));

        AbrirNav2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav2.setText("Agro Ferreteria San Francisco");
        AbrirNav2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelLibroDiario.add(AbrirNav2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 710, -1));

        lblPeriodo1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblPeriodo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPeriodo1.setText("Totales");
        lblPeriodo1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelLibroDiario.add(lblPeriodo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 100, -1));

        jTextField1.setText("jTextField1");
        PanelLibroDiario.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, 130, 30));

        jTextField2.setText("jTextField2");
        PanelLibroDiario.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 140, 30));

        btnReporteLibroDiario.setText("Reportes");
        btnReporteLibroDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteLibroDiarioActionPerformed(evt);
            }
        });
        PanelLibroDiario.add(btnReporteLibroDiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 480, -1, -1));

        getContentPane().add(PanelLibroDiario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        PanelLibroMayor.setBackground(new java.awt.Color(105, 171, 165));
        PanelLibroMayor.setPreferredSize(new java.awt.Dimension(920, 540));
        PanelLibroMayor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AbrirNav7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav7.setText("Libro Mayor del 1 de enero al 31 de diciembre de 2018");
        AbrirNav7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelLibroMayor.add(AbrirNav7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 750, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Fecha", "Detalle", "Debe", "Haber", "Saldo"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        PanelLibroMayor.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 750, 340));

        AbrirNav8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav8.setText("Agro Ferreteria San Francisco");
        AbrirNav8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelLibroMayor.add(AbrirNav8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 750, -1));

        btnActualizarMyr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarMyr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMyrMouseClicked(evt);
            }
        });
        PanelLibroMayor.add(btnActualizarMyr, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 50, 50));

        btnReporteMayor.setText("Reporte");
        btnReporteMayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteMayorActionPerformed(evt);
            }
        });
        PanelLibroMayor.add(btnReporteMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        getContentPane().add(PanelLibroMayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 50, -1, 530));

        PanelBalanceComprobacion.setBackground(new java.awt.Color(122, 153, 199));
        PanelBalanceComprobacion.setPreferredSize(new java.awt.Dimension(920, 540));
        PanelBalanceComprobacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AbrirNav9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav9.setText("Balance de Comprobacion del 1 de enero al 31 de diciembre de 2018");
        AbrirNav9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelBalanceComprobacion.add(AbrirNav9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 750, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Cuenta", "Debe", "Haber"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        PanelBalanceComprobacion.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 750, 370));

        AbrirNav10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav10.setText("Agro Ferreteria San Francisco");
        AbrirNav10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelBalanceComprobacion.add(AbrirNav10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 750, -1));

        btnActualizarComp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarCompMouseClicked(evt);
            }
        });
        PanelBalanceComprobacion.add(btnActualizarComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 50, 50));

        AbrirNav11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav11.setText("TOTALES");
        AbrirNav11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelBalanceComprobacion.add(AbrirNav11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 100, -1));

        jTextField4.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceComprobacion.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 160, 20));

        jTextField3.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceComprobacion.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 490, 160, 20));

        jButton1.setText("Reportes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        PanelBalanceComprobacion.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        getContentPane().add(PanelBalanceComprobacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1840, 50, -1, 530));

        PanelEstadoResultados.setBackground(new java.awt.Color(198, 231, 246));
        PanelEstadoResultados.setPreferredSize(new java.awt.Dimension(920, 540));
        PanelEstadoResultados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AbrirNav12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav12.setText("Estado de Resultados del 1 de enero al 31 de diciembre de 2018");
        AbrirNav12.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelEstadoResultados.add(AbrirNav12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 750, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "", ""
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        PanelEstadoResultados.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 750, 370));

        AbrirNav13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        AbrirNav13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AbrirNav13.setText("Agro Ferreteria San Francisco");
        AbrirNav13.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelEstadoResultados.add(AbrirNav13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 750, -1));

        btnActualizarEstRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarEstRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarEstResMouseClicked(evt);
            }
        });
        PanelEstadoResultados.add(btnActualizarEstRes, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, 50, 50));

        btnReporteResultado.setText("Reporte");
        btnReporteResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteResultadoActionPerformed(evt);
            }
        });
        PanelEstadoResultados.add(btnReporteResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        getContentPane().add(PanelEstadoResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(2760, 50, -1, 530));

        PanelBalanceGeneral.setBackground(new java.awt.Color(201, 210, 209));
        PanelBalanceGeneral.setPreferredSize(new java.awt.Dimension(920, 540));
        PanelBalanceGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBG.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblBG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBG.setText("Balance General del 1 de enero al 31 de diciembre de 2018");
        lblBG.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelBalanceGeneral.add(lblBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 750, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Pasivo", "$"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        PanelBalanceGeneral.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 360, 370));

        EmpresaBG.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        EmpresaBG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EmpresaBG.setText("Agro Ferreteria San Francisco");
        EmpresaBG.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelBalanceGeneral.add(EmpresaBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 750, -1));

        btnActualizarBG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarBG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarBGMouseClicked(evt);
            }
        });
        PanelBalanceGeneral.add(btnActualizarBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 50, 50));

        lblTOTALESBG.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lblTOTALESBG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTOTALESBG.setText("TOTALES");
        lblTOTALESBG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelBalanceGeneral.add(lblTOTALESBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 100, -1));

        txtBG1.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceGeneral.add(txtBG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, 70, 20));

        jTextField8.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceGeneral.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 160, 20));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Activo", "$"
            }
        ));
        jScrollPane6.setViewportView(jTable4);

        PanelBalanceGeneral.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 360, 370));

        jTextField9.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceGeneral.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 160, 20));

        txtBG2.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceGeneral.add(txtBG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 70, 20));

        txtBG3.setBackground(new java.awt.Color(241, 237, 225));
        PanelBalanceGeneral.add(txtBG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, 70, 20));

        btnReporteGeneral.setText("Reporte");
        btnReporteGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteGeneralActionPerformed(evt);
            }
        });
        PanelBalanceGeneral.add(btnReporteGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 100, -1, -1));

        getContentPane().add(PanelBalanceGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(3680, 50, -1, 530));

        PanelEncabezado.setBackground(new java.awt.Color(241, 237, 225));

        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });

        btnMinimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMinimizarMouseClicked(evt);
            }
        });

        lblPanelActual.setBackground(new java.awt.Color(0, 27, 27));
        lblPanelActual.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPanelActual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPanelActual.setText("Libro #");
        lblPanelActual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        btnElimLibro.setText("elim");
        btnElimLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnElimLibroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelEncabezadoLayout = new javax.swing.GroupLayout(PanelEncabezado);
        PanelEncabezado.setLayout(PanelEncabezadoLayout);
        PanelEncabezadoLayout.setHorizontalGroup(
            PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPanelActual)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnElimLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 595, Short.MAX_VALUE)
                .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelEncabezadoLayout.setVerticalGroup(
            PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPanelActual, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnElimLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(PanelEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /* Inicio botones con animaciones */

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked

        System.exit(0);
// TODO add your handling code here:
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void btnMinimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMinimizarMouseClicked

        this.setExtendedState(ICONIFIED);

    }//GEN-LAST:event_btnMinimizarMouseClicked

    /*Inicio de panel de navegacion*/

    private void btnSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteMouseClicked

        if (PanelBalanceGeneral.getX() != 0) { // Si aun no hemos llegado al ultimo panel

            Contador++; //aumentamos el contador de paneles
            Avanzar();

            if (PanelBalanceGeneral.getX() == 0) {
                PanelActual = Paneles[1];
            }

            if (Contador - 1 >= 0) {

                btnAnterior.setToolTipText(Paneles[Contador - 1]); //pondremos el texto con el panel anterior
                btnSiguiente.setToolTipText(Paneles[Contador + 1]); //pondremos el texto con el panel siguiente

            }

        } //si ya llegamos al ultimo panel no hacemos nada


    }//GEN-LAST:event_btnSiguienteMouseClicked

    private void btnAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnteriorMouseClicked

        if (PanelLibroDiario.getX() != 0) { //si NO estamos en el libro diario

            Contador--;    //reducimos el contador de paneles
            Retroceder();

            if (PanelLibroDiario.getX() == 0) {
                PanelActual = Paneles[0];
            }

            if (Contador + 1 < Paneles.length) {

                btnAnterior.setToolTipText(Paneles[Contador - 1]);
                btnSiguiente.setToolTipText(Paneles[Contador + 1]);

            }

        } //si estamos en el libro diario, no se retrocedera mas

    }//GEN-LAST:event_btnAnteriorMouseClicked


    private void PanelNavMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNavMouseEntered

        // le instanciamos la posicion inicial (-100) la posicion final (-80), un retraso (2 milisegundos), y la velocidad ( 1 pixel cada 2 milisegundos )
        anim.jPanelYDown(-100, -80, 2, 1, PanelNav); // movemos un poco el panel de navegacion hacia abajo
        //para que resalte el panel cuando pase el mouse por encima

    }//GEN-LAST:event_PanelNavMouseEntered

    private void PanelNavMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelNavMouseExited

        anim.jPanelYUp(-80, -100, 2, 1, PanelNav); // si el mouse salio escondemos del todo el panel

    }//GEN-LAST:event_PanelNavMouseExited

    private void AbrirNavMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AbrirNavMouseClicked

        anim.jPanelYDown(-80, 0, 2, 1, PanelNav); //en caso de que este en -80 (escondido pero resaltado por el mouse) lo movemos a cero (mostrado)

        anim.jPanelYUp(0, -100, 2, 1, PanelNav); //en caso de que este en cero (mostrado) lo movemos a -100 (escondido del todo)

    }//GEN-LAST:event_AbrirNavMouseClicked

    /*Fin del panel de navegacion*/
    //Panel de la izquierda

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        //Si apretamos el boton de menu

        anim.jPanelXRight(-200, 0, 2, 1, PanelEditPartida); //si el panel esta escondido (-200) lo mostramos (0)
        anim.jPanelXLeft(0, -200, 2, 1, PanelEditPartida); //si el panel esta mostrado (0) lo escondemos (-200)

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarMouseClicked

    /* botones de chepe */

    private void btnAjusteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjusteMouseClicked
        AjusteIVA();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjusteMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked

        AgregarPartida agg = new AgregarPartida();
        DefaultTableModel modelo = (DefaultTableModel) agg.tablePartidaPreview.getModel();
        try {
            Conexion con = new Conexion();
            ResultSet rs = null;
            ResultSet rs1 = null;

            //rs = con.Consulta("SELECT `id_partida`, `fecha`, `concepto` FROM `partida` WHERE `id_partida` = " + idPartida + " && n_libro = '" + jSpinner1.getValue().toString() + "'", con.getConexion());
            rs = con.Consulta("SELECT `id_partida`, `fecha`, `concepto`, `n_partida`,`n_libro` FROM `partida` WHERE `n_partida`='" + idPartida + "' && `n_libro` = '" + jSpinner1.getValue().toString() + "' ", con.getConexion());

            rs1 = con.Consulta("SELECT cuenta.codigo,cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE partida.n_partida = '" + idPartida + "' && partida.n_libro = '" + jSpinner1.getValue().toString() + "' ", con.getConexion());

            while (rs.next()) {
                String partida = "Partida " + rs.getString("id_partida");
                modelo.addRow(new Object[]{rs.getString("fecha"), "", partida, "", ""});

                //hay que mandar la fecha
                String fecha = rs.getString("fecha");
                SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fechaDate;
                try {
                    fechaDate = form.parse(fecha);
                    agg.txtFecha.setDate(fechaDate);

                } catch (ParseException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                //agg.txtFecha.setText(rs.getString("fecha"));
                agg.txtModif.setText(rs.getString("id_partida"));
                while (rs1.next()) {
                    modelo.addRow(new Object[]{"", rs1.getString("codigo"), rs1.getString("nombre_cuenta"), rs1.getString("Debe"), rs1.getString("Haber")});
                }
                modelo.addRow(new Object[]{"", "", rs.getString("concepto"), "", ""});
                agg.txtConcepto.setText(rs.getString("concepto"));

            }
            agg.jSpinner1.setValue(jSpinner1.getValue());
            con.close(); //aqui esta un close
            rs.close();
            rs1.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        agg.btnAgregar.setVisible(false);
        agg.btnModificar.setVisible(true);
        agg.setVisible(true);
        agg.setDefaultCloseOperation(agg.DISPOSE_ON_CLOSE);
        agg.setLocationRelativeTo(null);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {

        if (EliminarPartidaBD()) {
            ModificarId(idPartida);
            CargandoPartidas(Integer.parseInt(jSpinner1.getValue().toString()));
            sumar(TableMostrarPartidas, 3, 4, jTextField1, jTextField2);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
        }
        btnEliminar.setVisible(false);
        btnModificar.setVisible(false);

// TODO add your handling code here:
    }

    /* fin del CRUD (Ag, Mod, Elim, ajuste iva) Inicio botones actualizar*/

    private void btnActualizarMyrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMyrMouseClicked

        LibroMayor(Integer.parseInt(jSpinner1.getValue().toString()));
// btn actualizar del libro mayor
    }//GEN-LAST:event_btnActualizarMyrMouseClicked

    private void btnActualizarCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarCompMouseClicked

        BalanceComprobacion(Integer.parseInt(jSpinner1.getValue().toString()));
        sumar(jTable2, 2, 3, jTextField3, jTextField4);
        // actualizar de comprobacion
    }//GEN-LAST:event_btnActualizarCompMouseClicked

    private void btnActualizarEstResMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarEstResMouseClicked

        EstadoResultados(Integer.parseInt(jSpinner1.getValue().toString()));
// actualizar estado de resultados
    }//GEN-LAST:event_btnActualizarEstResMouseClicked

    private void btnActualizarBGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarBGMouseClicked

        BalanceGeneral(Integer.parseInt(jSpinner1.getValue().toString()));
// actualizar balance general
    }//GEN-LAST:event_btnActualizarBGMouseClicked

    private void btnAgregarPartidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarPartidaMouseClicked

        l.setAnterior(Integer.parseInt(jSpinner1.getValue().toString()));//le pasamos este dato para usarlo en agregarpartida

        AgregarPartida agregarPartida = new AgregarPartida();
        agregarPartida.setVisible(true);
        //agregarPartida.cargarNPartida();
        //agregarPartida.cargarLista("SELECT * FROM `cuenta`;");
        //agregarPartida.eliminar(true);
        agregarPartida.setLocationRelativeTo(null);
        agregarPartida.setDefaultCloseOperation(agregarPartida.DISPOSE_ON_CLOSE);
        CargandoPartidas(Integer.parseInt(jSpinner1.getValue().toString()));
        agregarPartida.jSpinner1.setValue(jSpinner1.getValue());

// TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarPartidaMouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged

        Conexion c = new Conexion();
        ResultSet rs = c.Consulta("SELECT n_libro FROM librodiario WHERE n_libro = '" + jSpinner1.getValue().toString() + "'", c.getConexion());
        l.setAnterior(Integer.parseInt(jSpinner1.getValue().toString()));//le pasamos este dato para usarlo en agregarpartida

        try {
            if (rs.next()) {
                spin = Integer.parseInt(jSpinner1.getValue().toString());
                CargandoPartidas(Integer.parseInt(jSpinner1.getValue().toString()));
                LibroMayor(Integer.parseInt(jSpinner1.getValue().toString()));
                BalanceComprobacion(Integer.parseInt(jSpinner1.getValue().toString()));
                sumar(TableMostrarPartidas, 3, 4, jTextField1, jTextField2);
                sumar(jTable2, 2, 3, jTextField3, jTextField4);
                EstadoResultados(Integer.parseInt(jSpinner1.getValue().toString()));
                BalanceGeneral(Integer.parseInt(jSpinner1.getValue().toString()));

            } else {

                NuevoLibro nv = new NuevoLibro();
                nv.setVisible(true);
                nv.setLocationRelativeTo(null);
                this.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_jSpinner1StateChanged

    private void TableMostrarPartidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMostrarPartidasMouseClicked

        int i = 1;//contador
        int Fila = TableMostrarPartidas.getSelectedRow();//obtengo el numero de fila seleccionada
        int Col = TableMostrarPartidas.getRowCount();//obtengo el numero total de filas que hay en la tabla
        String nPartida = TableMostrarPartidas.getValueAt(Fila, 2).toString();//Obtener la el texto de la celda seleccionada
        String busqueda = "Partida N. ";//esta variable la utilizaremos para saber cual es el numero de partida
        while (Col > 0) {
            //aqui hacemos una validacion para saber cual es el numero de la partida que se a seleccionado
            if ((busqueda + i).equals(nPartida)) {
                Col = 0;//para que salga del while
                idPartida = i;
                //mostramos los botones
                btnModificar.setVisible(true);
                btnEliminar.setVisible(true);
                lblModificar.setVisible(true);
                lblEliminar.setVisible(true);
            } else {
                //ocultamos los botones
                btnModificar.setVisible(false);
                btnEliminar.setVisible(false);
                lblModificar.setVisible(false);
                lblEliminar.setVisible(false);
            }
            i++;
            Col--;
        }
// TODO add your handling code here:
    }//GEN-LAST:event_TableMostrarPartidasMouseClicked

    private void btnElimLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnElimLibroMouseClicked

        Conexion con = new Conexion();
        ResultSet rs = con.Consulta("SELECT n_libro FROM `librodiario` ORDER BY `n_libro` DESC LIMIT 1 ", con.getConexion());
        int totalLibros = 0;
        try {
            if (rs.next()) {
                totalLibros = Integer.parseInt(rs.getString(1));
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        int borre = Integer.parseInt(jSpinner1.getValue().toString());
        int nuevo = borre + 1;
        con.Ejecutar("DELETE FROM `librodiario` WHERE `n_libro` = '" + jSpinner1.getValue().toString() + "'");
        int voyMod = totalLibros - borre;

        int i = 0;
        while (i < voyMod) {
            con.Ejecutar("UPDATE `librodiario` SET `n_libro`='" + borre + "' WHERE `n_libro` ='" + nuevo + "'");
            nuevo++;
            i++;
        }
        int a = Integer.parseInt(jSpinner1.getValue().toString());

        jSpinner1.setValue(a - 1);
// TODO add your handling code here:
    }//GEN-LAST:event_btnElimLibroMouseClicked

    private void btnReporteLibroDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteLibroDiarioActionPerformed

        List lista = new ArrayList();
        ReporteDiario rd = null;
        for (int i = 0; i < TableMostrarPartidas.getRowCount() + 1; i++) {
            //System.out.println(TableMostrarPartidas.getValueAt(i, 2).toString().substring(0, 2));

            if (i < TableMostrarPartidas.getRowCount()) {
                if (TableMostrarPartidas.getValueAt(i, 2).toString().substring(0, 4).startsWith("Part") || TableMostrarPartidas.getValueAt(i, 2).toString().substring(0, 2).startsWith("C/")) {
                    rd = new ReporteDiario(TableMostrarPartidas.getValueAt(i, 0).toString(), TableMostrarPartidas.getValueAt(i, 1).toString(), TableMostrarPartidas.getValueAt(i, 2).toString(), TableMostrarPartidas.getValueAt(i, 3).toString(), TableMostrarPartidas.getValueAt(i, 4).toString());

                } else {
                    rd = new ReporteDiario(TableMostrarPartidas.getValueAt(i, 0).toString(), TableMostrarPartidas.getValueAt(i, 1).toString(), TableMostrarPartidas.getValueAt(i, 2).toString(), "$ " + TableMostrarPartidas.getValueAt(i, 3).toString(), "$ " + TableMostrarPartidas.getValueAt(i, 4).toString());
                }
            } else {
                rd = new ReporteDiario("", "", "TOTALES", "$ " + jTextField1.getText().toString(), "$ " + jTextField2.getText().toString());

            }

            lista.add(rd);
        }

        JasperReport reporte = null;

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/LibroDiario.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnReporteLibroDiarioActionPerformed

    private void btnReporteMayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteMayorActionPerformed
        List lista = new ArrayList();
        ReporteMayor rm;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            //System.out.println(TableMostrarPartidas.getValueAt(i, 2).toString().substring(0, 2));
            if (jTable1.getValueAt(i, 0).toString() == "") {
                rm = new ReporteMayor(jTable1.getValueAt(i, 0).toString(), jTable1.getValueAt(i, 1).toString(), jTable1.getValueAt(i, 2).toString(), jTable1.getValueAt(i, 3).toString(), jTable1.getValueAt(i, 4).toString());

            } else {
                rm = new ReporteMayor(jTable1.getValueAt(i, 0).toString(), jTable1.getValueAt(i, 1).toString(), "$ " + jTable1.getValueAt(i, 2).toString(), "$ " + jTable1.getValueAt(i, 3).toString(), "$ " + jTable1.getValueAt(i, 4).toString());
            }
            lista.add(rm);
        }

        JasperReport reporte = null;

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/LibroMayor.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteMayorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List lista = new ArrayList();
        ReporteComprobacion rc = null;
        for (int i = 0; i < jTable2.getRowCount() + 1; i++) {
            if (i < jTable2.getRowCount()) {
                rc = new ReporteComprobacion(jTable2.getValueAt(i, 0).toString(), jTable2.getValueAt(i, 1).toString(), "$ " + jTable2.getValueAt(i, 2).toString(), "$ " + jTable2.getValueAt(i, 3).toString());
            } else {
                rc = new ReporteComprobacion("", "TOTALES", "$ " + jTextField3.getText().toString(), "$ " + jTextField4.getText().toString());

            }
            lista.add(rc);
        }

        JasperReport reporte = null;

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/BalanceComprobacion.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnReporteResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteResultadoActionPerformed
        List lista = new ArrayList();
        ReporteEstadoResultados re = null;
        for (int i = 0; i < jTable3.getRowCount(); i++) {

            re = new ReporteEstadoResultados(jTable3.getValueAt(i, 0).toString(), "$ " + jTable3.getValueAt(i, 1).toString());

            lista.add(re);
        }

        JasperReport reporte = null;

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/EstadoResultados.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteResultadoActionPerformed

    private void btnReporteGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteGeneralActionPerformed
        List lista = new ArrayList();
        ReporteBalanceGeneral rb = null;
        //veremos que tabla tiene mas filas
        if (jTable4.getRowCount() < jTable5.getRowCount()) {
            for (int i = 0; i < jTable5.getRowCount() + 1; i++) {
                if (i < jTable4.getRowCount()) {
                    rb = new ReporteBalanceGeneral(jTable4.getValueAt(i, 0).toString(), jTable4.getValueAt(i, 1).toString(), jTable5.getValueAt(i, 0).toString(), jTable5.getValueAt(i, 1).toString());
                } else if (i < jTable5.getRowCount()) {
                    System.out.println(i);
                    rb = new ReporteBalanceGeneral("", "", jTable5.getValueAt(i, 0).toString(), jTable5.getValueAt(i, 1).toString());

                } else {
                    rb = new ReporteBalanceGeneral("TOTAL", jTextField8.getText().toString().toString(), "TOTAL", jTextField9.getText().toString());

                }
                lista.add(rb);
            }
        } else if (jTable4.getRowCount() > jTable5.getRowCount()) {
            for (int i = 0; i < jTable4.getRowCount(); i++) {
                if (i < jTable5.getRowCount()) {
                    rb = new ReporteBalanceGeneral(jTable4.getValueAt(i, 0).toString(), jTable4.getValueAt(i, 1).toString(), jTable5.getValueAt(i, 0).toString(), jTable5.getValueAt(i, 1).toString());
                }  else if(i < jTable4.getRowCount()){
                    rb = new ReporteBalanceGeneral(jTable4.getValueAt(i, 0).toString(), jTable4.getValueAt(i, 1).toString(), "", "");

                }else {
                    rb = new ReporteBalanceGeneral("TOTAL", jTextField8.getText().toString().toString(), "TOTAL", jTextField9.getText().toString());

                }
                lista.add(rb);
            }
        } else if (jTable4.getRowCount() == jTable5.getRowCount()) {
            for (int i = 0; i < jTable4.getRowCount() + 1; i++) {
                if (i < jTable4.getRowCount() + 1) {
                    rb = new ReporteBalanceGeneral(jTable4.getValueAt(i, 0).toString(), jTable4.getValueAt(i, 1).toString(), jTable5.getValueAt(i, 0).toString(), jTable5.getValueAt(i, 1).toString());
                } else {
                    rb = new ReporteBalanceGeneral("TOTAL", jTextField8.getText().toString().toString(), "TOTAL", jTextField9.getText().toString());

                }
                lista.add(rb);
            }
        }

        JasperReport reporte = null;

        try {
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/BalanceGenera.jasper"));
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(lista));
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnReporteGeneralActionPerformed

    /*fin botones de chepe*/
    public void Retroceder() {

        //movemos todos los paneles 920 pixeles (ancho de la ventana) asi -> -> -> lo que nos dara la sensacion de ir retrocediendo
        //arriba validamos que si se llego al libro diario no se retroceda mas
        anim.jPanelXRight(PanelLibroMayor.getX(), PanelLibroMayor.getX() + 920, 1, 5, PanelLibroMayor);
        anim.jPanelXRight(PanelLibroDiario.getX(), PanelLibroDiario.getX() + 920, 1, 5, PanelLibroDiario);
        anim.jPanelXRight(PanelBalanceComprobacion.getX(), PanelBalanceComprobacion.getX() + 920, 1, 5, PanelBalanceComprobacion);
        anim.jPanelXRight(PanelEstadoResultados.getX(), PanelEstadoResultados.getX() + 920, 1, 5, PanelEstadoResultados);
        anim.jPanelXRight(PanelBalanceGeneral.getX(), PanelBalanceGeneral.getX() + 920, 1, 5, PanelBalanceGeneral);

    }

    public void Avanzar() {

        //Movemos todos los paneles 920 pixeles asi <- <- <- lo que nos dara la sensacion de ir avanzando
        //arriba validamos que si se llego al balance general no sigamos avanzando
        anim.jPanelXLeft(PanelLibroMayor.getX(), PanelLibroMayor.getX() - 920, 1, 5, PanelLibroMayor);
        anim.jPanelXLeft(PanelLibroDiario.getX(), PanelLibroDiario.getX() - 920, 1, 5, PanelLibroDiario);
        anim.jPanelXLeft(PanelBalanceComprobacion.getX(), PanelBalanceComprobacion.getX() - 920, 1, 5, PanelBalanceComprobacion);
        anim.jPanelXLeft(PanelEstadoResultados.getX(), PanelEstadoResultados.getX() - 920, 1, 5, PanelEstadoResultados);
        anim.jPanelXLeft(PanelBalanceGeneral.getX(), PanelBalanceGeneral.getX() - 920, 1, 5, PanelBalanceGeneral);

    }

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);

            }
        });
    }

    public void CargandoPartidas(int libro) {
        boolean iva = false;
        DefaultTableModel modelo = (DefaultTableModel) TableMostrarPartidas.getModel();
        Conexion con = new Conexion();
        ResultSet rs = con.Consulta("SELECT Periodo FROM librodiario WHERE n_libro = '" + libro + "'", con.getConexion());

        try {
            if (rs.next()) {
                lblPeriodo.setText(rs.getString("Periodo"));
                AbrirNav7.setText("Libro Mayor " + rs.getString("Periodo"));
                AbrirNav9.setText("Balance de Comprobacion " + rs.getString("Periodo"));
                AbrirNav12.setText("Estado de Resultados " + rs.getString("Periodo"));
                lblBG.setText("Balance General " + rs.getString("Periodo"));
            }
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        //vacia la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        //tamaño de las columnas
        int[] anchos = {40, 40, 200, 50, 50};

        for (int i = 0; i < modelo.getColumnCount(); i++) {
            TableMostrarPartidas.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        rs = con.Consulta("SELECT `id_partida`, `fecha`, `concepto`, `n_partida`, `n_libro` FROM `partida` WHERE `n_libro` ='" + libro + "'", con.getConexion());//consulta
        try {
            while (rs.next()) {
                modelo.addRow(new Object[]{rs.getString("fecha"), "", "Partida N. " + rs.getString("n_partida"), "", ""});
                Conexion con1 = new Conexion();
                ResultSet rs1 = con1.Consulta("SELECT cuenta.codigo,cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta INNER JOIN cuenta_partida ON cuenta.id_cuenta = cuenta_partida.cuenta_id WHERE partida_id = " + rs.getString("id_partida") + "", con.getConexion());//consulta
                try {
                    while (rs1.next()) {
                        modelo.addRow(new Object[]{"", rs1.getString("codigo"), rs1.getString("nombre_cuenta"), rs1.getString("Debe"), rs1.getString("Haber")});

                    }
                    rs1.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                modelo.addRow(new Object[]{"", "", "C/ " + rs.getString("concepto"), "", ""});
                sumar(TableMostrarPartidas, 3, 4, jTextField1, jTextField2);

                //validaremos que no este hecho el ajuste de IVA para ocultarlo si ya esta
                if (rs.getString("concepto").equals("Ajuste de IVA")) {
                    iva = true;
                } else {
                    iva = false;
                }
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (iva) {
            btnAjuste.setVisible(false);
        } else {
            btnAjuste.setVisible(true);

        }
    }

    public void sumar(javax.swing.JTable tabla, int columna1, int columna2, javax.swing.JTextField jt1, javax.swing.JTextField jt2) {

        double t1 = 0;
        double p1 = 0;
        if (tabla.getRowCount() > 1) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if (!tabla.getValueAt(i, columna1).toString().isEmpty()) {
                    p1 = Double.parseDouble(tabla.getValueAt(i, columna1).toString());
                    t1 += p1;
                }
            }
            jt1.setText(String.valueOf(formato.format(t1)));
        } else {
            jt1.setText("");
        }

        double t = 0;
        double p = 0;
        if (tabla.getRowCount() > 1) {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if (!tabla.getValueAt(i, columna2).toString().isEmpty()) {
                    p = Double.parseDouble(tabla.getValueAt(i, columna2).toString());
                    t += p;
                }
            }
            jt2.setText(String.valueOf(formato.format(t)));
        } else {
            jt2.setText("");
        }

    }

    public boolean EliminarPartidaBD() {
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        Connection conn = con.getConexion();

        try {
            ps = conn.prepareStatement("DELETE FROM `partida` WHERE `n_partida` = " + idPartida + " && n_libro = '" + jSpinner1.getValue().toString() + "'");
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /*Esta funcion modifica los id de la tabla partida, tomando en cuenta un id eliminado que es el parametro que recibe*/
    public void ModificarId(int id_partida) {
        int habian = CuantosIdP() + 1;//almacena cuantos id habian antes de eliminar uno
        int VoyAmodificar = habian - id_partida;/*Esta variable recibe el numero de id que voy a modificar, la uso para
         contar los ciclos que tendra el while de abajo*/

        int a = 1; //contador para el ciclo while

        int modifica = id_partida + 1;/*esta variable recibe el id que voy a modificar en la base de datos, y como voy a 
         modificar el siguiente id despues del que elimine le sumo 1, ejemplo:
         si elimine el 2 voy a modificar el 3*/

        int nuevo = id_partida;/*esta variable recibe el nuevo id, y como sustituire el que elimine le paso ese valor*/

        while (a <= VoyAmodificar) {
            Conexion con = new Conexion();
            PreparedStatement ps = null;
            Connection conn = con.getConexion();
            try {
                ps = conn.prepareStatement("UPDATE `partida` SET `n_partida`= " + nuevo + " WHERE `n_partida` = " + modifica + " && n_libro = '" + jSpinner1.getValue().toString() + "'");
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            modifica++;
            nuevo++;
            a++;
        }

    }

    public int CuantosIdP() {
        int n = 0;//variable que retorna el valor
        Conexion con = new Conexion();
        PreparedStatement ps = null;
        Connection conn = con.getConexion();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM `partida` WHERE `n_libro` = '" + jSpinner1.getValue().toString() + "' ");
            rs = ps.executeQuery();
            if (rs.next()) {
                n = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void AjusteIVA() {
        //primero hay que recorrer las cuentas en busca de DFI o CFI
        int nFilas = TableMostrarPartidas.getRowCount();//obtenemos el numero total de filas en la tabla
        Double debitoD = 0.0;
        Double debitoH = 0.0;
        Double creditoD = 0.0;
        Double creditoH = 0.0;
        Double totalC = 0.0;
        Double totalD = 0.0;
        boolean IVAremanente = false;
        boolean IVAimpuesto = false;
        for (int i = 0; i < nFilas; i++) {
            /*dentro de este for vamos a tomar los valores del DFI/CFI y separar respectivamente si esta en el debe o haber*/
            if (TableMostrarPartidas.getValueAt(i, 2).toString().equals("Debito Fiscal IVA")) {
                debitoD += Double.parseDouble(TableMostrarPartidas.getValueAt(i, 3).toString());
                debitoH += Double.parseDouble(TableMostrarPartidas.getValueAt(i, 4).toString());
            } else if (TableMostrarPartidas.getValueAt(i, 2).toString().equals("Credito Fiscal IVA")) {
                creditoD += Double.parseDouble(TableMostrarPartidas.getValueAt(i, 3).toString());
                creditoH += Double.parseDouble(TableMostrarPartidas.getValueAt(i, 4).toString());
            }
        }

        //ahora vamos a realizar las T para el DFI y CFI
        if (debitoD > debitoH) {
            totalD = debitoD - debitoH;
        } else if (debitoD < debitoH) {
            totalD = debitoH - debitoD;
        }

        if (creditoD > creditoH) {
            totalC = creditoD - creditoH;
        } else if (creditoD < creditoH) {
            totalC = creditoH - creditoD;
        }

        if ((totalC > 0) || (totalD > 0)) {
            //hacemos el ajuste de iva
            Double remanente = 0.0;
            Double impuesto = 0.0;

            if (totalC > totalD) {
                remanente = totalC - totalD;
                IVAremanente = true;
            } else if (totalC < totalD) {
                impuesto = totalD - totalC;
                IVAimpuesto = true;
            }

            //hacemos y subimos la partida a la base de datos
            //primero obtenemos la ultima partida ingresada
            int ultimaPartida = CuantosIdP();
            ultimaPartida = ultimaPartida + 1;//nueva partida

            //obtenemos la fecha del sistema
            Calendar fecha = new GregorianCalendar();
            int anioi = fecha.get(Calendar.YEAR);
            String anio = String.valueOf(anioi);
            int mesi = fecha.get(Calendar.MONTH);
            String mes = String.valueOf(mesi);
            int diai = fecha.get(Calendar.DAY_OF_MONTH);
            if (diai < 10) {
                String dia = "0" + String.valueOf(diai);
            } else {
                String dia = String.valueOf(diai);
            }

            String fechaS = "2010" + "-" + mes + "-" + "02";
            String a = "Ajuste de IVA";
            //insertamos la partida
            DecimalFormat formato = new DecimalFormat("#.00");
            Conexion insertar = new Conexion();
            insertar.Ejecutar("INSERT INTO `partida` (`id_partida`, `fecha`, `concepto`, n_partida,n_libro) VALUES (NULL, '" + fechaS + "', 'Ajuste de IVA','" + ultimaPartida + "','" + jSpinner1.getValue().toString() + "');");

            //hay que obtener el ultimo id de las partidas para ingresar el nuevo
            String ultimoId = "";
            Conexion con = new Conexion();
            ResultSet rs = con.Consulta("select id_partida from partida order by id_partida DESC limit 1 ", con.getConexion());

            try {
                if (rs.next()) {
                    ultimoId = rs.getString("id_partida");
                }
                con.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (IVAremanente) {
                if (debitoD > debitoH) {
                    System.out.println("es la primera");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (38," + ultimoId + "," + formato.format(totalC) + ", 0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (39," + ultimoId + "," + formato.format(remanente) + ",0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (168," + ultimoId + ",0," + formato.format(totalD) + ");");
                } else if (debitoD < debitoH) {
                    System.out.println("es la segunda");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (168," + ultimoId + "," + formato.format(totalD) + ",0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (39," + ultimoId + "," + formato.format(remanente) + ",0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (38," + ultimoId + ",0," + formato.format(totalC) + ");");
                }

            } else if (IVAimpuesto) {
                if (debitoD > debitoH) {
                    System.out.println("es la tercera");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (38," + ultimoId + "," + formato.format(totalC) + ", 0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (146," + ultimoId + ",0," + formato.format(impuesto) + ");");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (168," + ultimoId + ",0," + formato.format(totalD) + ");");
                } else if (debitoD < debitoH) {
                    System.out.println("es la cuarta");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (168," + ultimoId + "," + formato.format(totalD) + ",0);");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (146," + ultimoId + ",0," + formato.format(impuesto) + ")");
                    insertar.Ejecutar("INSERT INTO `cuenta_partida`(`cuenta_id`, `partida_id`, `Debe`, `Haber`) VALUES (38," + ultimoId + ",0," + formato.format(totalC) + ");");
                }
            }
            CargandoPartidas(Integer.parseInt(jSpinner1.getValue().toString()));
        } else {
            JOptionPane.showMessageDialog(null, "NO HAY REGISTRO DE IVA");
        }

    }

    public void LibroMayor(int libro) {
        Double contando = 0.0;
        boolean esta = false;
        String nivel3 = "";
        String codigo = null;
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        Conexion con = new Conexion();
        ResultSet rs = null;

        //vacia la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        rs = con.Consulta("SELECT cuenta.codigo FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE partida.n_libro ='" + String.valueOf(libro) + "' ", con.getConexion());

        try {
            while (rs.next()) {
                codigo = rs.getString(1);//obteniendo codigo
                /*este for lo que hace es que solo obtenga a que cuenta de nivel 3 pertenece la cuenta obtenida
                 por la consulta de arriba, por ejemplo si es caja general sabriamos que es de efectivo y equivalente*/
                for (int i = 0; i < 4; i++) {
                    nivel3 += codigo.charAt(i);
                }
                Conexion con1 = new Conexion();
                ResultSet rs1 = null;
                rs1 = con1.Consulta("SELECT `nombre_cuenta`, `tipo_saldo` FROM `cuenta` WHERE `codigo` = '" + nivel3 + "'", con1.getConexion());

                if (rs1.next()) {

                    Conexion con2 = new Conexion();
                    ResultSet rs2 = null;
                    rs2 = con2.Consulta("SELECT cuenta.codigo,partida.fecha,partida.concepto, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE cuenta.codigo LIKE '" + nivel3 + "%' && partida.n_libro = '" + String.valueOf(libro) + "'", con2.getConexion());

                    /*SI YA HAY UNA FILA EN LA TABLA ENTRA ACA SI NO LA HAY LA AGREGA EN EL ELSE*/
                    if (modelo.getRowCount() > 0) {
                        int a = 0;
                        /*RECORREMOS LAS FILAS DE LA TABLA*/
                        while (a < modelo.getRowCount()) {
                            /*CON ESTO VERIFICAMOS QUE LA CUENTA DE NIVEL 3 QUE QUEREMOS METER NO ESTE DUPLICADA*/
                            if (jTable1.getValueAt(a, 1).toString().equals(rs1.getString("nombre_cuenta"))) {
                                esta = true;
                                break;
                            } else {
                                esta = false;
                            }
                            a++;
                        }
                        /*SI LA CUENTA QUE QUEREMOS METER YA ESTA EN LA TABLA ENTONCES NO HACEMOS NADA DE LO CONTRARIO
                         INGRESAMOS LA CUENTA*/
                        if (esta) {
                            //System.out.println("no la agrego");
                        } else {
                            modelo.addRow(new Object[]{"", rs1.getString("nombre_cuenta"), "", "", ""});
                            esta = false;
                            while (rs2.next()) {
                                /*LA VARIABLE CONTANDO SIRVE PARA IR SUMANDO EL SALDO, PERO CON ESTO VALIDAMOS QUE NO
                                 SE ESTE SUMANDO UN CAMPO VACIO*/
                                if (!jTable1.getValueAt(modelo.getRowCount() - 1, 4).toString().isEmpty()) {
                                    contando = Double.parseDouble(jTable1.getValueAt(modelo.getRowCount() - 1, 4).toString());
                                } else {
                                    contando = 0.0;
                                }

                                if (rs1.getString("tipo_saldo").equals("Deudor")) {
                                    modelo.addRow(new Object[]{rs2.getString("fecha"), rs2.getString("concepto"), rs2.getString("Debe"), rs2.getString("Haber"), formato.format(((Double.parseDouble(rs2.getString("Debe")) - Double.parseDouble(rs2.getString("Haber"))) + contando))});

                                } else if (rs1.getString("tipo_saldo").equals("Acreedor")) {
                                    modelo.addRow(new Object[]{rs2.getString("fecha"), rs2.getString("concepto"), rs2.getString("Debe"), rs2.getString("Haber"), formato.format(((Double.parseDouble(rs2.getString("Haber")) - Double.parseDouble(rs2.getString("Debe"))) + contando))});

                                }
                            }
                        }

                    } else {
                        modelo.addRow(new Object[]{"", rs1.getString("nombre_cuenta"), "", "", ""});
                        while (rs2.next()) {
                            if (!jTable1.getValueAt(modelo.getRowCount() - 1, 4).toString().isEmpty()) {
                                contando = Double.parseDouble(jTable1.getValueAt(modelo.getRowCount() - 1, 4).toString());
                            } else {
                                contando = 0.0;
                            }

                            if (rs1.getString("tipo_saldo").equals("Deudor")) {
                                modelo.addRow(new Object[]{rs2.getString("fecha"), rs2.getString("concepto"), rs2.getString("Debe"), rs2.getString("Haber"), formato.format(((Double.parseDouble(rs2.getString("Debe")) - Double.parseDouble(rs2.getString("Haber"))) + contando))});

                            } else if (rs1.getString("tipo_saldo").equals("Acreedor")) {
                                modelo.addRow(new Object[]{rs2.getString("fecha"), rs2.getString("concepto"), rs2.getString("Debe"), rs2.getString("Haber"), formato.format(((Double.parseDouble(rs2.getString("Haber")) - Double.parseDouble(rs2.getString("Debe"))) + contando))});

                            }
                        }
                    }
                    con2.close();
                    rs2.close();
                }

                //modelo.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), ""});
                con1.close();
                rs1.close();
                nivel3 = "";
            }
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BalanceComprobacion(int libro) {

        //tamaño de las columnas
        int[] anchos = {50, 200, 50, 50};

        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        boolean ya = false;
        Double sD = 0.0;
        Double sH = 0.0;
        int codigo;
        DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();

        //vacia la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Conexion con = new Conexion();
        ResultSet rs = con.Consulta("SELECT `codigo`, `nombre_cuenta`, `tipo_saldo` FROM `cuenta` WHERE `Nivel` = 1 OR `Nivel` = 2", con.getConexion());
        try {
            while (rs.next()) {
                codigo = Integer.parseInt(rs.getString("codigo"));

                if (codigo == 1 || codigo == 2 || codigo == 31 || codigo == 5 || codigo == 41 || codigo == 42) {
                    modelo.addRow(new Object[]{"", rs.getString("nombre_cuenta"), "", ""});
                    Conexion co = new Conexion();
                    //ResultSet r = co.Consulta("SELECT cuenta.codigo,cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta INNER JOIN cuenta_partida ON cuenta.id_cuenta = cuenta_partida.cuenta_id WHERE `codigo` Like '" + codigo + "%'", co.getConexion());
                    ResultSet r = co.Consulta("SELECT cuenta.codigo,cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE cuenta.codigo Like '" + codigo + "%' && partida.n_libro ='" + libro + "'", co.getConexion());

                    while (r.next()) {
                        if (modelo.getRowCount() == 1) {
                            if (rs.getString("tipo_saldo").equals("Deudor")) {
                                if (Double.parseDouble(r.getString("Haber")) <= 0.00) {
                                    modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), formato.format(Double.parseDouble(r.getString("Debe"))), formato.format(Double.parseDouble(r.getString("Haber")))});

                                } else if (Double.parseDouble(r.getString("Haber")) > 0) {
                                    modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(r.getString("Haber"))), "0"});

                                }
                            } else if (rs.getString("tipo_saldo").equals("Acreedor")) {
                                if (Double.parseDouble(r.getString("Debe")) <= 0.00) {
                                    modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), formato.format(r.getString("Debe")), formato.format(r.getString("Haber"))});

                                } else if (Double.parseDouble(r.getString("Debe")) > 0.00) {
                                    modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), "0", formato.format(0 - Double.parseDouble(r.getString("Debe")))});

                                }

                            }
                        } else if (modelo.getRowCount() > 1) {
                            //ver si hay cuentas parecidas
                            for (int g = 0; g < modelo.getRowCount(); g++) {
                                if (modelo.getValueAt(g, 0).toString().equals(r.getString("codigo"))) {
                                    //System.out.println(modelo.getValueAt(g, 0).toString() + " = " + r.getString("nombre_cuenta"));
                                    sD = Double.parseDouble(modelo.getValueAt(g, 2).toString());
                                    sH = Double.parseDouble(modelo.getValueAt(g, 3).toString());
                                    //String cuenta = modelo.getValueAt(g, 0).toString();

                                    if (rs.getString("tipo_saldo").equals("Deudor")) {
                                        if (Double.parseDouble(r.getString("Haber")) <= 0.00) {
                                            modelo.setValueAt(formato.format(sD += Double.parseDouble(r.getString("Debe"))), g, 2);
                                            //modelo.addRow(new Object[]{r.getString("nombre_cuenta"), r.getString("Debe"), r.getString("Haber")});

                                        } else if (Double.parseDouble(r.getString("Haber")) > 0) {
                                            modelo.setValueAt(formato.format(sD += 0 - Double.parseDouble(r.getString("Haber"))), g, 2);
                                            //modelo.addRow(new Object[]{r.getString("nombre_cuenta"), 0 - Double.parseDouble(r.getString("Haber")), "0"});

                                        }
                                    } else if (rs.getString("tipo_saldo").equals("Acreedor")) {
                                        if (Double.parseDouble(r.getString("Debe")) <= 0.00) {
                                            modelo.setValueAt(formato.format(sH += Double.parseDouble(r.getString("Haber"))), g, 3);
                                            //modelo.addRow(new Object[]{r.getString("nombre_cuenta"), r.getString("Debe"), r.getString("Haber")});

                                        } else if (Double.parseDouble(r.getString("Debe")) > 0.00) {
                                            modelo.setValueAt(formato.format(sH += 0 - Double.parseDouble(r.getString("Debe"))), g, 3);
                                            //modelo.addRow(new Object[]{r.getString("nombre_cuenta"), "0", 0 - Double.parseDouble(r.getString("Debe"))});

                                        }

                                    }

                                    ya = true;
                                    break;
                                } else {
                                    ya = false;
                                }
                            }

                            if (!ya) {
                                if (rs.getString("tipo_saldo").equals("Deudor")) {
                                    if (Double.parseDouble(r.getString("Haber")) <= 0.00) {
                                        modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), r.getString("Debe"), r.getString("Haber")});

                                    } else if (Double.parseDouble(r.getString("Haber")) > 0) {
                                        modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), 0 - Double.parseDouble(r.getString("Haber")), "0"});

                                    }
                                } else if (rs.getString("tipo_saldo").equals("Acreedor")) {
                                    if (Double.parseDouble(r.getString("Debe")) <= 0.00) {
                                        modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), r.getString("Debe"), r.getString("Haber")});

                                    } else if (Double.parseDouble(r.getString("Debe")) > 0.00) {
                                        modelo.addRow(new Object[]{r.getString("codigo"), r.getString("nombre_cuenta"), "0", 0 - Double.parseDouble(r.getString("Debe"))});

                                    }

                                }
                            }
                        }

                    }

                    co.close();
                    r.close();
                }

            }
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EstadoResultados(int libro) {
        Double impuestos = null;
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        Double Debe = 0.00;
        Double Haber = 0.00;
        Double ingresos = 0.0;
        Double costos = 0.0;
        Double gastos = 0.0;
        //vacia la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Conexion con = new Conexion();
        ResultSet rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE codigo LIKE '5%' && partida.n_libro = '" + libro + "' ", con.getConexion());

        //ResultSet rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta INNER JOIN cuenta_partida ON cuenta.id_cuenta = cuenta_partida.cuenta_id WHERE codigo LIKE '5%'", con.getConexion());
        try {
            while (rs.next()) {
                Debe += Double.parseDouble(rs.getString("Debe"));
                Haber += Double.parseDouble(rs.getString("Haber"));
                ingresos = Haber - Debe;
            }

            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        modelo.addRow(new Object[]{"Ingresos", formato.format(ingresos)});
        rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE codigo LIKE '41%' && partida.n_libro = '" + libro + "' ", con.getConexion());

        //rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta INNER JOIN cuenta_partida ON cuenta.id_cuenta = cuenta_partida.cuenta_id WHERE codigo LIKE '41%'", con.getConexion());
        try {
            Debe = 0.0;
            Haber = 0.0;
            while (rs.next()) {
                Debe += Double.parseDouble(rs.getString("Debe"));
                Haber += Double.parseDouble(rs.getString("Haber"));
                costos = Debe - Haber;

            }

            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelo.addRow(new Object[]{"Costos", formato.format(costos)});
        modelo.addRow(new Object[]{"Utilidad Bruta", formato.format(ingresos - costos)});

        try {
            rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE codigo LIKE '42%' && partida.n_libro = '" + libro + "' ", con.getConexion());

            //rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta INNER JOIN cuenta_partida ON cuenta.id_cuenta = cuenta_partida.cuenta_id WHERE cuenta.codigo LIKE '42%'", con.getConexion());
            Debe = 0.0;
            Haber = 0.0;
            while (rs.next()) {
                Debe += Double.parseDouble(rs.getString("Debe"));
                Haber += Double.parseDouble(rs.getString("Haber"));
                gastos = Debe - Haber;
            }

            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelo.addRow(new Object[]{"Gastos de Operacion", formato.format(gastos)});
        modelo.addRow(new Object[]{"Utilidad de Operacion", formato.format((ingresos - costos) - gastos)});
        
        
        
        modelo.addRow(new Object[]{"Reserva Legal", formato.format(((ingresos - costos) - gastos) * 0.07)});
        modelo.addRow(new Object[]{"Utilidad Antes de Impuestos", formato.format(((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07)});
        
        if (ingresos > 150000) {
            modelo.addRow(new Object[]{"Impuestos por Pagar", formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) * 0.3)});
            impuestos = Double.parseDouble(formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) * 0.3));
        } else if (ingresos < 150000) {
            modelo.addRow(new Object[]{"Impuestos por Pagar", formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) * 0.25)});
            impuestos = Double.parseDouble(formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) * 0.25));
        }

        modelo.addRow(new Object[]{"Utilidad Neta", formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) - impuestos)});
        /*utilidad neta*/
        txtBG1.setText(formato.format((((ingresos - costos) - gastos) - ((ingresos - costos) - gastos) * 0.07) - impuestos));
        /*impuestos por pagar*/
        txtBG2.setText(String.valueOf(formato.format(impuestos)));
        /*reserva legal*/
        txtBG3.setText(formato.format(((ingresos - costos) - gastos) * 0.07));
    }

    public void BalanceGeneral(int libro) {
        //tamaño de las columnas
        int[] anchos = {200, 50};

        for (int i = 0; i < jTable4.getColumnCount(); i++) {
            jTable4.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        for (int i = 0; i < jTable5.getColumnCount(); i++) {
            jTable5.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

        DefaultTableModel activos = (DefaultTableModel) jTable4.getModel();
        DefaultTableModel pasivos = (DefaultTableModel) jTable5.getModel();

        //vaciamos las tablas
        while (activos.getRowCount() > 0) {
            activos.removeRow(0);
        }
        while (pasivos.getRowCount() > 0) {
            pasivos.removeRow(0);
        }

        Conexion con = new Conexion();
        ResultSet rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE cuenta.codigo LIKE '1%' && partida.n_libro ='" + libro + "'", con.getConexion());

        Double saldo = 0.0;
        boolean ya = false;
        try {
            activos.addRow(new Object[]{"ACTIVOS", "0"});
            while (rs.next()) {
                if (activos.getRowCount() == 1) {
                    if (Double.parseDouble(rs.getString("Haber")) <= 0) {
                        activos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Debe")))});

                    } else if (Double.parseDouble(rs.getString("Haber")) > 0) {
                        activos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Haber")))});

                    }
                } else if (activos.getRowCount() > 1) {
                    //vemos si la cuenta no esta ingresada
                    for (int i = 0; i < activos.getRowCount(); i++) {
                        if (activos.getValueAt(i, 0).toString().equals(rs.getString("nombre_cuenta"))) {
                            saldo = Double.parseDouble(formato.format(Double.parseDouble(activos.getValueAt(i, 1).toString())));

                            if (Double.parseDouble(rs.getString("Haber")) <= 0) {
                                activos.setValueAt(formato.format(saldo += Double.parseDouble(rs.getString("Debe"))), i, 1);
                                //activos.addRow(new Object[]{rs.getString("nombre_cuenta"), rs.getString("Debe")});

                            } else if (Double.parseDouble(rs.getString("Haber")) > 0) {
                                activos.setValueAt(formato.format(saldo += 0 - Double.parseDouble(rs.getString("Haber"))), i, 1);

                                //activos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Haber")))});
                            }
                            ya = true;
                            break;
                        } else {
                            ya = false;
                        }
                    }

                    if (!ya) {
                        if (Double.parseDouble(rs.getString("Haber")) <= 0) {
                            activos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Debe")))});

                        } else if (Double.parseDouble(rs.getString("Haber")) > 0) {
                            activos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Haber")))});

                        }
                    }
                }
            }
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double sumActivos = 0.0;
        //sumamos los activos
        for (int i = 0; i < activos.getRowCount(); i++) {
            sumActivos += Double.parseDouble(formato.format(Double.parseDouble(activos.getValueAt(i, 1).toString())));
        }
        jTextField8.setText(formato.format(sumActivos));
        //activos.addRow(new Object[]{"TOTAL ", formato.format(sumActivos)});

        rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE cuenta.codigo LIKE '2%' && partida.n_libro ='" + libro + "'", con.getConexion());
        try {
            pasivos.addRow(new Object[]{"PASIVOS ", "0"});

            while (rs.next()) {
                if (pasivos.getRowCount() == 1) {
                    if (Double.parseDouble(rs.getString("Debe")) <= 0) {
                        pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Haber")))});
                    } else if (Double.parseDouble(rs.getString("Debe")) > 0) {
                        pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Debe")))});

                    }
                } else if (pasivos.getRowCount() > 1) {
                    //vemos si ya esta registrada
                    for (int i = 0; i < pasivos.getRowCount(); i++) {
                        if (pasivos.getValueAt(i, 0).toString().equals(rs.getString("nombre_cuenta"))) {
                            //System.out.println(pasivos.getValueAt(i, 0).toString()+ " = "+rs.getString("nombre_cuenta") );
                            saldo = Double.parseDouble(formato.format(Double.parseDouble(pasivos.getValueAt(i, 1).toString())));

                            if (Double.parseDouble(rs.getString("Debe")) <= 0) {
                                pasivos.setValueAt(formato.format(saldo += Double.parseDouble(rs.getString("Haber"))), i, 1);
                                //pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Haber")))});
                            } else if (Double.parseDouble(rs.getString("Debe")) > 0) {
                                pasivos.setValueAt(formato.format(saldo += 0 - Double.parseDouble(rs.getString("Debe"))), i, 1);
                                // pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Debe")))});

                            }

                            ya = true;
                            break;
                        } else {
                            ya = false;
                        }
                    }
                    if (!ya) {
                        if (Double.parseDouble(rs.getString("Debe")) <= 0) {
                            pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Haber")))});
                        } else if (Double.parseDouble(rs.getString("Debe")) > 0) {
                            pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Debe")))});

                        }
                    }
                }
            }
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        pasivos.addRow(new Object[]{"IMPUESTOS POR PAGAR ", txtBG2.getText()});

        pasivos.addRow(new Object[]{"PATRIMONIO ", "0"});

        rs = con.Consulta("SELECT cuenta.nombre_cuenta, cuenta_partida.Debe, cuenta_partida.Haber FROM cuenta_partida inner JOIN cuenta ON cuenta_partida.cuenta_id = cuenta.id_cuenta inner JOIN partida ON cuenta_partida.partida_id = partida.id_partida WHERE cuenta.codigo LIKE '3%' && partida.n_libro ='" + libro + "'", con.getConexion());
        try {

            while (rs.next()) {
                if (Double.parseDouble(rs.getString("Debe")) <= 0) {
                    pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(Double.parseDouble(rs.getString("Haber")))});
                } else if (Double.parseDouble(rs.getString("Debe")) > 0) {
                    pasivos.addRow(new Object[]{rs.getString("nombre_cuenta"), formato.format(0 - Double.parseDouble(rs.getString("Debe")))});

                }
            }
            con.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        pasivos.addRow(new Object[]{"UTILIDAD NETA ", formato.format(Double.parseDouble(txtBG1.getText()))});
        pasivos.addRow(new Object[]{"RESERVA LEGAL ", formato.format(Double.parseDouble(txtBG3.getText()))});

        Double sumPasivos = 0.0;
        //sumamos los pasivos
        for (int i = 0; i < pasivos.getRowCount(); i++) {
            sumPasivos += Double.parseDouble(formato.format(Double.parseDouble(pasivos.getValueAt(i, 1).toString())));
        }
        jTextField9.setText(formato.format(sumPasivos));
        //pasivos.addRow(new Object[]{"TOTAL ", formato.format(sumPasivos)});

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AbrirNav;
    private javax.swing.JLabel AbrirNav1;
    private javax.swing.JLabel AbrirNav10;
    private javax.swing.JLabel AbrirNav11;
    private javax.swing.JLabel AbrirNav12;
    private javax.swing.JLabel AbrirNav13;
    private javax.swing.JLabel AbrirNav2;
    private javax.swing.JLabel AbrirNav4;
    private javax.swing.JLabel AbrirNav6;
    private javax.swing.JLabel AbrirNav7;
    private javax.swing.JLabel AbrirNav8;
    private javax.swing.JLabel AbrirNav9;
    private javax.swing.JLabel EmpresaBG;
    private javax.swing.JPanel PanelBalanceComprobacion;
    private javax.swing.JPanel PanelBalanceGeneral;
    private javax.swing.JPanel PanelEditPartida;
    private javax.swing.JPanel PanelEncabezado;
    private javax.swing.JPanel PanelEstadoResultados;
    private javax.swing.JPanel PanelLibroDiario;
    private javax.swing.JPanel PanelLibroMayor;
    private javax.swing.JPanel PanelNav;
    private javax.swing.JTable TableMostrarPartidas;
    private javax.swing.JLabel btnActualizarBG;
    private javax.swing.JLabel btnActualizarComp;
    private javax.swing.JLabel btnActualizarEstRes;
    private javax.swing.JLabel btnActualizarMyr;
    private javax.swing.JLabel btnAgregarPartida;
    private javax.swing.JLabel btnAjuste;
    private javax.swing.JLabel btnAnterior;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnEditar;
    private javax.swing.JLabel btnElimLibro;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnMinimizar;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JButton btnReporteGeneral;
    private javax.swing.JButton btnReporteLibroDiario;
    private javax.swing.JButton btnReporteMayor;
    private javax.swing.JButton btnReporteResultado;
    private javax.swing.JLabel btnSiguiente;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblBG;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblPanelActual;
    private javax.swing.JLabel lblPeriodo;
    private javax.swing.JLabel lblPeriodo1;
    private javax.swing.JLabel lblTOTALESBG;
    private javax.swing.JTextField txtBG1;
    private javax.swing.JTextField txtBG2;
    private javax.swing.JTextField txtBG3;
    // End of variables declaration//GEN-END:variables
}
