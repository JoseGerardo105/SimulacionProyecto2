package application.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.config.ConfigManager;
import application.exception.RepositoryException;

/**
 * @authors Jose Gerardo Gomez - Neyder Fabian Rodriguez - Andres Felipe Amezquita - David Orlando Rodriguez
 */
class DBManager {

    private Connection connection;

    private DBManager() {
    }

    Connection getConnection() throws RepositoryException {
        String jdbcUrl = "jdbc:sqlite:" + ConfigManager.getInstance().getProperties("db.url");

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException ex) {
                throw new RepositoryException("Connexion impossible: " + ex.getMessage());
            }
        }
        return connection;
    }

    void startTransaction() throws RepositoryException {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {
            throw new RepositoryException("No se puede iniciar una transacción: " + ex.getMessage());
        }
    }

    static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {

        private static final DBManager INSTANCE = new DBManager();
    }
}
