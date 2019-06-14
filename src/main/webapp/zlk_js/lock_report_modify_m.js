$(function(){
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
