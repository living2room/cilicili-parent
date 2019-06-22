package com.cilicili.advertisement.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cilicili.advertisement.domain.AdvertiseType;
import com.cilicili.advertisement.domain.ButtleAdv;
import com.cilicili.advertisement.domain.ProductofJson;
import com.cilicili.advertisement.service.AdvertiseTypeService;
import com.cilicili.advertisement.service.ButtleService;
import com.cilicili.common.dto.JqGridDto;


@Controller("buttleCon")
@RequestMapping("/buttle")
public class ButtleController {

	@Resource
	private ButtleService buttleService;
	
	@Resource
	private AdvertiseTypeService advertiseTypeService;
	
	
	@RequestMapping("upload")//解决
	public String intocenter(ButtleAdv buttleAdv,@RequestParam("picsfile") MultipartFile[] multipartFiles) {
		ModelAndView mav = new ModelAndView();
		Date date = new Date();
		try {
			for (MultipartFile multipartFile : multipartFiles) {
				System.out.println(multipartFile.getName());
				String fileName = multipartFile.getOriginalFilename();
				String realPath ="F:/Desktop/Eclipse_Workspace/cilicili-parent/cilicili-web/src/main/resources/static/image/";//"F:/static/upfile/"; /*request.getServletContext().getRealPath("/static/upfile/");*/
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//String random = UUID.randomUUID().toString().replace("-", "");
				String path = buttleAdv.getImgSrc()+ suffix;
				String fullName = realPath +path;
				System.out.println("最终会保存的文件的全名是:" + fullName);
				multipartFile.transferTo(new File(fullName));
				IOUtils.copy(new FileInputStream(new File(fullName)), new FileOutputStream(new File("F:/find/"+path)));
				buttleAdv.setImgSrc("/image/"+path);
			}
			buttleAdv.setCreateTime(new Timestamp(date.getTime()));
			buttleAdv.setStatus(1);
			buttleService.addAdvByOne(buttleAdv);;
			mav.addObject("message", "发布成功");
			return "advertisetype/table_jqgrid";
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("message", "发布失败");
		return "advertisement/publish";
	}
		//ButtleService buttleService1 = new ButtleService();
	
	@RequestMapping("into")
	public String intobuttle() {
		System.out.println("2222244444");
		ButtleAdv butt=new ButtleAdv();
		Date date = new Date();
		butt.setType(1);
		butt.setUrl("http:www.baidu.com");
		butt.setImgSrc("image/pic.png");
		butt.setAlt("测试用例");
		butt.setRemark("Remark");
		butt.setCreateTime(new Timestamp(date.getTime()));
		butt.setOperatorId(3);
		butt.setStatus(1);
		buttleService.addAdvByOne(butt);;
		return "hello";
	}
	
	@RequestMapping("buttleService")
	@ResponseBody

	public String ButtleService(@RequestBody List<ButtleAdv> itemList) {
		return "";
	}
	
	@RequestMapping("del")
	public String delbuttle() {
		
		buttleService.delAdvById(1);
		return "hello";
	}
	
	@RequestMapping("delmore")
	public ModelAndView delmorebuttle(HttpServletRequest request) {
		System.out.println("*******Buttle***********");
		ModelAndView mav = new ModelAndView();
		List<Integer> list = new ArrayList<Integer>();
		String items = request.getParameter("ids");// System.out.println(items);
		if(!items.equals(""))
		{
			
			String[] strs = items.split(",");
			System.out.println("接受的数据如下：");
			for (String string : strs) {
				System.out.println(string);
			}
			System.out.println("转变类型的数据如下：");
			for (int i = 0; i < strs.length; i++) {
				System.out.println(Integer.parseInt(strs[i]));
				list.add(Integer.parseInt(strs[i]));
			}
			buttleService.delMoreAdv(list);
			buttleService.selAdvAll();
		}
		mav.setViewName("advertisement/table_jqgrid");
		return mav;
	}
	
	@RequestMapping("selectall")
	@ResponseBody
	public String selectall(Model model,HttpServletRequest request,JqGridDto JqGridDto) {
		List<AdvertiseType> list = advertiseTypeService.selAdvAll(request);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(2*60*60);
		session.setAttribute("typelist", list);
		
		QueryWrapper<ButtleAdv> queryWrapper = new QueryWrapper<>();
		Page<ButtleAdv> page = new Page<ButtleAdv>(JqGridDto.getPage(), JqGridDto.getRows());
		int count = buttleService.selectCount();
		IPage<ButtleAdv> selAdvAll;
		if((!JqGridDto.getToCreateTime().equals("") && !JqGridDto.getToFinalTime().equals(""))
				&& (!JqGridDto.getToCreateTime().equals("undefined") && !JqGridDto.getToFinalTime().equals("undefined"))){//起始时间不为空，为条件查询，为空为全部查询
				queryWrapper.between("create_time", Timestamp.valueOf(JqGridDto.getToCreateTime()+" 00:00:00"), Timestamp.valueOf(JqGridDto.getToFinalTime()+" 24:00:00"));
				selAdvAll= buttleService.selAdvByMoreConByPage(page, queryWrapper);
			}
		else if(!JqGridDto.getToCreateTime().equals("")&&!JqGridDto.getToCreateTime().equals("undefined")) {
			queryWrapper.ge("create_time", Timestamp.valueOf(JqGridDto.getToCreateTime()+" 00:00:00"));
			selAdvAll= buttleService.selAdvByMoreConByPage(page, queryWrapper);
		}else
			selAdvAll= buttleService.selAdvByMoreConByPage(page, queryWrapper);
		
		ProductofJson productofJson = new ProductofJson();
//		model.addAttribute("advlist", selAdvAll);
		productofJson.setRecords(count);
		productofJson.setPage(JqGridDto.getPage());
		productofJson.setRows(selAdvAll.getRecords());
		int total = count / (int)selAdvAll.getSize();
		total = count % selAdvAll.getSize() == 0 ? total:total+1;
		productofJson.setTotal(total);
		
		System.out.println(selAdvAll.getRecords());
		String jsonString = JSON.toJSONString(productofJson);
		model.addAttribute("controller", "弹出广告位");
		return jsonString;
	}
	/*
	 * public String delmorebuttle() { List<Integer> list = new
	 * ArrayList<Integer>(); list.add(2); list.add(3); list.add(4);
	 * buttleService.delMoreAdv(list); return "hello"; }
	 */
	
	/*
	 * @RequestMapping("selectall") public String selectall() { List<ButtleAdv>
	 * selAdvAll = buttleService.selAdvAll(); for (ButtleAdv buttle_Adv : selAdvAll)
	 * { System.out.println(buttle_Adv); } return "hello"; }
	 */
	
	@RequestMapping("selectone")
	public String selectone() {
		ButtleAdv selAdvone = buttleService.selAdvById(5);
		System.out.println(selAdvone);
		return "hello";
	}
	
	@RequestMapping("tomodify")
	public String tomodify(Model model,int advid) {
		ButtleAdv selAdvone = buttleService.selAdvById(advid);
		model.addAttribute("name", "buttle");
		model.addAttribute("advmode", selAdvone);
		return "advertisement/update";
	}
	
	@RequestMapping("modify")
	public ModelAndView modifyone(ButtleAdv selAdvone,@RequestParam("picsfile") MultipartFile multipartFile) {
		ModelAndView mav = new ModelAndView();
		try {
			if(!multipartFile.isEmpty())
			{
				String fileName = multipartFile.getOriginalFilename();
				System.out.println("上传文件名全称"+fileName);
				String realPath ="F:/Desktop/Eclipse_Workspace/cilicili-parent/cilicili-web/src/main/resources/static/image/";//"F:/static/upfile/"; /*request.getServletContext().getRealPath("/static/upfile/");*/
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				//String random = UUID.randomUUID().toString().replace("-", "");
				String name = fileName.substring(fileName.lastIndexOf("/")+1,fileName.lastIndexOf("."));
				System.out.println("只有文件名称"+name);
				String path = name+ suffix;
				String fullName = realPath +path;
				System.out.println("最终会保存的文件的全名是:" + fullName);
				multipartFile.transferTo(new File(fullName));
				IOUtils.copy(new FileInputStream(new File(fullName)), new FileOutputStream(new File("F:/find/"+path)));
				selAdvone.setImgSrc("/image/"+path);
			}
			Date date = new Date();
			selAdvone.setUpdateTime(new Timestamp(date.getTime()));
			selAdvone.setOperatorId(3);
			buttleService.upAdvByOBJ(selAdvone);
			//将数据带回也页面
			List<ButtleAdv> selAdvAll = buttleService.selAdvAll();
			mav.addObject("advlist", selAdvAll);
			mav.addObject("controller", "弹出广告位");
			mav.setViewName("advertisement/table_jqgrid");
			return mav;
		} catch (Exception e) {
			
		}
		mav.setViewName("update");
		return mav;
	}
	
	//禁用/启用广告
		@RequestMapping("isUse")
			public ModelAndView isUse(int operator,int isNormal,int advid) {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
			ModelAndView mav = new ModelAndView();
			ButtleAdv selAdvone = buttleService.selAdvById(advid);
			selAdvone.setStatus(isNormal);
			buttleService.upAdvByOBJ(selAdvone);
			List<ButtleAdv> selAdvAll = buttleService.selAdvAll();
			mav.addObject("advlist", selAdvAll);
			mav.setViewName("advertisement/table_jqgrid");
			return mav;
			}
		
		
//		转成Excle文件
		@RequestMapping(value = "/outPutExcel",method = RequestMethod.GET)
	    @ResponseBody
	    public void outPutExcel(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
	        //一、从数据库拿数据
	        if (null == request || null == response){
	            return;
	        }
	        //根据request获得前台传递过来的数据
	        List<Integer> list = new ArrayList<Integer>();
	        List<ButtleAdv> centerlist = new ArrayList<ButtleAdv>();
	        String items = request.getParameter("ids");
	        String[] ids = items.split(",");
	        for (int i = 0; i < ids.length; i++) {
	        	System.out.println(Integer.parseInt(ids[i]));
	        	list.add(Integer.parseInt(ids[i]));
	        }
	        //根据前台的数据去数据库查出来自己要的数据
	        for(int i=0;i<list.size();i++) {
	        	centerlist.add(buttleService.selectmore(list.get(i)));
	        }        
	        //二、 数据转成excel
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/x-download");
			//定义excel的文件的名字
	        String fileName="产品列表.xlsx";
	        fileName= URLEncoder.encode(fileName, "UTF-8");
	        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

	        // 定义一个新的工作簿
	        XSSFWorkbook wb = new XSSFWorkbook();
	        // 创建一个Sheet页
	        XSSFSheet sheet = wb.createSheet("products");
	        sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
	        sheet.setColumnWidth(0, 4000);//设置列宽
	        sheet.setColumnWidth(1,4000);
	        sheet.setColumnWidth(2,4000);
	        sheet.setColumnWidth(3,4000);
	        sheet.setColumnWidth(11,4000);
	        sheet.setColumnWidth(12,4000);
	        sheet.setColumnWidth(13,4000);
	        XSSFFont font = wb.createFont();
	        font.setFontName("宋体");
	        font.setFontHeightInPoints((short) 16);
			//获得表格第一行
	        XSSFRow row = sheet.createRow(0);
			//根据需要给第一行每一列设置标题
	        XSSFCell cell = row.createCell(0);
	        cell.setCellValue("产品ID");

	        cell=row.createCell(1);
	        cell.setCellValue("链接");

	        cell=row.createCell(2);
	        cell.setCellValue("图片地址");

	        cell=row.createCell(3);
	        cell.setCellValue("图片描述");

	        cell=row.createCell(4);
	        cell.setCellValue("图片信息");

	        cell=row.createCell(5);
	        cell.setCellValue("发布时间");

	        cell=row.createCell(6);
	        cell.setCellValue("修改时间");

	        cell=row.createCell(7);
	        cell.setCellValue("发布人id");

	        cell=row.createCell(8);
	        cell.setCellValue("广告状态");


	        XSSFRow rows;
	        XSSFCell cells;
			//循环后台拿到的数据给所有行每一列设置对应的值
	        for (int i=0;i<list.size();i++){
	            // 在这个sheet页里创建一行
	            rows = sheet.createRow(i+1);
	            // 该行创建一个单元格,在该单元格里设置值
	            cells = rows.createCell(0);
	            cells.setCellValue(centerlist.get(i).getId());
	            cells = rows.createCell(1);
	            cells.setCellValue(centerlist.get(i).getUrl());
	            cells = rows.createCell(2);
	            cells.setCellValue(centerlist.get(i).getImgSrc());
	            cells = rows.createCell(3);
	            cells.setCellValue(centerlist.get(i).getAlt());
	            cells = rows.createCell(4);
	            cells.setCellValue(centerlist.get(i).getRemark());
	            
	            cells = rows.createCell(5);
	            Date date= (Date) centerlist.get(i).getCreateTime();
	            String strDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	            cells.setCellValue(strDate);
	            cells = rows.createCell(6);
	            if(centerlist.get(i).getUpdateTime()!=null) {
	            	Date date1= (Date) centerlist.get(i).getUpdateTime();
	                String strDate1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date1);
	                cells.setCellValue(strDate1);
	            }else
	            	cells.setCellValue("");
	            
	            cells = rows.createCell(7);
	            cells.setCellValue(centerlist.get(i).getOperatorId());
	            cells = rows.createCell(8);
	            cells.setCellValue(centerlist.get(i).getStatus());
	            //如果拿到的是一个时间戳，根据时间戳转换成string格式存放
	        
	        }
	        OutputStream out=null;
	        try {
	            out = response.getOutputStream();
	            wb.write(out);
	        }catch (IOException e){
	            e.printStackTrace();
	        }finally {
	            try {

	                out.close();
	                wb.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
