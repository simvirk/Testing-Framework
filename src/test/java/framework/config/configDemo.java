package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class configDemo {

    private static configDemo instance;
    private Properties properties;

    private configDemo(){
        try{
            InputStream inputStream = Files.newInputStream(Paths.get("src", "test","resources", "framework.properties"));
            properties = new Properties();
            properties.load(inputStream);
        }catch (IOException e){
            System.out.println("Something went wrong in reading file" + e.getMessage());
        }
    }

    public synchronized static String getProperty(String key){
        if (instance==null){
            instance=new configDemo();
        }
        return instance.properties.getProperty(key);
    }
}
