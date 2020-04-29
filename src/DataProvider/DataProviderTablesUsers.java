package DataProvider;

import Models.User;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTablesUsers extends AbstractTableModel {

    List<User> userList = new ArrayList<User>();

    private final String HEADER[] = {"№->", "Name", "Family", "Email", "Phone Number", "id"};

    public void setList(List<User> listProduct) {
        this.userList = listProduct;
    }

    public void save(User product) {
        userList.add(product);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    public void edit(int index, User product) {
        userList.set(index, product);
        fireTableRowsUpdated(index, index);
    }

    public void delete(int index) {
        userList.remove(index);
        fireTableRowsDeleted(index, index);
    }

    public User findOne(int index) {
        return userList.get(index);
    }

    public int getRowCount() {
        return userList.size();
    }

    public int getColumnCount() {
        return HEADER.length;
    }

    public String getColumnName(int column) {
        return HEADER[column];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;//Row number counter for the table

            case 1:
                return user.getFirstName();

            case 2:
                return user.getFamilyName();

            case 3:
                return user.getEmailAddress();

            case 4:
                return user.getPhoneNumber();

            case 5:
                return user.getId();


            default:
                return null;//Defaut state null
        }
    }

    public void addRow(Object[] row) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
