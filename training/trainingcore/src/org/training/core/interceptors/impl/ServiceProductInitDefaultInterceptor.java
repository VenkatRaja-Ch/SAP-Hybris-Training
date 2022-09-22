package org.training.core.interceptors.impl;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.keygenerator.impl.PersistentKeyGenerator;
import org.training.core.model.ServiceProductModel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ServiceProductInitDefaultInterceptor implements InitDefaultsInterceptor<ServiceProductModel>
{
    private PersistentKeyGenerator serviceProductIdGenerator;

    @Override
    public void onInitDefaults(ServiceProductModel currentServiceProductItem, InterceptorContext interceptorContext) throws InterceptorException
    {

        /* setting the id for the ServiceProduct Item */
        final String generatedServiceProductID = serviceProductIdGenerator.generate().toString();
        currentServiceProductItem.setId( generatedServiceProductID );

        /* setting the date for ServiceProduct  */
        ZoneId currentSystemZoneId = ZoneId.systemDefault();  // fetching current Zone
        LocalDate currentLocalDate = LocalDate.now();  // fetching current date
        Date currentDate = Date.from(currentLocalDate.atStartOfDay(currentSystemZoneId).toInstant()); // converting it into Date format
        // setting date to ServiceProduct
        currentServiceProductItem.setCreationDate(currentDate);
    }

    public PersistentKeyGenerator getServiceProductIdGenerator() {
        return serviceProductIdGenerator;
    }

    public void setServiceProductIdGenerator(PersistentKeyGenerator serviceProductIdGenerator) {
        this.serviceProductIdGenerator = serviceProductIdGenerator;
    }
}
