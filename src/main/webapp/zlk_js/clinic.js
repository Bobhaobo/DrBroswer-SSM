$(function(){
    var idcard = document.getElementById("idCard").innerText;
    $.ajax({
        type:"post",
        url:"clinic/" + idcard + "/getClinicInfo",
        dataType:"html",
        async:false,
        success:function(data){
            // alert("lalala");
            var pro=null;
            pro = eval("("+data+")");
             alert(data);
            // alert("@@@@@@@@");
            document.getElementById("idCard").innerHTML=(pro.idcard);
            document.getElementById("patName").innerHTML=(pro.patname);
            document.getElementById("patGender").innerHTML=(pro.patgender);
            document.getElementById("entity1").innerHTML=(pro.entity);
            document.getElementById("patBrithdate").innerHTML=(pro.patbirthdate);
            document.getElementById("address").innerHTML=(pro.address);
            document.getElementById("yibaoId").innerHTML=(pro.yibaoid);
            document.getElementById("telephone").innerHTML=(pro.telephone);
            document.getElementById("ageage").innerHTML=(pro.age);
            document.getElementById("updateTime").innerHTML=(pro.updatetime);
            document.getElementById("clinicDoc").innerHTML=(pro.clinicdoc);
            document.getElementById("mainSuit").innerHTML=(pro.mainsuit);
            document.getElementById("personalHis").innerHTML=(pro.personalhis);
            document.getElementById("maritalHis").innerHTML=(pro.maritalhis);
            document.getElementById("familyHis").innerHTML=(pro.familyhis);
            document.getElementById("pastIllnessHis").innerHTML=(pro.pastillnesshis);
            document.getElementById("presentIllnessHis").innerHTML=(pro.presentillnesshis);
            document.getElementById("specialityCheck").innerHTML=(pro.specialitycheck);
            document.getElementById("tentativeDiagnosis").innerHTML=(pro.tentativediagnosis);
        }
    });
});