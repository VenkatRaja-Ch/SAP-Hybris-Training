package org.training.core.crud;


import org.training.core.model.EcentaNotificationModel;

import java.util.List;

public interface CustomEcentaNotificationCRUDService{

    public List<EcentaNotificationModel> createEcentaNotification();
    public List<EcentaNotificationModel> updateEcentaNotification();
    public List<EcentaNotificationModel> cloneEcentaNotification();
    public List<EcentaNotificationModel> deleteEcentaNotification();
}
