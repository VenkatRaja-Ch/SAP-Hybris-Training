package org.training.facades.cmsComponents;

import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.crud.CustomEcentaNotificationCRUDService;
import org.training.core.dao.CustomEcentaNotificationsDAO;
import org.training.core.enums.NotificationPriorityEnumeration;
import org.training.core.enums.NotificationTypeEnumeration;
import org.training.core.model.EcentaNotificationModel;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EcentaNotificationCMSComponentFacades
{

    // services
    private CustomEcentaNotificationsDAO customEcentaNotificationsDAO;
    private CustomEcentaNotificationCRUDService customEcentaNotificationCRUDService;
    private final static Logger LOG = Logger.getLogger(EcentaNotificationCMSComponentFacades.class.getName());

    // get current B2bCustomer using uid
    public List<B2BCustomerModel> getAllB2bCustomersList(String b2BCustomerUid){

        if(!(Objects.isNull(getCustomEcentaNotificationsDAO().findB2BCustomerUsingUID(b2BCustomerUid)))) {
            return getCustomEcentaNotificationsDAO().findB2BCustomerUsingUID(b2BCustomerUid);
        }
        else
            return null;
    }

    // get all EcentaNotifications
    public List<EcentaNotificationModel> getAllEcentaNotificationsForB2bCustomer(B2BCustomerModel passedB2bCustomer){

        return sortEcentaNotificationInDescWithCreationDate(
                getCustomEcentaNotificationsDAO()
                        .findAllNotificationForSpecificB2bCustomer(passedB2bCustomer)
        );
    }

    /*      FETCHING ECENTA NOTIFICATIONS WITH CERTAIN PRIORITY        */
    // get all EcentaNotifications with High Priority
    public List<EcentaNotificationModel> getAllEcentaNotificationsWithHighPriority(B2BCustomerModel passedB2bCustomer){

        if(!(Objects.isNull(getCustomEcentaNotificationsDAO()
                .findAllNotificationForSpecificB2bCustomerAndSpecificPriority(passedB2bCustomer, NotificationPriorityEnumeration.HIGH)))) {
            return sortEcentaNotificationInDescWithCreationDate(
                    getCustomEcentaNotificationsDAO().findAllNotificationForSpecificB2bCustomerAndSpecificPriority(passedB2bCustomer, NotificationPriorityEnumeration.HIGH)
            );
        }
        else
            return null;
    }


    /*      FETCHING ECENTA NOTIFICATIONS WITH CERTAIN TYPE        */
    // get all EcentaNotifications of Order Management Type
    public List<EcentaNotificationModel> getAllEcentaNotificationsOfOrderManagementType(B2BCustomerModel passedB2bCustomer){
        return sortEcentaNotificationInDescWithCreationDate(
                getCustomEcentaNotificationsDAO()
                        .findAllNotificationForSpecificB2bCustomerAndSpecificType(passedB2bCustomer, NotificationTypeEnumeration.ORDERMANAGEMENT)
        );
    }

    // get all EcentaNotifications of News Type
    public List<EcentaNotificationModel> getAllEcentaNotificationsOfNewsType(B2BCustomerModel passedB2bCustomer){
        return sortEcentaNotificationInDescWithCreationDate(
                getCustomEcentaNotificationsDAO()
                        .findAllNotificationForSpecificB2bCustomerAndSpecificType(passedB2bCustomer, NotificationTypeEnumeration.NEWS)
        );
    }

    // get all EcentaNotifications of Service Tickets Type
    public List<EcentaNotificationModel> getAllEcentaNotificationsOfServiceTicketsType(B2BCustomerModel passedB2bCustomer){
        return sortEcentaNotificationInDescWithCreationDate(
                getCustomEcentaNotificationsDAO()
                        .findAllNotificationForSpecificB2bCustomerAndSpecificType(passedB2bCustomer, NotificationTypeEnumeration.SERVICETICKETS)
        );
    }

    // get all EcentaNotifications of Workflow Type
    public List<EcentaNotificationModel> getAllEcentaNotificationsOfWorkflowType(B2BCustomerModel passedB2bCustomer){
        return getCustomEcentaNotificationsDAO().findAllNotificationForSpecificB2bCustomerAndSpecificType(passedB2bCustomer, NotificationTypeEnumeration.WORKFLOW);
    }

    // sorting EcentaNotification with respect to their creation dates
    List<EcentaNotificationModel> sortEcentaNotificationInDescWithCreationDate(List<EcentaNotificationModel> currentEcentaNotificationList){
        return currentEcentaNotificationList
                .stream()
                .sorted(
                        Comparator
                                .comparing(EcentaNotificationModel::getCreationtime)
                                .reversed()
                )
                .collect(Collectors.toList());
    }

    public EcentaNotificationModel getEcentaNotificationFromPK(final String ecentaNotificationPK){
        return getCustomEcentaNotificationsDAO().getEcentaNotificationForSpecificPk(ecentaNotificationPK);
    }


    // Counting unread Notifications from EcentaNotification List
    public int countUnreadEcentaNotifications(List<EcentaNotificationModel> passedEcentaNotificationList){

        int unreadNotifications = 0;

        for(EcentaNotificationModel currentEcentaNotificationItem : passedEcentaNotificationList){

            if(!(Objects.isNull(currentEcentaNotificationItem.getRead()))) {
                if (!(currentEcentaNotificationItem.getRead())) {
                    unreadNotifications++;
                }
            }
        }

        return unreadNotifications;
    }

    /* GETTERS AND SETTERS */
    public CustomEcentaNotificationsDAO getCustomEcentaNotificationsDAO() {
        return customEcentaNotificationsDAO;
    }

    public void setCustomEcentaNotificationsDAO(CustomEcentaNotificationsDAO customEcentaNotificationsDAO) {
        this.customEcentaNotificationsDAO = customEcentaNotificationsDAO;
    }

    public CustomEcentaNotificationCRUDService getCustomEcentaNotificationCRUDService() {
        return customEcentaNotificationCRUDService;
    }

    public void setCustomEcentaNotificationCRUDService(CustomEcentaNotificationCRUDService customEcentaNotificationCRUDService) {
        this.customEcentaNotificationCRUDService = customEcentaNotificationCRUDService;
    }
}