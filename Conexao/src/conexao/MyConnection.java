package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/websocket";
    private static final String USER = "root";
    private static final String PASSWD = "system";
    private static Connection con = null;
    private static MyConnection conn;

    static {
        conn = new MyConnection();
    }

    public static MyConnection getInstance() {
        return conn;
    }

    private MyConnection() {
    }

    public Connection getConnection() throws Exception {
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection(URL, USER, PASSWD);
                con.setAutoCommit(true);
            } catch (ClassNotFoundException e) {
                System.err.println("Não consegui conectar em: " + URL);
                throw e;
            }
        }

        return con;
    }

    public static void closeConnection() throws Exception {
        if (con != null || !con.isClosed()) {
            try {
                con.close();
                con = null;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String showState() {
        StringBuilder state = new StringBuilder("MyConnection state is... \n");
        try {
            Connection connection = getInstance().getConnection();
            state.append("+ Connection instance Ok!\n");
            if (connection.isClosed()) {
                state.append("+ Connection is closed!\n");
            } else {
                state.append("+ Connection is open!\n");
            }
        } catch (Exception ex) {
            state.append("MyConnction:showState() throws Exception ...\n");
            state.append(ex.getMessage());
        }

        return state.toString();
    }
}
