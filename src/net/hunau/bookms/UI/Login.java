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
	private int role ;//��¼��ɫ��0Ϊ��ͨ�û���1Ϊ����Ա
	private String username;//�û���
	private String password;//����
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
		
		addLabel();//��ӱ�ǩ
		addInput();//����û��������ı���
		addButton();//��ӵ�¼��ע�ᰴť
		addRadioButton();//��ӵ�ѡ��ť
		
		JLabel lblNewLabel_2 = new JLabel("New label");	// ����ͼƬ
		lblNewLabel_2.setIcon(new ImageIcon("image//background.jpg"));
		lblNewLabel_2.setBounds(-59, -11, 485, 295);
		contentPane.add(lblNewLabel_2);
		
		
	}
	
	public void addRadioButton()		//��ѡ��ť
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
		
		ButtonGroup grout=new ButtonGroup();//����һ���� ����������ϲ�Ϊ��
		grout.add(userRb);
		grout.add(managerRb);
	}
	
	public void addLabel()
	{
		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("����", Font.BOLD, 18));
		label_1.setBounds(66, 124, 79, 21);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("����", Font.BOLD, 18));
		label_2.setBounds(66, 166, 79, 21);
		contentPane.add(label_2);
	}
	
	public void addInput()//����û��������ı���
	{
		usernameText = new JTextField();
		usernameText.setBounds(139, 121, 131, 24);
		contentPane.add(usernameText);
		usernameText.setColumns(10);
		

		passwordText = new JPasswordField();
		passwordText.setBounds(139, 166, 131, 24);
		contentPane.add(passwordText);
	}
	
	public void addButton() //��ӵ�¼��ע�ᰴť
	{

		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				username=usernameText.getText();
				char [] passwords=passwordText.getPassword();
				password=new String(passwords);//���ַ�����ת���ɿ����Ķ����ַ���
				if(username.equals("") || password.equals(""))
				{
					JOptionPane.showMessageDialog(Login.this, new String("�������û��������룡"));
					return ;
				}
				
				//���ݵ�¼�����ת����Ӧ�Ľ���
				if(role==0)
				{
					UserDAO ud =new UserDAO();				
					User user= ud.getUser(username, password);
					if(user==null)
					{
						JOptionPane.showMessageDialog(Login.this, new String("�û������������!"));
						return;
					}
					else 
					{	//���Ƿ��Ž��м��
						if(user.getValidity()==0) {	
							JOptionPane.showMessageDialog(Login.this, new String("�����˺��ѱ���!"));
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
						JOptionPane.showMessageDialog(Login.this, new String("�û������������!"));
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
				//��ѡ��Ի������û�ע�����role;
				int registRole = 1000;//��ע���ɫ����Ϊ��0��1����,ͨ��ѡ��Ի������¸�ֵΪ0��1
				registRole=JOptionPane.showOptionDialog(Login.this, "��ѡ��ע�����", "�����֤", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object[]{"��ͨ�û�","����Ա"},null);
				if(registRole==0) //���ע�����ͨ�û�����ת����ͨ�û�ע�����
				{
					new UserRegister().setVisible(true);
				}
				else if(registRole==1)
				{
					new AdminRegister().setVisible(true);
				}
				else	//��0��1����ʾû��ѡ����ݣ�����
				{
					return;
				}
			}
		});
		button_1.setBounds(293, 166, 79, 24);
		contentPane.add(button_1);
	}
}
