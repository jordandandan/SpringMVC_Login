package cn.edu.tju.dao;

import java.io.Serializable;
import java.sql.*;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
@Component
public class JdbcDataSource implements Serializable {
	private String driver;
	@Value("#{jdbcProps.url}")
	private String url;
	@Value("#{jdbcProps.user}")
	private String user;
	@Value("#{jdbcProps.pwd}")
	private String pwd;
	
	public JdbcDataSource() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("JDBCDataSource实例化");
	}
	public String getDriver() {
		return driver;
	}
	/** 必须使用Bean属性输入, 否则不能进行JDBC Driver注册 */
    @Value("#{jdbcProps.driver}")
	public void setDriver(String driver) {
		try {
			Class.forName(driver);
			this.driver = driver;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Connection getConnection() throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
	public void close(Connection conn){
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
	}
}
