<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Role列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/role/role.js"></script>
		<style type="text/css">
			#ffff>div{width: 97.5%;height: 40px;margin-left: 10px;}
			#ffff>div:first-child {margin-top: 10px;}
			.in{width: 200px;height: 20px;border:1px solid #ccc;border-radius: 5px;}
			.in:focus{border:1px solid #66AFE9;box-shadow: 0px 0px 10px 1px #66AFE9;}
			.searchIn{width: 100px;}
		</style>
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
		<div id="win" class="easyui-window" title="新增/编辑角色信息" style="width:800px;height:485px;"
			 data-options="iconCls:'icon-save',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">
			<form id="ffff">
				<div>
					<input type="hidden" name="id" id="roleid">
					<input class="in" type="text" name="name" placeholder="名称" />
					<input class="in" type="text" name="sn" placeholder="编码" />
				</div>
				<div style="height: 350px;">
					<div style="width: 50%;height: 350px;float: left;">
						<table id="selectedPermissions"></table>
					</div>
					<div style="width: 50%;height: 350px;float: left;">
						<table id="allPermissions"></table>
					</div>
				</div>
				<div style="text-align: center;box-sizing: border-box;padding-top: 10px;">
					<a data-method="cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">取消</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>