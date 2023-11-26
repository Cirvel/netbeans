/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dashboard;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connect.Connect;

/**
 *
 * @author masag
 */
public class Siswa extends javax.swing.JFrame {

    /**
     * Creates new form siswa
     */
    private DefaultTableModel TblSiswa;
    private String sql;
    public Siswa() {
        initComponents();
        this.AppendData();
        this.ComboKelas();
        this.ComboSpp();
    }
    
    public void setSession(String id) {
        jId.setText(id);
    }

    public void AppendData() {
        int no = 1;
        TblSiswa = new DefaultTableModel(){
            @Override // Membuat semua barisan dalam tabel tidak bisa di edit
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TblSiswa.addColumn("NISN");
        TblSiswa.addColumn("NIS");
        TblSiswa.addColumn("Nama Siswa");
        TblSiswa.addColumn("#");
        TblSiswa.addColumn("Kelas");
        TblSiswa.addColumn("Alamat");
        TblSiswa.addColumn("No. Telepon");
        TblSiswa.addColumn("Id SPP");
        tblData.setModel(TblSiswa);
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM siswa join kelas on kelas.id_kelas = siswa.id_kelas";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()){
                TblSiswa.addRow(new Object[]{
                    res.getInt("nisn"),
                    res.getString("nis"),
                    res.getString("nama"),
                    res.getString("id_kelas"),
                    res.getString("nama_kelas"),
                    res.getString("alamat"),
                    res.getString("no_tlp"),
                    res.getString("id_spp"),
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void ComboKelas() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM kelas";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                String barang = res.getString("id_kelas");
                String nama = res.getString("nama_kelas");
                cKelas.addItem(barang);
                if (barang.equals(cKelas.getSelectedItem())) {
                    jNamaKelas.setText(nama);
                }
            }
            cKelas.addActionListener((ActionEvent e) -> {
                String selectedId = (String) cKelas.getSelectedItem();
                
                String query = "SELECT * FROM kelas WHERE id_kelas = '"+selectedId+"'";
                try {
                    java.sql.Statement stmt2 = conn.createStatement();
                    java.sql.ResultSet res2 = stmt2.executeQuery(query);
                    if (res2.next()) {
                        String nama = res2.getString("nama_kelas");                        
                        jNamaKelas.setText(nama);
                    }
                }
                catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void ComboSpp() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM spp";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                String barang = res.getString("id_spp");
                String nama = res.getString("tahun");
//                String nominal = res.getString("nominal");
                cSpp.addItem(barang);
                if (barang.equals(cKelas.getSelectedItem())) {
                    jSpp.setText(nama);
                }
            }
            cSpp.addActionListener((ActionEvent e) -> {
                String selectedId = (String) cSpp.getSelectedItem();
                
                String query = "SELECT * FROM spp WHERE id_spp = '"+selectedId+"'";
                try {
                    java.sql.Statement stmt2 = conn.createStatement();
                    java.sql.ResultSet res2 = stmt2.executeQuery(query);
                    if (res2.next()) {
                        String nama = res2.getString("tahun");                        
                        jSpp.setText(nama);
                    }
                }
                catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

        jNISN = new javax.swing.JTextField();
        bReturn = new javax.swing.JButton();
        jId = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jNamaSiswa = new javax.swing.JTextField();
        jNIS = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jAlamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cKelas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jNoTlp = new javax.swing.JTextField();
        jNamaKelas = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cSpp = new javax.swing.JComboBox<>();
        jSpp = new javax.swing.JTextField();

        jNISN.setText("jTextField1");

        bReturn.setText("Return");
        bReturn.setEnabled(false);
        bReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReturnActionPerformed(evt);
            }
        });

        jId.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Siswa");

        jLabel1.setText("Menu Siswa");

        jLabel2.setText("NIS");

        jLabel3.setText("Nama Siswa");

        jNIS.setToolTipText("MaxLength: 8");
        jNIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNISActionPerformed(evt);
            }
        });

