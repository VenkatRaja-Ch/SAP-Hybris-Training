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
    /* Finds the B2BCustomer using the passed uid */
    public List<B2BCustomerModel> findB2BCustomerUsingUID(final String b2bCustomerUid);

    /* Find the Ecenta Notification for the passed B2B Customer */
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(B2BCustomerModel b2BCustomerModel);

    /* Finds the Ecenta Notification for the passed B2B Customer with Specific Type */
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificType(B2BCustomerModel b2BCustomerModel, String ecentaNotificationType);

    /* Finds the Ecenta Notification for the passed B2B Customer with Specific Priority */
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificPriority(B2BCustomerModel b2BCustomerModel, String ecentaNotificationPriority);
}
