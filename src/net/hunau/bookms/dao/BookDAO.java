package net.hunau.bookms.dao;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.hunau.bookms.bean.Book;
import net.hunau.bookms.bean.BookType;
import net.hunau.bookms.bean.SearchBook;
import net.hunau.bookms.bean.SecondFindBook;
import net.hunau.bookms.jdbc.JDBCUtils;


public class BookDAO {
	private Connection conn;
	private PreparedStatement pst;
	
	 
	//按书籍类型名查询图书；
	public List<Book> getBookByBookTypeName(String bktName) throws IOException{
		  //读取mybatis-congfig文件
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		List<Book> books=new ArrayList<Book>();
		books=session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByBookTypeName",bktName);
		
		session.commit();
		session.close();
		return books;
	}
	
	//按书籍名称查询书籍信息；
	public List<Book> getBookInfoByBookName(String bookName) throws IOException{
		 //读取mybatis-congfig文件
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Book> books=new ArrayList<Book>();
		  books = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookInfoByBookName",bookName);
		  session.commit();
		  session.close();
		  return books;
		
	}

	//按书籍条码查询书籍信息；
		public Book getBookInfoByBarcode(String barcode) throws IOException{
			 //读取mybatis-congfig文件
			  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
			  //初始化mybatis，创建SqlSessionFactory实例
			  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			  //创建session实例
			  SqlSession session=sqlSessionFactory.openSession();
			  Book book = new Book();
			  book = session.selectOne("net.hunau.bookms.mapper.BookMapper.getBookInfoByBarcode",barcode);
			  session.commit();
			  session.close();
			  return book;
			
		}
		
	//查询所有图书
	public List<Book> getBookInfoAll() throws IOException{
		//读取mybatis-congfig文件
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Book> books=new ArrayList<Book>();
		  books = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookInfoAll");
		  session.commit();
		  session.close();
		  return books;
	}
	
/*************************************************************************
 * 代码在这里
 * @throws IOException 
 * **********************************************************************/	
	//查找书籍类型id是否存在  SELECT * FROM booktype WHERE booktypeid='10203';
	public boolean isRightBookTypeId(String bookTypeId) throws IOException
	{
		
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  BookType bty=session.selectOne("net.hunau.bookms.mapper.BookMapper.isRightBookTypeId",bookTypeId);
		  session.commit();
		  session.close();
		  if(bty ==null)
		  {
			  return false;
		  }
		  return true;
	}
	//添加图书，给的有以前项目添加图书的模板，自己参考着写,写好后删掉注释
	public void addBook(Book book) throws IOException
	{
		 InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  session.insert("net.hunau.bookms.mapper.BookMapper.addBook",book);
		  session.commit();
		  session.close();
	}
	
	 //根据barcode编辑图书
	public void updateBook(Book book) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  session.update("net.hunau.bookms.mapper.BookMapper.updateBook",book);
		  session.commit();
		  session.close();
	}
	
	
	//根据书条码删除图书
	public void deleBook(String barcode)
	{
		String sql = "delete from book where barcode="+barcode;
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
	/*
	 * @param string
	 * @return SearchBook
	 * @exception 根据书籍名称模糊查询书籍
	 */
	public List<SearchBook> getBookByName(String bookName) throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByName",bookName);
		  session.commit();
		  session.close();
		  return booklist;
	}
	/*
	 * @param string
	 * @return SearchBook
	 * @exception 根据书籍名称模糊查询书籍
	 */
	public List<SearchBook> getBookByTypeName(String bookByTypeName) throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByTypeName",bookByTypeName);
		  session.commit();
		  session.close();
		  return booklist;
	}
	/*
	 * @param 
	 * @return SearchBook
	 * @exception 查全部书籍
	 */
	public List<SearchBook> getAllBook() throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getAllBook");
		  session.commit();
		  session.close();
		  return booklist;
	}
	public List<SecondFindBook> getSecondBook(String tbarcode) throws IOException{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<SecondFindBook> booklist = session.selectList("net.hunau.bookms.mapper.BookMapper.getSecondBook",tbarcode);
		  session.commit();
		  session.close();
		  return booklist;
	}
	public static void main(String[] agrs) throws IOException {
		BookDAO bd = new BookDAO();
		List<SecondFindBook> booklist = bd.getSecondBook("10000100");
		for(SecondFindBook b:booklist) {
			System.out.println(b);
		}
		
	}

}
