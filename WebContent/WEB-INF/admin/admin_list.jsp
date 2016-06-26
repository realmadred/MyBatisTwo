﻿<%@page pageEncoding="UTF-8" import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" /> 
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <script language="javascript" type="text/javascript">
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
               var $checkeds =  $("td>:checked");
               if($checkeds.length <= 0){
            		alert("请至少选择一条数据！");
               }else{
            	   //创建数组
            	   var ids = new Array();
            	   //获取所有选择的id
            	   for(var i=0;i<$checkeds.length;i++){
            		   ids[i] = $checkeds.eq(i).val();
            	   }
            	  console.log(ids.toString());
            	  location.href ="toResetPassword.admin?ids="+ids.toString();
               }
            }
            //删除
            function deleteAdmin(id) {
                var r = window.confirm("确定要删除此管理员吗？");
                if(r){
                	location.href='delete.admin?id='+id;//直接删除没有删除页面
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
            //模块管理
            $(function(){
            	//页面加载后绑定事件
            	$("select").change(function(e){
            		//console.log($(e.target).val());
            		//console.log($("select>:selected").val());
            		var id = $("select>:selected").val();
            		//判断
            		if(id != '0'){
	            		location.href ="findByModule.admin?id="+$("select>:selected").val();
            		}else{
            			location.href ="find.admin";
            		}
            	});
            });
            //搜索
            $(function(){
            	//页面加载后绑定事件
            	$(".btn_search").click(function(){
            		//获取文本框的字符
            		var name = $(".text_search").val();
            		//console.log(name);
            		location.href = "findByRoleName.admin?name="+name;
            	});
            });
        </script>       
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <a href="back.do">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <!-- 使用导入，可以复用 -->
               <c:import url="/WEB-INF/menu.jsp"/>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--查询-->
                <div class="search_add">
                    <div>
                        模块：
                        <select id="selModules" class="select_search">
                            <option value="0">全部</option>
                            <option value="1" ${param.id == '1' ? 'selected' : ''}>角色管理</option>
                            <option value="2" <c:if test="${param.id == '2'}">selected</c:if>>管理员管理</option>
                            <option value="3" ${param.id == '3' ? 'selected' : ''}>资费管理</option>
                            <option value="4" <c:if test="${param.id == '4'}">selected</c:if>>账务账号</option>
                            <option value="5" <c:if test="${param.id == '5'}">selected</c:if>>业务账号</option>
                            <option value="6" ${param.id == '6' ? 'selected' : ''}>账单管理</option>
                            <option value="7" <c:if test="${param.id == '7'}">selected</c:if>>报表</option>
                        </select>
                    </div>
                    <div>角色：<input type="text" value="${param.name}" class="text_search width200" /></div>
                    <div><input type="button" value="搜索" class="btn_search" /></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAdd.admin';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr> 
                        <c:forEach items="${admins}" var="admin">
	                         <tr>
	                            <td><input type="checkbox" name="re_pass" value="${admin.admin_id}" /></td>
	                            <td>${admin.admin_id}</td>
	                            <td>${admin.name}</td>
	                            <td>${admin.admin_code}</td>
	                            <td>${admin.telephone}</td>
	                            <td>${admin.email}</td>
	                            <td>${admin.enrolldate}</td>
	                            <td>
	                                <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
	                                		<c:forEach items="${admin.roleNames}" var="roleName" begin="0" end="0">
	                                    		${roleName}...
	                                    	</c:forEach>
	                                </a>
	                                <!--浮动的详细信息-->
	                                <div class="detail_info">
	                                    <c:forEach items="${admin.roleNames}" var="roleName" varStatus="n">
	                                    	${roleName}
	                                    </c:forEach>
	                                </div>
	                            </td>
	                            <td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdate.admin?id=${admin.admin_id}';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${admin.admin_id});" />
	                            </td>
	                        </tr>
                        </c:forEach>                   
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
                </div>                    
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
