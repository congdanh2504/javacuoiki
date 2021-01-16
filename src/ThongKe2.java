import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class ThongKe2 extends JFrame {

	public ThongKe2() {
		dataConnection con = (dataConnection) new dataConnection();
		Connection conn = con.ConnectDB();
		Vector<String> list = new Vector();
		Vector<String> list1 = new Vector();
		try {
			Statement sta = conn.createStatement();
			Statement statement = conn.createStatement();	
			ResultSet resultSet = statement.executeQuery("select * from nganhang");
			while (resultSet.next()) {
				list.add(resultSet.getString(3));
				list1.add(resultSet.getString(2));
			}
			resultSet.close();
			statement.close();
			DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
			for (int i=0;i<list.size();i++) {
				categoryDataset.setValue(Double.parseDouble(list.get(i)), "Lãi xuất", list1.get(i));
			}
			JFreeChart jFreeChart = ChartFactory.createBarChart("Biểu đồ lãi vay từng ngân hàng","Tên ngân hàng","Lãi vay (%)", categoryDataset,PlotOrientation.VERTICAL, true, true, false);
			CategoryPlot p = (CategoryPlot) jFreeChart.getPlot();
			ChartFrame chartFrame = new ChartFrame("Thống kê",jFreeChart);
			chartFrame.setVisible(true);
			jFreeChart.getPlot().setBackgroundPaint( new Color(64, 157, 250) );
			chartFrame.setLocation(700,150);
			chartFrame.setSize(600,500);
			chartFrame.setTitle("Thống kê");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
