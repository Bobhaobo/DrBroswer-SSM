<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
$(function(){
        // alert("1111111111");
        $.ajax({
            type:"post",
            url:"pathology/getPathologyInfo?idcard022="+ $("#patIdCards1").val(),
            dataType: "html",
            async:false,
            success:function(data){
                alert(data);
                var pro=null;
                // pro=JSON.parse(data);
                pro=eval("("+data+")");
                // $("#pat_name").val(pro.list[0].patname);//赋值
                $("#patnamell21").val(pro.pathologydto.patname);
                alert(pro.pathologydto.patname);
                $("#age1321").val(pro.pathologydto.age);
                $("#genderll921").val(pro.pathologydto.sex);
                $("#ID321").val(pro.pathologydto.patientid);
                $("#IDcard121").val(pro.pathologydto.idcard);
                $("#examHos661").val(pro.pathologydto.sendHospital);
                $("#examDept661").val(pro.pathologydto.sendDept);
                $("#examDoc661").val(pro.pathologydto.checkDoc);
                $("#specimen1").val(pro.pathologydto.specimenName);
                $("#reportDate21").val(pro.pathologydto.reportDate);
                $("#entity111").val(pro.pathologydto.entity);
                // alert("$$$");

                document.getElementById("clinicDiagnosis1").innerHTML=(pro.pathologydto.clinicDiagnosis);
                document.getElementById("eyeView1").innerHTML=(pro.pathologydto.eyeView);
                document.getElementById("glassView1").innerHTML=(pro.pathologydto.glassView);
                document.getElementById("pathologySuggestion1").innerHTML=(pro.pathologydto.pathologyDiagnosis);
                // alert($("#pat_name").val());    //取值
                // alert("lalala");
                document.getElementById("myimage111").src=(pro.image);
            }
        });
    });
</script>
<div class="bjui-pageContent">
    <input value="${param.idCards1231}" type="hidden" id="patIdCards1">
    <form action="" id="j_patient_form1" name="j_patient_form">
        <fieldset><legend style="font-family:verdana;font-size:130%;color:orange;">检索结果：</legend>
            <div class="bjui-row col-4">
                <label class="row-label">患者姓名：</label>
                <div class="row-input">
                    <input type="text" name="patname66" id="patnamell21" class="name" size="20">
                </div>
                <label class="row-label">性别：</label>
                <div class="row-input">
                    <input type="text" name="genderll" id="genderll921" class="clinic_id" size="20">
                </div>
                <label class="row-label">年龄：</label>
                <div class="row-input">
                    <input type="text" name="age13" id="age1321" class="age13" size="20">
                </div>
                <label class="row-label">病人ID：</label>
                <div class="row-input">
                    <input type="text" name="ID3" id="ID321" class="ID" size="20">
                </div>
                <label class="row-label">身份证号：</label>
                <div class="row-input">
                    <input type="text" name="ID3" id="IDcard121" class="ID" size="20">
                </div>
                <label class="row-label">病种：</label>
                <div class="row-input">
                    <input type="text" name="entity111" id="entity111"  size="20">
                </div>
                <label class="row-label">送检医院：</label>
                <div class="row-input">
                    <input type="text" name="examDoc6" id="examHos661"  size="20">
                </div>
                <label class="row-label">送检部门：</label>
                <div class="row-input">
                    <input type="text" name="examDoc6" id="examDept661"  size="20">
                </div>
                <label class="row-label">送检医师：</label>
                <div class="row-input">
                    <input type="text" name="examDoc6" id="examDoc661"  size="20">
                </div>

                <!--<label class="row-label">病种：</label>-->
                <!--<div class="row-input">-->
                <!--<input type="text" name="entity" id="entity3" class="entity" size="20" value="肺癌" readonly="">-->
                <!--</div>-->
                <label class="row-label">标本名称：</label>
                <div class="row-input">
                    <input type="text" name="checkpart" id="specimen1"  size="20">
                </div>
                <label class="row-label">报告日期：</label>
                <div class="row-input">
                    <input type="text" name="reportDate" id="reportDate21"  size="20">
                </div>
                <!--<label class="row-label">病种：</label>-->
                <!--<div class="row-input">-->
                <!--<input type="text" name="entity" id="entity3" class="entity" size="20" value="肺癌" readonly="">-->
                <!--</div>-->
            </div>
            <div style="margin-left:11%;margin-top: 26px; width: 350px;height: 200px">
                <img id="myimage111"  alt="肺部图片" />
            </div>
            <div>
                <div style="margin-left:4%;float: left;margin-right: 190px">
                    <label >临床诊断：</label><br/>
                    <textarea id="clinicDiagnosis1" rows="12" cols="65"></textarea>
                </div>
                <div style="float: left">
                    <label >肉眼所见：</label><br/>
                    <textarea id="eyeView1" rows="12" cols="65"></textarea><br/>
                </div><br/>
                <div style="margin-left:4%;float: left;margin-right: 190px">
                    <label >镜下所见：</label><br/>
                    <textarea id="glassView1" rows="12" cols="65"></textarea>
                </div>
                <div style="float: left">
                    <label >病理诊断：</label><br/>
                    <textarea id="pathologySuggestion1" rows="12" cols="65"></textarea>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<%--<div class="bjui-pageFooter">--%>
    <%--<ul>--%>
        <%--<li><button type="reset" class="btn-orange" form="j_patient_form0">搜索重置</button></li>--%>
        <%--<li><button type="button" class="btn-default" id="lung_search0">信息查询</button></li>--%>
    <%--</ul>--%>
<%--</div>--%>


