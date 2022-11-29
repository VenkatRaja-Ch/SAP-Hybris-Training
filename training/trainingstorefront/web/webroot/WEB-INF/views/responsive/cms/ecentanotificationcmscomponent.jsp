<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>

<html>
    <style><%@include file="/_ui/responsive/common/bootstrap/css/ecentaNotificationCMSComponentStyle.css"%></style>
    <div class="tab-Container">
        <div class="button-Container">
<%--            TODO: create a function in JS for onClick functionality --%>
            <button onclick="showPanel(0)" data-i18n-key="btn-title-all">Show All<span class="my-Badge">${allUnreadEcentaNotificationCount}</span></button>
            <button onclick="showPanel(1)" data-i18n-key="btn-title-all">High Priority<span class="my-badge">${allUnreadHighPriorityEcentaNotificationCount}</span></button>
            <button onclick="showPanel(2)" data-i18n-key="btn-title-all">Order Management<span class="my-badge">${allUnreadOrderManagementEcentaNotificationCount}</span></button>
            <button onclick="showPanel(3)" data-i18n-key="btn-title-all">News<span class="my-badge">${allUnreadNewsTypeEcentaNotificationCount}</span></button>
            <button onclick="showPanel(4)" data-i18n-key="btn-title-all">Service Tickets<span class="my-badge">${allUnreadServiceTicketsTypeEcentaNotificationCount}</span></button>
            <button onclick="showPanel(5)" data-i18n-key="btn-title-all">Workflow<span class="my-badge">${allUnreadWorkflowTypeEcentaNotificationCount}</span></button>
        </div>
    </div>
</html>