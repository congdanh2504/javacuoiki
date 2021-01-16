import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ChinhNganHang extends JFrame {

	private JTextField textmanh;
	private JTextField texttennh;
	private JTextField textlaivay;
	SuaNganHang snh;
	public ChinhNganHang(String dma, String dten, String dlai, SuaNganHang suaNganHang) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(880, 180, 320, 325);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 157, 250));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4,2));
		snh=suaNganHang;
		JLabel lblmanh = new JLabel("Mã ngân hàng ");
		lblmanh.setForeground(new Color(255, 255, 255));
		lblmanh.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblmanh);
		textmanh = new JTextField();
		contentPane.add(textmanh);
		textmanh.setColumns(10);
		JLabel lbltennh = new JLabel("Tên ngân hàng");
		lbltennh.setForeground(new Color(255, 255, 255));
		lbltennh.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbltennh);
		texttennh = new JTextField();
		contentPane.add(texttennh);
		texttennh.setColumns(10);
		JLabel lbllaivay = new JLabel("Lãi vay");
		lbllaivay.setForeground(new Color(255, 255, 255));
		lbllaivay.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbllaivay);
		textlaivay = new JTextField();
		contentPane.add(textlaivay);
		textlaivay.setColumns(10);
		textmanh.setText(dma);
		textmanh.setEditable(false);
		texttennh.setText(dten);
		textlaivay.setText(dlai);
		JButton btnok = new JButton("OK");
		btnok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String manh = textmanh.getText();
				String tennh = texttennh.getText();
				String laivay = textlaivay.getText();
				if(laivay.equals("") || tennh.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ");
				} else {
					try {
						Double.parseDouble(laivay);
						dataConnection con = (dataConnection) new dataConnection();
						Connection conn = con.ConnectDB();
						Statement statement1 = conn.createStatement();
						nganhang nh = new nganhang(manh,tennh,laivay);
						statement1.executeUpdate("UPDATE `nganhang` SET manh='"+nh.getManh()+"', tennganhang='"+nh.getTennganhang()+"', laixuat='"+nh.getLaivay()+"' "
								+ "where manh='"+nh.getManh()+"'");
						statement1.close();
						snh.reload();
						snh.model.fireTableDataChanged();
						dispose();
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
					} catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Nhập tiền lãi là một số");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Lỗi, vui lòng nhập lại");	
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
		btncancel.setForeground(new Color(255, 255, 255));
		btncancel.setBackground(new Color(64, 157, 250));
		btncancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btncancel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
}
