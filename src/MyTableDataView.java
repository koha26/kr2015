import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MyTableDataView extends JPanel{
    private MyTableDataModel model;
    private JTable table = new JTable();
    public MyTableDataView(){
        this(null);
    }
    public MyTableDataView(MyTableDataModel model){
        this.setModel(model);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(new Color(0x3D3E3A));
        this.setBorder(new LineBorder(Color.WHITE, 1));

        JScrollPane scroll = new JScrollPane(this.table);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //gridx,gridy,gridwidth,gridheight,weightx,weighty,anchor,fill,insets,ipadx,ipady
        this.add(scroll, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));

    }
    public void setModel(MyTableDataModel model) {
        if (model == null) {
            this.table.setModel(new DefaultTableModel());
            return;
        }
        this.model = model;
        this.table.setModel(new ModelForTable(model));
    }
    public MyTableDataModel getModel(){
        return model;
    }
    public void addSelectionListener(ListSelectionListener listener) {
        ListSelectionModel model = this.table.getSelectionModel();
        model.addListSelectionListener(listener);
    }

    public int getSelectedRow() {
        return this.table.getSelectedRow();
    }
    public JTable getTable(){
        return table;
    }
    private class ModelForTable extends AbstractTableModel implements Observer {
        private MyTableDataModel tab_model;

        @Override
        public int getColumnCount() {
            return 11;
        }

        public Class getColumnClass(int c) {
            return String.class;
        }

        @Override
        public String getColumnName(int col) {
            switch (col){
                case 0: return "TourID";
                case 1: return "TourName";
                case 2: return "From";
                case 3: return "DepartureTime";
                case 4: return "To";
                case 5: return "ArrivalTime";
                case 6: return "Date";
                case 7: return "FreePlaces";
                case 8: return "Price";
                case 9: return "Days";
            }
            return "AllPlaces";
            /*if (col == 0) {
                return "Nick";
            }
            return "IP";*/
        }

        public ModelForTable(MyTableDataModel m) {
            assert (m != null);
            this.tab_model = m;
            this.tab_model.addObserver(this);
        }

        @Override
        public int getRowCount() {
            return this.tab_model.getSize();
        }

        @Override
        public Object getValueAt(int row, int col) {
            //String[] nicks = this.tav.getNicks();
            String[] tourNames = this.tab_model.getAllTourName();
            switch (col){
                case 0: return tab_model.getIdAt(row);
                case 1: return tourNames[row];
                case 2: return tab_model.getFromAt(row);
                case 3: return tab_model.getDepTimeAt(row);
                case 4: return tab_model.getToAt(row);
                case 5: return tab_model.getArrTimeAt(row);
                case 6: return tab_model.getDateAt(row);
                case 7: return tab_model.getFreePlacesAt(row);
                case 8: return tab_model.getPriceAt(row);
                case 9: return tab_model.getDays(row);
            }
            assert (false);
            return null;
        }

        @Override
        public void update(Observable arg0, Object arg1) {
            this.fireTableDataChanged();
        }
    }
}
