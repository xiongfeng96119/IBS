<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <meta charset="utf-8">
        <%@include file="/WEB-INF/views/common.jsp"%>
        <script type="text/javascript">
            $(function () {
               // $("#fb").filebox({
               //     onChange:function (newValue, oldValue) {
               //         var filebox = $(".textbox-value[type=file]");
               //         var file = filebox[0].files[0];
               //         var xhr = new XMLHttpRequest();
               //         xhr.open("POST","/carousel/upload.do",true);
               //         xhr.onload = function(){
               //             if(xhr.readyState == 4 && xhr.status == 200){
               //
               //             }
               //         }
               //         var formData = new FormData();		//键值对集合
               //         formData.append("imgFile",file);
               //         xhr.send(formData);
               //     }
               // });
                $("#btn-import").click(function () {
                     $("#ff")[0].submit();
                });
            });
        </script>
    </head>
    <body>
        <form method="post" action="/import/importEmployees" id="ff" enctype="multipart/form-data">
            <input id="fb" class="easyui-filebox" type="text" style="width:300px;" name="excelFile" data-options="buttonText:'选择文件'" />
            <a href="javascript:void(0);" id="btn-import" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导入</a>
        </form>
    </body>
</html>
