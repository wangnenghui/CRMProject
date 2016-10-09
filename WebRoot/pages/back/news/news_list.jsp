<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.mldn.cn/c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>CRM管理系统</title>
<jsp:include page="/pages/plugins/import_file.jsp"/>
<script type="text/javascript" src="js/pages/back/news/news_list.js"></script>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="main.html" target="_top">首页</a></li>
			<li><a href="#">公告列表</a></li>
		</ul>
	</div>

	<div class="rightinfo">

		<div class="tools">

			<ul class="toolbar">
				<c:if test="${allActions['12'] != null}">
					<li class="click" id="liButAdd"><span><img src="images/t01.png" /></span>添加</li>
				</c:if>
				<c:if test="${allActions['13'] != null}">
					<li class="click" id="liButEdit"><span><img src="images/t02.png" /></span>修改</li>
				</c:if>
				<c:if test="${allActions['14'] != null}">
					<li class="click" id="liButRm"><span><img src="images/t03.png" /></span>删除</li>
				</c:if>
			</ul>

		</div>

		<jsp:include page="/pages/plugins/split_page_plugin_search.jsp"/>
		<table class="tablelist">
			<thead>
				<tr>
					<th><input id="selAll" name="selAll" type="checkbox"/></th>
					<th>编号</th>
					<th>标题</th>
					<th>公告分类</th>
					<th>发布时间</th>
					<th>是否查看</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${allNewses != null}">
					<c:forEach items="${allNewses}" var="news">
						<tr>
							<td><input id="nid" name="nid" type="checkbox" value="${news.nid}" /></td>
							<td>${news.nid}</td>
							<c:if test="${allActions['33'] != null}">
								<td><a href="<%=basePath%>/${allActions['33'].url}?news.nid=${news.nid}" target="rightFrame">
									<c:if test="${unreadMap[news.nid]}">
										<font color="red">[未读]</font>
									</c:if>
									${news.title}</a></td>
							</c:if>
							<c:if test="${allActions['33'] == null}">
								<td><c:if test="${unreadMap[news.nid]}">
										<font color="red">[未读]</font>
									</c:if>${news.title}</td>
							</c:if>
							<td>${news.type == 0 ? "普通通知" : "重要通知"}</td>
							<td>${news.pubdate}</td>
							<td>${unreadMap[news.nid] ? "未读" : 已查看}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>


		<jsp:include page="/pages/plugins/split_page_plugin_bar.jsp"/>


		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>

			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p id="pMsg">是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input id="butSure" type="button" class="sure" value="确定" />&nbsp; 
				<input id="butCancel" type="button" class="cancel" value="取消" />
			</div>
		</div>
	</div>
</body>
</html>
