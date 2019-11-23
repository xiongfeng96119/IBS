<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product列表</title>
		<%@include file="/WEB-INF/views/common.jsp"%>
		<script type="text/javascript" src="/js/product/product.js"></script>
		<style type="text/css">
			.datagrid-row{height: 28px;}
		</style>
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
		<div id="dd" class="easyui-dialog" title="新增/编辑商品信息" style="width:400px;height:350px;"
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="ffff">
				<input type="hidden" name="id" id="productid" />
				
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="name"
					    placeholder="商品名称" data-options="" />
				</div>
				<div class="form-group">
					<input type="color" class="form-control" name="color" placeholder="颜色" style="width:204px;height: 25px;" />
				</div>
				<div class="form-group" id="form-group-pic" style="margin-top: 8px;">
					<input type="text" class="easyui-filebox form-control" id="picFile"
					    data-options="buttonText:'选择图片',prompt:'商品图片'" />
					<input type="hidden" id="pic" name="pic" />
					<div id="progressBar" style="width:204px;height: 3px;background-color: #DADADA;">
						<div id="currentProgress" style="height: 3px;background-color: #6fb3ff;width: 0%;"></div>
					</div>
				</div>
				<%--<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="smallpic"
					   placeholder="smallpic" data-options="" />
				</div>--%>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="costprice"
					   placeholder="成本价" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="saleprice"
					   placeholder="零售价" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="types.id"
					   id="typesId" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="unit.id"
					   id="unitId" data-options="" />
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="brand.id"
					   id="brandId" data-options="" />
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>