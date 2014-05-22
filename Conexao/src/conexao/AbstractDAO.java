package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

    protected int countAll(String SqlCmd) throws Exception {
        Integer allRows = new Integer(0);
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = __getConnection();
            stmt = con.prepareStatement(SqlCmd);
            rs = __doDatabaseQuery(stmt);
            if (rs.next()) {
                allRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            __closeConnection(con, stmt, rs);
        }

        return allRows;
    }

    protected void removeAll(String SqlCmd) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = __getConnection();
            stmt = con.prepareStatement(SqlCmd);
            __doDatabaseUpdate(stmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            __closeConnection(con, stmt, null);
        }
    }

    protected Connection __getConnection() throws Exception {
        Connection con = null;
        try {
            con = MyConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw e;
        }

        return con;
    }

    protected void __closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
     * executeQuery()  --- This is used generally for reading the content of the database.
     *                     The output will be in the form of ResultSet. Generally SELECT statement is used.
     *
     * executeUpdate() --- This is generally used for altering the databases.
     *                     Generally DROP TABLE or DATABASE, INSERT into TABLE, UPDATE TABLE, DELETE from TABLE statements will be used in this.
     *                     The output will be in the form of int. This int value denotes the number of rows affected by the query.
     *
     * execute()       --- If you dont know which method to be used for executing SQL statements, this method can be used. This will return a boolean.
     *                     TRUE indicates the result is a ResultSet and FALSE indicates it has the int value which denotes number of rows affected by the query.
     */
    protected int __doDatabaseUpdate(PreparedStatement stmt) throws SQLException {
        return stmt.executeUpdate();
    }

    protected ResultSet __doDatabaseQuery(PreparedStatement stmt) throws SQLException {
        return stmt.executeQuery();
    }

}
