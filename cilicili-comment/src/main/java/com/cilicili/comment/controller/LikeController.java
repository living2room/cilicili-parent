package com.cilicili.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cilicili.comment.domain.Comment;
import com.cilicili.comment.domain.Like;
import com.cilicili.comment.service.CommentService;
import com.cilicili.comment.service.LikeService;


@Controller
@RequestMapping("/")
public class LikeController {

}
	/*
	@Autowired
	private LikeCountService likeCountService;
	
	@Autowired
	private CommentService commentService;
	
	*//**
	 * 统计点赞数
	 * @param like
	 * @return 
	 *//*
	@RequestMapping("/likeCount")
	@ResponseBody
	public String Like(Like like) {
		
		 System.out.println("点赞模块："+like.toString());
	        //查询是否有该用户的点赞记录
		 Like likeCount1=likeCountService.findById(like.getLikeCountId());
	        int result=0;
	        if (likeCount1!=null){
	            System.out.println("有该记录");
	            //如果找到这条记录，删除该记录，同时文章的点赞数减一
	            //删除记录
	            likeCountService.deleteById(likeCount1.getLikeCountId());
	            //根据点赞id找到文章
	            Comment comment=commentService.findById(likeCount1.getLikeCountId());
	            //文章点赞数减一
	            comment.setLikeCount(comment.getLikeCount()-1);
	            result=comment.getLikeCount();
	            System.out.println("result:"+result);
	            //更新文章点赞数
	            commentService.updateById(comment);
	        }else{
	            //如果没有找到这条记录，则添加这条记录，同时文章数加一；
	            //添加记录
	            System.out.println("没有记录：");
	            likeCountService.insert(like);
	            Comment comment=commentService.findById(likeCount1.getLikeCountId());
	            //文章点赞数加一
	            comment.setLikeCount(comment.getLikeCount()+1);
	            result=comment.getLikeCount();
	            System.out.println("res:"+result);
	            //更新文章点赞数
	            commentService.updateById(comment);
	        }
	        return ""+result;
	}
	
}


 * @Controller
@RequestMapping("/user")
public class NiceDetailController {
 
    @Autowired
    private NiceDetailService  niceDetailService;
 
    @Autowired
    private ContentService contentService;
 
    @RequestMapping("/niceDetail")
    @ResponseBody
    public String niceDetail(NiceDetail niceDetail ){
        System.out.println("点赞模块："+niceDetail.toString());
        //查询是否有该用户的点赞记录
        NiceDetail niceDetail1=niceDetailService.findNiceDetail(niceDetail);
        int result=0;
        if (niceDetail1!=null){
            System.out.println("有该记录");
            //如果找到这条记录，删除该记录，同时文章的点赞数减一
            //删除记录
            niceDetailService.deleteNiceDetail(niceDetail1.getId());
            //根据点赞id找到文章
            Content content=contentService.selectContent(niceDetail1.getContentid());
            //文章点赞数减一
            content.setNice(content.getNice()-1);
            result=content.getNice();
            System.out.println("res:"+result);
            //更新文章点赞数
            contentService.updateContent(content);
        }else{
            //如果没有找到这条记录，则添加这条记录，同时文章数加一；
            //添加记录
            System.out.println("没有记录：");
            niceDetailService.insertNiceDetail(niceDetail);
            Content content=contentService.selectContent(niceDetail.getContentid());
            //文章点赞数加一
            content.setNice(content.getNice()+1);
            result=content.getNice();
            System.out.println("res:"+result);
            //更新文章点赞数
            contentService.updateContent(content);
        }
        return ""+result;
    }
 
}

 * 
 * 
 * -- ----------------------------
-- Table structure for `nicedetail`
-- ----------------------------
DROP TABLE IF EXISTS `nicedetail`;
CREATE TABLE `nicedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点赞id,自增,主键',
  `userid` varchar(50) DEFAULT NULL COMMENT '所点赞的用户',
  `contentid` int(11) DEFAULT NULL COMMENT '用户所点赞说说的id',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间,默认为入库时间',
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `contentid` (`contentid`),
  CONSTRAINT `nicedetail_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`),
  CONSTRAINT `nicedetail_ibfk_2` FOREIGN KEY (`contentid`) REFERENCES `content` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1141 DEFAULT CHARSET=utf8;

原文：https://blog.csdn.net/weixin_39220472/article/details/80974642 
 * 
 * */
