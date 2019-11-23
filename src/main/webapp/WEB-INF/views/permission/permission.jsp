<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Permission列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/permission/permission.js"></script>
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
		<div id="dd" class="easyui-dialog" title="新增/编辑权限信息" style="width:400px;height:250px;"
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="permissionid" />
				
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="name"
					   placeholder="名称" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="url"
					   placeholder="资源路径" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="sn"
					   placeholder="编码" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="menu.id" id="menuId"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="descs"
					   placeholder="描述" data-options="" />
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>