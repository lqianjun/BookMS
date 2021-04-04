package net.hunau.bookms.dao;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.hunau.bookms.bean.Book;
import net.hunau.bookms.bean.Feedback;
public class FeedbackDAO {
	//查找全部用户反馈
	public List<Feedback> getAllFeedback() throws IOException
	{
		//读取mybatis-congfig文件
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Feedback> feedbacks=new ArrayList<Feedback>();
		  feedbacks = session.selectList("net.hunau.bookms.mapper.FeedbackMapper.getAllFeedback");
		  session.commit();
		  session.close();
		  return feedbacks;
	}
	
	//通过feedid查询反馈
	public Feedback getFeedbackById(int i) throws IOException
	{
			//读取mybatis-congfig文件
			  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
			  //初始化mybatis，创建SqlSessionFactory实例
			  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			  //创建session实例
			  SqlSession session=sqlSessionFactory.openSession();
			  Feedback fd=new Feedback();
			  fd = session.selectOne("net.hunau.bookms.mapper.FeedbackMapper.getFeedbackById",i);
			  session.close();
			  return fd;
	}
	
	//查找未阅读的反馈，handle=0;    SELECT userNo,DATE,content,handle FROM feedback WHERE handle='0';
	public List<Feedback> getUnReadedFeedback() throws IOException
	{
		//读取mybatis-congfig文件
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Feedback> feedbacks=new ArrayList<Feedback>();
		  feedbacks = session.selectList("net.hunau.bookms.mapper.FeedbackMapper.getUnReadedFeedback()");
		  session.commit();
		  session.close();
		  return feedbacks;
	}
	
	//更新Feedback
	public void updateFeedback(int feedback) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  session.update("net.hunau.bookms.mapper.FeedbackMapper.updateFeedback",feedback);
		  session.commit();
		  session.close();
	}
	//添加用户反馈  INSERT INTO Feedback(userNo,DATE,content,handle) VALUES('201641635124',"2019-01-01 10:01:01","hhh",0);
	public void addFeedback(Feedback feedback) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //初始化mybatis，创建SqlSessionFactory实例
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //创建session实例
		  SqlSession session=sqlSessionFactory.openSession();
		  session.insert("net.hunau.bookms.mapper.FeedbackMapper.addFeedback",feedback);
		  session.commit();
		  session.close();
	}
}
