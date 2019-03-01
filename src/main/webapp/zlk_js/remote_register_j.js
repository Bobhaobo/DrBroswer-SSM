$("#remoteDiagReg").click(function(){
    $.ajax({
        type: "post",
        url:"/remote"
        + "/" + $('input[name="PatName"]').val()
        + "/" + $('input[name="PatGender"]').val()
        + "/" + $('#CheckType').val()
        + "/" + $('input[name="Age"]').val()
        + "/" + $('#AgeType').val()
        + "/" + $('input[name="PatType"]:checked').val()
        + "/" + $('input[name="tel"]').val()
        + "/" + $('input[name="address"]').val()
        + "/" + $('input[name="societyID"]').val()
        + "/" + $('input[name="PatIDCard"]').val()
        + "/remote_register",
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
                //$("#qianzhui").text("生成的病人ID：" + $("#CheckType").val());
                $("#r_checknum").text(info);
                $("#uploadDicom").attr("href",url_path);
                $("#uploadDicom").show();
            }
        }
    });
});