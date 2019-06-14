<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="zlk_js/report_modify_m.js"></script>
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
            <div id="evts" class="demo"></div>
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
                <div id="examDesc_text"><span><strong>影像所见：</strong></span><p id="examDesc_text_p"></p></div>
                <hr style="width:350px; align:center;margin: 2px 0px 2px 0px" width="350">
                <div id="examDiagnosis_text"><span><strong>印象：</strong></span><p id="examDiagnosis_text_p"></p></div>
                <hr style="width:350px; align:center;margin: 2px 0px 6px 0px" width="350">
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
    </ul>
</div>
</body>
</html>

