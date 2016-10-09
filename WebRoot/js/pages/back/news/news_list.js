var jsUrl = "" ;
$(document).ready(function() {
	$(".click").click(function() {
		var eleId = this.id ;
		$(".tip").fadeIn(200,function(){
			if (eleId == "liButAdd") {	// 如果是由
				document.getElementById("pMsg").innerHTML = "是否要添加新公告？" ;
				jsUrl = "pages/back/news/NewsServletBack/addPre" ;
			} 
			if (eleId == "liButRm") {	// 删除处理
				var nid = "" ;	// 保存所有的删除的ID编号
				var nidEle = document.all("nid") ;
				if (nidEle.length != undefined) {
					for (var x = 0 ; x < nidEle.length ; x ++) {
						if (nidEle[x].checked) {
							nid += nidEle[x].value + "|" ;
						}
					}
				} else {
					if (nidEle.checked) {
						nid = nidEle.value + "|" ;
					}
				} 
				if (nid != "") {
					jsUrl = "pages/back/news/NewsServletBack/rm?ids=" + nid ;
					document.getElementById("pMsg").innerHTML = "是否要删除这些公告？" ;
				} else {	// 没有选择任何的数据
					jsUrl = "" ;
					document.getElementById("pMsg").innerHTML = "未选择要删除公告！" ;
				}
			}
			
			if (eleId == "liButEdit") {	// 现在执行的是修改处理操作
				var nidEle = document.all("nid") ;
				var count = 0  ;	// 保存选中的个数
				var nid = 0 ;
				if (nidEle.length != undefined) {
					for (var x = 0 ; x < nidEle.length ; x ++) {
						if (nidEle[x].checked) {
							nid = nidEle[x].value ;
							count ++ ;
						}
					}
				} else {
					if (nidEle.checked) {
						nid = nidEle.value ;
						count ++ ;
					}
				}
				
				if (count == 1) {	// 现在只有一个被选中
					jsUrl = "pages/back/news/NewsServletBack/editPre?news.nid=" + nid ;
					document.getElementById("pMsg").innerHTML = "是否确认对信息的修改 ？" ;
				} else {
					jsUrl = "" ;
					document.getElementById("pMsg").innerHTML = "更新信息选择错误，必须选择一条要更新的公告！" ;
				} 
			}
		});
	});

	$(".tiptop a").click(function() {
		$(".tip").fadeOut(200);
	});

	$(".sure").click(function() {
		$(".tip").fadeOut(100,function() {
			if (jsUrl != "") {
				window.location = jsUrl ;
			}
		});
	});

	$(".cancel").click(function() {
		$(".tip").fadeOut(100);
	});
	$('.tablelist tbody tr:odd').addClass('odd');
});