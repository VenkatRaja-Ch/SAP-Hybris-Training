package org.training.core.crud.impl;

import de.hybris.platform.core.GenericSearchConstants;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.log4j.Logger;
import org.training.core.crud.CustomEcentaNotificationCRUDService;
import org.training.core.job.EcentaNotificationsRemovalJob;
import org.training.core.model.EcentaNotificationModel;

import java.time.LocalDateTime;
import java.util.Objects;

public class CustomEcentaNotificationCRUDServiceImpl implements CustomEcentaNotificationCRUDService {

    private ModelService modelService;
    private final static Logger LOG = Logger.getLogger(EcentaNotificationsRemovalJob.class.getName());

    @Override
    public EcentaNotificationModel createEcentaNotification() {

        return getModelService().create(EcentaNotificationModel.class);
    }

    @Override
    public void updateEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem){

        if(!Objects.isNull(currentEcentaNotificationItem))
        {
            getModelService().save(currentEcentaNotificationItem);
        }
    }

    @Override
    public void cloneEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem) {

        try {
            if (!Objects.isNull(currentEcentaNotificationItem)) {
                getModelService().clone(currentEcentaNotificationItem).setId("cloneItem_" + LocalDateTime.now());
            }
        } catch (Exception e){
            e.printStackTrace();
            LOG.info("\n\n\nError caught\n\n\n");
        }
    }

    @Override
    public void deleteEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem) {

        if(!Objects.isNull(currentEcentaNotificationItem))
            getModelService().remove(currentEcentaNotificationItem);
    }

    /* GETTERS & SETTERS */
    public ModelService getModelService() {
        return modelService;
    }
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
