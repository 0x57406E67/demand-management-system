package com.demo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.model.TAnaylze;
import com.demo.model.TAttach;
import com.demo.model.TConfirm;
import com.demo.model.TOrder;
import com.demo.model.TUser;
import com.demo.service.AnaylzeService;
import com.demo.service.AttachService;
import com.demo.service.ConfirmService;
import com.demo.service.OrderService;
import com.demo.util.Constants;
import com.demo.util.Tools;

/**
 * 需求确认控制器
 * @author 
 *
 */
@Results({
	@Result(name="add_confirm",location="/WEB-INF/jsp/addConfirm.jsp"),
	
})
@Namespace("/confirm")
@Controller
public class ConfirmAction {
	Logger logger=Logger.getLogger(AnaylzeAction.class);
	@Autowired
	AnaylzeService anaylzeService;
	@Autowired
	OrderService orderService;
	@Autowired
	AttachService attachService;
	@Autowired
	ConfirmService confirmService;
	
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	

	private File[] file;              //文件    
	private String[] fileFileName;    //文件名     
	private String[] filePath;        //文件路径
	
	private File[] file2;              //文件    
	private String[] fileFileName2;    //文件名     
	private String[] filePath2;        //文件路径
	/**
	 * 跳转添加需求确认页面
	 * @return
	 */
	@Action(value="to_add_confirm")
	public String to_add_confirm(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String order_id=request.getParameter("order_id");
		if (Tools.notEmpty(order_id)) {
			Integer i_order_id=Integer.parseInt(order_id);
			//获取工单需求信息
			TOrder order=orderService.getOrder(i_order_id);
			request.setAttribute("order", order);
		}
		return "add_confirm";
	}
	/**
	 * 添加需求确认
	 * @return
	 */
	@Action(value="add_confirm")
	public void add_confirm(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8") ; 
		PrintWriter out=null;
		String order_id=request.getParameter("order_id");
		String predict_time=request.getParameter("predict_time");
		String pre_work=request.getParameter("pre_work");
		String init_work=request.getParameter("init_work");
		TOrder order = new TOrder();
		order.setId(Integer.parseInt(order_id));;
		order.setId(Integer.parseInt(order_id));
		TConfirm confirm=new TConfirm();
		confirm.setInitWork(init_work);
		confirm.setPredictTime(Tools.strToDate(predict_time));
		confirm.setPreWork(pre_work);
		confirm.setTOrder(order);
		
		TAttach attach1=new TAttach();
		TAttach attach2=new TAttach();
		//多文件上传---》 开始
		String url="";
		MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) request;
		Enumeration fileParameterNames = multiWrapper.getFileParameterNames();
		int attach_index=0;
		while (fileParameterNames != null && fileParameterNames.hasMoreElements()) {  
			attach_index++;
			String inputName = (String) fileParameterNames.nextElement();  
			System.out.println(inputName);
			String[] contentType = multiWrapper.getContentTypes(inputName);
			if (contentType!=null) {
				String[] fileName = multiWrapper.getFileNames(inputName);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String fName=sdf.format(new Date());
				String storageFileName = fName + fileName[0].substring(fileName[0].lastIndexOf("."));
				url = "/upload";
				String full_url = ServletActionContext.getServletContext().getRealPath("/upload")+ "/";
				File storageFile = new File(full_url + storageFileName);
				File[] files = multiWrapper.getFiles(inputName);
				copy(files[0], storageFile);
				//设置attach1
				if(attach_index==1){
					attach1.setAttachName(fileName[0]);
					attach1.setServerName(storageFileName);
					attach1.setTOrder(order);
					attach1.setStatus(Constants.ORDER_CONFIRM);
					attach1.setTag(Constants.ATTACH_ORDER_CONFIRM);
				}
				//设置attach2
				else if(attach_index==2){
					attach2.setAttachName(fileName[0]);
					attach2.setServerName(storageFileName);
					attach2.setTOrder(order);
					attach2.setStatus(Constants.ORDER_CONFIRM);
					attach2.setTag(Constants.ATTACH_WORK_PREDICT);
				}
			}
			
		}
		//多文件上传---》 结束
		try {
			out=response.getWriter();
			if (Tools.notEmpty(order_id)) {
				//入库
				confirmService.addConfirm(confirm, attach1, attach2);
				out.print("{\"result\":\"success\"}");
				logger.info("添加成功");
			}
		} catch (NumberFormatException e) {
			out.print("{\"result\":\"fail\"}");
			e.printStackTrace();
		} catch (IOException e) {
			out.print("{\"result\":\"fail\"}");
			e.printStackTrace();
		}
		finally{
			out.close();
		}
	}
	/**
	 * 提交审核
	 */
	@Action(value="submit_audit")
	public void submit_audit(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8") ; 
		PrintWriter out=null;
		String order_id=request.getParameter("order_id");
		try {
			out=response.getWriter();
			if (Tools.notEmpty(order_id)) {
				TOrder order = new TOrder();
				order.setId(Integer.parseInt(order_id));
				order.setStatus(Constants.ORDER_AUDIT);
				orderService.updateOrderStatus(order);
				out.print("{\"result\":\"success\"}");
			}
		} catch (NumberFormatException e) {
			out.print("{\"result\":\"fail\"}");
			e.printStackTrace();
		} catch (IOException e) {
			out.print("{\"result\":\"fail\"}");
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	

	public void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	public File[] getFile() {
		return file;
	}
	public void setFile(File[] file) {
		this.file = file;
	}
	public String[] getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String[] getFilePath() {
		return filePath;
	}
	public void setFilePath(String[] filePath) {
		this.filePath = filePath;
	}

	public File[] getFile2() {
		return file2;
	}
	public void setFile2(File[] file2) {
		this.file2 = file2;
	}
	public String[] getFileFileName2() {
		return fileFileName2;
	}
	public void setFileFileName2(String[] fileFileName2) {
		this.fileFileName2 = fileFileName2;
	}
	public String[] getFilePath2() {
		return filePath2;
	}
	public void setFilePath2(String[] filePath2) {
		this.filePath2 = filePath2;
	}
}
