package Helpers.TableUtils;

import DataProvider.DataProviderTableJobOffers;
import Views.MainFrame;

import java.util.Enumeration;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class TableUtility {

    public static void autoResizeColumn(JTable jTableToResize) {
        //Autoresizing method for tables
        JTableHeader header = jTableToResize.getTableHeader();//Gets table header
        int rowCount = jTableToResize.getRowCount();//Gets row count

        final Enumeration columns = jTableToResize.getColumnModel().getColumns();//Gets the model of the columns
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) jTableToResize.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(jTableToResize, column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();

            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) jTableToResize.getCellRenderer(row, col).getTableCellRendererComponent(jTableToResize,
                        jTableToResize.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + jTableToResize.getIntercellSpacing().width);
        }
        //return jTableToResize;
    }

    public static void initPagination(JTable jTable
            , MainFrame mainFrame
            , JButton last
            , JButton next
            , JButton previous
            , JButton first
            , JLabel status
            , JLabel totalData
            , JComboBox pageSelect
            , DataProviderTableJobOffers productTableModel) {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        mainFrame.totalData = productTableModel.count();
        //Testing coutn utput
        System.out.println(mainFrame.totalData);

        mainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        Double totalPageD = Math.ceil(mainFrame.totalData.doubleValue() / mainFrame.rowCountPerPage.doubleValue());
        mainFrame.totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (mainFrame.page.equals(1)) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (mainFrame.page.equals(mainFrame.totalPage)) {
            last.setEnabled(false);
            next.setEnabled(false);
        } else {
            last.setEnabled(true);
            next.setEnabled(true);
        }

        if (mainFrame.page > mainFrame.totalPage) {
            mainFrame.page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.findAll(mainFrame.page, mainFrame.rowCountPerPage));
        //Set model
        jTable.setModel(productTableModel);
        status.setText("Page " + mainFrame.page + " for " + mainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + mainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);
    }


    public static void initPaginationC(JTable jTable
            , MainFrame mainFrame
            , JButton last
            , JButton next
            , JButton previous
            , JButton first
            , JLabel status
            , JLabel totalData
            , JComboBox pageSelect
            , DataProviderTableJobOffers productTableModel,
            int currentIdComp) {
        //Initializes pagiantion of rows in jtable
        //Counts total rows in SQL DB
        mainFrame.totalData = productTableModel.count();
        //Testing coutn utput
        System.out.println(mainFrame.totalData);

        mainFrame.rowCountPerPage = Integer.valueOf(pageSelect.getSelectedItem().toString());
        Double totalPageD = Math.ceil(mainFrame.totalData.doubleValue() / mainFrame.rowCountPerPage.doubleValue());
        mainFrame.totalPage = totalPageD.intValue();
        //Bquttons for page navigation
        //Buttons for first page adn next page
        if (mainFrame.page.equals(1)) {
            first.setEnabled(false);
            previous.setEnabled(false);
        } else {
            first.setEnabled(true);
            previous.setEnabled(true);
        }
        //Buittons for last apge and next page
        if (mainFrame.page.equals(mainFrame.totalPage)) {
            last.setEnabled(false);
            next.setEnabled(false);
        } else {
            last.setEnabled(true);
            next.setEnabled(true);
        }

        if (mainFrame.page > mainFrame.totalPage) {
            mainFrame.page = 1;
        }
        //New instance of table for client table model
        productTableModel = new DataProviderTableJobOffers();
        //Popialte table
        productTableModel.setList(DataProviderTableJobOffers.findAllForCompany(mainFrame.page, mainFrame.rowCountPerPage,currentIdComp));
        //Set model
        jTable.setModel(productTableModel);
        status.setText("Page " + mainFrame.page + " for " + mainFrame.totalPage);//Page position count
        totalData.setText(("Row count " + mainFrame.totalData));//Row count
        //Resizes jtables columns
        TableUtility.autoResizeColumn(jTable);
    }
}
