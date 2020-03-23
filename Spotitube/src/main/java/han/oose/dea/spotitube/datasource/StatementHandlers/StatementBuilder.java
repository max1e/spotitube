package han.oose.dea.spotitube.datasource.StatementHandlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatementBuilder {
    private Connection connection;
    private String procedureName;
    private List<Object> parameters;

    public StatementBuilder() {
        parameters = new ArrayList<>();
    }

    public StatementBuilder setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    public StatementBuilder setProcedureName(String procedureName) {
        this.procedureName = procedureName;
        return this;
    }

    public StatementBuilder addParameter(Object object) {
        parameters.add(object);
        return this;
    }

    public PreparedStatement build() throws SQLException {
        var sqlQuery = appendSQLQuery();

        var sqlStatement = connection.prepareStatement(sqlQuery);

        setQueryParameters(sqlStatement);


        return sqlStatement;
    }

    private String appendSQLQuery() {
        var sqlQuery = new StringBuilder("CALL " + procedureName + "(");

        for (var ignored : parameters) {
            sqlQuery.append("?, ");
        }

        sqlQuery = new StringBuilder(sqlQuery.substring(0, sqlQuery.length() - 2));

        sqlQuery.append(");");

        return sqlQuery.toString();
    }

    private void setQueryParameters(PreparedStatement sqlStatement) throws SQLException {
        var i = 1;

        for (var parameter : parameters) {
            if (parameter instanceof String) {
                sqlStatement.setString(i , (String) parameter);
            }
            else if (parameter instanceof Integer){
                sqlStatement.setInt(i , (Integer) parameter);
            }
            else if (parameter instanceof Boolean){
                sqlStatement.setBoolean(i , (Boolean) parameter);
            }
            else {
                throw new RuntimeException();
            }

            i++;
        }
    }
}
