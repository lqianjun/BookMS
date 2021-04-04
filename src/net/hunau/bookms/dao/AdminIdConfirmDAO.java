package net.hunau.bookms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.hunau.bookms.bean.AdminIdConfirm;
import net.hunau.bookms.jdbc.JDBCUtils;

public class AdminIdConfirmDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public AdminIdConfirm confirmAdminId(String confirmId) {	//检查此验证码是否有效
		AdminIdConfirm idConfirm=null;
		String sql="SELECT * FROM admin_confirm WHERE available='1' AND adminId=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, confirmId);
			rs = pst.executeQuery();
			if(rs.next()){
				idConfirm = new AdminIdConfirm();
				idConfirm.setCno(rs.getString("cno"));
				idConfirm.setAdminId(rs.getString("adminId"));
				idConfirm.setAvailable(rs.getInt("available"));
				
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
		return idConfirm;
	}
	
	public void updateId(String confirm)
	{
		String sql="UPDATE admin_confirm SET available='0' WHERE adminId=?";
		try {
			conn = JDBCUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, confirm);
			pst.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			JDBCUtils.release(rs,pst,conn);//释放连接池资源
		}
	}

}
