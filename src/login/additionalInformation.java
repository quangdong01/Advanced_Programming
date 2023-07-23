package login;

import java.awt.Color;
import java.sql.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author NGUYEN QUANG DONG
 */

public class additionalInformation extends javax.swing.JFrame {
    
    public loginWindow loginwindow;
    public int userID;
 
    public additionalInformation(registerWindow registerwindow) {
         this.loginwindow = registerwindow.loginwindow;
         userID = registerwindow.userID;
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jbtnComplete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jtfName = new javax.swing.JTextField();
        jlWarningName = new javax.swing.JLabel();
        jtfIdentityNumber = new javax.swing.JTextField();
        jlWarningIdentityNumber = new javax.swing.JLabel();
        jtfNumberPhone = new javax.swing.JTextField();
        jlWarningPhoneNumber = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jlWarningAddress = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jlGmail = new javax.swing.JLabel();
        jlWarningEmail = new javax.swing.JLabel();
        jcbDay = new javax.swing.JComboBox<>();
        jcbMonth = new javax.swing.JComboBox<>();
        jcbYear = new javax.swing.JComboBox<>();
        jcbGen = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));

        jbtnComplete.setBackground(new java.awt.Color(51, 102, 255));
        jbtnComplete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnComplete.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComplete.setText("Tiếp theo");
        jbtnComplete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnComplete.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnCompleteMouseMoved(evt);
            }
        });
        jbtnComplete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnCompleteMouseExited(evt);
            }
        });
        jbtnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCompleteActionPerformed(evt);
            }
        });

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 35, 35));
        jLabel1.setText("Personal");

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Họ tên");
        jLabel2.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setText("Số chứng minh thư");
        jLabel4.setOpaque(true);

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");
        jLabel5.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 56, 56));
        jLabel7.setText("Contact");

        jLabel8.setBackground(new java.awt.Color(204, 204, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");
        jLabel8.setOpaque(true);

        jSeparator3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel6.setBackground(new java.awt.Color(247, 247, 247));

        jLabel11.setBackground(new java.awt.Color(247, 247, 247));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("được bảo mật và an toàn");

        jLabel10.setBackground(new java.awt.Color(247, 247, 247));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Logoright.png"))); // NOI18N

        jLabel9.setBackground(new java.awt.Color(247, 247, 247));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Thông tin cá nhân của bạn luôn");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap())
        );

        jtfName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfName.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));

        jlWarningName.setForeground(new java.awt.Color(255, 0, 51));

        jtfIdentityNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfIdentityNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));

        jlWarningIdentityNumber.setForeground(new java.awt.Color(255, 0, 0));

        jtfNumberPhone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfNumberPhone.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfNumberPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumberPhoneActionPerformed(evt);
            }
        });

        jlWarningPhoneNumber.setForeground(new java.awt.Color(255, 0, 51));

        jtfAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));

        jlWarningAddress.setForeground(new java.awt.Color(255, 0, 51));

        jtfEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));

        jLabel12.setBackground(new java.awt.Color(204, 204, 255));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel12.setText("Email");
        jLabel12.setOpaque(true);

        jlGmail.setBackground(new java.awt.Color(255, 255, 255));
        jlGmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlGmail.setText("@gmail.com");
        jlGmail.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jlGmail.setOpaque(true);

        jlWarningEmail.setForeground(new java.awt.Color(255, 0, 51));

        jcbDay.setBackground(new java.awt.Color(247, 247, 247));
        jcbDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jcbDay.setOpaque(true);
        jcbDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDayActionPerformed(evt);
            }
        });

        jcbMonth.setBackground(new java.awt.Color(247, 247, 247));
        jcbMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jcbYear.setBackground(new java.awt.Color(247, 247, 247));
        jcbYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023" }));

        jcbGen.setBackground(new java.awt.Color(247, 247, 247));
        jcbGen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbGen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jcbGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGenActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 204, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setText("Ngày tháng năm sinh");
        jLabel3.setOpaque(true);

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel6.setText("Giới tính");
        jLabel6.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSeparator3)
                                        .addGap(131, 131, 131))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 416, Short.MAX_VALUE)))
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jlWarningIdentityNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jlGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlWarningEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlWarningPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel1)
                                                .addComponent(jSeparator1)
                                                .addComponent(jtfName, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jlWarningName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jcbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(43, 43, 43)
                                                    .addComponent(jcbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(41, 41, 41)
                                                    .addComponent(jcbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jcbGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel6))))
                                    .addComponent(jtfIdentityNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(jbtnComplete))
                            .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlWarningAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jlWarningName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbMonth)
                            .addComponent(jcbYear)
                            .addComponent(jcbDay)
                            .addComponent(jcbGen))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jtfIdentityNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlWarningIdentityNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlGmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jlWarningEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jtfNumberPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jlWarningPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(1, 1, 1)
                .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlWarningAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jbtnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCompleteActionPerformed
        // TODO add your handling code here:
        
        // thực hiện kiểm tra các dữ liệu đã nhập đầy đủ chưa, nếu chưa yêu cầu điền 
        
        // ban đầu  ta xóa hết các label thành trống
        jlWarningName.setText("");
        jlWarningIdentityNumber.setText("");
        jlWarningPhoneNumber.setText("");
        jlWarningAddress.setText("");
        jlWarningEmail.setText("");
        
        String Name, IdentityNumber, NumberPhone, Address, Email;
        
        // lấy thông tin tên, số cmt, số điện thoại, địa chỉ
        Name = jtfName.getText();
        IdentityNumber = jtfIdentityNumber.getText();
        NumberPhone = jtfNumberPhone.getText();
        Address = jtfAddress.getText();
        
        //Chỉ lấy mỗi trường đầu của email
        String onlyFirstEmail = jtfEmail.getText();
        Email = jtfEmail.getText() + "@gmail.com";
        
        // lấy thông tin ngày sinh 
        String day = jcbDay.getSelectedItem().toString();
        String month  = jcbMonth.getSelectedItem().toString();
        String year  = jcbYear.getSelectedItem().toString();
        
        // chuyển đổi thành dạng "YYYY-MM-DD";
        String dayOfBirth = year + "-" + month + "-" + day;
        
        // chuyển String to Date
        
        Date dateBirth = Date.valueOf(dayOfBirth);
        
        String gender = jcbGen.getSelectedItem().toString();
        
        System.out.println(Name);
        System.out.println(IdentityNumber);
        System.out.println(NumberPhone);
        System.out.println(Address);
        System.out.println(Email);
        System.out.println(dateBirth);
        System.out.println(gender);
        
        
        
        // Xử lí ghi dữ diệu kiểm tra và ghi vào database
        if((Name.equals("")) || (IdentityNumber.equals("")) || (NumberPhone.equals("")) || (Address.equals("")) || (onlyFirstEmail.equals("")))
        {
            // kiểm tra trường Họ tên 
            if(Name.equals(""))
            {
                jlWarningName.setText("Yêu cầu nhập họ tên");
            }
            
            // kiểm tra trường số chứng minh thư
            if(IdentityNumber.equals(""))
            {
                jlWarningIdentityNumber.setText("Yêu cầu nhập chứng minh thư");
            }
            
            // kiểm tra trường Email
            if(onlyFirstEmail.equals(""))
            {
                jlWarningEmail.setText("Yêu cầu nhập Email");
            }
            
            // kiểm tra trường số điện thoại
            if(NumberPhone.equals(""))
            {
                jlWarningPhoneNumber.setText("Yêu cầu nhập số điện thoại");
            }
            
            // kiểm tra trường địa chỉ 
            if(Address.equals(""))
            {
                jlWarningAddress.setText("Yêu cầu nhập địa chỉ");
            }       
        }
        else
        {      
            try {
                String sqlSweepIDN = "Select [Số chứng minh thư] from [User]";
            
                Statement sqlQuery = this.loginwindow.db.cnt.createStatement();
                
                ResultSet rs = sqlQuery.executeQuery(sqlSweepIDN);
                
                // cờ kiểm tra duyệt chứng minh thư
                boolean checkIDN =  false;
                
                while(rs.next())
                {
                    String CMT = rs.getString("Số chứng minh thư");
                    if(IdentityNumber.equals(CMT))
                    {
                        checkIDN = true;
                        break;
                    }
                }
                if(checkIDN)
                {
                    jlWarningIdentityNumber.setText("Số chứng minh thư đã tồn tại");
                }
                else
                {
                    // câu truy vấn Email
                    String sqlSweepEmail = "Select [Email] from [User]";
                    
                    // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                    PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlSweepEmail);
                    
                    // trả về bản ghi 
                    ResultSet rsEmail = sqlUserWrite.executeQuery();
                    
                    boolean checkEmail = false;
                    
                    while(rsEmail.next())
                    {
                        String Mail = rsEmail.getString("Email");
                        if(Email.equals(Mail))
                        {
                            checkEmail = true;
                            break;
                        }
                    }
                    if(checkEmail)
                    {
                        jlWarningEmail.setText("Email đã tồn tại");
                    }
                    else
                    {
                        // câu truy vấn
                        String sqlSweepPN = "Select [Số điện thoại] from [User]";

                        // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                        sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlSweepPN);

                        // trả về bản ghi 
                        ResultSet rsPn = sqlUserWrite.executeQuery();

                        boolean checkPn = false;

                        while(rsPn.next())
                        {
                            String Pn = rsPn.getString("Số điện thoại");
                            if(NumberPhone.equals(Pn))
                            {
                                checkPn = true;
                                break;
                            }
                        }                    
                        if(checkPn)
                        {
                            jlWarningPhoneNumber.setText("Số điện thoại phải là duy nhất");
                        }
                        else
                        {

                            String sqlQueryWrite = "Update [User]"
                                    + " Set [Họ và tên] = ?, [Giới tính] = ?, [Ngày sinh] = ?, [Số chứng minh thư] = ?, [Số điện thoại] = ?, [Địa chỉ] = ?, Email = ?"
                                    + " Where User_ID = ?";

                            PreparedStatement stmt = this.loginwindow.db.cnt.prepareStatement(sqlQueryWrite);

                            stmt.setString(1, Name);
                            stmt.setString(2, gender);
                            stmt.setDate(3, dateBirth);
                            stmt.setString(4, IdentityNumber);
                            stmt.setString(5, NumberPhone);
                            stmt.setString(6, Address);
                            stmt.setString(7, Email);
                            stmt.setInt(8, this.userID);

                            // thực hiện truy vấn
                            stmt.executeUpdate();   

                            this.setEnabled(false);
                            new tickGreenComplete(this.loginwindow, this).setVisible(true);
                            }
                        }
                    }                
                } catch (SQLException ex) {
                    Logger.getLogger(additionalInformation.class.getName()).log(Level.SEVERE, null, ex);
            }       
        }   
    }//GEN-LAST:event_jbtnCompleteActionPerformed

    private void jbtnCompleteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCompleteMouseMoved
        // TODO add your handling code here:
        jbtnComplete.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_jbtnCompleteMouseMoved

    private void jbtnCompleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCompleteMouseExited
        // TODO add your handling code here:
        jbtnComplete.setBackground(new Color(51,102,255));
    }//GEN-LAST:event_jbtnCompleteMouseExited

    private void jcbDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDayActionPerformed

    private void jtfNumberPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumberPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumberPhoneActionPerformed

    private void jcbGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbGenActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JButton jbtnComplete;
    private javax.swing.JComboBox<String> jcbDay;
    private javax.swing.JComboBox<String> jcbGen;
    private javax.swing.JComboBox<String> jcbMonth;
    private javax.swing.JComboBox<String> jcbYear;
    private javax.swing.JLabel jlGmail;
    private javax.swing.JLabel jlWarningAddress;
    private javax.swing.JLabel jlWarningEmail;
    private javax.swing.JLabel jlWarningIdentityNumber;
    private javax.swing.JLabel jlWarningName;
    private javax.swing.JLabel jlWarningPhoneNumber;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfIdentityNumber;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfNumberPhone;
    // End of variables declaration//GEN-END:variables
}
