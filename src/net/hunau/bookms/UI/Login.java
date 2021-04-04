package net.hunau.bookms.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.dao.AdminDAO;
import net.hunau.bookms.dao.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private int role ;//登录角色，0为普通用户，1为管理员
	private String username;//用户名
	private String password;//密码
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("\u6E58\u519C\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 300, 440, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		addLabel();//添加标签
		addInput();//添加用户和密码文本框
		addButton();//添加登录和注册按钮
		addRadioButton();//添加单选按钮
		
		JLabel lblNewLabel_2 = new JLabel("New label");	// 背景图片
		lblNewLabel_2.setIcon(new ImageIcon("image//background.jpg"));
		lblNewLabel_2.setBounds(-59, -11, 485, 295);
		contentPane.add(lblNewLabel_2);
		
		
	}
	
	public void addRadioButton()		//单选按钮
	{
		JRadioButton userRb = new JRadioButton("\u666E\u901A\u7528\u6237");
		userRb.setSelected(true);
		userRb.setForeground(Color.BLUE);
		userRb.setBounds(118, 209, 99, 21);
		userRb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				role=0;
			}
		});
		contentPane.add(userRb);
		
		JRadioButton managerRb = new JRadioButton("\u7BA1\u7406\u5458");
		managerRb.setForeground(Color.BLUE);
		managerRb.setBounds(232, 209, 79, 21);
		managerRb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				role=1;
			}
		});
		contentPane.add(managerRb);
		
		ButtonGroup grout=new ButtonGroup();//创建一个组 把两个对象合并为组
		grout.add(userRb);
		grout.add(managerRb);
	}
	
	public void addLabel()
	{
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("黑体", Font.BOLD, 18));
		label_1.setBounds(66, 124, 79, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("黑体", Font.BOLD, 18));
		label_2.setBounds(66, 166, 79, 21);
		contentPane.add(label_2);
	}
	
	public void addInput()//添加用户和密码文本框
	{
		usernameText = new JTextField();
		usernameText.setBounds(139, 121, 131, 24);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		

		passwordText = new JPasswordField();
		passwordText.setBounds(139, 166, 131, 24);
		contentPane.add(passwordText);
	}
	
	public void addButton() //添加登录和注册按钮
	{

		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username=usernameText.getText();
				char [] passwords=passwordText.getPassword();
				password=new String(passwords);//将字符密码转换成可以阅读的字符串
				if(username.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(Login.this, new String("请输入用户名或密码！"));
					return ;
				}
				
				//根据登录身份跳转至相应的界面
				if(role==0)
				{
					UserDAO ud =new UserDAO();				
					User user= ud.getUser(username, password);
					if(user==null)
					{
						JOptionPane.showMessageDialog(Login.this, new String("用户名或密码错误!"));
						return;
					}
					else 
					{	//对是否封号进行检查
						if(user.getValidity()==0) {	
							JOptionPane.showMessageDialog(Login.this, new String("您的账号已被封!"));
							return ;
						}
						else
						{
							try {
								new UserPage(user).setVisible(true);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							dispose();
						}
						
					}
					
					
				}
				else
				{
					AdminDAO ad=new AdminDAO();
					Admin admin=ad.getAdmin(username, password);
					if(admin==null)
					{
						JOptionPane.showMessageDialog(Login.this, new String("用户名或密码错误!"));
						return;
					}
					else 
					{	
						new AdminPage(admin).setVisible(true);
						dispose();
					}
				}
				
				
			}
		});
		button.setBounds(293, 124, 79, 24);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//用选项对话框获得用户注册身份role;
				int registRole = 1000;//将注册角色设置为非0和1的数,通过选项对话框重新赋值为0或1
				registRole=JOptionPane.showOptionDialog(Login.this, "请选择注册身份", "身份验证", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"普通用户","管理员"},null);
				if(registRole==0) //如果注册的普通用户，跳转至普通用户注册界面
				{
					new UserRegister().setVisible(true);
				}
				else if(registRole==1)
				{
					new AdminRegister().setVisible(true);
				}
				else	//非0和1，表示没有选择身份，返回
				{
					return;
				}
			}
		});
		button_1.setBounds(293, 166, 79, 24);
		contentPane.add(button_1);
	}
}
