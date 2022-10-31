package org.training.storefront.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.core.model.EcentaNotificationCMSComponentModel;

import javax.servlet.http.HttpServletRequest;

@Scope("tenant")
@Controller("EcentaNotificationCMSComponentController")
@RequestMapping("/view/EcentaNotificationCMSComponentController")

public class EcentaNotificationCMSComponentController
        extends AbstractCMSComponentController<EcentaNotificationCMSComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, EcentaNotificationCMSComponentModel component) {

    }

    @Override
    protected String getView(EcentaNotificationCMSComponentModel component) {
        return null;
    }
}

