$(function(){
    alert("lalala");
    alert( $("#bgCode").val());
    $.ajax({
        type:"post",
        url:"report/"+ $("#bgCode").val() +"/modifyReportPatDetail",
        dataType:"html",
        async:false,
        success:function(data){
            alert(data);
            var pro=null;
            pro = eval("("+data+")");
            $("#hosName").text(pro.hosName);
            $("#deptName").val(pro.deptName);
            $("#clinicId").val(pro.clinicId);
            $("#bedNo").val(pro.bedNo);
            $("#jcbw").val(pro.jcbw);
            $("#sfyangxing").val(pro.sfyangxing);
            $("#examDesc").val(pro.examDesc);
            $("#examDiagnosis").val(pro.examDiagnosis);
            $("#imagePath").attr("href","DrViewerBoot://" + pro.imagePath);
        }
    });
});

$("#modifyReport").click(function(){
    BJUI.ajax('doajax',{
        url:"report/modifyReport?bgCode=" + $("#bgCode").val()
        + "&pat_checknum=" + $("#pat_checknum").val()
        + "&deptName=" + $("#deptName").val()
        + "&clinicId=" + $("#clinicId").val()
        + "&bedNo=" + $("#bedNo").val()
        + "&jcbw=" + $("#jcbw").val()
        + "&sfyangxing=" + $("#sfyangxing").val()
        + "&hosName=" + $("#hosName").text()
        + "&pat_name=" + $("#pat_name").val()
        + "&pat_gender=" + $("#pat_gender").val()
        + "&pat_age=" + $("#pat_age").val()
        + "&examDesc=" + $("#examDesc").val()
        + "&examDiagnosis=" + $("#examDiagnosis").val(),
        okalert:false,
        loadingmask:true,
        callback: function(data) {
            alert(data);
            if(data == 1){
                BJUI.alertmsg('ok', '提交成功！');
            }else{
                BJUI.alertmsg('error','未操作成功！请重新尝试！');
            }
        }
    });
});
$("#qingkong").click(function(){
    $("#examDesc").val("");
    $("#examDiagnosis").val("");
});
$("#tihuan").click(function(){
    var examDesc = $("#examDesc_text_p").text();
    var examDiagnosis = $("#examDiagnosis_text_p").text();
    $("#examDesc").val("");
    $("#examDiagnosis").val("");
    $("#examDesc").val(examDesc);
    $("#examDiagnosis").val(examDiagnosis);
});
$("#zhuijia").click(function(){
    var examDesc_old = $("#examDesc").val();
    var examDiagnosis_old = $("#examDiagnosis").val();
    var examDesc = $("#examDesc_text_p").text();
    var examDiagnosis = $("#examDiagnosis_text_p").text();
    $("#examDesc").val(examDesc_old + "\r\n" + examDesc);
    $("#examDiagnosis").val(examDiagnosis_old + "\r\n" + examDiagnosis);
});