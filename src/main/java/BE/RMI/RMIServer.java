package BE.RMI;

import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

  private String url;
  private boolean is_binding;

  public RMIServer() {
    this.url = null;
    this.is_binding = false;
  }

  public void startBindingOnRmiServer(String host, int port) throws RemoteException, MalformedURLException, AWTException {
    if(this.is_binding == false) {
      try {
        this.url = "rmi://" + host + ":" + port + "/remote";
        this.is_binding = true;
        System.setProperty("java.rmi.server.hostname", host);
        LocateRegistry.createRegistry(port);
        Naming.rebind(this.url, new EnoteImpl()); // only new object in here
      }
      catch(Exception e) {
        // TODO: rebind when port already bound
        Naming.rebind(this.url, new EnoteImpl());
        System.out.println("RMI Server: " + e);
      }
    }
  }

  public void stopBindingOnRmiServer() throws RemoteException, MalformedURLException, NotBoundException {
    if(this.is_binding == true) {
      Naming.unbind(this.url);
      this.url = null;
      this.is_binding = false;
    }
  }

  public boolean isBinding() {
    return this.is_binding;
  }

}
