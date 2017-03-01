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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.model.TAttach;
import com.demo.model.TCompany;
import com.demo.model.TOrder;
import com.demo.model.TUser;
import com.demo.service.AttachService;
import com.demo.service.OrderService;
import com.demo.util.Constants;
import com.demo.util.HibernateProxyTypeAdapter;
import com.demo.util.PageBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * 工单需求控制器
 * @author 
 *
 */
@Results({
	@Result(name="to_login",location="/login.jsp"),
	@Result(name="addOrder",location="/WEB-INF/jsp/addOrder.jsp"),
	@Result(name="list",location="/WEB-INF/jsp/listOrder.jsp"),
	@Result(name="list1",location="/WEB-INF/jsp/myOrder.jsp"),
	@Result(name="left",location="/WEB-INF/jsp/left.jsp"),
})
@Namespace("/order")
@ParentPackage("all")
@InterceptorRefs({
    @InterceptorRef("loginInterceptor"),
    @InterceptorRef("defaultStack")
})
@Controller
public class OrderAction {
	Logger logger=Logger.getLogger(OrderAction.class);
	@Autowired
	OrderService orderServiceImpl;
	@Autowired
	AttachService attachService;
	private TOrder orderparm;
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 16 * 1024;
	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String contentType;
	// 封装上传文件名的属性
	private String fileName;
	private String storageFileName;
    private int page;
    private String userid;
    private PageBean pageBean;
    private PrintWriter out;
	/**
	 * 跳转添加工单需求
	 * @return
	 */
	@Action(value="to_add_order")
	public String to_add_order(){
		return "addOrder";
	}
	/**
	 * 访问工单需求列表
	 */
	@Action(value="listOrder")
	public String listOrder(){
		//HttpServletRequest request=ServletActionContext.getRequest();
		//获取列表数据
		this.pageBean=orderServiceImpl.getOrderList(null,10, page);
		return "list";
		
	}
	/**
	 *访问个人需求列表〃
	 */
	@Action(value="my_Order")
	public String myorder(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("uersname");
		List<TOrder> orderList=orderServiceImpl.getmyOrderList(userid);
		request.setAttribute("orderList", orderList);
		return "list1";
		
	}
	/**
	 *璁块棶宸ュ崟闇�眰鍒楄〃()
	 */
	@Action(value="listOrder2")
	public String listOrder2(){
		return "list2";
	}
	/**
	 * 工单需求列表(Ajax方式)
	 */
	@Action(value="listOrderAjax")
	public String listOrderAjax(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();		
		response.setCharacterEncoding("UTF-8");
		String jsonStr=request.getParameter("parm");
		String date_end = request.getParameter("date_end");	
		if(request.getParameter("page") == null){
			page = 1;
		}else{
			page = Integer.parseInt(request.getParameter("page"));
		}
		logger.info(jsonStr);

		try {
				out=response.getWriter();
				logger.info(jsonStr);
				//解析JSON串
				//Gson gson = new GsonBuilder().registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY).create();
				Gson gson = new GsonBuilder().create();
				orderparm=gson.fromJson(jsonStr, TOrder.class);
				//获取列表数据
				this.pageBean = orderServiceImpl.getOrderListJson(orderparm,10,page,date_end);
				String jsonList = pageBean.getJsonList();
				logger.info(jsonList);
				out.print("{\"list\":"+jsonList+",\"totalPage\":\""+pageBean.getTotalPage()+"\",\"allRow\":\""+pageBean.getAllRow()+"\",\"currentPage\":\""+pageBean.getCurrentPage()+"\"}");
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			out.close();
		}
		System.out.println("\n鎵ц缁撴潫");
		return "list";
	}
	/**
	 * 左侧菜单显示
	 */
	@Action(value="left")
	public String to_left(){
		return "left";
	}
	
