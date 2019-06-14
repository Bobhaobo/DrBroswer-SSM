$(function(){

    var s=$("#123").val();

    var d=0;

    $.ajax({
        type:"post",
        url:"remote/loadModifyCount?checknum="+s,
        dataType: "html",
        async:false,
        success:function(data){
            alert(data);
            d=data;
            for(var i=0;i<data;i++)
            {
                $("#tab").append("<tr >" + '<td>修改报告</td>' + '<td><div id="'+i+'"></div></td>' +'<td><div id="'+i+i+i+i+'"></div></td>' + "</tr>"
                +"<tr >"+'<td id="'+i+i+i+'v">审核报告</td>' + '<td><div id="'+i+i+i+'"></div></td>' +'<td><div id="'+i+i+i+i+i+'"></div></td>' +"</tr>");
            }
            // alert(document.getElementById("0").value);
        }
    });


    var message = "";
            var options={
                type:"post",
                url:'remote/getRemotePatByCheckNum?checknum='+s,
                dataType: "html",
                async:false,
                success:function (msg) {
                    alert("123");
                    alert(msg);
                    var pro=null;
                    pro = eval("("+msg+")");
                    message=msg;
                    $("#name1").text(pro.patname);
                    $("#sex1").text(pro.sex);
                    $("#age1").text(pro.age);
                    $("#pattype1").text(pro.pattype);
                    // $("#modality1").text(pro.modality);
                    // document.getElementById("modality1").innerText=pro.modality;
                    $("#zxc").text(pro.modality);
                    $("#remotehos1").text(pro.remotehos);
                    $("#status1").text(pro.status);

                    if(pro.status=="已分配"){
                        $("#www").after("<tr >" + '<td>图像上传医师</td>' + '<td><div id="ca1"></div></td>' +'<td><div id="caTime1"></div></td>' + "</tr>"+
                            "<tr >" + '<td>分配医师</td>' + '<td><div id="ca"></div></td>' +'<td><div id="caTime"></div></td>' + "</tr>");
                        // $("#tab").append("<tr >" + '<td>图像上传医师</td>' + '<td><div id="ca1"></div></td>' +'<td><div id="caTime1"></div></td>' + "</tr>"+
                        //     "<tr >" + '<td>分配医师</td>' + '<td><div id="ca"></div></td>' +'<td><div id="caTime"></div></td>' + "</tr>")
                    }

                    if(pro.status=="已上传图像"){
                        $("#www").after("<tr >" + '<td>图像上传医师</td>' + '<td><div id="ca1"></div></td>' +'<td><div id="caTime1"></div></td>' + "</tr>");
                        // $("#tab").append("<tr >" + '<td>图像上传医师</td>' + '<td><div id="ca1"></div></td>' +'<td><div id="caTime1"></div></td>' + "</tr>")
                    }

                    alert("333");
                    for(var i=0;i<d;i++){

                        var time="1900-01-01 00:00";

                        $("#"+i).text(pro.list[i].writedocname);
                        $("#"+i+i+i).text(pro.list[i].verifydocname);
                        $("#"+i+i+i+i).text(pro.list[i].writeupdatetime);
                        alert(pro.list[i].verifyupdatetime);
                        if(time==pro.list[i].verifyupdatetime ||pro.list[i].verifyupdatetime=="" ||pro.list[i].verifyupdatetime==null){

                            $("#"+i+i+i+i+i).text("");
                            $("#"+i+i+i+"v").text("");
                        }else {
                            $("#" + i + i + i + i + i).text(pro.list[i].verifyupdatetime);
                        }

                        // alert( $("#"+i).innerText);
                    }
                }
            }
    $.ajax(options);
    // $("#checknum1").text(message.checknum);
    // alert(document.getElementById("checknum1").val());
    alert(message);

    // $("#count").text(message);




    // return message;
});