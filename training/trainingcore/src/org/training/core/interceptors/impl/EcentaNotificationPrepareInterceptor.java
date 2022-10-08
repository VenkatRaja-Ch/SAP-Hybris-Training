package org.training.core.interceptors.impl;

import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import org.training.core.model.EcentaNotificationModel;

import java.util.Objects;

public class EcentaNotificationPrepareInterceptor implements PrepareInterceptor<EcentaNotificationModel>
{
    @Override
    public void onPrepare(EcentaNotificationModel currentEcentaNotificationItem, InterceptorContext interceptorContext) throws InterceptorException
    {
        if(!Objects.isNull(currentEcentaNotificationItem))
        {
            if(!currentEcentaNotificationItem.getRead()){
                currentEcentaNotificationItem.setDeleted(false);
            }
        }
        else {
            throw new InterceptorException("No object was found in currentEcentaNotificationItem");
        }
    }
}
