<%@page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title> Thông tin truyện</title>
</head>
<body>
<h3>Thông tin truyện</h3>
<table	 border ="1" cellpadding = "5" cellspacing ="1">
<tr>
<th>Tên truyện</th>
<th>Số chương hiện tại</th>
<c:forEach items ="${list}" var="book">
<tr>
			<td>${book.name}</td>
			<td>${book.chapter}</td>
			<td><form method = "POST" action ="${pageContext.request.contextPath}/downloadAsPdf">
<table border="0">
<tr><td>Ấn nút download bên dưới để download truyện đến chương mới nhất</td></tr>
<tr><td>* Lưu ý: tổng số chương download được có thể không giống với số chương mới nhất của truyện</br>
Vì 1 số truyện bị đăng thiếu chương</br></td>
** mỗi 1 chap tương ứng 1s, nên bạn phải đợi <a>${book.chapter}</a> giây
</tr>
<tr><td><input type= hidden name= "url" value= "${book.url}" ></td></tr>
<tr><td><input type="submit" value="Download"/></td></tr>
</table>
</form></td>
</tr>
</c:forEach>
</table>
<c:forEach items ="${list}" var="book">
<form method ="POST" action="${pageContext.request.contextPath}/downloadPart">
<table border="0">
<tr><td>Download riêng lẻ từng chương</td></tr>
<tr><td> Từ chương</td>
<tr><td><input type ="number" name="start"></td></tr>

<tr><td> đến chương</td><tr>
<tr><td><input type ="number" name="end"></td></tr>
<tr><td><input type= hidden name= "url" value= "${book.url}" ></td></tr>
<tr><td><input type = "submit" value = "Download"/></td>
</tr>

</table>
</form>
</c:forEach>
<a>  **Sau khi nhấn nút download sẽ tự động download file về trên desktop, vui lòng kiểm tra</a>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
