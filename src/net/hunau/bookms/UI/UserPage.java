package net.hunau.bookms.UI;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.hunau.bookms.bean.Admin;
import net.hunau.bookms.bean.Book;
import net.hunau.bookms.bean.LoanInfos;
import net.hunau.bookms.bean.SearchBook;
import net.hunau.bookms.bean.SecondFindBook;
import net.hunau.bookms.bean.User;
import net.hunau.bookms.dao.BookDAO;
import net.hunau.bookms.dao.LoanInfosDAO;
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
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JTable;

public class UserPage extends JFrame {

	private JPanel contentPane;
	User user;
	private JTextField searchText;
	Timer timer;
	JLabel labelDate;
	JTextField textWelcome;
	int index;
	JButton buttons;
	private JTextField nowUser;
	List<Book> searchBookResult;
	final CardLayout card;
	final JPanel panel_1;
	private int searchType=2;//��������,Ĭ��Ϊ����ʾȫ���鼮
	private int temp;//��ʱ����searchType
	private String searchParam="";//��������
	private JTable table;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDAO ud=new UserDAO();
					User user=ud.getUser("peter", "123456");
					UserPage frame = new UserPage(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public UserPage(final User user) throws IOException {
		setResizable(false);
		setTitle("\u7528\u6237\u754C\u9762");
		this.user=user;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel headPanel = getHeadPanel();//��ʼ��head���
		JPanel controlPanel = getControlPanel();//��ʼ��������
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(176, 80, 706, 473);
		card=new CardLayout();
		panel_1.setLayout(card);
		JPanel homePagePanel=getHomePagePanel();//��ҳ
		JPanel borrowRecordPanel=getBorrowRecord();//���ļ�¼ģ��
		JPanel rechargePanel = getRechargePanel(user);//��ֵģ��
		JPanel feedbackPanel = getFeedback();//����ģ��
		JPanel myInfoPanel = getMyInfoPanel(user);//�ҵ���Ϣ
		panel_1.add(homePagePanel,"��ҳ");
		panel_1.add(borrowRecordPanel,"���ļ�¼");
		panel_1.add(rechargePanel,"��ֵģ��");
		panel_1.add(feedbackPanel,"����ģ��");
		panel_1.add(myInfoPanel,"�ҵ���Ϣ");
		card.show(panel_1, "��ҳ");
		contentPane.add(panel_1);
		
		
		
		timer=new Timer();	//���ö�ʱ�����ý���������ֺ�ʱ��
		timer.schedule(new TimerTask(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd�� HH��mm��ss��");		
			public void run() {	
				
				index++;
				String text="��ӭ����ũ��ͼ���      ";
                if (index >= text.length()) {
                    index = 0;
                }
                //������Ҫ��ʾ���ַ�
                String str = text.substring(index, text.length()) + text.substring(0, index);
				labelDate.setText(sdf.format(new Date()));
				textWelcome.setText(str);
			}
			 
		 }, 0,1000);
	
		
	}
	JTable borrowTable=null;
	JScrollPane borrowjs;
	boolean op = false;
	
	public JPanel getBorrowRecord() throws IOException //���ļ�¼
	{
		LoanInfosDAO loan = new LoanInfosDAO();
		List<LoanInfos> list = loan.getAllBorrow(user.getUserNo());
		JPanel borrowRecord = new JPanel();
		borrowRecord.setBackground(SystemColor.inactiveCaption);
		borrowRecord.setBounds(0, 0, 706, 473);
		borrowRecord.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u60A8\u5171\u501F\u9605\u4E86\uFF1A");
		lblNewLabel_1.setBounds(27, 13, 90, 26);
		borrowRecord.add(lblNewLabel_1);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(114, 14, 69, 24);
		textField_12.setText(""+list.size());
		textField_12.setEditable(false);
		borrowRecord.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel label = new JLabel("\u672C\u4E66\uFF1A");
		label.setBounds(184, 13, 90, 26);
		borrowRecord.add(label);
		
		JLabel label_1 = new JLabel("\u8FDD\u7EA6\u6B21\u6570\uFF1A");
		label_1.setBounds(240, 13, 90, 26);
		borrowRecord.add(label_1);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setColumns(10);
		textField_13.setBounds(316, 14, 69, 24);
		int count = 0;
		for(LoanInfos ls:list) {
			if(ls.getOvertime()>0)
				count++;
		}
		textField_13.setText(count+"");
		textField_13.setEditable(false);
		borrowRecord.add(textField_13);
		
		JButton btnNewButton_6 = new JButton();
		if(!op)
			btnNewButton_6.setText("������");
		else
			btnNewButton_6.setText("����");
		
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op = !op;
				try {
					List<LoanInfos> list = loan.getNOReturn(user.getUserNo());
					borrowTable = MytableUtils.getBorrowTable(list);
					if(op)
					borrowjs.add(borrowTable);
					try {
						panel_1.add(getBorrowRecord(),"���ļ�¼");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					card.show(panel_1, "���ļ�¼");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(553, 13, 93, 27);
		borrowRecord.add(btnNewButton_6);
		
		JLabel label_3 = new JLabel("\u8BDA\u4FE1\u5EA6\uFF1A");
		label_3.setBounds(399, 13, 90, 26);
		borrowRecord.add(label_3);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setColumns(10);
		textField_14.setBounds(455, 14, 69, 24);
		//textField_14.setText(((list.size()-count)/list.size())*100+"");
		textField_14.setEditable(false);
		borrowRecord.add(textField_14);
		
		borrowjs=new JScrollPane();
		borrowjs.setBounds(0, 72, 706, 401);
		//��Jtable
		if(!op)
		borrowTable = MytableUtils.getBorrowTable(list);
		borrowjs.setViewportView(borrowTable);
		borrowRecord.add(borrowjs);
		return borrowRecord;
	}
	
	
	public JPanel getHeadPanel() //���涥�����
	{
		JPanel headPanel = new JPanel();
		headPanel.setBounds(-49, -11, 882, 81);
		headPanel.setLayout(null);
		contentPane.add(headPanel);
		JLabel lblNewLabel = new JLabel("\u6E58\u519C\u56FE\u4E66");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("����", Font.PLAIN, 20));
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\Workspace\\BookMS\\image\\icon.png"));
		lblNewLabel.setBounds(57, 12, 147, 60);
		headPanel.add(lblNewLabel);
		
		String []bookType={"ȫ������","�̸��̲�","ɢ�����","��ѧʷ","��������","�ƻ�С˵","�ഺС˵","�汾","����","����ѧ","����ѧ","֤ȯ","����"};
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
				card.show(panel_1, "��ҳ");
				BookDAO bd=new BookDAO();

				List<Book> books;
				try {
					if(searchType==0) //����������
					{
						books = bd.getBookInfoByBookName(searchText.getText().trim());
					}
					else if(searchType==1)	//���鼮��������
					{
						books = bd.getBookByBookTypeName(searchText.getText().trim());
					}
					else //����ȫ���鼮
					{
						books = bd.getBookInfoAll();
					}
					for(Book book:books)
					{
						System.out.println(book+"\n");
						//��ȡ�����ҽ���󷵻���table����
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(510, 12, 62, 24);
		headPanel.add(btnNewButton);
		
		labelDate = new JLabel("2019\u5E7405\u670820\u65E5 12\u70B953\u520606\u79D2");
		labelDate.setForeground(Color.RED);
		labelDate.setFont(new Font("����", Font.BOLD, 20));
		labelDate.setBounds(578, 50, 304, 22);
		headPanel.add(labelDate);
		
		textWelcome = new JTextField();
		textWelcome.setEditable(false);
		textWelcome.setFont(new Font("����", Font.PLAIN, 20));
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
		radioButton_1.setBounds(341, 43, 89, 25);
		radioButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchType = 1;//����������
			}
			
		});
		headPanel.add(radioButton_1);
		
		JRadioButton radioButton_0 = new JRadioButton("\u4E66\u7C4D\u540D\u79F0");
		radioButton_0.setSelected(true);
		radioButton_0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchType = 0;//����������
			}
		
		});
		radioButton_0.setBounds(246, 43, 89, 27);
		headPanel.add(radioButton_0);
		ButtonGroup grp=new ButtonGroup();
		
		JRadioButton radioButton_2 = new JRadioButton("\u5168\u90E8\u4E66\u7C4D");
		radioButton_2.setBounds(436, 43, 98, 25);
		headPanel.add(radioButton_2);
		radioButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				searchType = 2;//����������
			}
		
		});
		grp.add(radioButton_1);
		grp.add(radioButton_0);
		grp.add(radioButton_2);
		

		return headPanel;
	}
	
	public JPanel getControlPanel()	//�������
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
		nowUser.setFont(new Font("����", Font.PLAIN, 20));
		nowUser.setEditable(false);
		nowUser.setBackground(Color.YELLOW);
		nowUser.setForeground(Color.BLUE);
		nowUser.setHorizontalAlignment(SwingConstants.CENTER);
		nowUser.setBounds(14, 47, 137, 27);
		controlPanel.add(nowUser);
		nowUser.setColumns(10);
		nowUser.setText(user.getTruename());
		
		JButton btnNewButton_1 = new JButton("\u6211\u7684\u4FE1\u606F");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.add(getMyInfoPanel(user),"�ҵ���Ϣ");
				card.show(panel_1, "�ҵ���Ϣ");
				//System.out.println(user);
			}
		});
		btnNewButton_1.setBounds(27, 87, 113, 27);
		controlPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u4FE1\u606F\u4FEE\u6539");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdateMyInfo(user).setVisible(true);
			}
		});
		btnNewButton_2.setBounds(27, 122, 113, 27);
		controlPanel.add(btnNewButton_2);
		
		JButton button = new JButton("\u6211\u8981\u501F\u4E66");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barcode=JOptionPane.showInputDialog("��������Ҫ�������");
				if(barcode==null) return;//�û�û������ֵ���򷵹�
				BookDAO bd=new BookDAO();
				Book book;
				try {
					book = bd.getBookInfoByBarcode(barcode);
					if(book==null)
					{
						JOptionPane.showMessageDialog(UserPage.this, "��Ҫ����鼮������");return;
					}
					else
					{	//�ж��鼮�Ƿ��ڿ�
						if(book.getLend()==1)	//1��ʾ�ѽ��
						{
							JOptionPane.showMessageDialog(UserPage.this, "�����ѽ����");return;
						}
						else	//û�н���Ļ������Ƿ���У����ʿ��У����ʿ�������С���鼮�۸�������顣
						{

							if(user.getBalance()<book.getPrice())
							{
								JOptionPane.showMessageDialog(UserPage.this, "����Ѻ���㣬���ֵ!");return;
							}
/***********************************�����Ʋ���������Ҫ�Ƕ�loaninfo����в���start*********************************************************************/
							LoanInfosDAO ldao = new LoanInfosDAO();
						    List<LoanInfos> borrowlist = ldao.getNOReturn(user.getUserNo());
						    int sum = 0;
						    for(LoanInfos loan:borrowlist) {
						    	sum+=bd.getBookInfoByBarcode(loan.getBarcode()).getPrice();
						    }
						    sum+=book.getPrice();
						    if(sum>user.getBalance()) {
						    	JOptionPane.showMessageDialog(UserPage.this, "��δ�����鼮��Ԥ���鼮�ܽ���������Ѻ�����ֵ!");return;
						    }
						    /*******************�������鼮����*********************************/
							book.setLend(1);
							bd.updateBook(book);
							LoanInfos loan = new LoanInfos();
							loan.setBarcode(book.getBarcode());
							
							loan.setBooknmae(book.getBookName());
							loan.setUserNo(user.getUserNo());
							loan.setTruename(user.getTruename());
							ldao.borrowOneBookSuccess(loan);
							JOptionPane.showMessageDialog(UserPage.this, "����ɹ���");
/***********************************�����Ʋ���������Ҫ�Ƕ�loaninfo����в���end*********************************************************************/
							
						}
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		button.setBounds(27, 155, 113, 27);
		controlPanel.add(button);
		
		JButton button_1 = new JButton("\u6211\u8981\u8FD8\u4E66");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String barcode=JOptionPane.showInputDialog("��������Ҫ���������룡");
				if(barcode==null) return;//�û�û������ֵ���򷵹�
				BookDAO bd=new BookDAO();
				Book book;
				try {
					book = bd.getBookInfoByBarcode(barcode);
					if(book==null)
					{
						JOptionPane.showMessageDialog(UserPage.this, "��Ҫ�����鼮�����ڣ�");
						return;
					}
					else
					{	//�ж��鼮�Ƿ��ڿ�
						if(book.getLend()==1)	//1��ʾ�ѽ��
						{
							book.setLend(0);//���ڽ����黹
							bd.updateBook(book);
							/*********************��loan��¼���в���**********************************/
							LoanInfosDAO lo = new LoanInfosDAO();
							UserDAO ud = new UserDAO();
							LoanInfos ls = new LoanInfos();
							User tempUser = new User();
							
							
							ls.setBarcode(book.getBarcode());
							ls.setUserNo(user.getUserNo());
							ls = lo.getBorrow(ls);
							
							System.out.println(book.getPrice());
							if(ls.getOvertime()<=0) {
								lo.updateReturnBook(ls);
							}else {
								Float fine = ls.getFine();
								float price = book.getPrice();
								//���ĵ�Ԥ�Ʒ�������鼮���
								if(fine > price) {
									tempUser.setBalance(user.getBalance() - price);
									tempUser.setUserNo(user.getUserNo());
									ud.updateUserBalance(tempUser);
									
									ls.setFine(price);
									lo.updateReturnBook(ls);
								}
								else {
									tempUser.setBalance(user.getBalance() - fine);
									tempUser.setUserNo(user.getUserNo());
									ud.updateUserBalance(tempUser);
									
									ls.setFine(fine);
									lo.updateReturnBook(ls);
								}
							}
							/*************************************************************/
							JOptionPane.showMessageDialog(UserPage.this, "����ɹ���");
						}
						else	//�Ѿ����ˣ����ظ����鲻���κ����ݿ����
						{
							JOptionPane.showMessageDialog(UserPage.this, "�����Ѿ��黹��");
						}
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(27, 186, 113, 27);
		controlPanel.add(button_1);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u7528\u6237");
		label_1.setFont(new Font("����", Font.PLAIN, 20));
		label_1.setForeground(Color.RED);
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(41, 13, 98, 21);
		controlPanel.add(label_1);
		
		JButton button_2 = new JButton("\u5145\u503C");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.add(getRechargePanel(user),"��ֵģ��");
				card.show(panel_1, "��ֵģ��");
			}
		});
		button_2.setBounds(27, 250, 113, 27);
		controlPanel.add(button_2);
		
		JButton btnNewButton_3 = new JButton("\u501F\u9605\u8BB0\u5F55");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//buttons=(JButtion)e.getSource();
				try {
					panel_1.add(getBorrowRecord(),"���ļ�¼");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				card.show(panel_1, "���ļ�¼");
			}
		});
		btnNewButton_3.setBounds(27, 218, 113, 27);
		controlPanel.add(btnNewButton_3);
		
		JButton button_3 = new JButton("\u9996\u9875");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "��ҳ");
			}
		});
		button_3.setBounds(27, 281, 113, 27);
		controlPanel.add(button_3);
		
		JButton btnNewButton_4 = new JButton("\u66F4\u6362\u8D26\u53F7");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(27, 345, 113, 27);
		controlPanel.add(btnNewButton_4);
		
		JButton button_4 = new JButton("\u53CD\u9988");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_1.add(getFeedback(),"����ģ��");
				card.show(panel_1, "����ģ��");
			}
		});
		button_4.setBounds(27, 314, 113, 27);
		controlPanel.add(button_4);
		return controlPanel;
	}
	
	public JPanel getRechargePanel(User user) //��������ڳ�ֵ
	{
		JPanel rechargePanel = new JPanel();
		rechargePanel.setBackground(SystemColor.activeCaption);
		rechargePanel.setBounds(0, 0, 706, 473);
		rechargePanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("������κ", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(121, 86, 149, 30);
		rechargePanel.add(lblNewLabel_2);
		
		JLabel label = new JLabel("\u5B66    \u53F7\uFF1A");
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("������κ", Font.PLAIN, 35));
		label.setBounds(121, 149, 149, 30);
		rechargePanel.add(label);
		
		JLabel label_1 = new JLabel("\u624B\u673A\u53F7\uFF1A");
		label_1.setForeground(SystemColor.textHighlight);
		label_1.setFont(new Font("������κ", Font.PLAIN, 35));
		label_1.setBounds(121, 208, 149, 30);
		rechargePanel.add(label_1);
		
		JLabel label_2 = new JLabel("\u91D1    \u989D\uFF1A");
		label_2.setForeground(SystemColor.textHighlight);
		label_2.setFont(new Font("������κ", Font.PLAIN, 35));
		label_2.setBounds(121, 267, 149, 30);
		rechargePanel.add(label_2);
		
		JTextField usernameText = new JTextField();
		usernameText.setForeground(Color.GREEN);
		usernameText.setFont(new Font("����", Font.PLAIN, 20));
		usernameText.setEditable(false);
		usernameText.setBounds(261, 86, 149, 32);
		rechargePanel.add(usernameText);
		usernameText.setColumns(10);
		
		JTextField userNoText = new JTextField();
		userNoText.setForeground(Color.GREEN);
		userNoText.setFont(new Font("����", Font.PLAIN, 20));
		userNoText.setEditable(false);
		userNoText.setColumns(10);
		userNoText.setBounds(261, 149, 149, 32);
		rechargePanel.add(userNoText);
		
		JTextField telephoneText = new JTextField();
		telephoneText.setForeground(new Color(0, 255, 0));
		telephoneText.setFont(new Font("����", Font.PLAIN, 20));
		telephoneText.setColumns(10);
		telephoneText.setBounds(261, 206, 149, 32);
		rechargePanel.add(telephoneText);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(10)));
		spinner.setFont(new Font("����", Font.PLAIN, 20));
		spinner.setBounds(261, 267, 149, 32);
		rechargePanel.add(spinner);
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u4F59\u989D");
		label_3.setForeground(SystemColor.textHighlight);
		label_3.setFont(new Font("������κ", Font.PLAIN, 35));
		label_3.setBounds(508, 149, 149, 30);
		rechargePanel.add(label_3);
		
		JTextField C_balance = new JTextField();
		C_balance.setForeground(SystemColor.activeCaption);
		C_balance.setFont(new Font("����", Font.PLAIN, 20));
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
		
		C_balance.setText(user.getBalance()+"");	//��ʾ��ǰ���
		usernameText.setText(user.getUsername());	//��ʾ�û���
		userNoText.setText(user.getUserNo());		//��ʾѧ��
		telephoneText.setText(user.getTelePhone());	//��ʾ�ֻ�
		JButton btnNewButton_5 = new JButton("\u786E\u8BA4");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float balance=Float.parseFloat(C_balance.getText().trim());
				user.setBalance((int)spinner.getValue()+balance);
				C_balance.setText(user.getBalance()+"");
				UserDAO ud =new UserDAO();
				ud.updateUser(user);//�����û��鼮
				JOptionPane.showMessageDialog(UserPage.this, "��ֵ�ɹ���");
			}
		});
		btnNewButton_5.setForeground(SystemColor.textHighlight);
		btnNewButton_5.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_5.setBounds(213, 366, 113, 27);
		rechargePanel.add(btnNewButton_5);
		
		return rechargePanel;
	}	
	
	
	public JPanel getHomePagePanel() throws IOException//����һ��������壬��Ӧͼ������ģ��
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
		Search.setBackground(SystemColor.activeCaptionBorder);
		Search.setBounds(0, 0, 692, 473);
		contentPane.add(Search);
		Search.setLayout(null);
		
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
		textField.setBounds(133, 10, 86, 24);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setText("\u7545\u9500\u56FE\u4E66");
		Search.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(233, 10, 86, 24);
		textField_1.setText("\u70ED\u5356\u65B0\u4E66");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		Search.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(333, 10, 86, 24);
		textField_2.setText("\u4E94\u661F\u56FE\u4E66");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		Search.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(433, 10, 86, 24);
		textField_3.setText("\u7AE5\u4E66\u699C");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		Search.add(textField_3);
		
		JLabel label_3 = new JLabel("\u66F4\u591A\u300B");
		label_3.setBounds(545, 13, 72, 18);
		Search.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(133, 43, 86, 24);
		textField_4.setText("\u70ED\u95E8\u5C0F\u8BF4");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		Search.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(233, 43, 86, 24);
		textField_5.setText("\u6559\u8F85\u6559\u6750");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		Search.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(333, 43, 86, 24);
		textField_6.setText("\u9752\u6625\u6587\u5B66");
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		Search.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(433, 43, 86, 24);
		textField_7.setText("\u6210\u529F\u52B1\u5FD7");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		Search.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setBounds(133, 74, 86, 24);
		textField_8.setText("\u4EBA\u7269\u4F20\u8BB0");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		Search.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setBounds(233, 74, 86, 24);
		textField_9.setText("\u52A8\u6F2B\u5E7D\u9ED8");
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setEditable(false);
		textField_9.setColumns(10);
		Search.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setBounds(333, 74, 86, 24);
		textField_10.setText("\u82F1\u6587\u539F\u7248");
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		Search.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(433, 74, 86, 24);
		textField_11.setText("\u793E\u4F1A\u5FC3\u7406\u5B66");
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setEditable(false);
		textField_11.setColumns(10);
		Search.add(textField_11);
		
		JLabel label_4 = new JLabel("\u66F4\u591A\u300B");
		label_4.setBounds(545, 46, 72, 18);
		Search.add(label_4);
		
		JLabel label_5 = new JLabel("\u66F4\u591A\u300B");
		label_5.setBounds(545, 77, 72, 18);
		Search.add(label_5);
		
		JLabel label_6 = new JLabel("\u67E5\u8BE2\u7ED3\u679C");
		label_6.setBounds(285, 113, 72, 18);
		label_6.setFont(new Font("΢���ź�", Font.BOLD, 15));
		Search.add(label_6);
		
		
		table = getSeachTableByBookName(searchParam,searchType);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane js=new JScrollPane();
		js.setBounds(0, 142, 706, 331);
		js.setViewportView(table);
		Search.add(js);
		return contentPane;
	}


	public JPanel getFeedback() //���ļ�¼
	{
		JPanel borrowRecord = new JPanel();
		borrowRecord.setBackground(SystemColor.inactiveCaption);
		borrowRecord.setBounds(0, 0, 706, 473);
		borrowRecord.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("����ģ�����ڿ����С���");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 30));
		lblNewLabel_2.setBounds(138, 378, 554, 82);
		borrowRecord.add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 240, 245));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(50, 51, 592, 264);
		borrowRecord.add(textArea);
		
		JButton btnNewButton_7 = new JButton("\u63D0\u4EA4");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_7.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton_7.setBounds(287, 328, 113, 37);
		borrowRecord.add(btnNewButton_7);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(50, 51, 592, 264);
		borrowRecord.add(scrollPane);
		return borrowRecord;
	}


	public JPanel getMyInfoPanel(User user) //�ҵ���Ϣ
	{
		JPanel myInfo= new JPanel();
		myInfo.setBackground(SystemColor.inactiveCaption);
		myInfo.setBounds(0, 0, 706, 473);
		myInfo.setLayout(null);
		
		JTextArea myInfoText = new JTextArea();
		myInfoText.setWrapStyleWord(true);
		myInfoText.setForeground(new Color(0, 0, 255));
		myInfoText.setBackground(SystemColor.inactiveCaption);
		myInfoText.setText(user.toString());
		myInfoText.setEditable(false);
		myInfoText.setFont(new Font("������κ", Font.PLAIN, 40));
		myInfoText.setBounds(150, 13, 441, 400);
		myInfo.add(myInfoText);
		
		JScrollPane scrollPane = new JScrollPane(myInfoText);
		scrollPane.setBounds(150, 13, 441, 400);
		myInfo.add(scrollPane);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel_1, "��ҳ");
			}
		});
		btnNewButton.setBounds(326, 426, 113, 27);
		myInfo.add(btnNewButton);
		return myInfo;
	}
	/*
	 * @param:int:��������,string:�û�����Ĳ���
	 * @return JTable
	 * exception �����鼮����ģ����ѯ�鼮�õ��Ľ����װ��JTable��
	 */
	public JTable getSeachTableByBookName(String seachParam,int searchTpye) throws IOException {
		BookDAO bd = new BookDAO();
		List<SearchBook> list=null;
		if(searchType==3) {
			return getSeachTableByBookName(seachParam);
		}
		
		if(searchTpye==0) {
			 list =  bd.getBookByName(seachParam);
		}else if(searchTpye==1){
			 list =  bd.getBookByTypeName(seachParam);
		}
		else if(searchTpye==2){
			list =  bd.getAllBook();
		}
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(UserPage.this, "û���ҵ����");
			return table;
		}
		int row = list.size();
		
		String[] columnNames = new String[7];
		columnNames[0] = new String("�鼮���");
		columnNames[1] = new String("�鼮����");
		columnNames[2] = new String("����");
		columnNames[3] = new String("��ŵص�");
		columnNames[4] = new String("����");
		columnNames[5] = new String("���");
		columnNames[6] = new String("�鿴");
		Object[][] data = new Object[row][7];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < 7; j++) {
				switch(j) {
				case 0:
					data[i][j] = list.get(i).getTbarcode();
					break;
				case 1:
					data[i][j] = list.get(i).getBookName();
					break;
				case 2:
					data[i][j] =list.get(i).getAuthor();
					break;
				case 3:
					data[i][j] = list.get(i).getLocation();
					break;
				case 4:
					data[i][j] = list.get(i).getAmount();
					break;
				case 5:
					data[i][j] = list.get(i).getIntroduction();
					break;
				case 6:
					data[i][j] = "�鿴";
				}
			}
		}
		JTable table = new JTable(data,columnNames);
		table.setEnabled(false);
		table.setBackground(SystemColor.control);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent event) {//������굥��ʱ��Ӧ

	               //�õ�ѡ�е����е�����ֵ
	               int row = table.rowAtPoint(event.getPoint());
                   int col = table.columnAtPoint(event.getPoint());
                   if(col==6) {
                     String tbarcode= new String((String)table.getValueAt(row,0)); //����鼮����(tbarcode)
                     searchParam = tbarcode;
                     temp=searchType;
                     searchType = 3; //������������3
                     try {
     					panel_1.add(getHomePagePanel(),"����ģ��");
     				} catch (IOException e1) {
     					// TODO Auto-generated catch block
     					e1.printStackTrace();
     				}
     				card.show(panel_1, "����ģ��");
                   }
	               
	        }
			public void mousePressed(MouseEvent event) { //���±��
				int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if(col==6)
                table.setValueAt("<html><font color=\"red\">�鿴</font></html>", row, col);
			}
			public void mouseReleased(MouseEvent event) { //�ɿ����
				int row = table.rowAtPoint(event.getPoint());
                int col = table.columnAtPoint(event.getPoint());
                if(col==6)
                table.setValueAt("�鿴", row, col);
			}
		});
		return table;
	}
	public JTable getSeachTableByBookName(String seachParam) throws IOException {
		BookDAO bd = new BookDAO();
		List<SecondFindBook> list=null;
	    list =  bd.getSecondBook(seachParam);
		int row = list.size();
		
		String[] columnNames = new String[8];
		columnNames[0] = new String("�鼮����");
		columnNames[1] = new String("�鼮����");
		columnNames[2] = new String("����");
		columnNames[3] = new String("�۸�");
		columnNames[4] = new String("������");
		columnNames[5] = new String("��������");
		columnNames[6] = new String("�𻵳̶�");
		columnNames[7] = new String("�ڿ�");
		Object[][] data = new Object[row][8];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < 8; j++) {
				switch(j) {
				case 0:
					data[i][j] = list.get(i).getBarcode();
					break;
				case 1:
					data[i][j] = list.get(i).getBookName();
					break;
				case 2:
					data[i][j] =list.get(i).getAuthor();
					break;
				case 3:
					data[i][j] = list.get(i).getPrice();
					break;
				case 4:
					data[i][j] = list.get(i).getPublish();
					break;
				case 5:
					data[i][j] = list.get(i).getPulishDate();
					break;
				case 6:
					data[i][j] = list.get(i).getDamage()/10*100+"%";
					break;
				case 7:
					data[i][j] = list.get(i).getLend()==1?"��":"��";
					break;
				}
			}
		}
		JTable table = new JTable(data,columnNames);
		System.out.println(data[0][6]);
		table.setEnabled(false);
		table.setBackground(SystemColor.control);
		this.searchType=this.temp; //�ֶ����û���
		return table;	
	}
}