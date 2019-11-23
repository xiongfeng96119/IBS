$(function(){
    /**
     * easyui的datagrid插件要求json数据一定要包含两个属性
     *	total	总行数
     *	rows	当前页的数据集合
     */
    $("#dg").datagrid({
        striped:true,			//是否显示斑马线效果
        rownumbers:true,		//是否显示行号
        pagination:true,		//是否显示分页工具栏
        url:'/stockincomebill/page',
        method:"POST",
        toolbar:'#toolbar',
        frozenColumns:[[{field:'aaaa',checkbox:true}]],
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'vdate',title:'入库日期',width:100},
            {field:'totalamount',title:'总金额',width:100},
            {field:'totalnum',title:'总数量',width:100},
            {field:'inputtime',title:'录入时间',width:100},
            {field:'auditortime',title:'审核时间',width:100},
            {field:'status',title:'状态',width:60,formatter:formatStatus},
            {field:'supplier',title:'供应商',width:100,formatter:format},
            {field:'auditor',title:'审核人',width:100,formatter:formatEmp},
            {field:'inputuser',title:'录入人',width:100,formatter:formatEmp},
            {field:'keeper',title:'库管员',width:100,formatter:formatEmp},
            {field:'depot',title:'仓库',width:100,formatter:format},
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

    //采购单明细表格
    var dg = $("#billItems"),
        //点击添加按钮后添加的默认行的json数据
        defaultRow = {ID: "", product: "", color: "", pic: "", price: 0, num: 1, amount: 0, descs: ""},
        insertPosition = "bottom";//添加按钮添加的行在最下边
    //初始化
    var dgInit = function () {
        var getColumns = function () {
            var result = [];
            var normal = [
                {
                    field: 'product', title: '采购商品', width: 220,
                    editor: {
                        type: "combobox",
                        options: {
                            url:"/product/findAll",
                            valueField:"id",
                            textField:"name",
                            editable:false,
                            prompt:"请选择采购的商品",
                            required: true
                        }
                    },
                    formatter:format
                },
                {
                    field: 'color', title: '颜色', width: 60,
                    formatter:function (value, row, index) {
                        return row.product && row.product.color ? '<div style="width: 40px;height: 10px;background-color: '+row.product.color+';"></div>' : '';
                    }
                },
                {
                    field: 'pic', title: '图片', width: 60,
                    formatter:function (value, row, index) {
                        return row.product && row.product.smallpic ? '<div style="width: 100%;height: 28px;line-height: 28px;box-sizing: border-box;padding-top: 2px;"><img src="'+row.product.smallpic+'"/></div>' : '暂无图片';
                    }
                },
                {
                    field: 'price', title: '采购单价', width: 80,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,
                            precision:2
                        }
                    }
                },
                {
                    field: 'num', title: '采购数量', width: 80,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,
                            precision:2
                        }
                    }
                },
                {
                    field: 'amount', title: '小计金额', width: 80,
                    formatter:function (value, row, index) {
                        return row.price && row.num ? "￥" + ((row.price * row.num).toFixed(2)) : "0";
                    }
                },
                {
                    field: 'descs', title: '描述', width: 100,
                    editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);//做成二维数组
            return result;
        };
        var options = {
            striped:true,			//是否显示斑马线效果
            rownumbers:true,		//是否显示行号
            toolbar:'#toolbar2',
            title:'入库单明细',
            height:'320px',
            singleSelect: true,     //只能单选
            columns: getColumns(),
            enableCellEdit: true        //表示开启单元格编辑功能
        };
        dg.datagrid(options);
    };

    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }
    //给按钮绑定点击事件
    var buttonBindEvent = function () {
        $("#billitem-add").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, { ID: $.util.guid() });
            dg.datagrid("insertRow", { index: targetIndex, row: targetRow });   //给datagrid末尾插入一行数据
            dg.datagrid("editCell", { index: targetIndex, field: "product" });  //立即开始编辑当前插入的那行数据的哪一列
        });
        $("#billitem-remove").click(function () {
            var selectedRow = dg.datagrid("getSelected");
            if (selectedRow){
                //getRowIndex row 返回指定行的索引号，该行的参数可以是一行记录或一个ID字段值。
                var index = dg.datagrid("getRowIndex", selectedRow);
                //deleteRow index 删除行。
                dg.datagrid("deleteRow", index);
            }else{
                $.messager.alert("错误","请选择要删除的采购单明细！","error");
            }
        });
    };
    dgInit(); buttonBindEvent();
});

