package org.training.core.crud;

import org.training.core.model.EcentaNotificationModel;

public interface CustomEcentaNotificationCRUDService
{

    EcentaNotificationModel createEcentaNotification();

    void updateEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem);

    EcentaNotificationModel cloneEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem);

    void deleteEcentaNotification(final EcentaNotificationModel currentEcentaNotificationList);
}
