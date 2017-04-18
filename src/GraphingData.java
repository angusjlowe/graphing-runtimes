

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class GraphingData extends ApplicationFrame {

    /**
     * A demonstration application showing an XY series containing a null value.
     *
     * @param title the frame title.
     */
    public GraphingData(final String title, double[][] dataPoints) {

        super(title);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection();
        final XYSeries series1 = new XYSeries("Runtimes1");
        final XYSeries series2 = new XYSeries("Runtimes2");
        for(double[] item : dataPoints) {
            series1.add(item[0],item[1]);
            series2.add(item[0],item[2]);
        }
        xySeriesCollection.addSeries(series1);
        xySeriesCollection.addSeries(series2);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Algorithm Runtimes",
                "Input Size",
                "Runtime (Milliseconds)",
                xySeriesCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);

    }
}