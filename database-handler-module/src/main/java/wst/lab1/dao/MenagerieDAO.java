package wst.lab1.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wst.lab1.db.Menagerie;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class MenagerieDAO {

    private final String TABLE_NAME = "menagerie";
    private final String ID_COLUMN = "id";
    private final String ANIMAL_COLUMN = "animal";
    private final String NAME_COLUMN = "name";
    private final String BREED_COLUMN = "breed";
    private final String HEALTH_COLUMN = "health";
    private final String ARRIVAL_COLUMN = "arrival";

    private final DataSource dataSource;
    private final List<String> columnNames = Arrays.asList(ID_COLUMN, ANIMAL_COLUMN, NAME_COLUMN, BREED_COLUMN, HEALTH_COLUMN, ARRIVAL_COLUMN);

    @Data
    @AllArgsConstructor
    private static class Statement {
        private int number;
        private Object value;
        private int sqlType;
    }

    private int detectSqlType(Class<?> claz) {
        if (claz == Long.class) {
            return Types.BIGINT;
        } else if (claz == String.class) {
            return Types.VARCHAR;
        } else if (claz == Date.class) {
            return Types.TIMESTAMP;
        }

        throw new IllegalArgumentException(claz.getName());
    }

    private void fillStatementPattern(PreparedStatement ps, List<Statement> statements) throws SQLException {
        for (Statement statement : statements) {
            if (statement.getValue() == null) {
                ps.setNull(statement.number, statement.sqlType);
            } else {
                switch (statement.getSqlType()) {
                    case Types.BIGINT:
                        ps.setLong(statement.number, (Long) statement.getValue());
                        break;
                    case Types.VARCHAR:
                        ps.setString(statement.number, (String) statement.getValue());
                        break;
                    case Types.TIMESTAMP:
                        ps.setDate(statement.number, (java.sql.Date) statement.getValue());
                        break;
                    default:
                        throw new RuntimeException(statement.toString());
                }
            }
        }
    }

    private List<Menagerie> addResToList(ResultSet rs) throws SQLException {
        List<Menagerie> result = new ArrayList<>();
        while (rs.next()) {
            result.add(addResToMenagerie(rs));
        }
        return result;
    }

    public List<Menagerie> filterValues(Long id, String animal, String name, String breed, String health, Date arrival) throws SQLException {
        log.debug("Filter with args: {} {} {} {} {} {}", id, animal, name, breed, health, arrival);
        if (Stream.of(id, animal, name, breed, health, arrival).allMatch(Objects::isNull)) {
            log.debug("Args are empty");
            return findAllValues();
        }
        StringBuilder query = new StringBuilder();
        query.append("SELECT ").append(String.join(",", columnNames)).append(" FROM ").append(TABLE_NAME).append(" WHERE ");
        int i = 1;
        List<Statement> statements = new ArrayList<>();
        if (id != null) {
            query.append(ID_COLUMN).append("= ?");
            statements.add(new Statement(i, id, detectSqlType(Long.class)));
            i++;
        }
        if (animal != null) {
            if (!statements.isEmpty()) {
                query.append(" AND ");
            }
            query.append(ANIMAL_COLUMN).append("= ?");
            statements.add(new Statement(i, animal, detectSqlType(String.class)));
            i++;
        }
        if (name != null) {
            if (!statements.isEmpty()) {
                query.append(" AND ");
            }
            query.append(NAME_COLUMN).append("= ?");
            statements.add(new Statement(i, name, detectSqlType(String.class)));
            i++;
        }
        if (breed != null) {
            if (!statements.isEmpty()) {
                query.append(" AND ");
            }
            query.append(BREED_COLUMN).append("= ?");
            statements.add(new Statement(i, breed, detectSqlType(String.class)));
            i++;
        }
        if (health != null) {
            if (!statements.isEmpty()) {
                query.append(" AND ");
            }
            query.append(HEALTH_COLUMN).append("= ?");
            statements.add(new Statement(i, health, detectSqlType(String.class)));
            i++;
        }
        if (arrival != null) {
            if (!statements.isEmpty()) {
                query.append(" AND ");
            }
            query.append(ARRIVAL_COLUMN).append("= ?");
            statements.add(new Statement(i, arrival, detectSqlType(Date.class)));
        }

        log.debug("Built query {}", query.toString());
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query.toString());
            fillStatementPattern(ps, statements);
            ResultSet rs = ps.executeQuery();
            return addResToList(rs);
        }

    }

    private Menagerie addResToMenagerie(ResultSet rs) throws SQLException {
        long id = rs.getLong(ID_COLUMN);
        String animal = rs.getString(ANIMAL_COLUMN);
        String name = rs.getString(NAME_COLUMN);
        String breed = rs.getString(BREED_COLUMN);
        String health = rs.getString(HEALTH_COLUMN);
        Date arrival = rs.getDate(ARRIVAL_COLUMN);
        return new Menagerie(id, animal, name, breed, health, arrival);
    }

    private List<Menagerie> addResToMenageries(ResultSet rs) throws SQLException {
        List<Menagerie> result = new ArrayList<>();
        while (rs.next()) {
            result.add(addResToMenagerie(rs));
        }
        log.debug("Result set was added to entities {}", result);
        return result;
    }

    public List<Menagerie> findAllValues() throws SQLException {
        log.debug("Find all values");
        try (Connection connection = dataSource.getConnection()) {
            java.sql.Statement statement = connection.createStatement();
            statement.execute("SELECT id, animal, name, breed, health, arrival FROM menagerie");
            List<Menagerie> result = addResToMenageries(statement.getResultSet());
            return result;
        }

    }

    public long create(String animal, String name, String breed, String health, Date arrival) throws SQLException {
        log.debug("Create with args {} {} {} {} {}", animal, name, breed, health, arrival);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            long newId;
            try (java.sql.Statement idStatement = connection.createStatement()) {
                idStatement.execute("SELECT nextval('menagerie_sequence') nextval");
                try (ResultSet rs = idStatement.getResultSet()) {
                    rs.next();
                    newId = rs.getLong("nextval");
                }

            }
            try (PreparedStatement stmnt = connection.prepareStatement(
                    "INSERT INTO menagerie(id, animal, name, breed, health, arrival) " +
                            "VALUES (?, ?, ?, ?, ?, ?)")) {
                stmnt.setLong(1, newId);
                stmnt.setString(2, animal);
                stmnt.setString(3, name);
                stmnt.setString(4, breed);
                stmnt.setString(5, health);
                stmnt.setDate(6, new java.sql.Date(arrival.getTime()));
                int count = stmnt.executeUpdate();
                if (count == 0) {
                    throw new RuntimeException("Could not execute query");
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return newId;
        }
    }

    public int delete(long id) throws SQLException {
        log.debug("Delete entity with id {}", id);
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM menagerie WHERE id = ?")) {
                ps.setLong(1, id);
                return ps.executeUpdate();
            }
        }
    }

    public int update(long id, String animal, String name, String breed, String health, Date arrival) throws SQLException {
        log.debug("Update entity with id {} and new values {} {} {} {} {}", id, animal, name, breed, health, arrival);
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(true);
            String updateStr = "UPDATE menagerie SET animal = ?, name = ?, breed = ?, health = ?, arrival = ? WHERE id = ?";
            try (PreparedStatement stmnt = conn.prepareStatement(updateStr)) {
                stmnt.setString(1, animal);
                stmnt.setString(2, name);
                stmnt.setString(3, breed);
                stmnt.setString(4, health);
                stmnt.setDate(5, new java.sql.Date(arrival.getTime()));
                stmnt.setLong(6, id);
                int updated = stmnt.executeUpdate();
                log.debug("{} rows updated", updated);
                return updated;
            }
        }
    }
}
