package net.hunau.bookms.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import net.hunau.bookms.bean.LoanInfos;

public class LoanInfosDAO {

	//�������н�����Ϣ
	public List<LoanInfos> getAllBorrow(String userNo) throws IOException
	{
		//System.out.println(LoanInfosDAO.class);
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<LoanInfos> loanInfos=new ArrayList<LoanInfos>();
		  
		  loanInfos = session.selectList("net.hunau.bookms.mapper.LoanInfosMapper.getAllBorrow",userNo);
		  session.commit();
		  session.close();
		  return loanInfos;
	}
	
	public List<LoanInfos> getNOReturn(String userNo) throws IOException
	{
		//System.out.println(LoanInfosDAO.class);
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  List<LoanInfos> loanInfos=new ArrayList<LoanInfos>();
		  
		  loanInfos = session.selectList("net.hunau.bookms.mapper.LoanInfosMapper.getNOReturn",userNo);
		  session.commit();
		  session.close();
		  return loanInfos;
	}
	//���ݻ���ı�ź�useNo��ѯҪ���������¼ 
	public LoanInfos getBorrow(LoanInfos loanInfos) throws IOException {
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  
		  loanInfos = session.selectOne("net.hunau.bookms.mapper.LoanInfosMapper.getBorrow",loanInfos);
		  session.commit();
		  session.close();
		  return loanInfos;
	}
	//�黹�鼮���½��ļ�¼
	public void updateReturnBook(LoanInfos loanInfos) throws IOException {
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  
		  session.update("net.hunau.bookms.mapper.LoanInfosMapper.updateReturnBook",loanInfos);
		  session.commit();
		  session.close();
	}
	//�鼮�������һ����¼
	public void borrowOneBookSuccess(LoanInfos loanInfos) throws IOException {
		//��ȡmybatis-congfig�ļ�
		  InputStream inputStream = (InputStream) Resources.getResourceAsStream("mybatis-config.xml");
		  //��ʼ��mybatis������SqlSessionFactoryʵ��
		  SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
		  //����sessionʵ��
		  SqlSession session=sqlSessionFactory.openSession();
		  
		  session.insert("net.hunau.bookms.mapper.LoanInfosMapper.borrowOneBookSuccess",loanInfos);
		  session.commit();
		  session.close();
	}
	public static void main(String[] args) throws IOException {
		 LoanInfosDAO lo = new  LoanInfosDAO();
		 LoanInfos ls = new LoanInfos();
		 ls.setBarcode("1000010001");
		 ls.setUserNo("201644454896");
		 lo.updateReturnBook(ls);
		// LoanInfos l = lo.getBorrow(ls);
		 //System.out.println(l.getTruename());
		/* List<LoanInfos> loanInfos = lo.getNOReturn("201644454896");
		 for(LoanInfos l:loanInfos) {
			 System.out.println(l);
		 }*/
	}
	
}
