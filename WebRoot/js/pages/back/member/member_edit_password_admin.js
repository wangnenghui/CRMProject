window.onload = function() {
	bind(ele("member.password"),"blur",function(){
		validatePassword() ;
	}) ;
	bind(ele("editForm"),"submit",function(e){
		if (validateForm()) {
			this.submit() ;
		} else {
			stopFormSubmit(e) ;
		}
	}) ;
}
function validateForm() {
	return validateOldpass() ;
}
function validatePassword() {
	return validateEmpty("member.password") ;
}