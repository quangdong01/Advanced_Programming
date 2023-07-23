package login;

import hust.ConnectDB.*;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import manageInformation.User;
import manageInformation.Driver;
import manageInformation.Manager;

public class loginWindow extends javax.swing.JFrame {

    // các thuộc tính của lớp
    protected Random rand = new Random();
    public ConnectDb db = new ConnectDb();
    
    
    // Định nghĩa các phương thức
    public loginWindow() {
        initComponents();
        // Tạo thể hiện của ConnectDb để thực hiện kết nối Netbean với SQL Server
        db.connectToDatabase("DESKTOP-FK8GTVI\\SQLEXPRESS", "qd", "QuangDong@21122001", "Vehicle and Driver Management", 1433);
        jlRandomNumber.setText(String.valueOf(10000 + rand.nextInt(99999)));
        jpwfPassword.setEchoChar((char)0);
        // jbtnRegister.setContentAreaFilled(false);
        // jbtnLogin.setContentAreaFilled(false); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngRole = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jlLogoHust = new javax.swing.JLabel();
        jlLogoVinFast = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jl1 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlLogoLogin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jlPromptLoginOption = new javax.swing.JLabel();
        jrbtnManager = new javax.swing.JRadioButton();
        jrbtnShiper = new javax.swing.JRadioButton();
        jrbtnUser = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jlRandomNumber = new javax.swing.JLabel();
        jbtnChangeRandomNumber = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jtfIdentifyCode = new javax.swing.JTextField();
        jbtnRegister = new javax.swing.JButton();
        jlbWarningAccount = new javax.swing.JLabel();
        jlbWarningPassword = new javax.swing.JLabel();
        jlWarningIdentityCode = new javax.swing.JLabel();
        jbtnLogin = new javax.swing.JButton();
        jpwfPassword = new javax.swing.JPasswordField();
        jlWarningLogin = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jtfAccount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trung tâm quản lý giám sát vận tải ");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1025, 824));

        jlLogoHust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/HustLogo.png"))); // NOI18N

        jlLogoVinFast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logo VIN.png"))); // NOI18N
        jlLogoVinFast.setText("jLabel2");

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(247, 247, 247), null, null));
        jSeparator2.setDoubleBuffered(true);
        jSeparator2.setOpaque(true);

        jl1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jl1.setText("CỔNG THÔNG TIN DỊCH VỤ VÀ QUẢN LÝ");
        jl1.setPreferredSize(new java.awt.Dimension(273, 25));

        jl2.setFont(new java.awt.Font("Segoe UI", 0, 27)); // NOI18N
        jl2.setText("TRUNG TÂM GIÁM SÁT THÔNG TIN VẬN TẢI VINFAST");
        jl2.setPreferredSize(new java.awt.Dimension(273, 25));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));

        jlLogoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Đăng nhập.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(225, 41, 78));
        jLabel1.setText("ĐĂNG NHẬP");

        jlPromptLoginOption.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlPromptLoginOption.setText("Vui lòng chọn vai trò đăng nhập:");

        jrbtnManager.setBackground(new java.awt.Color(255, 255, 255));
        btngRole.add(jrbtnManager);
        jrbtnManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnManager.setSelected(true);
        jrbtnManager.setText("Người quản lý");
        jrbtnManager.setBorder(null);
        jrbtnManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbtnManager.setOpaque(true);

        jrbtnShiper.setBackground(new java.awt.Color(255, 255, 255));
        btngRole.add(jrbtnShiper);
        jrbtnShiper.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnShiper.setText("Tài xế");
        jrbtnShiper.setBorder(null);
        jrbtnShiper.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jrbtnUser.setBackground(new java.awt.Color(255, 255, 255));
        btngRole.add(jrbtnUser);
        jrbtnUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jrbtnUser.setText("Người dùng");
        jrbtnUser.setBorder(null);
        jrbtnUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSeparator3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));

        jlRandomNumber.setFont(new java.awt.Font("Segoe UI Emoji", 2, 30)); // NOI18N

        jbtnChangeRandomNumber.setBackground(new java.awt.Color(246, 246, 246));
        jbtnChangeRandomNumber.setForeground(new java.awt.Color(246, 246, 246));
        jbtnChangeRandomNumber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/reload.png"))); // NOI18N
        jbtnChangeRandomNumber.setBorder(null);
        jbtnChangeRandomNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnChangeRandomNumber.setOpaque(true);
        jbtnChangeRandomNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChangeRandomNumberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlRandomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnChangeRandomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jbtnChangeRandomNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jlRandomNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Điền mã xác thực");

        jtfIdentifyCode.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfIdentifyCode.setForeground(new java.awt.Color(153, 153, 153));
        jtfIdentifyCode.setText("mã xác thực");
        jtfIdentifyCode.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));
        jtfIdentifyCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfIdentifyCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfIdentifyCodeFocusLost(evt);
            }
        });

        jbtnRegister.setBackground(new java.awt.Color(0, 51, 255));
        jbtnRegister.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnRegister.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRegister.setText("Tạo tài khoản ");
        jbtnRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnRegister.setMaximumSize(new java.awt.Dimension(151, 36));
        jbtnRegister.setMinimumSize(new java.awt.Dimension(151, 36));
        jbtnRegister.setPreferredSize(new java.awt.Dimension(121, 29));
        jbtnRegister.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnRegisterMouseMoved(evt);
            }
        });
        jbtnRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnRegisterMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnRegisterMousePressed(evt);
            }
        });
        jbtnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegisterActionPerformed(evt);
            }
        });

        jlbWarningAccount.setForeground(new java.awt.Color(255, 0, 102));

        jlbWarningPassword.setForeground(new java.awt.Color(255, 0, 102));

        jlWarningIdentityCode.setForeground(new java.awt.Color(255, 0, 102));

        jbtnLogin.setBackground(new java.awt.Color(0, 0, 255));
        jbtnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        jbtnLogin.setText("Đăng nhập");
        jbtnLogin.setToolTipText("");
        jbtnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnLogin.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnLoginMouseMoved(evt);
            }
        });
        jbtnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnLoginMousePressed(evt);
            }
        });
        jbtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLoginActionPerformed(evt);
            }
        });

        jpwfPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfPassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfPassword.setText("mật khẩu");
        jpwfPassword.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));
        jpwfPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfPasswordFocusLost(evt);
            }
        });

        jlWarningLogin.setForeground(new java.awt.Color(255, 0, 102));

        jSeparator4.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));

        jtfAccount.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfAccount.setForeground(new java.awt.Color(153, 153, 153));
        jtfAccount.setText("tài khoản");
        jtfAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfAccountFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlLogoLogin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlPromptLoginOption, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jrbtnManager)
                                    .addComponent(jrbtnUser)
                                    .addComponent(jrbtnShiper)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jlbWarningAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                                    .addComponent(jlbWarningPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jlWarningIdentityCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jbtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(136, 136, 136)
                                        .addComponent(jbtnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jtfIdentifyCode, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jpwfPassword)
                                    .addComponent(jlWarningLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jtfAccount))))
                        .addGap(0, 206, Short.MAX_VALUE))
                    .addComponent(jSeparator3))
                .addContainerGap())
            .addComponent(jSeparator4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlLogoLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlPromptLoginOption, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnManager)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnShiper)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrbtnUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbWarningAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpwfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbWarningPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jtfIdentifyCode, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlWarningIdentityCode, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jlWarningLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setText("Copyright@Hanoi University of Science and Technology");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jlLogoHust))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jlLogoVinFast, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jl2, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addComponent(jl1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(82, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlLogoHust)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlLogoVinFast, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jl2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jpwfPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfPassword.getText().equals("")) {

            // thiết lập placeholder cho mật khẩu
            jpwfPassword.setText("mật khẩu");
            jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfPassword.setForeground(new Color(153, 153, 153));
            jpwfPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfPasswordFocusLost

    private void jpwfPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfPassword.getText().equals("mật khẩu")) {
            jpwfPassword.setText("");
            jpwfPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfPassword.setEchoChar('.');
            jpwfPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfPasswordFocusGained

    private void jbtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLoginActionPerformed
        // TODO add your handling code here:
        // Check username password
        // Lấy các thông tin đăng nhập và mã xác thực người nhận nhập

        // Xóa các cảnh báo
        jlWarningLogin.setText("");
        jlbWarningAccount.setText("");
        jlbWarningPassword.setText("");
        jlWarningIdentityCode.setText("");

        // Lấy thông tin vai trò đăng nhập
        String role = "";
        if(jrbtnManager.isSelected())
        {
            role += "Manager";
        }
        else if(jrbtnShiper.isSelected())
        {
            role += "Shiper";
        }
        else
        {
            role += "User";
        }

        // Lấy tài khoản từ người nhập vào bàn phím
        String user_name = jtfAccount.getText();

        // lấy mật khẩu từ người nhập vào bàn phím
        String password = jpwfPassword.getText();

        // Lấy mã xác thực từ người nhập vào bàn phím
        String identityCode = jtfIdentifyCode.getText();

        // Kiểm tra các thông số đã nhập đủ hay chưa
        if((user_name.equals("tài khoản")) || (password.equals("mật khẩu")) || (identityCode.equals("mã xác thực")))
        {

            // Kiểm tra phần tài khoản có bỏ trống hay không
            if((user_name.equals("tài khoản")))
            {
                // hiển hị thông báo đỏ yêu cầu nhập tài khoản
                jlbWarningAccount.setText("Nhập tài khoản");
            }
            else
            {
                // xóa cảnh báo đỏ
                jlbWarningAccount.setText("");
            }

            // Kiểm tra phần tài khoản có bỏ trống hay không
            if((password.equals("mật khẩu")))
            {
                // hiển hị thông báo đỏ yêu cầu nhập mật khẩu
                jlbWarningPassword.setText("Nhập mật khẩu");
            }
            else
            {
                // xóa cảnh báo đỏ
                jlbWarningPassword.setText("");
            }

            // Kiểm tra phần mã xác thực có bỏ trống hay không
            if((identityCode.equals("mã xác thực")))
            {
                // hiển hị thông báo đỏ yêu cầu nhập mã xác thực
                jlWarningIdentityCode.setText("Yêu cầu nhập mã xác thực");
            }
            else
            {
                // xóa cảnh báo đỏ
                jlWarningIdentityCode.setText("");
            }
        }
        else
        {
            // xóa cảnh báo đỏ
            jlbWarningAccount.setText("");
            // xóa cảnh báo đỏ
            jlbWarningPassword.setText("");
            // xóa cảnh báo đỏ
            jlWarningIdentityCode.setText("");

            // tiến hành kiểm tra mã xác thực trước
            if(identityCode.equals(jlRandomNumber.getText()))
            {
                if(role.equals("User"))
                {
                    try {
                        // Khởi tạo câu truy vấn
                        String sqlUser = "Select User_ID, [Tài khoản] from [User]";

                        // khởi tại thể hiện của Statement để thực hiện truy vân với cơ sở dữ liệu
                        Statement st = db.cnt.createStatement();

                        // Đọc dữ liệu từ database
                        ResultSet rsAccount = st.executeQuery(sqlUser);

                        // tạo cờ để duyệt toàn bộ cơ sở dữ liệu để kiểm tra tài khoản có tồn tại hay không
                        boolean flagAccount = false;

                        // khởi tạo biến lưu User_ID
                        int userID = -1;

                        // duyệt cơ sở dữ liệu để kiểm tra tài khoản
                        while(rsAccount.next())
                        {
                            // Kiểm tra tài khoản từng hàng, nếu khớp thì thoát vòng lặp
                            String taiKhoan = rsAccount.getString("Tài khoản");
                            if(taiKhoan.equals(user_name))
                            {
                                // đọc UserID từ database
                                userID = rsAccount.getInt("User_ID");
                                String sql = "Select [User_ID] From Customers Where [User_ID] = %d".formatted(userID);
                                
                                // khởi tạo thể hiện để truy vấn
                                Statement sqlCheckUserIDCustomer = db.cnt.createStatement();
                                
                                // lấy kết quả truy vấn
                                ResultSet rsCheckUserIDCustomer = sqlCheckUserIDCustomer.executeQuery(sql);
                                
                                while(rsCheckUserIDCustomer.next())
                                {
                                    if(userID == rsCheckUserIDCustomer.getInt("User_ID"))
                                    {
                                        flagAccount = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        
                        // duyệt cơ sở dữ liệu để kiểm tra mật khẩu
                        if(flagAccount)
                        {
                            String txt = "Select [Mật khẩu] From [User] Where [Tài khoản] = '%s'".formatted(user_name);
                            Statement sqlUserPassword = db.cnt.createStatement();
                            ResultSet rsPassword = sqlUserPassword.executeQuery(txt);

                            if(rsPassword.next())
                            {
                                String matKhau =rsPassword.getString("Mật khẩu");
                                if(matKhau.equals(password))
                                {
                                    new User(userID, this).setVisible(true);
                                    this.dispose();
                                }
                                else
                                {
                                    // hiển hị thông báo đỏ không tồn tại tài khoản
                                    jlWarningLogin.setText("Tài khoản không khợp với vai trò hoặc nhập sai mật khẩu");

                                    // Hàm thực hiện thay đổi mã xác nhận
                                    int valueChanged = 10000 + rand.nextInt(99999);
                                    while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                                        valueChanged = 10000 + rand.nextInt(99999);
                                    }
                                    jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                                    jlRandomNumber.setText(String.valueOf(valueChanged));

                                    // thiết lập placeholder cho mật khẩu
                                    jpwfPassword.setText("mật khẩu");
                                    jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                                    jpwfPassword.setForeground(new Color(153, 153, 153));
                                    jpwfPassword.setEchoChar((char)0);
                                }
                            }
                        }
                        else
                        {
                            // hiển hị thông báo đỏ không tồn tại tài khoản
                            jlWarningLogin.setText("Tài khoản không khợp với vai trò hoặc nhập sai mật khẩu");

                            // Hàm thực hiện thay đổi mã xác nhận
                            int valueChanged = 10000 + rand.nextInt(99999);
                            while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                                valueChanged = 10000 + rand.nextInt(99999);
                            }
                            jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                            jlRandomNumber.setText(String.valueOf(valueChanged));

                            // thiết lập placeholder cho mật khẩu
                            jpwfPassword.setText("mật khẩu");
                            jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jpwfPassword.setForeground(new Color(153, 153, 153));
                            jpwfPassword.setEchoChar((char)0);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(loginWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(role.equals("Shiper"))
                {
                    try {
                        // Khởi tạo câu truy vấn
                        String sqlUser = "Select User_ID, [Tài khoản] from [User]";

                        // khởi tại thể hiện của Statement để thực hiện truy vân với cơ sở dữ liệu
                        Statement st = db.cnt.createStatement();

                        // Đọc dữ liệu từ database
                        ResultSet rsAccount = st.executeQuery(sqlUser);

                        // tạo cờ để duyệt toàn bộ cơ sở dữ liệu để kiểm tra tài khoản có tồn tại hay không
                        boolean flagAccount = false;

                        // khởi tạo biến lưu User_ID
                        int userID = -1;

                        // duyệt cơ sở dữ liệu để kiểm tra tài khoản
                        while(rsAccount.next())
                        {
                            // Kiểm tra tài khoản từng hàng, nếu khớp thì thoát vòng lặp
                            String taiKhoan = rsAccount.getString("Tài khoản");
                            if(taiKhoan.equals(user_name))
                            {
                                userID = rsAccount.getInt("User_ID");
                                String sql = "Select [User_ID] From Drivers Where [User_ID] = %d".formatted(userID);
                                Statement sqlCheckUserIDCustomer = db.cnt.createStatement();
                                ResultSet rsCheckUserIDCustomer = sqlCheckUserIDCustomer.executeQuery(sql);
                                while(rsCheckUserIDCustomer.next())
                                {
                                    if(userID == rsCheckUserIDCustomer.getInt("User_ID"))
                                    {
                                        flagAccount = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        // duyệt cơ sở dữ liệu để kiểm tra mật khẩu
                        if(flagAccount)
                        {
                            String txt = "Select [Mật khẩu] From [User] Where [Tài khoản] = '%s'".formatted(user_name);
                            Statement sqlUserPassword = db.cnt.createStatement();
                            ResultSet rsPassword = sqlUserPassword.executeQuery(txt);

                            if(rsPassword.next())
                            {
                                String matKhau =rsPassword.getString("Mật khẩu");
                                if(matKhau.equals(password))
                                {
                                    new Driver(userID, this).setVisible(true);
                                    this.dispose();
                                }
                                else
                                {
                                    // hiển hị thông báo đỏ không tồn tại tài khoản
                                    jlWarningLogin.setText("Tài khoản không khợp với vai trò hoặc nhập sai mật khẩu");

                                    // Hàm thực hiện thay đổi mã xác nhận
                                    int valueChanged = 10000 + rand.nextInt(99999);
                                    while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                                        valueChanged = 10000 + rand.nextInt(99999);
                                    }
                                    jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                                    jlRandomNumber.setText(String.valueOf(valueChanged));

                                    // thiết lập placeholder cho mật khẩu
                                    jpwfPassword.setText("mật khẩu");
                                    jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                                    jpwfPassword.setForeground(new Color(153, 153, 153));
                                    jpwfPassword.setEchoChar((char)0);
                                }
                            }
                        }
                        else
                        {
                            // hiển hị thông báo đỏ không tồn tại tài khoản
                            jlWarningLogin.setText("Tài khoản không khợp với vai trò hoặc nhập sai mật khẩu");

                            // Hàm thực hiện thay đổi mã xác nhận
                            int valueChanged = 10000 + rand.nextInt(99999);
                            while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                                valueChanged = 10000 + rand.nextInt(99999);
                            }
                            jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                            jlRandomNumber.setText(String.valueOf(valueChanged));

                            // thiết lập placeholder cho mật khẩu
                            jpwfPassword.setText("mật khẩu");
                            jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jpwfPassword.setForeground(new Color(153, 153, 153));
                            jpwfPassword.setEchoChar((char)0);
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(loginWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(role.equals("Manager"))
                {
                    if(user_name.equals("admin") && (password.equals("admin")))
                    {
                        this.dispose();
                        new Manager().setVisible(true);
                    }
                    else
                    {
                        // hiển hị thông báo đỏ không tồn tại tài khoản
                        jlWarningLogin.setText("Tài khoản không khợp với vai trò hoặc nhập sai mật khẩu");

                        // Hàm thực hiện thay đổi mã xác nhận
                        int valueChanged = 10000 + rand.nextInt(99999);
                        while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                            valueChanged = 10000 + rand.nextInt(99999);
                        }
                        jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                        jlRandomNumber.setText(String.valueOf(valueChanged));

                        // thiết lập placeholder cho mật khẩu
                        jpwfPassword.setText("mật khẩu");
                        jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jpwfPassword.setForeground(new Color(153, 153, 153));
                        jpwfPassword.setEchoChar((char)0);
                    }
                }
            }
            else
            {
                // hiển hị thông báo đỏ mã xác thực nhập bị sai
                jlWarningIdentityCode.setText("Mã xác thực không đúng");

                // Hàm thực hiện thay đổi mã xác nhận
                int valueChanged = 10000 + rand.nextInt(99999);
                while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
                    valueChanged = 10000 + rand.nextInt(99999);
                }
                jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
                jlRandomNumber.setText(String.valueOf(valueChanged));

                // thiết lập placeholder cho mật khẩu
                jpwfPassword.setText("mật khẩu");
                jpwfPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                jpwfPassword.setForeground(new Color(153, 153, 153));
                jpwfPassword.setEchoChar((char)0);
            }
        }
    }//GEN-LAST:event_jbtnLoginActionPerformed

    private void jbtnLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnLoginMousePressed
        // TODO add your handling code here:
        jbtnLogin.move(61, 577);
    }//GEN-LAST:event_jbtnLoginMousePressed

    private void jbtnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnLoginMouseExited
        // TODO add your handling code here:
        jbtnLogin.move(61, 579);
    }//GEN-LAST:event_jbtnLoginMouseExited

    private void jbtnLoginMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnLoginMouseMoved
        // TODO add your handling code here:
        jbtnLogin.move(61, 577);
    }//GEN-LAST:event_jbtnLoginMouseMoved

    private void jbtnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegisterActionPerformed
        // TODO add your handling code here:
        new registerWindow(this).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jbtnRegisterActionPerformed

    private void jbtnRegisterMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnRegisterMousePressed
        // TODO add your handling code here:
        jbtnRegister.move(327, 577);
    }//GEN-LAST:event_jbtnRegisterMousePressed

    private void jbtnRegisterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnRegisterMouseExited
        // TODO add your handling code here:
        jbtnRegister.move(327, 579);
    }//GEN-LAST:event_jbtnRegisterMouseExited

    private void jbtnRegisterMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnRegisterMouseMoved
        // TODO add your handling code here:
        jbtnRegister.move(327, 577);
        // System.out.println("x = " + jbtnRegister.getX() + " y = " + jbtnRegister.getY());
    }//GEN-LAST:event_jbtnRegisterMouseMoved

    private void jtfIdentifyCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdentifyCodeFocusLost
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập mã xác thực chưa, nếu rồi thì tiến hành các bước tiếp
        if (jtfIdentifyCode.getText().equals("")) {
            jtfIdentifyCode.setText("mã xác thực");
            jtfIdentifyCode.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfIdentifyCode.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfIdentifyCodeFocusLost

    private void jtfIdentifyCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdentifyCodeFocusGained
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập mã xác thực chưa, nếu rồi thì tiến hành các bước tiếp
        if (jtfIdentifyCode.getText().equals("mã xác thực")) {
            jtfIdentifyCode.setText("");
            jtfIdentifyCode.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfIdentifyCode.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfIdentifyCodeFocusGained

    private void jbtnChangeRandomNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChangeRandomNumberActionPerformed
        // TODO add your handling code here:
        // Hàm thực hiện thay đổi mã xác nhận
        int valueChanged = 10000 + rand.nextInt(99999);
        while (valueChanged == Integer.parseInt(jlRandomNumber.getText())) {
            valueChanged = 10000 + rand.nextInt(99999);
        }
        jlRandomNumber.setFont(new Font("Segoe UI Emoji", Font.ITALIC, 30));
        jlRandomNumber.setText(String.valueOf(valueChanged));
    }//GEN-LAST:event_jbtnChangeRandomNumberActionPerformed

    private void jtfAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAccountFocusGained
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập tài khoản chưa, nếu rồi thì tiến hành các bước tiếp
        if (jtfAccount.getText().equals("tài khoản")) {
            jtfAccount.setText("");
            jtfAccount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfAccount.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfAccountFocusGained

    private void jtfAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAccountFocusLost
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập tài khoản chưa, nếu rồi thì tiến hành các bước tiếp
        if (jtfAccount.getText().equals("")) {
            jtfAccount.setText("tài khoản");
            jtfAccount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfAccount.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfAccountFocusLost

                              
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton jbtnChangeRandomNumber;
    private javax.swing.JButton jbtnLogin;
    private javax.swing.JButton jbtnRegister;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jlLogoHust;
    private javax.swing.JLabel jlLogoLogin;
    private javax.swing.JLabel jlLogoVinFast;
    private javax.swing.JLabel jlPromptLoginOption;
    private javax.swing.JLabel jlRandomNumber;
    private javax.swing.JLabel jlWarningIdentityCode;
    private javax.swing.JLabel jlWarningLogin;
    private javax.swing.JLabel jlbWarningAccount;
    private javax.swing.JLabel jlbWarningPassword;
    private javax.swing.JPasswordField jpwfPassword;
    private javax.swing.JRadioButton jrbtnManager;
    private javax.swing.JRadioButton jrbtnShiper;
    private javax.swing.JRadioButton jrbtnUser;
    private javax.swing.JTextField jtfAccount;
    private javax.swing.JTextField jtfIdentifyCode;
    // End of variables declaration//GEN-END:variables
}
