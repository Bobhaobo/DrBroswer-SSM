/*
$(function(){
    $.ajax({
        type:"post",
        url:"report/" + $("#pat_checknum").val() + "/writeReportPatDetail" ,
        dataType:"json",
        async:false,
        success:function(data){
            var pro=null;
            pro = eval("("+data+")");
            $(".patName").text("姓名：" + pro.patientName);
            $(".patGender").text("性别：" + pro.patientGender);
            $(".patAge").text("年龄：" + pro.patientAge);
            $("#hosName").text(pro.hosName);
            $("#imagePath").attr("href","DrViewerBoot://" + pro.imagePath);
        }
    });
});
*/
$("#submitReport").click(function(){
    BJUI.ajax('doajax',{
        url:"report/submitReport?examDesc=" + $("#examDesc").val()
            + "&examDiagnosis=" + $("#examDiagnosis").val()
            + "&pat_checknum=" + $("#pat_checknum").val()
            + "&deptName=" + $("#deptName").val()
            + "&clinicId=" + $("#clinicId").val()
            + "&bedNo=" + $("#bedNo").val()
            + "&jcbw=" + $("#jcbw").val()
            + "&sfyangxing=" + $("#sfyangxing").val()
            + "&hosName=" + $("#hosName").text()
            + "&pat_name=" + $("#pat_name").val()
            + "&pat_gender=" + $("#pat_gender").val()
            + "&pat_age=" + $("#pat_age").val(),
        okalert:false,
        loadingmask:true,
        callback: function(data) {
            if(data == 1){
                BJUI.alertmsg('ok', '提交成功！');
            }else{
                BJUI.alertmsg('error','未操作成功！请重新尝试！');
            }
        }
    });
});
$("#qingkong").click(function(){
    $("#examDesc").empty();
    $("#examDiagnosis").empty();
});
$("#tihuan").click(function(){
    var examDesc = $("#examDesc_text_p").text();
    var examDiagnosis = $("#examDiagnosis_text_p").text();
    $("#examDesc").empty();
    $("#examDiagnosis").empty();
    $("#examDesc").append(examDesc);
    $("#examDiagnosis").append(examDiagnosis);
});
$("#zhuijia").click(function(){
    var examDesc = $("#examDesc_text_p").text();
    var examDiagnosis = $("#examDiagnosis_text_p").text();
    $("#examDesc").append("\r\n" + examDesc);
    $("#examDiagnosis").append("\r\n" + examDiagnosis);
});