<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(function(){
        $.ajax({
            type:"post",
            url:"lung/getLungInfo?idcard122="+ $("#patIdCards").val(),
            dataType: "html",
            async:false,
            success:function(data){
                var pro=null;
                // pro=JSON.parse(data);
                pro=eval("("+data+")");
                // alert(pro.lungDto.age);
                // $("#pat_name").val(pro.list[0].patname);//赋值
                $("#pat_names").val(pro.lungDto.patname);
                // alert(pro.lungDto.patname);
                $("#age36s").val(pro.lungDto.age);
                $("#sex123s").val(pro.lungDto.patgender);
                $("#ID1s").val(pro.lungDto.patientid);
                $("#examDoc61s").val(pro.lungDto.examdoc);
                $("#imageclass1s").val(pro.lungDto.imageclass);
                $("#checkpart1s").val(pro.lungDto.checkpart);
                $("#scanmethod1s").val(pro.lungDto.scanmethod);
                $("#scanway1s").val(pro.lungDto.scanway);
                $("#clinicdiagnosis1s").val(pro.lungDto.clinicdiagnosis);
                $("#checkDate1s").val(pro.lungDto.checkdate);
                $("#reportDate1s").val(pro.lungDto.reportdate);
                $("#IDcard7s").val(pro.lungDto.idcard);
                $("#entity10").val(pro.lungDto.entity);
                // alert("$$$");
                document.getElementById("imageViews").innerHTML=(pro.lungDto.imageview);
                document.getElementById("imageSuggestions").innerHTML=(pro.lungDto.imagesuggestion);
                // alert($("#pat_name").val());    //取值
                // alert("lalala");
                document.getElementById("myimages").src=(pro.base64Url);
            }
        });
    });
</script>
<div class="bjui-pageContent">
    <input value="${param.idCards123}" type="hidden" id="patIdCards">
    <%--<input value="${param.patName1s}"  id="patNames">--%>
    <form action="" id="j_patient_form13" name="j_patient_form">
        <fieldset><legend style="font-family:verdana;font-size:130%;color:orange;">检索结果：</legend>
            <div class="bjui-row col-4">
                <label class="row-label">患者姓名：</label>
                <div class="row-input">
                    <input id="pat_names" type="text" size="10"  readonly="" />
                </div>
                <label class="row-label">性别：</label>
                <div class="row-input">
                    <input type="text" name="sex" id="sex123s" class="lung_id" size="20">
                </div>
                <label class="row-label">年龄：</label>
                <div class="row-input">
                    <input type="text" name="age1" id="age36s" class="age1" size="20">
                </div>
                <label class="row-label">病人ID：</label>
                <div class="row-input">
                    <input type="text" name="ID" id="ID1s" class="ID" size="20">
                </div>
                <label class="row-label">身份证号：</label>
                <div class="row-input">
                    <input type="text" name="IDcard7s" id="IDcard7s" class="IDcard7s" size="20">
                </div>
                <label class="row-label">病种：</label>
                <div class="row-input">
                    <input type="text" name="entity10" id="entity10"  size="20">
                </div>
                <label class="row-label">检查部门：</label>
                <div class="row-input" >
                    <input type="text" name="examDoc6" id="examDoc61s"  size="20">
                </div>
                <!--<label class="row-label">病种：</label>-->
                <!--<div class="row-input">-->
                <!--<input type="text" name="entity" id="entity3" class="entity" size="20" value="肺癌" readonly="">-->
                <!--</div>-->
                <label class="row-label">影像类型：</label>
                <div class="row-input">
                    <input type="text" name="imageclass" id="imageclass1s"  size="20">
                </div>
                <label class="row-label">检查部位：</label>
                <div class="row-input">
                    <input type="text" name="checkpart" id="checkpart1s"  size="20">
                </div>
                <label class="row-label">平扫方法：</label>
                <div class="row-input">
                    <input type="text" name="scanmethod" id="scanmethod1s"  size="20">
                </div>
                <label class="row-label">平扫方式：</label>
                <div class="row-input">
                    <input type="text" name="scanway" id="scanway1s"  size="20">
                </div>
                <label class="row-label">临床诊断：</label>
                <div class="row-input">
                    <input type="text" name="clinicdiagnosis" id="clinicdiagnosis1s"  size="20">
                </div>
                <label class="row-label">检查日期：</label>
                <div class="row-input">
                    <input type="text" name="checkDate" id="checkDate1s"  size="20">
                </div>
                <label class="row-label">报告日期：</label>
                <div class="row-input">
                    <input type="text" name="reportDate" id="reportDate1s"  size="20">
                </div>
            </div>

            <div style="margin-left:4%; float: left" >
                <label class="row-label">影像所见：</label><br/>
                <textarea id="imageViews" rows="12" cols="65"></textarea><br/>
                <label class="row-label">影像诊断：</label><br/>
                <textarea id="imageSuggestions" rows="12" cols="65"></textarea>
            </div>
            <div style="margin-left:11%;  float:left;margin-top: 26px;">
                <img id="myimages"  alt="肺部图片" />
            </div>
        </fieldset>
    </form>
</div>
<%--<div class="bjui-pageFooter">--%>
    <%--<ul>--%>
        <%--<li><button type="reset" class="btn-orange" form="j_patient_form">搜索重置</button></li>--%>
        <%--<li><button type="button" class="btn-default" id="lung_search">信息查询</button></li>--%>
    <%--</ul>--%>
<%--</div>--%>

