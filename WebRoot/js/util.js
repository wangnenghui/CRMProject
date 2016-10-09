function preview(file) {
	var prevDiv = document.getElementById('preview');
	if (file.files && file.files[0]) {
		var reader = new FileReader();
		reader.onload = function(evt) {
			prevDiv.innerHTML = '<img src="' + evt.target.result + '" />';
		} 
		reader.readAsDataURL(file.files[0]);
	} else {
		prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
	}
}

function ele(eleId) {
	return document.getElementById(eleId) ;
}

// 验证数据是否为空
function validateEmpty(eleId) {
	var obj = ele(eleId) ;
	if (obj != null) {
		if (obj.value == "") {	// 此时没有输入数据
			setFailure(obj) ;
			return false ;
		} else {
			setSuccess(obj) ;
			return true ;
		}
	}
	return false ;
}
//验证两个组件的内容是否相同
function validateEquals(seleid,deleid) {
	if (validateEmpty(deleid)) {	// 判断目标是否有内容输入
		var sObj = ele(seleid) ;	// newpass
		var dObj = ele(deleid) ;	// confpass
		if (sObj.value == dObj.value) {	// 成功
			setSuccess(dObj) ;
			return true ;
		} else {
			setFailure(dObj) ;
			return false ;
		}
	}
	return false ;
}
function validateRegex(eleId,regex) {
	if (validateEmpty(eleId)) {
		if (!regex.test(ele(eleId).value)) {
			setFailure(ele(eleId)) ;
			return false ;
		} else {
			setSuccess(ele(eleId)) ;
			return true ;
		}
	}
	return false ;
}
function setStyle(obj,className,txt) {
	obj.className = className ;
	var objSpan = ele(obj.id + "Span") ;
	if (objSpan != null) {
		objSpan.innerHTML = txt ;
	}
}
function setSuccess(obj) {
	setStyle(obj,"success","<font color='green'>√</font>") ;
}
function setFailure(obj) {
	setStyle(obj,"failure","<font color='red'>X</font>") ;
}
function bind(obj,event,fun) {
	obj.addEventListener(event,fun,false) ;
}

function stopFormSubmit(e) {
	if (e && e.preventDefault) {	// 现在是在W3C标准下执行
		e.preventDefault() ;	// 阻止浏览器的动作
	} else {	// 专门针对于IE浏览器的处理
		window.event.returnValue = false ;
	}

}
/**
 * 实现全选操作，可以适用于单个元素或一组元素
 * @param eieId 要实现全选的复选框的id
 * @param status 当前复选框的状态
 */
function handleSelectAll(eleId,status) {
	var obj = document.all(eleId) ;	// 取得指定名称的元素对象
	if (obj.length == undefined) {	// 是一个元素
		obj.checked = status ;
	} else {	// 多个元素
		for (var x = 0 ; x < obj.length ; x ++) {
			obj[x].checked = status ;
		}
	}
}
/**
 * 实现选中数据的删除处理操作
 * @param eleId 要删除的元素ID
 * @param url 执行删除的JSP页面路径，但是考虑到实际的情况，所以此处传递的一定是“xxx?a=a”
 */
function handleDelete(eleId,url) {
	var obj = document.all(eleId) ;	// 取得指定名称的元素对象
	var ids = "" ;		// 保存所有要删除的数据
	if (obj.length == undefined) {	// 是一个元素
		if (obj.checked) {	// 当前被选中
			ids += obj.value + "|" ;
		}
	} else {	// 多个元素
		for (var x = 0 ; x < obj.length ; x ++) {
			if (obj[x].checked) {
				ids += obj[x].value + "|" ;
			}
		}
	}
	if (ids == "") {
		alert("您还未选择要删除的数据，请确认后删除！") ;
	} else {	// 有数据选择了
		if (window.confirm("您确定要删除这些数据吗？")) {
			window.location = url + "&ids=" + ids ;
		}
	}
}
