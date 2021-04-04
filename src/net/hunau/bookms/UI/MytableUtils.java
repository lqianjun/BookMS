package net.hunau.bookms.UI;

import java.io.IOException;
import java.util.List;

import javax.swing.JTable;

import net.hunau.bookms.bean.LoanInfos;

public class MytableUtils  {
	/*
	 * ������List<LoanInfos>
	 * �������ͣ�JTable
	 * ˵������list������ת��һ��table��ά��
	 */
		public static JTable getBorrowTable(List<LoanInfos> list) throws IOException {
			//System.out.println(MytableUtils.class);
			 int row = list.size();
				String[] columnNames = new String[9];
				columnNames[0] = new String("�鼮���");
				columnNames[1] = new String("�鼮����");
				columnNames[2] = new String("ѧ��");
				columnNames[3] = new String("����");
				columnNames[4] = new String("�������");
				columnNames[5] = new String("ʵ�ʹ黹����");
				columnNames[6] = new String("�黹����");
				columnNames[7] = new String("��������");
				columnNames[8] = new String("����");
				Object[][] data = new Object[row][9];
				for(int i = 0; i < row; i++) {
					for(int j = 0; j < 9; j++) {
						switch(j) {
						case 0:
							data[i][j] = list.get(i).getBarcode();
							break;
						case 1:
							data[i][j] = list.get(i).getBookname();
							break;
						case 2:
							data[i][j] = list.get(i).getUserNo();
							break;
						case 3:
							data[i][j] =list.get(i).getTruename();
							break;
						case 4:
							data[i][j] = list.get(i).getBorrowDate();
							break;
						case 5:
							data[i][j] = list.get(i).getReturnDate();
							break;
						case 6:
							data[i][j] = list.get(i).getDealine();
							break;
						case 7:
							data[i][j] = list.get(i).getOvertime()<=0?0:list.get(i).getOvertime();
							break;
						case 8:
							data[i][j] = list.get(i).getFine()<=0?0:list.get(i).getFine();
							break;
						}
					}
				}
			JTable table = new JTable(data,columnNames);
			table.setEnabled(false);
			return table;
		}
}
