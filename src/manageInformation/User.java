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

public class User extends javax.swing.JFrame {

    public loginWindow loginwindow;
    public int userID;
    public int customerID;
    public String nameUser;
    public String username;
    public String password;
    
    public User(int userID, loginWindow loginwindow) {
        this.loginwindow = loginwindow;
        this.userID = userID;
        getCustomerID();
        initComponents();
        setEchor();
        setLabelGen();
        jTabbedPaneUser.setSelectedIndex(0);
        setFirstInformation();
        setSecondInformation();
        setForthInformation();
        setSixthInformation();
        setSeventhInformation();
        setEighthInformation();
        setNinthInformation();
        
    }
    
    public void getCustomerID()
    {
        try
        {
            String getCustomerID = "Select Customer_ID From Customers Where User_ID = ?";
            try(PreparedStatement sqlgetCustomerID = this.loginwindow.db.cnt.prepareStatement(getCustomerID))
            {
                sqlgetCustomerID.setInt(1, this.userID);
                ResultSet rs = sqlgetCustomerID.executeQuery();
                
                while(rs.next())
                {
                    this.customerID = rs.getInt("Customer_ID");
                }
            }
            
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void setFirstInformation() 
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
                jlRole.setText("Khách hàng");
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
        String setTaiKhoan = "   " + this.userID + " - " + this.nameUser;
        jlShowName.setText(setTaiKhoan);
    }
    
    public void setForthInformation() 
    {
        try
        {
            jtfEvaluate.enable(false);
            // thực hiện lấy dữ liệu để in ra panel
            String getSatisfyLevel = "Select [Độ hài lòng] From Customers"
                    + " Where User_ID = ?";

            try(PreparedStatement sqlgetSatisfyLevel = this.loginwindow.db.cnt.prepareStatement(getSatisfyLevel))
            {
                sqlgetSatisfyLevel.setInt(1, this.userID);

                // lấy bảng dữ liệu
                ResultSet rs = sqlgetSatisfyLevel.executeQuery();

                while(rs.next())
                {
                    jtfEvaluate.setText(rs.getString("Độ hài lòng"));
                }
            }
        }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
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
                    + " From Trips Where Customer_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.customerID);
            sqlgetListTrip.setString(2, "Đang chờ");
            
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
                    + " From Trips Where Customer_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.customerID);
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
                    + " From Trips Where Customer_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.customerID);
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
                    + " From Trips Where Customer_ID = ? And [Trạng thái] = ?";
            PreparedStatement sqlgetListTrip = this.loginwindow.db.cnt.prepareStatement(getListTrip);
            sqlgetListTrip.setInt(1, this.customerID);
            sqlgetListTrip.setString(2, "Đã hủy");
            
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
        jlInformationUser = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jlUpdateInformationUser = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jlEvaluateApplication = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jlTripBook = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jlWaitTrip = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jlCompleteTrip = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jlShippingTrip = new javax.swing.JLabel();
        jlCancelTrip = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jTabbedPaneUser = new javax.swing.JTabbedPane();
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
        jPanel5 = new javax.swing.JPanel();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jl1sao = new javax.swing.JLabel();
        jl2sao = new javax.swing.JLabel();
        jl3sao = new javax.swing.JLabel();
        jl4sao = new javax.swing.JLabel();
        jl5sao = new javax.swing.JLabel();
        jlEva5sao = new javax.swing.JLabel();
        jlEva1sao = new javax.swing.JLabel();
        jlEva2sao = new javax.swing.JLabel();
        jlEva3sao = new javax.swing.JLabel();
        jlEva4sao = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jtfEvaluate = new javax.swing.JTextField();
        jbtnUpdateEvaluate = new javax.swing.JButton();
        jlSuccessEvaluate = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jlCarID1 = new javax.swing.JLabel();
        jtfWeight = new javax.swing.JTextField();
        jlCarID2 = new javax.swing.JLabel();
        jtfStartPoint = new javax.swing.JTextField();
        jlCarID3 = new javax.swing.JLabel();
        jtfDestinationPoint = new javax.swing.JTextField();
        jlWeight = new javax.swing.JLabel();
        jlStartPoint = new javax.swing.JLabel();
        jlDestinationPoint = new javax.swing.JLabel();
        jlCarID4 = new javax.swing.JLabel();
        jtfDistanceSum = new javax.swing.JTextField();
        jlDistanceSum = new javax.swing.JLabel();
        jbtnTripBook = new javax.swing.JButton();
        jcbConfirmBook = new javax.swing.JCheckBox();
        jlConfirmBook = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtWaitTrip = new javax.swing.JTable();
        jbtnDeleteTrip = new javax.swing.JButton();
        jlWarning = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtShippingTrip = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtCompleteTrip = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtCancelTrip = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logo VIN.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlName.setForeground(new java.awt.Color(202, 46, 46));

        jlMailBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlMailBox.setForeground(new java.awt.Color(51, 102, 255));
        jlMailBox.setText("Thông báo");
        jlMailBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlMailBox.setOpaque(true);
        jlMailBox.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlMailBoxMouseMoved(evt);
            }
        });
        jlMailBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlMailBoxMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlMailBoxMouseExited(evt);
            }
        });

        jlChangePassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlChangePassword.setForeground(new java.awt.Color(51, 102, 255));
        jlChangePassword.setText("Đổi mật khẩu");
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

        jlExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlExit.setForeground(new java.awt.Color(51, 102, 255));
        jlExit.setText("Thoát đăng nhập");
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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("HỒ SƠ NGƯỜI DÙNG");

        jlInformationUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlInformationUser.setForeground(new java.awt.Color(51, 102, 255));
        jlInformationUser.setText("Thông tin người dùng");
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

        jlUpdateInformationUser.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlUpdateInformationUser.setForeground(new java.awt.Color(51, 102, 255));
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

        jlEvaluateApplication.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlEvaluateApplication.setForeground(new java.awt.Color(51, 102, 255));
        jlEvaluateApplication.setText("Đánh giá ứng dụng");
        jlEvaluateApplication.setToolTipText("");
        jlEvaluateApplication.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlEvaluateApplication.setOpaque(true);
        jlEvaluateApplication.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlEvaluateApplicationMouseMoved(evt);
            }
        });
        jlEvaluateApplication.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlEvaluateApplicationMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlEvaluateApplicationMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 255));
        jLabel2.setText("QUẢN LÝ ĐƠN HÀNG");

        jlTripBook.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlTripBook.setForeground(new java.awt.Color(51, 102, 255));
        jlTripBook.setText("Đặt chuyến hàng");
        jlTripBook.setToolTipText("");
        jlTripBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlTripBook.setOpaque(true);
        jlTripBook.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlTripBookMouseMoved(evt);
            }
        });
        jlTripBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlTripBookMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlTripBookMouseExited(evt);
            }
        });

        jlWaitTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlWaitTrip.setForeground(new java.awt.Color(51, 102, 255));
        jlWaitTrip.setText("Đơn hàng chờ xác nhận");
        jlWaitTrip.setToolTipText("");
        jlWaitTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlWaitTrip.setOpaque(true);
        jlWaitTrip.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jlWaitTripMouseMoved(evt);
            }
        });
        jlWaitTrip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlWaitTripMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlWaitTripMouseExited(evt);
            }
        });

        jlCompleteTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCompleteTrip.setForeground(new java.awt.Color(51, 102, 255));
        jlCompleteTrip.setText("Đơn hàng giao thành công");
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/resource/logovantai.png"))); // NOI18N

        jlShippingTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlShippingTrip.setForeground(new java.awt.Color(51, 102, 255));
        jlShippingTrip.setText("Đơn hàng đang giao");
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

        jlCancelTrip.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCancelTrip.setForeground(new java.awt.Color(51, 102, 255));
        jlCancelTrip.setText("Đơn hàng đã hủy");
        jlCancelTrip.setToolTipText("");
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
                                .addComponent(jlTripBook, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(jlUpdateInformationUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlMailBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlInformationUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlEvaluateApplication, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jlWaitTrip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCompleteTrip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlShippingTrip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCancelTrip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jlInformationUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlUpdateInformationUser, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlEvaluateApplication, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jlTripBook, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlWaitTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlShippingTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCompleteTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlCancelTrip, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(154, 154, 154)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(676, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, -1));

        jTabbedPaneUser.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneUser.setOpaque(true);

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

        jTabbedPaneUser.addTab("", jPanel3);

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

        jTabbedPaneUser.addTab("", jPanel4);

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

        jTabbedPaneUser.addTab("", jPanel11);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 95, 194, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("ĐÁNH GIÁ ỨNG DỤNG");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 64, -1, -1));

        jl1sao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/1sao.jpg"))); // NOI18N
        jl1sao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl1sao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl1saoMouseClicked(evt);
            }
        });
        jPanel5.add(jl1sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 209, -1, -1));

        jl2sao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/2sao.jpg"))); // NOI18N
        jl2sao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl2sao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl2saoMouseClicked(evt);
            }
        });
        jPanel5.add(jl2sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 199, -1, -1));

        jl3sao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/3sao.jpg"))); // NOI18N
        jl3sao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl3sao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl3saoMouseClicked(evt);
            }
        });
        jPanel5.add(jl3sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(376, 189, -1, -1));

        jl4sao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/4sao.jpg"))); // NOI18N
        jl4sao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl4sao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl4saoMouseClicked(evt);
            }
        });
        jPanel5.add(jl4sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(478, 179, -1, -1));

        jl5sao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/5sao.jpg"))); // NOI18N
        jl5sao.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl5sao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl5saoMouseClicked(evt);
            }
        });
        jPanel5.add(jl5sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 169, -1, -1));

        jlEva5sao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlEva5sao.setForeground(new java.awt.Color(51, 255, 0));
        jlEva5sao.setText(" Rất hài lòng");
        jPanel5.add(jlEva5sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, -1, 20));

        jlEva1sao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlEva1sao.setForeground(new java.awt.Color(204, 0, 0));
        jlEva1sao.setText(" Rất kém");
        jPanel5.add(jlEva1sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        jlEva2sao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlEva2sao.setForeground(new java.awt.Color(204, 0, 0));
        jlEva2sao.setText(" Kém");
        jPanel5.add(jlEva2sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, -1));

        jlEva3sao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlEva3sao.setForeground(new java.awt.Color(51, 51, 255));
        jlEva3sao.setText(" Tạm ổn");
        jlEva3sao.setToolTipText("");
        jPanel5.add(jlEva3sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, -1, 20));

        jlEva4sao.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jlEva4sao.setForeground(new java.awt.Color(51, 51, 255));
        jlEva4sao.setText("Hài lòng");
        jPanel5.add(jlEva4sao, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, -1, 20));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Đánh giá:");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 80, 50));

        jtfEvaluate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel5.add(jtfEvaluate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 410, 50));

        jbtnUpdateEvaluate.setBackground(new java.awt.Color(51, 51, 255));
        jbtnUpdateEvaluate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnUpdateEvaluate.setForeground(new java.awt.Color(255, 255, 255));
        jbtnUpdateEvaluate.setText("Cập nhật");
        jbtnUpdateEvaluate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnUpdateEvaluate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateEvaluateActionPerformed(evt);
            }
        });
        jPanel5.add(jbtnUpdateEvaluate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, 40));

        jlSuccessEvaluate.setBackground(new java.awt.Color(153, 255, 153));
        jlSuccessEvaluate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jlSuccessEvaluate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/check-box.png"))); // NOI18N
        jlSuccessEvaluate.setText("Thành công! Bạn đã cập nhật xe thành công.");
        jlSuccessEvaluate.setOpaque(true);
        jPanel5.add(jlSuccessEvaluate, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, 310, 30));

        jTabbedPaneUser.addTab("", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sourceIMG/newImage/important.jpg"))); // NOI18N
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 204, 0));
        jLabel24.setText("THÔNG TIN ĐẶT HÀNG");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, 43));

        jlCarID1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID1.setText("Khối lượng hàng:");
        jPanel6.add(jlCarID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, 30));

        jtfWeight.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jtfWeight.setForeground(new java.awt.Color(153, 153, 153));
        jtfWeight.setText("khối lượng hàng");
        jtfWeight.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfWeight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfWeightFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfWeightFocusLost(evt);
            }
        });
        jPanel6.add(jtfWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 610, 37));

        jlCarID2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID2.setText("Điểm xuất phát:");
        jPanel6.add(jlCarID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 30));

        jtfStartPoint.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jtfStartPoint.setForeground(new java.awt.Color(153, 153, 153));
        jtfStartPoint.setText("điểm xuất phát");
        jtfStartPoint.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfStartPoint.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfStartPointFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfStartPointFocusLost(evt);
            }
        });
        jPanel6.add(jtfStartPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, 610, 37));

        jlCarID3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID3.setText("Điểm kết thúc:");
        jPanel6.add(jlCarID3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 30));

        jtfDestinationPoint.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jtfDestinationPoint.setForeground(new java.awt.Color(153, 153, 153));
        jtfDestinationPoint.setText("điểm kết thúc");
        jtfDestinationPoint.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfDestinationPoint.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfDestinationPointFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDestinationPointFocusLost(evt);
            }
        });
        jPanel6.add(jtfDestinationPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 610, 37));

        jlWeight.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlWeight, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 610, 20));

        jlStartPoint.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlStartPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 610, 20));

        jlDestinationPoint.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlDestinationPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 610, 20));

        jlCarID4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jlCarID4.setText("Tổng quãng đường:");
        jPanel6.add(jlCarID4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, 30));

        jtfDistanceSum.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jtfDistanceSum.setForeground(new java.awt.Color(153, 153, 153));
        jtfDistanceSum.setText("tổng quãng đường");
        jtfDistanceSum.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtfDistanceSum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtfDistanceSumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtfDistanceSumFocusLost(evt);
            }
        });
        jPanel6.add(jtfDistanceSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 610, 37));

        jlDistanceSum.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlDistanceSum, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 610, 20));

        jbtnTripBook.setBackground(new java.awt.Color(51, 51, 255));
        jbtnTripBook.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnTripBook.setForeground(new java.awt.Color(255, 255, 255));
        jbtnTripBook.setText("Đặt hàng");
        jbtnTripBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnTripBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTripBookActionPerformed(evt);
            }
        });
        jPanel6.add(jbtnTripBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 120, 40));

        jcbConfirmBook.setBackground(new java.awt.Color(255, 255, 255));
        jcbConfirmBook.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jcbConfirmBook.setText("Tôi xác nhận đặt hàng");
        jcbConfirmBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcbConfirmBook.setOpaque(true);
        jPanel6.add(jcbConfirmBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, -1, -1));

        jlConfirmBook.setForeground(new java.awt.Color(255, 0, 51));
        jPanel6.add(jlConfirmBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 100, 20));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel26.setText("CHÚC QUÝ KHÁCH TRẢI NGHIỆM DỊCH VỤ HÀI LÒNG");
        jPanel6.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 730, 380, 50));

        jTabbedPaneUser.addTab("", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 890, 710));

        jbtnDeleteTrip.setBackground(new java.awt.Color(51, 51, 255));
        jbtnDeleteTrip.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jbtnDeleteTrip.setForeground(new java.awt.Color(255, 255, 255));
        jbtnDeleteTrip.setText("Hủy đơn");
        jbtnDeleteTrip.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jbtnDeleteTrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteTripActionPerformed(evt);
            }
        });
        jPanel7.add(jbtnDeleteTrip, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 18, -1, -1));

        jlWarning.setForeground(new java.awt.Color(204, 0, 0));
        jPanel7.add(jlWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 240, 20));

        jTabbedPaneUser.addTab("", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel8.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 79, 900, 714));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(204, 0, 0));
        jLabel27.setText("ĐƠN HÀNG ĐANG GIAO");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(356, 20, -1, -1));

        jTabbedPaneUser.addTab("", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));
        jLabel28.setText("ĐƠN HÀNG GIAO THÀNH CÔNG");
        jPanel9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 32, -1, -1));

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

        jPanel9.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 75, 898, 718));

        jTabbedPaneUser.addTab("", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));
        jLabel29.setText("ĐƠN HÀNG ĐÃ HỦY");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(371, 371, 371))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneUser.addTab("", jPanel10);

        getContentPane().add(jTabbedPaneUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 830));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 910, 40));

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

    private void jlMailBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jlMailBoxMouseClicked

    private void jlMailBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailBoxMouseExited
        // TODO add your handling code here:
        jlMailBox.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlMailBoxMouseExited

    private void jlMailBoxMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlMailBoxMouseMoved
        // TODO add your handling code here:
        jlMailBox.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlMailBoxMouseMoved

    private void jlChangePasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseClicked
        // TODO add your handling code here:
        jlSuccess.setVisible(false);
        jlnotifyOldPassword.setText("");
        jlRequest.setText("");
        jlConfirm.setText("");
        jTabbedPaneUser.setSelectedIndex(1);
    }//GEN-LAST:event_jlChangePasswordMouseClicked

    private void jlChangePasswordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseExited
        // TODO add your handling code here:
        jlChangePassword.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlChangePasswordMouseExited

    private void jlChangePasswordMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlChangePasswordMouseMoved
        // TODO add your handling code here:
        jlChangePassword.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlChangePasswordMouseMoved

    private void jlInformationUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseClicked
        // TODO add your handling code here:
        jlSuccess.setVisible(false);
        jTabbedPaneUser.setSelectedIndex(0);
    }//GEN-LAST:event_jlInformationUserMouseClicked

    private void jlInformationUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseExited
        // TODO add your handling code here:
        jlInformationUser.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlInformationUserMouseExited

    private void jlInformationUserMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlInformationUserMouseMoved
        // TODO add your handling code here:
        jlInformationUser.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlInformationUserMouseMoved

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
                            setFirstInformation();
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

    private void jtfEditDayOfBirthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditDayOfBirthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditDayOfBirthActionPerformed

    private void jtfEditIdentityNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditIdentityNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditIdentityNumberActionPerformed

    private void jtfEditAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfEditAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfEditAddressActionPerformed

    private void jlUpdateInformationUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseClicked
        // TODO add your handling code here:
        editUser(this.userID);
        jTabbedPaneUser.setSelectedIndex(2);
    }//GEN-LAST:event_jlUpdateInformationUserMouseClicked

    private void jlUpdateInformationUserMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseMoved
        // TODO add your handling code here:
        jlUpdateInformationUser.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlUpdateInformationUserMouseMoved

    private void jlUpdateInformationUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlUpdateInformationUserMouseExited
        // TODO add your handling code here:
        jlUpdateInformationUser.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlUpdateInformationUserMouseExited

    private void jtfEditMailUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditMailUserFocusGained
        // TODO add your handling code here:
        jlWarningEmail.setText("");
    }//GEN-LAST:event_jtfEditMailUserFocusGained

    private void jtfEditIdentityNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditIdentityNumberFocusGained
        // TODO add your handling code here:
        jlWarningIdentityNumber.setText("");
    }//GEN-LAST:event_jtfEditIdentityNumberFocusGained

    private void jtfEditPhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfEditPhoneNumberFocusGained
        // TODO add your handling code here:
        jlWarningPhoneNumber.setText("");
    }//GEN-LAST:event_jtfEditPhoneNumberFocusGained

    private void jlEvaluateApplicationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEvaluateApplicationMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jTabbedPaneUser.setSelectedIndex(3);
    }//GEN-LAST:event_jlEvaluateApplicationMouseClicked

    private void jlEvaluateApplicationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEvaluateApplicationMouseExited
        // TODO add your handling code here:
        jlEvaluateApplication.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlEvaluateApplicationMouseExited

    private void jlEvaluateApplicationMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlEvaluateApplicationMouseMoved
        // TODO add your handling code here:
        jlEvaluateApplication.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlEvaluateApplicationMouseMoved

    private void jl1saoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl1saoMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jtfEvaluate.setText("Rất kém");
    }//GEN-LAST:event_jl1saoMouseClicked

    private void jl2saoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl2saoMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jtfEvaluate.setText("Kém");
    }//GEN-LAST:event_jl2saoMouseClicked

    private void jl3saoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl3saoMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jtfEvaluate.setText("Tạm ổn");
    }//GEN-LAST:event_jl3saoMouseClicked

    private void jl4saoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl4saoMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jtfEvaluate.setText("Hài lòng");     
    }//GEN-LAST:event_jl4saoMouseClicked

    private void jl5saoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl5saoMouseClicked
        // TODO add your handling code here:
        jlSuccessEvaluate.setVisible(false);
        jtfEvaluate.setText("Rất hài lòng");
    }//GEN-LAST:event_jl5saoMouseClicked

    private void jbtnUpdateEvaluateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateEvaluateActionPerformed
        // TODO add your handling code here:
   
        // câu truy vấn quét để kiểm tra xem mã ID có tồn tại không
        
        String satisfyLevel = jtfEvaluate.getText();
        
        String updateSatisfy = "Update [Customers] "
        + "Set [Độ hài lòng] = ?"
        + " Where User_ID = ?";
        
        try
        {
            // tạo thể hiện
            PreparedStatement sqlupdateSatisfy = this.loginwindow.db.cnt.prepareStatement(updateSatisfy);

            sqlupdateSatisfy.setString(1, satisfyLevel);
            sqlupdateSatisfy.setInt(2, this.userID);

            // thực hiện truy vấn
            sqlupdateSatisfy.executeUpdate();

            // hiện label success
            jlSuccessEvaluate.setVisible(true);
            }
        catch(SQLException ex)
        {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnUpdateEvaluateActionPerformed

    private void jtfStartPointFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfStartPointFocusGained
        // TODO add your handling code here:
        if (jtfStartPoint.getText().equals("điểm xuất phát"))
        {
            jtfStartPoint.setText("");
            jlStartPoint.setText("");
            jtfStartPoint.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfStartPoint.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfStartPointFocusGained

    private void jtfStartPointFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfStartPointFocusLost
        // TODO add your handling code here:
        if (jtfStartPoint.getText().equals(""))
        {
            jtfStartPoint.setText("điểm xuất phát");
            jtfStartPoint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfStartPoint.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfStartPointFocusLost

    private void jtfDestinationPointFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationPointFocusGained
        // TODO add your handling code here:
        if (jtfDestinationPoint.getText().equals("điểm kết thúc"))
        {
            jtfDestinationPoint.setText("");
            jlDestinationPoint.setText("");
            jtfDestinationPoint.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfDestinationPoint.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfDestinationPointFocusGained

    private void jtfDestinationPointFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDestinationPointFocusLost
        // TODO add your handling code here:
        if (jtfDestinationPoint.getText().equals(""))
        {
            jtfDestinationPoint.setText("điểm kết thúc");
            jtfDestinationPoint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfDestinationPoint.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfDestinationPointFocusLost

    private void jtfDistanceSumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDistanceSumFocusGained
        // TODO add your handling code here:
        if (jtfDistanceSum.getText().equals("tổng quãng đường"))
        {
            jtfDistanceSum.setText("");
            jlDistanceSum.setText("");
            jtfDistanceSum.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfDistanceSum.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfDistanceSumFocusGained

    private void jtfDistanceSumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfDistanceSumFocusLost
        // TODO add your handling code here:
        if (jtfDistanceSum.getText().equals(""))
        {
            jtfDistanceSum.setText("tổng quãng đường");
            jtfDistanceSum.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfDistanceSum.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfDistanceSumFocusLost

    private void jtfWeightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfWeightFocusGained
        // TODO add your handling code here:
        if (jtfWeight.getText().equals("khối lượng hàng"))
        {
            jtfWeight.setText("");
            jlWeight.setText("");
            jtfWeight.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            jtfWeight.setForeground(new Color(0, 0, 0));
        }
    }//GEN-LAST:event_jtfWeightFocusGained

    private void jtfWeightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtfWeightFocusLost
        // TODO add your handling code here:
        if (jtfWeight.getText().equals(""))
        {
            jtfWeight.setText("khối lượng hàng");
            jtfWeight.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            jtfWeight.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtfWeightFocusLost

    private void jlTripBookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTripBookMouseClicked
        // TODO add your handling code here:
        jcbConfirmBook.setSelected(false);
        jTabbedPaneUser.setSelectedIndex(4);
    }//GEN-LAST:event_jlTripBookMouseClicked

    private void jlTripBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTripBookMouseExited
        // TODO add your handling code here:
        jlTripBook.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlTripBookMouseExited

    private void jlTripBookMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlTripBookMouseMoved
        // TODO add your handling code here:
        jlTripBook.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jlTripBookMouseMoved

    private void jbtnTripBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTripBookActionPerformed
        // TODO add your handling code here:
        
        jlStartPoint.setText("");
        jlDestinationPoint.setText("");
        jlDistanceSum.setText("");
        jlWeight.setText("");
        jlConfirmBook.setText("");
        
        String startp = jtfStartPoint.getText();
        String destp = jtfDestinationPoint.getText();
        String distances = jtfDistanceSum.getText();
        String wei = jtfWeight.getText();
        boolean checkCommit = jcbConfirmBook.isSelected();
        
        if(startp.equals("điểm xuất phát") || destp.equals("điểm kết thúc") || distances.equals("tổng quãng đường") || wei.equals("khối lượng hàng"))
        {
            if(startp.equals("điểm xuất phát"))
            {
                jlStartPoint.setText("bắt buộc");
            }
            
            if(destp.equals("điểm kết thúc"))
            {
               jlDestinationPoint.setText("bắt buộc"); 
            }
            
            if(distances.equals("tổng quãng đường"))
            {
                jlDistanceSum.setText("bắt buộc");
            }
            
            if(wei.equals("khối lượng hàng"))
            {
                jlWeight.setText("bắt buộc");
            }
        }
        else
        {
            if(!checkCommit)
            {
                jlConfirmBook.setText("bắt buộc");
            }
            else
            {
                // chuyen string to int 
                // quang duong
                int intdistances = Integer.parseInt(distances);
                
                // khoi luong hang
                int intwei = Integer.parseInt(wei);
                
                // tinh tong so tien can thanh toan
                int pay = intdistances * 10000 * intwei;   
                
                // thoi gian dat hang
                Timestamp datetime = new Timestamp(System.currentTimeMillis());
                
                try
                {
                    // dua du lieu vao database Trips

                    String createTrip = "Insert Into Trips([Customer_ID], [Điểm xuất phát], [Điểm kết thúc], [Thời gian đặt hàng], [Tổng quãng đường], [Khối lượng hàng], [Phí dịch vụ], [Trạng thái])"
                            + " Values(?, ?, ?, ?, ?, ?, ?, ?)";

                    try(PreparedStatement sqlcreateTrip = this.loginwindow.db.cnt.prepareStatement(createTrip))
                    {
                        sqlcreateTrip.setInt(1, this.customerID);
                        sqlcreateTrip.setString(2, startp);
                        sqlcreateTrip.setString(3, destp);
                        sqlcreateTrip.setTimestamp(4, datetime);
                        sqlcreateTrip.setInt(5, intdistances);
                        sqlcreateTrip.setInt(6, intwei);
                        sqlcreateTrip.setInt(7, pay);
                        sqlcreateTrip.setString(8, "Đang chờ");
                        
                        sqlcreateTrip.executeUpdate();
                    }
                    
                    String getTripID = "SELECT TOP 1 * FROM Trips ORDER BY Trip_ID DESC ";
                    
                    int tripID = -1;
                    try(PreparedStatement sqlgetTripID = this.loginwindow.db.cnt.prepareStatement(getTripID))
                    {


                        // System.out.println("Here!");
                        ResultSet rs = sqlgetTripID.executeQuery();
                        while(rs.next())
                        {
                            tripID = rs.getInt("Trip_ID");
                        }
                    }                    
                    this.enable(false);
                    setSixthInformation();
                    new Bill(this, datetime, tripID, startp, destp, intdistances, intwei, pay).setVisible(true);                   
                    // set place holder
                    jtfStartPoint.setText("điểm xuất phát");
                    jtfStartPoint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfStartPoint.setForeground(new Color(153, 153, 153));
                    
                    jtfDestinationPoint.setText("điểm kết thúc");
                    jtfDestinationPoint.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfDestinationPoint.setForeground(new Color(153, 153, 153));
                    
                    jtfDistanceSum.setText("tổng quãng đường");
                    jtfDistanceSum.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfDistanceSum.setForeground(new Color(153, 153, 153));
                    
                    jtfWeight.setText("khối lượng hàng");
                    jtfWeight.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                    jtfWeight.setForeground(new Color(153, 153, 153));

                }
                catch(SQLException ex)
                {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
    }//GEN-LAST:event_jbtnTripBookActionPerformed

    private void jbtnDeleteTripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteTripActionPerformed
        // TODO add your handling code here:
        
        String updateTripStatus = "Update [Trips] "
        + "Set [Trạng thái] = ?"
        + " Where Trip_ID = ?";
        
        try
        {
            int row = jtWaitTrip.getSelectedRow();
            
            if(row == -1)
            {
                jlWarning.setText("row = -1");
            }
            else
            {
                jlWarning.setText("");
                int stripID = Integer.parseInt(String.valueOf(jtWaitTrip.getValueAt(row, 0)));
                
                // tạo thể hiện
                PreparedStatement sqlupdateTripStatus = this.loginwindow.db.cnt.prepareStatement(updateTripStatus);

                sqlupdateTripStatus.setString(1, "Đã hủy");
                sqlupdateTripStatus.setInt(2, stripID);

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
        
    }//GEN-LAST:event_jbtnDeleteTripActionPerformed

    private void jlWaitTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlWaitTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneUser.setSelectedIndex(5);
    }//GEN-LAST:event_jlWaitTripMouseClicked

    private void jlWaitTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlWaitTripMouseExited
        // TODO add your handling code here:
        jlWaitTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlWaitTripMouseExited

    private void jlWaitTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlWaitTripMouseMoved
        // TODO add your handling code here:
        jlWaitTrip.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlWaitTripMouseMoved

    private void jlShippingTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneUser.setSelectedIndex(6);
        
    }//GEN-LAST:event_jlShippingTripMouseClicked

    private void jlShippingTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseExited
        // TODO add your handling code here:
        jlShippingTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlShippingTripMouseExited

    private void jlShippingTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlShippingTripMouseMoved
        // TODO add your handling code here:
        jlShippingTrip.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlShippingTripMouseMoved

    private void jlCompleteTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneUser.setSelectedIndex(7);
    }//GEN-LAST:event_jlCompleteTripMouseClicked

    private void jlCompleteTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseExited
        // TODO add your handling code here:
        jlCompleteTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlCompleteTripMouseExited

    private void jlCompleteTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCompleteTripMouseMoved
        // TODO add your handling code here:
        jlCompleteTrip.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlCompleteTripMouseMoved

    private void jlCancelTripMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseClicked
        // TODO add your handling code here:
        jTabbedPaneUser.setSelectedIndex(8);
    }//GEN-LAST:event_jlCancelTripMouseClicked

    private void jlCancelTripMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseExited
        // TODO add your handling code here:
        jlCancelTrip.setBackground(new Color(242,242,242));
    }//GEN-LAST:event_jlCancelTripMouseExited

    private void jlCancelTripMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlCancelTripMouseMoved
        // TODO add your handling code here:
        jlCancelTrip.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_jlCancelTripMouseMoved

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
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
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
    private javax.swing.JTabbedPane jTabbedPaneUser;
    private javax.swing.JButton jbtnDeleteTrip;
    private javax.swing.JButton jbtnSubmit;
    private javax.swing.JButton jbtnTripBook;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JButton jbtnUpdateEvaluate;
    private javax.swing.JCheckBox jcbConfirm;
    private javax.swing.JCheckBox jcbConfirmBook;
    private javax.swing.JCheckBox jcbConfirmEditUser;
    private javax.swing.JLabel jl1sao;
    private javax.swing.JLabel jl2sao;
    private javax.swing.JLabel jl3sao;
    private javax.swing.JLabel jl4sao;
    private javax.swing.JLabel jl5sao;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlAvatarCustomer;
    private javax.swing.JLabel jlCancelTrip;
    private javax.swing.JLabel jlCarID;
    private javax.swing.JLabel jlCarID1;
    private javax.swing.JLabel jlCarID2;
    private javax.swing.JLabel jlCarID3;
    private javax.swing.JLabel jlCarID4;
    private javax.swing.JLabel jlChangePassword;
    private javax.swing.JLabel jlCheckOK;
    private javax.swing.JLabel jlCompleteTrip;
    private javax.swing.JLabel jlConfirm;
    private javax.swing.JLabel jlConfirmBook;
    private javax.swing.JLabel jlDayOfBirth;
    private javax.swing.JLabel jlDestinationPoint;
    private javax.swing.JLabel jlDistanceSum;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEva1sao;
    private javax.swing.JLabel jlEva2sao;
    private javax.swing.JLabel jlEva3sao;
    private javax.swing.JLabel jlEva4sao;
    private javax.swing.JLabel jlEva5sao;
    private javax.swing.JLabel jlEvaluateApplication;
    private javax.swing.JLabel jlExit;
    private javax.swing.JLabel jlFullName;
    private javax.swing.JLabel jlIdentityNumber;
    private javax.swing.JLabel jlInformationUser;
    private javax.swing.JLabel jlMailBox;
    private javax.swing.JLabel jlName;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlRequest;
    private javax.swing.JLabel jlRole;
    private javax.swing.JLabel jlSex;
    private javax.swing.JLabel jlShippingTrip;
    private javax.swing.JLabel jlShowName;
    private javax.swing.JLabel jlStartPoint;
    private javax.swing.JLabel jlSuccess;
    private javax.swing.JLabel jlSuccessEditUser;
    private javax.swing.JLabel jlSuccessEvaluate;
    private javax.swing.JLabel jlTripBook;
    private javax.swing.JLabel jlUpdateInformationUser;
    private javax.swing.JLabel jlUserID;
    private javax.swing.JLabel jlWaitTrip;
    private javax.swing.JLabel jlWarning;
    private javax.swing.JLabel jlWarningConfirm;
    private javax.swing.JLabel jlWarningEmail;
    private javax.swing.JLabel jlWarningIdentityNumber;
    private javax.swing.JLabel jlWarningPhoneNumber;
    private javax.swing.JLabel jlWeight;
    private javax.swing.JLabel jlnotifyOldPassword;
    private javax.swing.JPasswordField jpwfConfirmPassword;
    private javax.swing.JPasswordField jpwfNewPassword;
    private javax.swing.JPasswordField jpwfOldPassword;
    private javax.swing.JTable jtCancelTrip;
    private javax.swing.JTable jtCompleteTrip;
    private javax.swing.JTable jtShippingTrip;
    private javax.swing.JTable jtWaitTrip;
    private javax.swing.JTextField jtfDestinationPoint;
    private javax.swing.JTextField jtfDistanceSum;
    private javax.swing.JTextField jtfEditAddress;
    private javax.swing.JTextField jtfEditDayOfBirth;
    private javax.swing.JTextField jtfEditIdentityNumber;
    private javax.swing.JTextField jtfEditMailUser;
    private javax.swing.JTextField jtfEditPhoneNumber;
    private javax.swing.JTextField jtfEditSexUser;
    private javax.swing.JTextField jtfEditUserName;
    private javax.swing.JTextField jtfEvaluate;
    private javax.swing.JTextField jtfStartPoint;
    private javax.swing.JTextField jtfUserID;
    private javax.swing.JTextField jtfWeight;
    // End of variables declaration//GEN-END:variables
}
