window.onload = function() {
	bind(ele("selAll"),"click",function(){
		handleSelectAll("rid",this.checked) ;
	}) ;
	bind(ele("delClient"),"click",function(){
		handleDelete("rid","pages/back/role/RoleServletBack/rm?p=p") ;
	}) ;
}