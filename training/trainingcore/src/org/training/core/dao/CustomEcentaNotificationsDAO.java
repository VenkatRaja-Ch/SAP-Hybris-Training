package org.training.core.dao;

// imports
import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.model.EcentaNotificationModel;

import java.util.Date;
import java.util.List;

public interface CustomEcentaNotificationsDAO{

    public List<EcentaNotificationModel> findAllNotificationOlderThanSpecificDays(final Date oldDate);

    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(B2BCustomerModel b2BCustomerModel);

    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificType(B2BCustomerModel b2BCustomerModel, String ecentaNotificationType);

    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificPriority(B2BCustomerModel b2BCustomerModel, String ecentaNotificationPriority);
}
