package JDBC.src;

import org.postgresql.ds.PGSimpleDataSource;

public class DataSourceImp {
    public javax.sql.DataSource dataSource(){
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setDatabaseName("postgres");
        pgSimpleDataSource.setUser("postgres");
        pgSimpleDataSource.setPassword("Leaph@11042003");
        return dataSource();
    }
}
