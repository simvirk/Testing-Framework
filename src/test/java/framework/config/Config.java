package framework.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class Config {
    private static Config instance;
    private static final Logger logger = LogManager.getLogger(Config.class);
    private Properties properties;

    private Config() {
        properties = new Properties();
        try {
            Path path = Paths.get("src/test/resources","framework.properties");
            logger.info("Reading {} as configuration file", path.toAbsolutePath().toString());
            InputStream stream = new FileInputStream(path.toFile());
            properties.load(stream);

        }catch (IOException e){
            logger.error("Error while reading file {} ",  e.getMessage());

        }
    }

    public static String getProperty(String key){
        if(key==null || key.isEmpty()){                    //Key is used to read the properties in framework file
            logger.error("You cant pass the empty key");
            throw new RuntimeException("Empty or null key passes");
        }
        logger.debug("Retrieving value for key {}", key);
        synchronized (Configuration.class){
            if(instance == null){
                instance = new Config();
            }
            String property = instance.properties.getProperty(key);

            if (property==null) {
                logger.warn("No value present for key [{}] please use correct key", key);
            }
            logger.debug("Value : {} found for Key: {}", property, key);
            return property;
        }
    }
}
