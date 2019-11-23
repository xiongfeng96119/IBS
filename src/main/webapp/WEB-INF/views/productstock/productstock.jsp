<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Productstock列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/productstock/productstock.js"></script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
				<input type="text" class="form-control searchIn" name="name" placeholder="按名称搜索" />
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑productstock信息" style="width:400px;height:250px;"   
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="productstockid" />
				
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="num"
					   placeholder="num" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="amount"
					   placeholder="amount" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="price"
					   placeholder="price" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="incomedate"
					   placeholder="incomedate" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="warning"
					   placeholder="warning" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="topnum"
					   placeholder="topnum" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="bottomnum"
					   placeholder="bottomnum" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="productId"
					   placeholder="productId" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="depotId"
					   placeholder="depotId" data-options="" />
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>