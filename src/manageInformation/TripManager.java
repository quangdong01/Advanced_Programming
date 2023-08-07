/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manageInformation;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import login.loginWindow;
import login.registerWindow;

/**
 *
 * @author Quang Dong
 */
public class TripManager extends javax.swing.JFrame {
    public loginWindow loginwindow;
    public int userID;
    public int managerID;
    public String nameManager;
    public String username;
    public String password;
    
    /**
     * Creates new form TripManager
     */
    public TripManager(int userID, loginWindow loginwindow) {
        this.userID = userID;
        this.loginwindow = loginwindow;
        initComponents();     
        
        getManagerID();
        setLabelGen();
        setEchor();
        setFirstInformation();
        setSecondInformation();
    }
    
    
        public void getManagerID()
    {
        try
        {
            String GetManagerID = "Select Manager_ID from [Manager] Where User_ID = ?";

            // tạo thể hiện kết nối với db để chuẩn bị truy vấn
            PreparedStatement sqlGetManagerID = this.loginwindow.db.cnt.prepareStatement(GetManagerID);

            sqlGetManagerID.setInt(1, this.userID);
            // trả về bản ghi
            ResultSet rs = sqlGetManagerID.executeQuery();

            while(rs.next())
            {
                this.managerID = rs.getInt("Manager_ID");
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void setLabelGen()
        {
            try {
                // Tạo câu lệnh truy vấn
                String sqlSweepGen = "Select [Tài khoản], [Mật khẩu], [Họ và tên], [Giới tính] from [User] Where User_ID = ?";

                // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                PreparedStatement sqlUserCheckGen = this.loginwindow.db.cnt.prepareStatement(sqlSweepGen);

                sqlUserCheckGen.setInt(1, this.userID);
                // trả về bản ghi
                ResultSet rsGen = sqlUserCheckGen.executeQuery();

                // tạo cờ check Giới tính
                int checkGen = 0;

                jlName.setHorizontalAlignment(SwingConstants.CENTER);
                jlName.setVerticalAlignment(SwingConstants.CENTER);

                jlUserID.setHorizontalAlignment(SwingConstants.CENTER);
                jlUserID.setVerticalAlignment(SwingConstants.CENTER);

                if(rsGen.next())
                {
                    String Gen = rsGen.getString("Giới tính");
                    this.nameManager = rsGen.getString("Họ và tên");
                    this.username = rsGen.getString("Tài khoản");
                    this.password = rsGen.getString("Mật khẩu");
                    // hiện thị tên cho label
                    if(this.nameManager == null)
                    {
                        jlName.setText("Chưa xác định");
                    }
                    else
                    {
                        jlName.setText(this.nameManager);
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
                    ImageIcon icon1 = new ImageIcon(getClass().getResource("Male.png"));
                    ImageIcon icon2 = new ImageIcon(getClass().getResource("avatar_male.png"));
                    jlName.setIcon(icon1);
                    jlAvatarManager.setIcon(icon2);
                }
                else if(checkGen == 2)
                {
                    ImageIcon icon1 = new ImageIcon(getClass().getResource("Female.png"));
                    ImageIcon icon2 = new ImageIcon(getClass().getResource("avatar_female.png"));
                    jlName.setIcon(icon1);
                    jlAvatarManager.setIcon(icon2);
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


    public void setEchor()
    {
        jpwfOldPassword.setEchoChar((char)0);
        jpwfNewPassword.setEchoChar((char)0);
        jpwfConfirmPassword.setEchoChar((char)0);
    }

    public void setFirstInformation() 
    {
        try
        {
            jTabbedPaneAll.setSelectedIndex(0);

            jlUserID.setText(String.valueOf(this.managerID));

            // thực hiện lấy dữ liệu để in ra panel
            String sqlGetInfor = "Select [Họ và tên], [Giới tính], [Ngày sinh], [Email], [Số chứng minh thư], [Số điện thoại], [Địa chỉ]"
                    + " From [User] Where User_ID = ?";

            PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlGetInfor);

            sqlUserWrite.setInt(1, this.userID);

            // lấy bảng dữ liệu
            ResultSet rs = sqlUserWrite.executeQuery();

            while(rs.next())
            {
                jlFullName.setText(rs.getString("Họ và tên"));
                jlSex.setText(rs.getString("Giới tính"));
                jlIdentityNumber.setText(rs.getString("Số chứng minh thư"));
                jlPhone.setText(rs.getString("Số điện thoại"));
                jlEmail.setText(rs.getString("Email"));
                jlDayOfBirth.setText(rs.getString("Ngày sinh"));
                jlAddress.setText(rs.getString("Địa chỉ"));
            }

            // thực hiện lấy dữ liệu để in ra panel
            String sqlGetInforRole = "Select [Chức vụ] From [Manager] Where User_ID = ?";

            // gán câu truy vấn
            sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlGetInforRole);

            // gán giá trị buộc 
            sqlUserWrite.setInt(1, this.userID);

            // trả về result
            rs = sqlUserWrite.executeQuery();

            while(rs.next())
            {
                jlRole.setText(rs.getString("Chức vụ"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSecondInformation()
    {
        jlSuccess.setVisible(false);
        String setTaiKhoan = "   " + this.managerID + " - " + this.nameManager;
        jlShowName.setText(setTaiKhoan);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jlchangePasswordTripManager = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jllogOutTripManager = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jlwaitingTrip = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jldeliveryTrip = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jlcompleteTrip = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jlCancelTrip = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jlShowPersonalInfor = new javax.swing.JLabel();
        jTabbedPaneAll = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jlAvatarManager = new javax.swing.JLabel();
        jlUserID = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jlFullName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlSex = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jlDayOfBirth = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlIdentityNumber = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlPhone = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlAddress = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlRole = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlShowName = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jlnotifyOldPassword = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jlRequest = new javax.swing.JLabel();
        jpwfNewPassword = new javax.swing.JPasswordField();
        jpwfConfirmPassword = new javax.swing.JPasswordField();
        jlConfirm = new javax.swing.JLabel();
        jcbConfirm = new javax.swing.JCheckBox();
        jpwfOldPassword = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jbtnSubmit = new javax.swing.JButton();
        jlCheckOK = new javax.swing.JLabel();
        jlSuccess = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlName.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jlName.setForeground(new java.awt.Color(51, 153, 255));
        jPanel2.add(jlName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, 263, 34));

        jlchangePasswordTripManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlchangePasswordTripManager.setForeground(new java.awt.Color(0, 102, 255));
        jlchangePasswordTripManager.setText("Đổi mật khẩu");
        jlchangePasswordTripManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlchangePasswordTripManager.setOpaque(true);
        jlchangePasswordTripManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlchangePasswordTripManagerMouseMoved(evt);
            }
        });
        jlchangePasswordTripManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlchangePasswordTripManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlchangePasswordTripManagerMouseExited(evt);
            }
        });
        jPanel2.add(jlchangePasswordTripManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 130, 243, 35));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 171, 263, -1));

        jllogOutTripManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jllogOutTripManager.setForeground(new java.awt.Color(0, 102, 255));
        jllogOutTripManager.setText("Đăng xuất");
        jllogOutTripManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jllogOutTripManager.setOpaque(true);
        jllogOutTripManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jllogOutTripManagerMouseMoved(evt);
            }
        });
        jllogOutTripManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jllogOutTripManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jllogOutTripManagerMouseExited(evt);
            }
        });
        jPanel2.add(jllogOutTripManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 180, 243, 35));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 221, 263, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("QUẢN LÝ ĐƠN HÀNG");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 258, 182, 34));

        jlwaitingTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlwaitingTrip.setForeground(new java.awt.Color(0, 102, 255));
        jlwaitingTrip.setText("Đơn hàng chờ xác nhận");
        jlwaitingTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlwaitingTrip.setOpaque(true);
        jlwaitingTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlwaitingTripMouseMoved(evt);
            }
        });
        jlwaitingTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlwaitingTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlwaitingTripMouseExited(evt);
            }
        });
        jPanel2.add(jlwaitingTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 310, 243, 35));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 351, 263, -1));

        jldeliveryTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jldeliveryTrip.setForeground(new java.awt.Color(0, 102, 255));
        jldeliveryTrip.setText("Đơn hàng đang giao");
        jldeliveryTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jldeliveryTrip.setOpaque(true);
        jldeliveryTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jldeliveryTripMouseMoved(evt);
            }
        });
        jldeliveryTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jldeliveryTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jldeliveryTripMouseExited(evt);
            }
        });
        jPanel2.add(jldeliveryTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 360, 243, 35));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 401, 263, -1));

        jlcompleteTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlcompleteTrip.setForeground(new java.awt.Color(0, 102, 255));
        jlcompleteTrip.setText("Đơn hàng thành công");
        jlcompleteTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlcompleteTrip.setOpaque(true);
        jlcompleteTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlcompleteTripMouseMoved(evt);
            }
        });
        jlcompleteTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlcompleteTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlcompleteTripMouseExited(evt);
            }
        });
        jPanel2.add(jlcompleteTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 410, 243, 35));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 451, 263, -1));

        jlCancelTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCancelTrip.setForeground(new java.awt.Color(0, 102, 255));
        jlCancelTrip.setText("Đơn hàng đã hủy");
        jlCancelTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlCancelTrip.setOpaque(true);
        jlCancelTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlCancelTripMouseMoved(evt);
            }
        });
        jlCancelTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlCancelTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlCancelTripMouseExited(evt);
            }
        });
        jPanel2.add(jlCancelTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 460, 243, 35));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 501, 263, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 680, -1, -1));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, 263, -1));

        jlShowPersonalInfor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlShowPersonalInfor.setForeground(new java.awt.Color(0, 102, 255));
        jlShowPersonalInfor.setText("Thông tin cá nhân");
        jlShowPersonalInfor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlShowPersonalInfor.setOpaque(true);
        jlShowPersonalInfor.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlShowPersonalInforMouseMoved(evt);
            }
        });
        jlShowPersonalInfor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlShowPersonalInforMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlShowPersonalInforMouseExited(evt);
            }
        });
        jPanel2.add(jlShowPersonalInfor, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 80, 243, 35));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, 820));

        jTabbedPaneAll.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("THÔNG TIN CÁ NHÂN");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));
        jPanel3.add(jlAvatarManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 150));

        jlUserID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(jlUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Họ và tên  :");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, 30));

        jlFullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 180, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Giới tính  :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 80, 30));

        jlSex.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 70, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Ngày sinh  :");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 90, 30));

        jlDayOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlDayOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 140, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Email  :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 60, 30));

        jlEmail.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 250, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Số chứng minh thư  :");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 160, 30));

        jlIdentityNumber.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlIdentityNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 250, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Số điện thoại  :");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, 30));

        jlPhone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 240, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Địa chỉ  : ");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 70, 30));

        jlAddress.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 340, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Chức vụ  :");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 210, -1, 30));

        jlRole.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 280, 30));

        jTabbedPaneAll.addTab("tab1", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(183, 27, 58));
        jLabel7.setText("ĐỔI MẬT KHẨU NGƯỜI DÙNG");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 37, -1, 38));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Key.png"))); // NOI18N
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 37, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Tài khoản");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 90, -1, -1));

        jlShowName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlShowName.setOpaque(true);
        jPanel4.add(jlShowName, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 118, 830, 36));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("Mật khẩu cũ");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 166, -1, -1));

        jlnotifyOldPassword.setForeground(new java.awt.Color(255, 51, 51));
        jPanel4.add(jlnotifyOldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 250, 829, 20));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setText("Mật khẩu mới");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 280, 107, -1));

        jlRequest.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlRequest.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(jlRequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 360, 830, 20));

        jpwfNewPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfNewPassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfNewPassword.setText("Password");
        jpwfNewPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfNewPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfNewPasswordFocusLost(evt);
            }
        });
        jPanel4.add(jpwfNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 308, 829, 46));

        jpwfConfirmPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfConfirmPassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfConfirmPassword.setText("Confirm");
        jpwfConfirmPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfConfirmPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfConfirmPasswordFocusLost(evt);
            }
        });
        jPanel4.add(jpwfConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 392, 829, 46));

        jlConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlConfirm.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(jlConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 444, 830, 20));

        jcbConfirm.setBackground(new java.awt.Color(255, 255, 255));
        jcbConfirm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbConfirm.setText("Tôi đồng ý");
        jcbConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbConfirm.setOpaque(true);
        jPanel4.add(jcbConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 481, -1, -1));

        jpwfOldPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfOldPassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfOldPassword.setText("mật khẩu cũ");
        jpwfOldPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfOldPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfOldPasswordFocusLost(evt);
            }
        });
        jPanel4.add(jpwfOldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 780, 46));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Key2.png"))); // NOI18N
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jbtnSubmit.setBackground(new java.awt.Color(102, 102, 255));
        jbtnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnSubmit.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSubmit.setText("Submit");
        jbtnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSubmit.setOpaque(true);
        jbtnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, -1, 30));

        jlCheckOK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlCheckOK.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(jlCheckOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 820, 20));

        jlSuccess.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccess.setText("Thành công! Bạn đã đổi mật khẩu thành công.");
        jlSuccess.setOpaque(true);
        jPanel4.add(jlSuccess, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 830, 30));

        jTabbedPaneAll.addTab("tab2", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPaneAll.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPaneAll.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPaneAll.addTab("tab5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 779, Short.MAX_VALUE)
        );

        jTabbedPaneAll.addTab("tab6", jPanel8);

        jPanel1.add(jTabbedPaneAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, -4, 930, 810));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, 30));

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

    private void jpwfOldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfOldPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfOldPassword.getText().equals("mật khẩu cũ"))
        {
            jpwfOldPassword.setText("");
            jpwfOldPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfOldPassword.setEchoChar('.');
            jpwfOldPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfOldPasswordFocusGained

    private void jpwfOldPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfOldPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfOldPassword.getText().equals("")) 
        {

            // thiết lập placeholder cho mật khẩu
            jpwfOldPassword.setText("mật khẩu cũ");
            jpwfOldPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfOldPassword.setForeground(new Color(153, 153, 153));
            jpwfOldPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfOldPasswordFocusLost

    private void jpwfNewPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfNewPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfNewPassword.getText().equals("Password"))
        {
            jpwfNewPassword.setText("");
            jpwfNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfNewPassword.setEchoChar('.');
            jpwfNewPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfNewPasswordFocusGained

    private void jpwfNewPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfNewPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfNewPassword.getText().equals("")) {
            jpwfNewPassword.setText("Password");
            jpwfNewPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfNewPassword.setForeground(new Color(153, 153, 153));
            jpwfNewPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfNewPasswordFocusLost

    private void jpwfConfirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfConfirmPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfConfirmPassword.getText().equals("Confirm"))
        {
            jpwfConfirmPassword.setText("");
            jpwfConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfConfirmPassword.setEchoChar('.');
            jpwfConfirmPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfConfirmPasswordFocusGained

    private void jpwfConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfConfirmPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfConfirmPassword.getText().equals("")) {
            jpwfConfirmPassword.setText("Confirm");
            jpwfConfirmPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfConfirmPassword.setForeground(new Color(153, 153, 153));
            jpwfConfirmPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfConfirmPasswordFocusLost

    private void jlchangePasswordTripManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordTripManagerMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(1);
    }//GEN-LAST:event_jlchangePasswordTripManagerMouseClicked

    private void jlchangePasswordTripManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordTripManagerMouseMoved
        // TODO add your handling code here:
        jlchangePasswordTripManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlchangePasswordTripManagerMouseMoved

    private void jlchangePasswordTripManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordTripManagerMouseExited
        // TODO add your handling code here:
        jlchangePasswordTripManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlchangePasswordTripManagerMouseExited

    private void jbtnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitActionPerformed
        // TODO add your handling code here:
        // set init label
        jlSuccess.setVisible(false);
        jlnotifyOldPassword.setText("");
        jlRequest.setText("");
        jlConfirm.setText("");
        jlCheckOK.setText("");
        // get passwordfield;
        String oldPassword = jpwfOldPassword.getText();
        String newPassword = jpwfNewPassword.getText();
        String confirmPassword = jpwfConfirmPassword.getText();
        boolean checkOK = jcbConfirm.isSelected();
        if((oldPassword.equals("mật khẩu cũ")) || (newPassword.equals("Password")) || (confirmPassword.equals("Confirm")))
        {
            if(oldPassword.equals("mật khẩu cũ"))
            {
                jlnotifyOldPassword.setText("nhập mật khẩu cũ");
            }
            else
            {
                jlnotifyOldPassword.setText("");
            }
            
            if(newPassword.equals("Password"))
            {
                jlRequest.setText("nhập mật khẩu mới");
            }
            else
            {
                jlRequest.setText("");
            }
            
            if(confirmPassword.equals("Confirm"))
            {
                jlConfirm.setText("xác nhận lại mật khẩu");
            }
            else
            {
                jlConfirm.setText("");
            }          
        }
        else
        {
            if(checkOK)
            {
                if(oldPassword.equals(this.password))
                {
                    if(newPassword.length() < 8)
                    {
                        jlRequest.setText("tối thiểu 8 ký tự");
                    }
                    else
                    {
                        if(confirmPassword.equals(newPassword))
                        {
                            try
                            {
                                // thực hiện lấy dữ liệu để in ra panel
                                String sqlGetInfor = "Update [User]"
                                        + " Set [Mật khẩu] = ? Where User_ID = ?";

                                PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlGetInfor);

                                sqlUserWrite.setString(1, newPassword);
                                sqlUserWrite.setInt(2, this.userID);

                                // thực hiện truy vấn
                                sqlUserWrite.executeUpdate();
                                this.password = newPassword;
                                
                                jlSuccess.setVisible(true);
                                
                                // thiết lập placeholder cho mật khẩu cũ
                                jpwfOldPassword.setText("mật khẩu cũ");
                                jpwfOldPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                                jpwfOldPassword.setForeground(new Color(153, 153, 153));
                                jpwfOldPassword.setEchoChar((char)0);
                                
                                // thiết lập place holder cho mật khẩu mới
                                jpwfNewPassword.setText("Password");
                                jpwfNewPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                                jpwfNewPassword.setForeground(new Color(153, 153, 153));
                                jpwfNewPassword.setEchoChar((char)0);

                                // thiết lập place holder cho xác thực mật khẩu 
                                jpwfConfirmPassword.setText("Confirm");
                                jpwfConfirmPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                                jpwfConfirmPassword.setForeground(new Color(153, 153, 153));
                                jpwfConfirmPassword.setEchoChar((char)0);
                                
                            }
                            catch (SQLException ex)
                            {
                                Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else
                        {
                            jlConfirm.setText("mật khẩu không khớp");
                        }
                    }
                }
                else
                {
                    jlnotifyOldPassword.setText("mật khẩu cũ không đúng");
                }
            }
            else
            {
                jlCheckOK.setText("bạn phải xác nhận");
            }
        }
    }//GEN-LAST:event_jbtnSubmitActionPerformed

    private void jllogOutTripManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutTripManagerMouseClicked
        // TODO add your handling code here:
        this.loginwindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jllogOutTripManagerMouseClicked

    private void jllogOutTripManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutTripManagerMouseExited
        // TODO add your handling code here:
        jllogOutTripManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jllogOutTripManagerMouseExited

    private void jllogOutTripManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutTripManagerMouseMoved
        // TODO add your handling code here:
        jllogOutTripManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jllogOutTripManagerMouseMoved

    private void jlwaitingTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlwaitingTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(2);
    }//GEN-LAST:event_jlwaitingTripMouseClicked

    private void jlwaitingTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlwaitingTripMouseExited
        // TODO add your handling code here:
        jlwaitingTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlwaitingTripMouseExited

    private void jlwaitingTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlwaitingTripMouseMoved
        // TODO add your handling code here:
        jlwaitingTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlwaitingTripMouseMoved

    private void jldeliveryTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeliveryTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(3);
    }//GEN-LAST:event_jldeliveryTripMouseClicked

    private void jldeliveryTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeliveryTripMouseExited
        // TODO add your handling code here:
        jldeliveryTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jldeliveryTripMouseExited

    private void jldeliveryTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeliveryTripMouseMoved
        // TODO add your handling code here:
        jldeliveryTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jldeliveryTripMouseMoved

    private void jlcompleteTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcompleteTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(4);
    }//GEN-LAST:event_jlcompleteTripMouseClicked

    private void jlcompleteTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcompleteTripMouseExited
        // TODO add your handling code here:
        jlcompleteTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlcompleteTripMouseExited

    private void jlcompleteTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlcompleteTripMouseMoved
        // TODO add your handling code here:
        jlcompleteTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlcompleteTripMouseMoved

    private void jlCancelTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(5);
    }//GEN-LAST:event_jlCancelTripMouseClicked

    private void jlCancelTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseExited
        // TODO add your handling code here:
        jlCancelTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlCancelTripMouseExited

    private void jlCancelTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseMoved
        // TODO add your handling code here:
        jlCancelTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlCancelTripMouseMoved

    private void jlShowPersonalInforMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseMoved
        // TODO add your handling code here:
        jlShowPersonalInfor.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlShowPersonalInforMouseMoved

    private void jlShowPersonalInforMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(0);
    }//GEN-LAST:event_jlShowPersonalInforMouseClicked

    private void jlShowPersonalInforMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseExited
        // TODO add your handling code here:
        jlShowPersonalInfor.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShowPersonalInforMouseExited

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPaneAll;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarManager;
    private javax.swing.JLabel jlCancelTrip;
    private javax.swing.JLabel jlCheckOK;
    private javax.swing.JLabel jlConfirm;
    private javax.swing.JLabel jlDayOfBirth;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlFullName;
    private javax.swing.JLabel jlIdentityNumber;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlRequest;
    private javax.swing.JLabel jlRole;
    private javax.swing.JLabel jlSex;
    private javax.swing.JLabel jlShowName;
    private javax.swing.JLabel jlShowPersonalInfor;
    private javax.swing.JLabel jlSuccess;
    private javax.swing.JLabel jlUserID;
    private javax.swing.JLabel jlchangePasswordTripManager;
    private javax.swing.JLabel jlcompleteTrip;
    private javax.swing.JLabel jldeliveryTrip;
    private javax.swing.JLabel jllogOutTripManager;
    private javax.swing.JLabel jlnotifyOldPassword;
    private javax.swing.JLabel jlwaitingTrip;
    private javax.swing.JPasswordField jpwfConfirmPassword;
    private javax.swing.JPasswordField jpwfNewPassword;
    private javax.swing.JPasswordField jpwfOldPassword;
    // End of variables declaration//GEN-END:variables
}
