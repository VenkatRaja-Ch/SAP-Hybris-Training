package org.training.core.interceptors.impl;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.keygenerator.impl.PersistentKeyGenerator;
import org.training.core.model.EcentaNotificationModel;

import java.util.Objects;

public class EcentaNotificationInitDefaultsInterceptor implements InitDefaultsInterceptor<EcentaNotificationModel>
{

    private PersistentKeyGenerator ecentaNotificationPersistentKeyGenerator;

    @Override
    public void onInitDefaults(EcentaNotificationModel currentEcentaNotificationItem, InterceptorContext interceptorContext) throws InterceptorException
    {
        if(!(Objects.isNull(currentEcentaNotificationItem)))
        {
            final String alphaNumericID = ecentaNotificationPersistentKeyGenerator.generate().toString();
            currentEcentaNotificationItem.setId(alphaNumericID);
        }
        else {
            throw new InterceptorException("No object was found in currentEcentaNotificationItem");
        }
    }

    public PersistentKeyGenerator getEcentaNotificationPersistentKeyGenerator() {
        return ecentaNotificationPersistentKeyGenerator;
    }

    public void setEcentaNotificationPersistentKeyGenerator(PersistentKeyGenerator ecentaNotificationPersistentKeyGenerator) {
        this.ecentaNotificationPersistentKeyGenerator = ecentaNotificationPersistentKeyGenerator;
    }
}