<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${success != null}">
<script type="text/javascript">
	if('${close}' == 'true'){
		alert('${success}',function(){
	    	parent.location.reload();
	    	closeWindow();
		});
	}else if('${success}'){
		alert('${success}');
	}
	$.post('removeMessage.do');
</script>
</c:if>
<c:if test="${error != null}">
<script type="text/javascript">
	if('${close}' == 'true'){
		alert('${error}',function(){
	    	parent.location.reload();
	    	closeWindow();
		});
	}else if('${error}'){
		alert('${error}');
	}
	$.post('removeMessage.do');
</script>
</c:if>
