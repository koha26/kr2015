import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
    private DataAccess dataAccess;
    private MyTableDataModel tableModel;
    private MyTableDataView tableView;
    private Tour[] tours;
    private String[] IDs;
    private AddWindow addWindow;
    private JScrollPane scrollPane;

    public GUI(){
        initialize();
        initializeTableData();
    }
    public void initializeTableData(){
        dataAccess = new DataAccess();
        String []names = dataAccess.getAllNames();
        for (int i=0;i<names.length;i++){
            String id = names[i].substring(5);
//            IDs[i]=id;
            tableModel.add(dataAccess.getTourById(id));
        }
    }
    public void initialize(){
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

        saveMenu.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    try  {
                        FileWriter fw = new FileWriter(fc.getSelectedFile());
                        fw.write("Blah blah blah...");
                    }
                    catch ( IOException e2 ) {
                        System.out.println("Всё погибло!");
                    }
                }
            }
        });
        tablePanel.setLayout(layout1);
        tableModel=new MyTableDataModel();
        tableView=new MyTableDataView(tableModel);

        scrollPane = new JScrollPane(tableView.getTable());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //JTextArea textArea = new JTextArea("Здесь вместо текст ареа - таблица");
        tablePanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

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


        mainPanel.add(leftPanel, new GridBagConstraints(0,0,1,1,0.2,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
        mainPanel.add(tablePanel, new GridBagConstraints(1,0,1,1,0.8,1,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));

        mainFrame.add(mainPanel);
        mainFrame.pack();


        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromTextField.getText();
                String to = toTextField.getText();
                String date = dateTextField.getText();
                search(from,to,date);
            }
        });
        resetButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableView.getModel().clear();
                initializeTableData();
                dateTextField.setText("");
                fromTextField.setText("");
                toTextField.setText("");
            }
        });
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWindow = new AddWindow();
            }
        });
    }

    public void search(String from,String to,String date){
        checkDate(date);
        List<Tour> tourNew = new ArrayList<Tour>();
        for (int i=0;i<tableView.getModel().getSize();i++){
           if (from.equals(tableView.getModel().getFromAt(i)) && to.equals(tableView.getModel().getToAt(i))){
               tourNew.add(tableView.getModel().getAt(i));
           }
           else {
               if (!from.equals(tableView.getModel().getFromAt(i)) && to.equals(tableView.getModel().getToAt(i))){
                   for (int j=0;j<tableView.getModel().getStationsAt(i).length;j++){
                       if (from.equals(tableView.getModel().getStationsAt(i)[j].StationName)){
                           tourNew.add(tableView.getModel().getAt(i));
                       }
                   }
               }
               else {
                   if (from.equals(tableView.getModel().getFromAt(i)) && !to.equals(tableView.getModel().getToAt(i))){
                       for (int j=0;j<tableView.getModel().getStationsAt(i).length;j++){
                           if (to.equals(tableView.getModel().getStationsAt(i)[j].StationName)){
                               tourNew.add(tableView.getModel().getAt(i));
                           }
                       }
                   }
                   if (!from.equals(tableView.getModel().getFromAt(i)) && !to.equals(tableView.getModel().getToAt(i))){
                       boolean f=false,t=false;
                       int indF=0,indT=0;
                       for (int j=0;j<tableView.getModel().getStationsAt(i).length;j++){
                           if (from.equals(tableView.getModel().getStationsAt(i)[j].StationName)){
                               f=true;
                               indF=j;
                           }
                           if (to.equals(tableView.getModel().getStationsAt(i)[j].StationName)){
                               t=true;
                               indT=j;
                           }
                       }
                       if (f && t &&(indF<indT)){
                           tourNew.add(tableView.getModel().getAt(i));
                       }
                   }
               }
           }
        }
        tableView.getModel().clear();
        for (int i=0;i<tourNew.size();i++){
            tableView.getModel().add(tourNew.get(i));
        }
    }
    public void checkDate(String date){
        int ind=0;
        while (ind<tableView.getModel().getSize()){
            if (!tableView.getModel().getDateAt(ind).equals(date)){
                tableView.getModel().delete(tableView.getModel().getTourNameAt(ind),tableView.getModel().getDateAt(ind));
                ind=0;
            }
            else ind++;
        }
    }
    class AddWindow{
        private JLabel idLabel;
        private JLabel tourNameLabel;
        private JLabel fromLabel;
        private JLabel toLabel;
        private JLabel detTLabel;
        private JLabel arrTLabel;
        private JLabel dateLabel;
        private JLabel freePlacesLabel;
        private JLabel stationLabel1;
        private JLabel priceLabel1;
        private JLabel daysLabel;
        private JLabel allPlacesLabel;
        private JLabel mainPrice;

        private JTextField idText;
        private JTextField tourNameText;
        private JTextField fromText;
        private JTextField toText;
        private JTextField detTText;
        private JTextField arrTText;
        private JTextField dateText;
        private JTextField freePlacesText;
        private JTextField stationText;
        private JTextField priceText1;
        private JTextField allPlacesText;
        private JTextField daysText;
        private JTextField mainPriceText;
        private JButton addStationButton;
        private JButton addTourButton;
        private JButton clearButton;
        private JTextField stationsPane;
        private JPanel addPanel;
        private JScrollPane stationPane;

        public AddWindow(){
            initialize();
        }
        public void initialize(){
            final JFrame addForm=new JFrame("Add");
            addForm.setSize(600,300);
            addForm.setVisible(true);

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeigth = screenSize.height;

            //addForm.setBounds(screenWidth / 4, screenHeigth / 4, screenWidth / 2, screenHeigth / 2);

            idLabel = new JLabel("TourID: ");
            tourNameLabel = new JLabel("TourName: ");
            fromLabel = new JLabel("From: ");
            toLabel = new JLabel("To: ");
            detTLabel = new JLabel("DepartureTime: ");
            arrTLabel = new JLabel("ArrivalTime: ");
            dateLabel = new JLabel("Date: ");
            freePlacesLabel = new JLabel("FreePlaces: ");
            stationLabel1 = new JLabel("Station: ");
            priceLabel1 = new JLabel("Price: ");
            daysLabel = new JLabel("Days: ");
            allPlacesLabel = new JLabel("AllPlaces: ");
            mainPrice = new JLabel("MainPrice: ");

            idText= new JTextField();
            tourNameText= new JTextField();
            fromText= new JTextField();
            toText= new JTextField();
            detTText= new JTextField();
            arrTText= new JTextField();
            dateText= new JTextField();
            freePlacesText= new JTextField();
            priceText1= new JTextField();
            allPlacesText= new JTextField();
            stationText = new JTextField();
            daysText= new JTextField();
            mainPriceText= new JTextField();

            stationPane = new JScrollPane(stationText);
            stationPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            addStationButton = new JButton("AddStation");
            addTourButton = new JButton("Add");
            clearButton = new JButton("Clear");

            addPanel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            addPanel.setLayout(layout);

            //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
            addPanel.add(idLabel, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(idText, new GridBagConstraints(1,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(tourNameLabel, new GridBagConstraints(2,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(tourNameText, new GridBagConstraints(3,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(fromLabel, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(fromText, new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(detTLabel, new GridBagConstraints(2,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(detTText, new GridBagConstraints(3,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(toLabel, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(toText, new GridBagConstraints(1,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(arrTLabel, new GridBagConstraints(2,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(arrTText, new GridBagConstraints(3,2,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(dateLabel, new GridBagConstraints(1,3,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(dateText, new GridBagConstraints(2,3,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(freePlacesLabel, new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(freePlacesText, new GridBagConstraints(2,4,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(stationLabel1, new GridBagConstraints(0,5,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(stationPane, new GridBagConstraints(1,5,2,2,3,3,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(2,2,2,2),0,0));
            addPanel.add(daysLabel, new GridBagConstraints(0,8,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(daysText, new GridBagConstraints(1,8,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(allPlacesLabel, new GridBagConstraints(2,8,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(allPlacesText, new GridBagConstraints(3,8,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(mainPrice, new GridBagConstraints(1,9,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.EAST,new Insets(2,2,2,2),0,0));
            addPanel.add(mainPriceText, new GridBagConstraints(2,9,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));


            addPanel.add(addTourButton, new GridBagConstraints(1,10,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));
            addPanel.add(clearButton, new GridBagConstraints(2,10,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.HORIZONTAL,new Insets(2,2,2,2),0,0));

            addForm.add(addPanel);
            final boolean[] flag = {false};
            stationText.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (!flag[0])
                                JOptionPane.showMessageDialog(addForm, "Введите пожалуйста, станицию и через пробел цену билета." +
                                        "Каждую станцию вводить через пробел. Пример: Харьков 23.6 Купянск 12.6");
                            flag[0] =true;
                        }
                    });
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            clearButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    idText.setText("");
                    tourNameText.setText("");
                    fromText.setText("");
                    toText.setText("");
                    detTText.setText("");
                    arrTText.setText("");
                    dateText.setText("");
                    freePlacesText.setText("");
                    daysText.setText("");
                    allPlacesText.setText("");
                    mainPriceText.setText("");
                    stationText.setText("");
                }
            });

            addTourButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!idText.getText().equals("")&&!tourNameText.getText().equals("")&&
                            !fromText.getText().equals("")&&!toText.getText().equals("")&&!detTText.getText().equals("")&&
                            !arrTText.getText().equals("")&&!dateText.getText().equals("")&&!freePlacesText.getText().equals("")&&
                            !daysText.getText().equals("")&&!allPlacesText.getText().equals("")&&!mainPriceText.getText().equals("")){
                        String []parts =stationText.getText().split(" ");
                        Station [] stations = new Station[parts.length / 2];
                        int ind=0;
                        for (int i=0; i<parts.length;i+=2){
                            stations[ind]=new Station(parts[i],Double.parseDouble(parts[i+1]));
                            ind++;
                        }
                        String [] days = daysText.getText().split(" ");
                        if (dataAccess.isAvaivable(idText.getText())){
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                        JOptionPane.showMessageDialog(addForm, "Введите, пожалуйста, другой ID, т.к. такой уже существует.");
                                }
                            });
                        }
                        else {
                        tableView.getModel().add(new Tour(idText.getText(),tourNameText.getText(),fromText.getText(),
                                detTText.getText(),toText.getText(),arrTText.getText(),dateText.getText(),
                                Integer.parseInt(freePlacesText.getText()),stations,Double.parseDouble(mainPriceText.getText()),
                                days,Integer.parseInt(allPlacesText.getText())));
                            try {
                                dataAccess.addTour(tableView.getModel().getAt(tableView.getModel().getSize()-1));
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            //mainFrame.setEnabled(true);
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                        JOptionPane.showMessageDialog(addForm, "Тур добавлен!");
                                }
                                });
                            addForm.dispose();
                            mainFrame.setEnabled(true);
                        }
                    }
                    else {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(addForm, "Вы ввели не все данные!");
                            }
                        });
                    }
                }
            });
        }
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
