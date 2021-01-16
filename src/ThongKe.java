import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ThongKe  {
	
	public ThongKe() {
		dataConnection con = (dataConnection) new dataConnection();
		Connection conn = con.ConnectDB();
		Vector<String> list = new Vector();
		try {
			Statement sta = conn.createStatement();
			Statement statement = conn.createStatement();	
			ResultSet resultSet = statement.executeQuery("select tennganhang from nganhang");
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
			int[] list1 = new int[list.size()];
			ResultSet resultSet1 = statement.executeQuery("select tennganhang, masv from nganhang inner join hoso on hoso.manh = nganhang.manh");
			while (resultSet1.next()) {
				list1[list.indexOf(resultSet1.getString(1))]++;
			}
			resultSet.close();
			statement.close();
			DefaultPieDataset pieDataset = new DefaultPieDataset();
			for (int i=0;i<list.size();i++) {
				pieDataset.setValue(list.get(i), list1[i]);
			}
			JFreeChart jFreeChart = ChartFactory.createPieChart("Tỉ lệ sinh viên vay vốn mỗi ngân hàng", pieDataset, true, true, true);
			PiePlot p = (PiePlot) jFreeChart.getPlot();
			ChartFrame chartFrame = new ChartFrame("Thống kê",jFreeChart);
			chartFrame.setVisible(true);
			jFreeChart.getPlot().setBackgroundPaint( new Color(64, 157, 250) );
			chartFrame.setLocation(70,150);
			chartFrame.setSize(600,500);
			chartFrame.setTitle("Thống kê");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi");
		}
		
	}

}
