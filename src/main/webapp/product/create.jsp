<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/8/2022
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
          crossorigin="anonymous"></script>
  <style>
    div {
      margin: auto;
      width: 500px;
    }
  </style>
</head>
<body>
<div>
  <h1>Create Imployee</h1>
  <form method="post">
    <div class="mb-3 mt-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="text" class="form-control" id="email" placeholder="Enter Email" name="email">
    </div>
    <div class="mb-3">
      <label for="address" class="form-label">Address</label>
      <input type="text" class="form-control" id="address" placeholder="Enter address" name="address">
    </div>
    <div class="mb-3">
      <label for="phonenumber" class="form-label">Phone Number</label>
      <input type="text" class="form-control" id="phonenumber" placeholder="Enter phone number" name="phonenumber">
    </div>
    <div class="mb-3">
      <label for="salary" class="form-label">Salary</label>
      <input type="text" class="form-control" id="salary" placeholder="Enter salary" name="salary">
    </div>
    <div class="mb-3">
      <label for="department" class="form-label">Department</label>
      <select class="form-control" id="department" name="departmentid">
        <option >---------</option>
        <c:forEach items="${departments}" var="c">
          <option value="${c.getId()}"><c:out value="${c.getName()}"/></option>
        </c:forEach>

      </select>
    </div>
    <button type="submit" class="btn btn-primary">Create</button>
    <a href="imployee">
      <button type="button" class="btn btn-secondary">Cancel</button>
    </a>
  </form>
</div>
</body>
</html>
