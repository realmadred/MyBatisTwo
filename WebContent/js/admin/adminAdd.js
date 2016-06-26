
var validator;
//页面加载后
$(function() {
	//加载导航
	loadNavi();
	
	//获取所有的角色
	getRoleData();
	
	//设置验证
 	$.extend($.validator.messages, {
 		  required: "必选字段",
 		  remote: "请修正该字段",
 		  email: "请输入正确格式的电子邮件",
 		  url: "请输入合法的网址",
 		  date: "请输入合法的日期",
 		  dateISO: "请输入合法的日期 (ISO).",
 		  number: "请输入合法的数字",
 		  digits: "只能输入整数",
 		  creditcard: "请输入合法的信用卡号",
 		  equalTo: "请再次输入相同的值",
 		  accept: "请输入拥有合法后缀名的字符串",
 		  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
 		  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
 		  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
 		  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
 		  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
 		  min: jQuery.validator.format("请输入一个最小为{0} 的值")
 		});
 	
//     		    $.validator.setDefaults({
//     		        debug: true//调试模式
//     		    });
	    validator = $("#main_form").validate({
	    	 rules: {
	    	 name_input: {
	             required: true,
	             minlength:2,
	             maxlength: 20
	         },
	         code_input: {
	             required: true,
	             minlength:2,
	             maxlength:16,
	             remote:"checkNameExsis.do"
	         },
	         pasword_input: {
	             required: true,
	             minlength: 6,
	             maxlength: 20
	         },
	         re_pass_input: {
	             required: true,
	             minlength: 6,
	             maxlength: 20,
	             equalTo:"#pasword_input"
	         },
	         phone_input: {
	             required: true,
	             phone:true//自定义规则
	         },
	         email_input: {
	             required: true,
	             email:true
	         },
	         role_input: {
	        	 required: true
	         }
	     },
	     submitHandler: function (form) {
	         console.log($(form).serialize());
	     },
	     errorPlacement: function(error, element) { //错误信息位置设置方法
	     	error.appendTo(element.next().next()); //这里的element是录入数据的对象
	     }
	    });
	    $.validator.addMethod("phone",
	 		 function(value, element, params){
	    	 	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	    		var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	            return this.optional(element) || (isPhone.test(value)) || (isMob.test(value));
	         }, 
	   		 "请输入正确的电话号码");

});

//发送请求查询所有的角色
function getRoleData(){
	//发送异步请求
	$.ajax({
		url : "getRoles.do",
		type : "get",
		dataType : "json",
		success : function(data) {
			if (data.state == 0) {
				//获取所有角色数据
				var roles = data.data;
				var len = roles.length;
				if(len > 0){
					//循环添加角色到ul
					var $ul = $("#roles_ul");
					for(var i=0;i<len;i++){
						var role = roles[i];
						//检查当前管理员是否包含该角色
						var li = getRoleLi("",role);
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

//保存管理员
function addAdmin(){
	//获取姓名
	var name = $.trim($("#name_input").val());
	//校验不能为空
	if(name == ""){
		$("#name_msg_div").html("姓名不能为空！");
		$("#name_msg_div").css("color","red");
		return false;
	}
	
	//获取账号
	var code = $.trim($("#code_input").val());
	//校验不能为空
	if(code == ""){
		$("#code_msg_div").html("账号不能为空！");
		$("#code_msg_div").css("color","red");
		return false;
	}
	
	//获取密码
	var pass = $.trim($("#pasword_input").val());
	//校验不能为空
	if(pass == ""){
		$("#pass_msg_div").html("密码不能为空！");
		return false;
	}
	
	//获取重复密码
	var pass2 = $("#re_pass_input").val().trim();
	//校验不能为空
	if(pass != pass2){
		$("#re_pass_div").html("密码不一致！");
		return false;
	}
	
	//获取电话
	var phone = $("#phone_input").val().trim();
	//校验不能为空
	if(!checkTel(phone)){
		$("#phone_msg_div").html("电话号码格式不正确！");
		return false;
	}
	
	//获取邮箱
	var email = $("#email_input").val().trim();
	//校验不能为空
	if(!isEMailAddr(email)){
		$("#email_msg_div").html("email格式不正确！");
		return false;
	}
	
	//获取所有的角色
	var $check = $("#roles_ul :checked");
	//校验至少要选择一个角色
	if($check.length == 0){
		alert("请至少选择一个角色！");
		return false;
	}
	if(!validator.valid()){
		alert("数据填写不正确！");
		return false;
	}
	var roles = "";
	//循环获取角色id
	$check.each(function(){
		roles += $(this).parent().data("role_id")+",";
	});
	//取出最后的“，”
	roles = roles.substring(roles,roles.length-1);
	
	//发送请求保存数据
	$.ajax({
		type:"post",
		url:"addAdmin.do",
		data:{"name":name,
				"code":code,
				"pass":pass,
				"phone":phone,
				"email":email,
				"roles":roles
		},
		success:function(data){
			if(data.state == 0){
				showResult("save_result_info");
				window.location.href = "toAdminList.do"
			}else{
				alert("保存失败！");
			}
		},
		error:function(){
			alert("保存失败，系统繁忙！");
		},
		async:true
	});
}


