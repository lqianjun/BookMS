package net.hunau.bookms.UI;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Book;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.dao.BookDAO;
import net.hunau.bookms.dao.UserDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;

public class Test extends JFrame {

	private JPanel contentPane;
	User user;
	private JTextField searchText;
	Timer timer;
	JLabel labelDate;
	JTextField textWelcome;
	JTextArea textArea;
	int index;
	JButton buttons;
	private JTextField nowUser;
	private JTextField publicText;
	private JTextField stockText;
	List<Book> searchBookResult;
	final CardLayout card;
	final JPanel panel_1;
	JTextField bookNameText;
	JTextField authorText;
	JTextField priceText;
	JButton btnNewButton;
	int searchType=0;
	private JTextField usernameText;
	private JTextField userNoText;
	private JTextField telephoneText;
	private JTextField balanceText;
	private JTable table;
	private JTextField textField_12;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDAO ud=new UserDAO();
					User user=ud.getUser("peter", "123456");
					System.out.println("UserPage里面的主方法调用了*****************************************************");
					System.out.println(user);
					UserPage frame = new UserPage(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Test(final User user) {
		setTitle("\u7528\u6237\u754C\u9762");
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel headPanel = getHeadPanel();//初始化head面板
		JPanel controlPanel = getControlPanel();//初始化操作区
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(176, 80, 706, 473);
		card=new CardLayout();
		panel_1.setLayout(card);
		//卡片式布局，点击相应按钮跳转到不同模块
		JPanel searchUserPanel=getSearchUserPanel();//一个搜索模块
		panel_1.add(searchUserPanel,"查找用户");
		card.show(panel_1, "查找用户");
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
		table.setBounds(24, 23, 706, 376);
		searchUserPanel.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 105, 706, 368);
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
		
		textField_12 = new JTextField();
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
	
	public JPanel getSearchPanel()//建立一个搜索面板，对应图书搜索模块
	{
		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		JTextField textField_2;
		JTextField textField_3;
		JTextField textField_4;
		JTextField textField_5;
		JTextField textField_6;
		JTextField textField_7;
		JTextField textField_8;
		JTextField textField_9;
		JTextField textField_10;
		JTextField textField_11;
		JLabel lblNewLabel;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JPanel Search = new JPanel();
		Search.setForeground(Color.WHITE);
		Search.setBackground(SystemColor.inactiveCaptionBorder);
		Search.setBounds(0, 0, 706, 473);
		Search.setLayout(null);
		contentPane.add(Search);
		
		JLabel label = new JLabel("\u641C\u7D22\u699C\u5355");
		label.setBounds(39, 13, 72, 18);
		Search.add(label);
		
		JLabel label_1 = new JLabel("\u70ED\u95E8\u63A8\u8350");
		label_1.setBounds(39, 46, 72, 18);
		Search.add(label_1);
		
		JLabel label_2 = new JLabel("\u8BFB\u8005\u4EBA\u7FA4");
		label_2.setBounds(39, 77, 72, 18);
		Search.add(label_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setText("\u7545\u9500\u56FE\u4E66");
		textField.setBounds(133, 10, 86, 24);
		Search.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("\u70ED\u5356\u65B0\u4E66");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(233, 10, 86, 24);
		Search.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("\u4E94\u661F\u56FE\u4E66");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(333, 10, 86, 24);
		Search.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("\u7AE5\u4E66\u699C");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(433, 10, 86, 24);
		Search.add(textField_3);
		
		JLabel label_3 = new JLabel("\u66F4\u591A\u300B");
		label_3.setBounds(545, 13, 72, 18);
		Search.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setText("\u70ED\u95E8\u5C0F\u8BF4");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(133, 43, 86, 24);
		Search.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("\u6559\u8F85\u6559\u6750");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(233, 43, 86, 24);
		Search.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setText("\u9752\u6625\u6587\u5B66");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(333, 43, 86, 24);
		Search.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setText("\u6210\u529F\u52B1\u5FD7");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(433, 43, 86, 24);
		Search.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("\u4EBA\u7269\u4F20\u8BB0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(133, 74, 86, 24);
		Search.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("\u52A8\u6F2B\u5E7D\u9ED8");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		textField_9.setBounds(233, 74, 86, 24);
		Search.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("\u82F1\u6587\u539F\u7248");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(333, 74, 86, 24);
		Search.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("\u793E\u4F1A\u5FC3\u7406\u5B66");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		textField_11.setBounds(433, 74, 86, 24);
		Search.add(textField_11);
		
		JLabel label_4 = new JLabel("\u66F4\u591A\u300B");
		label_4.setBounds(545, 46, 72, 18);
		Search.add(label_4);
		
		JLabel label_5 = new JLabel("\u66F4\u591A\u300B");
		label_5.setBounds(545, 77, 72, 18);
		Search.add(label_5);
		
		JLabel label_6 = new JLabel("\u67E5\u8BE2\u7ED3\u679C");
		label_6.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_6.setBounds(133, 111, 72, 18);
		Search.add(label_6);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 142, 348, 331);
		panel.setLayout(null);
		Search.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E66\u7C4D\u540D\u79F0");
		lblNewLabel_1.setBounds(26, 30, 72, 18);
		panel.add(lblNewLabel_1);
		
		bookNameText = new JTextField();
		bookNameText.setEditable(false);
		bookNameText.setBounds(95, 28, 198, 21);
		panel.add(bookNameText);
		bookNameText.setColumns(10);
		
		JLabel label_7 = new JLabel("\u4F5C\u8005");
		label_7.setBounds(26, 64, 72, 18);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("\u4EF7\u683C");
		label_8.setBounds(26, 97, 72, 18);
		panel.add(label_8);
		
		authorText = new JTextField();
		authorText.setEditable(false);
		authorText.setColumns(10);
		authorText.setBounds(95, 61, 198, 21);
		panel.add(authorText);
		
		priceText = new JTextField();
		priceText.setEditable(false);
		priceText.setColumns(10);
		priceText.setBounds(95, 95, 198, 21);
		panel.add(priceText);
		
		JButton button = new JButton("\u52A0\u8D2D\u7269\u8F66");
		button.setBounds(26, 207, 113, 27);
		panel.add(button);
		
		btnNewButton = new JButton("\u8BE6\u60C5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			textArea.setText(searchBookResult.toString());
			}
		});
		btnNewButton.setBounds(180, 207, 113, 27);
		panel.add(btnNewButton);
		
		JLabel label_10 = new JLabel("\u51FA\u7248\u793E");
		label_10.setBounds(26, 128, 72, 18);
		panel.add(label_10);
		
		JLabel label_11 = new JLabel("\u5E93\u5B58");
		label_11.setBounds(26, 159, 72, 18);
		panel.add(label_11);
		
		publicText = new JTextField();
		publicText.setEditable(false);
		publicText.setColumns(10);
		publicText.setBounds(95, 125, 198, 21);
		panel.add(publicText);
		
		stockText = new JTextField();
		stockText.setEditable(false);
		stockText.setColumns(10);
		stockText.setBounds(95, 159, 198, 21);
		panel.add(stockText);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setRows(10);
		textArea.setColumns(10);
		textArea.setBackground(SystemColor.window);
		textArea.setBounds(346, 142, 360, 331);
		Search.add(textArea);
		
		JLabel label_9 = new JLabel("\u8BE6\u7EC6\u4FE1\u606F");
		label_9.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_9.setBounds(503, 111, 72, 18);
		Search.add(label_9);
		
		JScrollPane scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setBounds(346, 142, 360, 331);
		Search.add(scrollPane_1);
		return contentPane;
	}
	
