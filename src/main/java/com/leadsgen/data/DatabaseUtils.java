package com.leadsgen.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtils {
  public static String getDBValue(String query) {

    String dbValue="";
    try {
      Connection connection = DriverManager.getConnection("jdbc:oracle://hostname:port/30usd", "psp", "HXQPQSbwZKuz9YVz");
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      if(resultSet.next()) {
        dbValue = resultSet.getString("column_name");
      }
      resultSet.close();
      statement.close();
      connection.close();
    }catch (Exception e) {
      e.printStackTrace();
    }
    return dbValue;
  }
}
