package login;

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NGUYEN QUANG DONG
 */


public class registerWindow extends javax.swing.JFrame {
    loginWindow loginwindow;
    int userID;
    public registerWindow(loginWindow loginwindow) {
        this.loginwindow = loginwindow;
        this.userID = 0;
        initComponents();
        
        // set placeholder cho mật khẩu và xác thực mật khẩu
        jpwfCreatePassword.setEchoChar((char)0);
        jpwfIdentityCreatePassword.setEchoChar((char)0);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jtfCreateAccount = new javax.swing.JTextField();
        jlWarningAccount = new javax.swing.JLabel();
        jlWarningPassword = new javax.swing.JLabel();
        jlWarningidentityPassword = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jbtnContinue = new javax.swing.JButton();
        jlExistAccount = new javax.swing.JLabel();
        jpwfCreatePassword = new javax.swing.JPasswordField();
        jpwfIdentityCreatePassword = new javax.swing.JPasswordField();
        jcbAgree = new javax.swing.JCheckBox();
        jlbCheckAgree = new javax.swing.JLabel();
        jlTerm = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1025, 824));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtfCreateAccount.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCreateAccount.setForeground(new java.awt.Color(153, 153, 153));
        jtfCreateAccount.setText("tài khoản");
        jtfCreateAccount.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfCreateAccount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCreateAccountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCreateAccountFocusLost(evt);
            }
        });
        jPanel1.add(jtfCreateAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 235, 405, 42));

        jlWarningAccount.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlWarningAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 283, 405, 17));

        jlWarningPassword.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlWarningPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 406, 405, 17));

        jlWarningidentityPassword.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlWarningidentityPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 524, 405, 17));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Logoright.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(636, 185, 216, 215));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Một tài khoản. Hoạt đông trên");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 406, 252, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("mọi dịch vụ của chúng tôi.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 437, 219, -1));

        jbtnContinue.setBackground(new java.awt.Color(51, 102, 255));
        jbtnContinue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnContinue.setForeground(new java.awt.Color(255, 255, 255));
        jbtnContinue.setText("Tiếp theo");
        jbtnContinue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnContinue.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnContinueMouseMoved(evt);
            }
        });
        jbtnContinue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnContinueMouseExited(evt);
            }
        });
        jbtnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnContinueActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 646, -1, 40));

        jlExistAccount.setBackground(new java.awt.Color(247, 247, 247));
        jlExistAccount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlExistAccount.setForeground(new java.awt.Color(0, 51, 204));
        jlExistAccount.setText("  Tôi đã có tài khoản");
        jlExistAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlExistAccount.setOpaque(true);
        jlExistAccount.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlExistAccountMouseMoved(evt);
            }
        });
        jlExistAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlExistAccountMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlExistAccountMouseExited(evt);
            }
        });
        jPanel1.add(jlExistAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 645, 179, 40));

        jpwfCreatePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfCreatePassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfCreatePassword.setText("mật khẩu");
        jpwfCreatePassword.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(210, 234, 243), null));
        jpwfCreatePassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfCreatePasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfCreatePasswordFocusLost(evt);
            }
        });
        jPanel1.add(jpwfCreatePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 359, 405, 41));

        jpwfIdentityCreatePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfIdentityCreatePassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfIdentityCreatePassword.setText("xác thực mật khẩu");
        jpwfIdentityCreatePassword.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(198, 228, 244), null));
        jpwfIdentityCreatePassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfIdentityCreatePasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfIdentityCreatePasswordFocusLost(evt);
            }
        });
        jPanel1.add(jpwfIdentityCreatePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 477, 405, 41));

        jcbAgree.setBackground(new java.awt.Color(247, 247, 247));
        jcbAgree.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jcbAgree.setText("Tôi đồng ý với điều khoản");
        jcbAgree.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jcbAgree.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jcbAgree, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 559, -1, -1));

        jlbCheckAgree.setForeground(new java.awt.Color(255, 51, 51));
        jPanel1.add(jlbCheckAgree, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 594, 405, 17));

        jlTerm.setBackground(new java.awt.Color(247, 247, 247));
        jlTerm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlTerm.setForeground(new java.awt.Color(0, 51, 204));
        jlTerm.setText("  Điều khoản");
        jlTerm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlTerm.setOpaque(true);
        jlTerm.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlTermMouseMoved(evt);
            }
        });
        jlTerm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlTermMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlTermMouseExited(evt);
            }
        });
        jPanel1.add(jlTerm, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 720, 111, 39));

        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(247, 247, 247), null));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 198, 405, -1));

        jLabel2.setBackground(new java.awt.Color(233, 96, 96));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(239, 77, 77));
        jLabel2.setText("TẠO TÀI KHOẢN NGƯỜI DÙNG");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 51, 51), null));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("Account");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 51, 51), null));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlExistAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExistAccountMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new loginWindow().setVisible(true);
    }//GEN-LAST:event_jlExistAccountMouseClicked

    private void jlExistAccountMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExistAccountMouseMoved
        // TODO add your handling code here:
        jlExistAccount.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jlExistAccountMouseMoved

    private void jbtnContinueMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnContinueMouseMoved
        // TODO add your handling code here:
        jbtnContinue.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_jbtnContinueMouseMoved

    private void jlExistAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExistAccountMouseExited
        // TODO add your handling code here:
        jlExistAccount.setBackground(new Color(247,247,247));
    }//GEN-LAST:event_jlExistAccountMouseExited

    private void jbtnContinueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnContinueMouseExited
        // TODO add your handling code here:
        jbtnContinue.setBackground(new Color(51, 102, 255));
    }//GEN-LAST:event_jbtnContinueMouseExited

    private void jtfCreateAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCreateAccountFocusGained
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập tài khoản chưa, nếu rồi thì tiến hành các bước tiếp
        if (jtfCreateAccount.getText().equals("tài khoản")) 
        {
            jtfCreateAccount.setText("");
            jlWarningAccount.setText("");
            jtfCreateAccount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCreateAccount.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCreateAccountFocusGained

    private void jtfCreateAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCreateAccountFocusLost
        // TODO add your handling code here:
        // kiểm tra người thao tác đã nhập tài khoản chưa, nếu chưa thì set placehoder cho nó
        if (jtfCreateAccount.getText().equals("")) 
        { 
            jtfCreateAccount.setText("tài khoản");
            jtfCreateAccount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCreateAccount.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCreateAccountFocusLost

    private void jpwfCreatePasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfCreatePasswordFocusLost
        // TODO add your handling code here:
        if (jpwfCreatePassword.getText().equals("")) {

            // thiết lập placeholder cho mật khẩu
            jpwfCreatePassword.setText("mật khẩu");
            jpwfCreatePassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfCreatePassword.setForeground(new Color(153, 153, 153));
            jpwfCreatePassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfCreatePasswordFocusLost

    private void jpwfCreatePasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfCreatePasswordFocusGained
        // TODO add your handling code here:
        if (jpwfCreatePassword.getText().equals("mật khẩu"))
        {
            jpwfCreatePassword.setText("");
            jlWarningPassword.setText("");
            jpwfCreatePassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfCreatePassword.setEchoChar('.');
            jpwfCreatePassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfCreatePasswordFocusGained

    private void jpwfIdentityCreatePasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfIdentityCreatePasswordFocusGained
        // TODO add your handling code here:
        if (jpwfIdentityCreatePassword.getText().equals("xác thực mật khẩu"))
        {
            jpwfIdentityCreatePassword.setText("");
            jlWarningidentityPassword.setText("");
            jpwfIdentityCreatePassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfIdentityCreatePassword.setEchoChar('.');
            jpwfIdentityCreatePassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfIdentityCreatePasswordFocusGained

    private void jpwfIdentityCreatePasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfIdentityCreatePasswordFocusLost
        // TODO add your handling code here:
        if (jpwfIdentityCreatePassword.getText().equals("")) 
        {

            // thiết lập placeholder cho mật khẩu
            jpwfIdentityCreatePassword.setText("xác thực mật khẩu");
            jpwfIdentityCreatePassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfIdentityCreatePassword.setForeground(new Color(153, 153, 153));
            jpwfIdentityCreatePassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfIdentityCreatePasswordFocusLost

    private void jbtnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnContinueActionPerformed
        // TODO add your handling code here:
        
        jlWarningAccount.setText("");
        jlWarningPassword.setText("");
        jlWarningidentityPassword.setText("");
        jlbCheckAgree.setText("");
        
        String account = jtfCreateAccount.getText();
        String password = jpwfCreatePassword.getText();
        String identityPassword = jpwfIdentityCreatePassword.getText();
        boolean checkAgree = jcbAgree.isSelected();
        if((account.equals("tài khoản")) || (password.equals("mật khẩu")) || (identityPassword.equals("xác thực mật khẩu")))
        {
            // Cảnh báo chưa nhập tài khoản
            if(account.equals("tài khoản"))
            {
                jlWarningAccount.setText("Nhập tài khoản");
            }
            else
            {
                jlWarningAccount.setText("");
            }
            
            // Cảnh báo chưa nhập mật khẩu 
            if(password.equals("mật khẩu"))
            {
                jlWarningPassword.setText("Nhập mật khẩu");
            }
            else
            {
                jlWarningPassword.setText("");
            }
            
            if(identityPassword.equals("xác thực mật khẩu"))
            {
                jlWarningidentityPassword.setText("Cần xác nhận lại mật khẩu");
            }
            else
            {
                jlWarningidentityPassword.setText("");
            }
        }
        else
        {
            if(!checkAgree)
            {
                jlbCheckAgree.setText("Cần đồng ý với điều khoản");
            }
            else
            {
                jlbCheckAgree.setText("");
                
                // Quét tài khoản 
                String sqlUserSweep = "Select [Tài khoản] From [User]";
                
                try 
                {
                    // khởi tại thể hiện của Statement để thực hiện truy vân với cơ sở dữ liệu
                    Statement st = this.loginwindow.db.cnt.createStatement();
                    
                    // Đọc dữ liệu từ database
                    ResultSet rsAccount = st.executeQuery(sqlUserSweep);
                    
                    // Tạo cờ để duyệt cơ sở dữ liệu 
                    boolean flagAccount = false;
                    
                    // Tạo vòng lặp duyệt db 
                    while(rsAccount.next())
                    {
                        String dbAccount = rsAccount.getString("Tài khoản");
                        if(account.equals(dbAccount))
                        {
                            flagAccount = true;
                            break;
                        }
                    }
                    if(flagAccount)
                    {
                        jlWarningAccount.setText("Tài khoản này đã tồn tại");
                    }
                    else
                    {
                        if(password.equals(identityPassword))
                        {
                            // thực hiện lưu tài khoản và mật khẩu mới vào database
                            String writeSql = "Insert Into [User]([Tài khoản], [Mật khẩu], [Ngày cấp quyền])"
                                    + " Values(?, ?, ?)";
                            
                            PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(writeSql);
                            
                            // thực hiện lấy thời gian hiện tại để cấp quyền sử dụng tài khoản
                            Timestamp datetime = new Timestamp(System.currentTimeMillis());
                            
                            sqlUserWrite.setString(1, account);
                            sqlUserWrite.setString(2, password);
                            sqlUserWrite.setTimestamp(3, datetime);
                            
                            // System.out.println("Here!");
                            sqlUserWrite.executeUpdate();
                            
                            // thực hiện lấy UserID để truy vấn cho câu tiếp theo
                            
                            // câu truy vấn mới
                            String readUserIDSql = "Select [User_ID] from [User]"
                                    + " Where [Tài khoản] = ?";
                            
                            // gán câu truy vấn mới cho thể hiện để chuẩn bị truy vấn
                            sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(readUserIDSql);
                            
                            sqlUserWrite.setString(1, account);
                            
                            // Lấy kết quả đã truy vấn 
                            ResultSet rs = sqlUserWrite.executeQuery();
                            
                            if(rs.next())
                            {
                                this.userID = rs.getInt("User_ID");
                            }
                            
                            
                            // câu truy vấn lưu dữ liệu vào bảng Customer
                            String WriteCustomer = "Insert into Customers(User_ID)"
                                    + " Values(?)";
                            
                            // chỉ định câu truy cho thể hiện
                            sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(WriteCustomer);
                            
                            // set giá trị
                            sqlUserWrite.setInt(1, this.userID);
                            
                            // thực hiện truy vấn tới database
                            sqlUserWrite.executeUpdate();

                            
                            new additionalInformation(this).setVisible(true);
                            this.dispose();
                        }
                        else
                        {
                            jlWarningidentityPassword.setText("Mật khẩu xác thực không khớp");
                        }
                    }    
                } 
                catch (SQLException ex) 
                {
                    Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }//GEN-LAST:event_jbtnContinueActionPerformed

    private void jlTermMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTermMouseMoved
        // TODO add your handling code here:
        jlTerm.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_jlTermMouseMoved

    private void jlTermMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTermMouseExited
        // TODO add your handling code here:
        jlTerm.setBackground(new Color(247,247,247));
    }//GEN-LAST:event_jlTermMouseExited

    private void jlTermMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTermMouseClicked
        
        this.setEnabled(false);
        new showTerm(this).setVisible(true);
    }//GEN-LAST:event_jlTermMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jbtnContinue;
    private javax.swing.JCheckBox jcbAgree;
    private javax.swing.JLabel jlExistAccount;
    private javax.swing.JLabel jlTerm;
    private javax.swing.JLabel jlWarningAccount;
    private javax.swing.JLabel jlWarningPassword;
    private javax.swing.JLabel jlWarningidentityPassword;
    private javax.swing.JLabel jlbCheckAgree;
    private javax.swing.JPasswordField jpwfCreatePassword;
    private javax.swing.JPasswordField jpwfIdentityCreatePassword;
    private javax.swing.JTextField jtfCreateAccount;
    // End of variables declaration//GEN-END:variables
}
