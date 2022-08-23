package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private static ConfigReader configReaderInstance;

    public ConfigReader() {

        BufferedReader reader;
        String propertyFilePath = "src/test/resources/config.properties";

        try{

            reader = new BufferedReader( new FileReader(propertyFilePath));
            properties = new Properties();
            try {

                properties.load(reader);
                reader.close();

            }catch (IOException e){
                e.printStackTrace();
            }

        }catch (FileNotFoundException e){

            throw new RuntimeException("Config file not found" + propertyFilePath);

        }

    }

    public static ConfigReader getConfigReaderInstanceInstance() {

        if (configReaderInstance == null)
            configReaderInstance = new ConfigReader();

        return configReaderInstance;
    }

    public String getApiURL() {

        return properties.getProperty("ApiURL") ;

    }

    public String getApiKeyString() {

        return properties.getProperty("ApiKey");

    }

    public String getApiTokenString() {

        return properties.getProperty("ApiToken");

    }

    public String getReportConfigPath(){
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

}
