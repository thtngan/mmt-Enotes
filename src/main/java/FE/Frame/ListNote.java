package FE.Frame;

import BE.RMI.IEnote;
import BE.Shared.CommonBus;
import FE.Panel.ClientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class ListNote extends JFrame implements Runnable {
  public final static int WIDTH_DIALOG = 480;
  public final static int HEIGHT_DIALOG = 480;

  private ClientPanel client_panel;
  private CommonBus common_bus;
  private IEnote enote_obj;

  private SignUp signUp;

  public ListNote() {
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setTitle("E-NOTE APPLICATION");
    this.setResizable(false);
    this.getContentPane().setPreferredSize(new Dimension(ListNote.WIDTH_DIALOG, ListNote.HEIGHT_DIALOG));
    this.setLayout(null);
    this.pack();
    this.setVisible(true);

    this.common_bus = common_bus;
//    this.enote_obj = this.common_bus.getRMIClient().getRemoteObject();
    this.client_panel = client_panel;

    //add components
    this.initComponents();
  }

  private void initComponents() {
    JLabel label = new JLabel();
    label.setText("LIST NOTE");
    label.setFont(new Font("segoe ui", Font.BOLD, 30));
    label.setBounds(135, 20, 510, 50);
    this.add(label);

    // TODO: add refresh
    Button refreshBtn = new Button("Refresh");
    refreshBtn.setBounds(340, 80, 110, 25);
//    refreshBtn.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(MouseEvent e) {
//        initListProcess();
//      }
//    });
    this.add(refreshBtn);

    // TODO: add table


    // TODO: add two button
    Button btnAdd = new Button("ADD");
    btnAdd.setBounds(240, 120, 210,30);
//    btnStart.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(MouseEvent e) {
//        startSaveText();
//      }
//    });
    this.add(btnAdd);

    Button btnDel = new Button("DELETE");
    btnDel.setBounds(20, 120, 210,30);
//    btnStop.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(MouseEvent e) {
//        stopSaveText();
//      }
//    });
    this.add(btnDel);

  }

  @Override
  public void run() {

  }



  public static void main(String[] args) {
    new ListNote();
  }
}
