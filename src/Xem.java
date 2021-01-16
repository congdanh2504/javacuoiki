import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class Xem extends JFrame {
	
	Vector vData=null, vTitle=null;
	public Xem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(210, 100, 1000, 585);
		JPanel contentPane;
		try {
			contentPane = new JPanelWithBackground("bluez.jpg");
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);	
			JButton btnback = new JButton("Quay lại");
			btnback.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnback.setForeground(new Color(255, 255, 255));
			btnback.setBackground(new Color(64, 157, 250));
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new QLVVSV();
				}
			});
			btnback.setFocusable(false);
			btnback.setBounds(10, 10, 90, 25);
			contentPane.add(btnback);	
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(10, 45, 970, 490);
			contentPane.add(panel);
			dataConnection con = (dataConnection) new dataConnection();
			Connection conn = con.ConnectDB();
			try {
				Statement sta = conn.createStatement();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("select sinhvien.masv as 'Ma sinh vien',hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh',nganhhoc as 'Nganh hoc', lop 'Lop', truong as 'Truong',ngayvay as 'Ngay vay', sotien as 'So tien' ,tennganhang as 'Ten ngan hang', laixuat as 'Lai xuat' from SINHVIEN "
						+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh");
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int num_colum = resultSetMetaData.getColumnCount();
				vTitle = new Vector(num_colum);
				for (int i=1;i<=num_colum;i++) {
					vTitle.add(resultSetMetaData.getColumnLabel(i));
				}
				vData = new Vector();
				while (resultSet.next()) {
					Vector row = new Vector(num_colum);
					for (int i=1;i<=num_colum;i++) {
						row.add(resultSet.getString(i));
					}
					vData.add(row);
				}
				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			panel.setLayout(null);
			JScrollPane tableresult = new JScrollPane(new JTable(vData,vTitle));
			tableresult.setLocation(10, 10);
			tableresult.setSize(950, 470);
			panel.add(tableresult);
			setTitle("Xem");
			setResizable(false);
			setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
}