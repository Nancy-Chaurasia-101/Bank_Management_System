import java.awt.*;
import java.sql.*;

class CreateAccounts extends Component {
    public boolean registerUser(String userName, String EmailId, String Password){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountDetails", "root", "N@N3Y7267n@n3y");
            PreparedStatement Act = con.prepareStatement("select * from userdetails");
            ResultSet rs = Act.executeQuery();
            int accout_no = 0;
            int accGreat = 0;
            while (rs.next()) {
                accout_no = rs.getInt("AccountNo");
                if (accGreat < accout_no){
                    accGreat = accout_no;
                }
            }
            if (accout_no == 0) {
                accGreat = 10001;
            }else {
                accGreat++;
            }
            rs.close();
            Act.close();
            PreparedStatement ps = con.prepareStatement("insert into userdetails value(?,?,?,?,?)");
            ps.setInt(1,accGreat);
            ps.setString(2, userName.toUpperCase());
            ps.setString(3, EmailId);
            ps.setString(4, Password);
            ps.setInt(5,0);
            ps.executeUpdate();
            return true;
        }catch (ClassNotFoundException _){
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}