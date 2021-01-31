package Activitys;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Fun.JPanelWithBackground;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class QLVVSV extends JFrame {
	
	public static void main(String[] args) {
			new QLVVSV();
	}
	public QLVVSV() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(280, 120, 800, 550);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setOpaque(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel;
		try {
			panel = new JPanelWithBackground("blue.jpg");
			panel.setBounds(0, 0, 800, 550);
			contentPane.add(panel);
			panel.setLayout(null);
			JLabel lbltitle = new JLabel("Hệ thống quản lí vay vốn sinh viên");
			lbltitle.setIcon(new ImageIcon("system.png"));
			lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
			lbltitle.setBackground(new Color(255, 255, 255));
			lbltitle.setForeground(new Color(255, 255, 255));
			lbltitle.setFont(new Font("Tahoma", Font.BOLD, 30));
			lbltitle.setBounds(100, 10, 585, 85);
			panel.add(lbltitle);
			JButton btnXem = new JButton("Xem", new ImageIcon("sea.png"));
			btnXem.setForeground(new Color(255, 255, 255));
			btnXem.setBackground(new Color(64, 157, 250));
			btnXem.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnXem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Xem();
				}
			});
			btnXem.setBounds(60, 100, 190, 70);
			panel.add(btnXem);
			JButton btnTimkiem = new JButton("Tìm kiếm", new ImageIcon("xem.png"));
			btnTimkiem.setForeground(new Color(255, 255, 255));
			btnTimkiem.setBackground(new Color(64, 157, 250));
			btnTimkiem.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnTimkiem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					dispose();
					new Timkiem();
				}
			});
			btnTimkiem.setBounds(60, 200, 190, 70);
			panel.add(btnTimkiem);	
			JButton btnCapNhat = new JButton("Cập nhật", new ImageIcon("update.png"));
			btnCapNhat.setForeground(new Color(255, 255, 255));
			btnCapNhat.setBackground(new Color(64, 157, 250));
			btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnCapNhat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Sua();
				}
			});
			btnCapNhat.setBounds(60, 300, 190, 70);
			panel.add(btnCapNhat);
			JButton btnThongKe = new JButton("Thống kê", new ImageIcon("thongke.png"));
			btnThongKe.setForeground(new Color(255, 255, 255));
			btnThongKe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Thongke();
				}
			});
			btnThongKe.setBackground(new Color(64, 157, 250));
			btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnThongKe.setBounds(60, 400, 190, 70);
			panel.add(btnThongKe);
			btnXem.setFocusable(false);
			btnCapNhat.setFocusable(false);
			btnThongKe.setFocusable(false);
			btnTimkiem.setFocusable(false);
			JLabel lblicon = new JLabel("");
			lblicon.setBounds(380, 120, 330, 330);
			lblicon.setIcon(new ImageIcon("loan1.png"));
			panel.add(lblicon);
			setTitle("Hệ thống quản lí vay vốn ngân hàng của sinh viên");
			setResizable(false);
			setVisible(true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
