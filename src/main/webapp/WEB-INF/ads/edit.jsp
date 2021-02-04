<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="This Add Page" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Please edit your ad!</h1>
    <div class="col-md-6">
        <form action="/ads/editAd" method="post">
            <h3><c:out value="${ad.title}"/></h3>
            <label for="titleInput"><input type="text" name="titleInput" id="titleInput" placeholder="update title"></label>
            <p><c:out value="${ad.description}"/></p>
            <label for="descriptionInput"><input type="text" name="descriptionInput" id="descriptionInput" placeholder="update description"></label>
            <button type="submit" class="btn btn-primary">Submit Changes</button>
            <p>Delete ad</p><input type="checkbox" class="form-check-input" id="deleteAd" name="deleteAd" value="true">
            <input type="hidden" name="ad-id" id="ad-id" value="${ad.id}">
        </form>
    </div>
</div>
</body>
</html>
