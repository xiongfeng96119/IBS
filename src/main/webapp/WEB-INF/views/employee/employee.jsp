<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<%@include file="../common.jsp"%>
		<script type="text/javascript" src="/js/employee/employee.js"></script>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
				<input type="text" class="form-control searchIn" name="username" placeholder="按用户名搜索" />
				<input type="text" class="form-control searchIn" name="age1" placeholder="按年龄区间搜索" />
				<input type="text" class="form-control searchIn" name="age2" placeholder="按年龄区间搜索" />
				<select class="easyui-combobox form-control searchIn" name="departmentId" style="width: 204px;"
						data-options="valueField:'id',textField:'name',url:'/department/findAll',editable:false,panelHeight:'auto',prompt:'请选择部门'"></select>
				<input type="hidden" name="pageNo" id="pageNo" value="1"/>
				<input type="hidden" name="pageSize" id="pageSize" value="10"/>
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
				<a href="#"  data-method="export" class="easyui-linkbutton" iconCls="icon-redo" plain="true">导出</a>
			</form> 
		</div>
		<div id="dd" class="easyui-dialog" title="新增/编辑员工信息" style="width:400px;height:250px;"   
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="menu">
				<input type="hidden" name="id" id="employeeid" />
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="username"
						   placeholder="用户名" data-options="required:true,validType:['username','unique']"/>
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
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>