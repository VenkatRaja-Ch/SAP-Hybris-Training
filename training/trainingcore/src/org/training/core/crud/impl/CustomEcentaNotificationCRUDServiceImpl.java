package org.training.core.crud.impl;

import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.tx.Transaction;

import org.springframework.util.CollectionUtils;
import org.training.core.crud.CustomEcentaNotificationCRUDService;
import org.training.core.model.EcentaNotificationModel;
import org.apache.log4j.Logger;

import java.util.List;

public class CustomEcentaNotificationCRUDServiceImpl implements CustomEcentaNotificationCRUDService {

    private ModelService modelService;
    private final static Logger LOG = Logger.getLogger(CustomEcentaNotificationCRUDServiceImpl.class.getName());

    @Override
    public List<EcentaNotificationModel> createEcentaNotification() {
        return null;
    }

    @Override
    public List<EcentaNotificationModel> updateEcentaNotification() {
        return null;
    }

    @Override
    public List<EcentaNotificationModel> cloneEcentaNotification() {
        return null;
    }

    @Override
    public List<EcentaNotificationModel> deleteEcentaNotification() {
        return null;
    }

    private void commitCurrentTransaction(List<EcentaNotificationModel> ecentaNotificationModelList)
    {
        if(!(CollectionUtils.isEmpty(ecentaNotificationModelList)))
        {
            Transaction tx = null;

            try
            {
                tx = Transaction.current();
                tx.begin();
                getModelService().saveAll(ecentaNotificationModelList);
                tx.commit();
            }
            catch( final ModelRemovalException e)
            {
                if(!(null == tx))
                    tx.rollback();

                LOG.error("Error occurred while committing the current Transaction");
            }
        }

        LOG.info("\n\ncommitCurrentTransaction() has been executed. \n\n");
    }

    /* GETTERS & SETTERS */
    public ModelService getModelService() {
        return modelService;
    }
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }
}
