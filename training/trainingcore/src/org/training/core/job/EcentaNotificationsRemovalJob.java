package org.training.core.job;

/*
    * Job class for the modification of EcentaNotifications entities
    * EcentaNotificationRemovalJob extends AbstractJobPerformable
    * Method which will get EcentaNotification Entities which are 365 days or more old using DAO class
    * Modify these entities [read->true; deleted->true]
    * Persist them into the database
*/

import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.crud.CustomEcentaNotificationCRUDService;
import org.training.core.model.EcentaNotificationModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import org.training.core.model.EcentaNotificationRemovalCronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;
import org.training.core.dao.CustomEcentaNotificationsDAO;

public class EcentaNotificationsRemovalJob extends AbstractJobPerformable<EcentaNotificationRemovalCronJobModel> {

    private CustomEcentaNotificationsDAO customEcentaNotificationsDAO;
    private CustomEcentaNotificationCRUDService customEcentaNotificationCRUDService;

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

            if(!Objects.isNull(ecentaNotificationModel.getRead()))
            {
                if (!Boolean.TRUE.equals(ecentaNotificationModel.getRead()))
                {
                    ecentaNotificationModel.setRead(Boolean.TRUE);                          // marking them as read
                    ecentaNotificationModel.setDeleted(Boolean.TRUE);                       // marking them as deleted
                    ecentaNotificationModelListToBeDeleted.add(ecentaNotificationModel);    // adding them to a list
                }
            } else {
                LOG.info("EcentaNotification Entity -> " + ecentaNotificationModel + " has null value in read attribute");
            }
        }

        if(!CollectionUtils.isEmpty(ecentaNotificationModelListToBeDeleted))
        {
            for(final EcentaNotificationModel currentEcentaNotificationItem : ecentaNotificationModelListToBeDeleted)
            {
                // testing for Task: 7 -> ModelService
                getCustomEcentaNotificationCRUDService().updateEcentaNotification(currentEcentaNotificationItem);
            }
        }

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

        LOG.info("\nDAO task successfully tested!\n");
        /*  Testcase for executing the DAO Task ends    */

        /*  TestCase for testing ModelService Task starts */

        // testing the function for creating notification
        LOG.info("\nTesting For Creating the notification\n");
        EcentaNotificationModel newEcentaNotificationItem = getCustomEcentaNotificationCRUDService().createEcentaNotification();
        getCustomEcentaNotificationCRUDService().updateEcentaNotification(newEcentaNotificationItem);
        LOG.info("Creation Function passed!!");

        // testing the function for cloning the notification
        LOG.info("\nTesting For Cloning the notification\nFetched Notification:" + typeAndB2bCustomerNotificationList.get(0)+"\nExecuting the function for Cloning");
        getCustomEcentaNotificationCRUDService().cloneEcentaNotification(typeAndB2bCustomerNotificationList.get(0));
        LOG.info("Cloning Function Executed:\n");

        // testing the function for deleting the notification
        LOG.info("\nTesting For Deleting the notification\nFetched Notification:" + priorityAndB2bCustomerNotificationList.get(0)+"\nExecuting the function for deletion");
        getCustomEcentaNotificationCRUDService().deleteEcentaNotification(priorityAndB2bCustomerNotificationList.get(0));
        LOG.info("Deletion Function Executed:\n"+((Objects.isNull(priorityAndB2bCustomerNotificationList.get(0)))?"No Item Present":priorityAndB2bCustomerNotificationList.get(0)));
        /*  TestCase for testing ModelService Task Ends */

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

    public CustomEcentaNotificationCRUDService getCustomEcentaNotificationCRUDService() {
        return customEcentaNotificationCRUDService;
    }

    public void setCustomEcentaNotificationCRUDService(CustomEcentaNotificationCRUDService customEcentaNotificationCRUDService) {
        this.customEcentaNotificationCRUDService = customEcentaNotificationCRUDService;
    }
}
