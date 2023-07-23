package manageInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import login.loginWindow;

public class User extends javax.swing.JFrame {

    public int userID;
    public loginWindow loginwindow;
    
    
    public User(int userID, loginWindow loginwindow) {
        this.loginwindow = loginwindow;
        this.userID = userID;
        initComponents();
        setLabelGen();
    }
    public void setLabelGen()
    {
        try {
            // Tạo câu lệnh truy vấn
            String sqlSweepGen = "Select [Họ và tên],[Giới tính] from [User] Where User_ID = ?";
            
            // tạo thể hiện kết nối với db để chuẩn bị truy vấn
            PreparedStatement sqlUserCheckGen = this.loginwindow.db.cnt.prepareStatement(sqlSweepGen);
            
            sqlUserCheckGen.setInt(1, this.userID);
            // trả về bản ghi
            ResultSet rsGen = sqlUserCheckGen.executeQuery();
            
            // tạo cờ check Giới tính
            int checkGen = 0;
            
            jlName.setHorizontalAlignment(SwingConstants.CENTER);
            jlName.setVerticalAlignment(SwingConstants.CENTER);
            
            if(rsGen.next())
            {
                String Gen = rsGen.getString("Giới tính");
                String Name = rsGen.getString("Họ và tên");
                
                // hiện thị tên cho label
                if(Name == null)
                {
                    jlName.setText("Chưa xác định");
                }
                else
                {
                    jlName.setText(Name);
                }
                
                // kiểm tra để đặt cờ 
                if(Gen == null)
                {
                    checkGen = 3;
                }
                else if(Gen.equals("Nam"))
                {
                    checkGen = 1;
                }
                else
                {
                    checkGen = 2;
                }
            }
        
            if(checkGen == 1)
            {
                ImageIcon icon = new ImageIcon(getClass().getResource("Male.png"));
                jlName.setIcon(icon);
            }
            else if(checkGen == 2)
            {
                ImageIcon icon = new ImageIcon(getClass().getResource("Female.png"));
                jlName.setIcon(icon);
            }
            else
            {
                ImageIcon icon = new ImageIcon(getClass().getResource("No_determine.png"));
                jlName.setIcon(icon);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jlMailBox = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlChangePassword = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jlExit = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jbInformationUser = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jbInformationUser1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jbInformationUser2 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jbInformationUser3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jbInformationUser4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jbInformationUser6 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jbInformationUser7 = new javax.swing.JLabel();
        jbInformationUser5 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logo VIN.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jlName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlName.setForeground(new java.awt.Color(202, 46, 46));

        jlMailBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailBox.setForeground(new java.awt.Color(51, 102, 255));
        jlMailBox.setText("Thông báo");
        jlMailBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlChangePassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlChangePassword.setForeground(new java.awt.Color(51, 102, 255));
        jlChangePassword.setText("Đổi mật khẩu");
        jlChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlExit.setForeground(new java.awt.Color(51, 102, 255));
        jlExit.setText("Thoát đăng nhập");
        jlExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("HỒ SƠ NGƯỜI DÙNG");

        jbInformationUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser.setText("Thông tin người dùng");
        jbInformationUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jbInformationUser1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser1.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser1.setText("Cập nhật thông tin");
        jbInformationUser1.setToolTipText("");
        jbInformationUser1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jbInformationUser2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser2.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser2.setText("Đánh giá ứng dụng");
        jbInformationUser2.setToolTipText("");
        jbInformationUser2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("QUẢN LÝ ĐƠN HÀNG");

        jbInformationUser3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser3.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser3.setText("Đặt chuyến hàng");
        jbInformationUser3.setToolTipText("");

        jbInformationUser4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser4.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser4.setText("Đơn hàng chờ xác nhận");
        jbInformationUser4.setToolTipText("");

        jbInformationUser6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser6.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser6.setText("Đơn hàng giao thành công");
        jbInformationUser6.setToolTipText("");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N

        jbInformationUser7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser7.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser7.setText("Đơn hàng đang giao");
        jbInformationUser7.setToolTipText("");

        jbInformationUser5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbInformationUser5.setForeground(new java.awt.Color(51, 102, 255));
        jbInformationUser5.setText("Đơn hàng đã hủy");
        jbInformationUser5.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jbInformationUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator9)
                            .addComponent(jSeparator8)
                            .addComponent(jSeparator7)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator1)
                            .addComponent(jlName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbInformationUser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMailBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbInformationUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbInformationUser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbInformationUser4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbInformationUser6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbInformationUser7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbInformationUser5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator11)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jlMailBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlExit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbInformationUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jbInformationUser3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbInformationUser5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(154, 154, 154)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(667, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(922, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jbInformationUser;
    private javax.swing.JLabel jbInformationUser1;
    private javax.swing.JLabel jbInformationUser2;
    private javax.swing.JLabel jbInformationUser3;
    private javax.swing.JLabel jbInformationUser4;
    private javax.swing.JLabel jbInformationUser5;
    private javax.swing.JLabel jbInformationUser6;
    private javax.swing.JLabel jbInformationUser7;
    private javax.swing.JLabel jlChangePassword;
    private javax.swing.JLabel jlExit;
    private javax.swing.JLabel jlMailBox;
    private javax.swing.JLabel jlName;
    // End of variables declaration//GEN-END:variables
}
