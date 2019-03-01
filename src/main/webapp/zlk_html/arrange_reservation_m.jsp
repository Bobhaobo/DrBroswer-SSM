<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String checknum = request.getParameter("checknum");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/arrange_reservation_m.js"></script>
</head>
<body>
<div class="bjui-pageContent">
    <form data-toggle="ajaxform" method="post" data-close-current="true">
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
            <tr><!--    -->
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
                <td>申请检查日期：</td>
                <td id="orderDate"></td>
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
            <div class="row-input">
                <input type="text" data-toggle="datepicker" placeholder="点击选择日期" id="arrangeDate" size="15"/>
                <select data-toggle="selectpicker" data-width="70">
                    <option>8:30</option>
                    <option>9:00</option>
                    <option>9:30</option>
                    <option>10:00</option>
                    <option>10:30</option>
                    <option>11:00</option>
                    <option>11:30</option>
                </select>
            </div>
            <label class="row-label">检查类型：</label>
            <div class="row-input">
                <select id="subModality" class="subModality" data-width="150" data-toggle="selectpicker" >
                </select>
            </div>
            <label class="row-label">检查设备：</label>
            <div class="row-input">
                <select id="checkDevItem" class="checkDevItem" data-toggle="selectpicker" data-width="150" >
                    <option value='0'>--请选择--</option>
                </select>
            </div>
            <br/>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="submit" class="btn-default" id="arrangeCheck">提交安排</button></li>
    </ul>
</div>
</body>
</html>
