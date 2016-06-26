//检查是否是手机号或固定电话
function checkTel(str){
	var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
	var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
	str = str.trim();
	if(isMob.test(str)||isPhone.test(str)){
		return true;
	}else{
		return false;
	}
}

//邮件地址验证 
function isEMailAddr(str) {
	str = str.trim();
	var re = /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/; 
	if (!str.match(re)) { 
		return false; 
	} else { 
		return true; 
	} 
} 