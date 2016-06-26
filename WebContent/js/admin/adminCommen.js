//加载导航
function loadNavi(){
	$("#navi").load("../menu.html",function(){
		//设置自己的样式
		$("#navi a").eq(2).removeClass("admin_off").addClass("admin_on");
	});
}

//保存成功的提示消息
function showResult(id) {
    showResultDiv(true,id);
    window.setTimeout("showResultDiv(false);", 500);
}
function showResultDiv(flag,id) {
    var divResult = document.getElementById(id);
    if (flag)
        $(divResult).fadeIn(1000);
    else{
        $(divResult).fadeOut(2000);
    	window.history.back();
    }
}

//获取角色复选框的行，在更新和添加里面用到
function getRoleLi(admin_roles,role){
	var len = admin_roles.length;
	var li = '<li><input name="role_input" type="checkbox" />'+role.name+'</li>';
	if(len > 0){
		for(var j=0;j<len;j++){
			if(role.role_id == admin_roles[j].role_id){
				li = '<li><input name="role_input" type="checkbox" checked />'+role.name+'</li>';
			}
		}
	}
	return li;
}
