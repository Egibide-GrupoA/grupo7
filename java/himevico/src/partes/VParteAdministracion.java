/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partes;

import centros.Centro;
import himevico.GestorBBDD;
import vehiculo.Vehiculo;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JComboBox;
import java.sql.Time;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import trabajadores.Administracion;
import trabajadores.Logistica;
import viajes.Viaje;

/**
 *
 * @author Sheila
 */
public class VParteAdministracion extends javax.swing.JFrame {

    /**
     * Creates new form VParte
     */
    private Parte parte;
    public VParteAdministracion(Logistica logistica) throws Exception {
        initComponents();

        //Añadir vehiculos
        List<Vehiculo> vehiculos = null;
        vehiculos = GestorBBDD.listarVehiculos();
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo actual = (Vehiculo) vehiculos.get(i);
            jVehiculo.addItem(actual);
        }
        
        //Buscar parte abierto

        parte = new Parte();
        
        parte  = parte.parteAbierto(logistica);
        
        
        List<Viaje> viajes = null;
        viajes = GestorBBDD.listarViajes(parte.getIdParte());

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //añadir elementos a tabla
        for (int i = 0; i < viajes.size(); i++) {
            System.out.println(viajes.get(i).getHoraInicio());
            model.addRow(new Object[]{
                viajes.get(i), 
                viajes.get(i).getHoraInicio().getHours()+":"+((viajes.get(i).getHoraInicio().getMinutes()<10)?"0" : "")+viajes.get(i).getHoraInicio().getMinutes(), 
                viajes.get(i).getHoraFin().getHours()+":"+((viajes.get(i).getHoraFin().getMinutes()<10)?"0" : "")+viajes.get(i).getHoraFin().getMinutes(), 
                viajes.get(i).getMatricula(),
                viajes.get(i).getAlbaran()
            });

        }
        


        //Comprobar si es anterior a hoy
        
        //crear parte para hoy
        
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
        jHoraInicio2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jHoraFin1 = new javax.swing.JComboBox<>();
        JLabel4 = new javax.swing.JLabel();
        jNumeroAlbaran = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBotonAnadir = new javax.swing.JButton();
        jVehiculo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jHoraInicio1 = new javax.swing.JComboBox<>();
        jHoraFin2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Añadir viaje a parte"));

        jHoraInicio2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel4.setText(":");

        jLabel5.setText(":");

        jLabel3.setText("Hora fin: ");

        jHoraFin1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jHoraFin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHoraFin1ActionPerformed(evt);
            }
        });

        JLabel4.setText("Nº Albarán:");

        jLabel1.setText("Vehículo: ");

        jBotonAnadir.setText("Añadir");
        jBotonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonAnadirActionPerformed(evt);
            }
        });

        jVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jVehiculoActionPerformed(evt);
            }
        });

        jLabel2.setText("Hora inicio: ");

        jHoraInicio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jHoraInicio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHoraInicio1ActionPerformed(evt);
            }
        });

        jHoraFin2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jVehiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jHoraInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jHoraInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jHoraFin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jHoraFin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(JLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jNumeroAlbaran, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBotonAnadir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jHoraInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHoraInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jHoraFin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHoraFin2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(JLabel4)
                    .addComponent(jNumeroAlbaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBotonAnadir))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel6.setText("Viajes del ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Hora Inicio", "Hora Fin", "Vehiculo", "Albaran", "Eliminar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jCerrar.setText("Cerrar Parte");
        jCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jVehiculoActionPerformed
        // Botón vehículo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.getMarca();
        
    }//GEN-LAST:event_jVehiculoActionPerformed

    private void jHoraInicio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHoraInicio1ActionPerformed

    }//GEN-LAST:event_jHoraInicio1ActionPerformed

    private void jBotonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonAnadirActionPerformed
        // Botón añadir
        jBotonAnadir.setBackground(Color.GREEN);

       
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");

        Date horaInicio = null;
        try {
            horaInicio = DATE_FORMAT.parse(jHoraInicio1.getSelectedItem().toString()+":"+jHoraInicio2.getSelectedItem().toString());
        } catch (ParseException ex) {
            Logger.getLogger(VParteAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Date horaFin = null;
        try {
            horaFin = DATE_FORMAT.parse(jHoraFin1.getSelectedItem().toString()+":"+jHoraFin2.getSelectedItem().toString());
        } catch (ParseException ex) {
            Logger.getLogger(VParteAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        viajes.Viaje viaje = new Viaje(horaInicio, horaFin, (Vehiculo) jVehiculo.getSelectedItem(), jNumeroAlbaran.getText(), parte);
        

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        model.addRow(new Object[]{viaje, viaje.getHoraInicio().getHours()+":"+((viaje.getHoraInicio().getMinutes()<10)?"0" : "")+viaje.getHoraInicio().getMinutes(), viaje.getHoraFin().getHours()+":"+((viaje.getHoraFin().getMinutes()<10)?"0" : "")+viaje.getHoraFin().getMinutes(), viaje.getMatricula(), viaje.getAlbaran()});

        
    }//GEN-LAST:event_jBotonAnadirActionPerformed

    private void jHoraFin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHoraFin1ActionPerformed

    }//GEN-LAST:event_jHoraFin1ActionPerformed

    private void jCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCerrarActionPerformed
        VCerrarParte VCerrarParte = null;
        try {
            VCerrarParte = new VCerrarParte(parte, this);
        } catch (SQLException ex) {
            Logger.getLogger(VParteAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        VCerrarParte.setVisible(true);
    }//GEN-LAST:event_jCerrarActionPerformed

    
    public void deshabilitar() {
        jCerrar.setEnabled(false);
        jBotonAnadir.setEnabled(false);
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
            java.util.logging.Logger.getLogger(VParteAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VParteAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VParteAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VParteAdministracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //new VParteAdministracion().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(VParteAdministracion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel4;
    private javax.swing.JButton jBotonAnadir;
    private javax.swing.JButton jCerrar;
    private javax.swing.JComboBox<String> jHoraFin1;
    private javax.swing.JComboBox<String> jHoraFin2;
    private javax.swing.JComboBox<String> jHoraInicio1;
    private javax.swing.JComboBox<String> jHoraInicio2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jNumeroAlbaran;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<Vehiculo> jVehiculo;
    // End of variables declaration//GEN-END:variables
}
