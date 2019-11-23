<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="/s/login" method="post">
            <input type="text" name="username" placeholder="用户名" />
            <input type="text" name="password" placeholder="密码" />
            <button type="submit">登录</button>
        </form>
        ${errorMsg}
    </body>
</html>
