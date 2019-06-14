<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="zlk_js/flow_detail.js"></script>
</head>
<body>
<table id="tab">
    <tr>
        <td >检查号</td>
        <td ><div id="checknum1">${param.checknum}</div></td>
        <td><input id="123" value="${param.checknum}" type="hidden"/></td>
    </tr>
    <tr>
        <td >姓名</td>
    <td><div id="name1"></div></td>
    </tr>
    <tr>
        <td >性别</td>
    <td><div id="sex1"></div></td>
    </tr>
    <tr>
    <td >年龄</td>
    <td><div id="age1"></div></td>
    </tr>
    <tr>
        <td >患者来源</td>
    <td><div id="pattype1"></div></td>
    </tr>
    <tr>
        <td >申请医院</td>
    <td><div id="remotehos1"></div></td>
    </tr>
    <tr>
        <td >检查类型</td>
        <td><div id="zxc"></div></td>
    </tr>
    <tr id="www">
        <td >当前状态</td>
    <td><div id="status1"></div></td>
    </tr>
</table>
    <%--</thead>--%>
<%--</table>--%>
</body>
</html>
