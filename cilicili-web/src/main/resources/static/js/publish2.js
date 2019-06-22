function batchDeletes(){ //判断至少写了一项 
	var checkedNum = $("input[name='subcheck']:checked").length; 
	if(checkedNum==0){ 
		alert("请至少选择一项!"); 
		return false; } 
	if(confirm("确定删除所选项目?")){ 
		var checkedList = new Array();
		$("input[name='subcheck']:checked").each(function(){ 
			checkedList.push($(this).val());
			}); 
		$.ajax({ 
			type:"POST", <!--批量删除的进入路径，batchDeletes.do--> 
			url:"batchDeletes.do", 
			data:{"delitems":checkedList.toString()}, 
			datatype:"html", 
			success:function(data){ 
				$("[name='checkbox2']:checkbox").attr("checked",false); 
				location.reload();//页面刷新 
				},
			error:function(data){
				art.dialog.tips('删除失败!'); } });
	} }