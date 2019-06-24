/*
* @Author: name
* @Date:   2019-05-31 08:35:18
* @Last Modified by:   name
* @Last Modified time: 2019-05-31 11:01:50
*/
//滑动导航栏
function scroll_scroll(){
    $(window).scroll(function(event){
      var current = $(window).scrollTop()
      if (current < $(".con1").offset().top) {$(".ECode-floatBar").hide()}
      else if (current >= $(".clockimg").offset().top&&current < $(".middle3").offset().top) {$(".ECode-floatBar").show();$(".list li:eq(0)").addClass("on").siblings().removeClass("on")}
      else if (current >= $(".middle3").offset().top&&current < $(".middle4").offset().top){$(".ECode-floatBar").show();$(".list li:eq(1)").addClass("on").siblings().removeClass("on")}
      else if (current >= $(".middle4").offset().top&&current < $(".middle5").offset().top){$(".ECode-floatBar").show();$(".list li:eq(2)").addClass("on").siblings().removeClass("on")}
      else if (current >= $(".middle5").offset().top&&current < $(".middle6").offset().top){$(".ECode-floatBar").show();$(".list li:eq(3)").addClass("on").siblings().removeClass("on")}
      else if (current >= $(".middle6").offset().top&&current < $(".middle7").offset().top){$(".ECode-floatBar").show();$(".list li:eq(4)").addClass("on").siblings().removeClass("on")}
      else if (current >= $(".middle7").offset().top) {$(".ECode-floatBar").show();$(".list li:eq(5)").addClass("on").siblings().removeClass("on")}
      console.log(current,p1);
    // $(".list li:eq(0)").addClass("on").siblings().removeClass("on")
    });
}
//滑动导航栏_点击
function scroll_cilck(){
  $(".list li").click(function(){
    var index = $(this).index()
    if (index == 0) {var scroll_offset = $(".con1").offset().top;}
    else if (index == 1) {var scroll_offset = $(".middle3").offset().top;}
    else if (index == 2) {var scroll_offset = $(".middle4").offset().top;}
    else if (index == 3) {var scroll_offset = $(".middle5").offset().top;}
    else if (index == 4) {var scroll_offset = $(".middle6").offset().top;}
    else if (index == 5) {var scroll_offset = $(".middle7").offset().top;}
    $("body,html").stop().animate({
      scrollTop: scroll_offset
       }, 600);
    $(this).addClass("on").siblings().removeClass("on")
    console.log(index);
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