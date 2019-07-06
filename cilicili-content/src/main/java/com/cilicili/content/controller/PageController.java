package com.cilicili.content.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cilicili.advertisement.service.ButtleService;
import com.cilicili.advertisement.service.CenterService;
import com.cilicili.advertisement.service.LeftService;
import com.cilicili.advertisement.service.ReserveService;
import com.cilicili.advertisement.service.RightService;
import com.cilicili.common.dto.TvAdDto;
import com.cilicili.common.dto.TypeDto;
import com.cilicili.common.dto.VideoDetail;
import com.cilicili.common.dto.VideoReviewDto;
import com.cilicili.content.service.NotPassReasonService;
import com.cilicili.content.service.PageService;
import com.cilicili.content.service.RankService;
import com.cilicili.content.service.ReviewService;
import com.cilicili.content.service.TypeService;
import com.cilicili.content.service.VideoService;
import com.cilicili.domain.content.NotPassReason;
import com.cilicili.domain.content.Type;
/**
 * 页面导航的Controller
 * 
 * @author 李明睿
 *
 */
@Controller
@RequestMapping("/to")
public class PageController {
	@Resource
	private PageService pService;
	@Resource
	private RankService rService;
	@Resource 
	private TypeService tService;
	@Resource
	private ReviewService revService;
	@Resource
	private VideoService vService;

	@Resource
	private ButtleService buttleService;
	@Resource
	private CenterService centerService;
	@Resource
	private LeftService leftService;
	@Resource
	private ReserveService reserveService;
	@Resource
	private RightService rightService;

	@Resource
	private NotPassReasonService nprService;
	/**
	 * 去一级主页
	 * 
	 * @return
	 */
	@RequestMapping("home")
	public String toHomePage(Model model) {
		//拿到所有的Type
		List<TypeDto> typelist = new ArrayList<TypeDto>() ;
 		List<Type > vType = pService.getAllFirstType();
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		//把每个类型下的视频按照类型编号放进去
		for (int i = 0; i <= vType.size()-1; i++) {
			TypeDto typeDtof = new TypeDto();
			typeDtof.setType(vType.get(i));
			List<Type> secondTyps = pService.getItsSecondTyps(vType.get(i));
			List<TypeDto> typeDtolist = new ArrayList<TypeDto>();
			for (int j = 0; j <= secondTyps.size()-1; j++) {
				TypeDto typeDtos = new TypeDto();
				typeDtos.setType(secondTyps.get(j));
				typeDtolist.add(typeDtos);
			}
			typeDtof.setTypeDtolist(typeDtolist);
			typelist.add(typeDtof);
			List<TvAdDto> pageContent = pService.getPageContent(vType.get(i), 1, 4);
			System.out.println(pageContent);
			map.put(vType.get(i).getType(), pageContent);
		}
		//所有类型及其子类型
		model.addAttribute("PageType",typelist);
		
		//所有内容按类型封装
		model.addAttribute("PageContent", map);
		
		//System.out.println("PageType"+typelist);
		System.out.println("PageContent"+ map);
		return "content/index";
	}
	/**去上传界面
	 * @return
	 */
	@RequestMapping("up")
	public String toUpload() {
		return "content/upload";
	}
	/**去上传弹窗页面
	 * @return
	 */
	@RequestMapping("upPup")
	public String toUpload2() {
		return "content/upload2";
	}
	/**去填写视频信息页面
	 * @return
	 */
	@RequestMapping("upinfo/{id}")
	public String toUpInfo(@PathVariable("id") String videoInfoId, Model model) {
		List<Type> firsttypeList = tService.getAllTypeByRating(1);
		model.addAttribute("typeList", firsttypeList);
		return "content/videoInfo";
	}
	
	/**去后台主页
	 * @return
	 */
	@RequestMapping("b/index")
	public String toBIndex() {
		return "bg_index";
	}
	/**后台欢迎页
	 * @return
	 */
	@RequestMapping("b/contab")
	public String tocontab() {
		return "contab";
	}
	@RequestMapping("b/type")
	public String toTypeManager() {
		return "content/bg_typetree";
	}
	
	/**去视频审查页面
	 * @return
	 */
	@RequestMapping("b/review")
	public String toContentView() {
		
		return "content/bg_review";
	}
	/**去类型编辑弹窗
	 * @param text 类型名称
	 * @param model
	 * @return
	 */
	@RequestMapping("b/type/edit/{text}")
	public String toEditPup(@PathVariable String text,Model model) {
			Type type = tService.getTypeByName(text);
			if(type != null) {
				model.addAttribute("result",type);
			}else {
				model.addAttribute("result",0);
			}
		return "content/bg_typeedit";
	}
	/**去视频审查弹窗页
	 * @param text 视频Id
	 * @param model
	 * @return
	 */
	@RequestMapping("/b/review/edit/{text}")
	public String toReviewPup(@PathVariable String text, Model model) {
		VideoReviewDto vrDto = revService.getReviewInfo(text);
		System.out.println(""+vrDto);
		List<NotPassReason> nprlist = nprService.selectAll();
		System.out.println("###########################");
		for (NotPassReason notPassReason : nprlist) {
			System.out.println(notPassReason);
		}
		model.addAttribute("nprlist",nprlist);
		model.addAttribute("rinfo",vrDto);
		return "content/bg_reviewdetial";
	}
	/**IE支持页
	 * @return
	 */
	@RequestMapping("ie")
	public String toIE() {
		return "ie";
	}
//	 /**
//	 * 去二级主页
//	 *
//	 * @param url type的url
//	 * @return
//	 */
//	 @RequestMapping(value = "{url}", method = RequestMethod.GET)
//	 public String toSecHome(@PathVariable("url") String url) {
//		 
//	 return "";
//	 }
	
//	 /**
//	 * 去三级主页
//	 *
//	 * @param securl
//	 * @param thirdurl
//	 * @return
//	 */
//	 @RequestMapping(value = "{securl}/{thirdurl}", method =
//	 RequestMethod.GET)
//	 public String tothirdHome(@PathVariable("securl") String securl,
//	 @PathVariable("thirdurl") String thirdurl) {
//	 return "";
//	 }
	
	 /**
	 * 去某个视频页面
	 *
	 * @param video的request URL
	 * @return
	 */
	 @GetMapping("vi/{id}")
	 public String toVideoPage(Model model,@PathVariable("id") String videoPath) {
		 VideoDetail thisVideo = vService.getThisVideo(videoPath);
		 model.addAttribute("detail",thisVideo);
		 return "/content/video";
		}


//	 /**
//	 * 拿到远端IP地址
//	 * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值
//	 * X-Forwarded-For中第一个非unknown的有效IP字符串为用户的真实IP地址
//	 * @param request
//	 * @return
//	 */
//	 @RequestMapping("iptest")
//	 @ResponseBody
//	 public String ipTest(HttpServletRequest request) {
//	 String removeip = "";
//	 if (request.getHeader("x-forwarded-for") == null) {
//	 removeip = request.getRemoteAddr();
//	 String remoteHost = request.getRemoteHost();
//	 int remotePort = request.getRemotePort();
//	 String remoteUser = request.getRemoteUser();
//	 System.out.println(remoteHost+"+" + remotePort +" +" + remoteUser);
//	 }else {
//	 removeip = request.getHeader("x-forwarded-for");
//	 }
//	 return removeip;
//	 }

}
