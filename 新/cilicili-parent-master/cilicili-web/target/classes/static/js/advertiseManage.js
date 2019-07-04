/*批量选中的效果*/
$('#selall').click(function(){
	if($(this).is(':checked')){
		$('input:checkbox').each(function(){
			$(this).prop("checked",true);
		});
	}else{
		$('input:checkbox').each(function(){
			$(this).prop("checked",false);
		});
	}
});

/*获取ids,批量删除*/
$(".all").click(function(){
	var ids = '';
	$('input:checkbox').each(function(){
		if(this.checked == true){
			ids += this.value + ',';
		}
	});
	alert(ids);
	var addr=advertise.value;
	//console.log(confirm("dd"));
	//下面的ajax根据自己的情况写
	var b = confirm("批量删除后不可恢复，谨慎操作!");
	if(b){
		$.ajax({
			type: 'POST',
			url: 'delmore',
			data: {"ids": ids},
			dataType: 'html',
			success: function (data) {
				location.reload();//页面刷新 
			},
			error: function (data) {
				console.log(data.msg);
			},
		});
	}
});