let $tabButtons = $(".tab-Container .button-Container button");
let $tabPanels = $(".tab-Container .tab-panel");

function showPanel (panelIndex) {

    $tabButtons.forEach((currentPanel) => {
        currentPanel.style.display = "none";
    });

    $tabPanels[panelIndex].style.display="block";
}

showPanel(1);

$(document).ready(function(){
    $(".js-read-notification").click( function () {

        alert("read notification button was pressed!");
        let currentNotificationPK = this.id;
        ACC.ecentanotificationcmscomponent.readNotification(currentNotificationPK);
    });
});

$(document).ready(function () {
    $(".js-delete-notification").click( function () {

        let currentNotificationPK = this.id;
        ACC.ecentanotificationcmscomponent.deleteNotification(currentNotificationPK);
    });
});

ACC.ecentanotificationcmscomponent = {

    _autoload: [
        "readNotification",
        "deleteNotification"
    ],

    readNotification : function (notificationPK) {
        $(".js-read-notification").click(function () {

            let ajaxToSetReadInControllerUrl = `${ACC.config.encodedContextPath}/view/EcentaNotificationCMSComponentController/setReadNotification`;

            $.ajax({
                url : ajaxToSetReadInControllerUrl,
                data: {"notificationPK" : notificationPK},
                type: "GET",
                success: function(){
                    console.log("Successfully marked notification as Read");
                },
                error: function(error){
                    console.log(error);
                }
            });
        });
    },

    deleteNotification : function (notificationPK) {
        $(".js-delete-notification").click(function () {

            let ajaxToSetDeleteInControllerUrl = `${ACC.config.encodedContextPath}/view/EcentaNotificationCMSComponentController/setDeleteNotification`;

            $.ajax({
                url : ajaxToSetDeleteInControllerUrl,
                data: {"notificationPK" : notificationPK},
                type: "GET",
                success: function(){
                    location.reload();
                },
                error: function(error){
                    console.log(error)
                }
            });
        });
    }
}