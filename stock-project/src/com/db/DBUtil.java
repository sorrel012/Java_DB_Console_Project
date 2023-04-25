package com.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection open() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "st";
        String pw = "1234";

        try {

            Connection conn = null;

            Class.forName("oracle.jdbc.driver.OracleDriver");

            conn = DriverManager.getConnection(url,id,pw);

            return conn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

}