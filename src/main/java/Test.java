import BE.Model.Account;
import BE.Service.AccountService;
import BE.Shared.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

  public static void main(String[] args) {
    Account account = new Account();
    account.setUsername("user1");
    account.setPassword("12345");

    AccountService.addOne(account);

  }
}
