package com.cilicili.comment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cilicili.comment.domain.Comment;
import com.cilicili.comment.domain.Reply;
import com.cilicili.comment.service.CommentService;
import com.cilicili.comment.service.ReplyService;

/**
 * @author Hym
 *
 */

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
    private CommentService commentService;
	
	@Resource
    private ReplyService replyService;
	
	/*
	 * @Resource private UserService userService;
	 */

	@RequestMapping("showComment")
	public String showComment(@RequestParam(value="videoId")int videoId, Model model){
		System.out.println("videoId:"+videoId); 
		//获取该视频的所有评论信息
		Comment comment =commentService.findByVideoId(videoId);
		System.out.println("comment:"+comment);
		
		//获取在该内容下的所有评论
		List<Comment> commentList=commentService.findAll(); 
		System.out.println("commentList:"+commentList);
		
		
		//获取该视频的所有评论信息
		Reply reply =replyService.findByCommentId(comment.getCommentId());
		System.out.println("reply:"+reply);
				
		//获取在该内容下的所有回复
		List<Reply> replyList = replyService.findAll();
		System.out.println("replyList:"+replyList);
		
		//获取该评论的用户信息 
		//Userinfo userinfo=userinfoService.selectUser(content.getUserid()); 
		//User user = userService.		
		
		model.addAttribute("comment",comment);
		model.addAttribute("commentList",commentList);
		model.addAttribute("reply",reply);
		model.addAttribute("replyList",replyList);
		
		//model.addAttribute("user",userinfo);
		return "comment/showComment";

	}
	  
	
	
	/*
	 * 
	 * 
	 * @Autowired private VideoService videoService;
	 * 
	 * @Autowired private UserinfoService userinfoService;
	 * 
	 * @Autowired private CommentService commentService;
	 * 
	 * @Autowired private CommentReplyService commentReplyService;
	 * 
	 *//**
		 * 跳到评论页面
		 * 
		 * @param contentId
		 * @param model
		 * @return
		 */
	/*
	 * 
	 * @RequestMapping("showComment") public String
	 * showComment(@RequestParam(value="commentId")int commentId, Model model){
	 * System.out.println("commentId:"+commentId); //获取该内容的所有信息 Comment
	 * comment=commentService.findById(commentId); System.out.println(comment);
	 * //获取该内容的用户信息 // Userinfo
	 * userinfo=userinfoService.selectUser(content.getUserid()); //获取在该内容下的所有评论
	 * List<Comment> commentList=commentService.findAll(); //获取在该内容下的所有回复
	 * List<Reply> commentReplyList = commentReplyService.findAll();
	 * model.addAttribute("comment",comment);
	 * model.addAttribute("commentList",commentList);
	 * model.addAttribute("commentReplyList",commentReplyList);
	 * //model.addAttribute("user",userinfo); return "comment/showComment";
	 * 
	 * }
	 * 
	 *//**
		 * 增加评论
		 * 
		 * @param comment
		 * @return
		 */
	/*
	 * 
	 * @RequestMapping("/addComment") public String addComment(Comment comment){
	 * System.out.println("增加评论："+comment.toString()); if(comment.getContent()!=null
	 * && !comment.getContent().equals("")) { commentService.insert(comment); }
	 * return "redirect:/comment/showComment?commentId="+comment.getCommentId();
	 * //return "redirect:/user/comment?contentId="+comment.getcContentid(); }
	 * 
	 * 
	 * 
	 *//**
		 * 通过评论ID查看评论内容
		 * 
		 * @param commentId
		 * @return
		 */
	/*
	 * 
	 * @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.GET)
	 * public Comment getById(@PathVariable("commentId") int commentId) { return
	 * commentService.findById(commentId); }
	 * 
	 * 
	 * 
	 *//**
		 * 添加评论
		 * 
		 * @param comment
		 * @return
		 */
	/*
	 * @RequestMapping(value = "/comment", method = RequestMethod.POST) public
	 * Comment add(@RequestBody Comment comment) { commentService.insert(comment);
	 * return comment; }
	 * 
	 * 
	 * 
	 *//**
		 * 更新评论
		 * 
		 * @param commentId
		 * @param comment
		 * @return
		 */
	/*
	 * @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PUT)
	 * public Comment update(@PathVariable("commentId") int commentId, @RequestBody
	 * Comment comment) { comment.setCommentId(commentId);
	 * //commentService.updateById(commentId); return comment; }
	 * 
	 * 
	 * 
	 *//**
		 * 删除评论
		 * 
		 * @param commentId
		 *//*
			 * @RequestMapping(value = "/comment/{commentId}", method =
			 * RequestMethod.DELETE) public void delete(@PathVariable("commentId") int
			 * commentId) { commentService.deleteById(commentId); }
			 */
}
