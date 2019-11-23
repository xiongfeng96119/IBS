<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/employee/employee.js"></script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
				<shiro:hasPermission name="employee:delete">
					<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
				</shiro:hasPermission>
				<a data-method="export" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导出</a>
				<input type="text" class="form-control searchIn" name="username" placeholder="按用户名搜索" />
				<input type="text" class="form-control searchIn" name="age1" placeholder="按年龄区间搜索" />
				<input type="text" class="form-control searchIn" name="age2" placeholder="按年龄区间搜索" />
				<select class="easyui-combobox form-control searchIn" name="departmentId" style="width: 104px;"
						data-options="valueField:'id',textField:'name',url:'/department/findAll',editable:false,panelHeight:'auto',prompt:'按部门搜索'"></select>
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑员工信息" style="width:400px;height:250px;"   
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="employeeid" />
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="username"
					   placeholder="用户名" data-options="required:true,validType:['username','unique']" />
				</div>
				<div class="form-group passwordbox">
					<input type="text" class="easyui-validatebox form-control" name="password" placeholder="密码" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="email" placeholder="邮箱" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="age" placeholder="年龄" />
				</div>
				<div class="form-group">
					<select class="easyui-combobox form-control" id="departmentId" name="department.id" style="width: 204px;"
						data-options="valueField:'id',textField:'name',url:'/department/findAll',editable:false,panelHeight:'auto',prompt:'请选择部门'"></select>
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>