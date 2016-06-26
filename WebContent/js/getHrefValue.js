function getHrefValues(href){
	if(href){
		var strh = href.toString();
		if(strh.indexOf("?")>0){
			var str = strh.substring(strh.indexOf("?")+1);
			console.log(str);
			return toJson(str);
			//return getStrValues(str);
		}
	}
}

function getStrValues(str){
	if(str != null){
		var s = str.toString();
		var values = [];
		var n1 = 0;
		var n2 = 0;
		while(true){
			n1 = s.indexOf("=",n2);
			if(n1 <= 0){
				return values;
			}
			n2 = s.indexOf("&",n1);
			console.log(n2);
			if(n2 >= 0){
				values[values.length] = s.substring(n1+1,n2);
			}else{
				values[values.length] = s.substring(n1+1).trim();
				return values;
			}
		}
		return values;
	}
}

function toJson(str){
	if(str){
		str = '{"'+str.toString().replace(/=/g,'":"').replace(/&/g,'","')+'"}';
		str = JSON.parse(str);
		console.log(str);
		return str;
	}
}

//别人的方法
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
