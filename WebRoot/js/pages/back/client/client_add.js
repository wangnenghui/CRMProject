window.onload = function() {
	bind(ele("client.name"),"blur",function(){
		validateName() ;
	}) ;
	
	bind(ele("client.tel"),"blur",function(){
		validateTel() ;
	}) ;
	bind(ele("client.email"),"blur",function(){
		validateEmail() ;
	}) ;
	bind(ele("client.qq"),"blur",function(){
		validateQq() ;
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
	return validateName() & validateTel() & validateEmail() & validateQq() ;
}
function validateName() {
	return validateEmpty("client.name") ;
}
function validateTel() {
	return validateEmpty("client.tel") ;
}
function validateEmail() {
	return validateRegex("client.email",/^\w+@\w+\.\w+$/) ;
}
function validateQq() {
	return validateEmpty("client.qq") ;
}