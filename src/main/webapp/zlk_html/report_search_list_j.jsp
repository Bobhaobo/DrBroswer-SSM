<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        function checkReport_Operation(value,data) {
            if(data.check_number == "没有数据！"){
                var html = null;
            }else{
                var html = '<button type="button" class="btn-blue" onclick="dialog_report(\''+data.check_number+'\');">查看报告</button>';
            }
            return html;
        }
        function dialog_report(check_number) {
            BJUI.dialog({
                id:'reportImageToday_check_number-'+check_number,
                url:'zlk_html/report_image_j.jsp?check_number='+check_number,
                height: 890,
                width: 830,
                title:'检查报告单'
            });
        }
    </script>
</head>
<body>
<div class="bjui-pageContent">
    <table class="table table-bordered" id="datagrid-test-filter" data-toggle="datagrid" data-options="{
            height: '100%',
            gridTitle : '患者信息',
            showToolbar: false,
            dataUrl: '/search/getReportListByCondition?modality=${param.modality}&pat_type=${param.pat_type}&dateBegin=${param.dateBegin}&dateEnd=${param.dateEnd}&name=${param.name}&ID=${param.ID}&number=${param.number}&clinic_id=${param.clinic_id}',
            dataType: 'json',
            jsonPrefix: 'obj',
            paging: {pageSize:60},
            showCheckboxcol: false,
            linenumberAll: true,
            contextMenuH:false,
            filterThead:false,
            showToolbar: true,
            toolbarItem: 'refresh',
        }">
        <thead>
        <tr>
            <th rowspan="2" align="center" data-options="{name:'check_number',align:'center',width:170,menu:false}">检查号</th>
            <th colspan="4" align="center">患者信息</th>
            <th colspan="3" align="center">检查信息</th>
            <th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:checkReport_Operation}">查看报告</th>
        </tr>
        <tr>
            <th align="center" data-options="{name:'patient_Name',align:'center',width:100,menu:false}">姓名</th>
            <th align="center" data-options="{name:'patient_Sex',align:'center',width:70,menu:false}">性别</th>
            <th align="center" data-options="{name:'patient_Age',align:'center',width:50,menu:false}">年龄</th>
            <th align="center" data-options="{name:'pat_type',align:'center',width:100,menu:false}">病人类型</th>
            <th align="center" data-options="{name:'modality',align:'center',width:180,menu:false}">检查类型</th>
            <th align="center" data-options="{name:'sqks',align:'center',width:180,menu:false}">科室</th>
            <th align="center" data-options="{name:'report_Date',align:'center',type:'date',pattern:'yyyy-MM-dd HH:mm',render:function(value){return value?value.substr(0,16):value},width:200,menu:false}">报告日期</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
