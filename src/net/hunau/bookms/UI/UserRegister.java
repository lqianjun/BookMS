package net.hunau.bookms.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.AdminIdConfirm;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.dao.AdminIdConfirmDAO;
import net.hunau.bookms.dao.UserDAO;
import java.awt.Font;

public class UserRegister extends JFrame {


	private JPanel contentPane;
		int role;
	private JTextField C_username;
	private JPasswordField C_password;
	private JPasswordField C_rePassword;
	private JTextField C_userNo;
	private JTextField C_sex;
	private JTextField C_address;
	private JTextField C_email;
	private JTextField C_qq;
	private JTextField C_telephone;
	private JTextField C_birthday;
	private JTextField C_readerType;
	private JTextField C_truename;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegister frame = new UserRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public UserRegister() {
		setResizable(false);
		setBounds(800, 200, 406, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7528\u6237\u6CE8\u518C");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(163, 13, 89, 18);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("*\u7528\u6237\u540D\uFF1A");
		lblNewLabel.setBounds(71, 49, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("*\u5BC6  \u7801\uFF1A");
		label_1.setBounds(71, 84, 72, 18);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("*\u518D\u6B21\u8F93\u5165\uFF1A");
		label_2.setBounds(71, 119, 84, 21);
		contentPane.add(label_2);
		
		C_username = new JTextField();
		C_username.setBounds(149, 44, 163, 23);
		contentPane.add(C_username);
		C_username.setColumns(10);
		
		C_password = new JPasswordField();
		C_password.setBounds(149, 81, 163, 21);
		contentPane.add(C_password);
		
		C_rePassword = new JPasswordField();
		C_rePassword.setBounds(149, 117, 163, 23);
		contentPane.add(C_rePassword);
		
		JLabel label_3 = new JLabel("*\u5B66  \u53F7\uFF1A");
		label_3.setBounds(71, 154, 84, 21);
		contentPane.add(label_3);
		
		C_userNo = new JTextField();
		C_userNo.setColumns(10);
		C_userNo.setBounds(149, 152, 163, 23);
		contentPane.add(C_userNo);
		
		JLabel label_4 = new JLabel("\u6027  \u522B\uFF1A");
		label_4.setBounds(71, 188, 84, 21);
		contentPane.add(label_4);
		
		C_sex = new JTextField();
		C_sex.setColumns(10);
		C_sex.setBounds(149, 188, 163, 23);
		contentPane.add(C_sex);
		
		JLabel label_5 = new JLabel("\u5730  \u5740\uFF1A");
		label_5.setBounds(71, 222, 84, 21);
		contentPane.add(label_5);
		
		C_address = new JTextField();
		C_address.setColumns(10);
		C_address.setBounds(149, 222, 163, 23);
		contentPane.add(C_address);
		
		JLabel label_6 = new JLabel("\u90AE  \u7BB1\uFF1A");
		label_6.setBounds(71, 256, 84, 21);
		contentPane.add(label_6);
		
		JLabel lblQQ = new JLabel(" Q  Q\uFF1A");
		lblQQ.setBounds(71, 290, 84, 21);
		contentPane.add(lblQQ);
		
		JLabel label_8 = new JLabel("\u7535  \u8BDD\uFF1A");
		label_8.setBounds(71, 324, 84, 21);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
		label_9.setBounds(71, 358, 84, 21);
		contentPane.add(label_9);
		
		C_email = new JTextField();
		C_email.setColumns(10);
		C_email.setBounds(149, 256, 163, 23);
		contentPane.add(C_email);
		
		C_qq = new JTextField();
		C_qq.setColumns(10);
		C_qq.setBounds(149, 290, 163, 23);
		contentPane.add(C_qq);
		
		C_telephone = new JTextField();
		C_telephone.setColumns(10);
		C_telephone.setBounds(149, 324, 163, 23);
		contentPane.add(C_telephone);
		
		C_birthday = new JTextField();
		C_birthday.setToolTipText("\u4F8B\uFF1A1997-12-02");
		C_birthday.setColumns(10);
		C_birthday.setBounds(149, 358, 163, 23);
		contentPane.add(C_birthday);
		
		JLabel label_7 = new JLabel("*\u8EAB  \u4EFD\uFF1A");
		label_7.setBounds(71, 392, 84, 21);
		contentPane.add(label_7);
		
		C_readerType = new JTextField();
		C_readerType.setColumns(10);
		C_readerType.setBounds(149, 391, 163, 23);
		contentPane.add(C_readerType);
		
		JLabel label_10 = new JLabel("*\u771F\u5B9E\u59D3\u540D\uFF1A");
		label_10.setBounds(71, 429, 84, 21);
		contentPane.add(label_10);
		
		C_truename = new JTextField();
		C_truename.setColumns(10);
		C_truename.setBounds(149, 426, 163, 23);
		contentPane.add(C_truename);
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

/**************************************************【对输入数据进行格式检查start】************************************************************/
			if(C_username.getText().isEmpty()) {		//判断用户名是否为空
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入用户名！"));return;
			}
			if((new String(C_password.getPassword())).isEmpty())
			{
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入密码！"));return;
			}
			if((new String(C_rePassword.getPassword())).isEmpty())
			{
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入二次密码！"));return;
			}
			//判断两次密码是否一致
			if(!(new String(C_password.getPassword())).equals((new String(C_rePassword.getPassword()))))
			{
				JOptionPane.showMessageDialog(null, new String("两次输入密码不一致！"));
				C_password.setText("");
				C_rePassword.setText("");
				return;
				
			}
			if(C_userNo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入学号！"));return;
			}
			if(C_readerType.getText().isEmpty()) {
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入身份！"));return;
			}
			if(C_truename.getText().isEmpty()) {
				JOptionPane.showMessageDialog(UserRegister.this, new String("请输入真实姓名！"));return;
			}
/**************************************************【对输入数据进行格式检查end】************************************************************/
			User user=new User();
			UserDAO ud=new UserDAO();
			user.setUserNo(C_userNo.getText().trim());
			user.setUsername(C_username.getText().trim());
			user.setTruename(C_truename.getText().trim());
			user.setPassword(new String(C_password.getPassword()).trim());
			user.setReaderType(C_readerType.getText().trim());
			user.setSex(C_sex.getText().trim());
			user.setTelePhone(C_telephone.getText().trim());
			user.setEmail(C_email.getText().trim());
			user.setQQ(C_qq.getText().trim());
			user.setAddress(C_address.getText().trim());
			user.setBirthday(C_birthday.getText().trim());
			System.out.println(user+"***************************************");
			//检查是否注册
			if(ud.getUser(user.getUsername(), user.getPassword())!=null)
			{
				JOptionPane.showMessageDialog(UserRegister.this, "该账号已经注册过！");
				return;
			}
			ud.addUser(user); 	//将用户添加进数据库
			if(ud.getUser(user.getUsername(), user.getPassword())!=null)
				JOptionPane.showMessageDialog(UserRegister.this, "注册成功！");
			
		 }
		});
		btnNewButton.setBounds(128, 472, 63, 32);
		contentPane.add(btnNewButton);
		//返回按钮设置
		JButton button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);//跳转至登录界面
				dispose();
			}
		});
		button.setBounds(229, 472, 63, 32);
		contentPane.add(button);
		
		
	}
	
}
