import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.ImageIcon;
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

public class Xem extends JFrame {
	JScrollPane tableresult;
	Connection conn ;
	JPanel contentPane;
	Vector vData=null, vTitle=null;
	public Xem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			contentPane = new JPanelWithBackground("bluez.jpg");
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());	
			JButton btnback = new JButton("Quay lại", new ImageIcon("back.png"));
			btnback.setForeground(new Color(255, 255, 255));
			btnback.setBackground(new Color(64, 157, 250));
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new QLVVSV();
				}
			});
			btnback.setFocusable(false);
			JPanel pnback = new JPanel();
			pnback.setLayout(new BorderLayout());
			pnback.setBackground(new Color(64, 157, 250));
			pnback.add(btnback, BorderLayout.WEST);	
			contentPane.add(pnback, BorderLayout.NORTH);
			conn = new dataConnection().ConnectDB();
			try {
				Statement sta = conn.createStatement();
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery("select sinhvien.masv as 'Ma sinh vien',hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh',nganhhoc as 'Nganh hoc', lop 'Lop', truong as 'Truong',ngayvay as 'Ngay vay', sotien as 'So tien' ,tennganhang as 'Ten ngan hang', laixuat as 'Lai xuat' from SINHVIEN "
						+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh "
						+ "order by sinhvien.hoten");
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
			tableresult = new JScrollPane(new JTable(vData,vTitle));
			contentPane.add(tableresult, BorderLayout.CENTER);
			JPanel pnBot = new JPanel();
			pnBot.setLayout(new FlowLayout());
			JButton btnSxNgay = new JButton("Sắp xếp theo ngày");
			btnSxNgay.setFocusable(false);
			btnSxNgay.setForeground(new Color(255, 255, 255));
			btnSxNgay.setBackground(new Color(64, 157, 250));
			btnSxNgay.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					sxNgayvay();
				}
			});
			JButton btnSxTien = new JButton("Sắp xếp theo tiền vay");
			btnSxTien.setFocusable(false);
			btnSxTien.setForeground(new Color(255, 255, 255));
			btnSxTien.setBackground(new Color(64, 157, 250));
			btnSxTien.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					sxTien();
				}
			});
			pnBot.add(btnSxNgay);
			pnBot.add(btnSxTien);
			contentPane.add(pnBot, BorderLayout.SOUTH);
			setTitle("Xem");
			setSize(1000,600);
			setLocation(200,100);
			setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	public void sxNgayvay() {
		conn = new dataConnection().ConnectDB();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select sinhvien.masv as 'Ma sinh vien',hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh',nganhhoc as 'Nganh hoc', lop 'Lop', truong as 'Truong',ngayvay as 'Ngay vay', sotien as 'So tien' ,tennganhang as 'Ten ngan hang', laixuat as 'Lai xuat' from SINHVIEN "
					+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh "
					+ "order by hoso.ngayvay");
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
		tableresult.setViewportView(new JTable(vData,vTitle));
	}
	public void sxTien() {
		conn = new dataConnection().ConnectDB();
		try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select sinhvien.masv as 'Ma sinh vien',hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh',nganhhoc as 'Nganh hoc', lop 'Lop', truong as 'Truong',ngayvay as 'Ngay vay', sotien as 'So tien' ,tennganhang as 'Ten ngan hang', laixuat as 'Lai xuat' from SINHVIEN "
					+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh "
					+ "order by hoso.sotien");
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
		tableresult.setViewportView(new JTable(vData,vTitle));
	}
}