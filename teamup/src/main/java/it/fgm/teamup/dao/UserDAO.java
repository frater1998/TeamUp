package it.fgm.teamup.dao;

import it.fgm.teamup.model.Utente;

import java.sql.*;

public class UserDAO {

    public Utente checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/teamup_new?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
        String dbUser = "root1234*";
        String dbPassword = "root1234*";
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM utente WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        Utente user = null;

        if (result.next()) {
            user = new Utente();
            user.setNome(result.getString("nome"));
            user.setEmail(email);
        }

        connection.close();

        return user;
    }
}
