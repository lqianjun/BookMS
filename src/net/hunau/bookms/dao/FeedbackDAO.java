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
	//����ȫ���û�����
	public List<Feedback> getAllFeedback() throws IOException
	{
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Feedback> feedbacks=new ArrayList<Feedback>();
		  feedbacks = session.selectList("net.hunau.bookms.mapper.FeedbackMapper.getAllFeedback");
		  session.commit();
		  session.close();
		  return feedbacks;
	}
	
	//ͨ��feedid��ѯ����
	public Feedback getFeedbackById(int i) throws IOException
	{
			//��ȡmybatis-congfig�ļ�
			  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
			  //��ʼ��mybatis������SqlSessionFactoryʵ��
			  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
			  //����sessionʵ��
			  SqlSession session=sqlSessionFactory.openSession();
			  Feedback fd=new Feedback();
			  fd = session.selectOne("net.hunau.bookms.mapper.FeedbackMapper.getFeedbackById",i);
			  session.close();
			  return fd;
	}
	
	//����δ�Ķ��ķ�����handle=0;    SELECT userNo,DATE,content,handle FROM feedback WHERE handle='0';
	public List<Feedback> getUnReadedFeedback() throws IOException
	{
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<Feedback> feedbacks=new ArrayList<Feedback>();
		  feedbacks = session.selectList("net.hunau.bookms.mapper.FeedbackMapper.getUnReadedFeedback()");
		  session.commit();
		  session.close();
		  return feedbacks;
	}
	
	//����Feedback
	public void updateFeedback(int feedback) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  session.update("net.hunau.bookms.mapper.FeedbackMapper.updateFeedback",feedback);
		  session.commit();
		  session.close();
	}
	//����û�����  INSERT INTO Feedback(userNo,DATE,content,handle) VALUES('201641635124',"2019-01-01 10:01:01","hhh",0);
	public void addFeedback(Feedback feedback) throws IOException
	{
		InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  session.insert("net.hunau.bookms.mapper.FeedbackMapper.addFeedback",feedback);
		  session.commit();
		  session.close();
	}
}
