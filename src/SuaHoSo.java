import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.JobAttributes;

public class SuaHoSo extends JFrame implements MouseListener{
	Vector vData=new Vector(), vTitle=new Vector();
	int selectedRow=0;
	JTable tb = new JTable();
	DefaultTableModel model;
	Statement statement ;
	Connection conn;
	ResultSet resultSet;
	Vector<String> masv = new Vector<String>();
	Vector<String> manh = new Vector<String>();
	Vector<String> tensv = new Vector<String>();
	Vector<String> tennh = new Vector<String>();
	public SuaHoSo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(210, 100, 1000, 585);
		JPanel contentPane;
		try {
			contentPane = new JPanelWithBackground("bluez.jpg");
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			JButton btnback = new JButton("Quay lui");
			btnback.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnback.setForeground(new Color(255, 255, 255));
			btnback.setBackground(new Color(64, 157, 250));
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new QLVVSV();
				}
			});
			btnback.setBounds(10, 10, 90, 25);
			btnback.setFocusable(false);
			contentPane.add(btnback);	
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 45, 965, 490);
			contentPane.add(panel);
			dataConnection con = (dataConnection) new dataConnection();
			conn = con.ConnectDB();
			reload();
			panel.setLayout(null);
			model = new DefaultTableModel(vData,vTitle);
			tb= new JTable(model);
			tb.addMouseListener(this);
			JScrollPane tableresult = new JScrollPane(tb);
			tableresult.setLocation(10, 10);
			tableresult.setSize(945, 415);
			panel.add(tableresult);	
			JButton btnsua = new JButton("Sửa");
			btnsua.setForeground(new Color(255, 255, 255));
			btnsua.setBackground(new Color(64, 157, 250));
			btnsua.setFont(new Font("Tahoma", Font.BOLD, 21));
			btnsua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Vector st = (Vector) vData.elementAt(selectedRow);
						new ChinhHoSo(st.get(2).toString(), st.get(3).toString(),st.get(0).toString(),st.get(1).toString(),SuaHoSo.this);
					} catch (ArrayIndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(null, "Không có gì để sửa");
					}
				}
			});
			btnsua.setBounds(305, 440, 100, 40);
			panel.add(btnsua);
			JButton btnxoa = new JButton("Xóa");
			btnxoa.setForeground(new Color(255, 255, 255));
			btnxoa.setBackground(new Color(64, 157, 250));
			btnxoa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String []list = {"Có","Không"};
					int n = JOptionPane.showOptionDialog(null,"Bạn có muốn xóa","Messeger",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,list,0);
					if (n==0) {	
						try {
							Vector st = (Vector) vData.elementAt(selectedRow);
							String smasv = masv.get(tensv.indexOf(st.elementAt(0)));
							String smanh = manh.get(tennh.indexOf(st.elementAt(1)));
							Statement sta = conn.createStatement();
							Statement statement = conn.createStatement();
							int check = statement.executeUpdate("Delete from hoso where masv= '"+smasv+"' and manh= '"+smanh+"'");
							vData.remove(selectedRow);
							model.fireTableDataChanged();
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
			btnxoa.setFont(new Font("Tahoma", Font.BOLD, 21));
			btnxoa.setBounds(445, 440, 100, 40);
			panel.add(btnxoa);
			btnsua.setFocusable(false);
			btnxoa.setFocusable(false);
			
			JButton btnchen = new JButton("Chèn");
			btnchen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChenHoSo(SuaHoSo.this);
				}
			});
			btnchen.setFont(new Font("Tahoma", Font.BOLD, 21));
			btnchen.setFocusable(false);
			btnchen.setForeground(new Color(255, 255, 255));
			btnchen.setBackground(new Color(64, 157, 250));
			btnchen.setBounds(590, 440, 100, 40);
			panel.add(btnchen);
			setTitle("Hồ sơ");
			setResizable(false);
			setVisible(true);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	public void reload() {
		try {
			vTitle.clear();
			vData.clear();
			masv.clear();
			manh.clear();
			tennh.clear();
			tensv.clear();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("select sinhvien.hoten as 'Ho ten', nganhang.tennganhang as 'Ten ngan hang', ngayvay as 'Ngay vay', sotien as 'So tien', hoso.masv, nganhang.manh "
					+ "from hoso inner join sinhvien on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh=hoso.manh");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for (int i=1;i<=4;i++) {
				vTitle.add(resultSetMetaData.getColumnLabel(i));
			}
			while (resultSet.next()) {
				Vector row = new Vector(4);
				tensv.add(resultSet.getString(1));
				tennh.add(resultSet.getString(2));
				masv.add(resultSet.getString(5));
				manh.add(resultSet.getString(6));
				for (int i=1;i<=4;i++) {
					row.add(resultSet.getString(i));
				}
				vData.add(row);
			}
			resultSet.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selectedRow=tb.getSelectedRow();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}