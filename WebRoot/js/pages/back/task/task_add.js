window.onload = function() {
	bind(ele("task.title"),"blur",function(){
		validateTitle() ;
	}) ;
	bind(ele("task.tdate"),"blur",function(){
		validateTdate() ;
	}) ;
	bind(ele("task.client.cid"),"blur",function(){
		validateCid() ;
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
	return validateTitle() & validateCid() & validateTdate() ;
}
function validateTitle() {
	return validateEmpty("task.title") ;
}
function validateCid() {
	return validateEmpty("task.client.cid") ;
}
function validateTdate() {
	return validateRegex("task.tdate",/^\d{4}-\d{2}-\d{2}$/) ;
}