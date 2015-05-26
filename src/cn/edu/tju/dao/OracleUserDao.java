package cn.edu.tju.dao;


import cn.edu.tju.entity.User;
import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
/** �־ò� ע��  */
@Repository("userDao") //ָ���ض���Bean ID ����setUserDAOע��
public class OracleUserDao implements UserDao {
	private JdbcDataSource dataSource;
	//����Լ�ʵ���˹�������ҲҪд��Ĭ�Ϲ�����
	 public OracleUserDao() {
	    }

	public OracleUserDao(JdbcDataSource datasource) {
		super();
		this.dataSource = datasource;
		System.out.println("OracleUserDao ʵ����"+dataSource.getDriver()+dataSource.getUser());
	}
	@Autowired
	public void setDataSource(@Qualifier("jdbcDataSource")JdbcDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public User findByName(String name) {
		// TODO Auto-generated method stub
		
        String sql = "select id, name, pwd, phone from USERS where name=?";
        Connection conn = null;
 
        User user = null;
        try {
            conn = dataSource.getConnection();
           
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            
            
           while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setPhone(rs.getString("phone"));
               
            }
          
            rs.close();
            ps.close();
            
            return	user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            dataSource.close(conn);
        }
    }
		
	

}
