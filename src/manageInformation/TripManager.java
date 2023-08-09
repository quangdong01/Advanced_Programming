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
import javax.swing.table.DefaultTableModel;
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
        setSixthInformation();
        setSeventhInformation();
        setEighthInformation();
        setNinthInformation();
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
        jPanel10 = new javax.swing.JPanel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtWaitTrip = new javax.swing.JTable();
        jbtnAccept = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jlAccept = new javax.swing.JLabel();
        jlCancel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtShippingTrip = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtCompleteTrip = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtCancelTrip = new javax.swing.JTable();

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

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, 40));

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
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtWaitTrip.setModel(new javax.swing.table.DefaultTableModel(
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
        jtWaitTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jtWaitTrip);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 900, 690));

        jbtnAccept.setBackground(new java.awt.Color(51, 51, 255));
        jbtnAccept.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnAccept.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAccept.setText("Xác nhận");
        jbtnAccept.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAcceptActionPerformed(evt);
            }
        });
        jPanel5.add(jbtnAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 6, -1, 31));

        jbtnCancel.setBackground(new java.awt.Color(51, 51, 255));
        jbtnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnCancel.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCancel.setText("Hủy đơn");
        jbtnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelActionPerformed(evt);
            }
        });
        jPanel5.add(jbtnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 6, -1, 31));
        jPanel5.add(jlAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 43, 202, 20));
        jPanel5.add(jlCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 43, 189, 20));

        jTabbedPaneAll.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 0, 0));
        jLabel27.setText("ĐƠN HÀNG ĐANG GIAO");
        jPanel6.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 20, -1, -1));

        jtShippingTrip.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jtShippingTrip);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 908, 714));

        jTabbedPaneAll.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("ĐƠN HÀNG GIAO THÀNH CÔNG");

        jtCompleteTrip.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jtCompleteTrip);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(311, 311, 311)
                .addComponent(jLabel28)
                .addContainerGap(340, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneAll.addTab("tab5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("ĐƠN HÀNG ĐÃ HỦY");

        jtCancelTrip.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jtCancelTrip);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(jLabel29)
                .addContainerGap(385, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneAll.addTab("tab6", jPanel8);

        jPanel1.add(jTabbedPaneAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 1, 930, -1));

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
        jlSuccess.setVisible(false);
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

    public void setSixthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtWaitTrip.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Trip_ID");
        defaultTableModel.addColumn("Điểm xuất phát");
        defaultTableModel.addColumn("Điểm kết thúc");
        defaultTableModel.addColumn("Thời gian đặt");
        defaultTableModel.addColumn("Thời gian bắt đầu");
        defaultTableModel.addColumn("Thời gian kết thúc");
        defaultTableModel.addColumn("Distance");
        defaultTableModel.addColumn("Weight");
        defaultTableModel.addColumn("Cost");
        
        jtWaitTrip.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtWaitTrip.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtWaitTrip.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtWaitTrip.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtWaitTrip.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtWaitTrip.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtWaitTrip.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtWaitTrip.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtWaitTrip.getColumnModel().getColumn(8).setPreferredWidth(30);

        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where [Trạng thái] = ?";
            
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setString(1, "Đang chờ");
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListTrip.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getString("Trip_ID"), rs.getString("Điểm xuất phát"), rs.getString("Điểm kết thúc"), rs.getString("Thời gian đặt hàng"), 
                rs.getString("Thời gian bắt đầu"), rs.getString("Thời gian kết thúc"), rs.getString("Tổng quãng đường"), rs.getString("Khối lượng hàng"), rs.getString("Phí dịch vụ")});
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
     
    public void setSeventhInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtShippingTrip.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Trip_ID");
        defaultTableModel.addColumn("Điểm xuất phát");
        defaultTableModel.addColumn("Điểm kết thúc");
        defaultTableModel.addColumn("Thời gian đặt");
        defaultTableModel.addColumn("Thời gian bắt đầu");
        defaultTableModel.addColumn("Thời gian kết thúc");
        defaultTableModel.addColumn("Distance");
        defaultTableModel.addColumn("Weight");
        defaultTableModel.addColumn("Cost");
        
        jtShippingTrip.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtShippingTrip.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtShippingTrip.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtShippingTrip.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtShippingTrip.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtShippingTrip.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtShippingTrip.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtShippingTrip.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtShippingTrip.getColumnModel().getColumn(8).setPreferredWidth(30);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where [Trạng thái] = ? And Manager_ID = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setString(1, "Đang giao");
            sqlgetListTrip.setInt(2, this.managerID);
            // lấy danh sách xe
            ResultSet rs = sqlgetListTrip.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getString("Trip_ID"), rs.getString("Điểm xuất phát"), rs.getString("Điểm kết thúc"), rs.getString("Thời gian đặt hàng"), 
                rs.getString("Thời gian bắt đầu"), rs.getString("Thời gian kết thúc"), rs.getString("Tổng quãng đường"), rs.getString("Khối lượng hàng"), rs.getString("Phí dịch vụ")});
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setEighthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtCompleteTrip.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Trip_ID");
        defaultTableModel.addColumn("Điểm xuất phát");
        defaultTableModel.addColumn("Điểm kết thúc");
        defaultTableModel.addColumn("Thời gian đặt");
        defaultTableModel.addColumn("Thời gian bắt đầu");
        defaultTableModel.addColumn("Thời gian kết thúc");
        defaultTableModel.addColumn("Distance");
        defaultTableModel.addColumn("Weight");
        defaultTableModel.addColumn("Cost");
        
        jtCompleteTrip.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtCompleteTrip.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtCompleteTrip.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtCompleteTrip.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtCompleteTrip.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtCompleteTrip.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtCompleteTrip.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtCompleteTrip.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtCompleteTrip.getColumnModel().getColumn(8).setPreferredWidth(30);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where [Trạng thái] = ? And Manager_ID = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setString(1, "Thành công");
            sqlgetListTrip.setInt(2, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListTrip.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getString("Trip_ID"), rs.getString("Điểm xuất phát"), rs.getString("Điểm kết thúc"), rs.getString("Thời gian đặt hàng"), 
                rs.getString("Thời gian bắt đầu"), rs.getString("Thời gian kết thúc"), rs.getString("Tổng quãng đường"), rs.getString("Khối lượng hàng"), rs.getString("Phí dịch vụ")});
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void setNinthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtCancelTrip.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Trip_ID");
        defaultTableModel.addColumn("Điểm xuất phát");
        defaultTableModel.addColumn("Điểm kết thúc");
        defaultTableModel.addColumn("Thời gian đặt");
        defaultTableModel.addColumn("Thời gian bắt đầu");
        defaultTableModel.addColumn("Thời gian kết thúc");
        defaultTableModel.addColumn("Distance");
        defaultTableModel.addColumn("Weight");
        defaultTableModel.addColumn("Cost");
        
        jtCancelTrip.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtCancelTrip.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtCancelTrip.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtCancelTrip.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtCancelTrip.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtCancelTrip.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtCancelTrip.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtCancelTrip.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtCancelTrip.getColumnModel().getColumn(8).setPreferredWidth(30);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where [Trạng thái] = ? And Manager_ID = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setString(1, "Đã hủy");
            sqlgetListTrip.setInt(2, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListTrip.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getString("Trip_ID"), rs.getString("Điểm xuất phát"), rs.getString("Điểm kết thúc"), rs.getString("Thời gian đặt hàng"), 
                rs.getString("Thời gian bắt đầu"), rs.getString("Thời gian kết thúc"), rs.getString("Tổng quãng đường"), rs.getString("Khối lượng hàng"), rs.getString("Phí dịch vụ")});
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    
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

    private void jbtnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAcceptActionPerformed
        // TODO add your handling code here:
        int driverID = -1;
        int carID = -1;
        
        try
        {
            int row = jtWaitTrip.getSelectedRow();
            if(row == -1)
            {
                jlAccept.setText("Vui lòng chọn một đơn hàng");
            }    
            else
            {
                int stripID = Integer.parseInt(String.valueOf(jtWaitTrip.getValueAt(row, 0)));
                int weightTrip = -1;
                // Tạo câu lệnh truy vấn tài khối lượng đơn hàng
                String getWeight = "Select [Khối lượng hàng] from [Trips] Where [Trip_ID] = ?";

                // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                try(PreparedStatement sqlgetWeight = this.loginwindow.db.cnt.prepareStatement(getWeight))
                {
                    sqlgetWeight.setInt(1, stripID);
                    // trả về bản ghi

                    ResultSet rsWeight = sqlgetWeight.executeQuery();

                    while(rsWeight.next())
                    {
                        weightTrip = Integer.parseInt(rsWeight.getString("Khối lượng hàng"));
                    }
                }

                // Tạo câu lệnh truy vấn tài xế 
                String SweepDriver = "Select [Driver_ID] from [Drivers] Where [Trạng thái] = ?";

                // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                try(PreparedStatement sqlSweepDriver = this.loginwindow.db.cnt.prepareStatement(SweepDriver))
                {
                    sqlSweepDriver.setString(1, "Đang chờ việc");
                    // trả về bản ghi

                    ResultSet rsDriver = sqlSweepDriver.executeQuery();

                    while(rsDriver.next())
                    {
                        driverID = Integer.parseInt(rsDriver.getString("Driver_ID"));
                        break;
                    }
                }
                if(driverID == -1)
                {
                    jlAccept.setText("thử lại sau, chưa có tài xế...");
                }
                else
                {
                    // Tạo câu lệnh truy vấn tìm xe
                    String SweepCar = "Select [Car_ID], [Khối lượng tối đa] from [Cars] Where [Trạng thái xe] = ?";

                    // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                    try(PreparedStatement sqlSweepCar = this.loginwindow.db.cnt.prepareStatement(SweepCar))
                    {
                        sqlSweepCar.setString(1, "Đang trong kho");
                        // trả về bản ghi

                        ResultSet rsCar = sqlSweepCar.executeQuery();

                        while(rsCar.next())
                        {
                            int weight = Integer.parseInt(rsCar.getString("Khối lượng tối đa"));
                            if(weight > weightTrip)
                            {
                                carID = Integer.parseInt(rsCar.getString("Car_ID"));
                                break;
                            }
                        }
                    }

                    if(carID == -1)
                    {
                        jlAccept.setText("thử lại sau, xe đang hết...");
                    }
                    else
                    {

                        String updateTripStatus = "Update [Trips] "
                        + "Set Driver_ID = ?, Car_ID = ?, Manager_ID = ?, [Trạng thái] = ?"
                        + " Where Trip_ID = ?";
                        jlCancel.setText("");
                        // tạo thể hiện
                        try(PreparedStatement sqlupdateTripStatus = this.loginwindow.db.cnt.prepareStatement(updateTripStatus))
                        {
                            sqlupdateTripStatus.setInt(1, driverID);
                            sqlupdateTripStatus.setInt(2, carID);
                            sqlupdateTripStatus.setInt(3, this.managerID);
                            sqlupdateTripStatus.setString(4, "Đã giao cho tài xế");
                            sqlupdateTripStatus.setInt(5, stripID);

                            // thực hiện truy vấn
                            sqlupdateTripStatus.executeUpdate();
                        }

                        String updateDrivers = "Update [Drivers] "
                        + "Set [Trạng thái] = ?"
                        + " Where Driver_ID = ?";

                        try(PreparedStatement sqlupdateDrivers = this.loginwindow.db.cnt.prepareStatement(updateDrivers))
                        {
                            sqlupdateDrivers.setString(1, "Đang làm việc");
                            sqlupdateDrivers.setInt(2, driverID);

                            // thực hiện truy vấn
                            sqlupdateDrivers.executeUpdate();
                        }

                        String updateCars = "Update [Cars] "
                        + "Set [Trạng thái xe] = ?, Driver_ID = ?"
                        + " Where Car_ID = ?";

                        try(PreparedStatement sqlupdateCars = this.loginwindow.db.cnt.prepareStatement(updateCars))
                        {
                            sqlupdateCars.setString(1, "Đang vận chuyển");
                            sqlupdateCars.setInt(2, driverID);
                            sqlupdateCars.setInt(3, carID);

                            // thực hiện truy vấn
                            sqlupdateCars.executeUpdate();

                            setSixthInformation();
                            setSeventhInformation(); 
                        } 
                    }
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnAcceptActionPerformed

    private void jbtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelActionPerformed
        // TODO add your handling code here:
        String updateTripStatus = "Update [Trips] "
        + "Set Manager_ID = ?, [Trạng thái] = ?"
        + " Where Trip_ID = ?";
        
        try
        {
            int row = jtWaitTrip.getSelectedRow();
            
            if(row == -1)
            {
                jlCancel.setText("chọn đơn hàng cần hủy");
            }
            else
            {
                jlCancel.setText("");
                int stripID = Integer.parseInt(String.valueOf(jtWaitTrip.getValueAt(row, 0)));
                
                // tạo thể hiện
                PreparedStatement sqlupdateTripStatus = this.loginwindow.db.cnt.prepareStatement(updateTripStatus);

                sqlupdateTripStatus.setInt(1, this.managerID);
                sqlupdateTripStatus.setString(2, "Đã hủy");
                sqlupdateTripStatus.setInt(3, stripID);

                // thực hiện truy vấn
                sqlupdateTripStatus.executeUpdate();
                
                setSixthInformation();
                setNinthInformation();  
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnCancelActionPerformed

    

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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPaneAll;
    private javax.swing.JButton jbtnAccept;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JLabel jlAccept;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarManager;
    private javax.swing.JLabel jlCancel;
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
    private javax.swing.JTable jtCancelTrip;
    private javax.swing.JTable jtCompleteTrip;
    private javax.swing.JTable jtShippingTrip;
    private javax.swing.JTable jtWaitTrip;
    // End of variables declaration//GEN-END:variables
}