//window.methods = {};  防止污染
window.methods = {
    //给提交按钮绑定点击事件
    save:function () {
        var params = $("#ffff").toJson();
        var url = "/stockincomebill/save";
        if($("#stockincomebillid").val()) {
            url = "/stockincomebill/update";
            params.action = "update";
        }
        //添加采购单明细的数据
        var billItems = $("#billItems").datagrid("getRows");
        for (var i=0;i<billItems.length;i++){
            params["billitems["+i+"].product.id"] = billItems[i].product.id;
            params["billitems["+i+"].price"] = billItems[i].price;
            params["billitems["+i+"].num"] = billItems[i].num;
            params["billitems["+i+"].descs"] = billItems[i].descs;
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
        $("#stockincomebillid").val("");
        //清空采购单明细表格的数据
        $("#billItems").datagrid("loadData",[]);
        $('#dd').dialog('open');  // open a window
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
        $.getJSON("/stockincomebill/findOne",{id:selectedRows[0].id},function(data){
            //表单回填[要求json对象的属性名称要与表单输入框的name属性值一致]
            $("#ffff").form("load", data);
            //关联的供应商和库管员、仓库要单独回填一下
            if (data.supplier) $("#supplierId").combobox("setValue", data.supplier.id);
            if (data.keeper) $("#keeperId").combobox("setValue", data.keeper.id);
            if (data.depot) $("#depotId").combobox("setValue", data.depot.id);
            //回填一下采购单明细数据
            $("#billItems").datagrid("loadData",data.billitems);
            //隐藏审核入库按钮，显示提交按钮
            $("[data-method=save]").show();
            $("[data-method=audit]").hide();
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
                $.postJSON("/stockincomebill/delete",{ids:ids.join(",")},function(data){
                    $.messager.alert('信息',data.msg,"info");
                    if(data.status == 200){
                        //刷新表格数据
                        $("#dg").datagrid('load',{pageNo:1,pageSize:10})
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
    toAudit:function () {
        //先获取选中了哪些行
        var selectedRows = $("#dg").datagrid("getSelections");
        if(selectedRows.length == 0){
            $.messager.alert("错误","请选择要审核的数据！","error");
            return;
        }
        if(selectedRows.length > 1){
            $.messager.alert("错误","您只能选择一行数据进行审核！","error");
            return;
        }
        $("#ffff").form("reset");
        //发送请求，通过主键id加载一个对象的数据，进行表单回填
        $.getJSON("/stockincomebill/findOne",{id:selectedRows[0].id},function(data){
            //表单回填[要求json对象的属性名称要与表单输入框的name属性值一致]
            $("#ffff").form("load", data);
            //关联的供应商和库管员、仓库要单独回填一下
            if (data.supplier) $("#supplierId").combobox("setValue", data.supplier.id);
            if (data.keeper) $("#keeperId").combobox("setValue", data.keeper.id);
            if (data.depot) $("#depotId").combobox("setValue", data.depot.id);
            //回填一下采购单明细数据
            $("#billItems").datagrid("loadData",data.billitems);
            //隐藏审核入库按钮，显示提交按钮
            $("[data-method=save]").hide();
            $("[data-method=audit]").show();
            //显示模态框
            $('#dd').dialog('open');
        });
    },
    //入库审核
    audit:function () {
        var params = $("#ffff").toJson();
        var url = "/stockincomebill/audit";
        //添加采购单明细的数据
        var billItems = $("#billItems").datagrid("getRows");
        for (var i=0;i<billItems.length;i++){
            params["billitems["+i+"].product.id"] = billItems[i].product.id;
            params["billitems["+i+"].price"] = billItems[i].price;
            params["billitems["+i+"].num"] = billItems[i].num;
            params["billitems["+i+"].descs"] = billItems[i].descs;
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
    }
};