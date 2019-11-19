<%@ page  contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>xxx智能商贸系统</title>
        <!-- jQuery的核心文件 -->
        <script type="text/javascript" src="/js/jquery-1.8.0.min.js"></script>
        <!-- easyui的核心js文件 -->
        <script type="text/javascript" src="/easyui/jquery.easyui.min.js" ></script>
        <!-- 语言包 -->
        <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js" ></script>
        <script type="text/javascript" src="/easyui/plugin/jquery.jdirk.js" ></script>
        <script type="text/javascript" src="/easyui/plugin/validatebox/jeasyui.extensions.validatebox.rules.js"></script>
        <link rel="stylesheet" type="text/css" href="/css/style2.0.css">
        <style type="text/css">
            ul li{font-size: 30px;color:#2ec0f6;}
            .tyg-div{z-index:-1000;float:left;position:absolute;left:5%;top:20%;}
            .tyg-p{
                font-size: 14px;
                font-family: 'microsoft yahei';
                position: absolute;
                top: 135px;
                left: 60px;
            }
            .tyg-div-denglv{
                z-index:1000;float:right;position:absolute;right:3%;top:10%;
            }
            .tyg-div-form{
                background-color: #23305a;
                width:300px;
                height:auto;
                margin:120px auto 0 auto;
                color:#2ec0f6;
            }
            .tyg-div-form form {padding:10px;}
            .tyg-div-form form input[type="text"]{
                width: 270px;
                height: 30px;
                margin: 25px 10px 0px 0px;
            }
            .tyg-div-form form {padding:10px;}
            .tyg-div-form form input[type="password"]{
                width: 270px;
                height: 30px;
                margin: 25px 10px 0px 0px;
            }
            .tyg-div-form form button {
                cursor: pointer;
                width: 270px;
                height: 44px;
                margin-top: 25px;
                padding: 0;
                background: #2ec0f6;
                -moz-border-radius: 6px;
                -webkit-border-radius: 6px;
                border-radius: 6px;
                border: 1px solid #2ec0f6;
                -moz-box-shadow:
                        0 15px 30px 0 rgba(255,255,255,.25) inset,
                        0 2px 7px 0 rgba(0,0,0,.2);
                -webkit-box-shadow:
                        0 15px 30px 0 rgba(255,255,255,.25) inset,
                        0 2px 7px 0 rgba(0,0,0,.2);
                box-shadow:
                        0 15px 30px 0 rgba(255,255,255,.25) inset,
                        0 2px 7px 0 rgba(0,0,0,.2);
                font-family: 'PT Sans', Helvetica, Arial, sans-serif;
                font-size: 14px;
                font-weight: 700;
                color: #fff;
                text-shadow: 0 1px 2px rgba(0,0,0,.1);
                -o-transition: all .2s;
                -moz-transition: all .2s;
                -webkit-transition: all .2s;
                -ms-transition: all .2s;
            }
        </style>
        <script type="text/javascript" src="/easyui/common.js"></script>
        <script type="text/javascript">
            $(function () {
                if (top != window) {// 如果不是顶级
                    //把子页面的地址，赋值给顶级页面显示
                    window.top.location.href = window.location.href;
                };
                //绑定点击事件【AJAX异步登录】
                $("#submit").click(function () {
                    var params = $("#loginForm").toJson();
                    $.postJSON("/page/login",params,function (data) {
                        if (data.status == 200){
                            //跳转到欢迎页面
                            location.href = "/page/index";
                        }else {
                            //登录失败
                            $("#errorMsg").text(data.msg);
                        }
                    });
                });

                //绑定键盘事件【回车键登录】
                $(document).keypress(function (event) {
                    if (event.keyCode == 13){
                        $("#submit").trigger("click");//触发登录按钮的点击事件
                    }
                });
            });
        </script>
    </head>
    <body>
    <div class="tyg-div">
        <ul>
            <li>让</li>
            <li><div style="margin-left:20px;">数</div></li>
            <li><div style="margin-left:40px;">据</div></li>
            <li><div style="margin-left:60px;">改</div></li>
            <li><div style="margin-left:80px;">变</div></li>
            <li><div style="margin-left:100px;">生</div></li>
            <li><div style="margin-left:120px;">活</div></li>
        </ul>
    </div>
    <div id="contPar" class="contPar">
        <div id="page1"  style="z-index:1;">
            <div class="title0">xxx智能商贸系统</div>
            <div class="imgGroug">
                <ul>
                    <img alt="" class="img0 png" src="/img/page1_0.jpg">
                    <img alt="" class="img1 png" src="/img/page1_1.jpg">
                    <img alt="" class="img2 png" src="/img/page1_2.jpg">
                </ul>
            </div>
            <img alt="" class="img3 png" src="/img/page1_3.jpg">
        </div>
    </div>
    <div class="tyg-div-denglv">
        <div class="tyg-div-form">
            <form id="loginForm">
                <h2>登录</h2><p class="tyg-p">欢迎访问xxx</p>
                <div style="margin:5px 0px;">
                    <input type="text" name="username" placeholder="用户名"/>
                </div>
                <div style="margin:5px 0px;">
                    <input type="password" name="password" placeholder="密码"/>
                </div>
                <span id="errorMsg" style="color: red"></span>
                <button id="submit" type="button" >登<span style="width:20px;"></span>录</button>
            </form>
        </div>
    </div>

<%--    More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>--%>
    <script type="text/javascript" src="/js/com.js"></script>
    </body>
</html>
