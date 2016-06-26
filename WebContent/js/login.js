//全局变量
var imageFlag = false;

//检查用户名
function checkUserName() {
	// 获取文本框里面的值
	var username = $(this).val();
	if (!username) {
		$("#nameError").html("用户名不能为空");
		$(this).focus();
		return;
	}
	// 发送异步请求
	$.ajax({
		url : "admin/checkName.do",
		type : "post",
		data : {
			"username" : username
		},
		dataType : "json",
		success : function(data) {
			if (data) {
				if (data.state != 0) {
					$("#nameError").html(data.msg);
				}
			} else {
				alert("服务器校验失败");
			}
		},
		error : function() {
			alert("系统繁忙，稍后重试！");
		}
	});
}

//检查验证码
function checkImageCode() {
	// 获取文本框里面的值
	var imageCode = $(this).val();
	if (!imageCode) {
		$(this).val("");
		$(this).focus();
		return;
	}
	// 发送异步请求
	$.ajax({
		url : "admin/checkImageCode.do",
		type : "post",
		data : {
			"imageCode" : imageCode
		},
		dataType : "json",
		success : function(data) {
			if (data) {
				if (data.state != 0) {
					imageFlag = false;
					$("#image_msg").html("");
				}else{
					imageFlag = true;
					$("#image_msg").html("ok");
				}
			} else {
				imageFlag = false;
				alert("服务器校验失败");
			}
		},
		error : function() {
			imageFlag = false;
			alert("系统繁忙，稍后重试！");
		}
	});
}

//清除错误提示
function removeErrorTip(){
	$("#nameError").html("");
	$("#span_msg").html("");
}

$(function() {
	// 页面加载后绑定用户名输入框失去焦点事件
	$("#username").blur(checkUserName);

	// 绑定获取焦点事件
	$("#username").focus(removeErrorTip);
	
	// 验证码输入框keyup事件
	$("#image_code_in").keyup(checkImageCode);
	
	// 验证码图片单击事件
	$("#imageCode").click(getImageCode);
	
	
});

// 登陆的方法
function login() {
	if(!imageFlag){
		alert("验证码不正确！");
		return false;
	}
	// 获取用户名和密码
	var username = $("#username").val();
	var password = $("#password").val();
	//获取验证码
	var imageCode = $("#image_code_in").val();

	//使当前按钮不可用
	$("#login_a").fadeOut(1000);
	// 发送异步亲求
	$.ajax({
		url : "admin/login.do",
		type : "post",
		data : {
			"username" : username,
			"imageCode" : imageCode,
			"password" : password
		},
		datatype : "json",
		success : function(data) {
			$("#login_a").fadeIn(1000);
			if (data) {
				if (data.state != 0) {
					$("#span_msg").html(data.msg);
				} else {
					window.location.href = "admin/toIndex.do";
				}
			} else {
				alert("服务器校验失败");
			}
		},
		error : function() {
			$("#login_a").fadeIn(1000);
			alert("系统繁忙，稍后重试！");
		}
	})
}

//获取验证码方法
var path = "admin/getImage.do";
function getImageCode() {
	$("#image_msg").html("");
	var rand = Math.random();
	var param = "?rand="+rand;
	$("#imageCode").attr("src",path+param);
}


//var validator;
//$(function () {
//    $.validator.setDefaults({
//        debug: true//调试模式
//    });
//    validator = $("#loginForm").validate({
//        rules: {
//            username: {
//                required: true,
//                minlength:2,
//                maxlength: 20
//                //postcode : "中国"
//            },
//            password: {
//                required: true,
//                minlength: 6,
//                maxlength: 16
//            },
//            image_code_in: {
//                required: true,
//                remote:"admin/checkImageCode.do"
//            },
//            postCode_in: {
//                postcode: "中国"
//            }
//        },
//        messages: {
//            username: {
//                required: "必须填写用户名",
//                minlength: "用户名最小为2位",
//                maxlength: "用户名最大为10位",
//                rangelength: "用户名应该在2-10位",
//                remote: "用户名不存在"
//            },
//            password: {
//                required: "必须填写密码",
//                minlength: "密码最小为6位",
//                maxlength: "密码最大为16位"
//            },
//            image_code_in: {
//                remote:"验证码不正确"
//            }
//        },
//        submitHandler: function (form) {
//            console.log($(form).serialize());
//        },
//        errorPlacement: function(error, element) { //错误信息位置设置方法
//        	error.appendTo( element.parent().next().find("span") ); //这里的element是录入数据的对象
//        },
//    });
//    $.validator.addMethod("postcode",
//		function(value, element, params){
//            var postcode = /^[0-9]{6}$/;
//            return this.optional(element) || (postcode.test(value));
//        }, 
//        $.validator.format("请填写正确的{0}邮编！"));
//});