/*
* @Author: name
* @Date:   2019-05-31 08:35:18
* @Last Modified by:   name
* @Last Modified time: 2019-06-27 11:39:13
*/
//滑动导航栏
function scroll_scroll(){
    $(window).scroll(function(event){
      var current = $(window).scrollTop()
      var conNum= $(".con3").index()
      for (var i = 0; i <= conNum; i++) {
        if (current < $(".con1").offset().top) {$(".ECode-floatBar").hide()}
        else if (current == $(".con1").offset().top) {$(".ECode-floatBar").show();$(".list li:eq("+i+")").addClass("on").siblings().removeClass("on")}
        else if (current >= $(".con3:eq("+i+")").offset().top&&current < $(".con3:eq("+(i+1)+")").offset().top)
          {$(".ECode-floatBar").show();$(".list li:eq("+i+")").addClass("on").siblings().removeClass("on")}
        else if(current >= $(".con3:eq("+conNum+")").offset().top)
          {$(".ECode-floatBar").show();$(".list li:eq("+conNum+")").addClass("on").siblings().removeClass("on")}
      };
    // $(".list li:eq(0)").addClass("on").siblings().removeClass("on")
    });
}
//滑动导航栏_点击
function scroll_cilck(){
  $(".list li").click(function(){
    var index = $(this).index()
    var conNum= $(".con3").index()
    for (var i = 0; i <= conNum; i++) {if (index == i) {var scroll_offset = $(".con3:eq("+i+")").offset().top;}
    };

    $("body,html").stop().animate({
      scrollTop: scroll_offset
       }, 600);
    $(this).addClass("on").siblings().removeClass("on")
  });
}
// 滚动
 function movedome(){

  var stop=false;
  $(".middle1-3-1").hover(function(){
       $(this).css("cursor","pointer");
          stop=true;
          // console.log(stop+"1");
       },function(){
           stop=false;
           // console.log(stop +"2");
       })
  // console.log(stop+"3");

      var margintop=0;//上边距的偏移量
      setInterval(function(){
        // console.log(stop+"4");
         if(stop==true){
             return;
         }
         $(".middle1-3-1").children("p").first().animate({"margin-top":margintop--},0,function(){
            var $li=$(this);
            if(!$li.is(":animated")){
              if(-margintop>$li.height()){
                 $li.css("margin-top","0px").appendTo($(".middle1-3-1"));
                 margintop=0;
              }
            }
         });
      },100);
    }