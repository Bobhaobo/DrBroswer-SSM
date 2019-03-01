$(function() {
    //alert("aaa");
    changeCode();
    $("#authcode_img").click(function(){
        changeCode();
    });
});
function changeCode(){
    var time_now = new Date().getTime();
    document.getElementById('authcode_img').src='login/' + time_now + '/authcode';
}
function login_info() {
    var status = 0;
    $.ajax({
        type: "post",
        url: "/login/queryUser/" + $("#username").val() + "/"
            + $("#password").val() + "/"
            + $("#authcode").val() + "/"
            + "login_auth",
        async: false,
        success: function(data){
            // alert(data);
            if(data != 2 && data != 1 && data != 5){
                if(data == 3){
                    document.getElementById("error_msg").innerHTML=("验证码错误！");
                }else if(data == 0){
                    document.getElementById("error_msg").innerHTML=("用户名密码不匹配！");
                } else if(data == 4){
                    document.getElementById("error_msg").innerHTML=("服务器内部错误！");
                }
                status = 0;
            }else{
                if(data == 2||data == 1||data == 5){
                    document.getElementById("login_form").setAttribute("action","index_menu_j.html");
                }
                else {

                }
                status = 1;
            }
        }
    });
    if(status == 0 ){
        document.getElementById("error_msg").style.display="inline";
        return false;
    }else {
        return true;
    }
}