package JDBCUtil;

import java.sql.*;

public class JDBCUtil {
    Connection myConn = null;
    private static JDBCUtil jdbcUtil = null;

    public static JDBCUtil getInstance(){
        if(jdbcUtil==null){
            jdbcUtil = new JDBCUtil();
        }
        return jdbcUtil;
    }

    public JDBCUtil(){
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paper_reviews", "root", "marosum1396");
        } catch (SQLException e) {
            System.out.println("Error occurred while connecting to DB.");
        }
    }

    public ResultSet executeQueryAndGetResult(String query) throws SQLException {
        Statement myStmt = null;
        myStmt = myConn.createStatement();
        return myStmt.executeQuery(query);
    }

    public boolean executeUpdateQueryAndGetResult(String query) throws SQLException {
        Statement myStmt = null;
        myStmt = myConn.createStatement();
        return myStmt.execute(query);
    }

    public int executeQueryAndreturnID(String query) throws SQLException {
        Statement myStmt = null;
        int result = 0;
        myStmt = myConn.createStatement();
        myStmt.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
        ResultSet idSet = myStmt.getGeneratedKeys();
        idSet.next();
        result =  idSet.getInt(1);
        return result;
    }

    public void closeConnection() throws SQLException {
        myConn.close();
    }
}