	public JPanel getHeadPanel()
	{
		JPanel headPanel = new JPanel();
		headPanel.setBounds(-49, -11, 882, 81);
		headPanel.setLayout(null);
		contentPane.add(headPanel);
		JLabel lblNewLabel = new JLabel("\u6E58\u519C\u56FE\u4E66");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Workspace\\BookMS\\image\\icon.png"));
		lblNewLabel.setBounds(57, 12, 147, 60);
		headPanel.add(lblNewLabel);
		
		String []bookType={"全部分类","教辅教材","散文随笔","文学史","世界名著","科幻小说","青春小说","绘本","漫画","管理学","经济学","证券","音乐"};
		//JComboBox<String> comboBox = new JComboBox<String>(bookType);
		JComboBox comboBox = new JComboBox();
		for(int i=0;i<bookType.length;i++)
		{
			comboBox.addItem(bookType[i]);
		}
		comboBox.setBounds(225, 12, 104, 22);
		headPanel.add(comboBox);
		
		searchText = new JTextField();
		searchText.setBounds(331, 12, 176, 24);
		headPanel.add(searchText);
		searchText.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "搜索模块");
				BookDAO bd=new BookDAO();

				textArea.setText("");
				List<Book> books;
				try {
					if(searchType==0) //按书名搜索
					{
						books = bd.getBookInfoByBookName(searchText.getText().trim());
					}
					else	//按书籍类型搜索
					{
						books = bd.getBookByBookTypeName(searchText.getText().trim());
					}
					for(Book book:books)
					{
						textArea.append(book.toString()+"\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(510, 12, 62, 24);
		headPanel.add(btnNewButton);
		
		labelDate = new JLabel("2018\u5E7406\u670820\u65E5 12\u70B953\u520606\u79D2");
		labelDate.setForeground(Color.RED);
		labelDate.setFont(new Font("宋体", Font.BOLD, 20));
		labelDate.setBounds(578, 50, 304, 22);
		headPanel.add(labelDate);
		
		textWelcome = new JTextField();
		textWelcome.setEditable(false);
		textWelcome.setFont(new Font("宋体", Font.PLAIN, 20));
		textWelcome.setForeground(Color.GREEN);
		textWelcome.setText("\u6B22\u8FCE\u5149\u4E34\u519C\u5927\u56FE\u4E66\u9986");
		textWelcome.setHorizontalAlignment(SwingConstants.TRAILING);
		textWelcome.setBounds(628, 11, 176, 24);
		headPanel.add(textWelcome);
		textWelcome.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setBounds(-35, 63, 246, 495);
		headPanel.add(label);
		
		JRadioButton radioButton_1 = new JRadioButton("\u4E66\u7C4D\u7C7B\u578B");
		radioButton_1.setBounds(388, 45, 119, 27);
		radioButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchType = 1;//按类型搜索
			}
			
		});
		headPanel.add(radioButton_1);
		
