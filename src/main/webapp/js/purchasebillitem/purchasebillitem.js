$(function(){
    /**
     * easyui的datagrid插件要求json数据一定要包含两个属性
     *	total	总行数
     *	rows	当前页的数据集合
     */
    $("#dg").datagrid({
        striped:true,			//是否显示斑马线效果
        rownumbers:true,		//是否显示行号
        url:'/purchasebillitem/page',
        method:"POST",
        toolbar:'#toolbar',
        columns:[[
            {field:'id',title:'id',width:100,hidden:true},
            {field:'supplier',title:'供应商',width:100},
            {field:'buyer',title:'采购员',width:100},
            {field:'product',title:'商品名称',width:100},
            {field:'productType',title:'商品类别',width:100},
            {field:'vdate',title:'采购日期',width:100},
            {field:'price',title:'单价',width:100},
            {field:'num',title:'数量',width:100},
            {field:'amount',title:'小计',width:100},
            {field:'status',title:'采购单状态',width:100,formatter:formatStatus}
        ]],
        groupField:'groupField',    //分组的字段名称【仅仅只是在页面上分组】展示的是字段的值
        view: groupview,            //分组的视图 groupview是datagrid-groupview.js文件中的一个变量名称
        groupFormatter:function(value, rows){   //分组格式化
            //value 就是分组字段的值
            //row 当前分组内的所有数据行[JSON对象的数组]
            var totalNum = 0, totalAmount = 0;
            for (var i=0;i<rows.length;i++){
                totalNum += rows[i].num;
                totalAmount += rows[i].amount;
            }
            return value + ' - ' + rows.length + ' 条数据 <span style="color: #0b8502;">共 ' + totalNum + ' 件商品</span> <span style="color: #c435ff;">总金额：' + totalAmount + '</span>';
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
    //搜索按钮点击事件
    search:function () {
        $("#dg").datagrid('loading');
        //load方法传入一个json对象，其实就是将json对象作为请求的参数发送给后端
        $("#dg").datagrid('load',$("#searchForm").toJson());
        $("#dg").datagrid('loaded');
    },
    show3dPie:function () {
        $.postJSON("/purchasebillitem/find3DPieData",$("#searchForm").toJson(),function (data) {
            //container表示将图形报表展示到一个div中，它是div的id属性值
            Highcharts.chart('dd', {
                //报表类型
                chart: {
                    type: 'pie',	//pie表示饼状图	 column表示柱状图
                    options3d: {
                        enabled: true,
                        alpha: 45,
                        beta: 0
                    }
                },
                //标题
                title: {
                    text: '采购报表'
                },
                //消息提示 鼠标移上去就会有消息提示出来
                tooltip: {
                    /**
                     * {series.name} 表示获取当前对象的series属性的name属性
                     * point 表示鼠标移到那一块上面，就代表谁
                     *  point.y 就表示当前这一块的总金额
                     *  point.percentage 表示当前这一块的总金额占所有总金额的百分比
                     *      .1f 表示保留小数点1位
                     */
                    pointFormat: '{series.name}: ￥<b>{point.y}</b>元 占比:<b>{point.percentage:.1f}%</b>'
                },
                //批注消息
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        depth: 35,
                        dataLabels: {
                            enabled: true,
                            format: '{point.name}'
                        }
                    }
                },
                //饼状图数据
                series: [{
                    type: 'pie',
                    name: '采购金额',
                    data: data
                }]
            });
        });
        $("#dd").dialog("open");
    }
};