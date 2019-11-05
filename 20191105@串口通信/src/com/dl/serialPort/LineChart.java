package com.dl.serialPort;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import com.dl.swing.util.FontAndColor;
import com.dl.swing.util.ISwing;

public class LineChart extends FontAndColor implements ISwing{
	private JFrame frameMain;
	private ClientFirst clientFirst;
	
	private ChartPanel panelTem;
	private JFreeChart chartTem;
	private ChartPanel panelHum;
	private JFreeChart chartHum;
	private CategoryPlot plotTem;
	private CategoryPlot plotHum;
	
	private DefaultCategoryDataset datasetTem;
	private DefaultCategoryDataset datasetHum;
	
	private static final Map<String, Integer> temMap;
	private static final Map<String, Integer> humMap;
	static {
		temMap = new HashMap<>();
		humMap = new HashMap<>();
	}
	
	public LineChart(ClientFirst clientFirst) {
		this.clientFirst = clientFirst;
		
		chartTem = ChartFactory.createLineChart("temperature", "", "C", datasetTem, PlotOrientation.VERTICAL , true, true, true);
		chartHum = ChartFactory.createLineChart("humidity", "", "%", datasetHum, PlotOrientation.VERTICAL , true, true, true);
		TextTitle ttleTem = new TextTitle("temperature", normalFont);
		TextTitle ttleHum = new TextTitle("humidity", normalFont);
		chartTem.setTitle(ttleTem);
		chartHum.setTitle(ttleHum);
		plotTem = (CategoryPlot)chartTem.getPlot();
		plotHum = (CategoryPlot)chartHum.getPlot();
		plotTem.setBackgroundPaint(blueColor);
		plotHum.setBackgroundPaint(blueColor);
		panelTem = new ChartPanel(chartTem, true);
		panelHum = new ChartPanel(chartHum, true);
		
		datasetTem = new DefaultCategoryDataset();
		datasetHum = new DefaultCategoryDataset();
		initSwing();
	}
	
	public void addDatasetTem(String type, String tem) {
		datasetTem.addValue(Integer.valueOf(tem), type, String.valueOf(getTemIndex(type)));
		plotTem.setDataset(datasetTem);
	}
	
	public void addDatasetHum(String type, String hum) {
		datasetHum.addValue(Integer.valueOf(hum), type, String.valueOf(getHumIndex(type)));
		plotHum.setDataset(datasetHum);
	}
	
	public int getTemIndex(String type) {
		if (!temMap.containsKey(type)) {
			temMap.put(type, 2);
			return 1;
		}
		int index = temMap.get(type);
		temMap.put(type, index + 1);
		return index;
	}
	
	public int getHumIndex(String type) {
		if (!humMap.containsKey(type)) {
			humMap.put(type, 2);
			return 1;
		}
		int index = humMap.get(type);
		humMap.put(type, index + 1);
		return index;
	}
	
	@Override
	public void init() {
		frameMain = new JFrame();
		frameMain.setLayout(new GridLayout(2, 1));
		frameMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		frameMain.setResizable(false);
		frameMain.setBounds(700, 50, 800, 750);
		frameMain.add(panelTem);
		frameMain.add(panelHum);
	}

	@Override
	public void reinit() {
	}

	@Override
	public void dealAction() {
		frameMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeView();
			}
		});
	}

	@Override
	public void showView() {
		frameMain.setVisible(true);
	}

	@Override
	public void closeView() {
		frameMain.dispose();
		clientFirst.setLocation(false);
	}
	
	public boolean isShow() {
		return frameMain.isVisible();
	}
}
