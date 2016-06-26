
//去往更新页面
function toUpdate(e){
	//获取当前行
	var $tr = $(e).parents("tr");
	//获取id
	var idStr = $tr.find("td").eq(1).html();
	//跳转
	var url = "toUpdate.do?id="+idStr;
	window.location.href = encodeURI(url);
}

//显示管理员列表
function showAdminList(){
	$.ajax({
		url : "adminList.do",
		type : "post",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data.state == 0) {
				var admins = data.data;
				// 列表
				var $table = $("#datalist");
				//alert(admins.length);
				// 遍历
				showAdmins(admins,$table);
			}

		},
		error : function() {
			alert("系统繁忙，稍后重试！");
		}
	});
}

function showAdmins(admins,$table){
	for (var i = 0; i < admins.length; i++) {
		var admin = admins[i];
		var enrolldate = getSmpFormatDateByLong(
				admin.enrolldate, false);
		var roles = admin.roles;
		//alert(roles.length);
		var name_0 = "";
		var rolenames = "";
		
		var len = roles.length;
		for(var j=0;j<len;j++){
			var name = roles[j].name;
			if(j == 0){
				name_0 = name;
			}
			if(j == len-1){
				rolenames += name
			}else{
				rolenames += name+",";
			}
		}
		
		var tr = '<tr>' + '<td><input type="checkbox" />'
				+ '</td>' + '<td>'
				+ admin.admin_id
				+ '</td>'
				+ '<td>'
				+ admin.name
				+ '</td>'
				+ '<td>'
				+ admin.admin_code
				+ '</td>'
				+ '<td>'
				+ admin.telephone
				+ '</td>'
				+ '<td>'
				+ admin.email
				+ '</td>'
				+ '<td>'
				+ enrolldate
				+ '</td>'
				+ '<td>'
				+ '<a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">'
				+ name_0
				+'</a>'
				+ '<div class="detail_info">'
				+ rolenames
				+ '</div> '
				+ '</td>'
				+ '<td class="td_modi">'
				+ '<input type="button" value="修改" class="btn_modify" onclick="toUpdate(this);" />'
				+ '<input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(this);" />'
				+ '</td>' + '</tr>';
		// 将tr添加到列表
		$table.append(tr);
	}
}

//页面加载后
$(function() {
	//加载导航
	loadNavi();
	//发生异步请求,显示管理员列表
	showAdminList();
});

//显示角色详细信息
function showDetail(flag, a) {
    var detailDiv = a.parentNode.getElementsByTagName("div")[0];
    if (flag) {
        detailDiv.style.display = "block";
    }
    else
        detailDiv.style.display = "none";
}
//重置密码
function resetPwd() {
    alert("请至少选择一条数据！");
    //document.getElementById("operate_result_info").style.display = "block";
}
//删除
function deleteAdmin(t) {
    var r = window.confirm("确定要删除此管理员吗？");
    if(r){
	    //获取当前行
	    var $tr = $(t).parents("tr");
	    //获取当前行的id
	    var idStr = $tr.find("td").eq(1).text();
	    var id = parseInt(idStr.trim());
	    //提示信息div
	    var $div_result = $("#operate_result_info");
	    //执行异步删除
	    $.ajax({
	    	type:"post",
	    	url:"deleteAdmin.do",
	    	data : {"id":id},
			dataType : "json",
			success : function(data) {
				if (data.state == 0) {
					//删除当前行
					$tr.empty();
					
					//提示删除成功
					//设置span提示信息
					$div_result.find("span").text("删除成功！");
					//显示
					$div_result.fadeIn(1000).fadeOut(2000);
				}else{
					//设置span提示信息
					$div_result.find("span").text("删除失败！");
					//显示
					$div_result.fadeIn(1000).fadeOut(2000);
				}
			},
			error : function() {
				$div_result.fadeIn(1000).fadeOut(2000);
			},
	    	async:true
	    });
    }
}
//全选
function selectAdmins(inputObj) {
    var inputArray = document.getElementById("datalist").getElementsByTagName("input");
    for (var i = 1; i < inputArray.length; i++) {
        if (inputArray[i].type == "checkbox") {
            inputArray[i].checked = inputObj.checked;
        }
    }
}

