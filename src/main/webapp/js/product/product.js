$(function(){
    $("#dg").datagrid({
        striped:true,
        rownumbers:true,
        pagination:true,
        url:'/product/page',
        method:"POST",
        toolbar:'#toolbar',
        frozenColumns:[[{field:'bbbb',checkbox:true}]],
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'name',title:'商品名称',width:100},
            {field:'color',title:'颜色',width:100,align:"center",formatter:function (value, row, index) {
                    return "<div style='width:40px;height:10px;margin:auto;background-color:"+value+";'></div>";
                }},
            {field:'pic',title:'图片',width:100,hidden:true},
            {field:'smallpic',title:'图片',width:100,align:"center",formatter:function (value, row, index) {
                    return "<img class='productPic' src='"+value+"' alt='没有图片' style='width:38px;height:38px;margin-top:5px;' />";
                }},
            {field:'costprice',title:'成本价',width:100},
            {field:'saleprice',title:'零售价',width:100},
            {field:'types',title:'商品类型',width:100,formatter:format},
            {field:'unit',title:'计量单位',width:100,formatter:format},
            {field:'brand',title:'商品品牌',width:100,formatter:format},
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
    //给文件上传框绑定onChange事件
    $("#imageFile").filebox({
        onChange:function (newValue, oldValue) {
            var file = $("input[name=imageFile]")[0].files[0];
            if(!file.type || file.type.indexOf("image/") != 0){
                //上传的文件类型不是图片
                $.messager.alert("错误","请选择商品图片！","error");
                return;
            }
            var formData = new FormData();
            formData.append("pic", file);
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "/product/upload", true);
            //请求完毕
            xhr.onload = function(){
                if(xhr.responseText != ""){
                    $("#pic").val(xhr.responseText);
                    $("#smallpic").val(xhr.responseText+"_small.png");
                }
            };
            xhr.send(formData);
        }
    });
    $(document).on("mouseover",".productPic",function(event){
        var src = $(this).attr("src");
        $("#bigPic").attr("src",src.substring(0,src.length-10));
        $("#bigPicDiv").css({"left":event.pageX,"top":event.pageY}).show();
    }).on("mouseout","#bigPicDiv",function () {
        $("#bigPicDiv").hide();
    });
});
function loadSuccess(data) {
    var rows = data.rows;
    for(var i=0;i<rows.length;i++){
        var result = rows[i];
        $.easyui.tooltip.init($("img[src='"+result.smallpic+"']"), {
            position: "right",
            content: "<div style=\"width:600px;height:480px;\"><img src='"+result.pic+"' style=\"width:600px;height:480px;\"/></div>"
        });
    }
}
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
                $.postJSON("/product/delete",{ids:ids.join(",")},function(data){
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
        $.getJSON("/product/findOne",{id:selectedRows[0].id},function(data){
            //表单回填
            $("#menu").form("load", data);
            //回填一下文件框
            $("#imageFile").filebox("setText",data.pic);
            $("#colorId").val(data.color);
            //回填一下商品类型、计量单位、品牌下拉框
            $("#typesId").combobox("setValue",data.types.id);
            $("#unitId").combobox("setValue",data.unit.id);
            $("#brandId").combobox("setValue",data.brand.id);
            //打开模态框
            $('#dd').dialog('open');
        });
    },
    add:function () {
        $("#menu").form("reset");
        $("#productid").val("");
        $('#dd').dialog('open');  // open a window
    },
    save:function () {
        var params = $("#menu").toJson();
        var url = "/product/save";
        if($("#productid").val()) {
            url = "/product/update";
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