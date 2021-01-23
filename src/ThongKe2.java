import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class Thongke2 extends JFrame {
	Vector<String> ngayvay= new Vector<>(), soluong = new Vector<>(),manh = new Vector<>(),tennh = new Vector<>(),sngayvay = new Vector<>();
	Connection connection;
	DefaultCategoryDataset dataset;
	public Thongke2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			JPanel contentPane = new JPanelWithBackground("bluez.jpg");
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout());
			setLocation(50,0);
			setSize(1200,700);
			connection = new dataConnection().ConnectDB();
			Statement statement = connection.createStatement();
			ResultSet resultSet1 = statement.executeQuery("SELECT * FROM nganhang");
			while (resultSet1.next()) {
				manh.add(resultSet1.getString(1));
				tennh.add(resultSet1.getString(2));
			}
			ResultSet resultSet2 = statement.executeQuery("SELECT ngayvay FROM hoso order by ngayvay");
			while (resultSet2.next()) {
				sngayvay.add(resultSet2.getString(1));
			}
			dataset = new DefaultCategoryDataset();
			for (int i=0;i<tennh.size();i++) {
				for (int j=0;j<sngayvay.size();j++) {
					dataset.addValue(0, tennh.get(i), sngayvay.get(j));
				}
			}
			for (int i=0;i<manh.size();i++) {
				if (ngayvay!=null && soluong!=null) {
					ngayvay.clear();
					soluong.clear();
				}
				ResultSet resultSet = statement.executeQuery("SELECT ngayvay as 'Ngay vay', COUNT(masv) as 'So ho so' FROM `hoso`"
						+ "WHERE manh='"+manh.get(i)+"' GROUP BY ngayvay ORDER BY ngayvay");
				while (resultSet.next()) {
					ngayvay.add(resultSet.getString(1));
					soluong.add(resultSet.getString(2));
				}
				
				for (int j=0;j<ngayvay.size();j++) {
					dataset.setValue(new Double(soluong.get(j)), tennh.get(manh.indexOf(manh.get(i))), ngayvay.get(j));
				}
			}
			JFreeChart chart = ChartFactory.createLineChart3D("Biểu đồ biểu diễn số lượng hồ sơ theo ngày", "Ngày", "Số hồ sơ", dataset);
			CategoryPlot p = (CategoryPlot) chart.getPlot();
			p.setRangeGridlinePaint(Color.BLUE);
			JPanel panel = new JPanel();
			panel.setBackground(new Color(64, 157, 250));
			panel.setLayout(new BorderLayout());
			JButton Back = new JButton("Quay lại", new ImageIcon("back.png"));
			Back.setFocusable(false);
			panel.add(Back, BorderLayout.WEST);
			Back.setForeground(new Color(255, 255, 255));
			Back.setBackground(new Color(64, 157, 250));
			Back.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
					new Thongke();
				}
			});
			contentPane.add(panel, BorderLayout.NORTH);
			chart.setBackgroundPaint(new Color(64, 157, 250));
			ChartPanel chartPanel = new ChartPanel(chart);
			contentPane.add(chartPanel, BorderLayout.CENTER);
			setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
