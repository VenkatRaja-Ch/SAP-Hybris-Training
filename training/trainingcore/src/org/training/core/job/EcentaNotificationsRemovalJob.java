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
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfig;
import de.hybris.platform.solrfacetsearch.config.FacetSearchConfigService;
import de.hybris.platform.solrfacetsearch.config.exceptions.FacetConfigServiceException;
import de.hybris.platform.solrfacetsearch.indexer.IndexerService;
import de.hybris.platform.solrfacetsearch.indexer.exceptions.IndexerException;
import de.hybris.platform.solrfacetsearch.model.config.SolrFacetSearchConfigModel;
import de.hybris.platform.tx.Transaction;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.training.core.dao.CustomEcentaNotificationsDAO;

public class EcentaNotificationsRemovalJob extends AbstractJobPerformable<EcentaNotificationRemovalCronJobModel> {

    // TODO: Ask the Mentor regarding the SITE_UID concept and what would be the SITE_UID for this task

    public static final String SITE_UID = "apparel-uk";
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
        cal.add(Calendar.DAY_OF_MONTH, -noOfDaysOldToRemove);
        final Date oldDate = cal.getTime();
        final List<EcentaNotificationModel> ecentaNotificationModelListToBeDeleted = new ArrayList<>();
        final List<EcentaNotificationModel> ecentaNotificationModelList = customEcentaNotificationsDAO.findAllNotificationOlderThanSpecificDays(oldDate);       // list of entities which are older than one year
        LOG.info("EcentaNotifications Which are older than specific date size: " + ecentaNotificationModelList.size());
        for(final EcentaNotificationModel ecentaNotificationModel : ecentaNotificationModelList) {
//            if (CollectionUtils.isEmpty(ecentaNotificationModel.getRead())) {

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

        try{
            final SolrFacetSearchConfigModel facetSearchConfigModel = baseSiteService.getBaseSiteForUID(SITE_UID).getSolrFacetSearchConfiguration();  //todo: check SITE_UID
            final FacetSearchConfig facetSearchConfig = facetSearchConfigService.getConfiguration(facetSearchConfigModel.getName());
            indexerService.performFullIndex(facetSearchConfig);
        }
        catch (final FacetConfigServiceException | IndexerException e){
            LOG.error("Could not index the product --> " + e);
        }


        LOG.info("facetSearchConfigModel line has been executed!");

        /*  Testcase for executing the DAO Task starts    */
        /* Task 1: Fetching a B2BCustomer */
        final B2BCustomerModel b2BCustomer1 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("william.hunter@rustic-hw.com").get(0);
        final B2BCustomerModel b2BCustomer2 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("ulf.becker@rustic-hw.com").get(0);
        final B2BCustomerModel b2BCustomer3 = customEcentaNotificationsDAO.findB2BCustomerUsingUID("mingmei.wang@pronto-hw.com").get(0);

        /* Task 2: Fetching the EcentaNotifications for that B2BCustomer */

        //todo: Use the fetched B2BCustomer to extract all the EcentaNotifications associated with fetched B2BCustomer.

        /*  Testcase for executing the DAO Task ends    */

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
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
