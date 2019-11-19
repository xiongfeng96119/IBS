<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Purchasebill列表</title>
		<%@include file="../common.jsp"%>
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.editors.js"></script>
		<script type="text/javascript" src="/easyui/plugin/CellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>
		<script type="text/javascript" src="/js/purchasebill/purchasebill.js"></script>
		<script type="text/css">
			.form-group{width:100%;box-sizing: border-box;padding: 10px;text-align: left;}
		</script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
				<input type="text" class="searchIn easyui-datebox" name="vdate1" data-options="prompt:'按采购日期范围搜索',editable:false" />
				<input type="text" class="searchIn easyui-datebox" name="vdate2" data-options="prompt:'按采购日期范围搜索',editable:false" />
				<input class="searchIn easyui-combobox" type="text" name="supplierId"
					   data-options="url:'/supplier/findAll',valueField:'id',textField:'name',value:'-1',editable:false" />
				<input type="text" class="searchIn easyui-combobox" name="buyerId"
					   data-options="url:'/employee/findAllBuyer',valueField:'id',textField:'username',value:'-1',editable:false"/>
				<select class="searchIn easyui-combobox" name="status" data-options="editable:false">
					<option value="-1">按状态搜索</option>
					<option value="0">待审</option>
					<option value="1">已审</option>
					<option value="2">作废</option>
				</select>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑Purchasebill信息" style="width:800px;height:450px;"
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="menu">
				<input type="hidden" name="id" id="purchasebillid" />
				<div class="form-group" style="margin-top: 0px;">
					<input type="text" class="easyui-datebox form-control" name="vdate"
					data-options="required:true,editable:false"/>
					<input type="text" class="easyui-combobox form-control" name="supplier.id" id="supplierId"
					data-options="url:'/supplier/findAll',valueField:'id',textField:'name',value:'-1',editable:false"/>
					<input type="text" class="easyui-combobox form-control" name="buyer.id" id="buyerId"
					data-options="url:'/employee/findAllBuyer',valueField:'id',textField:'username',value:'-1',editable:false"/>
				</div>
				<div class="form-group" style="width:100%;height:320px">
					<table id="billItems" ></table>
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">提交</a>
				</div>
			</form>
		</div>
		<div id="toolbar2">
			<a href="javascript:void(0);" id="btn-save" class="easyui-linkbutton"
			   data-options="iconCls:'icon-add',plain:true">添加</a>
			<a href="javascript:void(0);" id="btn-remove" class="easyui-linkbutton"
			   data-options="iconCls:'icon-remove',plain:true">删除</a>
		</div>
	</body>
</html>