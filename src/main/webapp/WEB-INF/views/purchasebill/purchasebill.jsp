<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Purchasebill列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<!-- 单元格编辑插件 -->
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.editors.js"></script>
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
		<script type="text/javascript" src="/js/purchasebill/purchasebill.js"></script>
		<style type="text/css">
			.form-group{width: 100%;box-sizing: border-box;padding: 10px;text-align: left;}
			#billItemsDiv .datagrid-row{height: 29px;}
		</style>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
				<input type="text" class="easyui-datebox form-control searchIn" name="vdate1" data-options="editable:false,prompt:'采购日期'" />
				<input type="text" class="easyui-datebox form-control searchIn" name="vdate2" data-options="editable:false,prompt:'采购日期'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="supplierId"
				   data-options="url:'/supplier/findAll',valueField:'id',textField:'name',editable:false,value:'-1',panelHeight:'auto'" />
				<input type="text" class="easyui-combobox form-control searchIn" name="buyerId"
				   data-options="url:'/employee/findAllBuyer',valueField:'id',textField:'username',editable:false,value:'-1'" />
				<select name="status" class="easyui-combobox form-control searchIn" data-options="editable:false">
					<option value="-1">按状态搜索</option>
					<option value="1">待审</option>
					<option value="2">已审</option>
					<option value="3">作废</option>
				</select>
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑采购单信息" style="width:800px;height:450px;"
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="purchasebillid" />
				<div class="form-group" style="margin-top: 0px;">
					<input type="text" class="easyui-datebox form-control" name="vdate" data-options="editable:false,prompt:'采购日期'" />
					<input type="text" class="easyui-combobox form-control" name="supplier.id" id="supplierId"
					   data-options="url:'/supplier/findAll',valueField:'id',textField:'name',editable:false,value:'-1',panelHeight:'auto'" />
					<input type="text" class="easyui-combobox form-control" name="buyer.id" id="buyerId"
					   data-options="url:'/employee/findAllBuyer',valueField:'id',textField:'username',editable:false,value:'-1'" />
				</div>
				<div class="form-group" style="height: 320px;" id="billItemsDiv">
					<table id="billItems"></table>
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
		<div id="toolbar2">
			<a id="billitem-add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
			<a id="billitem-remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
	</body>
</html>