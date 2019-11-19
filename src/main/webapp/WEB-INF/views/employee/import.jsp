<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <%@include file="../common.jsp"%>
        <script type="text/javascript">
            $(function () {
                $("#btn-import").click(function () {
                   $("#ff")[0].submit();
                });
            });
        </script>
    </head>
    <body style="padding-top: 100px;">
        <form method="post" action="/import/importEmployees" id="ff" enctype="multipart/form-data">
            <input id="fb" type="text" class="easyui-filebox" style="width: 300px;" name="excelFile"
               data-options="buttonText:'选择文件'" />
            <a href="javascript:void(0);" id="btn-import" class="easyui-linkbutton" data-options="iconCls:'icon-import'">提交</a>
        </form>
    </body>
</html>
