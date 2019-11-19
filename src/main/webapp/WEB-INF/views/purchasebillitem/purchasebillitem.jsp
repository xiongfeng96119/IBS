<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Purchasebillitem列表</title>
		<%@include file="../common.jsp"%>
		<script type="text/javascript" src="/js/purchasebillitem/purchasebillitem.js"></script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
				<input type="text" class="form-control searchIn" name="name" placeholder="按名称搜索" />
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑Purchasebillitem信息" style="width:400px;height:250px;"   
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="menu">
				<input type="hidden" name="id" id="purchasebillitemid" />
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="price"
						   placeholder="price"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="num"
						   placeholder="num"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="amount"
						   placeholder="amount"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="descs"
						   placeholder="descs"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="productId"
						   placeholder="productId"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="billId"
						   placeholder="billId"/>
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>