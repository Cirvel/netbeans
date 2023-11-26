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
public class Pembayaran extends javax.swing.JFrame {

    /**
     * Creates new form pembayaran
     */
    private DefaultTableModel TblPembayaran;
    private String sql;
    public Pembayaran() {
        initComponents();
        AppendData();
        ComboSiswa();
    }
    
    public void SetPetugas(String id) {
        jIdPetugas.setText(id);
    }
    
    public void AppendData() {
        int no = 1;
        TblPembayaran = new DefaultTableModel(){
            @Override // Membuat semua barisan dalam tabel tidak bisa di edit
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TblPembayaran.addColumn("#");
        TblPembayaran.addColumn("#Petugas");
        TblPembayaran.addColumn("Nama Petugas");
        TblPembayaran.addColumn("NISN");
        TblPembayaran.addColumn("Nama Siswa");
        TblPembayaran.addColumn("Tanggal Bayar");
        TblPembayaran.addColumn("Tahun");
        TblPembayaran.addColumn("Bulan");
        TblPembayaran.addColumn("#SPP");
        TblPembayaran.addColumn("Nominal");
        TblPembayaran.addColumn("Jumlah Bayar");
        tblData.setModel(TblPembayaran);
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM pembayaran AS a JOIN petugas AS b ON b.id_petugas = a.id_petugas JOIN siswa AS c ON c.nisn = a.nisn JOIN spp AS d ON d.id_spp = a.id_spp ORDER BY id_pembayaran";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()){
                TblPembayaran.addRow(new Object[]{
                    res.getInt("id_pembayaran"),
                    res.getInt("id_petugas"),
                    res.getString("nama_petugas"),
                    res.getInt("nisn"),
                    res.getString("nama"),
                    res.getString("tgl_bayar"),
                    res.getInt("tahun_dibayar"),
                    res.getString("bulan_dibayar"),
                    res.getInt("id_spp"),
                    res.getInt("nominal"),
                    res.getInt("jumlah_bayar"),
                });
                no++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void ComboBulan(String nisn, String currentMonth) {
        // Ketika siswa sudah bayar pada suatu bulan, pilihan bulan tersebut tidak akan bisa dibayar lagi
        
        // Refresh bulan
        cBulan.removeAllItems();
        cBulan.addItem("Januari");
        cBulan.addItem("Februari");
        cBulan.addItem("Maret");
        cBulan.addItem("April");
        cBulan.addItem("Mei");
        cBulan.addItem("Juni");
        cBulan.addItem("Juli");
        cBulan.addItem("Agustus");
        cBulan.addItem("September");
        cBulan.addItem("Oktober");
        cBulan.addItem("November");
        cBulan.addItem("Desember");
        
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            // Masukan bulan yang dibayar KECUALI yang mempunyai nilai sama dengan currentMonth.
                // Ini sangat berguna ketika ingin meng edit suatu data (QOL fitur)
            sql = "SELECT * FROM pembayaran WHERE nisn = "+nisn+" AND NOT bulan_dibayar = '"+currentMonth+"'";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                String barang = res.getString("bulan_dibayar");
//                System.out.println("removed "+barang);
                cBulan.removeItem(barang);
            }
        } catch (SQLException e) {  
            System.out.println(e.getMessage());
        }
    }
    
    public void ComboSiswa() {
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM siswa JOIN spp ON spp.id_spp = siswa.id_spp ORDER BY nisn";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                String barang = res.getString("nisn");
                String nama = res.getString("nama");
                String idspp = res.getString("id_spp");
                String spp = res.getString("tahun");
                String nominal = res.getString("nominal");
                cNISN.addItem(barang);
                if (barang.equals(cNISN.getSelectedItem())) {
                    jNamaSiswa.setText(nama);
                    jIdSPP.setText(idspp);
                    jSPP.setText(spp + " | " + nominal);
                    ComboBulan(barang,"");
                }
            }
            cNISN.addActionListener((ActionEvent e) -> {
                String selectedId = (String) cNISN.getSelectedItem();
                String query = "SELECT * FROM siswa JOIN spp ON spp.id_spp = siswa.id_spp WHERE nisn = '"+selectedId+"'";
                try {
                    java.sql.Statement stmt2 = conn.createStatement();
                    java.sql.ResultSet res2 = stmt2.executeQuery(query);
                    if (res2.next()) {
                        String barang = res2.getString("nisn");
                        String nama = res2.getString("nama");
                        String idspp = res2.getString("id_spp");
                        String spp = res2.getString("tahun");
                        String nominal = res2.getString("nominal");
                        jNamaSiswa.setText(nama);
                        jIdSPP.setText(idspp);
                        jSPP.setText(spp + " | " + nominal);
                        ComboBulan(barang,"");
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

        jIdPetugas = new javax.swing.JTextField();
        jIdSPP = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        bReturn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jNamaSiswa = new javax.swing.JTextField();
        bSubmit = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cBulan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTahunBayar = new javax.swing.JTextField();
        cNISN = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jJumlahBayar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSPP = new javax.swing.JTextField();
        bClear = new javax.swing.JButton();
        jTglBayar = new com.toedter.calendar.JDateChooser();

        jIdPetugas.setEditable(false);
        jIdPetugas.setText("0");

        jIdSPP.setText("0");

        jTextField1.setText("jTextField1");

        bReturn.setText("Return");
        bReturn.setEnabled(false);
        bReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReturnActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pembayaran");
        setPreferredSize(new java.awt.Dimension(1280, 768));

        jLabel1.setText("Menu Pembayaran");

        jLabel2.setText("NISN");

        jNamaSiswa.setEditable(false);
        jNamaSiswa.setToolTipText("Nama Siswa");

        bSubmit.setText("Submit");
        bSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSubmitActionPerformed(evt);
            }
        });

        bEdit.setText("Edit");

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
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);
        if (tblData.getColumnModel().getColumnCount() > 0) {
            tblData.getColumnModel().getColumn(0).setMaxWidth(30);
            tblData.getColumnModel().getColumn(2).setPreferredWidth(45);
        }

