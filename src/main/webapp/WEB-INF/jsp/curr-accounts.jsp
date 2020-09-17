<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Bank Balance and Dispensing System</title>
    <style>
        .container {
            margin: 0 auto;
            width: 600px;
            border: 1px solid #CCCCCC;
            padding: 10px;
        }
        td {
            padding: 0 10px;
        }
    </style>
</head>

<body>
<div class="container">
    <h3>Currency Account Balances</h3>
    <c:if test="${!empty accounts}">
        <table>
            <thead>
            <tr>
                <th>Account Number</th>
                <th>Account Type</th>
                <th>Account Balance</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td>${account.accountNumber}</td>
                    <td>${account.accountType.description}</td>
                    <td>${account.displayBalance}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty accounts}">
        <p style="color: red">No accounts to display.</p>
    </c:if>
    <a style="margin-top: 10px;" href="/"><button type="button">Back</button></a>
</div>
</body>