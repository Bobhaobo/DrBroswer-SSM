$("#report_search_j213").click(function(){
   // window.location.href="/remote/search?name="+ $("#name1").val();
   //  alert("123");
    var n=document.getElementById("name1").value;
    var m=document.getElementById("modality1").value;
    var p=document.getElementById("pat_type1").value;
    var num=document.getElementById("number1").value;
    var cs=document.getElementById("Sex1").value;
    var ca=document.getElementById("Age1").value;
    $.ajax({
        type: "post",
        url: "/remote/search",
        data: {name: n,modality:m,pat_type:p,number:num,sex:cs,age:ca},
        dataType: "json",
        async: false,
        success: function (data) {
             alert(data);
            // document.getElementById("name123").value=data;
        }
    });
});

$("#qingkong").click(function(){
    // window.location.href="/remote/search?name="+ $("#name1").val();
    // alert("123");
    document.getElementById("name1").value="";
    document.getElementById("modality1").value="";
    document.getElementById("pat_type1").value="";
    document.getElementById("number1").value="";
    document.getElementById("Sex1").value="";
    document.getElementById("Age1").value="";
    document.getElementById("name").value="";
    document.getElementById("modality").value="";
    document.getElementById("pat_type").value="";
    document.getElementById("number").value="";
    document.getElementById("sex").value="";
    document.getElementById("age").value="";
    var n=document.getElementById("name1").value;
    var m=document.getElementById("modality1").value;
    var p=document.getElementById("pat_type1").value;
    var num=document.getElementById("number1").value;
    var cs=document.getElementById("Sex1").value;
    var ca=document.getElementById("Age1").value;
    // alert("123")
    $.ajax({
        type: "post",
        url: "/remote/search",
        data: {name: n,modality:m,pat_type:p,number:num,sex:cs,age:ca},
        dataType: "json",
        async: false,
        success: function (data) {
            // alert(data);
            // document.getElementById("name123").value=data;
        }
    });
});


function allocate_doctor(value,data) {
    // alert(data.patName);
    if(data.check_number == "没有数据！"){
        var html = null;
    }else{
        var html = '<button type="button" class="btn-blue" onclick="allocate_report(\''+data.checknum+'\');">分配</button>';
    }
    return html;
}


// function select_doctor(value,data) {
//     // alert(data.patName);
//     if(data.check_number == "没有数据！"){
//         var html = null;
//     }else{
//         var html = '<button type="button" class="btn-blue" onclick="select_(\''+data.checknum+'\');">选择</button>';
//     }
//     return html;
// }


$(function(){
    alert("123");
    $.ajax({
        type: "post",
        url: "remote/getDocSelect",
        dataType: "html",
        async: false,
        success:function(data){
            // alert(data);
            var pro=null;
            // alert("lalala");
            pro = eval("("+data+")");
            // pro=data;
            // alert("321");
            var unitObj=document.getElementById('doctor'); //页面上的<html:select>元素
            alert(unitObj);
            if(pro!=null){ //后台传回来的select选项
                for(var i=0;i<pro.length;i++){
                    //遍历后台传回的结果，一项项往select中添加option
                    unitObj.add(new Option(pro[i].value,pro[i].label));
                    // alert(pro[i].label+pro[i].value);
                }
            }
            // $('#doctor').selectpicker('refresh');
        }
    });
});



function allocate_report(checknum) {
    var d=document.getElementById("doctor").value;
    $.ajax({
        type: "post",
        url: "/remote/allocate",
        data: {checknum:checknum,docname:d},
        dataType: "json",
        async: false,
        success: function (data) {
            // alert(data);
            if(data == 1){
                BJUI.alertmsg('ok', '分配成功！');
            }else{
                BJUI.alertmsg('error','未分配成功！请确认医师姓名！');
            }

        }
    });
}



function connectName(value){
    document.getElementById("name1").value  = value;
}

function connectModality(value){
    document.getElementById("modality1").value  = value;
}

function connectPat_type(value){
    document.getElementById("pat_type1").value  = value;
}

function connectNumber(value){
    document.getElementById("number1").value  = value;
}

function connectSex(value){
    document.getElementById("Sex1").value  = value;
}

function connectAge(value){
    document.getElementById("Age1").value  = value;
}