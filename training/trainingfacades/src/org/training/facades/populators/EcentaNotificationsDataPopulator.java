package org.training.facades.populators;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.b2bcommercefacades.company.data.B2BUnitData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.training.core.enums.NotificationPriorityEnumeration;
import org.training.core.enums.NotificationTypeEnumeration;
import org.training.core.model.EcentaNotificationModel;
import org.training.facades.product.data.EcentaNotificationData;

import java.util.Date;

public class EcentaNotificationsDataPopulator implements Populator<EcentaNotificationModel, EcentaNotificationData>
{

    private Converter<CustomerModel, CustomerData> customerModelCustomerDataConverter;
    private Converter<B2BUnitModel, B2BUnitData> b2BUnitModelB2BUnitDataConvert;

    @Override
    public void populate(EcentaNotificationModel sourceEcentaNotificationModel, EcentaNotificationData targetEcentaNotificationData) throws ConversionException
    {
        // Fetching the values from source EcentaNotification Class
        String sourceId = sourceEcentaNotificationModel.getId();
        B2BCustomerModel sourceB2BCustomer = sourceEcentaNotificationModel.getB2bCustomer();
        Date sourceDate = sourceEcentaNotificationModel.getDate();
        NotificationTypeEnumeration sourceType = sourceEcentaNotificationModel.getType();
        NotificationPriorityEnumeration sourcePriority = sourceEcentaNotificationModel.getPriority();
        String sourceMessage = sourceEcentaNotificationModel.getMessage();
        Boolean sourceRead = sourceEcentaNotificationModel.getRead();
        Boolean sourceDeleted = sourceEcentaNotificationModel.getDeleted();
        String sourceTitle = sourceEcentaNotificationModel.getTitle();
        B2BUnitModel sourceB2BUnit = sourceEcentaNotificationModel.getB2bUnit();

        // Setting the values to the target EcentaNotificationData
        targetEcentaNotificationData.setId(sourceId);
        targetEcentaNotificationData.setB2bCustomer(getCustomerModelCustomerDataConverter().convert(sourceB2BCustomer));
        targetEcentaNotificationData.setDate(sourceDate);
        targetEcentaNotificationData.setType(sourceType);
        targetEcentaNotificationData.setPriority(sourcePriority);
        targetEcentaNotificationData.setMessage(sourceMessage);
        targetEcentaNotificationData.setRead(sourceRead);
        targetEcentaNotificationData.setDeleted(sourceDeleted);
        targetEcentaNotificationData.setTitle(sourceTitle);
        targetEcentaNotificationData.setB2bUnit(getB2BUnitModelB2BUnitDataConvert().convert(sourceB2BUnit));
    }

    public Converter<CustomerModel, CustomerData> getCustomerModelCustomerDataConverter() {
        return this.customerModelCustomerDataConverter;
    }

    public void setCustomerModelCustomerDataConverter(Converter<CustomerModel, CustomerData> customerModelCustomerDataConverter) {
        this.customerModelCustomerDataConverter = customerModelCustomerDataConverter;
    }

    public Converter<B2BUnitModel, B2BUnitData> getB2BUnitModelB2BUnitDataConvert() {
        return this.b2BUnitModelB2BUnitDataConvert;
    }

    public void setB2BUnitModelB2BUnitDataConvert(Converter<B2BUnitModel, B2BUnitData> b2BUnitModelB2BUnitDataConvert) {
        this.b2BUnitModelB2BUnitDataConvert = b2BUnitModelB2BUnitDataConvert;
    }
}

