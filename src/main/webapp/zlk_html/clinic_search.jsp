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
            // alert("lalala");
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
     </script>
</head>
<body>
<div class="bjui-pageContent">
    <table class="table table-bordered" id="datagrid-test-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '临床信息',
        showToolbar: false,
        dataUrl: 'baseInfo/getClinicInfo1?idcard=${param.idcard}',
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
        <th rowspan="2" align="center" data-options="{name:'idcard',align:'center',width:170,menu:false}">身份证号</th>
        <th rowspan="2" align="center" data-options="{name:'patname',align:'center',width:170,menu:false}">姓名</th>
        <th rowspan="2" align="center" data-options="{name:'patgender',align:'center',width:170,menu:false}">性别</th>
        <th rowspan="2" align="center" data-options="{name:'age',align:'center',width:170,menu:false}">年龄</th>
        <th rowspan="2" align="center" data-options="{name:'entity',align:'center',width:170,menu:false}">病种</th>
        <th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:checkReport_Operation}">查看详情</th>
        </thead>
    </table>
</div>
</body>
</html>