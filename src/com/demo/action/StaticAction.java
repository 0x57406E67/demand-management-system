package com.demo.action;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

public class StaticAction extends ActionSupport{
	Logger logger=Logger.getLogger(StaticAction.class);
	@Autowired 
	OrderService orderService;
	 private JFreeChart chart; 
	 //必须提供 getChart() 方法，且由该方法返回 JFreeChart 对象
	 public JFreeChart getChart() 
	 { 
		 chart = ChartFactory.createPieChart3D("需求分布图", getDataSet(), true, false, false);
		// 重新设置图表标题，改变字体
		 chart.setTitle(new TextTitle("图书销量统计图"
			 , new Font("黑体", Font.ITALIC , 22))); 
		 // 取得统计图表的第一个图例
		 LegendTitle legend = chart.getLegend(0); 
		 // 修改图例的字体
		 legend.setItemFont(new Font("宋体", Font.BOLD, 14)); 
		 // 获得饼图的 Plot 对象
		 PiePlot plot = (PiePlot)chart.getPlot(); 
		 // 设置饼图各部分的标签字体
		 plot.setLabelFont(new Font("隶书", Font.BOLD, 18)); 
		 // 设定背景透明度（0-1.0 之间
       	 plot.setBackgroundAlpha(0.9f); 
		 // 设定前景透明度（0-1.0 之间）
      	 plot.setForegroundAlpha(0.50f); 
		 return chart; 
	 } 
	 // 获取生成统计图的 Dataset  	
	 private DefaultPieDataset getDataSet() 
	 { 
		 DefaultPieDataset dataset = new DefaultPieDataset(); 
//		 dataset.setValue("Java ",47000); 
//		 dataset.setValue("Java EE",38000); 
//		 dataset.setValue(" Ajax ",31000); 
//		 dataset.setValue("Struts 2 权威指南",29000); 
//		 dataset.setValue("XML ",25000); 
		 List<Map<String,Object>> ctlist= orderService.getStaticOrderCompany(null);
		 for (Map<String, Object> map : ctlist) {			
			 Object c_count=map.get("c_count").toString();
			 int i_count=Integer.parseInt((String) c_count);
			 logger.info(map.get("i_count"));
			 Object c_name=map.get("c_name");
			 dataset.setValue((Comparable) c_name, i_count);
		   //dataset.setValue(c_name,i_count); 
		}
		 return dataset; 
	 } 
}
