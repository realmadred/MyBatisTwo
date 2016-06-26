//获取参数id
var hrefStr = location.href;
var id = getHrefValues(hrefStr).id;

//页面加载后
$(function() {
	//加载导航
	loadNavi();
	//获取管理员的原始数据
	getAdminAndRoleData();
	
	//初始化校验器
	$("#main_form").validate();
});

//发送请求查询管理员数据
function getAdminAndRoleData(){
	//发送异步请求
	$.ajax({
		url : "getAdmin.do",
		type : "post",
		data : {"id":id},
		dataType : "json",
		success : function(data) {
			if (data.state == 0) {
				//获取管理员数据
				var admin = data.data;
				//该管理员的角色
				var admin_roles = admin.roles;
				//设置数据
				$("#name_input").val(admin.name);
				$("#code_input").val(admin.admin_code);
				$("#phone_input").val(admin.telephone);
				$("#email_input").val(admin.email);
				
				//获取所有角色数据
				var roles = data.data2;
				var len = roles.length;
				if(len > 0){
					//循环添加角色到ul
					var $ul = $("#roles_ul");
					for(var i=0;i<len;i++){
						var role = roles[i];
						//检查当前管理员是否包含该角色
						var li = getRoleLi(admin_roles,role);
						var $li = $(li);
						//绑定id到li
						$li.data("role_id",role.role_id);
						//添加到ul
						$ul.append($li);
					}
				}
				
			}else{
				alert(data.msg);
			}
		},
		error : function() {
			alert("系统繁忙，稍后重试！");
		}
	});
}

//保存按钮事件
function saveAdmin(){
	//获取姓名
	var name = $.trim($("#name_input").val());
	//检验姓名不能为空
	if(isEmptyStr(name)){
		alert("姓名不能为空！")
		return false;
	}
	
	//获取账号
	var admin_code = $.trim($("#code_input").val());
	
	//获取电话号码
	var phone = $.trim($("#phone_input").val());
	//检查手机号
	if(!checkTel(phone)){
		alert("电话号码格式不正确！")
		return false;
	}
	
	//获取邮箱
	var email =  $.trim($("#email_input").val());
	
	//获取所有的角色
	var $check = $("#roles_ul input:checked");
	var len = $check.length;
	if(len <= 0){
		alert("请至少选择一个角色！")
		return false;
	}
	//遍历所有选择的复选框获取角色id
	var roles = "";
	$check.each(function(){
		roles += $(this).parent().data("role_id")+",";
	});
	//取出最后的","
	roles = roles.substring(0,roles.length-1);
	
	//发送异步请求
	$.ajax({
		type:"post",
		url:"updateAdmin.do",
		data : {"id":id,
				"name":name,
				"admin_code":admin_code,
				"phone":phone,
				"email":email,
				"roles":roles
				},
		dataType : "json",
		success : function(data) {
			if(data.state == 0){
				showResult("save_result_info");
				//跳转到管理员列表
				//window.location.href = "toAdminList.do";
				//window.history.back();
			}else{
				alert("保存失败！");
			}
		},
		error : function() {
			alert("系统繁忙，稍后重试！");
		},
		async:true
	});
}

//检查字符串是空字符串
function isEmptyStr(str){
	if($.trim(str) == ""){
		return true;
	}
	return false;
}
