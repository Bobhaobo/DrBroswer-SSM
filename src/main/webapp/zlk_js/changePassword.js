$("#changePassword").click(function(){
	BJUI.ajax('doajax',{
		url:'changePassword?'
			+ 'oldpass=' + $("#j_userinfo_changepass_oldpass").val()
			+ '&newpass=' + $("#j_userinfo_changepass_newpass").val(),
		okalert:false,
		callback: function(data) {
			if(data == 0){
				BJUI.alertmsg('ok', '修改成功！');
				BJUI.dialog('closeDialog', 'changepassword');
			}else{
				BJUI.alertmsg('error','旧密码与账户信息不匹配！');
			}
		}
	})
})