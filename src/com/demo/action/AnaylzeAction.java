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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.TAnaylze;
import com.demo.model.TAttach;
import com.demo.model.TOrder;
import com.demo.model.TUser;
import com.demo.service.AnaylzeService;
import com.demo.service.AttachService;
import com.demo.service.OrderService;
import com.demo.util.Constants;
import com.demo.util.Tools;

/**
 * 需求分析控制器
 * @author 
 *
 */
@Results({
	@Result(name="add_anaylze",location="/WEB-INF/jsp/addAnaylze.jsp"),
	
})
@Namespace("/anaylze")
public class AnaylzeAction {
	Logger logger=Logger.getLogger(AnaylzeAction.class);
	@Autowired
	AnaylzeService anaylzeService;
	@Autowired
	OrderService orderService;
	@Autowired
	AttachService attachService;
	
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String contentType;
	// 封装上传文件名的属性
	private String fileName;
	private String storageFileName;
	/**
	 * 跳转添加需求分析页面
	 * @return
	 */
	@Action(value="to_add_anaylze")
	public String to_add_anaylze(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String order_id=request.getParameter("order_id");
		if (Tools.notEmpty(order_id)) {
			Integer i_order_id=Integer.parseInt(order_id);
			//获取工单需求信息
			TOrder order=orderService.getOrder(i_order_id);
			request.setAttribute("order", order);
		}
		return "add_anaylze";
	}
	/**
	 * 添加需求分析
	 * @return
	 */
	@Action(value="add_anaylze")
	public void add_anaylze(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8") ; 
		PrintWriter out=null;
		String order_id=request.getParameter("order_id");
		String opinion=request.getParameter("opinion");
		TOrder order = new TOrder();
		order.setId(Integer.parseInt(order_id));
		try {
			out=response.getWriter();
			if (Tools.notEmpty(order_id)) {
				//1-上传附件（需求分析文档）
				//上传文件------------->
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
				storageFileName =sdf.format(new Date()) + getExtention(fileName);
				//storageFileName = fileName;
				String url="/upload";
				String full_url=ServletActionContext.getServletContext().getRealPath("/upload") + "/";
				File storageFile = new File(full_url + storageFileName);
				copy(upload, storageFile);
				//上传文件-------------<
				//2-附件表写入
				//文件信息入库
				TAttach attach=new TAttach();
				attach.setAttachName(fileName);
				attach.setServerName(storageFileName);
				attach.setUrl(url);
				attach.setTOrder(order);
				attach.setStatus(Constants.ORDER_ANALYSE);
				//获取登录信息
				//TUser loginUser=(TUser)session.getAttribute(Constants.CURRENT_LOGIN_USER);
				//attach.setUploadMan(loginUser.getId()); 
				attachService.addAttach(attach);
				//3-需求分析表的信息入库
				TAnaylze anaylze = new TAnaylze();
				
				anaylze.setTOrder(order);
				anaylze.setOpinion(opinion);
				anaylzeService.addAnaylze(anaylze);
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
	 * 提交需求确认
	 * @return
	 */
	@Action(value="submit_confirm")
	public void submit_confirm(){
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
				order.setStatus(Constants.ORDER_CONFIRM);
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
	// private String storagePath;
	// since we are using <s:file name="upload" ... /> the File itself will be
	// obtained through getter/setter of <file-tag-name>
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	// since we are using <s:file name="upload" .../> the file name will be
	// obtained through getter/setter of <file-tag-name>FileName
	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStorageFileName() {
		return storageFileName;
	}

	public void setStorageFileName(String storageFileName) {
		this.storageFileName = storageFileName;
	}

	// since we are using <s:file name="upload" ... /> the content type will be
	// obtained through getter/setter of <file-tag-name>ContentType
	public String getUploadContentType() {
		return contentType;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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
}
