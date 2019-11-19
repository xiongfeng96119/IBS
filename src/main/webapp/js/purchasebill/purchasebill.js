$(function(){
    $("#dg").datagrid({
        striped:true,
        rownumbers:true,
        pagination:true,
        url:'/purchasebill/page',
        method:"POST",
        toolbar:'#toolbar',
        frozenColumns:[[{field:'bbbb',checkbox:true}]],
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'buyer',title:'采购员',width:100,formatter:formatEmp},
            {field:'vdate',title:'采购时间',width:100},
            {field:'totalamount',title:'采购总额',width:100},
            {field:'totalnum',title:'采购总数',width:100},
            {field:'inputuser',title:'录入人',width:100,formatter:formatEmp},
            {field:'inputtime',title:'录入时间',width:100},
            {field:'auditor',title:'审核人',width:100,formatter:formatEmp},
            {field:'auditortime',title:'审核时间',width:100},
            {field:'status',title:'状态',width:100,formatter:function (value, row, index) {
                    if(value == 0) return "待审";
                    else if(value == 1) return "已审";
                    else return "作废";
                }},
            {field:'supplier',title:'供应商',width:100,formatter:format},
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
    //采购单明细表格
    var dg = $("#billItems"),
        //默认行【一行的数据中包含哪些字段】
        defaultRow = {ID:"",product:"",productPic:"",productColor:"",price:0,num:0,amount:0,descs:""},
        insertPosition = "bottom"; //插入的行在最下面

    var dgInit = function () {
        var getColumns = function () {
            var result = [];
            var normal = [
                {
                    field: 'product', title: '商品', width: 200,
                    editor: {
                        type: "combobox",
                        options: {
                            required: true,
                            valueField:'id',
                            textField:'name',
                            prompt:'请选择商品',
                            editable:false,
                            url:'/product/findAll'
                        }
                    },
                    formatter:format
                },
                {field: 'productPic', title: '图片', width: 60,formatter:function (value, row, index) {
                        return row.product&&row.product.smallpic ? "<img style='width:38px;height:38px;margin-top:2px;margin-right:2px;' src='"+row.product.smallpic+"'/>" : "";
                    }},
                {field: 'productColor', title: '颜色', width: 60,formatter:function (value, row, index) {
                        return row.product&&row.product.color ? "<div style='width:40px;height:10px;margin:auto;background-color:"+row.product.color+";'></div>" : "";
                    }},
                {
                    field: 'price', title: '采购单价', width: 80,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,precision:2
                        }
                    }
                },
                {
                    field: 'num', title: '采购数量', width: 80,
                    editor: {
                        type: "numberbox",
                        options: {
                            required: true,precision:2
                        }
                    }
                },
                {field: 'amount', title: '小计', width: 80,formatter:function (value, row, index) {
                        return row.price && row.num ? row.price*row.num : "0";
                    }},
                {
                    field: 'descs', title: '备注', width: 140,
                    editor: {
                        type: "text"
                    }
                }
            ];
            result.push(normal);
            return result;
        };
        //datagrid的初始化参数
        var options = {
            striped:true,
            rownumbers: true,
            title:'采购单明细',
            singleSelect: true,
            height:"320px",
            toolbar:"#toolbar2",
            columns: getColumns(),
            enableCellEdit: true            //表示开启单元格编辑功能
        };
        dg.datagrid(options);
    };
    //获取插入的行在表格中的索引【插入到第一行还是最后一行】
    var getInsertRowIndex = function () {
        return insertPosition == "top" ? 0 : dg.datagrid("getRows").length;
    }
    //按钮绑定事件
    var buttonBindEvent = function () {
        $("#btn-save").click(function () {
            var targetIndex = getInsertRowIndex(), targetRow = $.extend({}, defaultRow, {ID:$.util.guid()});
            //从最后一行开始插入
            dg.datagrid("insertRow", { index: targetIndex, row: targetRow });
            //立即开始编辑当前插入数据的哪一列
            dg.datagrid("editCell", { index: targetIndex, field: "product" });
        });
        $("#btn-remove").click(function () {
            var row = dg.datagrid("getSelected");
            if(row!=null){
                $.messager.alert("错误","请选择要移除的行！","error");
                return;
            }
            var index = dg.datagrid("getRowIndex", row);
            dg.datagrid("deleteRow", index);
        });
    };
    dgInit(); buttonBindEvent();
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
                $.postJSON("/purchasebill/delete",{ids:ids.join(",")},function(data){
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
        $.getJSON("/purchasebill/findOne",{id:selectedRows[0].id},function(data){
            $("#menu").form("load", data);
            $("#supplierId").combobox("setValue",data.supplier.id);
            $("#buyerId").combobox("setValue",data.buyer.id);
            //回填采购单明细
            $("#billItems").datagrid("loadData",data.billitems);
            //打开模态框
            $('#dd').dialog('open');
        });
    },
    add:function () {
        $("#menu").form("reset");
        $("#purchasebillid").val("");
        $("#billItems").datagrid("loadData", []);
        $('#dd').dialog('open');  // open a window
    },
    save:function () {
        var params = $("#menu").toJson();
        var url = "/purchasebill/save";
        if($("#purchasebillid").val()) {
            url = "/purchasebill/update";
            params.action = "update";
        }
        //添加明细单数据
        var billItems = $("#billItems").datagrid("getRows");
        for (var i = 0;i < billItems.length;i++){
            params["billitems["+i+"].product.id"] = billItems[i].product.id;
            params["billitems["+i+"].price"] = billItems[i].price;
            params["billitems["+i+"].num"] = billItems[i].num;
            params["billitems["+i+"].descs"] = billItems[i].descs;
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