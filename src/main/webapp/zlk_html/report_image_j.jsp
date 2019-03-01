<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    double randomNum = Math.random();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
    <script language="text/javascript" src="js/jquery.jqprint-0.3.js"></script>
    <script type="text/javascript">
        function print_report_image(){
            $("#image").jqprint();
        }
        function download_report_image() {
            document.getElementById("download_report").click();
        }
    </script>
</head>
<body>
<div class="bjui-pageContent">
    <div id="image">
        <img id="reportImage" src="/search/${param.check_number}/<%=randomNum%>/loadReportImage"></img>
    </div>
</div>
<div class="bjui-pageFooter">
    <button type="button" class="btn-blue" onclick="print_report_image();">打印</button>
    <button type="button" class="btn-blue" onclick="download_report_image();">下载</button>
    <a hidden id="download_report" href="/search/${param.check_number}/<%=randomNum%>/loadReportImage" download="${param.check_number}.jpg">下载</a>
</div>
</body>
</html>

