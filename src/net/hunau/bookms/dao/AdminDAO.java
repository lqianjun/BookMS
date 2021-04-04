package net.hunau.bookms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.jdbc.JDBCUtils;

public class AdminDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;

	public Admin getAdmin(String username,String password)//通过用户名和密码访问数据库管理员信息
	{
		Admin admin=null;
		String sql="select * from bookms.admin where username=? and password=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()){
				admin =new Admin();
				admin.setAdminId(rs.getString("adminId"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setLevel(rs.getInt("level"));
				admin.setTrueName(rs.getString("truename"));
				
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
		return admin;
	}
	
	public Admin getAdmin(String adminid)//通过管理员编号查找管理员
	{
		Admin admin=null;
		String sql="select * from bookms.admin where adminid=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, adminid);
			rs = pst.executeQuery();
			if(rs.next()){
				admin =new Admin();
				admin.setAdminId(rs.getString("adminId"));
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setLevel(rs.getInt("level"));
				admin.setTrueName(rs.getString("truename"));
				
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
		return admin;
	}
	public void addAdmin(Admin admin)	//添加管理员
	{
		String sql ="INSERT INTO admin(adminId,username,password,level,truename) VALUE(?,?,?,?,?)";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, admin.getAdminId());
			pst.setString(2, admin.getUsername());
			pst.setString(3, new String(admin.getPassword()));
			pst.setInt(4, admin.getLevel());
			pst.setString(5, admin.getTruename());
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
	//修改管理员信息
	public void updateAdmin(Admin admin)
	{
		String sql = "UPDATE admin SET username=?,PASSWORD=? WHERE adminId=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, admin.getUsername());
			pst.setString(2, admin.getPassword());
			pst.setString(3, admin.getAdminId());
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
	
	public void addUser(User user)	//添加用户
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
	//编辑用户信息
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
	//管理员删除用户
	public void delUser(String userNo){
		String sql = "delete from reader where userNo="+userNo;
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
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
	
	//超级管理员删除管理员
		public void delAdmin(String adminid){
			String sql = "delete from admin where adminid="+adminid;
			try {
				conn = JDBCUtils.getConnection();
				pst = conn.prepareStatement(sql);
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
	
	//冻结用户
	public void freezUser(String userNo )
	{
		String sql = "UPDATE reader SET validity=0 where userNo=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, userNo);
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
	//解冻用户
	public void thawUser(String userNo )
	{
		String sql = "UPDATE reader SET validity=1 where userNo=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, userNo);
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
	//查看所有用户
	public List<User> getUsers()
	{ 
		List<User> users=new ArrayList<User>();
		String sql="SELECT * FROM reader";
		User user=null;
		try
		{
			conn=JDBCUtils.getConnection();
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next())
			{
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
				user.setBalance(rs.getInt("balance"));
				user.setValidity(rs.getInt("validity"));
				users.add(user);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs,pst,conn);
		}
		
		return users;
	}
	//按学号查找用户
	public User getUserByNo(String userNo)
	{ 
		String sql="SELECT * FROM reader where userNo=?";
		User user=null;
		try
		{
			conn=JDBCUtils.getConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, userNo);
			rs=pst.executeQuery();
			while(rs.next())
			{
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
				user.setBalance(rs.getInt("balance"));
				user.setValidity(rs.getInt("validity"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}finally{
			JDBCUtils.release(rs,pst,conn);
		}
		
		return user;
	}
	
}
