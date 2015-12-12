<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.customers"%>
<% customers customers = (customers) request.getAttribute("customers"); %>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title id="addTitle">Update A Customer</title>
    </head>
    <body>
        <h1> Update A Customer </h1>
        <form name="updateForm" action="updateCustomer" method="get">
            <label> Customer ID: </label>
            <input type="text" name="id" value="<%=customers.getCustID() %>" readonly/>
            <br>
            <label> First Name: </label>
            <input type="text" name="firstName" value="<%=customers.getFirstName() %>"/>
            <br>
            <label> Last Name: </label>
            <input type="text" name="lastName" value="<%=customers.getLastName() %>"/>
            <br>
            <label> Address 1: </label>
            <input type="text" name="addr1" value="<%=customers.getAddr1() %>"/>
            <br>
            <label> Address 2: </label>
            <input type="text" name="addr2" value="<%=customers.getAddr2() %>"/>
            <br>
            <label> City: </label>
            <input type="text" name="city" value="<%=customers.getCity() %>"/>
            <br>
            <label> State: </label>
            <input type="text" name="state" value="<%=customers.getState() %>"/>
            <br>
            <label> Zip Code: </label>
            <input type="text" name="zip" value="<%=customers.getZip() %>"/>
            <br>
            <label> Email Address: </label>
            <input type="text" name="emailAddr" value="<%=customers.getEmailAddr() %>"/>
            <br>
            <label> Age: </label>
            <input type="text" name="age" value="<%=customers.getAge() %>"/>
            <br>
            <input type="reset" name="reset" value="Clear"/>
            <input type="submit" name="submit" value="Update"/>
            </table>
        </form>
    </body>
</html>
