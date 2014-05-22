package conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends AbstractDAO {

    public final static String SQL_TO_FIND_ALL = "SELECT * FROM USER";
    public final static String SQL_TO_FIND_ID = "SELECT * FROM USER WHERE USER_ID = ?";
    public final static String SQL_TO_FIND_ALL_EXPRESSAO = "SELECT * FROM USER WHERE USER_EMAIL LIKE '% ? %' OR USER_LOGIN LIKE '%?%' OR USER_SENHA LIKE '%?%'";
    public final static String SQL_TO_FIND = "SELECT * FROM USER WHERE USER_ID=?";
    public final static String SQL_TO_CREATE = "INSERT INTO USER (USER_LOGIN, USER_SENHA, USER_EMAIL, USER_IMAGE) VALUES (?,?,?,?)";
    public final static String SQL_TO_UPDATE = "UPDATE USER SET USER_LOGIN=?, USER_SENHA=?,USER_EMAIL=?, USER_IMAGE=? WHERE USER_ID=?";
    public final static String SQL_TO_COUNT_ALL = "SELECT COUNT(*) FROM USER";
    public final static String SQL_TO_REMOVE_ALL = "DELETE FROM USER";
    public final static String SQL_TO_REMOVE = "DELETE FROM USER WHERE USER_ID=?";
    public final static String SQL_TO_FIRST = "SELECT * FROM USER LIMIT 1,1";
    public final static String SQL_TO_FIND_ALL_TIPO = "SELECT * FROM TIPOPERMISSAO";
    public final static String SQL_ADD_NEW_TIPO = "INSERT INTO RELACIONAMENTO (REL_TIPO_USUARIO, REL_LOGIN_USUARIO) VALUES (?,?)";
    public final static String SQL_TO_FIND_TIPO_SELECIONADO = "SELECT * FROM RELACIONAMENTO WHERE REL_LOGIN_USUARIO = ?";
    public final static String SQL_TO_DEL_RELACIONAMENTO = "DELETE FROM relacionamento where REL_ID > 1 and REL_LOGIN_USUARIO = ?";

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

    public User findAllId(int id) throws Exception {
        User user = new User();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_FIND_ID);
            stmt.setInt(1, id);
            rs = super.__doDatabaseQuery(stmt);
            while (rs.next()) {
                user = this.fillObject(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }

        return user;
    }

    public ArrayList<User> findAllExpression(String Expressao) throws Exception {
        ArrayList<User> lst = new ArrayList<User>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement("SELECT * FROM USER WHERE USER_EMAIL LIKE '%" + Expressao + "%' OR USER_LOGIN LIKE '%" + Expressao + "%' OR USER_SENHA LIKE '%" + Expressao + "%'");
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

    public void addNew(User o) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, o.getNome());
            stmt.setString(2, o.getSenha());
            stmt.setString(3, o.getEmail());
            stmt.setString(4, "http://www.sosalergia.com.br/loja/images/sem_foto.gif");
            super.__doDatabaseUpdate(stmt);
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                o.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }
    }

    public void saveTipo(String User, int Tipo) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_ADD_NEW_TIPO, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Tipo);
            stmt.setString(2, User);
            super.__doDatabaseUpdate(stmt);
            rs = stmt.getGeneratedKeys();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }
    }

    public void delete(int o) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_REMOVE);
            stmt.setInt(1, o);
            super.__doDatabaseUpdate(stmt);
        } catch (SQLException e) {
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }
    }

    public void deleteRalacionamento(int Id) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_DEL_RELACIONAMENTO);
            stmt.setInt(1, Id);
            super.__doDatabaseUpdate(stmt);
        } catch (SQLException e) {
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }
    }

    public void update(User o) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = super.__getConnection();
            stmt = con.prepareStatement(SQL_TO_UPDATE);
            stmt.setString(1, o.getNome());
            stmt.setString(2, o.getSenha());
            stmt.setString(3, o.getEmail());
            stmt.setInt(5, o.getId());
            super.__doDatabaseUpdate(stmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            super.__closeConnection(con, stmt, rs);
        }
    }

    private User fillObject(ResultSet rs) throws SQLException {
        User u = new User();
        u.setId(rs.getInt("USER_ID"));
        u.setNome(rs.getString("USER_LOGIN"));
        u.setSenha(rs.getString("USER_SENHA"));
        u.setEmail(rs.getString("USER_EMAIL"));
        return u;
    }

    public static void main(String args[]) throws Exception {
        UserDAO oo = new UserDAO();
        User u = new User();
        u.setEmail("teste");
        u.setSenha("teste");
        u.setNome("teste");
        oo.addNew(u);
    }
}
