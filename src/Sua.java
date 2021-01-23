import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Sua extends JFrame{
	Vector vDatas=new Vector(), vTitles=new Vector();
	Vector vDatah=new Vector(), vTitleh=new Vector();
	Vector vDatan=new Vector(), vTitlen=new Vector();
	JTable tbsv ;
	JTable tbhs;
	JTable tbnh;
	DefaultTableModel modelsv;
	DefaultTableModel modelhs;
	DefaultTableModel modelnh;
	Statement statement ;
	Connection conn;
	ResultSet resultSet;
	Vector<String> masv = new Vector<String>();
	Vector<String> manh = new Vector<String>();
	Vector<String> tensv = new Vector<String>();
	Vector<String> tennh = new Vector<String>();
	public Sua(){
		JPanel contentPane;
		try {
			contentPane = new JPanelWithBackground("bluez.jpg");
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabbedPane, BorderLayout.CENTER);
			JPanel sinhVien = new JPanel();
			tabbedPane.addTab("Sinh viên", null, sinhVien, null);
			JPanel hoSo = new JPanel();
			tabbedPane.addTab("Hồ sơ", null, hoSo, null);
			JPanel nganHang = new JPanel();
			tabbedPane.addTab("Ngân hàng", null, nganHang, null);
			sinhVien.setLayout(new BorderLayout());
			hoSo.setLayout(new BorderLayout());
			nganHang.setLayout(new BorderLayout());
			JPanel pnback = new JPanel();
			pnback.setLayout(new BorderLayout());
			pnback.setBackground(new Color(64, 157, 250));
			JButton Back = new JButton("Quay lại", new ImageIcon("back.png"));
			pnback.add(Back, BorderLayout.WEST);
			contentPane.add(pnback, BorderLayout.NORTH);
			Back.setForeground(new Color(255, 255, 255));
			Back.setBackground(new Color(64, 157, 250));
			Back.setFocusable(false);
			Back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					new QLVVSV();
				}
			});
			
			JPanel sinhVienm = new JPanel();
			sinhVien.add(sinhVienm, BorderLayout.SOUTH);
			sinhVienm.setLayout(new FlowLayout());
			JButton sSua = new JButton("Sửa", new ImageIcon("edit.png"));
			sinhVienm.add(sSua);
			sSua.setForeground(new Color(255, 255, 255));
			sSua.setBackground(new Color(64, 157, 250));
			sSua.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//System.out.println(selectedRowsv);
					try {
						Vector st = (Vector) vDatas.elementAt(tbsv.getSelectedRow());	
						new ChinhSinhVien(st.get(0).toString(),st.get(1).toString(),st.get(2).toString(),st.get(3).toString(),st.get(4).toString(),st.get(5).toString(),st.get(6).toString(),Sua.this,1);	
					} catch (ArrayIndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "Không có gì để sửa");
					}	
				}
			});
			sSua.setFont(new Font("Tahoma", Font.BOLD, 21));
			JButton sXoa = new JButton("Xóa", new ImageIcon("delete.png"));
			sinhVienm.add(sXoa);
			sXoa.setForeground(new Color(255, 255, 255));
			sXoa.setBackground(new Color(64, 157, 250));
			sXoa.setFont(new Font("Tahoma", Font.BOLD, 21));
			sXoa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String []list = {"Có","Không"};
					int n = JOptionPane.showOptionDialog(null,"Bạn có muốn xóa","Messeger",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,list,0);
					if (n==0) {	
						try {
							Vector st = (Vector) vDatas.elementAt(tbsv.getSelectedRow());
							Statement statement = conn.createStatement();
							statement.executeUpdate("Delete from sinhvien where masv = '"+st.elementAt(0)+"'");
							vDatas.remove(tbsv.getSelectedRow());
							modelsv.fireTableDataChanged();
							statement.close();
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						} catch (ArrayIndexOutOfBoundsException e2) {
							JOptionPane.showMessageDialog(null, "Không có gì để xóa");	
						} catch (MySQLIntegrityConstraintViolationException e2) {
							JOptionPane.showMessageDialog(null, "Xóa hồ sơ chứa sinh viên trước");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Lỗi khi xóa");
						}
					}
					
				}
			});
			JButton sChen = new JButton("Chèn", new ImageIcon("insert.png"));
			sChen.setForeground(new Color(255, 255, 255));
			sChen.setBackground(new Color(64, 157, 250));
			sChen.setFont(new Font("Tahoma", Font.BOLD, 21));
			sinhVienm.add(sChen);
			sChen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new ChinhSinhVien("","","","","","","",Sua.this,2);
				}
			});
			JPanel sinhVienC = new JPanel();
			sinhVien.add(sinhVienC, BorderLayout.CENTER);
		
			JPanel hoSom = new JPanel();
			hoSo.add(hoSom, BorderLayout.SOUTH);
			hoSom.setLayout(new FlowLayout());
			JButton hSua = new JButton("Sửa", new ImageIcon("edit.png"));
			hoSom.add(hSua);
			hSua.setForeground(new Color(255, 255, 255));
			hSua.setBackground(new Color(64, 157, 250));
			hSua.setFont(new Font("Tahoma", Font.BOLD, 21));
			hSua.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						Vector st = (Vector) vDatah.elementAt(tbhs.getSelectedRow());
						new ChinhHoSo(st.get(3).toString(), st.get(4).toString(),st.get(0).toString(),st.get(1).toString(),st.get(2).toString(),Sua.this,1);
					} catch (ArrayIndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(null, "Không có gì để sửa");
					}
				}
			});
			JButton hXoa = new JButton("Xóa", new ImageIcon("delete.png"));
			hoSom.add(hXoa);
			hXoa.setForeground(new Color(255, 255, 255));
			hXoa.setBackground(new Color(64, 157, 250));
			hXoa.setFont(new Font("Tahoma", Font.BOLD, 21));
			hXoa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String []list = {"Có","Không"};
					
					int n = JOptionPane.showOptionDialog(null,"Bạn có muốn xóa","Messeger",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,list,0);
					if (n==0) {	
						try {
							Vector st = (Vector) vDatah.elementAt(tbhs.getSelectedRow());
							String smasv = masv.get(tensv.indexOf(st.elementAt(1)));
							String smanh = manh.get(tennh.indexOf(st.elementAt(2)));
							Statement statement = conn.createStatement();
							int check = statement.executeUpdate("Delete from hoso where masv= '"+smasv+"' and manh= '"+smanh+"'");
							vDatah.remove(tbhs.getSelectedRow());
							modelhs.fireTableDataChanged();
							statement.close();
							if (check == 1) {
								JOptionPane.showMessageDialog(null, "Xóa thành công!");
							} else {
								JOptionPane.showMessageDialog(null, "Xóa thất bại!");
							}
						} catch (ArrayIndexOutOfBoundsException e2) {
							JOptionPane.showMessageDialog(null, "Không có gì để xóa");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Lỗi khi xóa");
						}
							
					}
				}
			});
			JButton hChen = new JButton("Chèn", new ImageIcon("insert.png"));
			hChen.setForeground(new Color(255, 255, 255));
			hChen.setBackground(new Color(64, 157, 250));
			hChen.setFont(new Font("Tahoma", Font.BOLD, 21));
			hChen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new ChinhHoSo("","","","","",Sua.this,2);
				}
			});
			hoSom.add(hChen);
			JPanel hoSoC = new JPanel();
			hoSo.add(hoSoC, BorderLayout.CENTER);
			
			JPanel nganHangm = new JPanel();
			nganHang.add(nganHangm, BorderLayout.SOUTH);
			nganHangm.setLayout(new FlowLayout());
			JButton nSua = new JButton("Sửa", new ImageIcon("edit.png"));
			nganHangm.add(nSua);
			nSua.setForeground(new Color(255, 255, 255));
			nSua.setBackground(new Color(64, 157, 250));
			nSua.setFont(new Font("Tahoma", Font.BOLD, 21));
			nSua.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						Vector st = (Vector) vDatan.elementAt(tbnh.getSelectedRow());
						new ChinhNganHang(st.get(0).toString(),st.get(1).toString(),st.get(2).toString(),Sua.this,1);
					} catch (ArrayIndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "Không có gì để sửa");	
					}
				}
			});
			JButton nXoa = new JButton("Xóa", new ImageIcon("delete.png"));
			nganHangm.add(nXoa);
			nXoa.setForeground(new Color(255, 255, 255));
			nXoa.setBackground(new Color(64, 157, 250));
			nXoa.setFont(new Font("Tahoma", Font.BOLD, 21));
			nXoa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String []list = {"Có","Không"};
					int n = JOptionPane.showOptionDialog(null,"Bạn có muốn xóa","Messeger",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,list,0);
					if (n==0) {
						try {
							Vector st = (Vector) vDatan.elementAt(tbnh.getSelectedRow());
							Statement statement = conn.createStatement();
							statement.executeUpdate("Delete from nganhang where manh= '"+st.elementAt(0)+"'");
							vDatan.remove(tbnh.getSelectedRow());
							modelnh.fireTableDataChanged();
							statement.close();
							JOptionPane.showMessageDialog(null, "Xóa thành công!");
						} catch (ArrayIndexOutOfBoundsException e2) {
							JOptionPane.showMessageDialog(null, "Không có gì để xóa");
						} catch (MySQLIntegrityConstraintViolationException e2) {
							JOptionPane.showMessageDialog(null, "Xóa hồ sơ chứa ngân hàng trước");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Lỗi khi xóa");
						}
					}
				}
			});
			JButton nChen = new JButton("Chèn", new ImageIcon("insert.png"));
			nChen.setForeground(new Color(255, 255, 255));
			nChen.setBackground(new Color(64, 157, 250));
			nChen.setFont(new Font("Tahoma", Font.BOLD, 21));
			nChen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new ChinhNganHang("","","",Sua.this,2);
				}
			});
			nganHangm.add(nChen);
			JPanel nganHangC = new JPanel();
			hoSo.add(nganHangC, BorderLayout.CENTER);
			conn = new dataConnection().ConnectDB();
			reloadsv();
			modelsv = new DefaultTableModel(vDatas,vTitles);
			tbsv = new JTable(modelsv);
			JScrollPane tableresult = new JScrollPane(tbsv);
			sinhVien.add(tableresult);
			reloadHoSo();
			modelhs = new DefaultTableModel(vDatah,vTitleh);
			tbhs = new JTable(modelhs);
			tableresult = new JScrollPane(tbhs);
			hoSo.add(tableresult);	
			reloadNganHang();
			modelnh = new DefaultTableModel(vDatan,vTitlen);
			tbnh = new JTable(modelnh);
			tableresult = new JScrollPane(tbnh);
			nganHang.add(tableresult);	
			setSize(1000,600);
			setLocation(200,100);
			setTitle("Sửa");
			setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void reloadsv() {
		try {
			vTitles.clear();
			vDatas.clear();
			statement = conn.createStatement();	
			resultSet = statement.executeQuery("select masv as 'Ma sinh vien', hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh', nganhhoc as 'Nganh hoc',lop as 'Lop',truong as 'Truong' from sinhvien");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int num_colum = resultSetMetaData.getColumnCount();
			for (int i=1;i<=num_colum;i++) {
				vTitles.add(resultSetMetaData.getColumnLabel(i));
			}	
			while (resultSet.next()) {
				Vector row = new Vector(num_colum);
				for (int i=1;i<=num_colum;i++) {
					row.add(resultSet.getString(i));
				}
				vDatas.add(row);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reloadNganHang() {
		try {
			vTitlen.clear();
			vDatan.clear();	
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select manh as 'Ma ngan hang', tennganhang as 'Ten ngan hang',laixuat as 'Lai xuat' from nganhang");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int num_colum = resultSetMetaData.getColumnCount();
			for (int i=1;i<=num_colum;i++) {
				vTitlen.add(resultSetMetaData.getColumnLabel(i));
			}
			while (resultSet.next()) {
				Vector row = new Vector(num_colum);
				for (int i=1;i<=num_colum;i++) {
					row.add(resultSet.getString(i));
				}
				vDatan.add(row);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void reloadHoSo() {
		try {
			vTitleh.clear();
			vDatah.clear();
			masv.clear();
			manh.clear();
			tennh.clear();
			tensv.clear();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select  hoso.masv as 'Ma sinh vien',sinhvien.hoten as 'Ho ten', nganhang.tennganhang as 'Ten ngan hang', ngayvay as 'Ngay vay', sotien as 'So tien', nganhang.manh "
					+ "from hoso inner join sinhvien on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh=hoso.manh");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int i=1;i<=5;i++) {
				vTitleh.add(resultSetMetaData.getColumnLabel(i));
			}
			while (resultSet.next()) {
				Vector row = new Vector(5);
				tensv.add(resultSet.getString(2));
				masv.add(resultSet.getString(1));
				if (tennh.indexOf(resultSet.getString(3))==-1) {
					tennh.add(resultSet.getString(3));	
					manh.add(resultSet.getString(6));
				}
				for (int i=1;i<=5;i++) {
					row.add(resultSet.getString(i));
				}
				vDatah.add(row);
			}
			resultSet.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
	}

}
