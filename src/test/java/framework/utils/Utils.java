package framework.utils;

import framework.config.Config;
import freemarker.template.SimpleDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    private static Logger logger = LogManager.getLogger(Utils.class);
    private static void createDirectories(Path path){
        try {
                Files.createDirectories(path);
            }
            catch (IOException e){
                logger.error("Something went wrong in creating directories at path - [ {} ] error-[{}]", path.toString(), e.getMessage());
            }
        }

    public  static  String captureScreenshot(WebDriver driver, String testName, boolean pass){
       String fileName="";
        if(driver==null){
            logger.error("Webdriver can't be null");
        }

        if(testName==null){
            logger.error("TestName can't be null");
        }

            String baseDir = Config.getProperty("screenshot.basedir");
            String passDir = Config.getProperty("screenshot.pass.dir");
            String failDir = Config.getProperty("screenshot.fail.dir");
            Path path = Paths.get(baseDir,pass ? passDir : failDir);

            createDirectories(path);
            fileName = generateFileName(testName);
            Path fileOnDisk = Paths.get(path.toString(), fileName);

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try{
            Files.copy(screenshot.toPath(), fileOnDisk);
            } catch(IOException e){
            throw new RuntimeException(e);
            }

            return fileName;
    }

    private static String generateFileName(String testName){
        String tsFormat = Config.getProperty("timestamp.pattern");
        SimpleDateFormat dateFormat = new SimpleDateFormat(tsFormat);
        String formattedDate = dateFormat.format(new Date());
        String filePattern = Config.getProperty("screenshot.file.pattern");
        filePattern = filePattern.replace("{TESTCASE_NAME}", testName);
        filePattern = filePattern.replace("{TIMESTAMP}", formattedDate);
        return filePattern;
    }
}
