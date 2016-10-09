window.onload = function() {
	bind(ele("role.title"),"blur",function(){
		validateTitle() ;
	}) ;
	bind(ele("myform"),"submit",function(e){
		if (validateForm()) {
			this.submit() ;
		} else {
			stopFormSubmit(e) ;
		}
	}) ;
} 
function validateForm() {
	return validateTitle() & validateGid() ;
}
function validateTitle() {
	return validateEmpty("role.title") ;
}
function validateGid() {
	var gid = document.all("gid") ;
	var count = 0 ;	// 保存已经选择的权限组的个数
	if (gid.length == undefined) {	// 单个元素
		if (gid.checked) {
			count ++ ;
		}
	} else {
		for (var x = 0 ; x < gid.length ; x ++) {
			if (gid[x].checked) {
				count ++ ;
			}
		}
	}
	if (count == 0) {	// 没有被选中的权限组
		ele("gidSpan").innerHTML = "<font color='red'>没有权限组被选中</font>" ;
		return false ;
	} else {
		ele("gidSpan").innerHTML = "" ;
		return true ;
	}
}