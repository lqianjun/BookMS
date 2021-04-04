package net.hunau.bookms.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.bean.AdminIdConfirm;
import net.hunau.bookms.dao.AdminDAO;
import net.hunau.bookms.dao.AdminIdConfirmDAO;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminRegister extends JFrame {

	private JPanel contentPane;
	private JTextField C_username;
	private JPasswordField C_password;
	private JPasswordField C_rePassword;
	private JLabel label_3;
	private JButton button;
	private JLabel label_4;
	private JTextField C_confirm;
	private JTextField C_truename;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRegister frame = new AdminRegister();
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
	public AdminRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 200, 393, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7BA1\u7406\u5458\u6CE8\u518C");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(135, 13, 100, 18);
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
		
		label_3 = new JLabel("*\u9A8C\u8BC1\u7801\uFF1A");
		label_3.setBounds(71, 153, 84, 21);
		contentPane.add(label_3);
		
		submit();//提交按钮
		back();//返回按钮
		
		
		
	}
	public void submit()
	{
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
/**************************************************【对输入数据进行格式检查start】************************************************************/
				if(C_username.getText().isEmpty()) {		//判断用户名是否为空
					JOptionPane.showMessageDialog(AdminRegister.this, new String("请输入用户名！"));return;
				}
				if((new String(C_password.getPassword())).isEmpty())
				{
					JOptionPane.showMessageDialog(AdminRegister.this, new String("请输入密码！"));return;
				}
				if((new String(C_rePassword.getPassword())).isEmpty())
				{
					JOptionPane.showMessageDialog(AdminRegister.this, new String("请输入二次密码！"));return;
				}
				//判断两次密码是否一致
				if(!(new String(C_password.getPassword())).equals((new String(C_rePassword.getPassword()))))
				{
					JOptionPane.showMessageDialog(null, new String("两次输入密码不一致！"));
					C_password.setText("");
					C_rePassword.setText("");
					return;
					
				}
				if(C_confirm.getText().isEmpty()) {
					JOptionPane.showMessageDialog(AdminRegister.this, new String("请输入验证码！"));return;
				}
				if(C_truename.getText().isEmpty()) {
					JOptionPane.showMessageDialog(AdminRegister.this, new String("请输入真实姓名！"));return;
				}
/**************************************************【对输入数据进行格式检查end】************************************************************/
				
				Admin admin=new Admin();	
				AdminIdConfirm confirm =new AdminIdConfirm();	//记录验证码信息
				AdminDAO ad=new AdminDAO();
				AdminIdConfirmDAO ic=new AdminIdConfirmDAO();
				confirm=ic.confirmAdminId(C_confirm.getText().trim());
				if(confirm==null)
				{
					JOptionPane.showMessageDialog(AdminRegister.this, new String("该验证码无效！"));return;
				}
				admin.setAdminId(confirm.getAdminId());
				admin.setUsername(C_username.getText().trim());
				admin.setPassword(new String(C_password.getPassword()).trim());
				admin.setTrueName(C_truename.getText().trim());
				ad.addAdmin(admin);		//将注册的管理员信息加入数据库
				//再次访问数据库，检查其有无新注册号
				if(ad.getAdmin(C_username.getText(), new String(C_password.getPassword()))!=null)
				{
					JOptionPane.showMessageDialog(AdminRegister.this, new String("注册成功！"));
				}
				else
				{
					JOptionPane.showMessageDialog(AdminRegister.this, new String("注册失败！"));return;
				}
				ic.updateId(new String(C_confirm.getText())); //更新id验证表，让其值为0，表示已经使用过了
				
			}
		});
		btnNewButton.setBounds(102, 224, 72, 27);
		contentPane.add(btnNewButton);
	}
	
	public void back()
	{
		button = new JButton("\u8FD4\u56DE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(205, 224, 72, 27);
		contentPane.add(button);
		
		label_4 = new JLabel("*\u771F\u5B9E\u59D3\u540D\uFF1A");
		label_4.setBounds(71, 187, 84, 21);
		contentPane.add(label_4);
		
		C_confirm = new JTextField();
		C_confirm.setColumns(10);
		C_confirm.setBounds(149, 153, 163, 23);
		contentPane.add(C_confirm);
		
		C_truename = new JTextField();
		C_truename.setColumns(10);
		C_truename.setBounds(149, 187, 163, 23);
		contentPane.add(C_truename);
	}
}
