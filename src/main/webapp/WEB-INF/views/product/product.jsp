<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Product列表</title>
		<%@include file="../common.jsp"%>
		<script type="text/javascript" src="/easyui/plugin/jeasyui.extensions.base.tooltip.js"></script>
		<script type="text/javascript" src="/js/product/product.js"></script>
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
		<div id="dd" class="easyui-dialog" title="新增/编辑Product信息" style="width:400px;height:250px;"   
		        data-options="iconCls:'icon-save',resizable:false,modal:true,closed:true">   
			<form id="menu">
				<input type="hidden" name="id" id="productid" />
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="name"
						   placeholder="name"/>
				</div>
				<div class="form-group">
					<input type="color" class="easyui-validatebox form-control" name="color"
						   id="colorId" placeholder="color"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-filebox form-control" name="imageFile" id="imageFile" data-options="prompt:'商品图片'"/>
					<input type="hidden" name="pic" id="pic"/>
				</div>
				<div class="form-group">
					<input type="hidden" class="easyui-validatebox form-control" id="smallpic"
						   name="smallpic"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="costprice"
						   placeholder="costprice"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-validatebox form-control" name="saleprice"
						   placeholder="saleprice"/>
				</div>
				<div class="form-group">
					<select type="text" class="easyui-combobox form-control" name="types.id" id="typesId" style="width: 204.5px"
						   data-options="url:'/producttype/findAllChildren',valueField:'id',textField:'name',prompt:'请选择类型',editable:false"></select>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="unit.id" id="unitId" style="width: 204.5px"
						   data-options="url:'/systemdictionarydetail/findUnit',valueField:'id',textField:'name',prompt:'请选择单位',editable:false"/>
				</div>
				<div class="form-group">
					<input type="text" class="easyui-combobox form-control" name="brand.id" id="brandId" style="width: 204.5px"
						   data-options="url:'/systemdictionarydetail/findBrand',valueField:'id',textField:'name',prompt:'请选择品牌',editable:false"/>
				</div>
				<div class="form-group">
					<a data-method="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true">提交</a>
				</div>
			</form>
		</div>
	</body>
</html>