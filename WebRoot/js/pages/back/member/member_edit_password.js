window.onload = function() {
	bind(ele("oldpass"),"blur",function(){
		validateOldpass() ;
	}) ;
	bind(ele("newpass"),"blur",function(){
		validateNewpass() ;
	}) ;
	bind(ele("confpass"),"blur",function(){
		validateConfpass() ;
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
	return validateOldpass() & validateNewpass() & validateConfpass() ;
}
function validateOldpass() {
	return validateEmpty("oldpass") ;
}
function validateNewpass() {
	return validateEmpty("newpass") ;
}
function validateConfpass() {
	return validateEquals("newpass","confpass") ;
}