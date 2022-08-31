package org.training.core.attributes;

import de.hybris.platform.b2b.model.B2BUnitModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

import org.training.core.model.EcentaNotificationModel;

import java.util.Objects;

public class B2BUnitDynamicAttributeHandler extends AbstractDynamicAttributeHandler<B2BUnitModel, EcentaNotificationModel> {

    private ModelService modelService;

    @Override
    public B2BUnitModel get(final EcentaNotificationModel model) {

        if(!Objects.isNull(model.getB2bCustomer())){
            if(!Objects.isNull(model.getB2bCustomer().getDefaultB2BUnit())) {
                return model.getB2bCustomer().getDefaultB2BUnit();
            }
        }

        return null;
    }

    public ModelService getModelService(){
        return modelService;
    }
    public void setModelService(final ModelService modelService){
        this.modelService = modelService;
    }
}


