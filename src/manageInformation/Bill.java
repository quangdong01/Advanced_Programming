/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manageInformation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Quang Dong
 */
public class Bill extends javax.swing.JFrame {
    public User user;
    public Timestamp timeBook;
    public int idTrip;
    public String startPoint;
    public String destinationPoint;
    public int distanceTrip;
    public int weightTrip;
    public int payMoney;
    
    public Bill(User user, Timestamp timeBook, int idTrip, String startPoint, String destinationPoint, int distanceTrip, int weightTrip, int payMoney) {
        initComponents();
        this.user = user;
        this.timeBook = timeBook;
        this.idTrip = idTrip;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.distanceTrip = distanceTrip;
        this.weightTrip = weightTrip;
        this.payMoney = payMoney;
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String stringtimeBook  = dateFormat.format(new Date());
        jldatetime.setText(stringtimeBook);
        jlIDTrip.setText(String.valueOf(this.idTrip));
        jlStart.setText(this.startPoint);
        jlDestination.setText(this.destinationPoint);
        jlTimeBook.setText(stringtimeBook);
        jlDistance.setText(String.valueOf(this.distanceTrip));
        jlWeight.setText(String.valueOf(this.weightTrip));
        jlPayMoney.setText(String.valueOf(this.payMoney) + "  VND");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlIDTrip = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlWeight = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlStart = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlDestination = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlTimeBook = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jlDistance = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlPayMoney = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jldatetime = new javax.swing.JLabel();
        jbtnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(229, 219, 219));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(242, 230, 230));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 43, 716, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("HÓA ĐƠN ĐẶT HÀNG");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, -1, 29));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 52, -1, 220));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("CÔNG TY VẬN TẢI");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 160, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("VINFAST");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel4.setText("Địa chỉ: ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel5.setText("Tây Mỗ, Nam Từ Liêm, Hà Nội");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel6.setText("Liên hệ:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jLabel7.setText("(03)83.899.999/(09)79.999.999");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jlIDTrip.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jPanel2.add(jlIDTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, 170, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Khối lượng:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, 20));
        jPanel2.add(jlWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 390, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Điểm xuất phát:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, 20));
        jPanel2.add(jlStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 390, 20));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Điểm đích:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, 20));
        jPanel2.add(jlDestination, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 390, 20));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Thời gian đặt:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, -1, 20));
        jPanel2.add(jlTimeBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 390, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Quãng đường:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, -1, 20));
        jPanel2.add(jlDistance, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 170, 390, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Thành tiền:");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, -1, 30));
        jPanel2.add(jlPayMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 220, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Đơn hàng:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 720, 280));
        jPanel1.add(jldatetime, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 200, 20));

        jbtnConfirm.setBackground(new java.awt.Color(51, 51, 255));
        jbtnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        jbtnConfirm.setText("Xác nhận");
        jbtnConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnConfirmActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, -1, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnConfirmActionPerformed
        // TODO add your handling code here:
        this.user.enable(true);
        this.dispose();
    }//GEN-LAST:event_jbtnConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnConfirm;
    private javax.swing.JLabel jlDestination;
    private javax.swing.JLabel jlDistance;
    private javax.swing.JLabel jlIDTrip;
    private javax.swing.JLabel jlPayMoney;
    private javax.swing.JLabel jlStart;
    private javax.swing.JLabel jlTimeBook;
    private javax.swing.JLabel jlWeight;
    private javax.swing.JLabel jldatetime;
    // End of variables declaration//GEN-END:variables
}
