import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
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
import javax.swing.UIManager;

public class Timkiem extends JFrame {
	private JPanel contentPane;
	private JTextField nhapten;
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
		setBounds(210, 100, 1000, 585);
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
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(10, 45, 965, 440);
			contentPane.add(panel);
			nhapten = new JTextField();
			nhapten.setBounds(350, 500, 256, 38);
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
			contentPane.add(nhapten);
			nhapten.setColumns(10);
			JLabel lblnhap = new JLabel("Nhập mã sinh viên hoặc tên:");
			lblnhap.setForeground(Color.WHITE);
			lblnhap.setFont(new Font("Tahoma", Font.BOLD, 21));
			lblnhap.setBounds(50, 500, 310, 40);
			contentPane.add(lblnhap);
			JButton btntim = new JButton("Tìm");
			btntim.setForeground(new Color(255, 255, 255));
			btntim.setBackground(new Color(64, 157, 250));
			btntim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					display();
				}
			});
			btntim.setFont(new Font("Tahoma", Font.BOLD, 21));
			btntim.setBounds(620, 500, 100, 40);
			contentPane.add(btntim);
			setTitle("Tìm kiếm");
			setResizable(false);
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
						+ "inner join hoso on hoso.masv = sinhvien.masv inner join nganhang on nganhang.manh = hoso.manh "
						+ "where sinhvien.masv like '%"+ten+"%' or hoten like '%"+ten+"%'");
				
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
			panel.setLayout(null);
			JScrollPane tableresult = new JScrollPane(new JTable(vData,vTitle));
			tableresult.setLocation(10, 10);
			tableresult.setSize(950, 410);
			panel.add(tableresult);	
			JOptionPane.showMessageDialog(null, "Tìm thấy "+vData.size()+" kết quả");
		}
	}
}
