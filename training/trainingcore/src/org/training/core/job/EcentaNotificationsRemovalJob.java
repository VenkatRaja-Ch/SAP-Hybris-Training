package org.training.core.job;

/*
    * Job class for the modification of EcentaNotifications entities
    * EcentaNotificationRemovalJob extends AbstractJobPerformable
    * Method which will get EcentaNotification Entities which are 365 days or more old using DAO class
    * Modify these entities [read->true; deleted->true]
    * Persist them into the database
*/

import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.model.EcentaNotificationModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import org.training.core.model.EcentaNotificationRemovalCronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.exceptions.ModelRemovalException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.indexer.IndexerService;
import de.hybris.platform.tx.Transaction;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.training.core.dao.CustomEcentaNotificationsDAO;

public class EcentaNotificationsRemovalJob extends AbstractJobPerformable<EcentaNotificationRemovalCronJobModel> {

    private CustomEcentaNotificationsDAO customEcentaNotificationsDAO;
    private ModelService modelService;
    private IndexerService indexerService;
    private FacetSearchConfigService facetSearchConfigService;
    private BaseSiteService baseSiteService;

    private final static Logger LOG = Logger.getLogger(EcentaNotificationsRemovalJob.class.getName());

    @Override
    public PerformResult perform(final EcentaNotificationRemovalCronJobModel ecentaNotificationRemovalCronJobModel){

        final int noOfDaysOldToRemove = ecentaNotificationRemovalCronJobModel.getXDaysOld();
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -noOfDaysOldToRemove); //todo: check
        final Date oldDate = cal.getTime();
        final List<EcentaNotificationModel> ecentaNotificationModelListToBeDeleted = new ArrayList<>();
        final List<EcentaNotificationModel> ecentaNotificationModelList = customEcentaNotificationsDAO.findAllNotificationOlderThanSpecificDays(oldDate);       // list of entities which are older than one year
        LOG.info("EcentaNotifications Which are older than specific date size: " + ecentaNotificationModelList.size());
        for(final EcentaNotificationModel ecentaNotificationModel : ecentaNotificationModelList) {

            if(!(null == ecentaNotificationModel.getRead())) {

                if (!Boolean.TRUE.equals(ecentaNotificationModel.getRead())) {

                    ecentaNotificationModel.setRead(Boolean.TRUE);                          // todo: marking them as read
                    ecentaNotificationModel.setDeleted(Boolean.TRUE);                       // todo: marking them as deleted
                    ecentaNotificationModelListToBeDeleted.add(ecentaNotificationModel);    // todo: adding them to a list
                }
            } else {
                LOG.info("EcentaNotification Entity -> " + ecentaNotificationModel + " has null value in read attribute");
            }
        }

        if(!CollectionUtils.isEmpty(ecentaNotificationModelListToBeDeleted)){

            Transaction tx = null;
            try {
                tx = Transaction.current();
                tx.begin();
                getModelService().saveAll(ecentaNotificationModelListToBeDeleted);
                tx.commit();
            }
            catch (final ModelRemovalException e){

                if(null != tx){
                    tx.rollback();
                }
                LOG.error("Could not remove the product List --> " + e);
            }
        }
        LOG.info("Transaction line has been executed!");

        LOG.info(
                "\n\n\n"
                +"facetSearchConfigModel line has been executed!"
                +"\n\n\n"
        );

        /*  Testcase for executing the DAO Task starts    */

        /* Task 1: Fetching a B2BCustomer */
        final B2BCustomerModel b2BCustomer1 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("mingmei.wang@pronto-hw.com").get(0);
        final B2BCustomerModel b2BCustomer2 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("william.hunter@rustic-hw.com").get(0);
        final B2BCustomerModel b2BCustomer3 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("ulf.becker@rustic-hw.com").get(0);
        LOG.info("\n\n\n"
                + b2BCustomer1.getName() + "\t"
                + b2BCustomer2.getName() + "\t"
                + b2BCustomer3.getName() + "\t"
                + "\n\n\n"
        );

        /* Task 2: Fetching all notification and testing the methods */
        // Fetch B2BCustomer Notification
        final List<EcentaNotificationModel> b2BCustomer1NotificationList = customEcentaNotificationsDAO.findAllNotificationForSpecificB2bCustomer( b2BCustomer1 );
        // Log Notification
        logEcentaNotification(b2BCustomer1NotificationList);

        // Fetch Type&Customer Notification
        final List<EcentaNotificationModel> typeAndB2bCustomerNotificationList = customEcentaNotificationsDAO.findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomer2,"OrderManagement");
        // Log Notification
        logEcentaNotification(typeAndB2bCustomerNotificationList);

        // Fetch Priority&Customer Notification
        final List<EcentaNotificationModel> priorityAndB2bCustomerNotificationList = customEcentaNotificationsDAO.findAllNotificationForSpecificB2bCustomerAndSpecificPriority(b2BCustomer3,"High");
        // Log Notification
        logEcentaNotification(priorityAndB2bCustomerNotificationList);

        /*  Testcase for executing the DAO Task ends    */

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    // TEST-CASE: EcentaNotification Logging function
    public void logEcentaNotification(List<EcentaNotificationModel> passedList)
    {
        if(!(null == passedList)) {
            LOG.info("ID" + "\t" + "NAME" + "\t" + "TYPE" + "\t" + "PRIORITY" + "\t" + "TITLE" + "\n");
            for (final EcentaNotificationModel ecentaNotificationModel : passedList) {
                LOG.info(
                        "\n"
                        + ecentaNotificationModel.getId() + "\t"
                        + ecentaNotificationModel.getB2bCustomer().getName() + "\t"
                        + ecentaNotificationModel.getType() + "\t"
                        + ecentaNotificationModel.getPriority() + "\t"
                        + ecentaNotificationModel.getTitle() + "\t"
                        + "\n"
                );
            }
        } else
        {
            LOG.info(
                    "\n\n\n"
                    + "empty list / no notifications found!"
                    + "\n\n\n"
            );
        }
    }

    public CustomEcentaNotificationsDAO getCustomEcentaNotificationsDAO(){
        return customEcentaNotificationsDAO;
    }

    public void setCustomEcentaNotificationsDAO(final CustomEcentaNotificationsDAO customEcentaNotificationsDAO){
        this.customEcentaNotificationsDAO = customEcentaNotificationsDAO;
    }

    public ModelService getModelService(){
        return modelService;
    }

//    @Override
    public void setModelService(final ModelService modelService){
        this.modelService = modelService;
    }

    public IndexerService getIndexerService()
    {
        return indexerService;
    }


    public void setIndexerService(final IndexerService indexerService)
    {
        this.indexerService = indexerService;
    }


    public FacetSearchConfigService getFacetSearchConfigService()
    {
        return facetSearchConfigService;
    }


    public void setFacetSearchConfigService(final FacetSearchConfigService facetSearchConfigService)
    {
        this.facetSearchConfigService = facetSearchConfigService;
    }

    public BaseSiteService getBaseSiteService()
    {
        return baseSiteService;
    }

    public void setBaseSiteService(final BaseSiteService baseSiteService)
    {
        this.baseSiteService = baseSiteService;
    }

}
