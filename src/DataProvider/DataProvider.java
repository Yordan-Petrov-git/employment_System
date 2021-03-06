package DataProvider;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataProvider {


    //Database location and login info
    private static final String serverName = "localhost";//SQL DB name of the server
    private static final String username = "root";//SQL DB  username
    private static final String dataBaseName = "employment_system";//SQL Table name
    private static final Integer portNumber = 3306;//SQL DB server port number //Default port number XAMPP APACHE SERVER
    //private static final String password = "";//SQL DB server password


    public static Connection getConnection() {

        //SQL DB connection method

        Connection connection = null;

        MysqlDataSource datasource = new MysqlDataSource();

        datasource.setServerName(serverName);
        datasource.setUser(username);
        datasource.setDatabaseName(dataBaseName);
        datasource.setPortNumber(portNumber);
        //datasource.setPassword(password);

        try {
            connection = datasource.getConnection();

            System.out.println(connection);//for testing

        } catch (SQLException ex) {//if connection fails
            Logger.getLogger(" Get Connection -> " + LoginDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
        return connection;

    }

}
