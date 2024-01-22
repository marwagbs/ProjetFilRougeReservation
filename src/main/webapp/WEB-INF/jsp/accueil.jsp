<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div>
    <%@ include file="/WEB-INF/fragments/searchbar.jspf" %>
</div>

<div>
    <h2>A Propos de nous</h2>
    <p></p>
</div>

<div>
    <h2> Nos coups de cœur</h2>
</div>

<div>
    <h2>Nous découvrir</h2>
    <c:forEach var="restaurant" items="${restaurants}">
        <div class="card">
            <div class="container">
                <td><c:out value="${restaurant.nom}" /></td>
            </div>
        </div>
    </c:forEach>
</div>

<div>
    <h2> Témoignages </h2>
</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>