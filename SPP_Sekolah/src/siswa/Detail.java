/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package siswa;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import connect.Connect;
import javax.swing.JPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author masag
 */
public class Detail extends javax.swing.JFrame {

    /**
     * Creates new form detail
     */
    private DefaultTableModel TblPembayaran;
    private String sql;
    public Detail() {
        initComponents();
        AppendData();
    }
    
    public void PrintPdf(String id){
        Connection conn = Connect.getConnection();
        try{
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM pembayaran AS a JOIN petugas AS b ON b.id_petugas = a.id_petugas JOIN siswa AS c ON c.nisn = a.nisn JOIN spp AS d ON d.id_spp = a.id_spp JOIN kelas AS e ON e.id_kelas = c.id_kelas WHERE a.nisn='"+id+"' ORDER BY id_pembayaran";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            int no = 1;
            
            File file = new File("detail.pdf"); // New file
            String file_name = "detail.pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            
            // Print title
            if (res.previous()) {   
                document.addTitle("PEMBAYARAN SPP");
                String nama = res.getString("nama");
                String kelas = res.getString("nama_kelas");
                String nominal = res.getString("nominal");

                Paragraph para = new Paragraph("SISWA:  "+nama+"\n");
                document.add(para);
                para = new Paragraph("KELAS:    "+kelas+"\n\n");
                document.add(para);
//                para = new Paragraph("NOMINAL:  "+nominal+"\n\n");
//                document.add(para);
            }
            // Print table //
            
            // Insert Header
            PdfPTable table = new PdfPTable(7);
            PdfPCell c1 = new PdfPCell(new Phrase("#"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Petugas"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Tanggal"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Bulan"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Tahun"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Nominal"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Jumlah Bayar"));
            table.addCell(c1);
            table.setHeaderRows(1);
            
            // Insert Data (SQL)
            while (res.next()){
                String num = String.valueOf(no);
                table.addCell(num); // #
                table.addCell(res.getString("nama_petugas")); // Petuas
                table.addCell(res.getString("tgl_bayar")); // Tanggal
                table.addCell(res.getString("bulan_dibayar")); // Bulan
                table.addCell(res.getString("tahun_dibayar")); // Tahun
                table.addCell(res.getString("nominal")); // Nominal
                table.addCell(res.getString("jumlah_bayar")); // Jumlah Bayar
                no++;
            }
            
            document.add(table); // Insert table into document, table will not display otherwise
            
            document.close();
            JOptionPane.showMessageDialog(null, "Document printed successfully as "+file_name, "Message", JOptionPane.INFORMATION_MESSAGE);
            
            Desktop desktop = Desktop.getDesktop(); // Allow access to desktop for google, mozilla..
            desktop.open(file); // Open file
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void setSession(String id) {
        jNISN.setText(id);
    }
    
    public void AppendData() {
        int no = 1;
        String id = jNISN.getText();
        TblPembayaran = new DefaultTableModel(){
            @Override // Membuat semua barisan dalam tabel tidak bisa di edit
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TblPembayaran.addColumn("#");
        TblPembayaran.addColumn("Nama Petugas");
//        TblPembayaran.addColumn("NISN");
//        TblPembayaran.addColumn("Nama Siswa");
        TblPembayaran.addColumn("Tanggal Bayar");
        TblPembayaran.addColumn("Tahun");
        TblPembayaran.addColumn("Bulan");
        TblPembayaran.addColumn("Nominal");
        TblPembayaran.addColumn("Jumlah Bayar");
        tblData.setModel(TblPembayaran);
        Connection conn = Connect.getConnection();
        try {
            java.sql.Statement stmt = conn.createStatement();
            sql = "SELECT * FROM pembayaran AS a JOIN petugas AS b ON b.id_petugas = a.id_petugas JOIN siswa AS c ON c.nisn = a.nisn JOIN spp AS d ON d.id_spp = a.id_spp WHERE a.nisn='"+id+"' ORDER BY id_pembayaran";
            java.sql.ResultSet res = stmt.executeQuery(sql);
            if (res.next()){
                String nama = res.getString("nama");
                jNamaSiswa.setText(nama);
            }
            while (res.next()){
                TblPembayaran.addRow(new Object[]{
                    res.getInt("id_pembayaran"),
                    res.getString("nama_petugas"),
//                    res.getInt("nisn"),
//                    res.getString("nama"),
                    res.getString("tgl_bayar"),
                    res.getInt("tahun_dibayar"),
                    res.getString("bulan_dibayar"),
                    res.getInt("nominal"),
                    res.getInt("jumlah_bayar"),
                });
                no++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void takePicture(JPanel panel) {
        panel.print(jScrollPane1.getGraphics());
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
        jNamaSiswa = new javax.swing.JTextField();
        bPrint = new javax.swing.JButton();
        bExit = new javax.swing.JButton();
        jRefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

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
        setTitle("Detail");

        jLabel1.setText("Menu Detail");

        jLabel2.setText("Siswa");

        jNamaSiswa.setEditable(false);
        jNamaSiswa.setToolTipText("Nama Siswa");

        bPrint.setText("Print");
        bPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintActionPerformed(evt);
            }
        });

        bExit.setText("Exit");
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        jRefresh.setText("Refresh");
        jRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshActionPerformed(evt);
            }
        });

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bExit))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2))
                            .addComponent(jNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(bPrint)
                        .addGap(6, 6, 6)
                        .addComponent(jRefresh)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
        Index abc = new Index();
        abc.setVisible(true);
        abc.setSession(jNISN.getText());
        dispose();
    }//GEN-LAST:event_bExitActionPerformed

    private void bPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintActionPerformed
        // TODO add your handling code here:
//        this.print(this.getGraphics());
        PrintPdf(jNISN.getText());
    }//GEN-LAST:event_bPrintActionPerformed
    
    private void bReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReturnActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
                dispose();
            }
        });
    }//GEN-LAST:event_bReturnActionPerformed

    private void jRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshActionPerformed
        // TODO add your handling code here:
        AppendData();
    }//GEN-LAST:event_jRefreshActionPerformed

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
//            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Detail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Detail().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bExit;
    private javax.swing.JButton bPrint;
    private javax.swing.JButton bReturn;
    private javax.swing.JTextField jId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jNISN;
    private javax.swing.JTextField jNamaSiswa;
    private javax.swing.JButton jRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    // End of variables declaration//GEN-END:variables

    void print() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
