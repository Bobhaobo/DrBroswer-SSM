$(function(){
    var checknum = document.getElementById("checkNum").innerText;
    $.ajax({
        type:"post",
        url:"arrange/" + checknum + "/getArrangedDetail",
        dataType:"html",
        async:false,
        success:function(data){
            var pro=null;
            pro = eval("("+data+")");
            document.getElementById("checkNum").innerHTML=(pro.checkNum);
            document.getElementById("recordId").innerHTML=(pro.recordId);
            document.getElementById("patientId").innerHTML=(pro.patientId);
            document.getElementById("registerTime").innerHTML=(pro.registerTime);
            document.getElementById("patientName").innerHTML=(pro.patientName);
            document.getElementById("patientGender").innerHTML=(pro.patientGender);
            document.getElementById("patientAge").innerHTML=(pro.patientAge);
            document.getElementById("examItemName").innerHTML=(pro.type);
            document.getElementById("orderSource").innerHTML=(pro.orderSource);
            document.getElementById("date").innerHTML=(pro.date);
            document.getElementById("type").innerHTML=(pro.type);
            document.getElementById("device").innerHTML=(pro.device);
        }
    });
});