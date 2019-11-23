<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Role列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/role/role.js"></script>
		<style type="text/css">
			.form-group{
				width: 760px;text-align: left;
			}
		</style>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
				<%--<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>--%>
				<%--<input type="text" class="form-control searchIn" name="name" placeholder="按名称搜索" />--%>
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<%--<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>--%>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑角色信息" style="width:800px;height:500px;"
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="roleid" />
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="name"
					   placeholder="角色名称" data-options="" />
					<input type="text" class="easyui-validatebox form-control" name="sn"
					   placeholder="角色编码" data-options="" />
				</div>
				<div class="form-group easyui-layout" style="height: 380px;">
					<div data-options="region:'west'" style="width: 380px;height: 380px;">
						<table id="selectedPermissions"></table>
					</div>
					<div data-options="region:'center'" style="width: 380px;height: 380px;">
						<table id="allPermissions" ></table>
					</div>
				</div>
				<div class="form-group" style="text-align: center;">
					<a data-method="cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">取消</a>
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>