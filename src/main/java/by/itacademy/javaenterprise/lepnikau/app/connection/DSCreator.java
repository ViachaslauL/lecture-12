package by.itacademy.javaenterprise.lepnikau.app.connection;

import by.itacademy.javaenterprise.lepnikau.app.dao.ParentDAO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DSCreator {

    private static final Logger log = LoggerFactory.getLogger(DSCreator.class.getSimpleName());

    private static BasicDataSource basicDS;

    static {
        try {
            basicDS = new BasicDataSource();
            Properties properties = new Properties();

            InputStream inputStream = DSCreator.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (inputStream == null) {
                throw new FileNotFoundException("File not found");
            }

            properties.load(inputStream);
            basicDS.setDriverClassName(properties.getProperty("DB.DRIVER_CLASS"));
            basicDS.setUrl(properties.getProperty("DB.DB_URL"));
            basicDS.setUsername(properties.getProperty("DB.DB_USER"));
            basicDS.setPassword(properties.getProperty("DB.DB_PASSWORD"));
            basicDS.setInitialSize(Integer.parseInt(properties.getProperty("DB.INITIAL_POOL_SIZE")));
            basicDS.setMaxTotal(Integer.parseInt(properties.getProperty("DB.MAX_POOL_SIZE")));

        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    public static DataSource getDataSource() {
        return basicDS;
    }
}
