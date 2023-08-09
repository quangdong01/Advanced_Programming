package manageInformation;

import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import login.loginWindow;
import login.registerWindow;

/**
 *
 * @author NGUYEN QUANG DONG
 */
public class Driver extends javax.swing.JFrame {

    public loginWindow loginwindow;
    public int userID;
    public int driverID;
    public String nameUser;
    public String username;
    public String password;
    
    public Driver(int userID, loginWindow loginwindow) {
this.loginwindow = loginwindow;
        this.userID = userID;
        initComponents();
        jTabbedPaneDriver.setSelectedIndex(2);
        getDriverID();
        setEchor();
        setLabelGen();
        setSecondInformation();
        setForthInformation();
        setThirdInformation();
        setFifthInformation();
        setSixInformation();
        setSeventhInformation();
    }
    
    public void setEchor()
    {
        jpwfOldPassword.setEchoChar((char)0);
        jpwfNewPassword.setEchoChar((char)0);
        jpwfConfirmPassword.setEchoChar((char)0);
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
                this.nameUser = rsGen.getString("Họ và tên");
                this.username = rsGen.getString("Tài khoản");
                this.password = rsGen.getString("Mật khẩu");
                // hiện thị tên cho label
                if(this.nameUser == null)
                {
                    jlName.setText("Chưa xác định");
                }
                else
                {
                    jlName.setText(this.nameUser);
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
                jlAvatarCustomer.setIcon(icon2);
            }
            else if(checkGen == 2)
            {
                ImageIcon icon1 = new ImageIcon(getClass().getResource("Female.png"));
                ImageIcon icon2 = new ImageIcon(getClass().getResource("avatar_female.png"));
                jlName.setIcon(icon1);
                jlAvatarCustomer.setIcon(icon2);
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
    
    public void getDriverID()
    {
        try
        {
            String getDriverID = "Select Driver_ID From Drivers Where User_ID = ?";
            try(PreparedStatement sqlgetDriverID = this.loginwindow.db.cnt.prepareStatement(getDriverID))
            {
                sqlgetDriverID.setInt(1, this.userID);
                ResultSet rs = sqlgetDriverID.executeQuery();
                
                while(rs.next())
                {
                    this.driverID = rs.getInt("Driver_ID");
                    System.out.println(this.driverID);
                }
            }  
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void setSecondInformation()
    {
        jlSuccess.setVisible(false);
        String setTaiKhoan = "   " + this.driverID + " - " + this.nameUser;
        jlShowName.setText(setTaiKhoan);
    }
    
    
    public void setThirdInformation()
    {
        try
        {
            jlUserID.setText(String.valueOf(this.userID));

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
                jlRole.setText("Tài xế");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setForthInformation()
    {
        try
        {

            // thực hiện lấy dữ liệu để in ra panel
            String sqlGetInfor = "Select [Số giấy phép], [Hạng], [Lương], [Trạng thái]"
                    + " From [Drivers] Where Driver_ID = ?";

            PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlGetInfor);

            sqlUserWrite.setInt(1, this.driverID);

            // lấy bảng dữ liệu
            ResultSet rs = sqlUserWrite.executeQuery();

            while(rs.next())
            {
                jtfLicense.setText(rs.getString("Số giấy phép"));
                jtfRank.setText(rs.getString("Hạng"));
                jtfSalary.setText(rs.getString("Lương"));
                jtfState.setText(rs.getString("Trạng thái"));
                
                jtfLicense.enable(false);
                jtfRank.enable(false);
                jtfSalary.enable(false);
                jtfState.enable(false);
            }
        }
        
        catch (SQLException ex)
        {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFifthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtWork.setModel(defaultTableModel);
        
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
        
        jtWork.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtWork.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtWork.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtWork.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtWork.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtWork.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtWork.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtWork.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtWork.getColumnModel().getColumn(8).setPreferredWidth(30);

        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where Driver_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            // sqlgetListTrip.setInt(1, this.customerI);
            
            sqlgetListTrip.setInt(1, this.driverID);
            sqlgetListTrip.setString(2, "Đã giao cho tài xế");
            
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
   
    
    
    public void setSixInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtShipping.setModel(defaultTableModel);
        
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
        
        jtShipping.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtShipping.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtShipping.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtShipping.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtShipping.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtShipping.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtShipping.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtShipping.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtShipping.getColumnModel().getColumn(8).setPreferredWidth(30);

        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where Driver_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.driverID);
            sqlgetListTrip.setString(2, "Đang giao");
            
            
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
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void setSeventhInformation()
    {
                // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtComplete.setModel(defaultTableModel);
        
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
        
        jtComplete.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtComplete.getColumnModel().getColumn(1).setPreferredWidth(100);
        jtComplete.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtComplete.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtComplete.getColumnModel().getColumn(4).setPreferredWidth(100);
        jtComplete.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtComplete.getColumnModel().getColumn(6).setPreferredWidth(30);
        jtComplete.getColumnModel().getColumn(7).setPreferredWidth(30);
        jtComplete.getColumnModel().getColumn(8).setPreferredWidth(30);

        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListTrip = "Select [Trip_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Thời gian bắt đầu], [Thời gian kết thúc], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ]"
                    + " From Trips Where Driver_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.driverID);
            sqlgetListTrip.setString(2, "Thành công");

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
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void editUser(int userID)
    {
        jcbConfirmEditUser.setSelected(false);
        jlSuccessEditUser.setVisible(false);
        jlWarningConfirm.setText("");
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String getInforSingleUser = "Select [Họ và tên], [Email], [Giới tính], [Ngày sinh], [Số chứng minh thư], [Số điện thoại], [Địa chỉ] From [User] Where User_ID = ?";
            
            // tạo thể hiện
            try(PreparedStatement sqlgetInforSingleUser = this.loginwindow.db.cnt.prepareStatement(getInforSingleUser))
            {
                sqlgetInforSingleUser.setInt(1, userID);

                // thực hiện truy vấn
                ResultSet rs = sqlgetInforSingleUser.executeQuery();

                while(rs.next())
                {
                    jtfUserID.setText(String.valueOf(userID));
                    jtfUserID.enable(false);
                    jtfEditUserName.setText(rs.getString("Họ và tên"));
                    jtfEditMailUser.setText(rs.getString("Email"));
                    jtfEditSexUser.setText(rs.getString("Giới tính"));
                    jtfEditDayOfBirth.setText(rs.getString("Ngày sinh"));
                    jtfEditIdentityNumber.setText(rs.getString("Số chứng minh thư"));
                    jtfEditPhoneNumber.setText(rs.getString("Số điện thoại"));
                    jtfEditAddress.setText(rs.getString("Địa chỉ"));
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPaneDriver = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtfEditUserName = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jtfEditMailUser = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jtfEditSexUser = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jtfEditDayOfBirth = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jtfEditPhoneNumber = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jcbConfirmEditUser = new javax.swing.JCheckBox();
        jbtnUpdate = new javax.swing.JButton();
        jlSuccessEditUser = new javax.swing.JLabel();
        jlWarningConfirm = new javax.swing.JLabel();
        jlCarID = new javax.swing.JLabel();
        jtfUserID = new javax.swing.JTextField();
        jtfEditIdentityNumber = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jtfEditAddress = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jlWarningPhoneNumber = new javax.swing.JLabel();
        jlWarningEmail = new javax.swing.JLabel();
        jlWarningIdentityNumber = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jlAvatarCustomer = new javax.swing.JLabel();
        jlUserID = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jlFullName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jlSex = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jlDayOfBirth = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlIdentityNumber = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jlPhone = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jlAddress = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlRole = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jtfRank = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jtfSalary = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jtfState = new javax.swing.JTextField();
        jlCarID1 = new javax.swing.JLabel();
        jtfLicense = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtWork = new javax.swing.JTable();
        jbtnStart = new javax.swing.JButton();
        jlStart = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtShipping = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jbtnComplete = new javax.swing.JButton();
        jlComplete = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtComplete = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jlMailbox = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlChangePassword = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jlExit = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jlInformationUser = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jlShowLicenseAndSalary = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jlUpdateInformationUser = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jlShippingTrip = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jlCompleteTrip = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jlShipping = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 940, 40));

        jTabbedPaneDriver.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 37, 839, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 0));
        jLabel21.setText("THÔNG TIN CÁ NHÂN");
        jPanel11.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 6, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Họ và tên:");
        jPanel11.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 118, -1, 28));

        jtfEditUserName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel11.add(jtfEditUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 114, 682, 37));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Email:");
        jPanel11.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 184, -1, -1));

        jtfEditMailUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEditMailUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEditMailUserFocusGained(evt);
            }
        });
        jPanel11.add(jtfEditMailUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 178, 682, 37));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Giới tính:");
        jPanel11.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 255, -1, -1));

        jtfEditSexUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel11.add(jtfEditSexUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 249, 682, 37));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Số chứng minh thư:");
        jPanel11.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 383, -1, -1));

        jtfEditDayOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEditDayOfBirth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEditDayOfBirthActionPerformed(evt);
            }
        });
        jPanel11.add(jtfEditDayOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 312, 682, 37));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Số điện thoại:");
        jPanel11.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 442, -1, -1));

        jtfEditPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEditPhoneNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEditPhoneNumberFocusGained(evt);
            }
        });
        jtfEditPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEditPhoneNumberActionPerformed(evt);
            }
        });
        jPanel11.add(jtfEditPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 436, 682, 37));
        jPanel11.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 571, 843, -1));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 0, 0));
        jLabel38.setText("Xác nhận");
        jPanel11.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 580, -1, -1));

        jcbConfirmEditUser.setBackground(new java.awt.Color(255, 255, 255));
        jcbConfirmEditUser.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jcbConfirmEditUser.setText("Xác nhận");
        jcbConfirmEditUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel11.add(jcbConfirmEditUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 617, -1, 29));

        jbtnUpdate.setBackground(new java.awt.Color(51, 51, 255));
        jbtnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jbtnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jbtnUpdate.setText("Cập nhật");
        jbtnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });
        jPanel11.add(jbtnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 664, -1, 38));

        jlSuccessEditUser.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessEditUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessEditUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessEditUser.setText("Thành công! Bạn đã cập nhật xe thành công.");
        jlSuccessEditUser.setOpaque(true);
        jPanel11.add(jlSuccessEditUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 720, 837, 30));

        jlWarningConfirm.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jlWarningConfirm.setForeground(new java.awt.Color(255, 0, 51));
        jlWarningConfirm.setToolTipText("");
        jPanel11.add(jlWarningConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 617, 748, 29));

        jlCarID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID.setText("User_ID:");
        jPanel11.add(jlCarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 58, -1, -1));

        jtfUserID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfUserID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel11.add(jtfUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 52, 682, 37));

        jtfEditIdentityNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEditIdentityNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEditIdentityNumberFocusGained(evt);
            }
        });
        jtfEditIdentityNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEditIdentityNumberActionPerformed(evt);
            }
        });
        jPanel11.add(jtfEditIdentityNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 377, 682, 37));

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Ngày sinh:");
        jPanel11.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 318, -1, -1));

        jtfEditAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEditAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfEditAddressActionPerformed(evt);
            }
        });
        jPanel11.add(jtfEditAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 499, 682, 37));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel39.setText("Địa chỉ:");
        jPanel11.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 505, -1, -1));

        jlWarningPhoneNumber.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jlWarningPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, 682, 15));

        jlWarningEmail.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jlWarningEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 680, 20));

        jlWarningIdentityNumber.setForeground(new java.awt.Color(255, 0, 0));
        jPanel11.add(jlWarningIdentityNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 415, 682, 15));

        jTabbedPaneDriver.addTab("tab1", jPanel11);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(183, 27, 58));
        jLabel13.setText("ĐỔI MẬT KHẨU NGƯỜI DÙNG");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 37, -1, 38));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Key.png"))); // NOI18N
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 37, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Tài khoản");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 90, -1, -1));

        jlShowName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jlShowName.setOpaque(true);
        jPanel4.add(jlShowName, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 118, 830, 36));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("Mật khẩu cũ");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 166, -1, -1));

        jlnotifyOldPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jTabbedPaneDriver.addTab("tab2", jPanel4);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 940, 10));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("THÔNG TIN CÁ NHÂN");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));
        jPanel3.add(jlAvatarCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 150));

        jlUserID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(jlUserID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("Họ và tên  :");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, 30));

        jlFullName.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 180, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Giới tính  :");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 80, 30));

        jlSex.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 70, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Ngày sinh  :");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 90, 30));

        jlDayOfBirth.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jPanel3.add(jlDayOfBirth, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 140, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Email  :");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 60, 30));

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

        jTabbedPaneDriver.addTab("tab3", jPanel3);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 64, 839, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 0, 0));
        jLabel22.setText("THÔNG TIN BẰNG LÁI VÀ LƯƠNG");
        jPanel12.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Hạng:");
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, 28));

        jtfRank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel12.add(jtfRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 682, 37));

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel40.setText("Lương:");
        jPanel12.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jtfSalary.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfSalary.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSalaryFocusGained(evt);
            }
        });
        jPanel12.add(jtfSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 682, 37));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel41.setText("Trạng thái làm việc:");
        jPanel12.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jtfState.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel12.add(jtfState, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 682, 37));

        jlCarID1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID1.setText("Số giấy phép:");
        jPanel12.add(jlCarID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jtfLicense.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfLicense.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel12.add(jtfLicense, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 682, 37));

        jTabbedPaneDriver.addTab("tab4", jPanel12);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtWork.setModel(new javax.swing.table.DefaultTableModel(
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
        jtWork.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jtWork);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 920, 660));

        jbtnStart.setBackground(new java.awt.Color(51, 51, 255));
        jbtnStart.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jbtnStart.setForeground(new java.awt.Color(255, 255, 255));
        jbtnStart.setText("Bắt đầu");
        jbtnStart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStartActionPerformed(evt);
            }
        });
        jPanel5.add(jbtnStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 100, 30));

        jlStart.setForeground(new java.awt.Color(255, 0, 0));
        jPanel5.add(jlStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 240, 20));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 0, 0));
        jLabel23.setText("ĐƠN HÀNG ĐƯỢC GIAO");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jTabbedPaneDriver.addTab("tab5", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtShipping.setModel(new javax.swing.table.DefaultTableModel(
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
        jtShipping.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane2.setViewportView(jtShipping);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 920, 680));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("ĐƠN HÀNG ĐANG GIAO");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jbtnComplete.setBackground(new java.awt.Color(51, 51, 255));
        jbtnComplete.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jbtnComplete.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComplete.setText("Hoàn thành");
        jbtnComplete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCompleteActionPerformed(evt);
            }
        });
        jPanel6.add(jbtnComplete, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 140, 30));

        jlComplete.setForeground(new java.awt.Color(255, 0, 0));
        jPanel6.add(jlComplete, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 240, 20));

        jTabbedPaneDriver.addTab("tab6", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtComplete.setModel(new javax.swing.table.DefaultTableModel(
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
        jtComplete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane3.setViewportView(jtComplete);

        jPanel7.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 920, 720));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("ĐƠN HÀNG THÀNH CÔNG");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jTabbedPaneDriver.addTab("tab7", jPanel7);

        jPanel1.add(jTabbedPaneDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 940, 820));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jlName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 263, 39));

        jlMailbox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailbox.setForeground(new java.awt.Color(0, 102, 255));
        jlMailbox.setText("Thông báo");
        jlMailbox.setToolTipText("");
        jlMailbox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlMailbox.setOpaque(true);
        jlMailbox.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlMailboxMouseMoved(evt);
            }
        });
        jlMailbox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlMailboxMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlMailboxMouseExited(evt);
            }
        });
        jPanel2.add(jlMailbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 71, 245, 35));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 112, 263, -1));

        jlChangePassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlChangePassword.setForeground(new java.awt.Color(0, 102, 255));
        jlChangePassword.setText("Đổi mật khẩu");
        jlChangePassword.setToolTipText("");
        jlChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlChangePassword.setOpaque(true);
        jlChangePassword.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlChangePasswordMouseMoved(evt);
            }
        });
        jlChangePassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlChangePasswordMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlChangePasswordMouseExited(evt);
            }
        });
        jPanel2.add(jlChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 121, 245, 35));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 162, 263, -1));

        jlExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlExit.setForeground(new java.awt.Color(0, 102, 255));
        jlExit.setText("Thoát đăng nhập");
        jlExit.setToolTipText("");
        jlExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlExit.setOpaque(true);
        jlExit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlExitMouseMoved(evt);
            }
        });
        jlExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlExitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlExitMouseExited(evt);
            }
        });
        jPanel2.add(jlExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 171, 245, 35));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 212, 263, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("HỒ SƠ TÀI XẾ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 247, 119, 35));

        jlInformationUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlInformationUser.setForeground(new java.awt.Color(0, 102, 255));
        jlInformationUser.setText("Thông tin cá nhân");
        jlInformationUser.setToolTipText("");
        jlInformationUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlInformationUser.setOpaque(true);
        jlInformationUser.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlInformationUserMouseMoved(evt);
            }
        });
        jlInformationUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlInformationUserMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlInformationUserMouseExited(evt);
            }
        });
        jPanel2.add(jlInformationUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 294, 245, 35));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 335, 263, -1));

        jlShowLicenseAndSalary.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlShowLicenseAndSalary.setForeground(new java.awt.Color(0, 102, 255));
        jlShowLicenseAndSalary.setText("Thông tin bằng lái và lương");
        jlShowLicenseAndSalary.setToolTipText("");
        jlShowLicenseAndSalary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlShowLicenseAndSalary.setOpaque(true);
        jlShowLicenseAndSalary.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlShowLicenseAndSalaryMouseMoved(evt);
            }
        });
        jlShowLicenseAndSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlShowLicenseAndSalaryMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlShowLicenseAndSalaryMouseExited(evt);
            }
        });
        jPanel2.add(jlShowLicenseAndSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 344, 245, 35));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 385, 263, -1));

        jlUpdateInformationUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlUpdateInformationUser.setForeground(new java.awt.Color(0, 102, 255));
        jlUpdateInformationUser.setText("Cập nhật thông tin");
        jlUpdateInformationUser.setToolTipText("");
        jlUpdateInformationUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlUpdateInformationUser.setOpaque(true);
        jlUpdateInformationUser.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlUpdateInformationUserMouseMoved(evt);
            }
        });
        jlUpdateInformationUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlUpdateInformationUserMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlUpdateInformationUserMouseExited(evt);
            }
        });
        jPanel2.add(jlUpdateInformationUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 394, 245, 35));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 435, 263, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("QUẢN LÝ ĐƠN HÀNG");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 477, -1, 35));

        jlShippingTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlShippingTrip.setForeground(new java.awt.Color(0, 102, 255));
        jlShippingTrip.setText("Đơn hàng được giao");
        jlShippingTrip.setToolTipText("");
        jlShippingTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlShippingTrip.setOpaque(true);
        jlShippingTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlShippingTripMouseMoved(evt);
            }
        });
        jlShippingTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlShippingTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlShippingTripMouseExited(evt);
            }
        });
        jPanel2.add(jlShippingTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 530, 245, 35));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 571, 263, -1));

        jlCompleteTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCompleteTrip.setForeground(new java.awt.Color(0, 102, 255));
        jlCompleteTrip.setText(" Đơn hàng đã hoàn thành");
        jlCompleteTrip.setToolTipText("");
        jlCompleteTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlCompleteTrip.setOpaque(true);
        jlCompleteTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlCompleteTripMouseMoved(evt);
            }
        });
        jlCompleteTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlCompleteTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlCompleteTripMouseExited(evt);
            }
        });
        jPanel2.add(jlCompleteTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 245, 35));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 692, -1, -1));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 621, 263, -1));

        jlShipping.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlShipping.setForeground(new java.awt.Color(0, 102, 255));
        jlShipping.setText("Đơn hàng đang giao");
        jlShipping.setToolTipText("");
        jlShipping.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlShipping.setOpaque(true);
        jlShipping.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlShippingMouseMoved(evt);
            }
        });
        jlShipping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlShippingMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlShippingMouseExited(evt);
            }
        });
        jPanel2.add(jlShipping, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 580, 245, 35));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 260, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 820));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlMailboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailboxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlMailboxMouseClicked

    private void jlMailboxMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailboxMouseMoved
        // TODO add your handling code here:
        jlMailbox.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlMailboxMouseMoved

    private void jlMailboxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailboxMouseExited
        // TODO add your handling code here:
        jlMailbox.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlMailboxMouseExited

    private void jlChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseClicked
        // TODO add your handling code here:
        jlSuccess.setVisible(false);
        jlnotifyOldPassword.setText("");
        jlRequest.setText("");
        jlConfirm.setText("");
        jTabbedPaneDriver.setSelectedIndex(1);       
    }//GEN-LAST:event_jlChangePasswordMouseClicked

    private void jlChangePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseExited
        // TODO add your handling code here:
        jlChangePassword.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlChangePasswordMouseExited

    private void jlChangePasswordMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseMoved
        // TODO add your handling code here:
        jlChangePassword.setBackground(new Color(255, 255, 255));
        
    }//GEN-LAST:event_jlChangePasswordMouseMoved

    private void jlExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseClicked
        // TODO add your handling code here:
        this.loginwindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jlExitMouseClicked

    private void jlExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseExited
        // TODO add your handling code here:
        jlExit.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlExitMouseExited

    private void jlExitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlExitMouseMoved
        // TODO add your handling code here:
        jlExit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlExitMouseMoved

    private void jlInformationUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseClicked
        // TODO add your handling code here:
        
        jTabbedPaneDriver.setSelectedIndex(2);
    }//GEN-LAST:event_jlInformationUserMouseClicked

    private void jlInformationUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseExited
        // TODO add your handling code here:
        jlInformationUser.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlInformationUserMouseExited

    private void jlInformationUserMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseMoved
        // TODO add your handling code here:
        jlInformationUser.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlInformationUserMouseMoved

    private void jlShowLicenseAndSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowLicenseAndSalaryMouseClicked
        // TODO add your handling code here:
        jTabbedPaneDriver.setSelectedIndex(3);
        
    }//GEN-LAST:event_jlShowLicenseAndSalaryMouseClicked

    private void jlShowLicenseAndSalaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowLicenseAndSalaryMouseExited
        // TODO add your handling code here:
        jlShowLicenseAndSalary.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShowLicenseAndSalaryMouseExited

    private void jlShowLicenseAndSalaryMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowLicenseAndSalaryMouseMoved
        // TODO add your handling code here:
        jlShowLicenseAndSalary.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlShowLicenseAndSalaryMouseMoved

    private void jlUpdateInformationUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseClicked
        // TODO add your handling code here:
        editUser(this.userID);
        jTabbedPaneDriver.setSelectedIndex(0);
    }//GEN-LAST:event_jlUpdateInformationUserMouseClicked

    private void jlUpdateInformationUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseExited
        // TODO add your handling code here:
        jlUpdateInformationUser.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlUpdateInformationUserMouseExited

    private void jlUpdateInformationUserMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseMoved
        // TODO add your handling code here:
        jlUpdateInformationUser.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlUpdateInformationUserMouseMoved

    private void jlShippingTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneDriver.setSelectedIndex(4);
    }//GEN-LAST:event_jlShippingTripMouseClicked

    private void jlShippingTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseExited
        // TODO add your handling code here:
        jlShippingTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShippingTripMouseExited

    private void jlShippingTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseMoved
        // TODO add your handling code here:
        jlShippingTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlShippingTripMouseMoved

    private void jlShippingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingMouseClicked
        // TODO add your handling code here:
        jTabbedPaneDriver.setSelectedIndex(5);
    }//GEN-LAST:event_jlShippingMouseClicked

    private void jlShippingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingMouseExited
        // TODO add your handling code here:
        jlShipping.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShippingMouseExited

    private void jlShippingMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingMouseMoved
        // TODO add your handling code here:
        jlShipping.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlShippingMouseMoved

    private void jlCompleteTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneDriver.setSelectedIndex(6);
    }//GEN-LAST:event_jlCompleteTripMouseClicked

    private void jlCompleteTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseExited
        // TODO add your handling code here:
        jlCompleteTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlCompleteTripMouseExited

    private void jlCompleteTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseMoved
        // TODO add your handling code here:
        jlCompleteTrip.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlCompleteTripMouseMoved

    private void jtfSalaryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSalaryFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSalaryFocusGained

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

    private void jpwfOldPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfOldPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfOldPassword.getText().equals("mật khẩu cũ"))
        {
            jpwfOldPassword.setText("");
            jlnotifyOldPassword.setText("");
            jpwfOldPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfOldPassword.setEchoChar('.');
            jpwfOldPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfOldPasswordFocusGained

    private void jpwfConfirmPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfConfirmPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfConfirmPassword.getText().equals("")) {
            jpwfConfirmPassword.setText("Confirm");
            jpwfConfirmPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfConfirmPassword.setForeground(new Color(153, 153, 153));
            jpwfConfirmPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfConfirmPasswordFocusLost

    private void jpwfConfirmPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfConfirmPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfConfirmPassword.getText().equals("Confirm"))
        {
            jpwfConfirmPassword.setText("");
            jlConfirm.setText("");
            jpwfConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfConfirmPassword.setEchoChar('.');
            jpwfConfirmPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfConfirmPasswordFocusGained

    private void jpwfNewPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfNewPasswordFocusLost
        // TODO add your handling code here:
        if (jpwfNewPassword.getText().equals("")) {
            jpwfNewPassword.setText("Password");
            jpwfNewPassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfNewPassword.setForeground(new Color(153, 153, 153));
            jpwfNewPassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfNewPasswordFocusLost

    private void jpwfNewPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfNewPasswordFocusGained
        // TODO add your handling code here:
        if (jpwfNewPassword.getText().equals("Password"))
        {
            jpwfNewPassword.setText("");
            jlRequest.setText("");
            jpwfNewPassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfNewPassword.setEchoChar('.');
            jpwfNewPassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfNewPasswordFocusGained

    private void jtfEditAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditAddressActionPerformed

    private void jtfEditIdentityNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditIdentityNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditIdentityNumberActionPerformed

    private void jtfEditIdentityNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditIdentityNumberFocusGained
        // TODO add your handling code here:
        jlWarningIdentityNumber.setText("");
    }//GEN-LAST:event_jtfEditIdentityNumberFocusGained

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        // TODO add your handling code here:
        jlWarningConfirm.setText("");
        if(!jcbConfirmEditUser.isSelected())
        {
            jlWarningConfirm.setText("bắt buộc");
        }
        else
        {
            int userIDUpdate = Integer.parseInt(jtfUserID.getText());
            String nameUserCustomer = jtfEditUserName.getText();
            String mailUser = jtfEditMailUser.getText();
            String sexUser = jtfEditSexUser.getText();
            String dayOfBirthUser = jtfEditDayOfBirth.getText();
            String indentityUser = jtfEditIdentityNumber.getText();
            String phoneUser = jtfEditPhoneNumber.getText();
            String addressUser = jtfEditAddress.getText();
            Date dateBirth = Date.valueOf(dayOfBirthUser);
            try
            {
                String sqlSweepIDN = "Select User_ID, [Số chứng minh thư] from [User]";

                Statement sqlQuery = this.loginwindow.db.cnt.createStatement();

                ResultSet rs = sqlQuery.executeQuery(sqlSweepIDN);

                // cờ kiểm tra duyệt chứng minh thư
                boolean checkIDN =  false;

                while(rs.next())
                {
                    String CMT = rs.getString("Số chứng minh thư");
                    if(indentityUser.equals(CMT))
                    {
                        if(userIDUpdate == rs.getInt("User_ID"))
                        {
                            continue;
                        }
                        else
                        {
                            checkIDN = true;
                            break;
                        }
                    }
                }
                if(checkIDN)
                {
                    jlWarningIdentityNumber.setText("số chứng minh thư đã tồn tại");
                }
                else
                {
                    // câu truy vấn Email
                    String sqlSweepEmail = "Select User_ID, Email from [User]";

                    // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                    PreparedStatement sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlSweepEmail);

                    // trả về bản ghi
                    ResultSet rsEmail = sqlUserWrite.executeQuery();

                    boolean checkEmail = false;

                    while(rsEmail.next())
                    {
                        String Mail = rsEmail.getString("Email");
                        if(mailUser.equals(Mail))
                        {
                            if(userIDUpdate == rsEmail.getInt("User_ID"))
                            {
                                continue;
                            }
                            else
                            {
                                checkEmail = true;
                                break;
                            }
                        }
                    }
                    if(checkEmail)
                    {
                        jlWarningEmail.setText("Email đã tồn tại");
                    }
                    else
                    {
                        // câu truy vấn
                        String sqlSweepPN = "Select User_ID, [Số điện thoại] from [User]";

                        // tạo thể hiện kết nối với db để chuẩn bị truy vấn
                        sqlUserWrite = this.loginwindow.db.cnt.prepareStatement(sqlSweepPN);

                        // trả về bản ghi
                        ResultSet rsPn = sqlUserWrite.executeQuery();

                        boolean checkPn = false;

                        while(rsPn.next())
                        {
                            String Pn = rsPn.getString("Số điện thoại");
                            if(phoneUser.equals(Pn))
                            {
                                if(userIDUpdate == rsPn.getInt("User_ID"))
                                {
                                    continue;
                                }
                                else
                                {
                                    checkPn = true;
                                    break;
                                }
                            }
                        }
                        if(checkPn)
                        {
                            jlWarningPhoneNumber.setText("Số điện thoại phải là duy nhất");
                        }
                        else
                        {
                            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
                            String editUser = "Update [User] "
                            + "Set [Họ và tên] = ?, [Email] = ?, [Giới tính] = ?, [Ngày sinh] = ?, [Số chứng minh thư] = ? , [Số điện thoại] = ?, [Địa chỉ] = ?"
                            + " Where User_ID = ?";

                            // tạo thể hiện
                            PreparedStatement sqleditUser = this.loginwindow.db.cnt.prepareStatement(editUser);

                            sqleditUser.setString(1, nameUserCustomer);
                            sqleditUser.setString(2, mailUser);
                            sqleditUser.setString(3, sexUser);
                            sqleditUser.setDate(4, dateBirth);
                            sqleditUser.setString(5, indentityUser);
                            sqleditUser.setString(6, phoneUser);
                            sqleditUser.setString(7, addressUser);
                            sqleditUser.setInt(8, userIDUpdate);

                            // thực hiện truy vấn
                            sqleditUser.executeUpdate();

                            // cập nhật lại bảng
                            setLabelGen();
                            setThirdInformation();
                            jlSuccessEditUser.setVisible(true);

                        }
                    }
                }
            } catch (SQLException ex)
            {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jtfEditPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditPhoneNumberActionPerformed

    private void jtfEditPhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditPhoneNumberFocusGained
        // TODO add your handling code here:
        jlWarningPhoneNumber.setText("");
    }//GEN-LAST:event_jtfEditPhoneNumberFocusGained

    private void jtfEditDayOfBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditDayOfBirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditDayOfBirthActionPerformed

    private void jtfEditMailUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditMailUserFocusGained
        // TODO add your handling code here:
        jlWarningEmail.setText("");
    }//GEN-LAST:event_jtfEditMailUserFocusGained

    private void jbtnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCompleteActionPerformed
        // TODO add your handling code here:
        try
        {
            int row = jtShipping.getSelectedRow();
            if(row == -1)
            {
                jlComplete.setText("Vui lòng chọn một đơn hàng");
            }    
            else
            {         
                jlComplete.setText("");
                int stripID = Integer.parseInt(String.valueOf(jtShipping.getValueAt(row, 0)));
                String updateTripStatus = "Update [Trips]"
                + "Set [Thời gian kết thúc] = ?, [Trạng thái] = ?"
                + " Where Trip_ID = ?";
                
                // thực hiện lấy thời gian hiện tại để cấp quyền sử dụng tài khoản
                Timestamp datetime = new Timestamp(System.currentTimeMillis());
                
                // tạo thể hiện
                try(PreparedStatement sqlupdateTripStatus = this.loginwindow.db.cnt.prepareStatement(updateTripStatus))
                {
                    sqlupdateTripStatus.setTimestamp(1, datetime);
                    sqlupdateTripStatus.setString(2, "Thành công");
                    sqlupdateTripStatus.setInt(3, stripID);
                    
                    // thực hiện truy vấn
                    sqlupdateTripStatus.executeUpdate();
                    
                    setSixInformation();
                    setSeventhInformation();
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jbtnCompleteActionPerformed

    private void jbtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStartActionPerformed
        // TODO add your handling code here:
        try
        {
            int row = jtWork.getSelectedRow();
            if(row == -1)
            {
                jlStart.setText("Vui lòng chọn một đơn hàng");
            }    
            else
            {         
                jlStart.setText("");
                int stripID = Integer.parseInt(String.valueOf(jtWork.getValueAt(row, 0)));
                String updateTripStatus = "Update [Trips]"
                + "Set [Thời gian bắt đầu] = ?, [Trạng thái] = ?"
                + " Where Trip_ID = ?";
                
                // thực hiện lấy thời gian hiện tại để cấp quyền sử dụng tài khoản
                Timestamp datetime = new Timestamp(System.currentTimeMillis());
                
                // tạo thể hiện
                try(PreparedStatement sqlupdateTripStatus = this.loginwindow.db.cnt.prepareStatement(updateTripStatus))
                {
                    sqlupdateTripStatus.setTimestamp(1, datetime);
                    sqlupdateTripStatus.setString(2, "Đang giao");
                    sqlupdateTripStatus.setInt(3, stripID);
                    
                    // thực hiện truy vấn
                    sqlupdateTripStatus.executeUpdate();
                    
                    setFifthInformation();
                    setSixInformation();
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jbtnStartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPaneDriver;
    private javax.swing.JButton jbtnComplete;
    private javax.swing.JButton jbtnStart;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JCheckBox jcbConfirmEditUser;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarCustomer;
    private javax.swing.JLabel jlCarID;
    private javax.swing.JLabel jlCarID1;
    private javax.swing.JLabel jlChangePassword;
    private javax.swing.JLabel jlCheckOK;
    private javax.swing.JLabel jlComplete;
    private javax.swing.JLabel jlCompleteTrip;
    private javax.swing.JLabel jlConfirm;
    private javax.swing.JLabel jlDayOfBirth;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlExit;
    private javax.swing.JLabel jlFullName;
    private javax.swing.JLabel jlIdentityNumber;
    private javax.swing.JLabel jlInformationUser;
    private javax.swing.JLabel jlMailbox;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlRequest;
    private javax.swing.JLabel jlRole;
    private javax.swing.JLabel jlSex;
    private javax.swing.JLabel jlShipping;
    private javax.swing.JLabel jlShippingTrip;
    private javax.swing.JLabel jlShowLicenseAndSalary;
    private javax.swing.JLabel jlShowName;
    private javax.swing.JLabel jlStart;
    private javax.swing.JLabel jlSuccess;
    private javax.swing.JLabel jlSuccessEditUser;
    private javax.swing.JLabel jlUpdateInformationUser;
    private javax.swing.JLabel jlUserID;
    private javax.swing.JLabel jlWarningConfirm;
    private javax.swing.JLabel jlWarningEmail;
    private javax.swing.JLabel jlWarningIdentityNumber;
    private javax.swing.JLabel jlWarningPhoneNumber;
    private javax.swing.JLabel jlnotifyOldPassword;
    private javax.swing.JPasswordField jpwfConfirmPassword;
    private javax.swing.JPasswordField jpwfNewPassword;
    private javax.swing.JPasswordField jpwfOldPassword;
    private javax.swing.JTable jtComplete;
    private javax.swing.JTable jtShipping;
    private javax.swing.JTable jtWork;
    private javax.swing.JTextField jtfEditAddress;
    private javax.swing.JTextField jtfEditDayOfBirth;
    private javax.swing.JTextField jtfEditIdentityNumber;
    private javax.swing.JTextField jtfEditMailUser;
    private javax.swing.JTextField jtfEditPhoneNumber;
    private javax.swing.JTextField jtfEditSexUser;
    private javax.swing.JTextField jtfEditUserName;
    private javax.swing.JTextField jtfLicense;
    private javax.swing.JTextField jtfRank;
    private javax.swing.JTextField jtfSalary;
    private javax.swing.JTextField jtfState;
    private javax.swing.JTextField jtfUserID;
    // End of variables declaration//GEN-END:variables
}
