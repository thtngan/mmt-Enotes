package BE.RMI;

import BE.Model.Note;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEnote extends Remote {
  int logIn(String username, String pwd) throws RemoteException;
  boolean signUp(String username, String pwd) throws RemoteException;
  boolean addNote(Note note) throws RemoteException;
}