        jLabel4.setText("Tanggal Bayar");

        jLabel5.setText("Bulan Bayar");

        cBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        cBulan.setToolTipText("Bulan yang sudah dibayar oleh siswa tidak akan ditampilkan");

        jLabel6.setText("Tahun Bayar");

        jLabel7.setText("SPP");

        jJumlahBayar.setToolTipText("Rupiah Currency");

        jLabel8.setText("Jumlah Bayar");

        jSPP.setEditable(false);
        jSPP.setToolTipText("Tahun | Nominal (Rp)");

        bClear.setText("Clear");
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        jTglBayar.setToolTipText("YYYY-MM-DD");
        jTglBayar.setDateFormatString("YYYY-MM-DD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cBulan, 0, 264, Short.MAX_VALUE)
                                    .addComponent(jJumlahBayar)
                                    .addComponent(jSPP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cNISN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jNamaSiswa))
                                    .addComponent(jTglBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTahunBayar, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(bDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(bSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bClear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bExit)))
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
                            .addComponent(cNISN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTglBayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTahunBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jSPP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jJumlahBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSubmit)
                            .addComponent(bEdit)
                            .addComponent(bClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDelete)
                        .addGap(0, 172, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
        Index abc = new Index();
        abc.setVisible(true);
        abc.setSession(jIdPetugas.getText());
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSubmitActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = Connect.getConnection();
            java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO pembayaran(id_petugas, nisn, tgl_bayar, tahun_bayar, bulan_bayar, id_spp, jumlah_bayar) values(?,?,?,?,?,?,?)");
            stmt.setString(1, jIdPetugas.getText());
            stmt.setString(2, cNISN.getSelectedItem().toString());
            stmt.setString(3, jTglBayar.getDateFormatString());
            stmt.setString(4, jTahunBayar.getText());
            stmt.setString(5, cBulan.getSelectedItem().toString());
            stmt.setString(6, jIdSPP.getText());
            stmt.setString(7, jJumlahBayar.getText());
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
                cNISN.setSelectedItem("");
                jNamaSiswa.setText("");
                jTglBayar.setDateFormatString("");
                jTahunBayar.setText("");
                cBulan.setSelectedItem("");
                jJumlahBayar.setText("");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Successfuly failed to remove data" + e.getMessage(), "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bDeleteActionPerformed
    private String selectedId;
    
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
        selectedId = TblPembayaran.getValueAt(baris, 0).toString();
        cNISN.setSelectedItem(TblPembayaran.getValueAt(baris, 3).toString());
        jTglBayar.setDateFormatString(TblPembayaran.getValueAt(baris, 5).toString());
        jTahunBayar.setText(TblPembayaran.getValueAt(baris, 6).toString());
        jJumlahBayar.setText(TblPembayaran.getValueAt(baris, 10).toString());
        ComboBulan(TblPembayaran.getValueAt(baris, 3).toString(), TblPembayaran.getValueAt(baris, 7).toString());
        cBulan.setSelectedItem(TblPembayaran.getValueAt(baris, 7).toString());
        bSubmit.setEnabled(false);
        bSubmit.setToolTipText("Please clear the form before being able to create new data");
    }//GEN-LAST:event_tblDataMouseClicked

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        // TODO add your handling code here:
        ComboBulan(cNISN.getSelectedItem().toString(), "");
        cNISN.setSelectedItem("");
        jTglBayar.setDateFormatString("");
        jTahunBayar.setText("");
        cBulan.setSelectedItem("");
        jJumlahBayar.setText("");
        bSubmit.setEnabled(true);
        bSubmit.setToolTipText(null);
    }//GEN-LAST:event_bClearActionPerformed

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
    private javax.swing.JButton bClear;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bEdit;
    private javax.swing.JButton bExit;
    private javax.swing.JButton bReturn;
    private javax.swing.JButton bSubmit;
    private javax.swing.JComboBox<String> cBulan;
    private javax.swing.JComboBox<String> cNISN;
    private javax.swing.JTextField jIdPetugas;
    private javax.swing.JTextField jIdSPP;
    private javax.swing.JTextField jJumlahBayar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jNamaSiswa;
    private javax.swing.JTextField jSPP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTahunBayar;
    private javax.swing.JTextField jTextField1;
    private com.toedter.calendar.JDateChooser jTglBayar;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables
}
