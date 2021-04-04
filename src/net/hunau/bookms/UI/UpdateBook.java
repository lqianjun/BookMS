package net.hunau.bookms.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Book;
import net.hunau.bookms.dao.BookDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class UpdateBook extends JFrame {

	private JPanel contentPane;
	private JTextField C_barcode;
	private JTextField C_bookname;
	private JTextField C_author;
	private JTextField C_bookTypeId;
	private JTextField C_location;
	private JTextField C_price;
	private JTextField C_publish;
	private JTextField C_publishDate;
	private JTextField C_introduction;
	private JTextField C_tbarcode;
	private JTextField C_damage;


	/**
	 * Create the frame.
	 */
	public UpdateBook(Book book) {
		setBounds(700, 250, 436, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u7F16\u8F91\u56FE\u4E66");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setBounds(174, 13, 135, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*\u56FE\u4E66\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setBounds(46, 50, 85, 18);
		contentPane.add(lblNewLabel_1);
		
		C_barcode = new JTextField();
		C_barcode.setBounds(122, 47, 245, 24);
		contentPane.add(C_barcode);
		C_barcode.setColumns(10);
		
		C_bookname = new JTextField();
		C_bookname.setColumns(10);
		C_bookname.setBounds(122, 78, 245, 24);
		contentPane.add(C_bookname);
		
		C_author = new JTextField();
		C_author.setColumns(10);
		C_author.setBounds(122, 115, 245, 24);
		contentPane.add(C_author);
		
		C_bookTypeId = new JTextField();
		C_bookTypeId.setToolTipText("\u53C2\u8003bookType\u8868");
		C_bookTypeId.setColumns(10);
		C_bookTypeId.setBounds(122, 150, 245, 24);
		contentPane.add(C_bookTypeId);
		
		C_location = new JTextField();
		C_location.setColumns(10);
		C_location.setBounds(122, 187, 245, 24);
		contentPane.add(C_location);
		
		C_price = new JTextField();
		C_price.setColumns(10);
		C_price.setBounds(122, 223, 245, 24);
		contentPane.add(C_price);
		
		C_publish = new JTextField();
		C_publish.setColumns(10);
		C_publish.setBounds(122, 260, 245, 24);
		contentPane.add(C_publish);
		
		C_publishDate = new JTextField();
		C_publishDate.setToolTipText("\u4F8B2019-04-01");
		C_publishDate.setColumns(10);
		C_publishDate.setBounds(122, 297, 245, 24);
		contentPane.add(C_publishDate);
		
		C_introduction = new JTextField();
		C_introduction.setColumns(10);
		C_introduction.setBounds(122, 334, 245, 24);
		contentPane.add(C_introduction);
		
		JLabel label = new JLabel("*\u56FE\u4E66\u540D\u79F0\uFF1A");
		label.setBounds(46, 81, 85, 18);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("*\u4F5C  \u8005\uFF1A");
		label_1.setBounds(46, 118, 85, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*\u7C7B\u578B\u7F16\u53F7\uFF1A");
		label_2.setBounds(46, 152, 85, 18);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("*\u5B58\u653E\u4F4D\u7F6E\uFF1A");
		label_3.setBounds(46, 193, 85, 18);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("*\u4EF7  \u683C\uFF1A");
		label_4.setBounds(46, 226, 85, 18);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel(" \u51FA\u7248\u793E\uFF1A");
		label_5.setBounds(46, 263, 85, 18);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("\u51FA\u7248\u65E5\u671F\uFF1A");
		label_6.setBounds(46, 300, 85, 18);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u5185\u5BB9\u7B80\u4ECB\uFF1A");
		label_7.setBounds(46, 334, 85, 18);
		contentPane.add(label_7);
		//将book数据刷新到文本框上
		C_barcode.setText(book.getBarcode());
		//C_tbarcode.setText(book.getTbarcode());
		C_author.setText(book.getAuthor());
		C_bookTypeId.setText(book.getBookTypeId());
		C_location.setText(book.getLocation());
		C_price.setText(book.getPrice()+"");
		//C_damage.setText(book.getDamage()+"");
		C_publish.setText(book.getPublish());
		C_publishDate.setText(book.getPublishDate()+"");
		C_introduction.setText(book.getIntroduction());
		C_bookname.setText(book.getBookName());
		
		JButton btnNewButton = new JButton("\u66F4\u65B0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	/**************************************************【对输入数据进行格式检查start】************************************************************/
				if(C_barcode.getText().isEmpty()) {		//判断用户名是否为空
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入书籍条码！"));return;
				}
				if(C_bookname.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入书名！"));return;
				}
				if(C_author.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入作者！"));return;
				}
				if(C_bookTypeId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入类型编码！"));return;
				}
				if(C_location.getText().isEmpty()) {
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入存放位置！"));return;
				}
				if(C_tbarcode.getText().isEmpty()) {
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入一级编号！"));return;
				}
				if(C_damage.getText().isEmpty()) {
					JOptionPane.showMessageDialog(UpdateBook.this, new String("请输入新旧程度！"));return;
				}
	/**************************************************【对输入数据进行格式检查end】************************************************************/
				BookDAO bd = new BookDAO();
				book.setBarcode(C_barcode.getText().trim());
				book.setBookName(C_bookname.getText().trim());
				book.setAuthor(C_author.getText().trim());
				//验证输入的书籍类型是否正确，错误则返回，不进行下一步
				try {
					if(!bd.isRightBookTypeId(C_bookTypeId.getText().trim()))
					{
						JOptionPane.showMessageDialog(UpdateBook.this, "请输入正确的书籍类型编号！");return;
					}
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				book.setBookTypeId(C_bookTypeId.getText().trim());
				book.setLocation(C_location.getText().trim());
				try {
					book.setPrice(Float.parseFloat(C_price.getText().trim()));
				}
				catch(NumberFormatException e)
				{
					JOptionPane.showMessageDialog(UpdateBook.this, "非法输入，请在价格栏输入数值!");return;
				}
				
				java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
				Date date = null;
				try {
					date = (Date) formatter.parse(C_publishDate.getText().trim());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				book.setPublishDate(date);
				book.setDamage(Integer.parseInt(C_damage.getText().trim()));
				book.setIntroduction(C_introduction.getText().trim());
				book.setTbarcode(C_tbarcode.getText().trim());
				JOptionPane.showMessageDialog(UpdateBook.this, "更新成功！");	
			}
		});
		btnNewButton.setBounds(82, 434, 113, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setBounds(217, 434, 113, 27);
		contentPane.add(button);
		
		JLabel label_8 = new JLabel("*\u4E00\u7EA7\u7F16\u53F7\uFF1A");
		label_8.setBounds(46, 368, 85, 18);
		contentPane.add(label_8);
		
		C_tbarcode = new JTextField();
		C_tbarcode.setColumns(10);
		C_tbarcode.setBounds(122, 366, 245, 24);
		contentPane.add(C_tbarcode);
		
		JLabel label_9 = new JLabel("*\u65B0\u65E7\u7A0B\u5EA6\uFF1A");
		label_9.setBounds(46, 403, 85, 18);
		contentPane.add(label_9);
		
		C_damage = new JTextField();
		C_damage.setToolTipText("0%\uFF0C\u767E\u5206\u6BD4\u8D8A\u9AD8\u4E66\u8D8A\u65E7");
		C_damage.setColumns(10);
		C_damage.setBounds(122, 399, 100, 24);
		contentPane.add(C_damage);
		
		JLabel label_10 = new JLabel(" %");
		label_10.setFont(new Font("宋体", Font.PLAIN, 20));
		label_10.setBounds(217, 403, 49, 18);
		contentPane.add(label_10);
	}
}
