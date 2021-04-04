package net.hunau.bookms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.hunau.bookms.bean.User;
import net.hunau.bookms.jdbc.JDBCUtils;

public class UserDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public User getUser(String username,String password)//通过用户名和密码访问数据库用户信息
	{
		User user=null;
		String sql="select * from reader where username=? and password=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				user = new User();
				user.setUserNo(rs.getString("userNo"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setReaderType(rs.getString("readerType"));
				user.setSex(rs.getString("sex"));
				user.setTelePhone(rs.getString("telephone"));
				user.setEmail(rs.getString("email"));
				user.setQQ(rs.getString("QQ"));
				user.setAddress("address");
				user.setBirthday(rs.getString("birthday"));
				user.setBalance(rs.getFloat("balance"));
				user.setValidity(rs.getInt("validity"));
				user.setTruename(rs.getString("truename"));
				
			}	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			JDBCUtils.release(rs,pst,conn);//释放连接池资源
		}
		return user;
	}
	
	public void addUser(User user)	//用户注册
	{	
		String sql ="insert into reader (userNo,username,password,readerType,sex,telephone,email,qq,address,birthday,balance,validity,truename)"
				+ " values(?,?,?,?,?,?,?,?,?,?,0,1,?)";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUserNo());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getReaderType());
			pst.setString(5, user.getSex());
			pst.setString(6, user.getTelePhone());
			pst.setString(7, user.getEmail());
			pst.setString(8, user.getQQ());
			pst.setString(9, user.getAddress());
			pst.setString(10, user.getBirthday());
			pst.setString(11, user.getTruename());
			pst.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(pst, conn);
		}	
	}
	
	//用户编辑自己信息(userNo,username,password,readerType,sex,telephone,email,qq,address,birthday,balance,validity)
		public void updateUser(User user){
			String sql = "UPDATE reader SET userNo=?,username=?,password=?,readerType=?,sex=?,telephone=?,email=?,qq=?,address=?,birthday=?,balance=?,validity=? where userNo=?";
			try {
				conn = JDBCUtils.getConnection();
				pst = conn.prepareStatement(sql);
				pst.setString(1, user.getUserNo());
				pst.setString(2, user.getUsername());
				pst.setString(3, user.getPassword());
				pst.setString(4, user.getReaderType());
				pst.setString(5, user.getSex());
				pst.setString(6, user.getTelePhone());
				pst.setString(7, user.getEmail());
				pst.setString(8, user.getQQ());
				pst.setString(9, user.getAddress());
				pst.setString(10, user.getBirthday());
				pst.setFloat(11, user.getBalance());
				pst.setInt(12, user.getValidity());
				pst.setString(13, user.getUserNo());
				pst.executeUpdate();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(pst, conn);
			}	
		}
	//更新用户余额
		public void updateUserBalance(User user) throws IOException {
			//读取mybatis-congfig文件
			  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
			  //初始化mybatis，创建SqlSessionFactory实例
			  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			  //创建session实例
			  SqlSession session=sqlSessionFactory.openSession();
			  session.update("net.hunau.bookms.mapper.UserMapper.updateUserBalance",user);
			  session.commit();
			  session.close();
		}
	public static void main(String[] args) throws IOException {
		User user = new User();
		user.setUserNo("201644454896");
		user.setBalance(300f);
		UserDAO ud = new UserDAO(); 
		ud.updateUserBalance(user);
	}
}
