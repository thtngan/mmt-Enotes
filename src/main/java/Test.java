import BE.Model.Account;
import BE.Shared.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

  public static void main(String[] args) {
    ConnectDB cn = new ConnectDB();
    Connection connection = cn.getConnection();
    PreparedStatement ps = null;

    try {
      String sql = "SELECT * FROM ACCOUNT where username = ?";
      PreparedStatement pst = connection.prepareStatement(sql);
      pst.setString (1, "Abc");
      ResultSet rs = pst.executeQuery();
      Account res = new Account();
      if (rs.next()) {
        res = new Account();
        res.setUsername(rs.getString("Username"));
        res.setPassword(rs.getString("Password"));
      }

      if (res.getUsername() == null)
          System.out.println("Hello");

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
