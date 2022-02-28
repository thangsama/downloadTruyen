<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset = "UTF-8"></head>
<title> Download truyện</title>
</head>
<body>
<h3>Download Truyện ở truyenfull.vn</h3>
<form method="POST" action ="${pageContext.request.contextPath}/infoBook">
<table border="0">
<tr><td>Điền link truyện tại đây</td>
<tr><td><input type ="text" name="url"  placeholder="https://truyenfull.vn/de-ba/"></td></tr>
<tr><td><span>* chú ý ghi chính xác url theo mẫu trên, có 1 gạch chéo ở cuối link "/"</span></td></tr>
<tr>
<td><input type = "submit" value = "Submit"/></td>
</tr>
</table>
</form>

<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>