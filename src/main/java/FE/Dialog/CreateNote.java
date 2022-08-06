package FE.Dialog;

import BE.Model.Note;
import BE.RMI.IEnote;
import BE.Service.NoteService;
import BE.Shared.ConnectDB;
import FE.Frame.ListNote;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateNote extends JDialog implements Runnable {
  public final static int WIDTH_DIALOG = 470;
  public final static int HEIGHT_DIALOG = 320;

  private IEnote enote_obj;
  private String username;

  private JTextField txtName;
  private JTextArea txtContent;
  private JScrollPane jScrollPane;
  private Choice choice;

  public CreateNote(JFrame owner, IEnote enote_obj, String username) {
    super(owner);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setTitle("CREATE NEW NOTE");
    this.setResizable(true);
    this.getContentPane().setPreferredSize(new Dimension(CreateNote.WIDTH_DIALOG, CreateNote.HEIGHT_DIALOG));
    this.setLayout(null);
    this.pack();

    this.enote_obj = enote_obj;
    this.username = username;
    this.initComponents();

  }

  private void initComponents() {

    // TODO: label
    JLabel label = new JLabel();
    label.setText("CREATE A NEW NOTE");
    label.setFont(new Font("segoe ui", Font.BOLD, 14));
    label.setBounds(150, 20, 500, 30);
    this.add(label);

    // TODO: Name
    JLabel name = new JLabel("Name:");
    name.setBounds(20, 50, 50, 30);
    this.add(name);

    this.txtName = new JTextField("");
    this.txtName.setBounds(80, 50, 370, 30);
    this.add(this.txtName);

    // TODO: Type
    JLabel type = new JLabel("Type:");
    type.setBounds(20, 100, 50, 30);
    this.add(type);

    this.choice = new Choice();
    this.choice.setBounds(80, 100, 370, 30);
    this.choice.add("Text");
    this.choice.add("Image");
    this.choice.add("File");
    this.add(this.choice);

    // TODO: Content
    JLabel content = new JLabel("Content:");
    content.setBounds(20, 150, 60, 30);
    this.add(content);

    this.txtContent = new JTextArea("");
    this.txtContent.setLineWrap(true);
    this.jScrollPane = new JScrollPane(txtContent);
    this.jScrollPane.setBounds(80, 150, 300, 100);
    this.add(jScrollPane);


    Button btnBrowser = new Button("Browser");
    btnBrowser.setBounds(380, 150, 70, 100);
    btnBrowser.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
          chooseFile(e);
      }
    });
    this.add(btnBrowser);

    // TODO: button SAVE
    Button btnSave = new Button("SAVE");
    btnSave.setBounds(20, 260, 430, 30);
    btnSave.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        save(e);
      }
    });
    this.add(btnSave);
  }

  @Override
  public void run() {

  }

  private void save(MouseEvent e) {
    Note note = new Note();
    note.setId(this.txtName.getText());
    note.setType(this.choice.getSelectedItem().toString());
    note.setCreate_time(new Timestamp(System.currentTimeMillis()));
//    note.setContent(this.txtContent.getText());
    try {
      FileInputStream fin = new FileInputStream(this.txtContent.getText());
      note.setContent(fin);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    note.setUser_id(this.username);

    System.out.println(note.toString());

//    NoteService.addNote(note);

    try {
      boolean flag = this.enote_obj.addNote(note);

      if (flag == true) {
        JOptionPane.showMessageDialog(this,"Create a new note successfully");
      }
      else {
        JOptionPane.showMessageDialog(this,"Create a new note fail.","Alert",JOptionPane.WARNING_MESSAGE);
      }

    } catch (RemoteException ex) {
      ex.printStackTrace();
    }

  }

  private void chooseFile(MouseEvent e) {
    if (this.choice.getSelectedIndex() != 0) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
      fileChooser.showDialog(this, "Send");

      File dir = fileChooser.getSelectedFile();
      this.txtContent.setText(dir.getAbsolutePath());
      this.txtContent.setEditable(false);
    } else {
      JOptionPane.showMessageDialog(this,"Only send text","Error",JOptionPane.WARNING_MESSAGE);
    }
  }

}
