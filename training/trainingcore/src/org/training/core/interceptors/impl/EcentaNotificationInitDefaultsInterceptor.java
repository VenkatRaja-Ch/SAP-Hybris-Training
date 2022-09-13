package org.training.core.interceptors.impl;

import de.hybris.platform.servicelayer.interceptor.InitDefaultsInterceptor;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.keygenerator.impl.PersistentKeyGenerator;
import org.training.core.model.EcentaNotificationModel;

public class EcentaNotificationInitDefaultsInterceptor implements InitDefaultsInterceptor<EcentaNotificationModel>
{

    private PersistentKeyGenerator ecentaNotificationPersistentKeyGenerator;

    @Override
    public void onInitDefaults(EcentaNotificationModel currentEcentaNotificationItem, InterceptorContext interceptorContext) throws InterceptorException
    {
        final String alphaNumericID = ecentaNotificationPersistentKeyGenerator.generate().toString();
        currentEcentaNotificationItem.setId( alphaNumericID );
    }

    public PersistentKeyGenerator getEcentaNotificationPersistentKeyGenerator() {
        return ecentaNotificationPersistentKeyGenerator;
    }

    public void setEcentaNotificationPersistentKeyGenerator(PersistentKeyGenerator ecentaNotificationPersistentKeyGenerator) {
        this.ecentaNotificationPersistentKeyGenerator = ecentaNotificationPersistentKeyGenerator;
    }
}