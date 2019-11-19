<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<!--导入easyui的核心样式文件-->
		<link type="text/css" rel="stylesheet" href="../../easyui/themes/default/easyui.css" />
		<!--导入easyui的图标样式文件-->
		<link type="text/css" rel="stylesheet" href="../../easyui/themes/icon.css" />
		<!--导入jQuery的核心js文件-->
		<script type="text/javascript" src="../../easyui/jquery-1.11.3.min.js"></script>
		<!--导入easyui的核心js文件-->
		<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
		<!--导入easyui的汉化包js文件-->
		<script type="text/javascript" src="../../easyui/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
			.north{width: 50%;height: 90px;float: left;line-height: 50px;box-sizing: border-box;padding-left: 150px;}
		</style>
		<script type="text/javascript">
			$(function(){
				$('#treeMenu').tree({
					url:'/menu/findMenusByEmployeeid',
					lines:true,
					//checkbox:true,
					method:'GET',
					onClick: function(node){
						if(!node.children || node.children.length == 0){
							if($('#tabs').tabs('exists', node.text)){
								//选项卡存在就选中它
								$('#tabs').tabs('select', node.text);
								// 获取已选择的面板
								var tab = $('#tabs').tabs('getSelected');
								//刷新
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
		easyui-layout  表示布局，可以在整个页面body，也可以用在body内部某个局部布局
			region表示区域的意思 取值有：east、west、south、north、center
			split表示分割，拖动改变宽度或者高度
			class="easyui-layout"的容器之内的每个div就代表了一块区域，每一个div都需要使用region属性来指定是哪个区域
				内部的每一个div都相当于panel
	-->
	<body class="easyui-layout">   
	    <div data-options="region:'north'" style="height:92px;">
			<div class="north">
				<h1>XXX智能商贸系统</h1>
			</div>
			<div class="north" style="text-align: right;line-height: 80px;padding-right: 60px;">
				<%--
					<shiro:principal />
						就是获取Shiro登录的主体对象，其实就是我们自定义的JpaRealm的SimpleAuthenticationInfo构造方法的第一个参数
						如果这个主体对象是简单类型，就直接可以显示了；
						如果是domain实体类对象，默认是自动调用对象toString方法得到字符串，我们需要加property属性来指定显示对象的哪个属性值
				--%>
				<span>欢迎登录,${lodinUser.username}</span>
				<a href="/page/logout" onclick="return confirm('你确定要退出？');" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">退出</a>
			</div>
		</div>

		<div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">
			<ul id="treeMenu"></ul>
		</div>
	    <!--
	    	fit表示是否填充父级容器尺寸大小
	    	class="easyui-tabs"表示当前div是一个选项卡的容器，这个div之内的每一个小div都是一个选项卡【本质就是一个panel】
	    -->
	    <div data-options="region:'center'"  style="padding:5px;background:#eee;">
	    	<div id="tabs" class="easyui-tabs" data-options="fit:true">
	    		<div title="欢迎">
	    			<div class="easyui-calendar" data-options="fit:true"></div>
	    		</div>
	    	</div>
	    </div>   
	</body> 
</html>