package com.cilicili.payment.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cilicili.payment.domain.ManageOrders;
import com.cilicili.payment.mapper.ManageOrdersMapper;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
public class manageOrdersService {
	@Resource
	private ManageOrdersMapper manageOrdersMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<ManageOrders> findAll(){
		return manageOrdersMapper.findAll();
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public void DBToXls() {
	       try {
	            WritableWorkbook wwb = null;
	             
	               // 创建可写入的Excel工作簿
	               String fileName = "orders.xls";
	               File file=new File(fileName);
	               if (!file.exists()) {
	                   file.createNewFile();
	               }
	               //以fileName为文件名来创建一个Workbook
	               wwb = Workbook.createWorkbook(file);

	               // 创建工作表
	               WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
	               
	               //查询数据库中所有的数据
	               List<ManageOrders> list =findAll();
	               //要插入到的Excel表格的行号，默认从0开始
	               Label labelPaymentID= new Label(0, 0, "订单ID");//表示第
	               Label labelAlipayID= new Label(1, 0, "支付宝交易号");
	               Label labelCountOfMoney= new Label(2, 0, "金额");
	               Label labelUserID= new Label(3, 0, "用户ID");
	               Label labelVipName= new Label(4, 0, "vip名");
	               
	               ws.addCell(labelPaymentID);
	               ws.addCell(labelAlipayID);
	               ws.addCell(labelCountOfMoney);
	               ws.addCell(labelUserID);
	               ws.addCell(labelVipName);
	               
	               for (int i = 0; i < list.size(); i++) {
	                   
	                   Label labelPaymentID_i= new Label(0, i+1, Long.toString(list.get(i).getPayment_id()));
	                   Label labelAlipayID_i= new Label(1, i+1, list.get(i).getAlipay_id());
	                   Label labelCountOfMoney_i= new Label(2, i+1, Double.toString(list.get(i).getPrice()));
	                   Label labelUserID_i= new Label(3, i+1, list.get(i).getUser_id());
	                   Label labelVipName_i= new Label(4, i+1, list.get(i).getVip_name());
	                   
	                   ws.addCell(labelPaymentID_i);
	                   ws.addCell(labelAlipayID_i);
	                   ws.addCell(labelCountOfMoney_i);
	                   ws.addCell(labelUserID_i);
	                   ws.addCell(labelVipName_i);
	               }
	             
	              //写进文档
	               wwb.write();
	              // 关闭Excel工作簿对象
	               wwb.close();
	             
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	}
}
