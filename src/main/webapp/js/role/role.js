$(function(){
    $("#dg").datagrid({
        striped:true,
        rownumbers:true,
        pagination:true,
        url:'/role/page',
        method:"POST",
        toolbar:'#toolbar',
        frozenColumns:[[{field:'bbbb',checkbox:true}]],
        columns:[[
            {field:'id',title:'id',width:100},
            {field:'name',title:'name',width:100},
            {field:'sn',title:'sn',width:100},
            {field:'permissions',title:'角色权限',width:600,formatter:function (value, row, index) {
                    var permissionNames = [];
                    for (var i = 0;i < value.length;i++) {
                        permissionNames.push(value[i].name);
                    }
                    return permissionNames.join(",");
                }}
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
    //将左边表格datagrid初始化
    $("#selectedPermissions").datagrid({
        striped:true,			//是否显示斑马线效果
        fit:true,
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'名称',width:80},
            {field:'url',title:'资源路径',width:130},
            {field:'sn',title:'编码',width:130}
        ]],
        onDblClickRow:function (index, row) {
            //双击后删除选中权限
            $("#selectedPermissions").datagrid("deleteRow",index);
        }
    });

    //将右边表格datagrid初始化
    $("#allPermissions").datagrid({
        striped:true,			//是否显示斑马线效果
        rownumbers:true,		//是否显示行号
        pagination:true,		//是否显示分页工具栏
        url:'/permission/page',
        fit:true,
        method:"POST",
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'名称',width:80},
            {field:'url',title:'资源路径',width:130},
            {field:'sn',title:'编码',width:130}
        ]],
        onDblClickRow:function (index, row) {
            var rows = $("#selectedPermissions").datagrid("getRows");
            //遍历左边表格，如果有跟右边表格所选权限相同的则不继续添加
            for (var i=0;i<rows.length;i++){
                if (rows[i].id == row.id){
                    $.messager.show({
                        title:"错误提示",
                        msg:"当前角色已拥有\""+row.name+"\"权限，请勿重复添加！"
                    });
                    return;
                }
            }
            //双击右边表格的权限，添加到左边表格中
            $("#selectedPermissions").datagrid("appendRow",row);
        }
    });
    //右边的表格实现翻页功能
    $("#allPermissions").datagrid("getPager").pagination({
        onSelectPage:function(pageNumber, pageSize){
            //触发一下搜索按钮的点击事件
            $("#allPermissions").datagrid('loading');
            $("#allPermissions").datagrid('load',{pageNo:pageNumber,pageSize:pageSize});
            $(this).pagination({pageNumber:pageNumber,pageSize:pageSize});
            $("#allPermissions").datagrid('loaded');
        }
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
                $.postJSON("/role/delete",{ids:ids.join(",")},function(data){
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
        $("#ffff").form("reset");
        $.getJSON("/role/findOne",{id:selectedRows[0].id},function(data){
            $("#ffff").form("load", data);
            //回填左边表格权限
            $("#selectedPermissions").datagrid("loadData",data.permissions);
            //打开模态框
            $('#win').dialog('open');
        });
    },
    add:function () {
        $("#ffff").form("reset");
        $("#roleid").val("");
        $('#win').dialog('open');  // open a window
    },
    save:function () {
        var params = $("#ffff").toJson();
        var url = "/role/save";
        if($("#roleid").val()) {
            url = "/role/update";
            params.action = "update";
        }
        //获取左边表格的所有行
        var rows = $("#selectedPermissions").datagrid("getRows");
        for (var i=0;i<rows.length;i++){
            params["permissions["+i+"].id"] = rows[i].id;
        }
        $.postJSON(url,params,function(data){
            if(data.status == 200){
                $.messager.alert('信息',data.msg,"info");
                //刷新表格数据
                $("#dg").datagrid('load',{pageNo:1,pageSize:10})
                //关闭模态框
                $('#win').dialog('close');
            }else{
                $.messager.alert('错误',data.msg,"error");
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