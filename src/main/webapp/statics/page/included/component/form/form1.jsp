<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form autocomplete="off" method="post" accept-charset="UTF-8" role="form" action="/admin/sign_up">
    <fieldset>
        <div class="form-group input-group">
            <label class="fa fa-user input-group-addon"></label>
            <input class="form-control" placeholder="用户名" name="loginName" type="text">
        </div>
        <div class="form-group input-group">
            <label class="fa fa-lock input-group-addon"></label>
            <input class="form-control" placeholder="密码" name="password" type="password" value="">
        </div>
        <div class="checkbox">
            <label>
                <input name="remember" type="checkbox" value="记住我"> 记住我
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><i class="fa fa-sign-in"></i> 登 录</button>
    </fieldset>
</form>

