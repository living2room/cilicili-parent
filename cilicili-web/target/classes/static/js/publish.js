$("input[name='url']").blur(function(){
	//alert($(this).val());
	var crr=$(this).val();
	if(crr=="")
		$(".text1").css({"display":"inline"});
	else
		$(".text1").css({"display":"none"});
});

$("input[name='imgSrc']").blur(function(){
	//alert($(this).val());
	var crr=$(this).val();
	if(crr=="")
		$(".text2").css({"display":"inline"});
	else
		$(".text2").css({"display":"none"});
});

$("input[name='picsfile']").blur(function(){
	//alert($(this).val());
	var crr=$(this).val();
	if(crr=="")
		$(".text3").css({"display":"inline"});
	else
		$(".text3").css({"display":"none"});
});
$("input[name='alt']").blur(function(){
	//alert($(this).val());
	var crr=$(this).val();
	if(crr=="")
		$(".text4").css({"display":"inline"});
	else
		$(".text4").css({"display":"none"});
});