        bSubmit.setText("Submit");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
            }
        });

        bEdit.setText("Edit");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "NIS", "Nama Siswa", "Kelas", "Alamat", "No. Telepon"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblData.setRowSelectionAllowed(false);
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);
        if (tblData.getColumnModel().getColumnCount() > 0) {
            tblData.getColumnModel().getColumn(0).setMaxWidth(0);
            tblData.getColumnModel().getColumn(2).setPreferredWidth(45);
        }

        jLabel4.setText("Kelas");

        jLabel5.setText("Alamat");

        jLabel6.setText("No. Telepon");

        jNoTlp.setToolTipText("MaxLength: 13");

        jNamaKelas.setEditable(false);

        jLabel7.setText("SPP");

        jSpp.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jNoTlp)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jNamaKelas))
                                    .addComponent(jNamaSiswa)
                                    .addComponent(jNIS)
                                    .addComponent(jAlamat)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bExit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNamaKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jNoTlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cSpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSubmit)
                            .addComponent(bEdit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDelete)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
        Index abc = new Index();
        abc.setVisible(true);
        abc.setSession(jId.getText());
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Connect.getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO siswa(nis, nama, id_kelas, alamat, no_tlp, id_spp) values(?,?,?,?,?,?)");
            stmt.setString(1, jNIS.getText());
            stmt.setString(2, jNamaSiswa.getText());
            stmt.setString(3, (String) cKelas.getSelectedItem());
            stmt.setString(4, jAlamat.getText());
            stmt.setString(5, jNoTlp.getText());
            stmt.setString(6, (String) cSpp.getSelectedItem());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Success", "Message", JOptionPane.INFORMATION_MESSAGE);
            AppendData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_bSubmitActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        Connection conn = Connect.getConnection();
        int confirm = JOptionPane.showConfirmDialog(null, "Confirm deletion?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == 0) {
            try {
                java.sql.PreparedStatement stmt = conn.prepareStatement("delete from siswa where nisn ='" + selectedId + "'");
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Removal success", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                AppendData();
                jNIS.setText("");
                jNamaSiswa.setText("");
                cKelas.setSelectedItem("");
                jNamaKelas.setText("");
                jAlamat.setText("");
                jNoTlp.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Successfuly failed to remove data" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed
    private String selectedId;

    private void jNISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNISActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNISActionPerformed

    private void bReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReturnActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
                dispose();
            }
        });
    }//GEN-LAST:event_bReturnActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        // TODO add your handling code here:
        int baris = tblData.getSelectedRow();
        selectedId = TblSiswa.getValueAt(baris, 0).toString();
        jNIS.setText(TblSiswa.getValueAt(baris, 1).toString());
        jNamaSiswa.setText(TblSiswa.getValueAt(baris, 2).toString());
        cKelas.setSelectedItem(TblSiswa.getValueAt(baris, 3).toString());
        jAlamat.setText(TblSiswa.getValueAt(baris, 5).toString());
        jNoTlp.setText(TblSiswa.getValueAt(baris, 6).toString());
    }//GEN-LAST:event_tblDataMouseClicked

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Connect.getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement("update siswa set nis=?, nama=?, id_kelas=?,alamat=?, no_tlp=? where nisn=?");
            stmt.setString(1, jNIS.getText());
            stmt.setString(2, jNamaSiswa.getText());
            stmt.setString(3, (String) cKelas.getSelectedItem());
            stmt.setString(4, jAlamat.getText());
            stmt.setString(5, jNoTlp.getText());
            stmt.setString(6, selectedId);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Edit success", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            AppendData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_bEditActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Kelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Kelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Kelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Kelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bReturn;
    private javax.swing.JButton bSubmit;
    private javax.swing.JComboBox<String> cKelas;
    private javax.swing.JComboBox<String> cSpp;
    private javax.swing.JTextField jAlamat;
    private javax.swing.JTextField jId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jNIS;
    private javax.swing.JTextField jNISN;
    private javax.swing.JTextField jNamaKelas;
    private javax.swing.JTextField jNamaSiswa;
    private javax.swing.JTextField jNoTlp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSpp;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
