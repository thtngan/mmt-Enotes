package BE.RMI;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class EnoteImpl extends UnicastRemoteObject implements IEnote {
  protected EnoteImpl() throws RemoteException {
  }

  protected EnoteImpl(int port) throws RemoteException {
    super(port);
  }

  protected EnoteImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
    super(port, csf, ssf);
  }
}