		JRadioButton radioButton_0 = new JRadioButton("\u4E66\u7C4D\u540D\u79F0");
		radioButton_0.setSelected(true);
		radioButton_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchType = 0;//按书名搜索
			}
		
		});
		radioButton_0.setBounds(263, 45, 119, 27);
		headPanel.add(radioButton_0);
		ButtonGroup grp=new ButtonGroup();
		grp.add(radioButton_1);
		grp.add(radioButton_0);
		return headPanel;
	}
	public JPanel getControlPanel()
	{
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setForeground(Color.RED);
		controlPanel.setBounds(0, 80, 176, 473);
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
		nowUser.setBounds(14, 47, 137, 27);
		controlPanel.add(nowUser);
		nowUser.setColumns(10);
		nowUser.setText(user.getUsername());
		
		JButton btnNewButton_1 = new JButton("\u6211\u7684\u4FE1\u606F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "搜索模块");
				textArea.setText(user.toString());
			}
		});
		btnNewButton_1.setBounds(27, 87, 113, 27);
		controlPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateUserInfo(user).setVisible(true);
			}
		});
		btnNewButton_2.setBounds(27, 133, 113, 27);
		controlPanel.add(btnNewButton_2);
		
		JButton button = new JButton("\u6211\u8981\u501F\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barcode=JOptionPane.showInputDialog("请输入你要借的条码");
				if(barcode==null) return;//用户没输入数值，则返归
				BookDAO bd=new BookDAO();
				try {
					if(bd.getBookInfoByBarcode(barcode)==null)
					{
						JOptionPane.showMessageDialog(Test.this, "你要借的书籍不存在");
						return;
					}
					else
					{
						JOptionPane.showMessageDialog(Test.this, "已查找到该书");
					}
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setBounds(27, 181, 113, 27);
		controlPanel.add(button);
		
		JButton button_1 = new JButton("\u6211\u8981\u8FD8\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "开发模块");
			}
		});
		button_1.setBounds(27, 224, 113, 27);
		controlPanel.add(button_1);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u7528\u6237");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setForeground(Color.RED);
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(41, 13, 98, 21);
		controlPanel.add(label_1);
		
		JButton button_2 = new JButton("\u5145\u503C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "开发模块");
			}
		});
		button_2.setBounds(27, 318, 113, 27);
		controlPanel.add(button_2);
		
		JButton btnNewButton_3 = new JButton("\u501F\u9605\u8BB0\u5F55");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buttons=(JButtion)e.getSource();
				card.show(panel_1, "开发模块");
			}
		});
		btnNewButton_3.setBounds(27, 270, 113, 27);
		controlPanel.add(btnNewButton_3);
		
		JButton button_3 = new JButton("\u9996\u9875");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "搜索模块");
			}
		});
		button_3.setBounds(27, 364, 113, 27);
		controlPanel.add(button_3);
		
		JButton btnNewButton_4 = new JButton("\u66F4\u6362\u8D26\u53F7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(27, 404, 113, 27);
		controlPanel.add(btnNewButton_4);
		return controlPanel;
	}
}