$(document).ready(function(){
    $(".js-read-notification").click( function () {

        alert("read notification button was pressed!");
        ACC.ecentanotificationcmscomponent.readNotification();
    });
});

$(document).ready(function () {
    $(".js-delete-notification").click( function () {

        ACC.ecentanotificationcmscomponent.deleteNotification();
    });
});

ACC.ecentanotificationcmscomponent = {

    _autoload: [
        "readNotification",
        "deleteNotification"
    ],

    readNotification : function () {
        $(".js-read-notification").click(function () {

            let ajaxToSetReadInControllerUrl = `${ACC.config.encodedContextPath}/view/EcentaNotificationCMSComponentController/setReadNotification`;
            let notificationPK = $(".js-read-notification").attr("id");
            console.log("Current Notification PK: ", notificationPK);

            $.ajax({
                url : ajaxToSetReadInControllerUrl,
                data: {"notificationPK" : notificationPK},
                type: "GET",
                success: function(){
                    console.log("Successfully marked notification as Read");
                },
                error: function(error){
                    console.log("Something went wrong while marking the notification read!");
                    console.log(error);
                }
            }).done(function(data){
                if(data === "success"){
                    $(`#${notificationPK}`).css("font-weight", "normal");
                }
            });
        });
    },

    deleteNotification : function () {
        $(".js-delete-notification").click(function () {

            let ajaxToSetDeleteInControllerUrl = `${ACC.config.encodedContextPath}/view/EcentaNotificationCMSComponentController/setDeleteNotification`;
            let notificationPK = $(".js-delete-notification").attr("id");
            console.log("Current Notification PK: ", notificationPK);

            $.ajax({
                url : ajaxToSetDeleteInControllerUrl,
                data: {"notificationPK" : notificationPK},
                type: "GET",
                success: function(){
                    location.reload();
                },
                error: function(error){
                    console.log(error);
                }
            });
        });
    }
}