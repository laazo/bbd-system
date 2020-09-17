<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bank Balance and Dispensing System</title>
    <style>
        .container {
            margin: 0 auto;
            width: 600px;
            border: 1px solid #CCCCCC;
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>Draw from account</h3>
    <form action="withdrawProcess" method="post">
        Type: <%= request.getParameter("type") %> <br>
        Number: <%= request.getParameter("number") %> <br>
        Required amount: <input type = "number" name = "amount">
        <input type="hidden" name="number" value="<%= request.getParameter("number")%>">
        <input type="hidden" name="type" value="<%= request.getParameter("type")%>">
        <button type="submit">Submit</button>
    </form>
    <c:if test="${!empty message}">
        <p>${message}</p>
    </c:if>
    <a style="margin-top: 10px;" href="/transactional-accounts"><button type="button">Back</button></a>
</div>
</body>
</html>