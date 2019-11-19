$.extend({
	postJSON : function(url, params, fn){
		$.post(url, params, function(data){
			fn(data);
		},"json");
	}
});
$.fn.extend({
	toJson: function(){
		var paramArray = $(this).serializeArray();  
        /*请求参数转json对象*/
        var jsonObj = {}; 
        //each是jQuery的foreach循环
        $(paramArray).each(function(){  
        	jsonObj[this.name] = this.value;  
        });  
        // json对象
        return jsonObj;
	}
});
//验证扩展：用户名是否可以重复使用
$.extend($.fn.validatebox.defaults.rules, {
	checkUsername: {
		validator: function(value,param){
			var employeeId = $("#employeeId").val();
			var result = $.ajax({
				type: "GET",
				url: "/employee/checkUsername",
				data:{username:value,id:employeeId},
				async: false
			}).responseText;
			return result=="true";
		},
		message: '用户名不能重复.'
	}
});

function format(value,row,index){
	return value && value.name ? value.name : "";
};
function formatEmp(value,row,index){
	return value && value.username ? value.username : "";
};
$(function () {
	$(document).bind('keypress','Alt+del',function () {
		$.messager.confirm('确认','想要删除吗？',function(r){
			if (r){
				var selectedRows = $("#dg").datagrid("getSelections");
				if(selectedRows.length == 0){
					$.messager.alert("错误信息","请选择要删除的数据！","error");
					return;
				}
				//拼接成字符串
				var ids = [];
				for(var i=0;i<selectedRows.length;i++){
					ids.push(selectedRows[i].id);
				}
				//发送请求进行删除
				$.postJSON("/product/delete",{ids:ids.join(",")},function(data){
					$.messager.alert('信息',data.msg,"info");
					if(data.status == 200){
						//刷新表格数据
						$("#dg").datagrid('load',{pageNo:1,pageSize:10})
					}
				});
			}
		});
	});
})
