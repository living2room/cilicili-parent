package com.cilicili.payment.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.domain.payment.ManageOrders;
import com.cilicili.payment.service.manageOrdersService;

@Controller
@RequestMapping("PaymentManageOrders")	
public class PaymentManageOrders {
	@Resource
	private manageOrdersService manageOrderService;

	@RequestMapping("findAll")
	public String findAll(Model model) {
		List<ManageOrders> manageOrdersList =manageOrderService.findAll();
		model.addAttribute("manageOrdersList", manageOrdersList);
		return "/payment/manageOrders/findAll";
	} 

	@RequestMapping("downloadXsl")
	public void downloadXsl( HttpServletResponse response) {
		manageOrderService.DBToXls();
		File file = new File("orders.xls");
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开            
			response.addHeader("Content-Disposition", "attachment;fileName=orders.xls");
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream outputStream = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					outputStream.write(buffer, 0, i);
					i = bis.read(buffer);
				}

				//return "下载成功";
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//return "e";

	}
}
