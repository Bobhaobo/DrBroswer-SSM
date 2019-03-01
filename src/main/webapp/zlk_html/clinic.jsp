<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/clinic.js"></script>

    <style type="text/css">
        .main td{ height:30px;}
    </style>

</head>
<body>
<div class="bjui-pageContent">
    <table style="margin-left:70px;" class="main">
        <caption>临床详情</caption>
        <tr>
            <td width="70px">身份证号：</td>
            <td id="idCard">${param.idCard}</td>
        <tr/>
        <tr>
            <td>姓名：</td>
            <td id="patName"></td>
        </tr>
        <tr>
            <td>性别：</td>
            <td id="patGender"></td>
        </tr>
        <tr>
            <td>病种：</td>
            <td id="entity1"></td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td id="patBrithdate"></td>
        </tr>
        <tr>
            <td>家庭住址：</td>
            <td id="address"></td>
        </tr>
        <tr>
            <td>医保号：</td>
            <td id="yibaoId"></td>
        </tr>
        <tr>
            <td>联系电话：</td>
            <td id="telephone"></td>
        </tr>
        <tr>
            <td>年龄：</td>
            <td id="ageage"></td>
        </tr>
        <tr>
            <td>录入时间：</td>
            <td id="updateTime"></td>
        </tr>
        <tr>
            <td>临床医生：</td>
            <td id="clinicDoc"></td>
        </tr>
        <tr>
            <td>主诉：</td>
            <td id="mainSuit"></td>
        </tr>
        <tr>
            <td>个人史：</td>
            <td id="personalHis"></td>
        </tr>
        <tr>
            <td>婚姻史：</td>
            <td id="maritalHis"></td>
        </tr>
        <tr>
            <td>家族史：</td>
            <td id="familyHis"></td>
        </tr>
        <tr>
            <td>既往史：</td>
            <td id="pastIllnessHis"></td>
        </tr>
        <tr>
            <td>现病史：</td>
            <td id="presentIllnessHis"></td>
        </tr>
        <tr>
            <td>专科检查：</td>
            <td id="specialityCheck"></td>
        </tr>
        <tr>
            <td>初步诊断：</td>
            <td id="tentativeDiagnosis"></td>
        </tr>
    </table>
    <input type="hidden" value="" id="examItemCode" />
    <br/>
    <%--<hr width="750" align="center">--%>
    <%--<div style="margin-left:70px;">--%>
        <%--<label class="row-label">安排检查时间：</label>--%>
        <%--<span id="date"></span><br/>--%>
        <%--<label class="row-label">检查类型：</label>--%>
        <%--<span id="type"></span><br/>--%>
        <%--<label class="row-label">检查设备：</label>--%>
        <%--<span id="device"></span><br/>--%>
    <%--</div>--%>
</div>
</body>
</html>

