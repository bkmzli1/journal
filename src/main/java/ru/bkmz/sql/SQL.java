package ru.bkmz.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class SQL {
    //sql
    private Connection conn;
    private Statement statmt;
    private ResultSet resSet;

    protected static final Logger logger = LogManager.getLogger();

    public SQL(String fileConn) throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + fileConn);
        logger.info("Connection: " + fileConn);


    }

    public void initStart() throws SQLException {
        statmt = conn.createStatement();

        statmt.execute("create table ListName" +
                " ( id INTEGER not null constraint ListName_pk primary key autoincrement, name TEXT );" +
                "  create unique index ListName_id_uindex on ListName (id);");

        statmt.execute("create table Fart" +
                " ( id INTEGER not null constraint ListName_pk primary key autoincrement, name TEXT );" +
                "  create unique index ListName_id_uindex on ListName (id);");


        statmt.execute("INSERT INTO 'ListName'('name') VALUES ('A')");
        statmt.execute("INSERT INTO 'ListName'('name') VALUES ('B')");
        statmt.execute("INSERT INTO 'ListName'('name') VALUES ('C')");
// таблица учене(предмет ,дата, оценка) сравнение по предмету
        statmt.execute("INSERT INTO 'Fart'('name') VALUES ('мат')");
        statmt.execute("INSERT INTO 'Fart'('name') VALUES ('ист')");
        statmt.execute("INSERT INTO 'Fart'('name') VALUES ('обж')");

        statmt.close();


        logger.info("create table ListName");
    }


    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStatmt() {
        return statmt;
    }

    public void setStatmt(Statement statmt) {
        this.statmt = statmt;
    }

    public ResultSet getResSet() {
        return resSet;
    }

    public void setResSet(ResultSet resSet) {
        this.resSet = resSet;
    }

    public void close() throws SQLException {
        conn.close();
        statmt.close();
        resSet.close();
    }
}
