import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GUI {
    private JMenuItem exitMenu;
    private JMenu fileMenu;
    private JMenuItem saveMenu;
    private JMenuBar menuBar;
    private JPanel mainPanel;
    private JTable tourTable;
    private JButton searchButton;
    private JButton resetButton;
    private JComboBox<String> timetableComboBox;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField dateComboBox;
    private JLabel dateLable;
    private JLabel toLable;
    private JLabel fromLable;
    private JLabel timetableLable;
    private JPanel leftPanel;
    public static GUI window;
    public JFrame mainFrame;
    public GUI(){
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeigth = screenSize.height;

        mainFrame.setBounds(screenWidth / 4, screenHeigth / 4, screenWidth / 2, screenHeigth / 2);
        mainFrame.setTitle("ChatApp");
        Image img = kit.getImage("1.jpg");

        leftPanel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        leftPanel.setLayout(layout);

        mainPanel = new JPanel();
        GridBagLayout layout1 = new GridBagLayout();
        mainPanel.setLayout(layout1);

        //leftPanel.setBackground(new Color(0x3D3E3A));

        timetableLable = new JLabel("Timetable: ");
        fromLable = new JLabel("From: ");
        toLable = new JLabel("To: ");
        dateLable = new JLabel("Date: ");

        timetableComboBox = new JComboBox<String>();
        fromComboBox = new JComboBox<String>();
        toComboBox = new JComboBox<String>();
        dateComboBox = new JTextField();

        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");

        tourTable = new JTable();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        saveMenu = new JMenuItem("Save");
        exitMenu = new JMenuItem("Exit");
        fileMenu.add(saveMenu);
        fileMenu.add(exitMenu);
        menuBar.add(fileMenu);
        mainFrame.setJMenuBar(menuBar);
        //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
        leftPanel.add(timetableLable, new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(timetableComboBox, new GridBagConstraints(1,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(fromLable, new GridBagConstraints(0,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(fromComboBox, new GridBagConstraints(1,2,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(toLable, new GridBagConstraints(0,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(toComboBox, new GridBagConstraints(1,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateLable, new GridBagConstraints(0,5,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateComboBox, new GridBagConstraints(1,5,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(searchButton, new GridBagConstraints(0,6,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(resetButton, new GridBagConstraints(1,6,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        mainPanel.add(leftPanel, new GridBagConstraints(0,0,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        mainPanel.add(tourTable, new GridBagConstraints(0,1,1,1,GridBagConstraints.RELATIVE,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));


        mainFrame.add(mainPanel);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    window = new GUI();
                    window.mainFrame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
