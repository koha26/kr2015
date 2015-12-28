import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
    private AddEditWindow addWindow;
    private JScrollPane scrollPane;
    private JMenu ticketMenu;
    private JMenuItem buyTicketMenu;
    private TicketWindow ticketWindow;
    private JButton deleteButton;
    private JButton editTourButton;
    private JButton returnTicketButton;

    public GUI() {
        initialize();
        initializeTableData();
    }

    public void initializeTableData() {
        dataAccess = new DataAccess();
        String[] names = dataAccess.getAllNames();
        for (int i = 0; i < names.length; i++) {
            String id = names[i].substring(5);
            tableModel.add(dataAccess.getTourById(id));
        }
    }

    public void initialize() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeigth = screenSize.height;

        mainFrame.setSize(1000, 250);
        mainFrame.setResizable(false);
        //mainFrame.setPreferredSize(new Dimension(1000,200));
        //mainFrame.setBounds(screenWidth / 4, screenHeigth / 4, screenWidth / 2, screenHeigth / 2);
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
        resetButton = new JButton("Refresh");
        addButton = new JButton("Add tour");
        deleteButton = new JButton("Delete tour");
        editTourButton = new JButton("Edit...");
        returnTicketButton = new JButton("Return ticket");

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
        tableModel = new MyTableDataModel();
        tableView = new MyTableDataView(tableModel);

        scrollPane = new JScrollPane(tableView.getTable());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

        //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
        leftPanel.add(fromLable, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(fromTextField, new GridBagConstraints(1, 0, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(toLable, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(toTextField, new GridBagConstraints(1, 1, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(dateLable, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(dateTextField, new GridBagConstraints(1, 2, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(searchButton, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(resetButton, new GridBagConstraints(1, 3, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(addButton, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(deleteButton, new GridBagConstraints(1, 4, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(editTourButton, new GridBagConstraints(0, 5, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        leftPanel.add(returnTicketButton, new GridBagConstraints(1, 5, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

        mainPanel.add(leftPanel, new GridBagConstraints(0, 0, 1, 1, 0.2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
        mainPanel.add(tablePanel, new GridBagConstraints(1, 0, 1, 1, 0.8, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

        mainFrame.add(mainPanel);

        deleteButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = tableView.getSelectedRow();
                if (pos >= 0) {
                    dataAccess.deleteById(tableView.getModel().getIdAt(pos));
                    String id = tableView.getModel().getIdAt(pos);
                    tableView.getModel().clear();
                    initializeTableData();
                    JOptionPane.showMessageDialog(mainFrame, "Тур №" + id + " успешно удален!");
                } else {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(mainFrame, "Сначала выберите тур!");
                        }
                    });
                }
            }
        });

        searchButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String from = fromTextField.getText();
                String to = toTextField.getText();
                String date = dateTextField.getText();
                search(from, to, date);
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

        tableView.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int pos = tableView.getSelectedRow();
                    if (pos >= 0) {
                        ticketWindow = new TicketWindow(tableView.getModel().getAt(pos));
                    }
                }
            }
        });
        addButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWindow = new AddEditWindow();
            }
        });

        editTourButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pos = tableView.getSelectedRow();
                if (pos >= 0) {
                    addWindow = new AddEditWindow(tableView.getModel().getAt(pos));
                }
                else {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(mainFrame, "Нужно выделить тур в таблице!");
                        }
                    });
                }
                tableView.getModel().clear();
                initializeTableData();
            }
        });

        returnTicketButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnTicketWindow returnTicketWindow = new ReturnTicketWindow();
            }
        });
    }

    public void search(String from, String to, String date) {
        checkDate(date);
        List<Tour> tourNew = new ArrayList<Tour>();
        for (int i = 0; i < tableView.getModel().getSize(); i++) {
            if (from.equals(tableView.getModel().getFromAt(i)) && to.equals(tableView.getModel().getToAt(i))) {
                tourNew.add(tableView.getModel().getAt(i));
            } else {
                if (!from.equals(tableView.getModel().getFromAt(i)) && to.equals(tableView.getModel().getToAt(i))) {
                    for (int j = 0; j < tableView.getModel().getStationsAt(i).length; j++) {
                        if (from.equals(tableView.getModel().getStationsAt(i)[j].StationName)) {
                            tourNew.add(tableView.getModel().getAt(i));
                        }
                    }
                } else {
                    if (from.equals(tableView.getModel().getFromAt(i)) && !to.equals(tableView.getModel().getToAt(i))) {
                        for (int j = 0; j < tableView.getModel().getStationsAt(i).length; j++) {
                            if (to.equals(tableView.getModel().getStationsAt(i)[j].StationName)) {
                                tourNew.add(tableView.getModel().getAt(i));
                            }
                        }
                    }
                    if (!from.equals(tableView.getModel().getFromAt(i)) && !to.equals(tableView.getModel().getToAt(i))) {
                        boolean f = false, t = false;
                        int indF = 0, indT = 0;
                        for (int j = 0; j < tableView.getModel().getStationsAt(i).length; j++) {
                            if (from.equals(tableView.getModel().getStationsAt(i)[j].StationName)) {
                                f = true;
                                indF = j;
                            }
                            if (to.equals(tableView.getModel().getStationsAt(i)[j].StationName)) {
                                t = true;
                                indT = j;
                            }
                        }
                        if (f && t && (indF < indT)) {
                            tourNew.add(tableView.getModel().getAt(i));
                        }
                    }
                }
            }
        }
        tableView.getModel().clear();
        for (int i = 0; i < tourNew.size(); i++) {
            tableView.getModel().add(tourNew.get(i));
        }
    }

    public void checkDate(String date) {
        int ind = 0;
        while (ind < tableView.getModel().getSize()) {
            if (!tableView.getModel().getDateAt(ind).equals(date)) {
                tableView.getModel().delete(tableView.getModel().getTourNameAt(ind), tableView.getModel().getDateAt(ind));
                ind = 0;
            } else ind++;
        }
    }

    class ReturnTicketWindow{
        private JLabel fioLabel;
        private JTextField fioText;
        private JPanel returnPanel;
        private JLabel tourNameLabel;
        private JTextField tourNameText;
        private JButton returnButton;
        private JButton cancelButton;

        public ReturnTicketWindow(){
            initialize();
        }
        public void initialize(){
            final JFrame returnForm = new JFrame("Return Ticket");
            returnForm.setSize(500,200);
            returnForm.setVisible(true);

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeigth = screenSize.height;

            fioLabel = new JLabel("Enter your F.I.O.");
            fioText = new JTextField();

            tourNameLabel = new JLabel("Tour name: ");
            tourNameText = new JTextField();

            returnButton = new JButton("Return");
            cancelButton = new JButton("Calcel");

            returnPanel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            returnPanel.setLayout(layout);

            //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
            returnPanel.add(fioLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            returnPanel.add(fioText, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            returnPanel.add(tourNameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            returnPanel.add(tourNameText, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

            returnPanel.add(returnButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            returnPanel.add(cancelButton, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));

            returnForm.add(returnPanel);

            cancelButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    returnForm.dispose();
                }
            });

            returnButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   if (!fioText.getText().equals("") && !tourNameText.getText().equals("")){
                       for (int i=0;i<tableView.getModel().getAllTourName().length;i++){
                           if (tableView.getModel().getAllTourName()[i].equals(tourNameText.getText())){
                               for (int j=0;j<tableView.getModel().getAt(i).getSetOfTickets().size();j++){
                                   if (tableView.getModel().getAt(i).getSetOfTickets().get(j).getCustomer().equals(fioText.getText())){
                                       try {
                                           new File(dataAccess.getNameOfTicket(tableView.getModel().getAt(i),tableView.getModel().getAt(i).getSetOfTickets().get(j))).delete();
                                           tableView.getModel().getAt(i).getSetOfTickets().remove(j);
                                           dataAccess.rewriteTour(tableView.getModel().getAt(i));
                                           SwingUtilities.invokeLater(new Runnable() {
                                               @Override
                                               public void run() {
                                                   JOptionPane.showMessageDialog(returnForm, "Возврат произведен!");
                                               }
                                           });
                                           returnForm.dispose();
                                           return;
                                       } catch (FileNotFoundException e1) {
                                           e1.printStackTrace();
                                       }
                                       return;
                                   }
                               }
                               SwingUtilities.invokeLater(new Runnable() {
                                   @Override
                                   public void run() {
                                       JOptionPane.showMessageDialog(returnForm, "Билета на имя этого человека не куплено!");

                                   }
                               });
                               return;
                           }
                       }
                       SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               JOptionPane.showMessageDialog(returnForm, "Такого тура не было найдено!");
                           }
                       });
                       return;
                   }
                    else {
                       SwingUtilities.invokeLater(new Runnable() {
                           @Override
                           public void run() {
                               JOptionPane.showMessageDialog(returnForm, "Вы не заполнили все поля!");
                           }
                       });
                   }
                }
            });
        }

    }

    class AddEditWindow {
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
        private boolean editable=false;

        public AddEditWindow() {
            initialize();
        }

        public AddEditWindow(Tour tour) {
            initialize();
            editable=true;
            idText.setText(tour.getPropTourID());
            tourNameText.setText(tour.getPropTourName());
            fromText.setText(tour.getPropFrom());
            toText.setText(tour.getPropTo());
            detTText.setText(tour.getPropDepartureTime());
            arrTText.setText(tour.getPropArrivalTime());
            dateText.setText(tour.getPropDate());
            freePlacesText.setText(Integer.toString(tour.getPropFreePlaces()));
            String st = "";
            if (tour.getPropStations().length != 0) {
                for (int i = 0; i < tour.getPropStations().length; i++) {
                    st += (tour.getPropStations()[i].getPropStationName() + " ");
                    st += (Double.toString(tour.getPropStations()[i].getPropPrice()));
                    if (i != tour.getPropStations().length - 1) st += " ";
                }
            }
            stationText.setText(st);
            mainPriceText.setText(Double.toString(tour.getPropPrice()));
            String d = "";
            for (int i = 0; i < tour.getPropDays().length; i++) {
                d += (tour.getPropDays()[i] + " ");
                if (i != tour.getPropDays().length - 1) d += " ";
            }
            daysText.setText(d);
            allPlacesText.setText(String.valueOf(tour.getPropAllPlaces()));
            freePlacesText.setText(String.valueOf(tour.getPropFreePlaces()));
        }

        public void initialize() {
            final JFrame addForm = new JFrame("Add");
            addForm.setSize(600, 300);
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

            idText = new JTextField();
            idText.setEditable(false);
            idText.setText(dataAccess.getId());

            tourNameText = new JTextField();
            fromText = new JTextField();
            toText = new JTextField();
            detTText = new JTextField();
            arrTText = new JTextField();
            dateText = new JTextField();
            freePlacesText = new JTextField();
            priceText1 = new JTextField();
            allPlacesText = new JTextField();
            stationText = new JTextField();
            daysText = new JTextField();
            mainPriceText = new JTextField();

            stationPane = new JScrollPane(stationText);
            stationPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            addStationButton = new JButton("AddStation");
            addTourButton = new JButton("Add");
            clearButton = new JButton("Clear");

            addPanel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            addPanel.setLayout(layout);

            //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
            addPanel.add(idLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(idText, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(tourNameLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(tourNameText, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(fromLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(fromText, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(detTLabel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(detTText, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(toLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(toText, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(arrTLabel, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(arrTText, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(dateLabel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(dateText, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(freePlacesLabel, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(freePlacesText, new GridBagConstraints(2, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(stationLabel1, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(stationPane, new GridBagConstraints(1, 5, 2, 2, 3, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(daysLabel, new GridBagConstraints(0, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(daysText, new GridBagConstraints(1, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(allPlacesLabel, new GridBagConstraints(2, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(allPlacesText, new GridBagConstraints(3, 8, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(mainPrice, new GridBagConstraints(1, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(mainPriceText, new GridBagConstraints(2, 9, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


            addPanel.add(addTourButton, new GridBagConstraints(1, 10, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            addPanel.add(clearButton, new GridBagConstraints(2, 10, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

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
                            flag[0] = true;
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
                    if (!idText.getText().equals("") && !tourNameText.getText().equals("") &&
                            !fromText.getText().equals("") && !toText.getText().equals("") && !detTText.getText().equals("") &&
                            !arrTText.getText().equals("") && !dateText.getText().equals("") && !freePlacesText.getText().equals("") &&
                            !daysText.getText().equals("") && !allPlacesText.getText().equals("") && !mainPriceText.getText().equals("")) {
                        String[] parts = stationText.getText().split(" ");
                        Station[] stations = new Station[parts.length / 2];
                        int ind = 0;
                        for (int i = 0; i < parts.length; i += 2) {
                            stations[ind] = new Station(parts[i], Double.parseDouble(parts[i + 1]));
                            ind++;
                        }
                        String[] days = daysText.getText().split(" ");

                        tableView.getModel().add(new Tour(idText.getText(), tourNameText.getText(), fromText.getText(),
                                detTText.getText(), toText.getText(), arrTText.getText(), dateText.getText(),
                                Integer.parseInt(freePlacesText.getText()), stations, Double.parseDouble(mainPriceText.getText()),
                                days, Integer.parseInt(allPlacesText.getText()),null));
                        try {
                            dataAccess.addTour(tableView.getModel().getAt(tableView.getModel().getSize() - 1));
                        } catch (FileNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        //mainFrame.setEnabled(true);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                if (!editable)
                                    JOptionPane.showMessageDialog(addForm, "Тур добавлен!");
                                else {
                                    JOptionPane.showMessageDialog(addForm, "Тур отредактирован!");
                                    editable=false;
                                }
                            }
                        });
                        addForm.dispose();
                        mainFrame.setEnabled(true);

                    } else {
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

    class TicketWindow {
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
        private JTextArea stationText;
        private JTextField priceText1;
        private JTextField allPlacesText;
        private JTextField daysText;
        private JTextField mainPriceText;
        private JButton addStationButton;
        private JButton addTourButton;
        private JButton clearButton;
        private JTextField stationsPane;
        private JPanel ticketPanel;
        private JScrollPane stationPane;
        private JTextField placeText;
        private JLabel placeLabel;
        private JButton printTourButton;
        private Tour tTour;
        private JTextField customerText;
        private JLabel customerLabel;
        private JFrame ticketForm;
        private boolean flag;

        public TicketWindow() {
            initialize();
        }

        public TicketWindow(Tour tour) {
            initialize();
            tTour = tour;
            idText.setText(tour.getPropTourID());
            tourNameText.setText(tour.getPropTourName());
            fromText.setText(tour.getPropFrom());
            toText.setText(tour.getPropTo());
            detTText.setText(tour.getPropDepartureTime());
            arrTText.setText(tour.getPropArrivalTime());
            dateText.setText(tour.getPropDate());
            freePlacesText.setText(Integer.toString(tour.getPropFreePlaces()));
            /*String st="";
            for (int i=0;i<tour.getPropStations().length;i++){
                st+=(tour.getPropStations()[i].getPropStationName()+" ");
                st+=(Double.toString(tour.getPropStations()[i].getPropPrice()));
                if (i!=tour.getPropStations().length-1) st+=" ";
            }
            stationText.setText(st);*/
            mainPriceText.setText(Double.toString(tour.getPropPrice()));
        }

        public void initialize() {
            ticketForm = new JFrame("Buy ticket");
            ticketForm.setSize(600, 300);
            ticketForm.setVisible(true);

            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeigth = screenSize.height;

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
            placeLabel = new JLabel("Place: ");
            customerLabel = new JLabel("F.I.O.");
            customerText = new JTextField();

            idText = new JTextField();
            idText.setEditable(false);
            tourNameText = new JTextField();
            tourNameText.setEditable(false);
            fromText = new JTextField();
            toText = new JTextField();
            detTText = new JTextField();
            detTText.setEditable(false);
            arrTText = new JTextField();
            arrTText.setEditable(false);
            dateText = new JTextField();
            dateText.setEditable(false);
            freePlacesText = new JTextField();
            freePlacesText.setEditable(false);
            allPlacesText = new JTextField();
            allPlacesText.setEditable(false);
            mainPriceText = new JTextField();
            mainPriceText.setEditable(false);
            placeText = new JTextField();

            stationPane = new JScrollPane(stationText);
            stationPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            printTourButton = new JButton("Print");
            clearButton = new JButton("Clear");

            ticketPanel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            ticketPanel.setLayout(layout);

            //gridx,gridy,gridwidth,gridheight,weidthx,weidthy,anchor,fill,insets,ipadx,ipady
            ticketPanel.add(idLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(idText, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(tourNameLabel, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(tourNameText, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(fromLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(fromText, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(detTLabel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(detTText, new GridBagConstraints(3, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(toLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(toText, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(arrTLabel, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(arrTText, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(dateLabel, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(dateText, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(mainPrice, new GridBagConstraints(2, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.EAST, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(mainPriceText, new GridBagConstraints(3, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(placeLabel, new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(placeText, new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(customerText, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(customerLabel, new GridBagConstraints(2, 6, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


            ticketPanel.add(printTourButton, new GridBagConstraints(1, 10, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
            ticketPanel.add(clearButton, new GridBagConstraints(2, 10, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

            ticketForm.add(ticketPanel);

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
                    customerText.setText("");
                    allPlacesText.setText("");
                    mainPriceText.setText("");
                }
            });

            printTourButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tTour.getPropFreePlaces() >= 1) {
                        Ticket ticket = null;
                        if (checkFields()) {
                            return;
                        }
                        if (tTour.getPropTo().equals(toText.getText()) && tTour.getPropFrom().equals(fromText.getText())) {
                            ticket = new Ticket(String.valueOf(tTour.getPropAllPlaces()-tTour.getPropFreePlaces()),Integer.parseInt(placeText.getText()), customerText.getText(), fromText.getText(), toText.getText(), detTText.getText(),
                                    arrTText.getText(), dateText.getText(), Double.parseDouble(mainPriceText.getText()));
                            tTour.addTicketToSet(ticket);

                            try {
                                dataAccess.rewriteTour(tTour);
                                dataAccess.printTicket(tTour,ticket);
                                flag = true;
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            Tour newTour = dataAccess.getTourById(tTour.getPropTourID());
                            newTour.setFreePlaces(tTour.getPropFreePlaces() - 1);
                            try {
                                dataAccess.rewriteTour(newTour);
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            if (!flag) {
                                showError();
                                ticketForm.dispose();
                                return;
                            }
                        } else {
                            if (tTour.getPropTo().equals(toText.getText()) && !tTour.getPropFrom().equals(fromText.getText())) {
                                for (int j = 0; j < tTour.getPropStations().length; j++) {
                                    if (tTour.getPropStations()[j].getPropStationName().equals(fromText.getText())) {
                                        ticket = new Ticket(String.valueOf(tTour.getPropAllPlaces()-tTour.getPropFreePlaces()),Integer.parseInt(placeText.getText()), customerText.getText(), fromText.getText(), toText.getText(), detTText.getText(),
                                                arrTText.getText(), dateText.getText(),
                                                Double.parseDouble(mainPriceText.getText()) - tTour.getPropStations()[j].getPropPrice());
                                        tTour.addTicketToSet(ticket);
                                        try {
                                            dataAccess.rewriteTour(tTour);
                                            dataAccess.printTicket(tTour,ticket);
                                            flag = true;
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }
                                        Tour newTour = dataAccess.getTourById(tTour.getPropTourID());
                                        newTour.setFreePlaces(tTour.getPropFreePlaces() - 1);
                                        try {
                                            dataAccess.rewriteTour(newTour);
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        }
                                        //break;
                                    }
                                }
                                if (!flag) {
                                    showError();
                                    ticketForm.dispose();
                                    return;
                                }
                            } else {
                                if (!tTour.getPropTo().equals(toText.getText()) && tTour.getPropFrom().equals(fromText.getText())) {
                                    for (int j = 0; j < tTour.getPropStations().length; j++) {
                                        if (tTour.getPropStations()[j].getPropStationName().equals(toText.getText())) {
                                            ticket = new Ticket(String.valueOf(tTour.getPropAllPlaces()-tTour.getPropFreePlaces()),Integer.parseInt(placeText.getText()), customerText.getText(), fromText.getText(), toText.getText(), detTText.getText(),
                                                    arrTText.getText(), dateText.getText(), tTour.getPropStations()[j].getPropPrice());
                                            tTour.addTicketToSet(ticket);
                                            try {
                                                dataAccess.rewriteTour(tTour);
                                                dataAccess.printTicket(tTour,ticket);
                                                flag = true;
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            Tour newTour = dataAccess.getTourById(tTour.getPropTourID());
                                            newTour.setFreePlaces(tTour.getPropFreePlaces() - 1);
                                            try {
                                                dataAccess.rewriteTour(newTour);
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            //break;
                                        }
                                    }
                                    if (!flag) {
                                        showError();
                                        ticketForm.dispose();
                                        return;
                                    }
                                } else {
                                    if (!tTour.getPropTo().equals(toText.getText()) && !tTour.getPropFrom().equals(fromText.getText())) {
                                        boolean f = false, t = false;
                                        int indF = 0, indT = 0;
                                        for (int j = 0; j < tTour.getPropStations().length; j++) {
                                            if (tTour.getPropStations()[j].getPropStationName().equals(fromText.getText())) {
                                                f = true;
                                                indF = j;
                                            }
                                            if (tTour.getPropStations()[j].getPropStationName().equals(toText.getText())) {
                                                t = true;
                                                indT = j;
                                            }
                                        }
                                        if (f && t && (indF < indT)) {
                                            ticket = new Ticket(String.valueOf(tTour.getPropAllPlaces()-tTour.getPropFreePlaces()),Integer.parseInt(placeText.getText()), customerText.getText(), tTour.getPropStations()[indF].getPropStationName(),
                                                    tTour.getPropStations()[indT].getPropStationName(), detTText.getText(),
                                                    arrTText.getText(), dateText.getText(),
                                                    tTour.getPropStations()[indT].getPropPrice() - tTour.getPropStations()[indF].getPropPrice());
                                            tTour.addTicketToSet(ticket);
                                            try {
                                                dataAccess.rewriteTour(tTour);
                                                dataAccess.printTicket(tTour,ticket);
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                            Tour newTour = dataAccess.getTourById(tTour.getPropTourID());
                                            newTour.setFreePlaces(tTour.getPropFreePlaces() - 1);
                                            try {
                                                dataAccess.rewriteTour(newTour);
                                            } catch (FileNotFoundException e1) {
                                                e1.printStackTrace();
                                            }
                                        } else {
                                            showError();
                                            ticketForm.dispose();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        showDialog();
                        ticketForm.dispose();
                    } else {
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JOptionPane.showMessageDialog(ticketForm, "Извините, но билетов нет!");
                            }
                        });
                    }
                    tableView.getModel().clear();
                    initializeTableData();
                }
            });

        }

        public boolean checkFields() {
            if (!idText.getText().equals("") && !tourNameText.getText().equals("") &&
                    !fromText.getText().equals("") && !toText.getText().equals("") && !detTText.getText().equals("") &&
                    !arrTText.getText().equals("") && !dateText.getText().equals("") && !customerText.getText().equals("") &&
                    !allPlacesText.getText().equals("") && !mainPriceText.getText().equals("")) {
                return true;
            }
            return false;
        }

        private void showError() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(ticketForm, "Билет невозможно купить, т.к.маршрут не лежит через эти станции.");

                }
            });
        }

        private void showDialog() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {

                    JOptionPane.showMessageDialog(ticketForm, "Билет удачно куплен и сохранен в корневой папке!");
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
