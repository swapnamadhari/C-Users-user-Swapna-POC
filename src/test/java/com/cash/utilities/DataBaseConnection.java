package com.cash.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cashe.base.BasePage;

public class DataBaseConnection extends BasePage {
	//Fetching data from DB
	public List<String> dataBaseSelect(String sql){
		List<String> DataResponse = new ArrayList<String>();
		Connection conn = null; 
		String q;
		initilize();
		//System.out.println("NAme is:"+ pro.getProperty("db_username"));
		/*Properties pro=new Properties();
		try {
			pro.load(new FileInputStream(System.getProperty("user.dir") +"/src/main/resources/properties/constant.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//conn = DriverManager.getConnection(pro.getProperty("db"), pro.getProperty("db_username"), pro.getProperty("db_password"));
			conn = DriverManager.getConnection("jdbc:mysql://replicadb.cashe.co.in:3306/ipaybridge2", "VijayKumar", "FE$Y%T%D&xZD");

			logger.debug("Database Connection Established Successfully...");
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result Set :::" + rs);
			rs.beforeFirst();
			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			logger.debug("SQl query ::: " + sql);
			logger.debug("Number of Columns ::: " + colCount);

			if (rs.next())
			{
				do{ 
					for (int j = 1; j<=colCount;j++)
					{
						q = rs.getString(j);
						DataResponse.add(q);
					}

				}while(rs.next());
			}
			//closing DB connection
			conn.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message : " + e.getMessage());
		}
		System.out.println("In function"+DataResponse);
		return DataResponse ;

	}

	//Upadating data into DB 
	public void dataBaseUpdate(String query){
		Connection conn = null; 

		initilize();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(pro.getProperty("db"), pro.getProperty("db_username"), pro.getProperty("db_password"));
			logger.debug("Database Connection Established Successfully...",true);
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			logger.debug("SQl query ::: " + query);
			logger.debug("Query executed");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message : " + e.getMessage());
		}
	}

}
