<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Purchasebillitem列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/easyui/plugin/groupview/datagrid-groupview.js"></script>
		<script src="/highcharts6/code/highcharts.js"></script>
		<script src="/highcharts6/code/highcharts-3d.js"></script>
		<script src="/highcharts6/code/modules/exporting.js"></script>
		<script src="/highcharts6/code/modules/export-data.js"></script>
		<script type="text/javascript" src="/js/purchasebillitem/purchasebillitem.js"></script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<input type="text" class="easyui-datebox form-control searchIn" name="vdate1" data-options="editable:false,prompt:'采购日期'" />
				<input type="text" class="easyui-datebox form-control searchIn" name="vdate2" data-options="editable:false,prompt:'采购日期'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="supplierId"
					   data-options="url:'/supplier/findAll',valueField:'id',textField:'name',editable:false,value:'-1',panelHeight:'auto'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="buyerId"
					   data-options="url:'/employee/findAllBuyer',valueField:'id',textField:'username',editable:false,value:'-1'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="producttypeId"
					   data-options="url:'/producttype/findAll?loadParent=false',valueField:'id',textField:'name',editable:false,value:'-1'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="productId"
					   data-options="url:'/product/findAll',valueField:'id',textField:'name',editable:false,value:'-1'" />
				<select name="status" class="easyui-combobox form-control searchIn" data-options="editable:false">
					<option value="-1">按状态搜索</option>
					<option value="1">待审</option>
					<option value="2">已审</option>
					<option value="3">作废</option>
				</select>
				<select name="groupField" class="easyui-combobox form-control searchIn" data-options="editable:false">
					<option value="">分组字段</option>
					<option value="o.bill.supplier.name">供应商</option>
					<option value="o.bill.buyer.username">采购员</option>
					<option value="o.product.types.name">商品类别</option>
					<option value="o.product.name">商品名称</option>
					<option value="o.bill.status">状态</option>
					<option value="date_format(o.bill.vdate,'%Y年%m月')">月份</option>
				</select>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
				<a data-method="show3dPie" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-large-chart'">3D饼图</a>
			</form>
		</div>
		<div id="dd" class="easyui-dialog" title="采购报表-3D饼状图" style="width:800px;height:450px;"
			 data-options="iconCls:'icon-large-chart',resizable:false,modal:true,closed:true">

		</div>
	</body>
</html>