	/**
	 * 添加工单需求(入库)
	 * 
	 * @return
	 */
	@Action(value = "add_order_ajax")
	public void add_order_ajax() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out = null;
		// 获取登录信息
		TUser loginUser = (TUser) session
				.getAttribute(Constants.CURRENT_LOGIN_USER);
		logger.info("add Order begin---------------------->");
		String jsonParm = request.getParameter("parm");
		logger.info("jsonParm=" + jsonParm);
		Gson gson = new GsonBuilder().create();
		TOrder order = gson.fromJson(jsonParm, TOrder.class);
		TCompany company = new TCompany();
		company.setId(order.getCompany_id());
		order.setCompany(company);
		order.setStatus(Constants.ADD_ORDER);//新建需求状态
		order.setOrderCreateMan(loginUser.getId());//登录用户的user_id
		try {
			out = response.getWriter();
			//添加需求工单
			orderServiceImpl.addOrder(order);
			//循环回填附件表 Order_id
			String attach_ids = request.getParameter("attach_ids");
			if (attach_ids!=null && !attach_ids.equals("")) {
				String[] attach_id_arr = attach_ids.split(",");
				for (String attach_id : attach_id_arr) {
					Integer int_attach_id = Integer.parseInt(attach_id);
					attachService.backFillOrderId(int_attach_id, order.getId());
				}
			}
			logger.info("娣诲姞鎴愬姛");
			out.print("{\"result\":\"success\",\"order_id\":\"" + order.getId()
					+ "\"}");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"result\":\"fail\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"result\":\"fail\"}");
		} finally {
			out.close();
		}

		// return "list";
	}
	/**
	 *修改工单需求
	 * @return
	 */
	@Action(value="update_order_ajax")
	public void update_order_ajax(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out=null;
		//获取登录信息
		TUser loginUser=(TUser)session.getAttribute(Constants.CURRENT_LOGIN_USER);
		logger.info("update Order begin---------------------->");
		String jsonParm=request.getParameter("parm");
		logger.info("jsonParm="+jsonParm);
		Gson gson = new GsonBuilder().create();
		TOrder order=gson.fromJson(jsonParm, TOrder.class);
		
		try {
			out=response.getWriter();
			//修改需求工单状态
			orderServiceImpl.updateOrderStatus(order);
			logger.info("工单状态修改成功");
			out.print("{\"result\":\"success\",\"order_id\":\""+order.getId()+"\"}");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"result\":\"fail\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("{\"result\":\"fail\"}");
		}
		finally{
			out.close();
		}
		
		//return "list";
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
	@Action(value="orderAttach")
	public void orderAttach() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8") ;  
		HttpSession session=request.getSession();
		PrintWriter out=null;
		try {
			out=response.getWriter();
			//上传文件------------->
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
			storageFileName =sdf.format(new Date()) + getExtention(fileName);
			//storageFileName = fileName;
			String url="/upload";
			String full_url=ServletActionContext.getServletContext().getRealPath("/upload") + "/";
			File storageFile = new File(full_url + storageFileName);
			copy(upload, storageFile);
			//上传文件-------------<
			
			//文件信息入库
			TAttach attach=new TAttach();
			attach.setAttachName(fileName);
			attach.setServerName(storageFileName);
			attach.setUrl(url);
			attach.setStatus(Constants.ADD_ORDER);
			//获取登录信息
			TUser loginUser=(TUser)session.getAttribute(Constants.CURRENT_LOGIN_USER);
			//attach.setUploadMan(loginUser.getId()); 
			attachService.addAttach(attach);
			logger.info("{\"result\":\"success\",\"attach_id\":\""+attach.getId()+"\",\"attachName\":\""+fileName+"\",\"url\":\""+url+"\"}");
			out.print("{\"result\":\"success\",\"attach_id\":\""+attach.getId()+"\",\"attachName\":\""+fileName+"\",\"url\":\""+url+"\"}");
		} catch (Exception e) {
			out.print("{\"result\":\"fail\"}");
			e.printStackTrace();
		}
		finally{
			out.close();
		}
		

	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
}
