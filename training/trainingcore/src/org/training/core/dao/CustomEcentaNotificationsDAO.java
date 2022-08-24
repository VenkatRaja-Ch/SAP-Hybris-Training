package org.training.core.dao;

// imports
import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.model.EcentaNotificationModel;

import java.util.Date;
import java.util.List;

public interface CustomEcentaNotificationsDAO{

    // Task: 4 -> CronJobs
    public List<EcentaNotificationModel> findAllNotificationOlderThanSpecificDays(final Date oldDate);

    // Task: 6 -> DAO
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForHighPriority(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForTypeOrderManagement(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForTypeNews(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForTypeServiceTickets(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForTypeWorkFlow(B2BCustomerModel b2BCustomerModel);

    public List<B2BCustomerModel> findB2BCustomerUsingUID(final String b2bCustomerUid);

    // todo: To be asked which would be better
    public List<EcentaNotificationModel> findAllEcentaNotificationForAB2bCustomerWithSpecificOrPriority
            (B2BCustomerModel b2BCustomer, String filterForNotification );
}
