window.onload = function() {
	bind(ele("selAll"),"click",function(){
		handleSelectAll("mid",this.checked) ;
	}) ;
	bind(ele("delBtn"),"click",function(){
		handleDelete("mid","pages/back/member/MemberServletBack/rm?p=p") ;
	}) ;
}