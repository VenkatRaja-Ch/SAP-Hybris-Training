<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<spring:htmlEscape defaultHtmlEscape="true"/>
<nav:pagination top="true" supportShowPage="${isShowPageAllowed}" supportShowAllowed="${isShowAllAllowed}"/>

<div class="ecentaNotification_listing ecentaNotification_list">
    <c:forEach items="${ecentaNotification.results}">
        <!-- something to be done -->
    </c:forEach>
</div>