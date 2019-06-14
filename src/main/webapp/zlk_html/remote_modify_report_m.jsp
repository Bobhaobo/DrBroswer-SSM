<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<script src="zlk_js/remote_register_j.js"></script>--%>
<%--<div class="bjui-pageContent">--%>
    <%--<div style="margin:0 3% 0 3%">--%>
        <%--<form id="register" data-toggle="validate" >--%>
            <%--<fieldset>--%>
                <%--<legend style="font-family:verdana;font-size:130%;color:orange">患者信息登记：</legend>--%>
                <%--<div class="bjui-row col-3">--%>
                    <%--<hr style="height:3px;">--%>
                    <%--<label class="row-label">患者姓名：</label>--%>
                    <%--<div class="row-input required">--%>
                        <%--<input type="text" name="PatName" class="PatName">--%>
                    <%--</div><br/>--%>
                    <%--<label class="row-label">性别：</label>--%>
                    <%--<div class="row-input required">--%>
                        <%--<input type="radio" name="PatGender" data-toggle="icheck" value="男" data-rule="checked" data-label="男&nbsp;&nbsp;&nbsp;">--%>
                        <%--<input type="radio" name="PatGender" data-toggle="icheck" value="女" data-label="女">--%>
                    <%--</div>--%>
                    <%--<label class="row-label">已检查类型：</label>--%>
                    <%--<div class="row-input required">--%>
                        <%--<select id="CheckType" name="CheckType" class="modality" data-toggle="selectpicker" data-width="150">--%>
                            <%--<option value = "CT">CT</option>--%>
                            <%--<option value = "CR">CR</option>--%>
                            <%--<option value = "DX">DX</option>--%>
                            <%--<option value = "MR">MR</option>--%>
                            <%--<option value = "US">US</option>--%>
                        <%--</select>--%>
                    <%--</div><br/>--%>
                    <%--<label class="row-label">出生日期：</label>--%>
                    <%--<div class="row-input required">--%>
                        <%--<input type="text" value="" data-toggle="datepicker" placeholder="点击选择日期" name="brithday" data-pattern="yyyy-MM-dd HH:mm:ss">--%>
                    <%--</div>--%>
                    <%--<label class="row-label">患者类型：</label>--%>
                    <%--<div class="row-input">--%>
                        <%--<input type="radio" name="PatType" data-toggle="icheck" value="门诊" data-label="门诊">--%>
                        <%--<input type="radio" name="PatType" data-toggle="icheck" value="住院" data-label="住院">--%>
                        <%--<input type="radio" name="PatType" data-toggle="icheck" value="急诊" data-label="急诊">--%>
                        <%--<input type="radio" name="PatType" data-toggle="icheck" value="其他" data-label="其他">--%>
                    <%--</div><br/>--%>
                    <%--<label class="row-label">身份证号：</label>--%>
                    <%--<div class="row-input">--%>
                        <%--<input type="text" name="PatIDCard" class="PatIDCard">--%>
                    <%--</div>--%>
                    <%--<label class="row-label">联系方式：</label>--%>
                    <%--<div class="row-input">--%>
                        <%--<input type="text" name="tel" class="tel">--%>
                    <%--</div><br/>--%>
                    <%--<label class="row-label">住址：</label>--%>
                    <%--<div class="row-input">--%>
                        <%--<input type="text" name="address" class="address">--%>
                    <%--</div>--%>
                    <%--<label class="row-label">医保号：</label>--%>
                    <%--<div class="row-input">--%>
                        <%--<input type="text" name="societyID" class="societyID">--%>
                    <%--</div><br/>--%>
                <%--</div>--%>
                <%--<hr style="height:3px;">--%>
                <%--<div>--%>
                    <%--<div style="float:left;margin-left:10%">--%>
                        <%--<p id="qianzhui" style="float:left">生成的远程检查号：<p id="r_checknum" style="float:left"></p>--%>
                    <%--</div>--%>
                    <%--<div style="margin:0 0 1% 15%;float:left">--%>
                        <%--<a id="uploadDicom" href="#" type="hidden">上传图像</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</fieldset>--%>
        <%--</form>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<div class="bjui-pageFooter">--%>
    <%--<ul>--%>
        <%--<li><button type="reset" class="btn-orange" form="register">重置表单</button></li>--%>
        <%--<li><button type="submit" class="btn-default" data-icon="save" id="remoteDiagReg">生成患者ID并登记</button></li>--%>
    <%--</ul>--%>
