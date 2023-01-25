<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form"%>

<style><%@include file="/_ui/responsive/common/bootstrap/css/ecentaNotificationCMSComponentStyle.css"%></style>
<div class="tab-Container">
    <div class="button-Container">
        <button onclick="showPanel(0)" data-i18n-key="btn-title-all">Show All <span class="my-Badge">${allUnreadEcentaNotificationCount}</span></button>
        <button onclick="showPanel(1)" data-i18n-key="btn-title-all">High Priority <span class="my-badge">${allUnreadHighPriorityEcentaNotificationCount}</span></button>
        <button onclick="showPanel(2)" data-i18n-key="btn-title-all">Order Management <span class="my-badge">${allUnreadOrderManagementEcentaNotificationCount}</span></button>
        <button onclick="showPanel(3)" data-i18n-key="btn-title-all">News <span class="my-badge">${allUnreadNewsTypeEcentaNotificationCount}</span></button>
        <button onclick="showPanel(4)" data-i18n-key="btn-title-all">Service Tickets <span class="my-badge">${allUnreadServiceTicketsTypeEcentaNotificationCount}</span></button>
        <button onclick="showPanel(5)" data-i18n-key="btn-title-all">Workflow <span class="my-badge">${allUnreadWorkflowTypeEcentaNotificationCount}</span></button>
    </div>
    <div class="tab-panel">
        <table class="ecenta-notification-table all-ecenta-notification-table">
            <tr>
                <th>PRIORITY ICON</th>
                <th>DATE</th>
                <th>MESSAGE</th>
                <th>READ</th>
                <th>DELETE</th>
            </tr>
            <c:forEach items="${allEcentaNotifications}" var="notificationPK">
                <tr class="active">
                    <td>
                        <c:choose>
                            <c:when test="${notificationPK.priority.code == 'Low'}">
                                <img class="priority-icon low-priority"
                                     src="https://t4.ftcdn.net/jpg/00/99/98/25/240_F_99982510_N8aK38HdJixdOiw71ybjBPcdkCR545rH.jpg"
                                     alt="Low Priority">
                            </c:when>
                            <c:when test="${notificationPK.priority.code == 'Normal'}">
                                <img class="priority-icon normal-priority"
                                     src="https://cdn-icons-png.flaticon.com/128/4647/4647854.png"
                                     alt="Normal Priority">
                            </c:when>
                            <c:when test="${notificationPK.priority.code == 'High'}">
                                <img class="priority-icon high-priority"
                                     src="https://cdn-icons-png.flaticon.com/128/3696/3696619.png"
                                     alt="High Priority">
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        ${notificationPK.date}
                    </td>
                    <c:choose>
                        <c:when test="${notificationPK.read == 'true'}">
                            <td>
                                    ${notificationPK.message}
                            </td>
                        </c:when>
                        <c:when test="${notificationPK.read == 'false'}">
                            <td style='font-weight:bold'>
                                    ${notificationPK.message}
                            </td>
                        </c:when>
                    </c:choose>
                    <td>
                        <form method="get" action="/setReadNotification">
                            <input class="js-read-notification js-read-delete-icon" id="${notificationPK.pk}" type="image" src="https://icons.veryicon.com/png/o/internet--web/website-common-icons/hollow-check-mark.png" alt="read-icon"/>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="/setDeleteNotification">
                            <input class="js-delete-notification js-read-delete-icon" id="${notificationPK.pk}" type="image" src="https://cdn-icons-png.flaticon.com/512/1214/1214428.png" alt="delete-icon"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>