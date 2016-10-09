window.onload = function() {
	bind(ele("member.mid"),"blur",function(){
		validateMid() ;
	}) ;
	bind(ele("member.password"),"blur",function(){
		validatePassword() ;
	}) ;
	bind(ele("member.tel"),"blur",function(){
		validateTel() ;
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
	return validateMid() & validatePassword() & validateTel() ;
}
function validateMid() {
	return validateEmpty("member.mid") ;
}
function validatePassword() {
	return validateEmpty("member.password") ;
}
function validateTel() {
	return validateEmpty("member.tel") ;
}