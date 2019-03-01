$(function(){
    $.ajax({
        type:"post",
        url:"register/load_check_hos",
        dataType: "html",
        async:false,
        success:function(data){
            //alert(data)
            var pro=null;
            pro = eval("("+data+")");
            var obj = document.getElementById('check_hos');
            for(var i=0;i<pro.length;i++){
                obj.add(new Option(pro[i].name,pro[i].id));
            }
        }
    });
    $.ajax({
        type:"post",
        url:"login/loadUserAccount",
        dataType:"html",
        async:false,
        success:function(data){
            var pro=null;
            pro = eval("("+data+")");
            $("#belong_hos").val(pro.hos);
        }
    });
    $.ajax({
        type:"post",
        url:"remote/" + $("#checkType_remote").val() +"/getChecknum",
        dataType:"html",
        async:false,
        success:function (data) {
            var pro=null;
            pro = eval("("+data+")");
            $("#card_num").val(pro);
        }
    });
    $.ajax({
        type:"post",
        url:"local/getCheckItems",
        dataType:"html",
        async:false,
        success:function (data) {
            //alert(data);
            var pro=null;
            pro = eval("("+data+")");
            var obj = document.getElementById('checkType_local_part');
            for(var i=0;i<pro.length;i++){
                obj.add(new Option(pro[i].label,pro[i].value));
            }
        }
    });
});

$("#remoteDiagReg").click(function(){
    $.ajax({
        type: "post",
        url:"remote/remote_register"
        + "?CheckNum=" + $('input[name="card_num_r"]').val()
        + "&PatName=" + $('input[name="PatName_r"]').val()
        + "&PatGender=" + $('input[name="PatGender_r"]').val()
        + "&CheckType=" + $('#checkType_remote').val()   //ok
        + "&Birthday=" + $('input[name="birthday_r"]').val()
        + "&PatType=" + $('input[name="PatType_r"]:checked').val()
        + "&Tel=" + $('input[name="tel_r"]').val()
        + "&Address=" + $('input[name="address_r"]').val()
        + "&SocietyID=" + $('input[name="societyID_r"]').val()
        + "&PatIDCard=" + $('input[name="PatIDCard_r"]').val(),
        dataType:"html",
        async:false,
        success: function(data) {
            //alert(data)
            var pro = null;
            pro = eval("("+data+")");
            var info = pro.info;
            var url_path = pro.url;
            if(info == 0){
                BJUI.alertmsg('error','未操作成功！请重新尝试！');
            }else{
                BJUI.alertmsg('ok', '提交成功！');
                $("#upload").attr("href",url_path);
            }
        }
    });
});
$("#checkType_remote").change(function(){
    $.ajax({
        type:"post",
        url:"remote/" + $("#checkType_remote").val() +"/getChecknum",
        dataType:"html",
        async:false,
        success:function (data) {
            var pro = null;
            pro = eval("("+data+")");
            $("#card_num").val(pro);
        }
    });
});
$("#checkType_local_part").change(function(){
    //alert($("#checkType_local_part").find("option:selected").text());
    $.ajax({
        type:"post",
        url:"local/" +  $("#checkType_local_part").find("option:selected").text() +"/getCheckNum",
        dataType:"html",
        async:false,
        success:function (data) {
            var pro = null;
            pro = eval("("+data+")");
            $("#checkNum_local_part").val(pro);
        }
    });
});
