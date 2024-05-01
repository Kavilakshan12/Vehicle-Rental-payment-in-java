<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Payment Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">

</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: rgb(5, 138, 246)">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand">Payment Management</a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Payment</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <!-- Add the GIF here with width and height attributes -->
<img src="main/webapp/image/img01.jpg" alt="Loading IMG">

            
            <c:if test="${Payment != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${Payment == null}">
                <form action="insert" method="post">
            </c:if>

            <caption>
                <h2>
                    <c:if test="${Payment != null}">
                        Edit Payment
                    </c:if>
                    <c:if test="${Payment == null}">
                        Add New Payment
                    </c:if>
                </h2>
            </caption>

            <c:if test="${Payment != null}">
                <input type="hidden" name="id" value="<c:out value='${Payment.id}' />" />
            </c:if>

            <fieldset class="form-group">
                <label>User Name</label>
                <input type="text" value="<c:out value='${Payment.uname}' />" class="form-control" name="Uname" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>User Email</label>
                <input type="text" value="<c:out value='${Payment.email}' />" class="form-control" name="Email">
            </fieldset>

            <fieldset class="form-group">
                <label>Payment Amount</label>
                <input type="text" value="<c:out value='${Payment.payAmount}' />" class="form-control" name="PayAmount">
            </fieldset>

            <fieldset class="form-group">
                <label>Type of Payment</label>
                <input type="text" value="<c:out value='${Payment.typeOfPayment}' />" class="form-control" name="TypeOfPayment">
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
