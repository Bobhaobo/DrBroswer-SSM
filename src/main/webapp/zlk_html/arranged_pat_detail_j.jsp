<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/arranged_pat_detail_j.js"></script>
</head>
<body>
<div class="bjui-pageContent">
    <table style="margin-left:70px;">
        <caption>预约登记详情</caption>
        <tr>
            <td>检查号：</td>
            <td id="checkNum">${param.checknum}</td>
        <tr/>
        <tr>
            <td>记录号：</td>
            <td id="recordId"></td>
        </tr>
        <tr>
            <td>病人号：</td>
            <td id="patientId"></td>
        </tr>
        <tr>
            <td>登记时间：</td>
            <td id="registerTime"></td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td id="patientName"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td id="patientGender"></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td id="patientAge"></td>
        </tr>
        <tr>
            <td>申请检查类型：</td>
            <td id="examItemName"></td>
        </tr>
        <tr>
            <td>申请医院：</td>
            <td id="orderSource"></td>
        </tr>
    </table>
    <input type="hidden" value="" id="examItemCode" />
    <br/>
    <hr width="750" align="center">
    <div style="margin-left:70px;">
        <label class="row-label">安排检查时间：</label>
        <span id="date"></span><br/>
        <label class="row-label">检查类型：</label>
        <span id="type"></span><br/>
        <label class="row-label">检查设备：</label>
        <span id="device"></span><br/>
    </div>
</div>
</body>
</html>

