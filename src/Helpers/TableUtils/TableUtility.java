package Helpers.TableUtils;

import java.util.Enumeration;
import javax.swing.*;
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





}
