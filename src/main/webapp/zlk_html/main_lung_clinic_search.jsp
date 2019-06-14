<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function checkReport_Operation(value,data) {
            // alert(data);
            // alert(value);
            if(data.idcard == "没有数据！"){
                var html = null;
            }else{
                var html = '<button type="button" class="btn-blue" onclick="dialog_report(\''+ data.idcard +'\');">临床详情</button>';
            }
            return html;
        }
        function dialog_report(idcard) {
            alert("123");
            alert("lalala");
            var timenow = new Date().getTime();
            // alert(idcard);
            BJUI.dialog({
                id:'arrangedDetial_time'+timenow,
                width: 830,
                height: 700,
                url: 'zlk_html/clinic.jsp?idCard=' + idcard ,
                title:'临床详情'
            });
        }
        $("#exportTable2").click(function(){
            $.ajax({
                type:"post",
                url:"baseInfo/getClinicInfo3?idcard=${param.idCard}&patname123=${param.patName1s2}&patientid123=${param.patientId909}&updatetime123=${param.datetime23}&entity123=${param.entity02}",
                dataType: "html",
                async:false,
                success:function(data) {
                    alert(data);
                    var jsonData=null;
                    jsonData=eval("("+data+")");
                    alert(jsonData);
                    if(jsonData[0]) {
                        alert("1");
                        var str = '<tr><td>姓名</td><td>性别</td><td>年龄</td><td>医保号</td><td>病历号</td><td>身份证号</td><td>病种</td><td>出生日期</td><td>联系电话</td><td>家庭住址</td><td>录入时间</td><td>临床医生</td><td>主诉</td><td>个人史</td><td>婚姻史</td><td>家族史</td><td>既往史</td><td>现病史</td><td>专科检查</td><td>初步诊断</td></tr>';
                        //循环遍历，每行加入tr标签，每个单元格加td标签
                        var count = (Object.keys(jsonData).length);
                        console.log(count);
                        for (let i = 0; i < count; i++) {
                            // alert("321");
                            str += '<tr>';
                            for (let item in jsonData[i]) {
                                // alert("123");
                                str += '<td>' + jsonData[i][item] +'</td>';
                                // alert(jsonData[i]);
                                // alert(str);
                                // alert(jsonData[i][item]);
                            }
                            str = str + '</tr>';
                        }
                    }else{
                        var str = '<tr><td>姓名</td><td>性别</td><td>年龄</td><td>医保号</td><td>病历号</td><td>身份证号</td><td>病种</td><td>出生日期</td><td>联系电话</td><td>家庭住址</td><td>录入时间</td><td>临床医生</td><td>主诉</td><td>个人史</td><td>婚姻史</td><td>家族史</td><td>既往史</td><td>现病史</td><td>专科检查</td><td>初步诊断</td></tr>';
                        //循环遍历，每行加入tr标签，每个单元格加td标签
                        var count = (Object.keys(jsonData).length)/20;
                        console.log(count);
                        for (let i = 0; i < count; i++) {
                            // alert("321");
                            str += '<tr>';
                            for (let item in jsonData) {
                                // alert("123");
                                str += '<td>' + jsonData[item] + '</td>';
                                // alert(jsonData[i]);
                                // alert(str);
                                // alert(jsonData[item]);
                            }
                            str = str + '</tr>';
                        }
                    }
                    //Worksheet名
                    var worksheet = '肺部临床';
                    var uri = 'data:application/vnd.ms-excel;base64,';

                    //下载的表格模板数据
                    var template = `<html xmlns:o="urn:schemas-microsoft-com:office:office"
      xmlns:x="urn:schemas-microsoft-com:office:excel"
      xmlns="http://www.w3.org/TR/REC-html40">
      <head>
      <!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>
        <x:Name>\${worksheet}</x:Name>
        <x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet>
        </x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->
        </head>
        <body><table>\${str}</table></body></html>`;
                    //下载模板
                    alert("\"" + str + "\"");
                    window.location.href = uri + base64(template);
                }
            });
        });

        //输出base64编码

        function base64 (s)
        {
            alert("lalala");
            return window.btoa(unescape(encodeURIComponent(s)))
        }
    </script>
</head>
<body>
<div class="bjui-pageContent">
    <table class="table table-bordered" id="datagrid-test-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '临床信息',
        showToolbar: false,
        dataUrl: 'baseInfo/getClinicInfo2?idcard=${param.idCard}&patname123=${param.patName1s2}&patientid123=${param.patientId909}&updatetime123=${param.datetime23}&entity123=${param.entity02}',
        dataType: 'json',
        jsonPrefix: 'obj',
        paging: {pageSize:60},
        showCheckboxcol: false,
        linenumberAll: true,
        contextMenuH:false,
        filterThead:false,
        showToolbar: true,
    	toolbarItem: 'refresh'
    }">
        <thead>
        <%--<input  id="hide1" name="hide1" size="20" class="code" value="${param.idCard}"/>--%>
        <tr>
            <%--<input value="${param.idCards8}"  type="hidden" id="patIdCards1">--%>
            <%--<input value="${param.patName1s}" type="hidden" id="patNames1">--%>
            <%--<input value="${param.patientId99}"  id="patientId123">--%>
            <th rowspan="2" align="center" data-options="{name:'idcard',align:'center',width:170,menu:false}">身份证号</th>
            <th colspan="5" align="center">患者信息</th>
            <th rowspan="2" align="center" data-options="{name:'updatetime',align:'center',width:200,menu:false}">报告日期</th>
            <th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:checkReport_Operation}">查看报告</th>
        </tr>
        <tr>
            <th align="center" data-options="{name:'patname',align:'center',width:100,menu:false}">姓名</th>
            <th align="center" data-options="{name:'patgender',align:'center',width:70,menu:false}">性别</th>
            <th align="center" data-options="{name:'age',align:'center',width:50,menu:false}">年龄</th>
            <th rowspan="2" align="center" data-options="{name:'entity',align:'center',width:170,menu:false}">病种</th>
            <th align="center" data-options="{name:'patientid',align:'center',width:180,menu:false}">病历号</th>
        </tr>
        <%--<th rowspan="2" align="center" data-options="{name:'idcard',align:'center',width:170,menu:false}">身份证号</th>--%>
        <%--<th rowspan="2" align="center" data-options="{name:'patname',align:'center',width:170,menu:false}">姓名</th>--%>
        <%--<th rowspan="2" align="center" data-options="{name:'patgender',align:'center',width:170,menu:false}">性别</th>--%>
        <%--<th rowspan="2" align="center" data-options="{name:'age',align:'center',width:170,menu:false}">年龄</th>--%>
        <%--<th rowspan="2" align="center" data-options="{name:'entity',align:'center',width:170,menu:false}">病种</th>--%>
        <%--<th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:checkReport_Operation}">查看详情</th>--%>
        </thead>
    </table>
</div>
</body>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-default" id="exportTable2">下载EXCEL</button></li>
    </ul>
</div>
</html>