<%--</div>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/remote_modify_report_m.js"></script>
    <link rel="stylesheet" href="jsTree/theme/style.min.css" />
    <link rel="stylesheet" href="zlk_css/remote_modify_report_m.css" />
</head>
<body>
<div class="bjui-pageContent">
    <div class="writeReport">
        <input id="bgCode" type="hidden" value="${param.id}" />
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
                <div class="patCheckNum">检查号：<input id="pat_checknum" type="text" value="${param.checknum}" size="15" readonly="" /></div>
                <div class="keshi">科室：<input id="deptName" type="text" size="10" /></div>
            </div>
            <div class="row_2">
                <div class="zhuyuanhao">住院号：<input id="clinicId" type="text" size="10" /></div>
                <div class="chuanghao">床号：<input id="bedNo" type="text" size="10" /></div>
                <div class="jcbw">检查部位：<input id="jcbw" type="text" size="15" /></div>
                <div class="yinoryangxing">阴/阳性：<select id="sfyangxing" data-toggle="selectpicker" data-width="100"><option value="阴">阴</option><option value="阳">阳</option></select></div>
            </div>
        </div>
        <hr width="750" align="center">
        <div style="margin-left:12%;">
            <label class="row-label">影像所见：</label><br/>
            <textarea id="examDesc" rows="12" cols="65"></textarea><br/>
            <label class="row-label">印象：</label><br/>
            <textarea id="examDiagnosis" rows="12" cols="65"></textarea>
        </div>
    </div>
    <div class="template">
        <fieldset>
            <legend style="font-family:verdana;font-size:105%;color:orange;">报告模板</legend>
            <div id="evts" class="demo" style="height: 300px"></div>
            <script src="jsTree/js/jstree.min.js"></script>
            <script>
                $('#evts')
                    .on("changed.jstree", function (e, data) {
                        if(data.selected.length) {
                            $.ajax({
                                type:"get",
                                url:"template/" + data.instance.get_node(data.selected[0]).id + "/getTemplateDetail",
                                dataType:"html",
                                async:false,
                                success:function(data){
                                    var pro=null;
                                    pro = eval("("+data+")");
                                    $("#examDesc_text_p").html(pro.examdesc);
                                    $("#examDiagnosis_text_p").html(pro.examdiagnosis);
                                }
                            });
                        }
                    })
                    .jstree({
                        'core' : {
                            'multiple' : false,
                            'data' : {
                                "url" : "template/loadTemplate",
                                "dataType" : "json" // needed only if you do not supply JSON headers
                            }
                        }
                    });
            </script>
            <hr style="width:350px; align:center;margin: 2px 0px 2px 0px">
            <div class="template_text">
                <div id="examDesc_text" style="height: 150px;"><span><strong>影像所见：</strong></span><p id="examDesc_text_p"></p></div>
                <hr style="width:350px; align:center;margin: 2px 0px 2px 0px" width="350">
                <div id="examDiagnosis_text" style="height: 150px;"><span><strong>印象：</strong></span><p id="examDiagnosis_text_p"></p></div>
                <hr style="width:350px; align:center;margin: 2px 0px 6px 0px" width="350">
                <div id="suggestion_text" style="height: 100px;"><span><strong>修改意见：</strong></span><textarea id="suggestion_text_p" style="height: 90px;border:none;width: 350px"></textarea></div>
                <hr style="width:350px; align:center;margin: 2px 0px 6px 0px" width="350">
                <%--<label class="row-label">修改意见：</label><br/>--%>
                <%--<textarea id="suggestion" rows="12" cols="30"></textarea>--%>
                <a id="imagePath" href="DrViewerBoot://${param.imagePath}">查看图像</a>
                <button style="position:relative;left:30%" type="button" class="btn-orange" id="qingkong">清空</button>
                <button style="position:relative;left:30%" type="button" class="btn-green" id="tihuan">替换</button>
                <button style="position:relative;left:30%" type="button" class="btn-blue" id="zhuijia">追加</button>
            </div>
        </fieldset>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="submit" class="btn-default" id="modifyReport">提交修改</button></li>
        <li><button type="submit" class="btn-default" id="save">保存</button></li>
    </ul>
</div>
</body>
</html>

