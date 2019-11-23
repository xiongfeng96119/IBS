<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<!-- easyui的核心样式文件 -->
		<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css"/>
		<!-- easyui的图标样式文件 -->
		<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css"/>
		<!-- jQuery的核心文件 -->
		<script type="text/javascript" src="/easyui/jquery.min.js" ></script>
		<!-- easyui的核心js文件 -->
		<script type="text/javascript" src="/easyui/jquery.easyui.min.js" ></script>
		<!-- 语言包 -->
		<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js" ></script>
		<style type="text/css">
			#north>div{
				width: 50%;
				height: 55px;
				float: left;
				box-sizing: border-box;
			}
			#north>div:last-child{
				text-align: right;
				line-height: 55px;
				padding-right: 20px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				$('#tt').tree({
					url:'/menu/findMenusByEmployeeid',
					lines:true,
					//checkbox:true,
					method:'GET',
					onClick: function(node){
						//alert(node.text);  // 在用户点击的时候提示
						if(!node.children || node.children.length == 0){
							if($('#tabs').tabs('exists', node.text)){
								//选项卡存在就选中它
								$('#tabs').tabs('select', node.text);
								// 获取已选择的面板
								var tab = $('#tabs').tabs('getSelected');  
								//刷新它
								$('#tabs').tabs('update', {
									tab: tab,
									options: {}
								});
							}else{
								//点击终极菜单后在中间区域添加一个Tab标签页
								$('#tabs').tabs('add',{
									title: node.text,
									selected: true,			//添加之后是否立即选中
									closable:true,			//是否显示关闭按钮
									content:'<iframe src="' + node.url + '" frameborder="0" scrolling="auto" width="99.5%" height="525px"></iframe>'
								});
							}
						}else{
							//点击的不是终级菜单
							if(node.state == "open"){
								$(this).tree("collapse",node.target);
							}else{
								$(this).tree("expand",node.target);
							}
						}
					}
				});
			});
		</script>
	</head>
	<!--
		layout 表示布局
			region表示区域
			split 分割线是否允许拖动来改变面板的大小
	-->
	<body class="easyui-layout">   
		
	    <div data-options="region:'north',split:false" style="height:60px;" id="north">
			<div style="background-color: #b5ede7"></div>
			<div style="background-color: #8bed78">
				<span>欢迎你，<shiro:principal property="username"/><%--${loginUser.username}--%></span>
				<a href="/page/logout" onclick="return confirm('你确定要拂袖而去吗？');" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">退出</a>
			</div>
		</div>
	    <div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">
	    	<ul id="tt"></ul> 
	    </div>
	    <div data-options="region:'center'" style="padding:2px;background:#eee;">
	    	<div id="tabs" class="easyui-tabs" data-options="fit:true">   
			    <div title="欢迎" style="padding:10px;">   
			        <h1>欢迎</h1>
			    </div>   
			</div> 
	    </div>   
	</body> 
</html>