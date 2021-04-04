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
	
	 
	//���鼮��������ѯͼ�飻
	public List<Book> getBookByBookTypeName(String bktName) throws IOException{
		  //��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		List<Book> books=new ArrayList<Book>();
		books=session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByBookTypeName",bktName);
		
		session.commit();
		session.close();
		return books;
	}
	
	//���鼮���Ʋ�ѯ�鼮��Ϣ��
	public List<Book> getBookInfoByBookName(String bookName) throws IOException{
		 //��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Book> books=new ArrayList<Book>();
		  books = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookInfoByBookName",bookName);
		  session.commit();
		  session.close();
		  return books;
		
	}

	//���鼮�����ѯ�鼮��Ϣ��
		public Book getBookInfoByBarcode(String barcode) throws IOException{
			 //��ȡmybatis-congfig�ļ�
			  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
			  //��ʼ��mybatis������SqlSessionFactoryʵ��
			  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			  //����sessionʵ��
			  SqlSession session=sqlSessionFactory.openSession();
			  Book book = new Book();
			  book = session.selectOne("net.hunau.bookms.mapper.BookMapper.getBookInfoByBarcode",barcode);
			  session.commit();
			  session.close();
			  return book;
			
		}
		
	//��ѯ����ͼ��
	public List<Book> getBookInfoAll() throws IOException{
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Book> books=new ArrayList<Book>();
		  books = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookInfoAll");
		  session.commit();
		  session.close();
		  return books;
	}
	
/*************************************************************************
 * ����������
 * @throws IOException 
 * **********************************************************************/	
	//�����鼮����id�Ƿ����  SELECT * FROM booktype WHERE booktypeid='10203';
	public boolean isRightBookTypeId(String bookTypeId) throws IOException
	{
		
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
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
	//���ͼ�飬��������ǰ��Ŀ���ͼ���ģ�壬�Լ��ο���д,д�ú�ɾ��ע��
	public void addBook(Book book) throws IOException
	{
		 InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  session.insert("net.hunau.bookms.mapper.BookMapper.addBook",book);
		  session.commit();
		  session.close();
	}
	
	 //����barcode�༭ͼ��
	public void updateBook(Book book) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  session.update("net.hunau.bookms.mapper.BookMapper.updateBook",book);
		  session.commit();
		  session.close();
	}
	
	
	//����������ɾ��ͼ��
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
	 * @exception �����鼮����ģ����ѯ�鼮
	 */
	public List<SearchBook> getBookByName(String bookName) throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByName",bookName);
		  session.commit();
		  session.close();
		  return booklist;
	}
	/*
	 * @param string
	 * @return SearchBook
	 * @exception �����鼮����ģ����ѯ�鼮
	 */
	public List<SearchBook> getBookByTypeName(String bookByTypeName) throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getBookByTypeName",bookByTypeName);
		  session.commit();
		  session.close();
		  return booklist;
	}
	/*
	 * @param 
	 * @return SearchBook
	 * @exception ��ȫ���鼮
	 */
	public List<SearchBook> getAllBook() throws IOException {
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  
		  List<SearchBook> booklist  = session.selectList("net.hunau.bookms.mapper.BookMapper.getAllBook");
		  session.commit();
		  session.close();
		  return booklist;
	}
	public List<SecondFindBook> getSecondBook(String tbarcode) throws IOException{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
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
