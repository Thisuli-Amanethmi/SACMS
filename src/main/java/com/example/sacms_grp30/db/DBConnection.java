package com.example.sacms_grp30.db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DBConnection {

        private static DBConnection dbConnection;
        private Connection connection;

        private DBConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sacms?createDatabaseIfNotExist=true", "root", "root@123");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        public static DBConnection getInstance() {
            if(dbConnection==null){
                dbConnection=new DBConnection();
            }
            return dbConnection;
        }

        public Connection getConnection() {
            return connection;
        }
}
