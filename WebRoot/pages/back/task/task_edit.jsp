<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String editUrl = basePath + "pages/back/task/TaskServletBack/edit" ;
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="<%=basePath%>js/pages/back/task/task_edit.js"></script>
<script type="text/javascript" src="<%=basePath%>js/date/WdatePicker.js"></script>

</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li>修改回访任务</li>
		</ul>
	</div>
	<div class="formbody">
		<div class="formtitle">
			<span>修改回访任务 </span>
		</div>
		<form action="<%=editUrl%>" method="post" id="myform">
			<ul class="forminfo">
				<li><label>主题</label>
					<input name="task.title" placeholder="任务的标题" id="task.title" type="text" class="dfinput" value="${task.title}"/>
					<i>不能超过10个字符</i></li>
				<li><label>优先级</label> <cite>
					<input id="task.level" name="task.level" type="radio" value="0" ${task.level == 0 ? "checked" : ""}/> 高 &nbsp; 
					<input id="task.level" name="task.level" type="radio" value="1" ${task.level == 1 ? "checked" : ""} /> 中  &nbsp; 
					<input id="task.level" name="task.level" type="radio" value="2" ${task.level == 2 ? "checked" : ""} /> 低 &nbsp; 
					</cite></li>
				<li><label>预计安排时间</label>
					<input id="task.tdate" name="task.tdate" type="text" class="dfinput" placeholder="选择任务的完成日期" onClick="WdatePicker()" value="${task.tdate}"/>
				<li><label>回访状态</label> <select id="task.type" name="task.type">
						<option value="0"  ${task.type == 0 ? "selected" : ""}>初步咨询</option>
						<option value="1"  ${task.type == 1 ? "selected" : ""}>有意向</option>
						<option value="2"  ${task.type == 2 ? "selected" : ""}>无意向</option>
						<option value="3"  ${task.type == 3 ? "selected" : ""}>准备签约</option>
						<option value="4"  ${task.type == 4 ? "selected" : ""}>签约完毕</option>
				</select></li>
				<li><label>回访方式</label> 
					<select id="task.visit" name="task.visit">
						<option value="0" ${task.visit == 0 ? "selected" : ""}>电话回访</option>
						<option value="1" ${task.visit == 1 ? "selected" : ""}>上门回访</option>
						<option value="2" ${task.visit == 2 ? "selected" : ""}>网络咨询</option>
				</select></li>
				<li><label>备注</label>
				<textarea name="task.note" id="task.note" cols="" rows="" class="textinput">${task.note}</textarea></li>
				<li><label>&nbsp;</label>
				<input type="hidden" name="task.tid" value="${task.tid}">
				<input type="submit" class="btn" value="确认保存" /></li>
			</ul>
		</form>
	</div>


</body>
</html>
