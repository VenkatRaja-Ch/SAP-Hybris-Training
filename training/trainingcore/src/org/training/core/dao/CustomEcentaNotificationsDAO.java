package org.training.core.dao;

// imports
import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.model.EcentaNotificationModel;
import org.training.core.enums.NotificationTypeEnumeration;
import org.training.core.enums.NotificationPriorityEnumeration;

import java.util.Date;
import java.util.List;

public interface CustomEcentaNotificationsDAO{

    // Task: 4 -> CronJobs
    List<EcentaNotificationModel> findAllNotificationOlderThanSpecificDays(final Date oldDate);

    // Task: 6 -> DAO
    /* Finds the B2BCustomer using the passed uid */
    List<B2BCustomerModel> findB2BCustomerUsingUID(final String b2bCustomerUid);

    /* Find the Ecenta Notification for the passed B2B Customer */
    List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(final B2BCustomerModel b2BCustomerModel);

    /* Finds the Ecenta Notification for the passed B2B Customer with Specific Type */
    List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificType(final B2BCustomerModel b2BCustomerModel, final NotificationTypeEnumeration ecentaNotificationType);

    /* Finds the Ecenta Notification for the passed B2B Customer with Specific Priority */
    List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificPriority(B2BCustomerModel b2BCustomerModel, final NotificationPriorityEnumeration ecentaNotificationPriority);
}
