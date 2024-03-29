package su.serviceit.ea.repository;

import su.serviceit.ea.exception.NotFoundException;
import su.serviceit.ea.model.IdAliasDto;
import su.serviceit.ea.setting.AppSettingState;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnterpriseArchitectRepository {

    private final String ip;
    private final String port;
    private final String databaseName;
    private final String login;
    private final String password;

    public EnterpriseArchitectRepository() {
        AppSettingState setting = AppSettingState.getInstance();

        ip = setting.ip;
        port = setting.port;
        databaseName = setting.databaseName;
        login = setting.login;
        password = setting.password;
    }

    private Connection dbConn = null;

    private Connection getDbConnection() throws SQLException {
        String connStr = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;encrypt=false;user=%s;password=%s",
                ip, port, databaseName, login, password);

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        dbConn = DriverManager.getConnection(connStr);

        return dbConn;
    }

    public IdAliasDto getAliasByGuid(String guid) {

        IdAliasDto dto;
        try {
            PreparedStatement statement =
                    getDbConnection().prepareStatement("select * from dbo.t_object o where o.ea_guid = ?");

            statement.setString(1, guid);
            ResultSet result = statement.executeQuery();

            List<IdAliasDto> aliasList = new ArrayList<>();

            while (result.next()) {
                Long id = result.getLong("Object_ID");
                String alias = result.getString("Alias");

                dto = new IdAliasDto(id, alias);
                aliasList.add(dto);
            }

            result.close();

            dto = aliasList.isEmpty() ? null : aliasList.get(0);
        } catch (SQLException e) {
            throw new NotFoundException("Incorrect GUID");
        }

        if (Objects.nonNull(dto)) {
            return dto;
        }
        throw new NotFoundException("Incorrect GUID");
    }

    public void createAlias(Long id, String alias) {
        int result;
        try {
            result = updateDataByIdAction(id, alias);
        } catch (SQLException e) {
            throw new NotFoundException("Error:\n " + e.getMessage());
        }

        if (result == 0) {
            throw new NotFoundException("Error update alias");
        }
    }

    public boolean deleteAlias(Long id) {
        int result;
        try {
            result = updateDataByIdAction(id, null);
        } catch (SQLException e) {
            throw new NotFoundException("Error:\n " + e.getMessage());
        }

        if (result == 0) {
            throw new NotFoundException("Error delete alias");
        }

        return result > 0;
    }

    private int updateDataByIdAction(Long id, String alias) throws SQLException {
        int result;
        PreparedStatement statement = getDbConnection()
                .prepareStatement("update dbo.t_object set Alias = ? where Object_ID = ?");

        statement.setString(1, alias);
        statement.setLong(2, id);
        result = statement.executeUpdate();

        statement.close();
        return result;
    }
}
