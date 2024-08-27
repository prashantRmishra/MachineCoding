package solidprinciple.SingleResponsibility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeeRepository {

    public void save(Employee employee) {
         String insert = MyUtil.serializeIntoString(employee);
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc://mysql://localhost:8080/MyDb", "root", "password");
            statement = connection.createStatement();
            statement.execute("insert into employee values (" + insert + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
}

}
