$("#submitReportLocal").click(function(){
    BJUI.ajax('doajax',{
        url:"local/submitReportLocal?examDesc=" + $("#examDesc").val()
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
    // $("#examDesc").empty();
    // $("#examDiagnosis").empty();
    $("#examDesc").val("");
    $("#examDiagnosis").val("");
});
$("#tihuan").click(function(){
    var examDesc = $("#examDesc_text_p").text();
    var examDiagnosis = $("#examDiagnosis_text_p").text();
    // $("#examDesc").empty();
    // $("#examDiagnosis").empty();
    // $("#examDesc").append(examDesc);
    // $("#examDiagnosis").append(examDiagnosis);
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
    $("#examDesc").val(examDesc_old+"\r\n" + examDesc);
    $("#examDiagnosis").val(examDiagnosis_old+"\r\n" + examDiagnosis);
});