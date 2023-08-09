package manageInformation;

import java.sql.Date;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
public class DriverManager extends javax.swing.JFrame {
    
    public loginWindow loginwindow;
    public int userID;
    public int managerID;
    public String nameManager;
    public String username;
    public String password;
    /**
     * Creates new form DriverManager
     */
    public DriverManager(int userID, loginWindow loginwindow) {
        this.userID = userID;
        this.loginwindow = loginwindow;
        initComponents();
        
        getManagerID();
        setLabelGen();
        setEchor();
        setFirstInformation();
        setSecondInformation();
        setFifthInformation();
        setForthInformation();
        setSeventhInformation();
    }
    
    public void setEchor()
    {
        jpwfOldPassword.setEchoChar((char)0);
        jpwfNewPassword.setEchoChar((char)0);
        jpwfConfirmPassword.setEchoChar((char)0);
        jpwfPasswordAddNewDriver.setEchoChar((char)0);
        jpwfRePassword.setEchoChar((char)0);
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

    
    public void setForthInformation()
    {
        // an label success
        jlSuccessDeleteDriver.setVisible(false);
        
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtListDriverToDelete.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("Số giấy phép");
        defaultTableModel.addColumn("Hạng");
        defaultTableModel.addColumn("Lương");
        defaultTableModel.addColumn("Đánh giá");
        defaultTableModel.addColumn("Trạng thái");
        
        jtListDriverToDelete.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtListDriverToDelete.getColumnModel().getColumn(1).setPreferredWidth(130);
        jtListDriverToDelete.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtListDriverToDelete.getColumnModel().getColumn(3).setPreferredWidth(50);
        jtListDriverToDelete.getColumnModel().getColumn(4).setPreferredWidth(70);
        jtListDriverToDelete.getColumnModel().getColumn(5).setPreferredWidth(70);
        jtListDriverToDelete.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        
        try
        {
            String getUserIDs = "Select [User_ID] From Drivers Where Manager_ID = ?";

            try (PreparedStatement sqlgetUserIDs = this.loginwindow.db.cnt.prepareStatement(getUserIDs)) 
            {
                sqlgetUserIDs.setInt(1, this.managerID);
                ResultSet rsUserIDs = sqlgetUserIDs.executeQuery();
                while(rsUserIDs.next())
                {
                    userIDs.add(rsUserIDs.getInt("User_ID"));
                }
            }      
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            for(int i = 0; i < userIDs.size(); i++)
            {
                String getNameIDs = "Select [Họ và tên] From [User] Where User_ID = ?";
                try (PreparedStatement sqlgetNameIDs = this.loginwindow.db.cnt.prepareStatement(getNameIDs)) 
                {
                    sqlgetNameIDs.setInt(1, userIDs.get(i));
                    ResultSet rsNameID = sqlgetNameIDs.executeQuery();
                    while(rsNameID.next())
                    {
                        names.add(rsNameID.getString("Họ và tên"));
                    }
                }      
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {   
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListID = "Select [Driver_ID], [Số giấy phép], [Hạng], [Lương], [Đánh giá], [Trạng thái]"
                    + " From Drivers Where Manager_ID = ?";
            PreparedStatement sqlgetListID = this.loginwindow.db.cnt.prepareStatement(getListID);
            sqlgetListID.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListID.executeQuery();
            int i = 0;
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Driver_ID"), names.get(i), rs.getString("Số giấy phép"), rs.getString("Hạng"), 
                rs.getInt("Lương"), rs.getString("Đánh giá"), rs.getString("Trạng thái")});
                i++;
            }        
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    public void setFifthInformation()
    {
                // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtlistDriverToShow.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("Số giấy phép");
        defaultTableModel.addColumn("Hạng");
        defaultTableModel.addColumn("Lương");
        defaultTableModel.addColumn("Đánh giá");
        defaultTableModel.addColumn("Trạng thái");
        
        jtlistDriverToShow.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtlistDriverToShow.getColumnModel().getColumn(1).setPreferredWidth(130);
        jtlistDriverToShow.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtlistDriverToShow.getColumnModel().getColumn(3).setPreferredWidth(50);
        jtlistDriverToShow.getColumnModel().getColumn(4).setPreferredWidth(70);
        jtlistDriverToShow.getColumnModel().getColumn(5).setPreferredWidth(70);
        jtlistDriverToShow.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        
        try
        {
            String getUserIDs = "Select [User_ID] From Drivers Where Manager_ID = ?";

            try (PreparedStatement sqlgetUserIDs = this.loginwindow.db.cnt.prepareStatement(getUserIDs)) 
            {
                sqlgetUserIDs.setInt(1, this.managerID);
                ResultSet rsUserIDs = sqlgetUserIDs.executeQuery();
                while(rsUserIDs.next())
                {
                    userIDs.add(rsUserIDs.getInt("User_ID"));
                }
            }      
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            for(int i = 0; i < userIDs.size(); i++)
            {
                String getNameIDs = "Select [Họ và tên] From [User] Where User_ID = ?";
                try (PreparedStatement sqlgetNameIDs = this.loginwindow.db.cnt.prepareStatement(getNameIDs)) 
                {
                    sqlgetNameIDs.setInt(1, userIDs.get(i));
                    ResultSet rsNameID = sqlgetNameIDs.executeQuery();
                    while(rsNameID.next())
                    {
                        names.add(rsNameID.getString("Họ và tên"));
                    }
                }      
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {   
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListID = "Select [Driver_ID], [Số giấy phép], [Hạng], [Lương], [Đánh giá], [Trạng thái]"
                    + " From Drivers Where Manager_ID = ?";
            PreparedStatement sqlgetListID = this.loginwindow.db.cnt.prepareStatement(getListID);
            sqlgetListID.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListID.executeQuery();
            int i = 0;
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Driver_ID"), names.get(i), rs.getString("Số giấy phép"), rs.getString("Hạng"), 
                rs.getInt("Lương"), rs.getString("Đánh giá"), rs.getString("Trạng thái")});
                i++;
            }        
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSeventhInformation()
    {
        
        // tạo thể hiện cho defaulttablemodol
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        jtListDriverToEdit.setModel(defaultTableModel);
        
        // thêm các cột cần thiết
        defaultTableModel.addColumn("Driver_ID");
        defaultTableModel.addColumn("Họ và tên");
        defaultTableModel.addColumn("Số giấy phép");
        defaultTableModel.addColumn("Hạng");
        defaultTableModel.addColumn("Lương");
        defaultTableModel.addColumn("Đánh giá");
        defaultTableModel.addColumn("Trạng thái");
        
        jtListDriverToEdit.getColumnModel().getColumn(0).setPreferredWidth(50);
        jtListDriverToEdit.getColumnModel().getColumn(1).setPreferredWidth(130);
        jtListDriverToEdit.getColumnModel().getColumn(2).setPreferredWidth(100);
        jtListDriverToEdit.getColumnModel().getColumn(3).setPreferredWidth(50);
        jtListDriverToEdit.getColumnModel().getColumn(4).setPreferredWidth(70);
        jtListDriverToEdit.getColumnModel().getColumn(5).setPreferredWidth(70);
        jtListDriverToEdit.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        ArrayList<Integer> userIDs = new ArrayList<Integer>();
        ArrayList<String> names = new ArrayList<String>();
        
        try
        {
            String getUserIDs = "Select [User_ID] From Drivers Where Manager_ID = ?";

            try (PreparedStatement sqlgetUserIDs = this.loginwindow.db.cnt.prepareStatement(getUserIDs)) 
            {
                sqlgetUserIDs.setInt(1, this.managerID);
                ResultSet rsUserIDs = sqlgetUserIDs.executeQuery();
                while(rsUserIDs.next())
                {
                    userIDs.add(rsUserIDs.getInt("User_ID"));
                }
            }      
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            for(int i = 0; i < userIDs.size(); i++)
            {
                String getNameIDs = "Select [Họ và tên] From [User] Where User_ID = ?";
                try (PreparedStatement sqlgetNameIDs = this.loginwindow.db.cnt.prepareStatement(getNameIDs)) 
                {
                    sqlgetNameIDs.setInt(1, userIDs.get(i));
                    ResultSet rsNameID = sqlgetNameIDs.executeQuery();
                    while(rsNameID.next())
                    {
                        names.add(rsNameID.getString("Họ và tên"));
                    }
                }      
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {   
            // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
            String getListID = "Select [Driver_ID], [Số giấy phép], [Hạng], [Lương], [Đánh giá], [Trạng thái]"
                    + " From Drivers Where Manager_ID = ?";
            PreparedStatement sqlgetListID = this.loginwindow.db.cnt.prepareStatement(getListID);
            sqlgetListID.setInt(1, this.managerID);
            
            // lấy danh sách xe
            ResultSet rs = sqlgetListID.executeQuery();
            int i = 0;
            while(rs.next())
            {
                defaultTableModel.addRow(new Object[]{rs.getInt("Driver_ID"), names.get(i), rs.getString("Số giấy phép"), rs.getString("Hạng"), 
                rs.getInt("Lương"), rs.getString("Đánh giá"), rs.getString("Trạng thái")});
                i++;
            }        
        }
        catch(SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanelActive = new javax.swing.JPanel();
        jlName = new javax.swing.JLabel();
        jlchangePasswordDriverManager = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jllogOutDriverManager = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jladdDriver = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jldeleteDriver = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jlListDriver = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jlShowPersonalInfor = new javax.swing.JLabel();
        jlChangeDriver = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPaneAll = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
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
        jLabel13 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jtfAccount = new javax.swing.JTextField();
        jlAccount = new javax.swing.JLabel();
        jpwfPasswordAddNewDriver = new javax.swing.JPasswordField();
        jlPasswordAddNewDriver = new javax.swing.JLabel();
        jpwfRePassword = new javax.swing.JPasswordField();
        jlRePassword = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jtfLicenseNumber = new javax.swing.JTextField();
        jlLicenseNumber = new javax.swing.JLabel();
        jlRank = new javax.swing.JLabel();
        jtfSalary = new javax.swing.JTextField();
        jlSalary = new javax.swing.JLabel();
        jbtnComplete = new javax.swing.JButton();
        jlSuccessAddNewDriver = new javax.swing.JLabel();
        jtfRank = new javax.swing.JTextField();
        jlUserIDAddNewDriver = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtListDriverToDelete = new javax.swing.JTable();
        jtfEnterIDToDelete = new javax.swing.JTextField();
        jlEnterIDToDelete = new javax.swing.JLabel();
        jbtnDeleteDriver = new javax.swing.JButton();
        jlSuccessDeleteDriver = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jlEnterIDToShow = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtlistDriverToShow = new javax.swing.JTable();
        jtfEnterIDToSearch = new javax.swing.JTextField();
        jl = new javax.swing.JLabel();
        jbtnSearch = new javax.swing.JButton();
        jbtnRefresh = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jbtnComplete1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jtfName = new javax.swing.JTextField();
        jlWarningName = new javax.swing.JLabel();
        jtfIdentityNumber = new javax.swing.JTextField();
        jlWarningIdentityNumber = new javax.swing.JLabel();
        jtfNumberPhone = new javax.swing.JTextField();
        jlWarningPhoneNumber = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jlWarningAddress = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jlGmail = new javax.swing.JLabel();
        jlWarningEmail = new javax.swing.JLabel();
        jcbDay = new javax.swing.JComboBox<>();
        jcbMonth = new javax.swing.JComboBox<>();
        jcbYear = new javax.swing.JComboBox<>();
        jcbGen = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jlLABEL = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtListDriverToEdit = new javax.swing.JTable();
        jtfEnterIDToEdit = new javax.swing.JTextField();
        jlEnterIDToEdit = new javax.swing.JLabel();
        jbtnDriverEdit = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jtfEditLicenseNumber = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jtfEditRank = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jtfEditSalary = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jtfEditEvaluate = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jtfEditState = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jcbConfirmEditDriver = new javax.swing.JCheckBox();
        jbtnUpdate = new javax.swing.JButton();
        jlSuccessEditCar = new javax.swing.JLabel();
        jlWarningConfirm = new javax.swing.JLabel();
        jlCarID = new javax.swing.JLabel();
        jtfDriverID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanelActive.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlName.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jlName.setForeground(new java.awt.Color(51, 153, 255));
        jpanelActive.add(jlName, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, 263, 34));

        jlchangePasswordDriverManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlchangePasswordDriverManager.setForeground(new java.awt.Color(0, 102, 255));
        jlchangePasswordDriverManager.setText("Đổi mật khẩu");
        jlchangePasswordDriverManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlchangePasswordDriverManager.setOpaque(true);
        jlchangePasswordDriverManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlchangePasswordDriverManagerMouseMoved(evt);
            }
        });
        jlchangePasswordDriverManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlchangePasswordDriverManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlchangePasswordDriverManagerMouseExited(evt);
            }
        });
        jpanelActive.add(jlchangePasswordDriverManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 116, 243, 35));
        jpanelActive.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 157, 263, -1));

        jllogOutDriverManager.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jllogOutDriverManager.setForeground(new java.awt.Color(0, 102, 255));
        jllogOutDriverManager.setText("Đăng xuất");
        jllogOutDriverManager.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jllogOutDriverManager.setOpaque(true);
        jllogOutDriverManager.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jllogOutDriverManagerMouseMoved(evt);
            }
        });
        jllogOutDriverManager.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jllogOutDriverManagerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jllogOutDriverManagerMouseExited(evt);
            }
        });
        jpanelActive.add(jllogOutDriverManager, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 166, 243, 35));
        jpanelActive.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 207, 263, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("QUẢN LÝ TÀI XẾ");
        jpanelActive.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 294, 139, 34));

        jladdDriver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jladdDriver.setForeground(new java.awt.Color(0, 102, 255));
        jladdDriver.setText("Thêm tài xế");
        jladdDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jladdDriver.setOpaque(true);
        jladdDriver.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jladdDriverMouseMoved(evt);
            }
        });
        jladdDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jladdDriverMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jladdDriverMouseExited(evt);
            }
        });
        jpanelActive.add(jladdDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 362, 243, 35));
        jpanelActive.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 403, 263, -1));

        jldeleteDriver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jldeleteDriver.setForeground(new java.awt.Color(0, 102, 255));
        jldeleteDriver.setText("Xóa tài xế");
        jldeleteDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jldeleteDriver.setOpaque(true);
        jldeleteDriver.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jldeleteDriverMouseMoved(evt);
            }
        });
        jldeleteDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jldeleteDriverMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jldeleteDriverMouseExited(evt);
            }
        });
        jpanelActive.add(jldeleteDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 412, 241, 35));
        jpanelActive.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 453, 263, -1));

        jlListDriver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlListDriver.setForeground(new java.awt.Color(0, 102, 255));
        jlListDriver.setText(" Danh sách tài xế");
        jlListDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlListDriver.setOpaque(true);
        jlListDriver.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlListDriverMouseMoved(evt);
            }
        });
        jlListDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlListDriverMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlListDriverMouseExited(evt);
            }
        });
        jpanelActive.add(jlListDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 243, 35));
        jpanelActive.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 503, 263, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N
        jpanelActive.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 683, -1, -1));
        jpanelActive.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 107, 263, -1));

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
        jpanelActive.add(jlShowPersonalInfor, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 66, 243, 35));

        jlChangeDriver.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlChangeDriver.setForeground(new java.awt.Color(0, 102, 255));
        jlChangeDriver.setText("Sửa thông tin tài xế");
        jlChangeDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlChangeDriver.setOpaque(true);
        jlChangeDriver.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlChangeDriverMouseMoved(evt);
            }
        });
        jlChangeDriver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlChangeDriverMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlChangeDriverMouseExited(evt);
            }
        });
        jpanelActive.add(jlChangeDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 462, 243, 35));

        getContentPane().add(jpanelActive, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 810));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 930, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 930, 30));

        jTabbedPaneAll.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 940, 10));

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

        jLabel13.setBackground(new java.awt.Color(255, 51, 51));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("THIẾT LẬP TÀI KHOẢN:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("Account");

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

        jlAccount.setForeground(new java.awt.Color(255, 0, 51));

        jpwfPasswordAddNewDriver.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfPasswordAddNewDriver.setForeground(new java.awt.Color(153, 153, 153));
        jpwfPasswordAddNewDriver.setText("mật khẩu");
        jpwfPasswordAddNewDriver.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfPasswordAddNewDriverFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfPasswordAddNewDriverFocusLost(evt);
            }
        });

        jlPasswordAddNewDriver.setForeground(new java.awt.Color(255, 51, 51));

        jpwfRePassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jpwfRePassword.setForeground(new java.awt.Color(153, 153, 153));
        jpwfRePassword.setText("xác nhận lại mật khẩu");
        jpwfRePassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jpwfRePasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jpwfRePasswordFocusLost(evt);
            }
        });

        jlRePassword.setForeground(new java.awt.Color(255, 51, 51));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("Thông tin bằng lái và lương");

        jtfLicenseNumber.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfLicenseNumber.setForeground(new java.awt.Color(153, 153, 153));
        jtfLicenseNumber.setText("số giấy phép");
        jtfLicenseNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfLicenseNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfLicenseNumberFocusLost(evt);
            }
        });

        jlLicenseNumber.setForeground(new java.awt.Color(255, 51, 51));

        jlRank.setForeground(new java.awt.Color(255, 51, 51));

        jtfSalary.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfSalary.setForeground(new java.awt.Color(153, 153, 153));
        jtfSalary.setText("lương");
        jtfSalary.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfSalaryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfSalaryFocusLost(evt);
            }
        });

        jbtnComplete.setBackground(new java.awt.Color(51, 102, 255));
        jbtnComplete.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jbtnComplete.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComplete.setText("Hoàn thành");
        jbtnComplete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCompleteActionPerformed(evt);
            }
        });

        jlSuccessAddNewDriver.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessAddNewDriver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessAddNewDriver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessAddNewDriver.setText("Thành công! Bạn đã thêm tài xế mới thành công.");
        jlSuccessAddNewDriver.setOpaque(true);

        jtfRank.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfRank.setForeground(new java.awt.Color(153, 153, 153));
        jtfRank.setText("hạng");
        jtfRank.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfRankFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfRankFocusLost(evt);
            }
        });

        jlUserIDAddNewDriver.setBackground(new java.awt.Color(255, 51, 51));
        jlUserIDAddNewDriver.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlUserIDAddNewDriver.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlUserIDAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlSuccessAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20)
                        .addComponent(jSeparator9)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtfAccount, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpwfPasswordAddNewDriver, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jlPasswordAddNewDriver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpwfRePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                        .addComponent(jlRePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfLicenseNumber)
                        .addComponent(jlLicenseNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlRank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfSalary)
                        .addComponent(jlSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtfRank))
                    .addComponent(jbtnComplete))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlUserIDAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpwfPasswordAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlPasswordAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpwfRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jtfLicenseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlLicenseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jtfRank, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlRank, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jbtnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jlSuccessAddNewDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jTabbedPaneAll.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtListDriverToDelete.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtListDriverToDelete.setModel(new javax.swing.table.DefaultTableModel(
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
        jtListDriverToDelete.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jtListDriverToDelete);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 80, 910, 693));

        jtfEnterIDToDelete.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfEnterIDToDelete.setForeground(new java.awt.Color(153, 153, 153));
        jtfEnterIDToDelete.setText("nhập ID để xóa");
        jtfEnterIDToDelete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEnterIDToDeleteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnterIDToDeleteFocusLost(evt);
            }
        });
        jPanel6.add(jtfEnterIDToDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 6, 158, 30));

        jlEnterIDToDelete.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlEnterIDToDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 42, 172, 20));

        jbtnDeleteDriver.setBackground(new java.awt.Color(51, 51, 255));
        jbtnDeleteDriver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnDeleteDriver.setForeground(new java.awt.Color(255, 255, 255));
        jbtnDeleteDriver.setText("Xóa");
        jbtnDeleteDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnDeleteDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteDriverActionPerformed(evt);
            }
        });
        jPanel6.add(jbtnDeleteDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 6, -1, 30));

        jlSuccessDeleteDriver.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessDeleteDriver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessDeleteDriver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessDeleteDriver.setText("Thành công! Bạn xóa tài xế thành công.");
        jlSuccessDeleteDriver.setOpaque(true);
        jPanel6.add(jlSuccessDeleteDriver, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 42, -1, -1));

        jTabbedPaneAll.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jlEnterIDToShow.setForeground(new java.awt.Color(255, 0, 0));

        jtlistDriverToShow.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtlistDriverToShow);

        jtfEnterIDToSearch.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtfEnterIDToSearch.setForeground(new java.awt.Color(153, 153, 153));
        jtfEnterIDToSearch.setText("nhập ID");
        jtfEnterIDToSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEnterIDToSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfEnterIDToSearchFocusLost(evt);
            }
        });

        jl.setForeground(new java.awt.Color(255, 0, 0));

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

        jbtnRefresh.setBackground(new java.awt.Color(0, 0, 255));
        jbtnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jbtnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRefresh.setText("Refresh");
        jbtnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jlEnterIDToShow, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jl, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jtfEnterIDToSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jbtnSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jbtnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(222, 222, 222))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfEnterIDToSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbtnSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jbtnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlEnterIDToShow, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneAll.addTab("tab5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnComplete1.setBackground(new java.awt.Color(51, 102, 255));
        jbtnComplete1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnComplete1.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComplete1.setText("Tiếp theo");
        jbtnComplete1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnComplete1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnComplete1MouseMoved(evt);
            }
        });
        jbtnComplete1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnComplete1MouseExited(evt);
            }
        });
        jbtnComplete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnComplete1ActionPerformed(evt);
            }
        });
        jPanel8.add(jbtnComplete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 698, -1, 47));

        jLabel22.setBackground(new java.awt.Color(204, 204, 255));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Họ tên");
        jLabel22.setOpaque(true);
        jPanel8.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 49, 53, 18));

        jLabel23.setBackground(new java.awt.Color(204, 204, 255));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Số chứng minh thư");
        jLabel23.setOpaque(true);
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 258, 126, -1));

        jLabel24.setBackground(new java.awt.Color(204, 204, 255));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Số điện thoại");
        jLabel24.setOpaque(true);
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 486, 95, -1));

        jLabel26.setBackground(new java.awt.Color(204, 204, 255));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Địa chỉ");
        jLabel26.setOpaque(true);
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 577, 57, -1));

        jPanel9.setBackground(new java.awt.Color(247, 247, 247));

        jLabel27.setBackground(new java.awt.Color(247, 247, 247));
        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("được bảo mật và an toàn");

        jLabel28.setBackground(new java.awt.Color(247, 247, 247));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/Logoright.png"))); // NOI18N

        jLabel29.setBackground(new java.awt.Color(247, 247, 247));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel29.setText("Thông tin cá nhân của bạn luôn");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(548, 229, -1, -1));

        jtfName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfName.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfNameFocusGained(evt);
            }
        });
        jPanel8.add(jtfName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 68, 437, 40));

        jlWarningName.setForeground(new java.awt.Color(255, 0, 51));
        jPanel8.add(jlWarningName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 111, 437, 20));

        jtfIdentityNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfIdentityNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfIdentityNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfIdentityNumberFocusGained(evt);
            }
        });
        jPanel8.add(jtfIdentityNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 296, 440, 40));

        jlWarningIdentityNumber.setForeground(new java.awt.Color(255, 0, 0));
        jPanel8.add(jlWarningIdentityNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 336, 440, 17));

        jtfNumberPhone.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfNumberPhone.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfNumberPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfNumberPhoneFocusGained(evt);
            }
        });
        jtfNumberPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfNumberPhoneActionPerformed(evt);
            }
        });
        jPanel8.add(jtfNumberPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 506, 440, 40));

        jlWarningPhoneNumber.setForeground(new java.awt.Color(255, 0, 51));
        jPanel8.add(jlWarningPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 546, 440, 20));

        jtfAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfAddressFocusGained(evt);
            }
        });
        jPanel8.add(jtfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 598, 440, 40));

        jlWarningAddress.setForeground(new java.awt.Color(255, 0, 51));
        jPanel8.add(jlWarningAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 644, 440, 19));

        jtfEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfEmail.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jtfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfEmailFocusGained(evt);
            }
        });
        jPanel8.add(jtfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 416, 330, 40));

        jLabel30.setBackground(new java.awt.Color(204, 204, 255));
        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Email");
        jLabel30.setOpaque(true);
        jPanel8.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 396, 44, -1));

        jlGmail.setBackground(new java.awt.Color(255, 255, 255));
        jlGmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlGmail.setText("@gmail.com");
        jlGmail.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(205, 220, 244), null));
        jlGmail.setOpaque(true);
        jPanel8.add(jlGmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 416, 110, 40));

        jlWarningEmail.setForeground(new java.awt.Color(255, 0, 51));
        jPanel8.add(jlWarningEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 456, 440, 20));

        jcbDay.setBackground(new java.awt.Color(247, 247, 247));
        jcbDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jcbDay.setOpaque(true);
        jcbDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDayActionPerformed(evt);
            }
        });
        jPanel8.add(jcbDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 187, -1, -1));

        jcbMonth.setBackground(new java.awt.Color(247, 247, 247));
        jcbMonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        jPanel8.add(jcbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 187, -1, -1));

        jcbYear.setBackground(new java.awt.Color(247, 247, 247));
        jcbYear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023" }));
        jPanel8.add(jcbYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 187, -1, -1));

        jcbGen.setBackground(new java.awt.Color(247, 247, 247));
        jcbGen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbGen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        jcbGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbGenActionPerformed(evt);
            }
        });
        jPanel8.add(jcbGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 187, -1, -1));

        jLabel31.setBackground(new java.awt.Color(204, 204, 255));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Ngày tháng năm sinh");
        jLabel31.setOpaque(true);
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 149, 146, -1));

        jLabel32.setBackground(new java.awt.Color(204, 204, 255));
        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Giới tính");
        jLabel32.setOpaque(true);
        jPanel8.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 149, -1, -1));

        jlLABEL.setBackground(new java.awt.Color(255, 51, 51));
        jlLABEL.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jlLABEL.setForeground(new java.awt.Color(255, 51, 51));
        jlLABEL.setText("THÊM TÀI XẾ");
        jlLABEL.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 51, 51), null));
        jPanel8.add(jlLABEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 23, 166, 37));

        jTabbedPaneAll.addTab("tab6", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtListDriverToEdit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jtListDriverToEdit.setModel(new javax.swing.table.DefaultTableModel(
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
        jtListDriverToEdit.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jtListDriverToEdit);

        jPanel10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 93, 900, 680));

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
        jPanel10.add(jtfEnterIDToEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 18, 158, 32));

        jlEnterIDToEdit.setForeground(new java.awt.Color(255, 0, 0));
        jPanel10.add(jlEnterIDToEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 56, 254, 19));

        jbtnDriverEdit.setBackground(new java.awt.Color(51, 102, 255));
        jbtnDriverEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnDriverEdit.setForeground(new java.awt.Color(255, 255, 255));
        jbtnDriverEdit.setText("Sửa");
        jbtnDriverEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnDriverEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDriverEditActionPerformed(evt);
            }
        });
        jPanel10.add(jbtnDriverEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 18, -1, -1));

        jTabbedPaneAll.addTab("tab7", jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(153, 0, 0));
        jLabel21.setText("THÔNG TIN TÀI XẾ");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setText("Số giấy phép: ");

        jtfEditLicenseNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel33.setText("Hạng: ");

        jtfEditRank.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("Lương:");

        jtfEditSalary.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel35.setText("Đánh giá:");

        jtfEditEvaluate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel36.setText("Trạng thái:");

        jtfEditState.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(153, 0, 0));
        jLabel38.setText("Xác nhận");

        jcbConfirmEditDriver.setBackground(new java.awt.Color(255, 255, 255));
        jcbConfirmEditDriver.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jcbConfirmEditDriver.setText("Xác nhận");
        jcbConfirmEditDriver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
        jlCarID.setText("Driver_ID:");

        jtfDriverID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jtfDriverID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator11)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel33)
                                            .addComponent(jlCarID))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jtfEditLicenseNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                                    .addComponent(jtfEditRank)
                                    .addComponent(jtfEditSalary)
                                    .addComponent(jtfEditEvaluate)
                                    .addComponent(jtfEditState)
                                    .addComponent(jtfDriverID)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnUpdate)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jcbConfirmEditDriver)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jlWarningConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlSuccessEditCar, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCarID)
                    .addComponent(jtfDriverID, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfEditLicenseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jtfEditRank, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jtfEditSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jtfEditEvaluate, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfEditState, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)))
                .addGap(44, 44, 44)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbConfirmEditDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlWarningConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jbtnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlSuccessEditCar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPaneAll.addTab("tab8", jPanel11);

        getContentPane().add(jTabbedPaneAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, -4, 930, 810));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jlchangePasswordDriverManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordDriverManagerMouseClicked
        // TODO add your handling code here:
        jlSuccess.setVisible(false);
        jlnotifyOldPassword.setText("");
        jlRequest.setText("");
        jlConfirm.setText("");
        jTabbedPaneAll.setSelectedIndex(1);
    }//GEN-LAST:event_jlchangePasswordDriverManagerMouseClicked

    private void jlchangePasswordDriverManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordDriverManagerMouseExited
        // TODO add your handling code here:
        jlchangePasswordDriverManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlchangePasswordDriverManagerMouseExited

    private void jlchangePasswordDriverManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlchangePasswordDriverManagerMouseMoved
        // TODO add your handling code here:
        jlchangePasswordDriverManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlchangePasswordDriverManagerMouseMoved

    private void jllogOutDriverManagerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutDriverManagerMouseClicked
        // TODO add your handling code here:
        this.loginwindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jllogOutDriverManagerMouseClicked

    private void jllogOutDriverManagerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutDriverManagerMouseExited
        // TODO add your handling code here:
        jllogOutDriverManager.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jllogOutDriverManagerMouseExited

    private void jllogOutDriverManagerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jllogOutDriverManagerMouseMoved
        // TODO add your handling code here:
        jllogOutDriverManager.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jllogOutDriverManagerMouseMoved

    
    
    
    
    
    private void jladdDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdDriverMouseClicked
        // TODO add your handling code here:
        jlSuccessAddNewDriver.setVisible(false);
        
        jlWarningName.setText("");
        jlWarningIdentityNumber.setText("");
        jlWarningEmail.setText("");
        jlWarningPhoneNumber.setText("");
        jlWarningAddress.setText("");
        
        jtfName.setText("");
        jtfIdentityNumber.setText("");
        jtfEmail.setText("");
        jtfNumberPhone.setText("");
        jtfAddress.setText("");
        
        jTabbedPaneAll.setSelectedIndex(5);
        
    }//GEN-LAST:event_jladdDriverMouseClicked

    private void jladdDriverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdDriverMouseExited
        // TODO add your handling code here:
        jladdDriver.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jladdDriverMouseExited

    private void jladdDriverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jladdDriverMouseMoved
        // TODO add your handling code here:
        jladdDriver.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jladdDriverMouseMoved

    private void jldeleteDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteDriverMouseClicked
        // TODO add your handling code here:
        jlSuccessDeleteDriver.setVisible(false);
        jTabbedPaneAll.setSelectedIndex(3);
    }//GEN-LAST:event_jldeleteDriverMouseClicked

    private void jldeleteDriverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteDriverMouseExited
        // TODO add your handling code here:
        jldeleteDriver.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jldeleteDriverMouseExited

    private void jldeleteDriverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jldeleteDriverMouseMoved
        // TODO add your handling code here:
        jldeleteDriver.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jldeleteDriverMouseMoved

    private void jlListDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlListDriverMouseClicked
        // TODO add your handling code here:
        jTabbedPaneAll.setSelectedIndex(4);
    }//GEN-LAST:event_jlListDriverMouseClicked

    private void jlListDriverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlListDriverMouseExited
        // TODO add your handling code here:
        jlListDriver.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlListDriverMouseExited

    private void jlListDriverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlListDriverMouseMoved
        // TODO add your handling code here:
        jlListDriver.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlListDriverMouseMoved

    private void jlChangeDriverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeDriverMouseMoved
        // TODO add your handling code here:
        // TODO add your handling code here:
        jlChangeDriver.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlChangeDriverMouseMoved

    private void jlChangeDriverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeDriverMouseClicked
        // TODO add your handling code here:   
        jlEnterIDToEdit.setText("");
        jTabbedPaneAll.setSelectedIndex(6);
    }//GEN-LAST:event_jlChangeDriverMouseClicked

    private void jlChangeDriverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangeDriverMouseExited
        // TODO add your handling code here:
        jlChangeDriver.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlChangeDriverMouseExited

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        // TODO add your handling code here:
        jlWarningConfirm.setText("");
        if(!jcbConfirmEditDriver.isSelected())
        {
            jlWarningConfirm.setText("bắt buộc");
        }
        else
        {
            int driverID = Integer.parseInt(jtfDriverID.getText());
            String soGiayPhep = jtfEditLicenseNumber.getText();
            String hang = jtfEditRank.getText();
            int luong = Integer.parseInt(jtfEditSalary.getText());
            String danhGia = jtfEditEvaluate.getText();
            String trangThai = jtfEditState.getText();

            try
            {
                // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
                String editDriver = "Update Drivers "
                + "Set [Số giấy phép] = ?, [Hạng] = ?, [Lương] = ?, [Đánh giá] = ?, [Trạng thái] = ? "
                + " Where Driver_ID = ?";

                // tạo thể hiện
                PreparedStatement sqleditDriver = this.loginwindow.db.cnt.prepareStatement(editDriver);

                sqleditDriver.setString(1, soGiayPhep);
                sqleditDriver.setString(2, hang);
                sqleditDriver.setInt(3, luong);
                sqleditDriver.setString(4, danhGia);
                sqleditDriver.setString(5, trangThai);
                sqleditDriver.setInt(6, driverID);

                // thực hiện truy vấn
                sqleditDriver.executeUpdate();

                // cập nhật lại bảng
                setForthInformation();
                setFifthInformation();
                setSeventhInformation();
                

                jlSuccessEditCar.setVisible(true);

            }
            catch (SQLException ex)
            {
                Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jbtnDriverEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDriverEditActionPerformed
        // TODO add your handling code here:
        String stringidDriver = jtfEnterIDToEdit.getText();
        if(stringidDriver.equals("nhập ID để sửa"))
        {
            jlEnterIDToEdit.setText("yêu cầu nhập ID");
        }
        else
        {
            jtfEnterIDToEdit.setText("nhập ID để sửa");
            jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));

            int intidDriver = Integer.parseInt(stringidDriver);
            if(checkExistDriver(intidDriver))
            {
                editDriver(intidDriver);
                jTabbedPaneAll.setSelectedIndex(7);
            }
            else
            {
                jlEnterIDToEdit.setText("không tồn tại ID");

                jtfEnterIDToEdit.setText("nhập ID để sửa");
                jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));
            }
        }
    }//GEN-LAST:event_jbtnDriverEditActionPerformed

    private void jtfEnterIDToEditFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToEditFocusLost
        // TODO add your handling code here:
        if (jtfEnterIDToEdit.getText().equals(""))
        {
            jtfEnterIDToEdit.setText("nhập ID để sửa");
            jtfEnterIDToEdit.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToEdit.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDToEditFocusLost

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

    private void jcbGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbGenActionPerformed

    private void jcbDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbDayActionPerformed

    private void jtfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEmailFocusGained
        // TODO add your handling code here:
        jlWarningEmail.setText("");
    }//GEN-LAST:event_jtfEmailFocusGained

    private void jtfAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAddressFocusGained
        // TODO add your handling code here:
        jlWarningAddress.setText("");
    }//GEN-LAST:event_jtfAddressFocusGained

    private void jtfNumberPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfNumberPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfNumberPhoneActionPerformed

    private void jtfNumberPhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNumberPhoneFocusGained
        // TODO add your handling code here:
        jlWarningPhoneNumber.setText("");
    }//GEN-LAST:event_jtfNumberPhoneFocusGained

    private void jtfIdentityNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfIdentityNumberFocusGained
        // TODO add your handling code here:
        jlWarningIdentityNumber.setText("");
    }//GEN-LAST:event_jtfIdentityNumberFocusGained

    private void jtfNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfNameFocusGained
        // TODO add your handling code here:
        jlWarningName.setText("");
    }//GEN-LAST:event_jtfNameFocusGained

    private void jbtnComplete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnComplete1ActionPerformed
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
                String sweepIDN = "Select [Số chứng minh thư] from [User]";

                PreparedStatement sqlsweepIDN = this.loginwindow.db.cnt.prepareStatement(sweepIDN);

                ResultSet rs = sqlsweepIDN.executeQuery();

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

                            String sqlQueryWrite = "Insert into [User]([Họ và tên], [Giới tính], [Ngày sinh], [Số chứng minh thư], [Số điện thoại], [Địa chỉ], [Email], [Ngày cấp quyền])"
                            + " Values(?,?,?,?,?,?,?,?)";

                            PreparedStatement stmt = this.loginwindow.db.cnt.prepareStatement(sqlQueryWrite);

                            Timestamp datetime = new Timestamp(System.currentTimeMillis());

                            stmt.setString(1, Name);
                            stmt.setString(2, gender);
                            stmt.setDate(3, dateBirth);
                            stmt.setString(4, IdentityNumber);
                            stmt.setString(5, NumberPhone);
                            stmt.setString(6, Address);
                            stmt.setString(7, Email);
                            stmt.setTimestamp(8, datetime);

                            // thực hiện truy vấn
                            stmt.executeUpdate();

                            String getDriverID = "Select [User_ID] From [User] Where [Số chứng minh thư] = ?";

                            stmt = this.loginwindow.db.cnt.prepareStatement(getDriverID);

                            stmt.setString(1, IdentityNumber);

                            ResultSet rsGetUserID = stmt.executeQuery();

                            while(rsGetUserID.next())
                            {
                                jlUserIDAddNewDriver.setText((rsGetUserID.getString("User_ID")));
                            }

                            jtfName.setText("");
                            jtfIdentityNumber.setText("");
                            jtfEmail.setText("");
                            jtfNumberPhone.setText("");
                            jtfAddress.setText("");

                            jTabbedPaneAll.setSelectedIndex(2);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnComplete1ActionPerformed

    private void jbtnComplete1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnComplete1MouseExited
        // TODO add your handling code here:
        jbtnComplete.setBackground(new Color(51,102,255));
    }//GEN-LAST:event_jbtnComplete1MouseExited

    private void jbtnComplete1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnComplete1MouseMoved
        // TODO add your handling code here:
        jbtnComplete.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_jbtnComplete1MouseMoved

    private void jbtnDeleteDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteDriverActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        jlSuccessDeleteDriver.setVisible(false);
        jlEnterIDToDelete.setText("");

        String stringDriverID = jtfEnterIDToDelete.getText();
        if(stringDriverID.equals("nhập ID để xóa"))
        {
            jlEnterIDToDelete.setText("không được bỏ trống");
        }
        else
        {
            int intDriverID  = Integer.parseInt(stringDriverID);
            boolean checkActiveDriver = false;
            boolean checkExistDriverID = false;
            int userID = -1;
            try
            {
                // thực hiện quét
                String sweepDriverList = "Select Driver_ID, User_ID From Drivers";

                // tạo thể hiện để truy vấn
                try(PreparedStatement sqlsweepDriverList = this.loginwindow.db.cnt.prepareStatement(sweepDriverList))
                {
                    // lấy bảng dữ liệu
                    ResultSet rs = sqlsweepDriverList.executeQuery();

                    while(rs.next())
                    {
                        if(intDriverID == rs.getInt("Driver_ID"))
                        {
                            checkExistDriverID = true;
                            userID = rs.getInt("User_ID");
                            break;
                        }
                    }
                }
                // kiểm tra cờ check
                if(checkExistDriverID)
                {

                    // thực hiện quét
                    String testActiveDriver = "Select Driver_ID From Cars";

                    // tạo thể hiện để truy vấn
                    try(PreparedStatement sqltestActiveDriver = this.loginwindow.db.cnt.prepareStatement(testActiveDriver))
                    {
                        // lấy bảng dữ liệu
                        ResultSet rssqltestActiveDriver = sqltestActiveDriver.executeQuery();

                        while(rssqltestActiveDriver.next())
                        {
                            if(intDriverID == rssqltestActiveDriver.getInt("Driver_ID"))
                            {
                                checkActiveDriver = true;
                                break;
                            }
                        }
                    }

                    if(checkActiveDriver == false)
                    {
                        // khởi tạo câu lệnh truy vấn xóa bảng Drivers
                        String sweepDeleteDriver = "Delete From Drivers Where Driver_ID = ?";

                        // tạo thể hiện để truy vấn
                        try(PreparedStatement sqlsweepDeleteDriver = this.loginwindow.db.cnt.prepareStatement(sweepDeleteDriver))
                        {        

                            sqlsweepDeleteDriver.setInt(1, intDriverID);

                            // lấy bảng dữ liệu
                            sqlsweepDeleteDriver.executeUpdate();

                        }
                        
                        // khởi tạo câu lệnh truy vấn xóa bảng User
                        String sweepDeleteUser = "Delete From [User] Where User_ID = ?";

                        // tạo thể hiện để truy vấn
                        try(PreparedStatement sqlsweepDeleteUser = this.loginwindow.db.cnt.prepareStatement(sweepDeleteUser))
                        {        

                            sqlsweepDeleteUser.setInt(1, userID);

                            // lấy bảng dữ liệu
                            sqlsweepDeleteUser.executeUpdate();
                        }
                        
                        
                        // thiet lap lai place holder
                        jtfEnterIDToDelete.setText("nhập ID để xóa");
                        jtfEnterIDToDelete.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jtfEnterIDToDelete.setForeground(new Color(153, 153, 153));
      
                        setForthInformation();
                        setFifthInformation();
                        setSeventhInformation();

                        jlSuccessDeleteDriver.setVisible(true);
                    }
                    else
                    {
                        jlEnterIDToDelete.setText("không thể xóa khi xe đang được sử dụng");

                        // thiet lap lai place holder
                        jlEnterIDToDelete.setText("nhập ID để xóa");
                        jlEnterIDToDelete.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                        jlEnterIDToDelete.setForeground(new Color(153, 153, 153));
                    }
                }
                else
                {
                    jlEnterIDToDelete.setText("ID không tồn tại");

                    // thiet lap lai place holder
                    jtfEnterIDToDelete.setText("nhập ID để xóa");
                    jtfEnterIDToDelete.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfEnterIDToDelete.setForeground(new Color(153, 153, 153));
                }

            }
            catch(SQLException ex)
            {
                Logger.getLogger(loginWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jbtnDeleteDriverActionPerformed

    private void jtfEnterIDToDeleteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToDeleteFocusLost
        // TODO add your handling code here:
        if (jtfEnterIDToDelete.getText().equals(""))
        {
            jtfEnterIDToDelete.setText("nhập ID để xóa");
            jtfEnterIDToDelete.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToDelete.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDToDeleteFocusLost

    private void jtfEnterIDToDeleteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToDeleteFocusGained
        // TODO add your handling code here:
        if (jtfEnterIDToDelete.getText().equals("nhập ID để xóa"))
        {
            jtfEnterIDToDelete.setText("");
            jlEnterIDToDelete.setText("");
            jtfEnterIDToDelete.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfEnterIDToDelete.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfEnterIDToDeleteFocusGained

    private void jtfRankFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfRankFocusLost
        // TODO add your handling code here:
        if (jtfRank.getText().equals(""))
        {
            jtfRank.setText("hạng");
            jtfRank.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfRank.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfRankFocusLost

    private void jtfRankFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfRankFocusGained
        // TODO add your handling code here:
        if (jtfRank.getText().equals("hạng"))
        {
            jtfRank.setText("");
            jlRank.setText("");
            jtfRank.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfRank.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfRankFocusGained

    private void jbtnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCompleteActionPerformed
        // TODO add your handling code here:

        jlAccount.setText("");
        jlPasswordAddNewDriver.setText("");
        jlRePassword.setText("");
        jlLicenseNumber.setText("");
        jlRank.setText("");
        jlSalary.setText("");

        int userID = Integer.parseInt(jlUserIDAddNewDriver.getText());
        String userName = jtfAccount.getText();
        String passwordNewDriver = jpwfPasswordAddNewDriver.getText();
        String confirmPassword = jpwfRePassword.getText();
        String licenseNumber = jtfLicenseNumber.getText();
        String rank = jtfRank.getText();
        String stringsalary = jtfSalary.getText();

        if(userName.equals("tài khoản") || passwordNewDriver.equals("mật khẩu") || confirmPassword.equals("xác nhận lại mật khẩu") || licenseNumber.equals("số giấy phép") || rank.equals("hạng") || stringsalary.equals("lương"))
        {
            if(userName.equals("tài khoản"))
            {
                jlAccount.setText("bắt buộc");
            }

            if(passwordNewDriver.equals("mật khẩu"))
            {
                jlPasswordAddNewDriver.setText("bắt buộc");
            }

            if(confirmPassword.equals("xác nhận lại mật khẩu"))
            {
                jlRePassword.setText("bắt buộc");
            }

            if(licenseNumber.equals("số giấy phép"))
            {
                jlLicenseNumber.setText("bắt buộc");
            }

            if(rank.equals("hạng"))
            {
                jlRank.setText("bắt buộc");
            }

            if(stringsalary.equals("lương"))
            {
                jlSalary.setText("bắt buộc");
            }
        }
        else
        {
            if(confirmPassword.equals(passwordNewDriver))
            {
                try
                {
                    // khởi tại thể hiện của Statement để thực hiện truy vân với cơ sở dữ liệu

                    // Quét tài khoản
                    String userSweep = "Select [Tài khoản] From [User]";

                    boolean flagAccount;
                    // Đọc dữ liệu từ database
                    try (PreparedStatement sqluserSweep = this.loginwindow.db.cnt.prepareStatement(userSweep))
                    {
                        // Đọc dữ liệu từ database
                        ResultSet rsAccount = sqluserSweep.executeQuery();
                        // Tạo cờ để duyệt cơ sở dữ liệu
                        flagAccount = false;
                        // Tạo vòng lặp duyệt db
                        while(rsAccount.next())
                        {
                            String dbAccount = rsAccount.getString("Tài khoản");
                            if(userName.equals(dbAccount))
                            {
                                flagAccount = true;
                                break;
                            }
                        }
                    }
                    if(flagAccount)
                    {
                        jlAccount.setText("tài khoản phải là duy nhất");
                    }
                    else
                    {
                        // Quét giấy phép lái xe
                        String licenseSweep = "Select [Số giấy phép] From [Drivers]";

                        boolean flagLicense;
                        // Đọc dữ liệu từ database
                        try (PreparedStatement sqllicenseSweep = this.loginwindow.db.cnt.prepareStatement(licenseSweep)) {
                            // Đọc dữ liệu từ database
                            ResultSet rsLicense = sqllicenseSweep.executeQuery();
                            // Tạo cờ để duyệt cơ sở dữ liệu
                            flagLicense = false;
                            // Tạo vòng lặp duyệt db
                            while(rsLicense.next())
                            {
                                String dbLicense = rsLicense.getString("Số giấy phép");
                                if(licenseNumber.equals(dbLicense))
                                {
                                    flagLicense = true;
                                    break;
                                }
                            }
                        }
                        if(flagLicense)
                        {
                            jlLicenseNumber.setText("số giấy phép đã tồn tại ở người dùng khác");
                        }
                        else
                        {
                            // câu truy vấn đẩy tài khoản mật khẩu vào database User
                            String updateAccount = "Update [User] "
                            +" Set [Tài khoản] = ?, [Mật khẩu] = ?"
                            + " Where [User_ID] = ?";

                            // truy vấn đẩy dữ liệu vào database
                            try (PreparedStatement sqlupdateAccount = this.loginwindow.db.cnt.prepareStatement(updateAccount))
                            {
                                // gán các giá trị
                                sqlupdateAccount.setString(1, userName);
                                sqlupdateAccount.setString(2, passwordNewDriver);
                                sqlupdateAccount.setInt(3, userID);

                                // thực thi truy vấn
                                sqlupdateAccount.executeUpdate();
                            }

                            
                            // câu truy vấn đẩy thông tin bằng lái và lương và database Drivers
                            String addNewDriver = "Insert into Drivers(User_ID, Manager_ID, [Số giấy phép], Hạng, Lương, [Đánh giá], [Trạng thái])"
                            + " Values(?, ?, ?, ?, ?, ?, ?)";

                            try (PreparedStatement sqladdNewDriver = this.loginwindow.db.cnt.prepareStatement(addNewDriver))
                            {
                                sqladdNewDriver.setInt(1, userID);
                                sqladdNewDriver.setInt(2, this.managerID);
                                sqladdNewDriver.setString(3, licenseNumber);
                                sqladdNewDriver.setString(4, rank);
                                sqladdNewDriver.setInt(5, Integer.parseInt(stringsalary));
                                sqladdNewDriver.setString(6, "Tốt");
                                sqladdNewDriver.setString(7, "Đang chờ việc");

                                sqladdNewDriver.executeUpdate();
                            }
                            jlSuccessAddNewDriver.setVisible(true);

                            jtfAccount.setText("tài khoản");
                            jtfAccount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jtfAccount.setForeground(new Color(153, 153, 153));

                            jpwfPasswordAddNewDriver.setText("mật khẩu");
                            jpwfPasswordAddNewDriver.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jpwfPasswordAddNewDriver.setForeground(new Color(153, 153, 153));
                            jpwfPasswordAddNewDriver.setEchoChar((char)0);

                            jpwfRePassword.setText("xác nhận lại mật khẩu");
                            jpwfRePassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jpwfRePassword.setForeground(new Color(153, 153, 153));
                            jpwfRePassword.setEchoChar((char)0);

                            jtfLicenseNumber.setText("số giấy phép");
                            jtfLicenseNumber.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jtfLicenseNumber.setForeground(new Color(153, 153, 153));

                            jtfRank.setText("hạng");
                            jtfRank.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jtfRank.setForeground(new Color(153, 153, 153));

                            jtfSalary.setText("lương");
                            jtfSalary.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                            jtfSalary.setForeground(new Color(153, 153, 153));

                            setForthInformation();
                            setFifthInformation();
                            setSeventhInformation();
                            
                        }
                    }
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                jlRePassword.setText("mật khẩu không khớp");
            }
        }
    }//GEN-LAST:event_jbtnCompleteActionPerformed

    private void jtfSalaryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSalaryFocusLost
        // TODO add your handling code here:
        if (jtfSalary.getText().equals(""))
        {
            jtfSalary.setText("lương");
            jtfSalary.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfSalary.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfSalaryFocusLost

    private void jtfSalaryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfSalaryFocusGained
        // TODO add your handling code here:
        if (jtfSalary.getText().equals("lương"))
        {
            jtfSalary.setText("");
            jlSalary.setText("");
            jtfSalary.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfSalary.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfSalaryFocusGained

    private void jtfLicenseNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfLicenseNumberFocusLost
        // TODO add your handling code here:
        if (jtfLicenseNumber.getText().equals(""))
        {
            jtfLicenseNumber.setText("số giấy phép");
            jtfLicenseNumber.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfLicenseNumber.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfLicenseNumberFocusLost

    private void jtfLicenseNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfLicenseNumberFocusGained
        // TODO add your handling code here:
        if (jtfLicenseNumber.getText().equals("số giấy phép"))
        {
            jtfLicenseNumber.setText("");
            jlLicenseNumber.setText("");
            jtfLicenseNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfLicenseNumber.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfLicenseNumberFocusGained

    private void jpwfRePasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfRePasswordFocusLost
        // TODO add your handling code here:
        if (jpwfRePassword.getText().equals(""))
        {

            // thiết lập placeholder cho mật khẩu
            jpwfRePassword.setText("xác nhận lại mật khẩu");
            jpwfRePassword.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfRePassword.setForeground(new Color(153, 153, 153));
            jpwfRePassword.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfRePasswordFocusLost

    private void jpwfRePasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfRePasswordFocusGained
        // TODO add your handling code here:
        if (jpwfRePassword.getText().equals("xác nhận lại mật khẩu"))
        {
            jpwfRePassword.setText("");
            jlRePassword.setText("");
            jpwfRePassword.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfRePassword.setEchoChar('.');
            jpwfRePassword.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfRePasswordFocusGained

    private void jpwfPasswordAddNewDriverFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfPasswordAddNewDriverFocusLost
        // TODO add your handling code here:
        if (jpwfPasswordAddNewDriver.getText().equals(""))
        {

            // thiết lập placeholder cho mật khẩu
            jpwfPasswordAddNewDriver.setText("mật khẩu");
            jpwfPasswordAddNewDriver.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jpwfPasswordAddNewDriver.setForeground(new Color(153, 153, 153));
            jpwfPasswordAddNewDriver.setEchoChar((char)0);
        }
    }//GEN-LAST:event_jpwfPasswordAddNewDriverFocusLost

    private void jpwfPasswordAddNewDriverFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jpwfPasswordAddNewDriverFocusGained
        // TODO add your handling code here:
        if (jpwfPasswordAddNewDriver.getText().equals("mật khẩu"))
        {
            jpwfPasswordAddNewDriver.setText("");
            jlPasswordAddNewDriver.setText("");
            jpwfPasswordAddNewDriver.setFont(new Font("Segoe UI", Font.BOLD, 25));
            jpwfPasswordAddNewDriver.setEchoChar('.');
            jpwfPasswordAddNewDriver.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jpwfPasswordAddNewDriverFocusGained

    private void jtfAccountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAccountFocusLost
        // TODO add your handling code here:
        if (jtfAccount.getText().equals(""))
        {
            jtfAccount.setText("tài khoản");
            jtfAccount.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfAccount.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfAccountFocusLost

    private void jtfAccountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfAccountFocusGained
        // TODO add your handling code here:
        if (jtfAccount.getText().equals("tài khoản"))
        {
            jtfAccount.setText("");
            jlAccount.setText("");
            jtfAccount.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfAccount.setForeground(new Color(0, 0, 0));
        }

    }//GEN-LAST:event_jtfAccountFocusGained

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

    private void jtfEnterIDToSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToSearchFocusGained
        // TODO add your handling code here:
        if (jtfEnterIDToSearch.getText().equals("nhập ID"))
        {
            jtfEnterIDToSearch.setText("");
            jlEnterIDToShow.setText("");
            jtfEnterIDToSearch.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfEnterIDToSearch.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfEnterIDToSearchFocusGained

    private void jtfEnterIDToSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEnterIDToSearchFocusLost
        // TODO add your handling code here:
        if (jtfEnterIDToSearch.getText().equals(""))
        {
            jtfEnterIDToSearch.setText("nhập ID");
            jtfEnterIDToSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfEnterIDToSearch.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfEnterIDToSearchFocusLost

    
    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
        // TODO add your handling code here:
     
        // lấy thông tin ở jtextfield
        String stringDriverID = jtfEnterIDToSearch.getText();
        
        if(stringDriverID.equals("nhập ID"))
        {
            jlEnterIDToShow.setText("yêu cầu nhập ID");
        }
        else
        {
            // ép string sang integer
            int intDriverID = Integer.parseInt(stringDriverID);

            // tìm kiếm
            searchDriver(intDriverID);
        }
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void jbtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRefreshActionPerformed
        // TODO add your handling code here:
        setFifthInformation();
        jlEnterIDToShow.setText("");
        jtfEnterIDToSearch.enable(true);
        
        // thiet lap lai place holder
        jtfEnterIDToSearch.setText("nhập ID");
        jtfEnterIDToSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        jtfEnterIDToSearch.setForeground(new Color(153, 153, 153));
    }//GEN-LAST:event_jbtnRefreshActionPerformed

    public void searchDriver(int driverID)
    {       
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String searchDriverList = "Select [Driver_ID] From Drivers";
            boolean checkExistDriverID = false;
            // tạo thể hiện
            try(PreparedStatement sqlsearchDriverList = this.loginwindow.db.cnt.prepareStatement(searchDriverList))
            {
                // thực hiện truy vấn
                ResultSet rs = sqlsearchDriverList.executeQuery();                   
                while(rs.next())
                {
                    if(driverID == rs.getInt("Driver_ID"))
                    {
                        checkExistDriverID = true;
                        break;
                    }
                }
            }
            if(checkExistDriverID)
            {
                // tạo thể hiện cho defaulttablemodol
                DefaultTableModel defaultTableModel = new DefaultTableModel();
                jtlistDriverToShow.setModel(defaultTableModel);

                // thêm các cột cần thiết
                defaultTableModel.addColumn("Driver_ID");
                defaultTableModel.addColumn("Họ và tên");
                defaultTableModel.addColumn("Số giấy phép");
                defaultTableModel.addColumn("Hạng");
                defaultTableModel.addColumn("Lương");
                defaultTableModel.addColumn("Đánh giá");
                defaultTableModel.addColumn("Trạng thái");

                jtlistDriverToShow.getColumnModel().getColumn(0).setPreferredWidth(50);
                jtlistDriverToShow.getColumnModel().getColumn(1).setPreferredWidth(130);
                jtlistDriverToShow.getColumnModel().getColumn(2).setPreferredWidth(100);
                jtlistDriverToShow.getColumnModel().getColumn(3).setPreferredWidth(50);
                jtlistDriverToShow.getColumnModel().getColumn(4).setPreferredWidth(70);
                jtlistDriverToShow.getColumnModel().getColumn(5).setPreferredWidth(70);
                jtlistDriverToShow.getColumnModel().getColumn(6).setPreferredWidth(80);

                ArrayList<Integer> userIDs = new ArrayList<Integer>();
                ArrayList<String> names = new ArrayList<String>();

                try
                {
                    String getUserIDs = "Select [User_ID] From Drivers Where Driver_ID = ?";

                    try (PreparedStatement sqlgetUserIDs = this.loginwindow.db.cnt.prepareStatement(getUserIDs)) 
                    {
                        sqlgetUserIDs.setInt(1, driverID);
                        ResultSet rsUserIDs = sqlgetUserIDs.executeQuery();
                        while(rsUserIDs.next())
                        {
                            userIDs.add(rsUserIDs.getInt("User_ID"));
                        }
                    }      
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
                }

                try
                {
                    for(int i = 0; i < userIDs.size(); i++)
                    {
                        String getNameIDs = "Select [Họ và tên] From [User] Where User_ID = ?";
                        try (PreparedStatement sqlgetNameIDs = this.loginwindow.db.cnt.prepareStatement(getNameIDs)) 
                        {
                            sqlgetNameIDs.setInt(1, userIDs.get(i));
                            ResultSet rsNameID = sqlgetNameIDs.executeQuery();
                            while(rsNameID.next())
                            {
                                names.add(rsNameID.getString("Họ và tên"));
                            }
                        }      
                    }
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
                }

                try
                {   
                    // truy vấn dữ liệu từ sql server để lấy danh sách thông tin về xe
                    String getListID = "Select [Số giấy phép], [Hạng], [Lương], [Đánh giá], [Trạng thái]"
                            + " From Drivers Where Driver_ID = ?";
                    PreparedStatement sqlgetListID = this.loginwindow.db.cnt.prepareStatement(getListID);
                    sqlgetListID.setInt(1, driverID);

                    // lấy danh sách xe
                    ResultSet rsSearch = sqlgetListID.executeQuery();
                    int i = 0;
                    while(rsSearch.next())
                    {
                        defaultTableModel.addRow(new Object[]{driverID, names.get(i), rsSearch.getString("Số giấy phép"), rsSearch.getString("Hạng"), 
                        rsSearch.getInt("Lương"), rsSearch.getString("Đánh giá"), rsSearch.getString("Trạng thái")});
                        i++;
                    }        
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                jlEnterIDToShow.setText("không tồn tại ID");
                
                // thiet lap lai place holder
                jtfEnterIDToSearch.setText("nhập ID");
                jtfEnterIDToSearch.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                jtfEnterIDToSearch.setForeground(new Color(153, 153, 153));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public boolean checkExistDriver(int idDriver)
    {
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String searchDriverList = "Select [Driver_ID] From Drivers";
            
            // tạo thể hiện
            PreparedStatement sqlsearchDriverList = this.loginwindow.db.cnt.prepareStatement(searchDriverList);

            // thực hiện truy vấn
            ResultSet rs = sqlsearchDriverList.executeQuery();
            
            boolean checkExistCarID = false;
            
            while(rs.next())
            {
                if(idDriver == rs.getInt("Driver_ID"))
                {
                    checkExistCarID = true;
                    break;
                }
            }
            return checkExistCarID;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DriverManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void editDriver(int idDriver)
    {
        jcbConfirmEditDriver.setSelected(false);
        jlSuccessEditCar.setVisible(false);
        jlWarningConfirm.setText("");
        try
        {
            // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
            String getInforSingleDriver = "Select [Số giấy phép], [Hạng], [Lương], [Đánh giá], [Trạng thái] From Drivers Where Driver_ID = ?";
            
            // tạo thể hiện
            try(PreparedStatement sqlgetInforSingleDriver = this.loginwindow.db.cnt.prepareStatement(getInforSingleDriver))
            {
                sqlgetInforSingleDriver.setInt(1, idDriver);

                // thực hiện truy vấn
                ResultSet rs = sqlgetInforSingleDriver.executeQuery();

                while(rs.next())
                {
                    jtfDriverID.setText(String.valueOf(idDriver));
                    jtfDriverID.enable(false);
                    jtfEditLicenseNumber.setText(rs.getString("Số giấy phép"));
                    jtfEditRank.setText(rs.getString("Hạng"));
                    jtfEditSalary.setText(rs.getString("Lương"));
                    jtfEditEvaluate.setText(rs.getString("Đánh giá"));
                    jtfEditState.setText(rs.getString("Trạng thái"));
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(registerWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPaneAll;
    private javax.swing.JButton jbtnComplete;
    private javax.swing.JButton jbtnComplete1;
    private javax.swing.JButton jbtnDeleteDriver;
    private javax.swing.JButton jbtnDriverEdit;
    private javax.swing.JButton jbtnRefresh;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JCheckBox jcbConfirmEditDriver;
    private javax.swing.JComboBox<String> jcbDay;
    private javax.swing.JComboBox<String> jcbGen;
    private javax.swing.JComboBox<String> jcbMonth;
    private javax.swing.JComboBox<String> jcbYear;
    private javax.swing.JLabel jl;
    private javax.swing.JLabel jlAccount;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarManager;
    private javax.swing.JLabel jlCarID;
    private javax.swing.JLabel jlChangeDriver;
    private javax.swing.JLabel jlCheckOK;
    private javax.swing.JLabel jlConfirm;
    private javax.swing.JLabel jlDayOfBirth;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEnterIDToDelete;
    private javax.swing.JLabel jlEnterIDToEdit;
    private javax.swing.JLabel jlEnterIDToShow;
    private javax.swing.JLabel jlFullName;
    private javax.swing.JLabel jlGmail;
    private javax.swing.JLabel jlIdentityNumber;
    private javax.swing.JLabel jlLABEL;
    private javax.swing.JLabel jlLicenseNumber;
    private javax.swing.JLabel jlListDriver;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPasswordAddNewDriver;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlRank;
    private javax.swing.JLabel jlRePassword;
    private javax.swing.JLabel jlRequest;
    private javax.swing.JLabel jlRole;
    private javax.swing.JLabel jlSalary;
    private javax.swing.JLabel jlSex;
    private javax.swing.JLabel jlShowName;
    private javax.swing.JLabel jlShowPersonalInfor;
    private javax.swing.JLabel jlSuccess;
    private javax.swing.JLabel jlSuccessAddNewDriver;
    private javax.swing.JLabel jlSuccessDeleteDriver;
    private javax.swing.JLabel jlSuccessEditCar;
    private javax.swing.JLabel jlUserID;
    private javax.swing.JLabel jlUserIDAddNewDriver;
    private javax.swing.JLabel jlWarningAddress;
    private javax.swing.JLabel jlWarningConfirm;
    private javax.swing.JLabel jlWarningEmail;
    private javax.swing.JLabel jlWarningIdentityNumber;
    private javax.swing.JLabel jlWarningName;
    private javax.swing.JLabel jlWarningPhoneNumber;
    private javax.swing.JLabel jladdDriver;
    private javax.swing.JLabel jlchangePasswordDriverManager;
    private javax.swing.JLabel jldeleteDriver;
    private javax.swing.JLabel jllogOutDriverManager;
    private javax.swing.JLabel jlnotifyOldPassword;
    private javax.swing.JPanel jpanelActive;
    private javax.swing.JPasswordField jpwfConfirmPassword;
    private javax.swing.JPasswordField jpwfNewPassword;
    private javax.swing.JPasswordField jpwfOldPassword;
    private javax.swing.JPasswordField jpwfPasswordAddNewDriver;
    private javax.swing.JPasswordField jpwfRePassword;
    private javax.swing.JTable jtListDriverToDelete;
    private javax.swing.JTable jtListDriverToEdit;
    private javax.swing.JTextField jtfAccount;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfDriverID;
    private javax.swing.JTextField jtfEditEvaluate;
    private javax.swing.JTextField jtfEditLicenseNumber;
    private javax.swing.JTextField jtfEditRank;
    private javax.swing.JTextField jtfEditSalary;
    private javax.swing.JTextField jtfEditState;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfEnterIDToDelete;
    private javax.swing.JTextField jtfEnterIDToEdit;
    private javax.swing.JTextField jtfEnterIDToSearch;
    private javax.swing.JTextField jtfIdentityNumber;
    private javax.swing.JTextField jtfLicenseNumber;
    private javax.swing.JTextField jtfName;
    private javax.swing.JTextField jtfNumberPhone;
    private javax.swing.JTextField jtfRank;
    private javax.swing.JTextField jtfSalary;
    private javax.swing.JTable jtlistDriverToShow;
    // End of variables declaration//GEN-END:variables
}
