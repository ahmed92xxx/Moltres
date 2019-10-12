<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Moltres Online Shop - ${title}</title>
<script>
	window.menu = '${title}';
	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.css" rel="stylesheet">

<!-- Bootstrap Minty theme CSS -->
<link href="${css}/bootstrap-theme-minty.css" rel="stylesheet">
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- navigation bar -->
		<%@ include file="./shared/navbar.jsp"%>

		<!-- content -->
		<div class="content">
			<c:if test="${userClickHome==true }">
				<%@ include file="home.jsp"%>
			</c:if>

			<c:if test="${userClickAbout==true }">
				<%@ include file="about.jsp"%>
			</c:if>

			<c:if test="${userClickContact==true }">
				<%@ include file="contact.jsp"%>
			</c:if>

			<c:if
				test="${userClickAllProducts==true  or userClickCategoryProducts==true}">
				<%@ include file="listProducts.jsp"%>
			</c:if>

			<c:if
				test="${userClickShowProduct==true  }">
				<%@ include file="singleProduct.jsp"%>
			</c:if>
			<c:if
				test="${userClickManageProducts==true  }">
				<%@ include file="manageProducts.jsp"%>
			</c:if>

		</div>
		<!-- footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- JavaScript -->
		<script src="${js}/jquery.js"></script>
		<script src="${js}/jquery.dataTables.js"></script>
		<script src="${js}/dataTables.bootstrap.js"></script>
		<script src="${js}/bootbox.min.js"></script>
		<script src="${js}/jquery.validate.js"></script>

		<script src="${js}/bootstrap.js"></script>
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
