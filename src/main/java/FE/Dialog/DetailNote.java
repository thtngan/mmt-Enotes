package FE.Dialog;

import BE.Model.Note;
import BE.RMI.IEnote;
import BE.Service.NoteService;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;

public class DetailNote extends JDialog implements Runnable {
  public final static int WIDTH_DIALOG = 470;
  public final static int HEIGHT_DIALOG = 350;

  private IEnote enote_obj;
  private String username;
  private String id;

  private JTextField txtName;
  private JTextArea txtContent;
  private JScrollPane jScrollPane;
  private Choice choice;

  public DetailNote(JFrame owner, IEnote enote_obj, String username, String id) {
    super(owner);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setTitle("DETAIL NOTE");
    this.setResizable(true);
    this.getContentPane().setPreferredSize(new Dimension(DetailNote.WIDTH_DIALOG, DetailNote.HEIGHT_DIALOG));
    this.setLayout(null);
    this.pack();

    this.enote_obj = enote_obj;
    this.username = username;
    this.id = id;
    this.initComponents();

  }

  private void initComponents() {
    Note note = NoteService.detailNote(this.username, this.id);
    System.out.println(note.toString());
    // TODO: label
    JLabel label = new JLabel();
    label.setText("DETAIL A NOTE");
    label.setFont(new Font("segoe ui", Font.BOLD, 14));
    label.setBounds(150, 20, 500, 30);
    this.add(label);

    // TODO: Name
    JLabel name = new JLabel("Name:");
    name.setBounds(20, 50, 50, 30);
    this.add(name);

    this.txtName = new JTextField("");
    this.txtName.setBounds(80, 50, 370, 30);
    this.txtName.setText(note.getId());
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
    this.choice.select(note.getType());
    this.choice.setEnabled(false);
    this.add(this.choice);

    // TODO: Content
    JLabel content = new JLabel("Content:");
    content.setBounds(20, 150, 60, 30);
    this.add(content);

    this.txtContent = new JTextArea(note.getContent().toString());
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

    // TODO: button DELETE
    Button btnDel = new Button("DELETE");
    btnDel.setBounds(20, 300, 430, 30);
    btnDel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        save(e);
      }
    });
    this.add(btnDel);
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

    NoteService.addNote(note);

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
