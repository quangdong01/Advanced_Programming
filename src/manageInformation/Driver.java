package manageInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import login.loginWindow;

/**
 *
 * @author NGUYEN QUANG DONG
 */
public class Driver extends javax.swing.JFrame {

    public int userID;
    public loginWindow loginwindow;
    
    public Driver(int userID, loginWindow loginwindow) {
        this.userID = userID;
        this.loginwindow = loginwindow;
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
            
            jlNameDriver.setHorizontalAlignment(SwingConstants.CENTER);
            jlNameDriver.setVerticalAlignment(SwingConstants.CENTER);
            
            if(rsGen.next())
            {
                String Gen = rsGen.getString("Giới tính");
                String Name = rsGen.getString("Họ và tên");
                
                // hiện thị tên cho label
                if(Name == null)
                {
                    jlNameDriver.setText("Chưa xác định");
                }
                else
                {
                    jlNameDriver.setText(Name);
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
                jlNameDriver.setIcon(icon);
            }
            else if(checkGen == 2)
            {
                ImageIcon icon = new ImageIcon(getClass().getResource("Female.png"));
                jlNameDriver.setIcon(icon);
            }
            else
            {
                ImageIcon icon = new ImageIcon(getClass().getResource("No_determine.png"));
                jlNameDriver.setIcon(icon);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlNameDriver = new javax.swing.JLabel();
        jlMailbox = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlMailbox1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jlMailbox2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jlMailbox3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jlMailbox4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jlMailbox5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jlMailbox6 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jlMailbox8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jlNameDriver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jlMailbox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox.setText("Thông báo");
        jlMailbox.setToolTipText("");
        jlMailbox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlMailbox1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox1.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox1.setText("Đổi mật khẩu");
        jlMailbox1.setToolTipText("");
        jlMailbox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlMailbox2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox2.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox2.setText("Thoát đăng nhập");
        jlMailbox2.setToolTipText("");
        jlMailbox2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("HỒ SƠ TÀI XẾ");

        jlMailbox3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox3.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox3.setText("Thông tin cá nhân");
        jlMailbox3.setToolTipText("");
        jlMailbox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlMailbox4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox4.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox4.setText("Thông tin bằng lái và lương");
        jlMailbox4.setToolTipText("");
        jlMailbox4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlMailbox5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox5.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox5.setText("Cập nhật thông tin");
        jlMailbox5.setToolTipText("");
        jlMailbox5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("QUẢN LÝ ĐƠN HÀNG");

        jlMailbox6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox6.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox6.setText("Đơn hàng được giao");
        jlMailbox6.setToolTipText("");
        jlMailbox6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jlMailbox8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox8.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox8.setText("Đơn hàng đã hoàn thành");
        jlMailbox8.setToolTipText("");
        jlMailbox8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMailbox3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMailbox4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMailbox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMailbox8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1)
                            .addComponent(jlNameDriver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 18, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlMailbox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMailbox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMailbox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMailbox6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator9))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlNameDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jlMailbox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMailbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMailbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlMailbox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMailbox4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMailbox5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlMailbox6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlMailbox8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 871, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel jlMailbox;
    private javax.swing.JLabel jlMailbox1;
    private javax.swing.JLabel jlMailbox2;
    private javax.swing.JLabel jlMailbox3;
    private javax.swing.JLabel jlMailbox4;
    private javax.swing.JLabel jlMailbox5;
    private javax.swing.JLabel jlMailbox6;
    private javax.swing.JLabel jlMailbox8;
    private javax.swing.JLabel jlNameDriver;
    // End of variables declaration//GEN-END:variables
}
