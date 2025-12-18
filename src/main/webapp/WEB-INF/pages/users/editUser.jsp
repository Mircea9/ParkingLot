<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Edit User">
    <h1>Edit User</h1>

    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditUser">

        <c:if test="${user != null}">
            <input type="hidden" name="user_id" value="${user.id}" />
        </c:if>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username"
                       placeholder="" value="${user.username}" required>
                <div class="invalid-feedback">
                    Username is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       placeholder="" value="${user.email}" required>
                <div class="invalid-feedback">
                    Email is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="Leave blank to keep current password">
                <div class="invalid-feedback">
                    Password is required.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="user_groups">Groups</label>
                <select class="custom-select d-block w-100" id="user_groups" name="user_groups" multiple>
                    <c:forEach var="group" items="${userGroups}">
                        <option value="${group}"
                                <c:if test="${user.groups.contains(group)}">selected</c:if>
                        >
                                ${group}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <button class="btn btn-primary btn-lg" type="submit">Save Changes</button>
            </div>
        </div>
    </form>
</t:pageTemplate>