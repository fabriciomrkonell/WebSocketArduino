package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO {

    public final static String SQL_TO_FIND_ALL = "SELECT * FROM USER";

    public ArrayList<User> findAll() throws Exception {
        ArrayList<User> lst = new ArrayList<User>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_FIND_ALL);
            rs = super.__doDatabaseQuery(stmt);
            while (rs.next()) {
                lst.add(this.fillObject(rs));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }

        return lst;
    }

    public User getInstance(String nome, String senha, String email) {
        User u = new User();
        u.setNome(nome);
        u.setSenha(senha);
        u.setEmail(email);
        return u;
    }

    public Object getNewInstance() {
        return new User();
    }

    private User fillObject(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setNome(rs.getString("name"));
        u.setSenha(rs.getString("email"));
        u.setEmail(rs.getString("senha"));
        return u;
    }

}
