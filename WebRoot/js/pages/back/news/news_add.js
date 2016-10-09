$(document).ready(function(e) {
	$(".select1").uedSelect({
		width : 345
	});
	$(".select2").uedSelect({
		width : 167
	});
	$(".select3").uedSelect({
		width : 100
	});
	$("#usual1 ul").idTabs(); 
	$('.tablelist tbody tr:odd').addClass('odd');
});
window.onload = function() {
	bind(ele("news.title"),"blur",function(){
		validateTitle() ;
	}) ;
	bind(ele("news.note"),"blur",function(){
		validateNote() ;
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
	return validateTitle() & validateNote() ;
}
function validateTitle() {
	return validateEmpty("news.title") ;
}
function validateNote() {
	return validateEmpty("news.note") ;
}