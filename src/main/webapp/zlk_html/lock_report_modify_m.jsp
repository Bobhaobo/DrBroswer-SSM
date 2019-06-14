<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/lock_report_modify_m.js"></script>
    <link rel="stylesheet" href="jsTree/theme/style.min.css" />
    <link rel="stylesheet" href="zlk_css/report_write_m.css" />
</head>
<body>
<div class="bjui-pageContent">
    <div class="writeReport">
        <input id="bgCode" type="hidden" value="${param.bgCode}" />
        <div class="reportHead">
            <div class="icon"><img src="images/redcross.png" /></div>
            <div class="hostitle"><span id="hosName"></span></div>
        </div>
        <hr width="750" align="center">
        <div class="patientInfo">
            <div class="row_1">
                <div class="patName">姓名：<input id="pat_name" type="text" value="${param.patName}" size="10" readonly="" /></div>
                <div class="patGender">性别：<input id="pat_gender" type="text" value="${param.patGender}" size="10" readonly="" /></div>
                <div class="patAge">年龄：<input id="pat_age" type="text" value="${param.patAge}" size="10" readonly="" /></div>
                <div class="patCheckNum">检查号：<input id="pat_checknum" type="text" value="${param.checknum}" size="10" readonly="" /></div>
                <div class="keshi">科室：<input id="deptName" type="text" size="10" readonly=""/></div>
            </div>
            <div class="row_2">
                <div class="zhuyuanhao">住院号：<input id="clinicId" type="text" size="10" readonly=""/></div>
                <div class="chuanghao">床号：<input id="bedNo" type="text" size="10" readonly=""/></div>
                <div class="jcbw">检查部位：<input id="jcbw" type="text" size="15" readonly=""/></div>
                <div class="yinoryangxing">阴/阳性：<input id="sfyangxing" type="text" size="15" readonly=""/></div>
            </div>
        </div>
        <hr width="750" align="center">
        <div style="margin-left:12%;">
            <label class="row-label">影像所见：</label><br/>
            <textarea id="examDesc" rows="12" cols="65" readonly=""></textarea><br/>
            <label class="row-label">印象：</label><br/>
            <textarea id="examDiagnosis" rows="12" cols="65" readonly=""></textarea>
        </div>
    </div>
</div>
</body>
</html>

