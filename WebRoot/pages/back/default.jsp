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
<script type="text/javascript" src="<%=basePath%>js/pages/back/default.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">工作台</a></li>
    </ul>
    </div>
    
    
    <div class="mainbox">
    
    <div class="mainleft">
    
    
    <div class="leftinfo">
    <div class="listtitle">
    	<c:if test="${allActions['2'] != null}">
			<a href="<%=basePath%>${allActions['2'].url}" class="more1">更多</a>
		</c:if>
    	客户信息</div>
        
    <div class="maintj">
      <table class="tablelist">
        <thead>
          <tr>
            <th width="10%">编号</th>
            <th width="15%">客户姓名</th>
            <th width="5%">性别</th>
            <th width="27%">邮箱</th>
            <th width="13%">电话</th>
            <th width="15%">QQ</th>
            <th width="20%">登记时间</th>
            </tr>
        </thead>
        <tbody>
         <c:if test="${allClients != null}">
			<c:forEach items="${allClients}" var="client">
				<tr>
					<td>${client.cid}</td>
					<td>${client.name}</td>
					<td>${client.sex}</td>
					<td>${client.email}</td>
					<td>${client.tel}</td>
					<td>${client.qq}</td>
					<td>${client.reg}</td>
				</tr>
			</c:forEach>
		</c:if>
        </tbody>
      </table>
	</div>
    
    </div>
    <!--leftinfo end-->
    
    
    <div class="leftinfo">
    
   
    <div class="leftinfo">
    
    <div class="listtitle">
    
    	<a href="#" class="more1">更多</a>待办事项
    </div>    
    <ul class="newlist">
    <c:forEach items="${allTasks}" var="task">
    	<c:if test="${allActions['31'] != null}">
			<li><a href="<%=basePath%>${allActions['31'].url}?cid=${task.client.cid}&tid=${task.tid}" target="rightFrame">${task.title}</a><b>[${task.tdate}]</b></li>
		</c:if>    	
    </c:forEach>
    </ul>   
    
    </div>
    
    
   
    
    </div>
    
    
    </div>
    <!--mainleft end-->
    
    
    <div class="mainright">
    
    
    <div class="dflist">
    <div class="listtitle"><a href="<%=basePath%>${allActions['11'].url}" class="more1">更多</a>最新公告</div>    
    <ul class="newlist">
    	<c:if test="${allNewses != null}">
    		<c:forEach items="${allNewses}" var="news">
    			<c:if test="${allActions['33'] != null}">
    				<li><a href="<%=basePath%>/${allActions['33'].url}?news.nid=${news.nid}" target="rightFrame">${news.title}</a> &nbsp; [${news.pubdate}]</li>
    			</c:if>
    			<c:if test="${allActions['33'] == null}">
					<li>${news.title}</li>  &nbsp; [${news.pubdate}]  				
    			</c:if>
    		</c:forEach>
    	</c:if>
  
    </ul>        
    </div>
    
    
    <div class="dflist1">
    <div class="listtitle"><a href="#" class="more1">更多</a>信息统计</div>    
    <ul class="newlist">
    <li><i>客户数：</a></i>${clientCount}</li>
    <li><i>未完任务：</a></i>${unfinishCount}</li>
    <li><i>待办任务：</a></i>${wfinishCount}</li>

    </ul>        
    </div>
    
    

    
    
    </div>
    <!--mainright end-->
    
    
    </div>



</body>
</html>
