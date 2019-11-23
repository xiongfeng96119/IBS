//扩展$本身的方法
$.extend({
	postJSON:function(url,data,fn){
		$.post(url,data,function(data){
			fn(data);
		},"json");
	}
});

//扩展$("#xxx")对象的方法：序列化表单字段为json对象
$.fn.toJson = function(){
   var arr = $(this).serializeArray();//form表单数据 name：value
     var param = {};
    $.each(arr,function(i,obj){ //将form表单数据封装成json对象
        param[obj.name] = obj.value;
    })
    return param;
}

function format(value,row,index){
    return value && value.name ? value.name : "";
}
function formatEmp(value,row,index){
    return value && value.username ? value.username : "";
}
function formatStatus(value,row,index){
    if (value == 1) return "<b style='color: #ed0c1e;'>待审</b>";
    else if (value == 2) return "<b style='color: #0b8502;'>已审</b>";
    if (value == 3) return "<b style='color: rgba(48,50,52,0.56);'>作废</b>";
}