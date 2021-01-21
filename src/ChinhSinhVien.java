import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ChinhSinhVien extends JFrame {

	private JTextField textmasv;
	private JTextField textten;
	private JTextField textngaysinh;
	private JTextField textnganh;
	private JTextField texttruong;
	private JTextField textlop;
	int check;
	Sua ssv;
	public ChinhSinhVien(String dmasv, String dhoten, String dngaysinh, String dgioitinh, String dnganh, String dlop, String dtruong, Sua sinhVien, int n) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(880, 180, 320, 325);
		check =n;
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 157, 250));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8,2));
		ssv=sinhVien;
		JLabel lblmasv = new JLabel("Mã sinh viên");
		lblmasv.setForeground(new Color(255, 255, 255));
		lblmasv.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblmasv);
		textmasv = new JTextField();
		contentPane.add(textmasv);
		textmasv.setColumns(10);
		JLabel lblten = new JLabel("Tên ");
		lblten.setForeground(new Color(255, 255, 255));
		lblten.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblten);
		textten = new JTextField();
		contentPane.add(textten);
		textten.setColumns(10);
		JLabel lblngaysinh = new JLabel("Ngày sinh");
		lblngaysinh.setForeground(new Color(255, 255, 255));
		lblngaysinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblngaysinh);
		textngaysinh = new HintTextField("yyyy-mm-dd");
		contentPane.add(textngaysinh);
		textngaysinh.setColumns(10);
		JLabel lblgioitinh = new JLabel("Giới tính");
		lblgioitinh.setForeground(new Color(255, 255, 255));
		lblgioitinh.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblgioitinh);
		JComboBox cbgioitinh = new JComboBox();
		contentPane.add(cbgioitinh);
		cbgioitinh.addItem("Nam");
		cbgioitinh.addItem("Nu");
		JLabel lblnganh = new JLabel("Ngành");
		lblnganh.setForeground(new Color(255, 255, 255));
		lblnganh.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblnganh);
		textnganh = new JTextField();
		contentPane.add(textnganh);
		textnganh.setColumns(10);
		JLabel lbltruong = new JLabel("Trường");
		lbltruong.setForeground(new Color(255, 255, 255));
		lbltruong.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbltruong);
		texttruong = new JTextField();
		contentPane.add(texttruong);
		texttruong.setColumns(10);
		JLabel lbllop = new JLabel("Lớp");
		lbllop.setForeground(new Color(255, 255, 255));
		lbllop.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbllop);
		textlop = new JTextField();
		contentPane.add(textlop);
		textlop.setColumns(10);
		textmasv.setText(dmasv);
		if (check==1) {
			textmasv.setEditable(false);
		}
		textlop.setText(dlop);
		textnganh.setText(dnganh);
		texttruong.setText(dtruong);
		textngaysinh.setText(dngaysinh);
		textten.setText(dhoten);
		JButton btnok = new JButton("OK");
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String masv = textmasv.getText();
				String hoten = textten.getText();
				String ngaysinh= textngaysinh.getText();
				String gioitinh = cbgioitinh.getSelectedItem().toString();
				String nganhhoc = textnganh.getText();
				String lop = textlop.getText();
				String truong = texttruong.getText();
				if (check == 1) {
					if (hoten.equals("") || ngaysinh.equals("") || nganhhoc.equals("") || lop.equals("") || truong.equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ");
					} else {
						try {
							dataConnection con = (dataConnection) new dataConnection();
							Connection conn = con.ConnectDB();
							Statement statement1 = conn.createStatement();
							sinhvien s = new sinhvien(masv,hoten,ngaysinh,gioitinh,nganhhoc,lop,truong);
							statement1.executeUpdate("UPDATE `sinhvien` set hoten='"+s.getHoten()+"', ngaysinh='"+s.getNgaysinh()+"', gioitinh='"+s.getGioitinh()+"', nganhhoc='"+s.getNganhhoc()+"', lop='"+s.getLop()+"', truong='"+s.getTruong()+"' "
									+ "where masv='"+s.getMasv()+"'");
							statement1.close();
							ssv.reloadsv();
							ssv.modelsv.fireTableDataChanged();
							dispose();
							JOptionPane.showMessageDialog(null, "Sửa thành công!");
						}	catch (MysqlDataTruncation e3) {
							JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Lỗi, vui lòng nhập lại");
						}		
					}
				} else {
					if (masv.equals("") || hoten.equals("") || ngaysinh.equals("") || nganhhoc.equals("") || lop.equals("") || truong.equals("")) {
						JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ");
					} else {
						try {
							dataConnection con = (dataConnection) new dataConnection();
							Connection conn = con.ConnectDB();
							Statement statement1 = conn.createStatement();	
							sinhvien s = new sinhvien(masv,hoten,ngaysinh,gioitinh,nganhhoc,lop,truong);
							int check = statement1.executeUpdate("INSERT INTO `sinhvien` (`masv`, `hoten`, `ngaysinh`, `gioitinh`, `nganhhoc`, `lop`, `truong`) VALUES ('"+s.getMasv()+"', '"+s.getHoten()+"', '"+s.getNgaysinh()+"', '"+s.getGioitinh()+"', '"+s.getNganhhoc()+"', '"+s.getLop()+"', '"+s.getTruong()+"');");
							statement1.close();
							ssv.reloadsv();
							ssv.modelsv.fireTableDataChanged();
							dispose();
							JOptionPane.showMessageDialog(null, "Chèn thành công!");			
						} catch (MysqlDataTruncation e3) {
							JOptionPane.showMessageDialog(null, "Nhập sai định dạng ngày");
						} catch (MySQLIntegrityConstraintViolationException e2) {
							JOptionPane.showMessageDialog(null, "Mỗi sinh viên chỉ có một mã");
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Lỗi, vui lòng nhập lại");			
						}
					}
				}
				
			}
		});
		btnok.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnok.setForeground(new Color(255, 255, 255));
		btnok.setBackground(new Color(64, 157, 250));
		contentPane.add(btnok);
		
		JButton btncancel = new JButton("Hủy");
		btncancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btncancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btncancel.setForeground(new Color(255, 255, 255));
		btncancel.setBackground(new Color(64, 157, 250));
		contentPane.add(btncancel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
