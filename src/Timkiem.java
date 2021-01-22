import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

public class Timkiem extends JFrame {
	private JPanel contentPane;
	private JTextField nhapten;
	DefaultTableModel model;
	dataConnection con;
	JScrollPane tableresult;
	Connection conn ;
	Vector vData=null, vTitle=null;
    public String Standardized(String str) {
    	str = str.trim();
        str = str.replaceAll("\\s+", " ");
        String temp[] = str.split(" ");
        str = ""; 
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) 
                str += " ";
        }
        return str;
    }
	public Timkiem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			contentPane = new JPanelWithBackground("bluez.jpg");
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());
			JButton btnback = new JButton("Quay lại", new ImageIcon("back.png"));
			btnback.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnback.setForeground(new Color(255, 255, 255));
			btnback.setBackground(new Color(64, 157, 250));
			JPanel pnback = new JPanel();
			pnback.setLayout(new BorderLayout());
			pnback.setBackground(new Color(64, 157, 250));
			pnback.add(btnback, BorderLayout.WEST);
			contentPane.add(pnback,BorderLayout.NORTH);
			btnback.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new QLVVSV();
				}
			});
			btnback.setFocusable(false);
			con = (dataConnection) new dataConnection();
			conn = con.ConnectDB();
			try {
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
			JPanel jPanel = new JPanelWithBackground("bluez.jpg");
			contentPane.add(jPanel, BorderLayout.SOUTH);
			jPanel.setLayout(new FlowLayout());
			nhapten = new JTextField();
			nhapten.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode()==10) {
						display();
					}
				}
			});
			nhapten.setColumns(10);
			JLabel lblnhap = new JLabel("Nhập mã sinh viên, tên hoặc ngày:");
			lblnhap.setForeground(Color.WHITE);
			lblnhap.setFont(new Font("Tahoma", Font.BOLD, 21));
			jPanel.add(lblnhap);
			jPanel.add(nhapten);
			JButton btntim = new JButton("Tìm", new ImageIcon("tim.png"));
			btntim.setForeground(new Color(255, 255, 255));
			btntim.setBackground(new Color(64, 157, 250));
			jPanel.add(btntim);	
			btntim.setFocusable(false);
			btntim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					display();
				}
			});
			btntim.setFont(new Font("Tahoma", Font.BOLD, 21));
			setSize(1000,600);
			setLocation(200,100);
			setTitle("Tìm kiếm");
			setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	public void display() {
		String ten = nhapten.getText();
		if (ten.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập");
		} else {
			ten = Standardized(ten);
			dataConnection con = (dataConnection) new dataConnection();
			Connection conn = con.ConnectDB();
			try {
				Statement statement = conn.createStatement();
				
				ResultSet resultSet = statement.executeQuery("select sinhvien.masv as 'Ma sinh vien',hoten as 'Ho ten',ngaysinh as 'Ngay sinh',gioitinh as 'Gioi tinh',nganhhoc as 'Nganh hoc', lop 'Lop', truong as 'Truong',ngayvay as 'Ngay vay', sotien as 'So tien' ,tennganhang as 'Ten ngan hang', laixuat as 'Lai xuat' from SINHVIEN "
						+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh "
						+ "where sinhvien.masv = '"+ten+"' or hoten like '%"+ten+"' or ngayvay = '"+ten+"'");
				
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
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			tableresult.setViewportView(new JTable(vData,vTitle));
			JOptionPane.showMessageDialog(null, "Tìm thấy "+vData.size()+" kết quả");
		}
	}
}

