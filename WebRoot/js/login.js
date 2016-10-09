$(function() {
	$('.loginbox').css({
		'position' : 'absolute',
		'left' : ($(window).width() - 692) / 2
	});
	$(window).resize(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
	})
});
window.onload = function() {
	bind(ele("member.mid"),"blur",function(){
		validateMid() ;
	}) ;
	bind(ele("member.password"),"blur",function(){
		validatePassword() ;
	}) ;
	bind(ele("loginForm"),"submit",function(e){
		if (validateForm()) {
			this.submit() ;
		} else {
			stopFormSubmit(e) ;
		}
	}) ;
}
function validateForm() {
	return validateMid() & validatePassword() ;
}
function validateMid() {
	var obj = ele("member.mid") ; 
	if (obj != null) {
		if (obj.value == "") {	// 此时没有输入数据
			setStyle(obj,"mid_failure","<font color='red'>X</font>") ;
			return false ;
		} else {
			setStyle(obj,"mid_success","<font color='green'>√</font>") ;
			return true ;
		}
	}
	return false ;
}
function validatePassword() {
	var obj = ele("member.password") ; 
	if (obj != null) {
		if (obj.value == "") {	// 此时没有输入数据
			setStyle(obj,"password_failure","<font color='red'>X</font>") ;
			return false ;
		} else {
			setStyle(obj,"password_success","<font color='green'>√</font>") ;
			return true ;
		}
	}
	return false ;
}