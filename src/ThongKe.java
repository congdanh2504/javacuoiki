import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Thongke extends JFrame {
	public Thongke() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 157, 250));
		setContentPane(contentPanel);
		Connection conn = new dataConnection().ConnectDB();
		Vector<String> tennh = new Vector();
		try {
			Statement statement = conn.createStatement();	
			ResultSet resultSet = statement.executeQuery("select tennganhang from nganhang");
			while (resultSet.next()) {
				tennh.add(resultSet.getString(1));
			}
			int[] sohoso = new int[tennh.size()];
			ResultSet resultSet1 = statement.executeQuery("select tennganhang, masv from nganhang inner join hoso on hoso.manh = nganhang.manh");
			while (resultSet1.next()) {
				sohoso[tennh.indexOf(resultSet1.getString(1))]++;
			}
			
			DefaultPieDataset pieDataset = new DefaultPieDataset();
			for (int i=0;i<tennh.size();i++) {
				pieDataset.setValue(tennh.get(i), new Integer(sohoso[i]*10));
			}
			JFreeChart jFreeChart = ChartFactory.createPieChart("Tỉ lệ sinh viên vay vốn mỗi ngân hàng", pieDataset, true, true, true);
			PiePlot p = (PiePlot) jFreeChart.getPlot();
			jFreeChart.setBackgroundPaint(new Color(64, 157, 250));
			ChartPanel chartPanel = new ChartPanel(jFreeChart);
			chartPanel.setBackground(new Color(64, 157, 250));
			contentPanel.setBackground(Color.WHITE);
			contentPanel.add(chartPanel, BorderLayout.WEST);
			Vector<String> laixuat = new Vector();		
			resultSet = statement.executeQuery("select * from nganhang");
			while (resultSet.next()) {
				laixuat.add(resultSet.getString(3));
			}
			resultSet.close();
			statement.close();
			DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
			for (int i=0;i<tennh.size();i++) {
				categoryDataset.setValue(Double.parseDouble(laixuat.get(i)), "Lãi xuất", tennh.get(i));
			}
			JFreeChart jFreeChart1 = ChartFactory.createBarChart3D("Biểu đồ lãi vay từng ngân hàng","Tên ngân hàng","Lãi vay (%/tháng)", categoryDataset,PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot p1 = (CategoryPlot) jFreeChart1.getPlot();
			jFreeChart1.setBackgroundPaint(new Color(64, 157, 250));
			ChartPanel chartPanel1 = new ChartPanel(jFreeChart1);
			contentPanel.add(chartPanel1, BorderLayout.EAST);
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
			JButton btnthongke = new JButton("Thống kê theo ngày");
			btnthongke.setForeground(new Color(255, 255, 255));
			btnthongke.setBackground(new Color(64, 157, 250));
			btnthongke.setFocusable(false);
			pnback.add(btnthongke,BorderLayout.EAST);
			btnthongke.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					new Thongke2();
				}
			});
			pnback.add(btnback, BorderLayout.WEST);	
			contentPanel.add(pnback, BorderLayout.NORTH);
			JPanel line = new JPanel();
			line.setBackground(new Color(64, 157, 250));
			contentPanel.add(line, BorderLayout.SOUTH);
			setVisible(true);
			setLocation(0,20);
			setSize(1400,700);
			setTitle("Thống kê");
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
