import BE.Model.Account;
import BE.Model.Note;
import BE.Service.AccountService;
import BE.Service.NoteService;
import BE.Shared.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    Note note = new Note();

    List<Note> a = NoteService.getAllNote("username");
    a.forEach(note1 -> System.out.println(note1.toString()));

  }
}
