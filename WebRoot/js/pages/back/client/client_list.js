$(document).ready(function(e) {
	$(".select3").uedSelect({
		width : 152
	});
});
window.onload = function() {
	bind(ele("selAll"),"click",function(){
		handleSelectAll("cid",this.checked) ;
	}) ;
	bind(ele("delClient"),"click",function(){
		handleDelete("cid","pages/back/client/ClientServletBack/rm?p=p") ;
	}) ;
}