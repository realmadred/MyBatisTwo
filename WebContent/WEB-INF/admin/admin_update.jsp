<%@page pageEncoding="UTF-8" 
	import="java.util.ArrayList" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            
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
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="update.admin" method="post" class="main_form">
            		 <input type="hidden" value="${admin.admin_id}" name="admin_id"/>
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.name}" name="name"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">20长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info"><input type="text" readonly="readonly" class="readonly"
                    	 value="${admin.admin_code}" name="admin_code" /></div>
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" value="${admin.telephone}" name="telephone"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="email" value="${admin.email}"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">50长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                            <ul>
                                <li><input type="checkbox" name="admin_module" value="1"
                                	<c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 1}">checked</c:if>
                                	</c:forEach> />角色管理员</li>
                                <li><input type="checkbox" name="admin_module" value="2"
                               		 <c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 2}">checked</c:if>
                                	</c:forEach>   />管理员</li>
                                <li><input type="checkbox" name="admin_module" value="3" 
                              	     <c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 3}">checked</c:if>
                                	</c:forEach> />资费管理员</li>
                                <li><input type="checkbox" name="admin_module" value="4" 
                               		 <c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 4}">checked</c:if>
                                	</c:forEach>  />账务管理员</li>
                                <li><input type="checkbox" name="admin_module" value="5" 
                                	<c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 5}">checked</c:if>
                                	</c:forEach>  />业务管理员</li>
                                <li><input type="checkbox" name="admin_module" value="6" 
                                	 <c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 6}">checked</c:if>
                                	</c:forEach>  />账单管理员</li>
                                <li><input type="checkbox" name="admin_module" value="7"
                                	 <c:forEach items="${admin.admin_modules}" var="role" >
                                		<c:if test="${role == 7}">checked</c:if>
                                	</c:forEach>  />报表管理员</li>
                            </ul>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny error_msg">至少选择一个</div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" />
                        <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
            ${admin.admin_modules[1]}
        </div>
    </body>
</html>
