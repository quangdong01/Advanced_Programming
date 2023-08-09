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
import java.sql.Statement;
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
public class CarManager extends javax.swing.JFrame {

    public loginWindow loginwindow;
    public int userID;
    public int managerID;
    public String nameManager;
    public String username;
    public String password;
    /**
     * Creates new form CarManager
     */
    public CarManager(int userID, loginWindow loginwindow) {
        this.userID = userID;
        this.loginwindow = loginwindow;
        initComponents();
        
        getManagerID();
        setLabelGen();
        setEchor();
        setFirstInformation();
        setSecondInformation();
        setThirdInformation();
        setForthInformation();
        setFifthInformation();
        setSixthInformation();
    }

    public void setEchor()
    {
        jpwfOldPassword.setEchoChar((char)0);
        jpwfNewPassword.setEchoChar((char)0);
        jpwfConfirmPassword.setEchoChar((char)0);
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
            
            jlManagerID.setHorizontalAlignment(SwingConstants.CENTER);
            jlManagerID.setVerticalAlignment(SwingConstants.CENTER);
            
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
    
public void setFirstInformation() 
{
    try
    {
        jTabbedPaneAll.setSelectedIndex(0);

        jlManagerID.setText(String.valueOf(this.managerID));
        
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
    
    public void setThirdInformation()
    {
        jlSuccessAddNewCar.setVisible(false);
    }
    
    public void setForthInformation()
    {
        // an label success
        jlSuccessDeleteCar.setVisible(false);
        
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtListCar.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Car_ID");
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Loại xe");
        defaultTableModel.addColumn("Biển số");
        defaultTableModel.addColumn("Năm");
        defaultTableModel.addColumn("Màu");
        defaultTableModel.addColumn("Quãng đường");
        defaultTableModel.addColumn("Trọng lượng tối đa");
        defaultTableModel.addColumn("Trạng thái");
        
        jtListCar.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtListCar.getColumnModel().getColumn(1).setPreferredWidth(60);
        jtListCar.getColumnModel().getColumn(2).setPreferredWidth(130);
        jtListCar.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtListCar.getColumnModel().getColumn(4).setPreferredWidth(50);
        jtListCar.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtListCar.getColumnModel().getColumn(6).setPreferredWidth(100);
        jtListCar.getColumnModel().getColumn(7).setPreferredWidth(120);
        jtListCar.getColumnModel().getColumn(8).setPreferredWidth(100);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListCar = "Select [Car_ID], [Driver_ID], [Loại xe], [Biển số xe], [Năm sản xuất], [Màu xe], [Quãng đường đi], [Khối lượng tối đa], [Trạng thái xe]"
                    + " From Cars Where Manager_ID = ?";
            PreparedStatement sqlgetListCar = this.loginwindow.db.cnt.prepareStatement(getListCar);
            sqlgetListCar.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListCar.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Car_ID"), rs.getInt("Driver_ID"), rs.getString("Loại xe"), rs.getString("Biển số xe"), 
                rs.getInt("Năm sản xuất"), rs.getString("Màu xe"), rs.getString("Quãng đường đi"), rs.getString("Khối lượng tối đa"), rs.getString("Trạng thái xe")});
            }
            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void setFifthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtListCarToShow.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Car_ID");
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Loại xe");
        defaultTableModel.addColumn("Biển số");
        defaultTableModel.addColumn("Năm");
        defaultTableModel.addColumn("Màu");
        defaultTableModel.addColumn("Quãng đường");
        defaultTableModel.addColumn("Trọng lượng tối đa");
        defaultTableModel.addColumn("Trạng thái");
        
        jtListCarToShow.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtListCarToShow.getColumnModel().getColumn(1).setPreferredWidth(60);
        jtListCarToShow.getColumnModel().getColumn(2).setPreferredWidth(130);
        jtListCarToShow.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtListCarToShow.getColumnModel().getColumn(4).setPreferredWidth(50);
        jtListCarToShow.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtListCarToShow.getColumnModel().getColumn(6).setPreferredWidth(100);
        jtListCarToShow.getColumnModel().getColumn(7).setPreferredWidth(120);
        jtListCarToShow.getColumnModel().getColumn(8).setPreferredWidth(100);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListCar = "Select [Car_ID], [Driver_ID], [Loại xe], [Biển số xe], [Năm sản xuất], [Màu xe], [Quãng đường đi], [Khối lượng tối đa], [Trạng thái xe]"
                    + " From Cars Where Manager_ID = ?";
            PreparedStatement sqlgetListCar = this.loginwindow.db.cnt.prepareStatement(getListCar);
            sqlgetListCar.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListCar.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Car_ID"), rs.getInt("Driver_ID"), rs.getString("Loại xe"), rs.getString("Biển số xe"), 
                rs.getInt("Năm sản xuất"), rs.getString("Màu xe"), rs.getString("Quãng đường đi"), rs.getString("Khối lượng tối đa"), rs.getString("Trạng thái xe")});
            }
            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void setSixthInformation()
    {
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtListCarToEdit.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Car_ID");
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Loại xe");
        defaultTableModel.addColumn("Biển số");
        defaultTableModel.addColumn("Năm");
        defaultTableModel.addColumn("Màu");
        defaultTableModel.addColumn("Quãng đường");
        defaultTableModel.addColumn("Trọng lượng tối đa");
        defaultTableModel.addColumn("Trạng thái");
        
        jtListCarToEdit.getColumnModel().getColumn(0).setPreferredWidth(70);
        jtListCarToEdit.getColumnModel().getColumn(1).setPreferredWidth(60);
        jtListCarToEdit.getColumnModel().getColumn(2).setPreferredWidth(130);
        jtListCarToEdit.getColumnModel().getColumn(3).setPreferredWidth(100);
        jtListCarToEdit.getColumnModel().getColumn(4).setPreferredWidth(50);
        jtListCarToEdit.getColumnModel().getColumn(5).setPreferredWidth(100);
        jtListCarToEdit.getColumnModel().getColumn(6).setPreferredWidth(100);
        jtListCarToEdit.getColumnModel().getColumn(7).setPreferredWidth(120);
        jtListCarToEdit.getColumnModel().getColumn(8).setPreferredWidth(100);
        
        try
        {
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListCar = "Select [Car_ID], [Driver_ID], [Loại xe], [Biển số xe], [Năm sản xuất], [Màu xe], [Quãng đường đi], [Khối lượng tối đa], [Trạng thái xe]"
                    + " From Cars Where Manager_ID = ?";
            PreparedStatement sqlgetListCar = this.loginwindow.db.cnt.prepareStatement(getListCar);
            sqlgetListCar.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListCar.executeQuery();
            
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Car_ID"), rs.getInt("Driver_ID"), rs.getString("Loại xe"), rs.getString("Biển số xe"), 
                rs.getInt("Năm sản xuất"), rs.getString("Màu xe"), rs.getString("Quãng đường đi"), rs.getString("Khối lượng tối đa"), rs.getString("Trạng thái xe")});
            }
            
        }
        catch(SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jladdNewCar = new javax.swing.JLabel();
        jldeleteCar = new javax.swing.JLabel();
        jllistCar = new javax.swing.JLabel();
        js = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jlName = new javax.swing.JLabel();
        jllogOutCarManager = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jlchangePasswordCarManager = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jlShowPersonalInfor = new javax.swing.JLabel();
        jlChangeInformationCar = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPaneAll = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jlAvatarManager = new javax.swing.JLabel();
        jlManagerID = new javax.swing.JLabel();
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
        jSeparator6 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jtfCarName = new javax.swing.JTextField();
        jlCarName = new javax.swing.JLabel();
        jtfCarSign = new javax.swing.JTextField();
        jlCarSign = new javax.swing.JLabel();
        jtfCarYear = new javax.swing.JTextField();
        jlCarYear = new javax.swing.JLabel();
        jtfCarColor = new javax.swing.JTextField();
        jlCarColor = new javax.swing.JLabel();
        jtfCarDistance = new javax.swing.JTextField();
        jlCarDistance = new javax.swing.JLabel();
        jtfCarMaxWeight = new javax.swing.JTextField();
        jlCarMaxWeight = new javax.swing.JLabel();
        jlSuccessAddNewCar = new javax.swing.JLabel();
        jbtnAddNewCar = new javax.swing.JButton();
        jcbOk = new javax.swing.JCheckBox();
        jlCheckOKNewCar = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListCar = new javax.swing.JTable();
        jtfEnterID = new javax.swing.JTextField();
        jbtnDeleteCar = new javax.swing.JButton();
        jlEnterID = new javax.swing.JLabel();
        jlSuccessDeleteCar = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtListCarToShow = new javax.swing.JTable();
        jtfEnterIDSearch = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        jbtnRefresh = new javax.swing.JButton();
        jlEnterIDToShow = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtListCarToEdit = new javax.swing.JTable();
        jtfEnterIDToEdit = new javax.swing.JTextField();
        jlEnterIDToEdit = new javax.swing.JLabel();
        jbtnCarEdit = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jtfEditCarName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfEditCarSign = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jtfEditCarYear = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jtfEditCarColor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jtfEditDistance = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jtfEditWeight = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jcbConfirmEditCar = new javax.swing.JCheckBox();
        jbtnUpdate = new javax.swing.JButton();
        jlSuccessEditCar = new javax.swing.JLabel();
        jlWarningConfirm = new javax.swing.JLabel();
        jlCarID = new javax.swing.JLabel();
        jtfCarID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("QUẢN LÝ XE ");

        jladdNewCar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jladdNewCar.setForeground(new java.awt.Color(0, 102, 255));
        jladdNewCar.setText("Thêm xe ");
        jladdNewCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jladdNewCar.setOpaque(true);
        jladdNewCar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jladdNewCarMouseMoved(evt);
            }
        });
        jladdNewCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jladdNewCarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jladdNewCarMouseExited(evt);
            }
        });

        jldeleteCar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jldeleteCar.setForeground(new java.awt.Color(0, 102, 255));
        jldeleteCar.setText("Xóa xe");
        jldeleteCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jldeleteCar.setOpaque(true);
        jldeleteCar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jldeleteCarMouseMoved(evt);
            }
        });
        jldeleteCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jldeleteCarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jldeleteCarMouseExited(evt);
            }
        });

        jllistCar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jllistCar.setForeground(new java.awt.Color(0, 102, 255));
        jllistCar.setText("Danh sách các xe");
        jllistCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jllistCar.setOpaque(true);
        jllistCar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jllistCarMouseMoved(evt);
            }
        });
        jllistCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jllistCarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jllistCarMouseExited(evt);
            }
        });

        jlName.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jlName.setForeground(new java.awt.Color(51, 153, 255));

        jllogOutCarManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jllogOutCarManager.setForeground(new java.awt.Color(0, 102, 255));
        jllogOutCarManager.setText("Đăng xuất");
        jllogOutCarManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jllogOutCarManager.setOpaque(true);
        jllogOutCarManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jllogOutCarManagerMouseMoved(evt);
            }
        });
        jllogOutCarManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jllogOutCarManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jllogOutCarManagerMouseExited(evt);
            }
        });

        jlchangePasswordCarManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlchangePasswordCarManager.setForeground(new java.awt.Color(0, 102, 255));
        jlchangePasswordCarManager.setText("Đổi mật khẩu");
        jlchangePasswordCarManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlchangePasswordCarManager.setOpaque(true);
        jlchangePasswordCarManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlchangePasswordCarManagerMouseMoved(evt);
            }
        });
        jlchangePasswordCarManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlchangePasswordCarManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlchangePasswordCarManagerMouseExited(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N

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

        jlChangeInformationCar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlChangeInformationCar.setForeground(new java.awt.Color(0, 102, 255));
        jlChangeInformationCar.setText("Sửa thông tin xe");
        jlChangeInformationCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlChangeInformationCar.setOpaque(true);
        jlChangeInformationCar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlChangeInformationCarMouseMoved(evt);
            }
        });
        jlChangeInformationCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlChangeInformationCarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlChangeInformationCarMouseExited(evt);
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
                                .addGap(82, 82, 82)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlShowPersonalInfor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlChangeInformationCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jllogOutCarManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jladdNewCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jldeleteCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jllistCar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlchangePasswordCarManager, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator4)
                        .addComponent(jSeparator5)
                        .addComponent(js))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlShowPersonalInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlchangePasswordCarManager, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jllogOutCarManager, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jladdNewCar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jldeleteCar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlChangeInformationCar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jllistCar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(393, 393, 393)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(98, 98, 98)
                    .addComponent(js, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(278, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 820));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, 30));

        jTabbedPaneAll.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 940, 10));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("THÔNG TIN CÁ NHÂN");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));
        jPanel3.add(jlAvatarManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, 150));

        jlManagerID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(jlManagerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, 30));

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

        jTabbedPaneAll.addTab("tab2", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("Thông tin xe");

        jtfCarName.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarName.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarName.setText("loại xe");
        jtfCarName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarNameFocusLost(evt);
            }
        });

        jlCarName.setForeground(new java.awt.Color(255, 0, 0));

        jtfCarSign.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarSign.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarSign.setText("biển số xe");
        jtfCarSign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarSignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarSignFocusLost(evt);
            }
        });

        jlCarSign.setForeground(new java.awt.Color(255, 0, 0));

        jtfCarYear.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarYear.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarYear.setText("năm sản xuất");
        jtfCarYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarYearFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarYearFocusLost(evt);
            }
        });

        jlCarYear.setForeground(new java.awt.Color(255, 0, 0));

        jtfCarColor.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarColor.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarColor.setText("màu xe");
        jtfCarColor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarColorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarColorFocusLost(evt);
            }
        });

        jlCarColor.setForeground(new java.awt.Color(255, 0, 0));

        jtfCarDistance.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarDistance.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarDistance.setText("quãng đường đã đi");
        jtfCarDistance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarDistanceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarDistanceFocusLost(evt);
            }
        });

        jlCarDistance.setForeground(new java.awt.Color(255, 0, 0));

        jtfCarMaxWeight.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfCarMaxWeight.setForeground(new java.awt.Color(153, 153, 153));
        jtfCarMaxWeight.setText("trọng lượng tối đa cho phép");
        jtfCarMaxWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfCarMaxWeightFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfCarMaxWeightFocusLost(evt);
            }
        });

        jlCarMaxWeight.setForeground(new java.awt.Color(255, 0, 0));

        jlSuccessAddNewCar.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessAddNewCar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessAddNewCar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessAddNewCar.setText("Thành công! Bạn đã thêm xe mới thành công.");
        jlSuccessAddNewCar.setOpaque(true);

        jbtnAddNewCar.setBackground(new java.awt.Color(51, 102, 255));
        jbtnAddNewCar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnAddNewCar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAddNewCar.setText("Thêm xe");
        jbtnAddNewCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnAddNewCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddNewCarActionPerformed(evt);
            }
        });

        jcbOk.setBackground(new java.awt.Color(255, 255, 255));
        jcbOk.setText("Đồng ý");
        jcbOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbOk.setOpaque(true);

        jlCheckOKNewCar.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbOk)
                    .addComponent(jlSuccessAddNewCar, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnAddNewCar)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jlCheckOKNewCar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlCarColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfCarName, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlCarName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfCarSign, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlCarSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfCarYear, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlCarYear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfCarColor, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jtfCarDistance, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jlCarDistance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfCarMaxWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                        .addComponent(jlCarMaxWeight, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfCarName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfCarSign, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarSign, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfCarYear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarYear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfCarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfCarDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfCarMaxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCarMaxWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcbOk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCheckOKNewCar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jbtnAddNewCar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jlSuccessAddNewCar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPaneAll.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtListCar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtListCar.setModel(new javax.swing.table.DefaultTableModel(
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
        jtListCar.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jtListCar);

        jPanel6.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 900, 680));

        jtfEnterID.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfEnterID.setForeground(new java.awt.Color(153, 153, 153));
        jtfEnterID.setText("nhập ID");
        jtfEnterID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEnterIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnterIDFocusLost(evt);
            }
        });
        jPanel6.add(jtfEnterID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 22, 179, 34));

        jbtnDeleteCar.setBackground(new java.awt.Color(51, 102, 255));
        jbtnDeleteCar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnDeleteCar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnDeleteCar.setText("Xóa");
        jbtnDeleteCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnDeleteCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteCarActionPerformed(evt);
            }
        });
        jPanel6.add(jbtnDeleteCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 22, 67, 34));

        jlEnterID.setForeground(new java.awt.Color(255, 0, 0));
        jPanel6.add(jlEnterID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 62, 180, 17));

        jlSuccessDeleteCar.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessDeleteCar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessDeleteCar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessDeleteCar.setText("Thành công! Bạn xóa xe thành công.");
        jlSuccessDeleteCar.setOpaque(true);
        jPanel6.add(jlSuccessDeleteCar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jTabbedPaneAll.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtListCarToShow.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtListCarToShow.setModel(new javax.swing.table.DefaultTableModel(
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
        jtListCarToShow.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jtListCarToShow);

        jPanel7.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 900, 680));

        jtfEnterIDSearch.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfEnterIDSearch.setForeground(new java.awt.Color(153, 153, 153));
        jtfEnterIDSearch.setText("nhập ID");
        jtfEnterIDSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEnterIDSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnterIDSearchFocusLost(evt);
            }
        });
        jPanel7.add(jtfEnterIDSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 160, 30));

        jbtnSearch.setBackground(new java.awt.Color(51, 51, 255));
        jbtnSearch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnSearch.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSearch.setText("Tìm kiếm");
        jbtnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });
        jPanel7.add(jbtnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 100, 30));

        jbtnRefresh.setBackground(new java.awt.Color(51, 51, 255));
        jbtnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jbtnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRefresh.setText("Refresh");
        jbtnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRefreshActionPerformed(evt);
            }
        });
        jPanel7.add(jbtnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 100, 30));

        jlEnterIDToShow.setForeground(new java.awt.Color(255, 0, 0));
        jPanel7.add(jlEnterIDToShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 290, 20));

        jTabbedPaneAll.addTab("tab5", jPanel7);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jtListCarToEdit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtListCarToEdit.setModel(new javax.swing.table.DefaultTableModel(
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
        jtListCarToEdit.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jtListCarToEdit);

        jtfEnterIDToEdit.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfEnterIDToEdit.setForeground(new java.awt.Color(153, 153, 153));
        jtfEnterIDToEdit.setText("nhập ID để sửa");
        jtfEnterIDToEdit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEnterIDToEditFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnterIDToEditFocusLost(evt);
            }
        });

        jlEnterIDToEdit.setForeground(new java.awt.Color(255, 0, 0));

        jbtnCarEdit.setBackground(new java.awt.Color(51, 102, 255));
        jbtnCarEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnCarEdit.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCarEdit.setText("Sửa");
        jbtnCarEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnCarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCarEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlEnterIDToEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jtfEnterIDToEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jbtnCarEdit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfEnterIDToEdit)
                    .addComponent(jbtnCarEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlEnterIDToEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneAll.addTab("tab6", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 0, 0));
        jLabel16.setText("THÔNG TIN XE");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Loại xe: ");

        jtfEditCarName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Biển số: ");

        jtfEditCarSign.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Năm sản xuất:");

        jtfEditCarYear.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Màu xe:");

        jtfEditCarColor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("Quãng đường đi:");

        jtfEditDistance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Tải trọng:");

        jtfEditWeight.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 0, 0));
        jLabel26.setText("Xác nhận");

        jcbConfirmEditCar.setBackground(new java.awt.Color(255, 255, 255));
        jcbConfirmEditCar.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jcbConfirmEditCar.setText("Xác nhận");
        jcbConfirmEditCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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

        jlSuccessEditCar.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessEditCar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessEditCar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessEditCar.setText("Thành công! Bạn đã cập nhật xe thành công.");
        jlSuccessEditCar.setOpaque(true);

        jlWarningConfirm.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jlWarningConfirm.setForeground(new java.awt.Color(255, 0, 51));
        jlWarningConfirm.setToolTipText("");

        jlCarID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID.setText("Car_ID:");

        jtfCarID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfCarID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10)
                            .addComponent(jlSuccessEditCar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jcbConfirmEditCar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlWarningConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel21)
                                            .addComponent(jlCarID))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfEditCarName)
                                    .addComponent(jtfEditCarSign)
                                    .addComponent(jtfEditCarYear)
                                    .addComponent(jtfEditCarColor)
                                    .addComponent(jtfEditDistance)
                                    .addComponent(jtfEditWeight, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                                    .addComponent(jtfCarID)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnUpdate)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCarID)
                    .addComponent(jtfCarID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfEditCarName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jtfEditCarSign, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jtfEditCarYear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jtfEditCarColor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfEditDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel24)))
                .addGap(39, 39, 39)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfEditWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(28, 28, 28)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlWarningConfirm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbConfirmEditCar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlSuccessEditCar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPaneAll.addTab("tab7", jPanel10);

        jPanel1.add(jTabbedPaneAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, -4, 920, 820));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            jlConfirm.setText("");
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

    private void jlShowPersonalInforMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(0);
    }//GEN-LAST:event_jlShowPersonalInforMouseClicked

    private void jlShowPersonalInforMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseExited
        // TODO add your handling code here:
        jlShowPersonalInfor.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShowPersonalInforMouseExited

    private void jlShowPersonalInforMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShowPersonalInforMouseMoved
        // TODO add your handling code here:
        jlShowPersonalInfor.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlShowPersonalInforMouseMoved

    private void jlchangePasswordCarManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordCarManagerMouseClicked
        // TODO add your handling code here:
        
        // thiet lap place holder and xoa label 
        // xóa cac label
        jlnotifyOldPassword.setText("");
        jlRequest.setText("");
        jlConfirm.setText("");
        jlCheckOK.setText("");
        jlSuccess.setVisible(false);
        jTabbedPaneAll.setSelectedIndex(1);       
    }//GEN-LAST:event_jlchangePasswordCarManagerMouseClicked

    private void jlchangePasswordCarManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordCarManagerMouseExited
        // TODO add your handling code here:
        jlchangePasswordCarManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlchangePasswordCarManagerMouseExited

    private void jlchangePasswordCarManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordCarManagerMouseMoved
        // TODO add your handling code here:
        jlchangePasswordCarManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlchangePasswordCarManagerMouseMoved

    private void jllogOutCarManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutCarManagerMouseClicked
        // TODO add your handling code here:
        this.loginwindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jllogOutCarManagerMouseClicked

    private void jllogOutCarManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutCarManagerMouseExited
        // TODO add your handling code here:
        jllogOutCarManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jllogOutCarManagerMouseExited

    private void jllogOutCarManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutCarManagerMouseMoved
        // TODO add your handling code here:
        jllogOutCarManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jllogOutCarManagerMouseMoved

    private void jladdNewCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdNewCarMouseClicked
        // TODO add your handling code here:
        jlCarName.setText("");
        jlCarSign.setText("");
        jlCarYear.setText("");
        jlCarColor.setText("");
        jlCarDistance.setText("");
        jlCarMaxWeight.setText("");
        jlCheckOKNewCar.setText("");
        
        jTabbedPaneAll.setSelectedIndex(2);
    }//GEN-LAST:event_jladdNewCarMouseClicked

    private void jladdNewCarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdNewCarMouseExited
        // TODO add your handling code here:
        jladdNewCar.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jladdNewCarMouseExited

    private void jladdNewCarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdNewCarMouseMoved
        // TODO add your handling code here:
        jladdNewCar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jladdNewCarMouseMoved

    private void jldeleteCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteCarMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(3);
    }//GEN-LAST:event_jldeleteCarMouseClicked

    private void jldeleteCarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteCarMouseExited
        // TODO add your handling code here:
        jldeleteCar.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jldeleteCarMouseExited

    private void jldeleteCarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteCarMouseMoved
        // TODO add your handling code here:
        jldeleteCar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jldeleteCarMouseMoved

    private void jllistCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllistCarMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(4);
    }//GEN-LAST:event_jllistCarMouseClicked

    private void jllistCarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllistCarMouseExited
        // TODO add your handling code here:
        jllistCar.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jllistCarMouseExited

    private void jllistCarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllistCarMouseMoved
        // TODO add your handling code here:
        jllistCar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jllistCarMouseMoved

    private void jtfCarNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarNameFocusGained
        // TODO add your handling code here:
        if (jtfCarName.getText().equals("loại xe")) 
        {
            jtfCarName.setText("");
            jlCarName.setText("");
            jtfCarName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarName.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarNameFocusGained

    private void jtfCarNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarNameFocusLost
        // TODO add your handling code here:
        if (jtfCarName.getText().equals("")) 
        {
            jtfCarName.setText("loại xe");
            jtfCarName.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarName.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarNameFocusLost

    private void jtfCarSignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarSignFocusGained
        // TODO add your handling code here:
        if (jtfCarSign.getText().equals("biển số xe")) 
        {
            jtfCarSign.setText("");
            jlCarSign.setText("");
            jlCarSign.setText("");
            jtfCarSign.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarSign.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarSignFocusGained

    private void jtfCarSignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarSignFocusLost
        // TODO add your handling code here:
        if (jtfCarSign.getText().equals("")) 
        {
            jtfCarSign.setText("biển số xe");
            jtfCarSign.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarSign.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarSignFocusLost

    private void jtfCarYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarYearFocusGained
        // TODO add your handling code here:
        if (jtfCarYear.getText().equals("năm sản xuất")) 
        {
            jtfCarYear.setText("");
            jlCarYear.setText("");
            jlCarYear.setText("");
            jtfCarYear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarYear.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarYearFocusGained

    private void jtfCarYearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarYearFocusLost
        // TODO add your handling code here:
        if (jtfCarYear.getText().equals("")) 
        {
            jtfCarYear.setText("năm sản xuất");
            jtfCarYear.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarYear.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarYearFocusLost

    private void jtfCarColorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarColorFocusGained
        // TODO add your handling code here:
        if (jtfCarColor.getText().equals("màu xe")) 
        {
            jtfCarColor.setText("");
            jlCarColor.setText("");
            jlCarColor.setText("");
            jtfCarColor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarColor.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarColorFocusGained

    private void jtfCarColorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarColorFocusLost
        // TODO add your handling code here:
        if (jtfCarColor.getText().equals("")) 
        {
            jtfCarColor.setText("màu xe");
            jtfCarColor.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarColor.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarColorFocusLost

    private void jtfCarDistanceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarDistanceFocusGained
        // TODO add your handling code here:
        if (jtfCarDistance.getText().equals("quãng đường đã đi")) 
        {
            jtfCarDistance.setText("");
            jlCarDistance.setText("");
            jlCarDistance.setText("");
            jtfCarDistance.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarDistance.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarDistanceFocusGained

    private void jtfCarDistanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarDistanceFocusLost
        // TODO add your handling code here:
        if (jtfCarDistance.getText().equals("")) 
        {
            jtfCarDistance.setText("quãng đường đã đi");
            jtfCarDistance.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarDistance.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarDistanceFocusLost

    private void jtfCarMaxWeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarMaxWeightFocusGained
        // TODO add your handling code here:
        if (jtfCarMaxWeight.getText().equals("trọng lượng tối đa cho phép")) 
        {
            jtfCarMaxWeight.setText("");
            jlCarMaxWeight.setText("");
            jlCarMaxWeight.setText("");
            jtfCarMaxWeight.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfCarMaxWeight.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfCarMaxWeightFocusGained

    private void jtfCarMaxWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfCarMaxWeightFocusLost
        // TODO add your handling code here:
        if (jtfCarMaxWeight.getText().equals("")) 
        {
            jtfCarMaxWeight.setText("trọng lượng tối đa cho phép");
            jtfCarMaxWeight.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfCarMaxWeight.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfCarMaxWeightFocusLost

    private void jbtnAddNewCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddNewCarActionPerformed
        // TODO add your handling code here:
        jlSuccessAddNewCar.setVisible(false);
        
        jlCarName.setText("");
        jlCarSign.setText("");
        jlCarYear.setText("");
        jlCarColor.setText("");
        jlCarDistance.setText("");
        jlCarMaxWeight.setText("");
        jlCheckOKNewCar.setText("");
        
        String carName = jtfCarName.getText();
        String carSign = jtfCarSign.getText();
        String carYear = jtfCarYear.getText();
        String carColor = jtfCarColor.getText();
        String carDistance = jtfCarDistance.getText();
        String carMaxWeight = jtfCarMaxWeight.getText();
        boolean checkOK = jcbOk.isSelected();
        
        if((carName.equals("loại xe")) || (carSign.equals("biển số xe")) || (carYear.equals("năm sản xuất")) || (carColor.equals("màu xe")) || (carDistance.equals("quãng đường đã đi")) || (carMaxWeight.equals("trọng lượng tối đa cho phép")))
        {
            if(carName.equals("loại xe"))
            {
                jlCarName.setText("nhập loại xe");
            }
            
            if(carSign.equals("biển số xe"))
            {
                jlCarSign.setText("nhập biển số xe");
            }
            
            if(carYear.equals("năm sản xuất"))
            {
                jlCarYear.setText("nhập năm sản xuất");
            }
            
            if(carColor.equals("màu xe"))
            {
                jlCarColor.setText("nhập màu xe");
            }
            
            if(carDistance.equals("quãng đường đã đi"))
            {
                jlCarDistance.setText("nhập quãng đường đã đi");
            }
            
            if(carMaxWeight.equals("trọng lượng tối đa cho phép"))
            {
                jlCarMaxWeight.setText("nhập trọng lượng tối đa cho phép");
            }
        }
        else
        {
            if(checkOK)
            {
                try
                {
                    boolean checkExistCarSign = false;
                    String checkCarSign = "Select [Biển số xe] From Cars";

                    // khởi tại thể hiện của Statement để thực hiện truy vân với cơ sở dữ liệu
                    Statement st = this.loginwindow.db.cnt.createStatement();

                    // Đọc dữ liệu từ database
                    ResultSet rs = st.executeQuery(checkCarSign);

                    while(rs.next())
                    {
                        String dbCarSign = rs.getString("Biển số xe");
                        if(carSign.equals(dbCarSign))
                        {
                            checkExistCarSign = true;
                            break;
                        }
                    }
                    if(checkExistCarSign)
                    {
                        jlCarSign.setText("biển số xe đã tồn tại");
                    }
                    else
                    {
                        // thực hiện lấy dữ liệu để in ra panel
                        String InsertNewCar = "Insert into Cars(Manager_ID, [Loại xe], [Biển số xe], [Năm sản xuất], [Màu xe], [Quãng đường đi], [Khối lượng tối đa], [Trạng thái xe])"
                                + " values(?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement sqlInsertNewCar = this.loginwindow.db.cnt.prepareStatement(InsertNewCar);

                        sqlInsertNewCar.setInt(1, this.managerID);
                        sqlInsertNewCar.setString(2, carName);
                        sqlInsertNewCar.setString(3, carSign);
                        sqlInsertNewCar.setInt(4, Integer.parseInt(carYear));
                        sqlInsertNewCar.setString(5, carColor);
                        sqlInsertNewCar.setInt(6, Integer.parseInt(carDistance));
                        sqlInsertNewCar.setInt(7, Integer.parseInt(carMaxWeight));
                        sqlInsertNewCar.setString(8, "Đang trong kho");

                        // lấy bảng dữ liệu
                        sqlInsertNewCar.executeUpdate();
                        
                        // set place holder cho ten xe
                        jtfCarName.setText("loại xe");
                        jtfCarName.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarName.setForeground(new Color(153, 153, 153));
                        
                        // set place holder cho bien so
                        jtfCarSign.setText("biển số xe");
                        jtfCarSign.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarSign.setForeground(new Color(153, 153, 153));
                        
                        // set place holder cho nam san xuat
                        jtfCarYear.setText("năm sản xuất");
                        jtfCarYear.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarYear.setForeground(new Color(153, 153, 153));

                        // set place holder cho mau xe
                        jtfCarColor.setText("màu xe");
                        jtfCarColor.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarColor.setForeground(new Color(153, 153, 153));
                        
                        // set place holder cho quang duong da di 
                        jtfCarDistance.setText("quãng đường đã đi");
                        jtfCarDistance.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarDistance.setForeground(new Color(153, 153, 153));
                        
                        // set place holder cho trọng lượng tối đa
                        jtfCarMaxWeight.setText("trọng lượng tối đa cho phép");
                        jtfCarMaxWeight.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfCarMaxWeight.setForeground(new Color(153, 153, 153));                    
                        
                        setForthInformation();
                        setFifthInformation();
                        setSixthInformation();
                        
                        jlSuccessAddNewCar.setVisible(true);
                        
                    }
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(loginWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                jlCheckOKNewCar.setText("bạn cần xác nhận");
            }
        }     
    }//GEN-LAST:event_jbtnAddNewCarActionPerformed

    private void jtfEnterIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDFocusGained
        // TODO add your handling code here:
        if (jtfEnterID.getText().equals("nhập ID")) 
        {
            jtfEnterID.setText("");
            jlEnterID.setText("");
            jtfEnterID.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfEnterID.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfEnterIDFocusGained

    private void jtfEnterIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDFocusLost
        // TODO add your handling code here:
        if (jtfEnterID.getText().equals("")) 
        {
            jtfEnterID.setText("nhập ID");
            jtfEnterID.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterID.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDFocusLost

    private void jbtnDeleteCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteCarActionPerformed
        // TODO add your handling code here:
        jlSuccessDeleteCar.setVisible(false);
        jlEnterID.setText("");
        
        String stringCarID = jtfEnterID.getText();
        if(stringCarID.equals("nhập ID"))
        {
            jlEnterID.setText("không được bỏ trống");
        }
        else
        {
            int intCarID  = Integer.parseInt(stringCarID);
            int driverID = -1;
            boolean checkExistCarID = false;
            try
            {
                // thực hiện quét
                String sweepCarList = "Select Car_ID, Driver_ID From Cars";

                // tạo thể hiện để truy vấn
                PreparedStatement sqlweepCarList = this.loginwindow.db.cnt.prepareStatement(sweepCarList);

                // lấy bảng dữ liệu
                ResultSet rs = sqlweepCarList.executeQuery();
                
                while(rs.next())
                {
                    if(intCarID == rs.getInt("Car_ID"))
                    {
                        checkExistCarID = true;
                        driverID = rs.getInt("Driver_ID");
                        break;
                    }
                }
                
                // kiểm tra cờ check
                if(checkExistCarID)
                {
                    if(driverID == 0)
                    {
                        // khởi tạo câu lệnh truy vấn xóa 
                        String sweepDeleteCar = "Delete From Cars Where Car_ID = ?";

                        // tạo thể hiện để truy vấn
                        PreparedStatement sqlsweepDeleteCar = this.loginwindow.db.cnt.prepareStatement(sweepDeleteCar);

                        sqlsweepDeleteCar.setInt(1, intCarID);
                        
                        // lấy bảng dữ liệu
                        sqlsweepDeleteCar.executeUpdate();
                        
                        // thiet lap lai place holder
                        jtfEnterID.setText("nhập ID");
                        jtfEnterID.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfEnterID.setForeground(new Color(153, 153, 153));

                        setForthInformation();
                        setFifthInformation();
                        setSixthInformation();
                        
                        jlSuccessDeleteCar.setVisible(true);
                        
                    }
                    else
                    {
                        jlEnterID.setText("không thể xóa khi xe đang được sử dụng");
                        
                        // thiet lap lai place holder
                        jtfEnterID.setText("nhập ID");
                        jtfEnterID.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfEnterID.setForeground(new Color(153, 153, 153));
                    }
                }
                else
                {
                    jlEnterID.setText("ID không tồn tại");
                    
                    // thiet lap lai place holder
                    jtfEnterID.setText("nhập ID");
                    jtfEnterID.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfEnterID.setForeground(new Color(153, 153, 153));
                }
                
            }
            catch(SQLException ex)
            {
                Logger.getLogger(loginWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnDeleteCarActionPerformed

    
    public void searchCar(int carID)
    {       
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String searchCarList = "Select [Car_ID] From Cars";
            
            // tạo thể hiện
            PreparedStatement sqlsearchCar = this.loginwindow.db.cnt.prepareStatement(searchCarList);

            // thực hiện truy vấn
            ResultSet rs = sqlsearchCar.executeQuery();
            
            boolean checkExistCarID = false;
            
            while(rs.next())
            {
                if(carID == rs.getInt("Car_ID"))
                {
                    checkExistCarID = true;
                    break;
                }
            }
            if(checkExistCarID)
            {
                // tạo thể hiện cho defaulttablemodol
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                jtListCarToShow.setModel(defaultTableModel);

                // thêm các cột cần thiết
                defaultTableModel.addColumn("Car_ID");
                defaultTableModel.addColumn("Driver_ID");
                defaultTableModel.addColumn("Loại xe");
                defaultTableModel.addColumn("Biển số");
                defaultTableModel.addColumn("Năm");
                defaultTableModel.addColumn("Màu");
                defaultTableModel.addColumn("Quãng đường");
                defaultTableModel.addColumn("Trọng lượng tối đa");
                defaultTableModel.addColumn("Trạng thái");

                jtListCarToShow.getColumnModel().getColumn(0).setPreferredWidth(70);
                jtListCarToShow.getColumnModel().getColumn(1).setPreferredWidth(60);
                jtListCarToShow.getColumnModel().getColumn(2).setPreferredWidth(130);
                jtListCarToShow.getColumnModel().getColumn(3).setPreferredWidth(100);
                jtListCarToShow.getColumnModel().getColumn(4).setPreferredWidth(50);
                jtListCarToShow.getColumnModel().getColumn(5).setPreferredWidth(100);
                jtListCarToShow.getColumnModel().getColumn(6).setPreferredWidth(100);
                jtListCarToShow.getColumnModel().getColumn(7).setPreferredWidth(120);
                jtListCarToShow.getColumnModel().getColumn(8).setPreferredWidth(100);
                
                // tạo câu truy vấn tìm kiếm
                String searchCarRequire = "Select * From Cars Where Car_ID = ?";
                
                // thể hiện
                sqlsearchCar = this.loginwindow.db.cnt.prepareStatement(searchCarRequire);
                
                sqlsearchCar.setInt(1, carID);
                
                ResultSet rsSearch = sqlsearchCar.executeQuery();
                
                while(rsSearch.next())
                {
                    defaultTableModel.addRow(new Object[]{rsSearch.getInt("Car_ID"), 0, rsSearch.getString("Loại xe"), rsSearch.getString("Biển số xe"), 
                    rsSearch.getInt("Năm sản xuất"), rsSearch.getString("Màu xe"), rsSearch.getString("Quãng đường đi"), rsSearch.getString("Khối lượng tối đa"), rsSearch.getString("Trạng thái xe")});
                }
                jtfEnterIDSearch.enable(false);
            }
            else
            {
                jlEnterIDToShow.setText("không tồn tại ID");
                
                // thiet lap lai place holder
                jtfEnterIDSearch.setText("nhập ID");
                jtfEnterIDSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                jtfEnterIDSearch.setForeground(new Color(153, 153, 153));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void jtfEnterIDSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDSearchFocusGained
        // TODO add your handling code here:
        if (jtfEnterIDSearch.getText().equals("nhập ID")) 
        {
            jtfEnterIDSearch.setText("");
            jlEnterIDToShow.setText("");
            jtfEnterIDSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfEnterIDSearch.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfEnterIDSearchFocusGained

    private void jtfEnterIDSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDSearchFocusLost
        // TODO add your handling code here:
        if (jtfEnterIDSearch.getText().equals("")) 
        {
            jtfEnterIDSearch.setText("nhập ID");
            jtfEnterIDSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDSearch.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDSearchFocusLost

    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
        // TODO add your handling code here:
        
        // delete all labels
        jlEnterIDToShow.setText("");
     
        // lấy thông tin ở jtextfield
        String stringcarID = jtfEnterIDSearch.getText();
        
        if(stringcarID.equals("nhập ID"))
        {
            jlEnterIDToShow.setText("yêu cầu nhập ID");
        }
        else
        {
            // ép string sang integer
            int intcarID = Integer.parseInt(stringcarID);

            // tìm kiếm
            searchCar(intcarID);
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRefreshActionPerformed
        // TODO add your handling code here:
        setFifthInformation();
        jlEnterIDToShow.setText("");
        jtfEnterIDSearch.enable(true);
        
        // thiet lap lai place holder
        jtfEnterIDSearch.setText("nhập ID");
        jtfEnterIDSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        jtfEnterIDSearch.setForeground(new Color(153, 153, 153));
    }//GEN-LAST:event_jbtnRefreshActionPerformed

    private void jlChangeInformationCarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeInformationCarMouseMoved
        // TODO add your handling code here:
        jlChangeInformationCar.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlChangeInformationCarMouseMoved

    private void jlChangeInformationCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeInformationCarMouseClicked
        // TODO add your handling code here:
        
        jlEnterIDToEdit.setText("");
        jTabbedPaneAll.setSelectedIndex(5);
    }//GEN-LAST:event_jlChangeInformationCarMouseClicked

    private void jlChangeInformationCarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeInformationCarMouseExited
        // TODO add your handling code here:
        jlChangeInformationCar.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlChangeInformationCarMouseExited

    private void jtfEnterIDToEditFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToEditFocusGained
        // TODO add your handling code here:
        if (jtfEnterIDToEdit.getText().equals("nhập ID để sửa")) 
        {
            jtfEnterIDToEdit.setText("");
            jlEnterIDToEdit.setText("");
            jtfEnterIDToEdit.setText("");
            jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfEnterIDToEdit.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfEnterIDToEditFocusGained

    private void jtfEnterIDToEditFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToEditFocusLost
        // TODO add your handling code here:
        if (jtfEnterIDToEdit.getText().equals("")) 
        {
            jtfEnterIDToEdit.setText("nhập ID để sửa");
            jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDToEditFocusLost

    
    public boolean checkExistCar(int idCar)
    {
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String searchCarList = "Select [Car_ID] From Cars";
            
            // tạo thể hiện
            PreparedStatement sqlsearchCar = this.loginwindow.db.cnt.prepareStatement(searchCarList);

            // thực hiện truy vấn
            ResultSet rs = sqlsearchCar.executeQuery();
            
            boolean checkExistCarID = false;
            
            while(rs.next())
            {
                if(idCar == rs.getInt("Car_ID"))
                {
                    checkExistCarID = true;
                    break;
                }
            }
            return checkExistCarID;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public void editCar(int idCar)
    {
        jcbConfirmEditCar.setSelected(false);
        jlSuccessEditCar.setVisible(false);
        jlWarningConfirm.setText("");
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String getInforSingleCar = "Select [Loại xe], [Biển số xe], [Năm sản xuất], [Màu xe], [Quãng đường đi], [Khối lượng tối đa] From Cars Where Car_ID = ?";
            
            // tạo thể hiện
            PreparedStatement sqlgetInforSingleCar = this.loginwindow.db.cnt.prepareStatement(getInforSingleCar);

            sqlgetInforSingleCar.setInt(1, idCar);
            
            // thực hiện truy vấn
            ResultSet rs = sqlgetInforSingleCar.executeQuery();
           
            while(rs.next())
            {
                jtfCarID.setText(String.valueOf(idCar));
                jtfCarID.enable(false);
                jtfEditCarName.setText(rs.getString("Loại xe"));
                jtfEditCarSign.setText(rs.getString("Biển số xe"));
                jtfEditCarYear.setText(rs.getString("Năm sản xuất"));
                jtfEditCarColor.setText(rs.getString("Màu xe"));
                jtfEditDistance.setText(rs.getString("Quãng đường đi"));
                jtfEditWeight.setText(rs.getString("Khối lượng tối đa"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jbtnCarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCarEditActionPerformed
        // TODO add your handling code here:
        String stringidCar = jtfEnterIDToEdit.getText();
        if(stringidCar.equals("nhập ID để sửa"))
        {
            jlEnterIDToEdit.setText("yêu cầu nhập ID");
        }
        else
        {      
            jtfEnterIDToEdit.setText("nhập ID để sửa");
            jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));
            
            int intidCar = Integer.parseInt(stringidCar);
            if(checkExistCar(intidCar))
            {
                editCar(intidCar);
                jTabbedPaneAll.setSelectedIndex(6);
            }
            else
            {
                jlEnterIDToEdit.setText("không tồn tại ID");

                jtfEnterIDToEdit.setText("nhập ID để sửa");
                jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));
            }
        }
    }//GEN-LAST:event_jbtnCarEditActionPerformed

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        // TODO add your handling code here:
        jlWarningConfirm.setText("");
        if(!jcbConfirmEditCar.isSelected())
        {
            jlWarningConfirm.setText("bắt buộc");
        }
        else
        {
            int carID = Integer.parseInt(jtfCarID.getText());
            String loaiXe = jtfEditCarName.getText();
            String bienSoXe = jtfEditCarSign.getText();
            int namSanXuat = Integer.parseInt(jtfEditCarYear.getText());
            String mauXe = jtfEditCarColor.getText();
            int quangDuongDi = Integer.parseInt(jtfEditDistance.getText());
            int khoiLuongToiDa = Integer.parseInt(jtfEditWeight.getText());
            
            try
            {
                // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
                String editCar = "Update Cars "
                        + "Set [Loại xe] = ?, [Biển số xe] = ?, [Năm sản xuất] = ?, [Màu xe] = ?, [Quãng đường đi] = ?, [Khối lượng tối đa] = ? "
                        + " Where Car_ID = ?";

                // tạo thể hiện
                PreparedStatement sqleditCar = this.loginwindow.db.cnt.prepareStatement(editCar);

                sqleditCar.setString(1, loaiXe);
                sqleditCar.setString(2, bienSoXe);
                sqleditCar.setInt(3, namSanXuat);
                sqleditCar.setString(4, mauXe);
                sqleditCar.setInt(5, quangDuongDi);
                sqleditCar.setInt(6, khoiLuongToiDa);
                sqleditCar.setInt(7, carID);
                        
                // thực hiện truy vấn
                sqleditCar.executeUpdate();
                
                // cập nhật lại bảng 
                setForthInformation();
                setFifthInformation();
                setSixthInformation();
                
                jlSuccessEditCar.setVisible(true);
                

            }
            catch (SQLException ex)
            {
                Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    
    
    

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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPaneAll;
    private javax.swing.JButton jbtnAddNewCar;
    private javax.swing.JButton jbtnCarEdit;
    private javax.swing.JButton jbtnDeleteCar;
    private javax.swing.JButton jbtnRefresh;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JCheckBox jcbConfirmEditCar;
    private javax.swing.JCheckBox jcbOk;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarManager;
    private javax.swing.JLabel jlCarColor;
    private javax.swing.JLabel jlCarDistance;
    private javax.swing.JLabel jlCarID;
    private javax.swing.JLabel jlCarMaxWeight;
    private javax.swing.JLabel jlCarName;
    private javax.swing.JLabel jlCarSign;
    private javax.swing.JLabel jlCarYear;
    private javax.swing.JLabel jlChangeInformationCar;
    private javax.swing.JLabel jlCheckOK;
    private javax.swing.JLabel jlCheckOKNewCar;
    private javax.swing.JLabel jlConfirm;
    private javax.swing.JLabel jlDayOfBirth;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEnterID;
    private javax.swing.JLabel jlEnterIDToEdit;
    private javax.swing.JLabel jlEnterIDToShow;
    private javax.swing.JLabel jlFullName;
    private javax.swing.JLabel jlIdentityNumber;
    private javax.swing.JLabel jlManagerID;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlRequest;
    private javax.swing.JLabel jlRole;
    private javax.swing.JLabel jlSex;
    private javax.swing.JLabel jlShowName;
    private javax.swing.JLabel jlShowPersonalInfor;
    private javax.swing.JLabel jlSuccess;
    private javax.swing.JLabel jlSuccessAddNewCar;
    private javax.swing.JLabel jlSuccessDeleteCar;
    private javax.swing.JLabel jlSuccessEditCar;
    private javax.swing.JLabel jlWarningConfirm;
    private javax.swing.JLabel jladdNewCar;
    private javax.swing.JLabel jlchangePasswordCarManager;
    private javax.swing.JLabel jldeleteCar;
    private javax.swing.JLabel jllistCar;
    private javax.swing.JLabel jllogOutCarManager;
    private javax.swing.JLabel jlnotifyOldPassword;
    private javax.swing.JPasswordField jpwfConfirmPassword;
    private javax.swing.JPasswordField jpwfNewPassword;
    private javax.swing.JPasswordField jpwfOldPassword;
    private javax.swing.JSeparator js;
    private javax.swing.JTable jtListCar;
    private javax.swing.JTable jtListCarToEdit;
    private javax.swing.JTable jtListCarToShow;
    private javax.swing.JTextField jtfCarColor;
    private javax.swing.JTextField jtfCarDistance;
    private javax.swing.JTextField jtfCarID;
    private javax.swing.JTextField jtfCarMaxWeight;
    private javax.swing.JTextField jtfCarName;
    private javax.swing.JTextField jtfCarSign;
    private javax.swing.JTextField jtfCarYear;
    private javax.swing.JTextField jtfEditCarColor;
    private javax.swing.JTextField jtfEditCarName;
    private javax.swing.JTextField jtfEditCarSign;
    private javax.swing.JTextField jtfEditCarYear;
    private javax.swing.JTextField jtfEditDistance;
    private javax.swing.JTextField jtfEditWeight;
    private javax.swing.JTextField jtfEnterID;
    private javax.swing.JTextField jtfEnterIDSearch;
    private javax.swing.JTextField jtfEnterIDToEdit;
    // End of variables declaration//GEN-END:variables
}
