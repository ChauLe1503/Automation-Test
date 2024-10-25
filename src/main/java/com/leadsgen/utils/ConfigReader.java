package com.leadsgen.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties getProperties() throws IOException {
        Properties props = new Properties();
        FileInputStream inputStream = new FileInputStream("./src/main/resources/config.properties");
        props.load(inputStream);
        inputStream.close();
        return props;
    }
}
