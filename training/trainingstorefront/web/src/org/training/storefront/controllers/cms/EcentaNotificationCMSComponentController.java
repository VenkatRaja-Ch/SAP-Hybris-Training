package org.training.storefront.controllers.cms;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.core.model.EcentaNotificationCMSComponentModel;
import org.training.facades.cmsComponents.EcentaNotificationCMSComponentFacades;
import org.training.storefront.controllers.ControllerConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Scope("tenant")
@Controller("EcentaNotificationCMSComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.EcentaNotificationCMSComponent)

public class EcentaNotificationCMSComponentController
        extends AbstractAcceleratorCMSComponentController<EcentaNotificationCMSComponentModel> {

    @Resource(name="userService")

    // services
    private UserService userService;
    private EcentaNotificationCMSComponentFacades ecentaNotificationCMSComponentFacades;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, EcentaNotificationCMSComponentModel component)
    {

        // all EcentaNotifications
        model.addAttribute("allEcentaNotifications",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsForB2bCustomer(
                getCurrentB2bCustomer()
            )
        );

        // All Unread EcentaNotification Count
        model.addAttribute("allUnreadEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsForB2bCustomer(
                    getCurrentB2bCustomer()
                )
            )
        );

        // all High Priority EcentaNotifications
        model.addAttribute("allHighPriorityEcentaNotification",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsWithHighPriority(
                getCurrentB2bCustomer()
            )
        );

        // all High Priority Unread EcentaNotifications count
        model.addAttribute("allUnreadHighPriorityEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsWithHighPriority(
                    getCurrentB2bCustomer()
                )
            )
        );

        // all OrderManagement Type EcentaNotifications
        model.addAttribute("allOrderManagementTypeEcentaNotification",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsOfOrderManagementType(
                getCurrentB2bCustomer()
            )
        );

        // all OrderManagement Type Unread EcentaNotifications count
        model.addAttribute("allUnreadOrderManagementEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsOfOrderManagementType(
                    getCurrentB2bCustomer()
                )
            )
        );

        // all News Type EcentaNotifications
        model.addAttribute("allNewsTypeEcentaNotification",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsOfNewsType(
                getCurrentB2bCustomer()
            )
        );

        // all News Type Unread EcentaNotifications count
        model.addAttribute("allUnreadNewsTypeEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsOfNewsType(
                    getCurrentB2bCustomer()
                )
            )
        );

        // all ServiceTickets Type EcentaNotifications
        model.addAttribute("allServiceTicketsTypeEcentaNotification",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsOfServiceTicketsType(
                getCurrentB2bCustomer()
            )
        );

        // all ServiceTickets Type Unread EcentaNotifications count
        model.addAttribute("allUnreadServiceTicketsTypeEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsOfServiceTicketsType(
                    getCurrentB2bCustomer()
                )
            )
        );

        // all Workflow Type EcentaNotifications
        model.addAttribute("allWorkflowTypeEcentaNotification",
            getEcentaNotificationCMSComponentFacades().
            getAllEcentaNotificationsOfWorkflowType(
                getCurrentB2bCustomer()
            )
        );

        // all Workflow Type Unread EcentaNotifications count
        model.addAttribute("allUnreadWorkflowTypeEcentaNotificationCount",
            getEcentaNotificationCMSComponentFacades().
            countUnreadEcentaNotifications(
                getEcentaNotificationCMSComponentFacades().
                getAllEcentaNotificationsOfWorkflowType (
                    getCurrentB2bCustomer()
                )
            )
        );
    }

    @Override
    protected String getView(EcentaNotificationCMSComponentModel component) {
        return null;
    }

    // Get Current B2BCustomer method
    private B2BCustomerModel getCurrentB2bCustomer() {

        String currentB2bCustomerUid = getUserService().getCurrentUser().getUid();
        return getEcentaNotificationCMSComponentFacades().getAllB2bCustomersList(currentB2bCustomerUid).get(0);
    }

    /* GETTERS AND SETTERS */
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public EcentaNotificationCMSComponentFacades getEcentaNotificationCMSComponentFacades() {
        return ecentaNotificationCMSComponentFacades;
    }

    @Autowired
    public void setEcentaNotificationCMSComponentFacades(EcentaNotificationCMSComponentFacades ecentaNotificationCMSComponentFacades) {
        this.ecentaNotificationCMSComponentFacades = ecentaNotificationCMSComponentFacades;
    }
}

