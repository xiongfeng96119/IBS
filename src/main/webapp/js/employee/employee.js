$(function(){
    /**
     * easyui的datagrid插件要求json数据一定要包含两个属性
     *	total	总行数
     *	rows	当前页的数据集合
     */
    $("#dg").datagrid({
        striped:true,			//是否显示斑马线效果
        rownumbers:true,		//是否显示行号
        //singleSelect:true,		//是否只能单选行
        pagination:true,		//是否显示分页工具栏
        toolbar:'#toolbar',
        method:'post',
        url:'/employee/page',
        frozenColumns:[[{field:'aaaa',checkbox:true}]],
        columns:[[
            {field:'id',title:'ID',width:100,hidden:true},
            {field:'username',title:'用户名',width:100},
            {field:'password',title:'密码',width:300},
            {field:'email',title:'邮箱',width:200},
            {field:'headImage',title:'头像',width:100},
            {field:'age',title:'年龄',width:100},
            {field:'department',title:'部门',width:100,formatter: format}
        ]]
    });
    //翻页功能
    $("#dg").datagrid("getPager").pagination({
        onSelectPage:function(pageNumber, pageSize){
            $("#pageNo").val(pageNumber);
            $("#pageSize").val(pageSize);
            //触发一下搜索按钮的点击事件
            $("#dg").datagrid('loading');
            //load方法传入一个json对象，其实就是将json对象作为请求的参数发送给后端
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

//window.methods = {};  防止污染
window.methods = {
    //给提交按钮绑定点击事件
    save:function () {
        var params = $("#ffff").toJson();
        var url = "/employee/save";  //employee:save
        if($("#employeeid").val()) {
            url = "/employee/update";
            params.action = "update";
        }
        $.postJSON(url,params,function(data){
            if(data.status == 200){
                $.messager.alert('信息',data.msg,"info");
                //刷新表格数据
                $("#dg").datagrid('load',{pageNo:1,pageSize:10})
                //关闭模态框
                $('#dd').dialog('close');
            }else{
                $.messager.alert('错误',data.msg,"error");
            }
        });
    },
    //新增按钮点击事件
    add:function () {
        $("#ffff").form("reset");
        $("#employeeid").val("");
        $('#dd').dialog('open');  // open a window
        //将密码框显示出来，设置为可用状态
        $(".passwordbox").show();
        $(".passwordbox>input[name=password]").prop("disabled",false);
    },
    //编辑按钮点击事件
    edit:function () {
        //先获取选中了哪些行
        var selectedRows = $("#dg").datagrid("getSelections");
        if(selectedRows.length == 0){
            $.messager.alert("错误","请选择要修改的数据！","error");
            return;
        }
        if(selectedRows.length > 1){
            $.messager.alert("错误","您只能选择一行数据进行修改！","error");
            return;
        }
        $("#ffff").form("reset");
        //发送请求，通过主键id加载一个对象的数据，进行表单回填
        $.getJSON("/employee/findOne",{id:selectedRows[0].id},function(data){
            //表单回填[要求json对象的属性名称要与表单输入框的name属性值一致]
            $("#ffff").form("load", data);
            //关联的部门要单独回填一下
            if (data.department) $("#departmentId").combobox("setValue", data.department.id);
            //将密码框隐藏，设置为禁用状态
            $(".passwordbox").hide();
            $(".passwordbox>input[name=password]").prop("disabled",true);
            //显示模态框
            $('#dd').dialog('open');
        });
    },
    //删除按钮点击事件
    remove:function () {
        $.messager.confirm('确认','您确认想要删除这些数据吗？',function(r){
            if (r){
                //先获取选中了哪些行
                var selectedRows = $("#dg").datagrid("getSelections");
                if(selectedRows.length == 0){
                    $.messager.alert("错误","请选择要删除的数据！","error");
                    return;
                }
                //获取每一行的id属性值，拼接成字符串
                var ids = [];
                for(var i=0;i<selectedRows.length;i++){
                    ids.push(selectedRows[i].id);
                }
                //发送请求，传递ids参数，进行删除
                $.postJSON("/employee/delete",{ids:ids.join(",")},function(data){
                    if(data.status == 200){
                        $.messager.alert('信息',data.msg,"info");
                        //刷新表格数据
                        $("#dg").datagrid('load',{pageNo:1,pageSize:10})
                    }else if (data.status == 501){
                        $.messager.alert('错误',data.msg,"error");
                        top.location.href = "/page/login";
                    }else{
                        $.messager.alert('错误',data.msg,"error");
                    }
                });
            }
        });
    },
    //搜索按钮点击事件
    search:function () {
        $("#dg").datagrid('loading');
        //load方法传入一个json对象，其实就是将json对象作为请求的参数发送给后端
        $("#dg").datagrid('load',$("#searchForm").toJson());
        $("#dg").datagrid("getPager").pagination({pageNumber:1,pageSize:10});
        $("#dg").datagrid('loaded');
    },
    export:function () {
        var param = $("#searchForm").serialize();
        location.href = "/employee/export?" + param;
    }
};