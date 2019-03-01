$(function(){
    var checknum = document.getElementById("checkNum").innerText;
    $.ajax({
        type:"post",
        url:"arrange/" + checknum + "/getArrangeDetail",
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
            document.getElementById("examItemName").innerHTML=(pro.examItemName);
            document.getElementById("orderDate").innerHTML=(pro.orderDate);
            document.getElementById("orderSource").innerHTML=(pro.orderSource);
            $(".subModality option").remove();
            $(".subModality").append("<option value="+pro.examItemCode+" selected=''>"+pro.examItemName+"</option>");
            $.ajax({
                type:"post",
                url:"arrange/"+ pro.examItemCode + "/getCheckDeviceList",
                dataType:"html",
                async:false,
                success:function(data){
                    $(".checkDevItem option").remove();
                    $(".checkDevItem").append("<option value='0'>--请选择--</option>");
                    var pro = null;
                    pro = eval("("+data+")");
                    for(var i=0;i<pro.length;i++){
                        $(".checkDevItem").append("<option value="+pro[i].devdesc+">"+pro[i].devdesc+"</option>");
                    }
                }
            });
        }
    });
});
$("#arrangeCheck").click(function(){
    BJUI.ajax('doajax',{
        url:"arrange/" + document.getElementById("checkNum").innerText
        + "/" + $("#arrangeDate").val()
        + "/" + $("#subModality").val()
        + "/" + $("#checkDevItem").val()
        + "/submitWorkList",
        okalert:false,
        callback: function(data) {
            if(data == 1){
                BJUI.alertmsg('ok', '安排成功！');
                BJUI.dialog('closeDialog', 'arrangehtml');
            }else{
                BJUI.alertmsg('error','未操作成功！请重新尝试！');
            }
        }
    });
});
