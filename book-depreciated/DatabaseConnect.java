package pl.bastus.booksapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

interface DatabaseConnect {
    //private static final String DB_DRIVER_CLASS="com.mysql.jdbc.Driver";
    String DB_URL="jdbc:mysql://localhost:3306/javatutorials?useUnicode=true&" +
            "useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String DB_USERNAME="root";
    String DB_PASSWORD="";

    static Connection connectDatabase() {
        //Properties props = new Properties();
        Connection conn = null;
        try {
            //Class.forName(props.getProperty("DB_DRIVER_CLASS"));
            //FileInputStream fis = new FileInputStream("db.properties");
            //props.load(fis);
            /*conn = DriverManager.getConnection(props.getProperty("DB_URL"),
                    props.getProperty("DB_USERNAME"),
                    props.getProperty("DB_PASSWORD"));*/
            conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch ( SQLException e) {
            e.printStackTrace();
        }
        System.out.println("DB Connection created successfully");
        return conn;
    }
}