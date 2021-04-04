package net.hunau.bookms.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.tree.DefaultTreeModel;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.bean.Book;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.dao.AdminDAO;
import net.hunau.bookms.dao.BookDAO;
import net.hunau.bookms.dao.UserDAO;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
//import javax.swing.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	static Admin admin;
	Timer timer;
	JLabel labelDate;
	JTextField textWelcome;
	int index;
	JButton buttons;
	private JTextField nowUser;
	List<Book> searchBookResult;
	final CardLayout card;
	final JPanel panel_1;
	int searchType;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_8;
	private JTextField textField_1;
	private JTextField textField_5;
	private JTextField textField_9;
	private JTextField textField_2;
	private JTextField textField_6;
	private JTextField textField_10;
	private JTextField textField_3;
	private JTextField textField_7;
	private JTextField textField_11;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDAO ad=new AdminDAO();
					Admin admin=ad.getAdmin("admin", "admin");
					System.out.println(admin);
					AdminPage frame = new AdminPage(admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminPage(Admin admin) {
		setResizable(false);
		setTitle("\u7BA1\u7406\u5458\u754C\u9762");
		this.admin=admin;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(1,1, 1, 1));
		contentPane.setLayout(null);
		setContentPane(contentPane);	
		
		JPanel headPanel = getHeadPanel();//初始化head面板
		JPanel controlPanel = getControlPanel();//初始化操作区
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(186, 80, 706, 473);
		card=new CardLayout();
		panel_1.setLayout(card);
		//卡片式布局，点击相应按钮跳转到不同模块
		JPanel homePage=getHomePage();//首页
		JPanel borrowRecordPanel=getBorrowRecord();//借阅记录模块
		JPanel searchUserPanel = getSearchUserPanel();//查找用户模块
		JPanel myInfoPanel = getMyInfoPanel(admin);//我的信息模块
		JPanel bookSeachPanel = getBookSearch();//图书检索
		JPanel feedbackPanel = getFeedbackPanel();//反馈模块
		panel_1.add(homePage,"首页");
		panel_1.add(borrowRecordPanel,"借阅记录");
		panel_1.add(searchUserPanel,"查找用户");
		panel_1.add(myInfoPanel,"我的信息");
		panel_1.add(bookSeachPanel,"图书检索");
		panel_1.add(feedbackPanel,"反馈模块");
		card.show(panel_1, "首页");
		contentPane.add(panel_1);
		
		
		
		
		timer=new Timer();	//利用定时器设置界面滚动文字和时间
		timer.schedule(new TimerTask(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");		
			public void run() {	
				
				index++;
				String text="欢迎光临农大图书馆      ";
                if (index >= text.length()) {
                    index = 0;
                }
                //标题栏要显示的字符
                String str = text.substring(index, text.length()) + text.substring(0, index);
				labelDate.setText(sdf.format(new Date()));
				textWelcome.setText(str);
			}
			 
		 }, 0,1000);
	
		
	}
	
	public JPanel getRechargePanel(User user) //此面板用于充值
	{
		JPanel rechargePanel = new JPanel();
		rechargePanel.setBackground(SystemColor.activeCaption);
		rechargePanel.setBounds(0, 0, 706, 473);
		rechargePanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("华文新魏", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(121, 86, 149, 30);
		rechargePanel.add(lblNewLabel_2);
		
		JLabel label = new JLabel("\u5B66    \u53F7\uFF1A");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("华文新魏", Font.PLAIN, 35));
		label.setBounds(121, 149, 149, 30);
		rechargePanel.add(label);
		
		JLabel label_1 = new JLabel("\u624B\u673A\u53F7\uFF1A");
		label_1.setForeground(SystemColor.textHighlight);
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 35));
		label_1.setBounds(121, 208, 149, 30);
		rechargePanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u91D1    \u989D\uFF1A");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("华文新魏", Font.PLAIN, 35));
		label_2.setBounds(121, 267, 149, 30);
		rechargePanel.add(label_2);
		
		JTextField usernameText = new JTextField();
		usernameText.setForeground(Color.GREEN);
		usernameText.setFont(new Font("宋体", Font.PLAIN, 20));
		usernameText.setEditable(false);
		usernameText.setBounds(261, 86, 149, 32);
		rechargePanel.add(usernameText);
		usernameText.setColumns(10);
		
		JTextField userNoText = new JTextField();
		userNoText.setForeground(Color.GREEN);
		userNoText.setFont(new Font("宋体", Font.PLAIN, 20));
		userNoText.setEditable(false);
		userNoText.setColumns(10);
		userNoText.setBounds(261, 149, 149, 32);
		rechargePanel.add(userNoText);
		
		JTextField telephoneText = new JTextField();
		telephoneText.setForeground(new Color(0, 255, 0));
		telephoneText.setFont(new Font("宋体", Font.PLAIN, 20));
		telephoneText.setColumns(10);
		telephoneText.setBounds(261, 206, 149, 32);
		rechargePanel.add(telephoneText);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(10)));
		spinner.setFont(new Font("宋体", Font.PLAIN, 20));
		spinner.setBounds(261, 267, 149, 32);
		rechargePanel.add(spinner);
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u4F59\u989D");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("华文新魏", Font.PLAIN, 35));
		label_3.setBounds(508, 149, 149, 30);
		rechargePanel.add(label_3);
		
		JTextField C_balance = new JTextField();
		C_balance.setForeground(SystemColor.activeCaption);
		C_balance.setFont(new Font("宋体", Font.PLAIN, 20));
		C_balance.setEditable(false);
		C_balance.setColumns(10);
		C_balance.setBounds(508, 206, 149, 32);
		rechargePanel.add(C_balance);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(469, 0, 237, 460);
		rechargePanel.add(panel);
		
		JRadioButton radioButton = new JRadioButton("\u652F\u4ED8\u5B9D");
		radioButton.setSelected(true);
		radioButton.setForeground(SystemColor.textHighlight);
		radioButton.setBounds(162, 314, 73, 27);
		rechargePanel.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5FAE\u4FE1");
		radioButton_1.setForeground(SystemColor.textHighlight);
		radioButton_1.setBounds(271, 314, 73, 27);
		rechargePanel.add(radioButton_1);
		ButtonGroup bgr =new ButtonGroup();
		bgr.add(radioButton_1);
		bgr.add(radioButton);
		
		C_balance.setText(user.getBalance()+"");	//显示当前余额
		usernameText.setText(user.getUsername());	//显示用户名
		userNoText.setText(user.getUserNo());		//显示学号
		telephoneText.setText(user.getTelePhone());	//显示手机
		JButton btnNewButton_5 = new JButton("\u786E\u8BA4");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float balance=Float.parseFloat(C_balance.getText().trim());
				user.setBalance((int)spinner.getValue()+balance);
				C_balance.setText(user.getBalance()+"");
				UserDAO ud =new UserDAO();
				ud.updateUser(user);//更新用户书籍
				JOptionPane.showMessageDialog(AdminPage.this, "充值成功！");
			}
		});
		btnNewButton_5.setForeground(SystemColor.textHighlight);
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_5.setBounds(213, 366, 113, 27);
		rechargePanel.add(btnNewButton_5);
		
		return rechargePanel;
	}
	
	public JPanel getHeadPanel() //界面顶端设计
	{
		JPanel headPanel = new JPanel();
		headPanel.setBounds(-49, -11, 941, 89);
		headPanel.setLayout(null);
		contentPane.add(headPanel);
		JLabel lblNewLabel = new JLabel("湘农图书");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		lblNewLabel.setIcon(new ImageIcon("image//icon.png"));
		lblNewLabel.setBounds(57, 12, 171, 60);
		headPanel.add(lblNewLabel);
		
		labelDate = new JLabel("1949\u5E7410\u670801\u65E5 12\u70B900\u520600\u79D2");
		labelDate.setForeground(Color.RED);
		labelDate.setFont(new Font("宋体", Font.BOLD, 20));
		labelDate.setBounds(637, 63, 304, 22);
		headPanel.add(labelDate);
		
		textWelcome = new JTextField();
		textWelcome.setEditable(false);
		textWelcome.setFont(new Font("宋体", Font.PLAIN, 20));
		textWelcome.setForeground(Color.GREEN);
		textWelcome.setText("\u6B22\u8FCE\u5149\u4E34\u519C\u5927\u56FE\u4E66\u9986");
		textWelcome.setHorizontalAlignment(SwingConstants.TRAILING);
		textWelcome.setBounds(693, 13, 191, 40);
		headPanel.add(textWelcome);
		textWelcome.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(-35, 63, 246, 495);
		headPanel.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(637, 12, 1, 2);
		headPanel.add(separator);
		
		JLabel label_6 = new JLabel("\u70ED\u641C\u699C\u5355");
		label_6.setBounds(242, 12, 72, 18);
		headPanel.add(label_6);
		
		JLabel label_1 = new JLabel("\u70ED\u95E8\u63A8\u8350");
		label_1.setBounds(242, 41, 72, 18);
		headPanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BFB\u8005\u4EBA\u7FA4");
		label_2.setBounds(242, 67, 72, 18);
		headPanel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(328, 12, 86, 24);
		headPanel.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setText("\u8BA1\u7B97\u673A");
		textField.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(328, 37, 86, 24);
		headPanel.add(textField_4);
		textField_4.setText("\u70ED\u95E8\u5C0F\u8BF4");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(328, 64, 86, 24);
		headPanel.add(textField_8);
		textField_8.setText("\u4EBA\u7269\u4F20\u8BB0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(428, 12, 86, 24);
		headPanel.add(textField_1);
		textField_1.setText("\u690D\u7269\u5B66");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(428, 37, 86, 24);
		headPanel.add(textField_5);
		textField_5.setText("\u6559\u8F85\u6559\u6750");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(428, 64, 86, 24);
		headPanel.add(textField_9);
		textField_9.setText("\u52A8\u6F2B\u5E7D\u9ED8");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(519, 12, 86, 24);
		headPanel.add(textField_2);
		textField_2.setText("\u75C5\u7406\u5B66");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(519, 38, 86, 24);
		headPanel.add(textField_6);
		textField_6.setText("\u9752\u6625\u6587\u5B66");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(519, 64, 86, 24);
		headPanel.add(textField_10);
		textField_10.setText("\u82F1\u6587\u539F\u7248");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		

		return headPanel;
	}
	
	public JPanel getHomePage()//首页
	{
		JPanel contentPane;
		JLabel lblNewLabel;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JPanel Search = new JPanel();
		Search.setForeground(Color.WHITE);
		Search.setBackground(SystemColor.inactiveCaptionBorder);
		Search.setBounds(0, 0, 692, 473);
		Search.setLayout(null);
		contentPane.add(Search);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("image//main.png"));
		lblNewLabel_1.setBounds(4, 0, 678, 473);
		Search.add(lblNewLabel_1);
		return contentPane;
	}
	
	public JPanel getControlPanel()	//控制面板
	{
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setForeground(Color.RED);
		controlPanel.setBounds(0, 80, 189, 473);
		controlPanel.setLayout(null);
		contentPane.add(controlPanel);
		
		JComboBox comboBox_1 = new JComboBox();
		controlPanel.add(comboBox_1);
		
		nowUser = new JTextField();
		nowUser.setFont(new Font("宋体", Font.PLAIN, 20));
		nowUser.setEditable(false);
		nowUser.setBackground(Color.YELLOW);
		nowUser.setForeground(Color.BLUE);
		nowUser.setHorizontalAlignment(SwingConstants.CENTER);
		nowUser.setBounds(24, 34, 137, 27);
		controlPanel.add(nowUser);
		nowUser.setColumns(10);
		nowUser.setText(admin.getTruename());
		
		JButton btnNewButton_1 = new JButton("\u6211\u7684\u4FE1\u606F");
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "我的信息");
				System.out.println(admin);
			}
		});
		btnNewButton_1.setBounds(-2, 65, 92, 27);
		controlPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "首页");
				new UpdateAdminInfo(admin).setVisible(true);
			}
		});
		btnNewButton_2.setBounds(94, 65, 92, 27);
		controlPanel.add(btnNewButton_2);
		
		JButton button = new JButton("\u67E5\u627E\u7528\u6237");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "查找用户");
			}
		});
		button.setBounds(94, 134, 92, 27);
		controlPanel.add(button);
		
		JButton button_1 = new JButton("\u5220\u9664\u7528\u6237");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNo=JOptionPane.showInputDialog("请输入您要删除的用户学号");
				if(userNo==null) return;//用户没输入数值，则返归
				AdminDAO ad=new AdminDAO();
				try {
					if(ad.getUserByNo(userNo)==null)
					{
						JOptionPane.showMessageDialog(AdminPage.this, "您要删除的用户不存在");
						return;
					}
					else
					{
						ad.delUser(userNo);//删除用户
						if(ad.getUserByNo(userNo)==null) //确认是否已删除
							JOptionPane.showMessageDialog(AdminPage.this, "删除成功！");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(94, 105, 92, 27);
		controlPanel.add(button_1);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u7528\u6237");
		label_1.setFont(new Font("华文新魏", Font.PLAIN, 26));
		label_1.setForeground(new Color(255, 0, 0));
		label_1.setBackground(SystemColor.textHighlight);
		label_1.setBounds(41, 0, 110, 34);
		controlPanel.add(label_1);
		
		JButton button_2 = new JButton("\u5220\u9664\u56FE\u4E66");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barcode=JOptionPane.showInputDialog("请输入您要删除的图书编号");
				if(barcode==null) return;//用户没输入数值，则返归
				BookDAO bd = new BookDAO();
				try {
					if(bd.getBookInfoByBarcode(barcode)==null)
					{
						JOptionPane.showMessageDialog(AdminPage.this, "您要删除的图书不存在");
						return;
					}
					else
					{
						bd.deleBook(barcode);//删除图书
						if(bd.getBookInfoByBarcode(barcode)==null) //确认是否已删除
							JOptionPane.showMessageDialog(AdminPage.this, "删除成功！");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(94, 172, 92, 27);
		controlPanel.add(button_2);
		
		JButton btnNewButton_3 = new JButton("\u6DFB\u52A0\u56FE\u4E66");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddBook().setVisible(true);
			}
		});
		btnNewButton_3.setBounds(-2, 172, 92, 27);
		controlPanel.add(btnNewButton_3);
		
		JButton button_3 = new JButton("\u9996\u9875");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "首页");
			}
		});
		button_3.setBounds(0, 300, 92, 27);
		controlPanel.add(button_3);
		
		JButton btnNewButton_4 = new JButton("\u66F4\u6362\u8D26\u53F7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(41, 433, 113, 27);
		controlPanel.add(btnNewButton_4);
		
		JButton button_4 = new JButton("\u6DFB\u52A0\u7528\u6237");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserRegister().setVisible(true);
			}
		});
		button_4.setBounds(-2, 105, 92, 27);
		controlPanel.add(button_4);
		
		JButton button_5 = new JButton("\u7F16\u8F91\u7528\u6237");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userNo=JOptionPane.showInputDialog("请输入您要编辑的用户学号！");
				if(userNo==null) return;//用户没输入数值，则返归
				AdminDAO ad = new AdminDAO();//按编号查询用户的dao在Admin里面，UserDAO里面没有
				User user =ad.getUserByNo(userNo) ;
				if(user==null)
				{
					JOptionPane.showMessageDialog(AdminPage.this, "您要编辑的用户信息不存在!");
					return;
				}
				new UpdateUserInfo(user).setVisible(true);
				
			}
		});
		button_5.setBounds(-2, 134, 92, 27);
		controlPanel.add(button_5);
		
		JButton button_6 = new JButton("\u7F16\u8F91\u56FE\u4E66");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String barcode =JOptionPane.showInputDialog("请输入您需要编辑的图书编号");
				if(barcode==null) return;//用户没有输入，返回
				BookDAO bd = new BookDAO();
				Book book;
				try {
					book = bd.getBookInfoByBarcode(barcode);
					if(book==null)
					{
						JOptionPane.showMessageDialog(AdminPage.this, "你要编辑的用户不存在!");
						return;
					}
					else
					{
						System.out.println(book);
						new UpdateBook(book).setVisible(true);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button_6.setBounds(-2, 201, 92, 27);
		controlPanel.add(button_6);
		
		JButton button_7 = new JButton("\u56FE\u4E66\u68C0\u7D22");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(panel_1, "图书检索");
			}
		});
		button_7.setBounds(94, 201, 92, 27);
		controlPanel.add(button_7);
		
		JButton button_8 = new JButton("\u501F\u9605\u7BA1\u7406");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "借阅记录");
			}
		});
		button_8.setBounds(0, 241, 92, 27);
		controlPanel.add(button_8);
		
		JButton button_9 = new JButton("\u66F4\u591A\u529F\u80FD");
		button_9.setBounds(94, 300, 92, 27);
		controlPanel.add(button_9);
		
		JButton button_10 = new JButton("\u7528\u6237\u53CD\u9988");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "反馈模块");
			}
		});
		button_10.setBounds(94, 241, 92, 27);
		controlPanel.add(button_10);
		
		JButton button_11 = new JButton("\u6DFB\u52A0\u7BA1\u7406");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(admin.getLevel()==0) //判断是不是超级管理员
				{
					JOptionPane.showMessageDialog(AdminPage.this, "您不是超级管理员！");return;
				}
				else
				{
					new AdminRegister().setVisible(true);
				}
			}
		});
		button_11.setFont(new Font("宋体", Font.PLAIN, 13));
		button_11.setBounds(0, 380, 92, 27);
		controlPanel.add(button_11);
		
		JButton button_12 = new JButton("\u5220\u9664\u7BA1\u7406");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(admin.getLevel()==0) //判断是不是超级管理员
				{
					JOptionPane.showMessageDialog(AdminPage.this, "您不是超级管理员！");return;
				}
				else
				{
					String adminid = JOptionPane.showInputDialog("请输入要删除的管理员编号！");
					if(adminid==null) return;
					AdminDAO ad = new AdminDAO();
					ad.delAdmin(adminid);//删除管理员，再验证数据库是否还有记录
					Admin a=ad.getAdmin(adminid);
					if(a==null) JOptionPane.showMessageDialog(AdminPage.this, "删除成功！");
				}
			}
		});
		button_12.setFont(new Font("宋体", Font.PLAIN, 13));
		button_12.setBounds(94, 380, 92, 27);
		controlPanel.add(button_12);
		
		JButton button_13 = new JButton("\u4E66\u67B6\u7BA1\u7406");
		button_13.setBounds(0, 273, 92, 27);
		controlPanel.add(button_13);
		
		JButton button_14 = new JButton("\u5907\u4EFD\u6062\u590D");
		button_14.setBounds(94, 273, 92, 27);
		controlPanel.add(button_14);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlShadow);
		panel.setBounds(0, 352, 189, 62);
		controlPanel.add(panel);
		
		JLabel label = new JLabel("\u8D85\u7EA7\u7BA1\u7406\u5458");
		panel.add(label);
		return controlPanel;
	}

	public JPanel getBorrowRecord() //借阅记录
	{
		JPanel borrowRecord = new JPanel();
		borrowRecord.setBackground(SystemColor.inactiveCaption);
		borrowRecord.setBounds(0, 0, 706, 473);
		borrowRecord.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("借阅记录");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 30));
		lblNewLabel_2.setBounds(72, 123, 554, 127);
		borrowRecord.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("\u5171");
		lblNewLabel_5.setBounds(25, 14, 72, 18);
		borrowRecord.add(lblNewLabel_5);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(46, 10, 72, 24);
		borrowRecord.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel label = new JLabel("\u6761\u8BB0\u5F55");
		label.setBounds(120, 14, 72, 18);
		borrowRecord.add(label);
		
		JButton btnNewButton_8 = new JButton("\u5386\u53F2\u8BB0\u5F55");
		btnNewButton_8.setBounds(188, 10, 93, 27);
		borrowRecord.add(btnNewButton_8);
		
		JButton button = new JButton("\u8D85\u65F6\u672A\u8FD8");
		button.setBounds(295, 10, 93, 27);
		borrowRecord.add(button);
		
		JButton button_1 = new JButton("\u8BFB\u8005\u50AC\u8FD8");
		button_1.setBounds(402, 10, 93, 27);
		borrowRecord.add(button_1);
		return borrowRecord;
	}

	public JPanel getSearchUserPanel() { //查找用户面板
		JPanel searchUserPanel = new JPanel();
		searchUserPanel.setBackground(SystemColor.activeCaption);
		searchUserPanel.setBounds(0, 0, 706, 473);
		searchUserPanel.setLayout(null);
		
		 String[] columnNames = new String[] { "id", "name", "hp", "damage" };
	    // 表格中的内容，是一个二维数组
	     String[][] heros = new String[][] { { "1", "盖伦", "616", "100" },
	            								{ "2", "提莫", "512", "102" }, 
	            								{ "3", "奎因", "832", "200" } };
	    JTable table = new JTable(heros, columnNames);
	    table.setFont(new Font("宋体", Font.PLAIN, 20));
	       
		table.setBackground(SystemColor.control);
		table.setBounds(0, 44, 706, 376);
		searchUserPanel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 105, 692, 368);
		searchUserPanel.add(scrollPane);
		
		JRadioButton radioButton_0 = new JRadioButton("\u7528\u6237\u540D");
		radioButton_0.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton_0.setSelected(true);
		radioButton_0.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchType = 0;//按书名搜索
			}
		
		});
		radioButton_0.setBounds(184, 54, 89, 27);
		searchUserPanel.add(radioButton_0);
		ButtonGroup grp=new ButtonGroup();
		
		JRadioButton radioButton_1 = new JRadioButton("\u5B66\u53F7");
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton_1.setBounds(285, 55, 89, 25);
		radioButton_1.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchType = 1;//按类型搜索
			}
			
		});
		searchUserPanel.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("\u5168\u90E8");
		radioButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton_2.setBounds(391, 55, 98, 25);
		searchUserPanel.add(radioButton_2);
		radioButton_2.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchType = 2;//按书名搜索
			}
		
		});
		grp.add(radioButton_1);
		grp.add(radioButton_0);
		grp.add(radioButton_2);
		
		JTextField textField_12 = new JTextField();
		textField_12.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_12.setBounds(184, 15, 180, 29);
		searchUserPanel.add(textField_12);
		
		JButton btnNewButton_5 = new JButton("\u67E5\u8BE2");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_5.setBounds(381, 16, 113, 27);
		searchUserPanel.add(btnNewButton_5);
		
		JLabel lblNewLabel_2 = new JLabel("\u67E5\u627E\u7528\u6237");
		lblNewLabel_2.setForeground(UIManager.getColor("ProgressBar.selectionBackground"));
		lblNewLabel_2.setFont(new Font("黑体", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(14, 29, 122, 32);
		searchUserPanel.add(lblNewLabel_2);
		
		return searchUserPanel;
	}

	public JPanel getMyInfoPanel(Admin admin) //借阅记录
	{
		JPanel myInfo= new JPanel();
		myInfo.setBackground(SystemColor.inactiveCaption);
		myInfo.setBounds(0, 0, 706, 473);
		myInfo.setLayout(null);
		
		JTextArea myInfoText = new JTextArea();
		myInfoText.setWrapStyleWord(true);
		myInfoText.setForeground(new Color(0, 0, 255));
		myInfoText.setBackground(SystemColor.inactiveCaption);
		myInfoText.setText(admin.toString());
		myInfoText.setEditable(false);
		myInfoText.setFont(new Font("华文新魏", Font.PLAIN, 40));
		myInfoText.setBounds(150, 13, 441, 400);
		myInfo.add(myInfoText);
		
		JScrollPane scrollPane = new JScrollPane(myInfoText);
		scrollPane.setBounds(150, 13, 441, 400);
		myInfo.add(scrollPane);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "首页");
			}
		});
		btnNewButton.setBounds(326, 426, 113, 27);
		myInfo.add(btnNewButton);
		return myInfo;
	}

	public JPanel getBookSearch() //图书检索
	{
		JPanel booksearch= new JPanel();
		booksearch.setBackground(SystemColor.inactiveCaption);
		booksearch.setBounds(0, 0, 706, 473);
		booksearch.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_3.setBounds(28, 13, 258, 35);
		booksearch.add(textField_3);
		textField_3.setColumns(10);
		
		JRadioButton jr1 = new JRadioButton("\u4E66\u540D");
		jr1.setSelected(true);
		jr1.setBounds(28, 57, 59, 27);
		booksearch.add(jr1);
		
		JRadioButton jr2 = new JRadioButton("\u7C7B\u578B");
		jr2.setBounds(93, 57, 66, 27);
		booksearch.add(jr2);
		
		JRadioButton jr3 = new JRadioButton("\u51FA\u7248\u793E");
		jr3.setBounds(165, 57, 75, 27);
		booksearch.add(jr3);
		
		JRadioButton jr4 = new JRadioButton("\u4F5C\u8005");
		jr4.setBounds(246, 57, 66, 27);
		ButtonGroup bt = new ButtonGroup();
		
		JRadioButton jr5 = new JRadioButton("\u6761\u7801");
		jr5.setBounds(322, 57, 66, 27);
		booksearch.add(jr5);
		bt.add(jr1);
		bt.add(jr2);
		bt.add(jr3);
		bt.add(jr4);
		bt.add(jr5);
		
		JButton btnNewButton_6 = new JButton("\u68C0\u7D22");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jr1.isSelected())
				{
					System.out.println("按书名搜索");
				}
				else if(jr2.isSelected())
				{
					System.out.println("按类型搜索");
				}
				else if(jr3.isSelected())
				{
					System.out.println("按出版社搜索");
				}
				else if(jr4.isSelected())
				{
					System.out.println("按作者搜索");
				}
				else
				{
					System.out.println("按二级条码");
				}
			}
		});
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_6.setBounds(312, 13, 82, 35);
		booksearch.add(btnNewButton_6);
		booksearch.add(jr4);
		
		JLabel lblNewLabel_3 = new JLabel("\u9986\u85CF\u56FE\u4E66");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(466, 18, 82, 27);
		booksearch.add(lblNewLabel_3);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(551, 20, 86, 24);
		booksearch.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel label = new JLabel("\u672C");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(643, 18, 49, 27);
		booksearch.add(label);
		
		JButton btnNewButton_7 = new JButton("\u70ED\u641C\u699C");
		btnNewButton_7.setBounds(438, 57, 77, 27);
		booksearch.add(btnNewButton_7);
		
		JButton button = new JButton("\u8001\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String damage =JOptionPane.showInputDialog("请输入要显示的最小时间（年)");
				if(damage==null) return;//表示用户没有输入，不进行下一步
			}
		});
		button.setBounds(612, 57, 78, 27);
		booksearch.add(button);
		
		JButton button_1 = new JButton("\u635F\u574F\u5EA6");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String damage =JOptionPane.showInputDialog("请输入要显示的最小损坏度值");
				if(damage==null) return;//表示用户没有输入，不进行下一步
				
			}
		});
		button_1.setBounds(524, 57, 78, 27);
		booksearch.add(button_1);
		
		JLabel lblNewLabel_4 = new JLabel("\u5373\u4E8C\u7EA7\u6761\u7801");
		lblNewLabel_4.setBounds(322, 80, 75, 50);
		booksearch.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBounds(432, 13, 264, 75);
		booksearch.add(panel);
		return booksearch;
	}

	public JPanel getFeedbackPanel() //反馈模块
	{
		JPanel feedbackPanel= new JPanel();
		feedbackPanel.setBackground(SystemColor.inactiveCaption);
		feedbackPanel.setBounds(0, 0, 706, 473);
		feedbackPanel.setLayout(null);	
		
		JButton btnNewButton_9 = new JButton("New button");
		btnNewButton_9.setBounds(200, 66, 113, 27);
		feedbackPanel.add(btnNewButton_9);

		return feedbackPanel;
	}
}
