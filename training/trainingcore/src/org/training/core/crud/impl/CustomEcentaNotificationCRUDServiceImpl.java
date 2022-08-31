package org.training.core.crud.impl;

import de.hybris.platform.servicelayer.model.ModelService;

import org.training.core.crud.CustomEcentaNotificationCRUDService;
import org.training.core.model.EcentaNotificationModel;

import java.util.Objects;

public class CustomEcentaNotificationCRUDServiceImpl implements CustomEcentaNotificationCRUDService {

    private ModelService modelService;

    @Override
    public EcentaNotificationModel createEcentaNotification() {

        return getModelService().create(EcentaNotificationModel.class);
    }

    @Override
    public void updateEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem){

        if(!Objects.isNull(currentEcentaNotificationItem)) {
            getModelService().save(currentEcentaNotificationItem);
        }

    }

    @Override
    public EcentaNotificationModel cloneEcentaNotification(final EcentaNotificationModel currentEcentaNotificationItem) {

        if(!Objects.isNull(currentEcentaNotificationItem))
            return getModelService().clone(currentEcentaNotificationItem);

        return null;
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
