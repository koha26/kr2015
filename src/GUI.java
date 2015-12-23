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
    private JButton addButton;
    private JLabel dateLable;
    private JLabel toLable;
    private JLabel fromLable;
    private JPanel leftPanel;
    private JPanel tablePanel;
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextField dateTextField;
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

        tablePanel = new JPanel();

        fromLable = new JLabel("From: ");
        fromLable.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        toLable = new JLabel("To: ");
        toLable.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        dateLable = new JLabel("Date: ");
        dateLable.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        fromTextField = new JTextField();
        toTextField = new JTextField();
        dateTextField = new JTextField();

        searchButton = new JButton("Search");
        resetButton = new JButton("Reset");
        addButton = new JButton("Add tour");

        tourTable = new JTable();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        saveMenu = new JMenuItem("Save");
        exitMenu = new JMenuItem("Exit");
        fileMenu.add(saveMenu);

        fileMenu.add(exitMenu);
        menuBar.add(fileMenu);
        mainFrame.setJMenuBar(menuBar);

        tablePanel.setLayout(layout1);
        MyTableDataModel tableModel=new MyTableDataModel();
        MyTableDataView tableView=new MyTableDataView(tableModel);
        //JTextArea textArea = new JTextArea("Здесь вместо текст ареа - таблица");
        tablePanel.add(tableView.getTable(), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

        //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
        leftPanel.add(fromLable, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(fromTextField, new GridBagConstraints(1,0,2,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        leftPanel.add(toLable, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(toTextField, new GridBagConstraints(1,1,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateLable, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateTextField, new GridBagConstraints(1,2,2,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
        leftPanel.add(searchButton, new GridBagConstraints(0,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(resetButton, new GridBagConstraints(1,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(addButton, new GridBagConstraints(2,3,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));

       /* leftPanel.add(fromLable, new GridBagConstraints(0,0,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(fromTextField, new GridBagConstraints(0,1,3,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(toLable, new GridBagConstraints(0,2,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(toTextField, new GridBagConstraints(0,3,3,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateLable, new GridBagConstraints(0,4,3,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(dateTextField, new GridBagConstraints(0,5,3,1,1,0,GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(searchButton, new GridBagConstraints(0,6,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(resetButton, new GridBagConstraints(1,6,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        leftPanel.add(addButton, new GridBagConstraints(2,6,1,1,1,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));*/


        mainPanel.add(leftPanel, new GridBagConstraints(0,0,1,1,0.2,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        mainPanel.add(tablePanel, new GridBagConstraints(1,0,1,1,0.8,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));


        mainFrame.add(mainPanel);
        mainFrame.pack();
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
