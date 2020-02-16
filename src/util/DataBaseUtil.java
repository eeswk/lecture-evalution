package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseUtil {

    public static Connection getConnection() {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/LectureEvaluation";
            String dbUrlOption = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String dbId = "root";
            String dbPassword = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUrl+dbUrlOption, dbId, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
