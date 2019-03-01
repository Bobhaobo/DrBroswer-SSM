$(function(){
     // alert("@@@@@@@@@@@@@@@@@@@@@@");
    $.ajax({
        type:"post",
        url:"userRegister/loadUserRegHos",
        dataType: "html",
        async:false,
        success:function(data){
             // alert(data);
            var pro=null;
            pro = eval("("+data+")");
            var obj = document.getElementById('user_hos');
            for(var i=0;i<pro.length;i++){
                obj.add(new Option(pro[i].label,pro[i].value));
            }
        }
    });
});





