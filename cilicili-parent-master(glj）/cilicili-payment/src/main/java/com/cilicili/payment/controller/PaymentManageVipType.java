package com.cilicili.payment.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cilicili.payment.domain.VipType;
import com.cilicili.payment.service.VipTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("PaymentManageVipType")
public class PaymentManageVipType {
	@Resource
	private VipTypeService vipTypeService;
	
	@RequestMapping("index")
	public String Index(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
		PageHelper.startPage(pageNum,5);
		List<VipType> vipTypeList = vipTypeService.findAll();
		PageInfo<VipType> pageInfo = new PageInfo<VipType>(vipTypeList);
		model.addAttribute("pageInfo", pageInfo);
		return "payment/manageVipType/index.html";
	}
	
	@RequestMapping("add")
	public String add(String VipName,String VipPrice,String VipDescribe, String VipTime,Model model) {
		if(VipName=="" || VipPrice=="" || VipTime=="") {//验证数据
			model.addAttribute("javascript","<script>alert('数据填写不完整，请重新填写!');location='index'</script>");
			return "payment/manageVipType/redirect.html";//数据不完整，不继续向下执行！ 
		}
		
		double double_VipTime=Integer.parseInt(VipTime)*30*24*60*60;
		VipType vipType=new VipType();
		vipType.setVipName(VipName);
		vipType.setVipPrice(Double.parseDouble(VipPrice));
		vipType.setVipTime(double_VipTime);
		vipType.setVipDescribe(VipDescribe);
		if(vipTypeService.add(vipType)==1) {
			model.addAttribute("javascript","<script>alert('添加成功!');location='index'</script>");
		}else {
			model.addAttribute("javascript","<script>alert('添加失败，请联系管理员。');location='index'</script>");
		}
		
		return "payment/manageVipType/redirect.html";
	}
	
	
	@RequestMapping("delete")
	public String delete(String VipID,Model model) {
		if(vipTypeService.delete(VipID)==1) {//等于1说明删除成功
			model.addAttribute("javascript","<script>alert('删除成功!');location='index'</script>");
		}else {
			model.addAttribute("javascript","<script>alert('删除失败!');location='index'</script>");
		}
		return "payment/manageVipType/redirect.html";
	}
	
	@RequestMapping("update")
	public String update(String VipID,Model model) {
		VipType vipType=vipTypeService.selectById(VipID);
		if(vipType==null) {
			model.addAttribute("javascript","<script>alert('没有该类型的Vip!');location='index'</script>");
			return "payment/manageVipType/redirect.html";
		}
		model.addAttribute("vipType", vipType);
		double double_VipTime=vipType.getVipTime()/(30*24*60*60);
		model.addAttribute("month", double_VipTime);
		return "payment/manageVipType/update.html";
	}
	
	@RequestMapping("update.do")
	public String updateDo(HttpServletRequest request, Model model) {
		int VipID=0;
		if(request.getParameter("VipID")!="") {
			VipID=Integer.parseInt(request.getParameter("VipID"));
		}
		
		String vipName = null;
		if(request.getParameter("VipName") != "") {
			vipName = request.getParameter("VipName");
		}
		double vipPrice = 0.00;
		if(request.getParameter("VipPrice") != "") {
			vipPrice = Double.parseDouble(request.getParameter("VipPrice"));
		}
		
		String VipDescribe=null;
		if(request.getParameter("VipDescribe")!="") {
			VipDescribe=request.getParameter("VipDescribe");
		}
		
		double VipTime=0.00;
		if(request.getParameter("VipTime")!="") {
			VipTime=Double.parseDouble(request.getParameter("VipTime"));
		}
		
		if(vipName==null ||vipPrice==0.00 ||VipTime==0.00 || VipID==0 ) {
			model.addAttribute("javascript","<script>alert('数据填写不完整!');location='index'</script>");
			return "payment/manageVipType/redirect.html";
		}
		
		VipType vipType=new VipType();
		vipType.setVipID(VipID);
		vipType.setVipDescribe(VipDescribe);
		vipType.setVipName(vipName);
		vipType.setVipPrice(vipPrice);
		VipTime=VipTime*30*24*60*60;
		vipType.setVipTime(VipTime);
		if(vipTypeService.updateDo(vipType)==1) {
			model.addAttribute("javascript","<script>alert('修改成功!');location='index'</script>");
			return "payment/manageVipType/redirect.html";
		}else {
			model.addAttribute("javascript","<script>alert('修改失败!');location='index'</script>");
			return "payment/manageVipType/redirect.html";
		}
	}
}
