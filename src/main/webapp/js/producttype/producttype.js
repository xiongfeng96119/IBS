$(function(){
    $("#dg").datagrid({
        striped:true,
        rownumbers:true,
        pagination:true,
        url:'/producttype/page',
        method:"POST",
        toolbar:'#toolbar',
        frozenColumns:[[{field:'bbbb',checkbox:true}]],
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'类型名称',width:100},
            {field:'descs',title:'描述',width:100},
            {field:'parent',title:'父级类型',width:100,formatter:format},
        ]]
    });
    //翻页功能
    $("#dg").datagrid("getPager").pagination({
        onSelectPage:function(pageNumber, pageSize){
            $("#pageNo").val(pageNumber);
            $("#pageSize").val(pageSize);
            $("#dg").datagrid('loading');
            $("#dg").datagrid('load',$("#searchForm").toJson());
            $(this).pagination({pageNumber:pageNumber,pageSize:pageSize});
            $("#dg").datagrid('loaded');
        }
    });
    //给所有有data-method属性的按钮绑定点击事件
    $("*[data-method]").click(function() {
        //获取当前点击的按钮的data-method属性值，把他当成一个方法名称使用
        var methodName = $(this).attr("data-method");
        window.methods[methodName]();
    });
});
window.methods = {
    search:function () {
        $("#dg").datagrid('loading');
        $("#dg").datagrid('load',$("#searchForm").toJson());
        $("#dg").datagrid("getPager").pagination({pageNumber:1,pageSize:10});
        $("#dg").datagrid('loaded');
    },
    remove:function () {
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
                $.postJSON("/producttype/delete",{ids:ids.join(",")},function(data){
                    $.messager.alert('信息',data.msg,"info");
                    if(data.status == 200){
                        //刷新表格数据
                        $("#dg").datagrid('load',{pageNo:1,pageSize:10})
                    }
                });
            }
        });
    },
    edit:function () {
        var selectedRows = $("#dg").datagrid("getSelections");
        if(selectedRows.length == 0){
            $.messager.alert("错误信息","请选择要修改的数据！","error");
            return;
        }
        if(selectedRows.length > 1){
            $.messager.alert("错误信息","一次只能修改一行！","error");
            return;
        }
        $("#menu").form("reset");
        $.getJSON("/producttype/findOne",{id:selectedRows[0].id},function(data){
            $("#menu").form("load", data);
            $("#parentId").combobox("setValue",data.parent.id);
            //打开模态框
            $('#dd').dialog('open');
        });
    },
    add:function () {
        $("#menu").form("reset");
        $("#producttypeid").val("");
        $('#dd').dialog('open');  // open a window
    },
    save:function () {
        var params = $("#menu").toJson();
        var url = "/producttype/save";
        if($("#producttypeid").val()) {
            url = "/producttype/update";
            params.action = "update";
        }
        $.postJSON(url,params,function(data){
            $.messager.alert('信息',data.msg,"info");
            if(data.status == 200){
                $("#dg").datagrid('load',{pageNo:1,pageSize:10})
                $('#dd').dialog('close');
            }
        });
    },
    cancel:function () {
        //清空表单
        $("#ffff").form("reset");
        //关闭模态框
        $('#win').dialog('close');
    }
}