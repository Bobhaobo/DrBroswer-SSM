<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="zlk_js/allocate_auth.js"></script>
<div class="bjui-pageContent">
    <input type="hidden" id="name1" />
    <input type="hidden" id="modality1" />
    <input type="hidden" id="pat_type1" />
    <input type="hidden" id="number1" />
    <input type="hidden" id="Sex1" />
    <input type="hidden" id="Age1" />
    <form action="" id="j_patient_form" name="j_patient_form">
        <fieldset><legend style="font-family:verdana;font-size:130%;color:orange;">患者信息检索：</legend>
            <div class="bjui-row col-4">
                <label class="row-label">患者姓名：</label>
                <div class="row-input">
                    <input type="text" name="name" id="name" class="name" size="20"  onchange="connectName(this.value)">
                </div>
                <label class="row-label">患者性别：</label>
                <div class="row-input">
                    <select name="modality" id="sex" class="modality" data-toggle="selectpicker" data-width="150"  onchange="connectSex(this.value)">
                        <option value = "">全部</option>
                        <option value = "男">男</option>
                        <option value = "女">女</option>
                    </select>
                </div>

                <label class="row-label">患者年龄：</label>
                <div class="row-input">
                    <input type="text" name="name" id="age" class="name" size="20"  onchange="connectAge(this.value)">
                </div>
                <label class="row-label">检查类型：</label>
                <div class="row-input">
                    <select name="modality" id="modality" class="modality" data-toggle="selectpicker" data-width="150"  onchange="connectModality(this.value)">
                        <option value = "">全部</option>
                        <option value = "CT">CT</option>
                        <option value = "CR">CR</option>
                        <option value = "DX">DX</option>
                        <option value = "MR">MR</option>
                        <option value = "US">US</option>
                    </select>
                </div>
                <label class="row-label">患者类型：</label>
                <div class="row-input">
                    <select name="pat_type" id="pat_type"  class="pat_type" data-toggle="selectpicker" data-width="150"  onchange="connectPat_type(this.value)">
                        <option value = "">全部</option>
                        <option value = "门诊">门诊</option>
                        <option value = "住院">住院</option>
                        <option value = "急诊">急诊</option>
                        <option value = "其他">其他</option>
                    </select>
                </div>
                <label class="row-label">检查号：</label>
                <div class="row-input">
                    <input type="text" name="number" id="number" class="number" size="20" onchange="connectNumber(this.value)">
                </div>
                <%--<label class="row-label">起始日期：</label>--%>
                <%--<div class="row-input">--%>
                    <%--<input type="text" value="" class="dateBegin" data-toggle="datepicker" placeholder="点击选择日期" name="dateBegin" id="dateBegin" size="20" onchange="connectDataBegin(this.value)">--%>
                <%--</div>--%>
                <%--<label class="row-label">终止日期：</label>--%>
                <%--<div class="row-input">--%>
                    <%--<input type="text" value="" class="dateEnd" data-toggle="datepicker" placeholder="点击选择日期" name="dateEnd" id="dateEnd" size="20" onchange="connectDataEnd(this.value)">--%>
                <%--</div>--%>
                <%--<label class="row-label">医生：</label>--%>
                <%--<div class="row-input">--%>
                    <%--<input type="text" name="number" id="doctor" class="number" size="20">--%>
                <%--</div>--%>
                <label class="row-label">医生：</label>
                <div class="row-input">
                <select name="doctor" id="doctor"  data-toggle="selectpicker" data-width="150" data-rule="required">
                    <option value="" selected="selected">-----请选择-----</option>
                </select>
                </div>
            </div>
        </fieldset>
    </form>
    <table id="checkReportToday" class="table table-bordered" id="datagrid-test-filter" data-toggle="datagrid" data-options="{
        	height: '100%',
        	gridTitle : '患者信息',
        	showToolbar: false,
        	dataUrl: 'remote/getSearchResults',
        	dataType: 'json',
        	jsonPrefix: 'obj',
        	paging: {pageSize:60},
        	showCheckboxcol: false,
        	linenumberAll: true,
        	contextMenuH: false,
        	filterThead: false,
        	showToolbar: true,
    		toolbarItem: 'refresh',
    	}">
        <thead>
        <tr>
            <th rowspan="2" align="center" data-options="{name:'checknum',align:'center',width:170,menu:false}">检查号</th>
            <th colspan="3" align="center">患者信息</th>
            <th colspan="2" align="center">检查信息</th>
            <%--<th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:select_doctor}">选择医生</th>--%>
            <th rowspan="2" align="center" data-options="{align:'center',width:100,menu:false,render:allocate_doctor}">分配医生</th>
        </tr>
        <tr>
            <th align="center" data-options="{name:'patName',align:'center',width:100,menu:false}">姓名</th>
            <th align="center" data-options="{name:'patGender',align:'center',width:70,menu:false}">性别</th>
            <th align="center" data-options="{name:'age',align:'center',width:50,menu:false}">年龄</th>
            <th align="center" data-options="{name:'pattype',align:'center',width:100,menu:false}">患者类型</th>
            <th align="center" data-options="{name:'modality',align:'center',width:180,menu:false}">检查类型</th>
            <%--<th align="center" data-options="{name:'sqks',align:'center',width:180,menu:false}">科室</th>--%>
            <%--<th align="center" data-options="{name:'report_Date',align:'center',type:'date',pattern:'yyyy-MM-dd HH:mm',render:function(value){return value?value.substr(0,16):value},width:200,menu:false}">报告日期</th>--%>
        </tr>
        </thead>
    </table>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-orange" id="qingkong">搜索重置</button></li>
        <li><button type="button" class="btn-default" id="report_search_j213">报告查询</button></li>
    </ul>
</div>
