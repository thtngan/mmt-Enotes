package BE.RMI;

import BE.Model.Account;
import BE.Model.Note;
import BE.Service.AccountService;
import BE.Service.NoteService;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class EnoteImpl extends UnicastRemoteObject implements IEnote {
  protected EnoteImpl() throws RemoteException {
  }

  protected EnoteImpl(int port) throws RemoteException {
    super(port);
  }

  protected EnoteImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
    super(port, csf, ssf);
  }


  @Override
  public int logIn(String username, String pwd) throws RemoteException {
    Account account = AccountService.getByUsername(username);
//    System.out.println(account.toString());
    if (account == null) {
      return 1;
    } else {
      if (!BCrypt.checkpw(pwd, account.getPassword())) {
        return 2;
      }
    }
    return 0;
  }

  @Override
  public boolean signUp(String username, String pwd) throws RemoteException {
    Account account = new Account(username, pwd);
    return AccountService.addOne(account);
  }

  @Override
  public boolean addNote(Note note) throws RemoteException {
    return NoteService.addNote(note);
  }

  @Override
  public ArrayList<Note> getAllNote(String username) throws RemoteException {
    return NoteService.getAllNote(username);
  }

  @Override
  public Note getNote(String username, String id) throws RemoteException {
    return NoteService.detailNote(username, id);
  }
}
