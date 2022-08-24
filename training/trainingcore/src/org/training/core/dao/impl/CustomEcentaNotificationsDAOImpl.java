package org.training.core.dao.impl;

/*
    * Implementation of the CustomEcentaNotificationDAO class
*/

import de.hybris.platform.b2b.model.B2BCustomerModel;
import org.training.core.job.EcentaNotificationsRemovalJob;
import org.training.core.model.EcentaNotificationModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.*;

import org.training.core.dao.CustomEcentaNotificationsDAO;


public class CustomEcentaNotificationsDAOImpl implements CustomEcentaNotificationsDAO{

    private FlexibleSearchService flexibleSearchService;
    private B2BCustomerModel b2BCustomerModel;
    private String ecentaNotificationPriority;

    // Task: 4 -> CronJobs
    /*
        * Logic to retrieve EcentaNotification entities which are older than 365 days (1 year) using FlexibleSearchService
        * Return the List of EcentaNotification entities retrieved
     */
    @Override
    public List<EcentaNotificationModel> findAllNotificationOlderThanSpecificDays(Date oldDate)
    {

        final String query = "SELECT {PK} FROM {EcentaNotification} WHERE {creationtime}>=?oldDate";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("oldDate", oldDate);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery( query.toString() );
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(EcentaNotificationModel.class));
        final SearchResult<EcentaNotificationModel> searchResult = getFlexibleSearchService().search(searchQuery);

        return searchResult.getResult();
    }


    // Task: 6 -> DAO
    /*
        * This method that returns a list of all the EcentaNotifications for a Specific B2BCustomer
    */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(B2BCustomerModel b2BCustomerModel) {

        final String queryToGetAllEcentaNotificationsForSpecificB2bCustomer =
                "SELECT * FROM {" + EcentaNotificationModel._TYPECODE + "} " +
                "WHERE {" + EcentaNotificationModel.B2BCUSTOMER + "} = { " +
                b2BCustomerModel.getOriginalUid() + " }";

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery( queryToGetAllEcentaNotificationsForSpecificB2bCustomer.toString() );
        final SearchResult<EcentaNotificationModel> searchResult = getFlexibleSearchService().search(searchQuery);

        if(!(null == searchResult.getResult()))
            return searchResult.getResult();
        else
            return Collections.emptyList();
    }


    @Override
    public List<B2BCustomerModel> findB2BCustomerUsingUID(final String b2bCustomerUid)
    {
        final String queryToGetB2bCustomer = "SELECT {PK} FROM {B2BCustomer} WHERE {uid}=?uid";
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid",b2bCustomerUid);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery( queryToGetB2bCustomer );
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(B2BCustomerModel.class));
        final SearchResult<B2BCustomerModel> searchResult = getFlexibleSearchService().search(searchQuery);

        if(!(null == searchResult.getResult()))
            return searchResult.getResult();
        else
            return Collections.emptyList();
    }


    private List<EcentaNotificationModel> getEcentaNotificationModels(String queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType, Map<String, Object> params) {
        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery( queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType.toString() );
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(EcentaNotificationModel.class));
        final SearchResult<EcentaNotificationModel> searchResult = getFlexibleSearchService().search(searchQuery);

        if(!(null == searchResult.getResult())) {
            return searchResult.getResult();
        }
        else {
            return Collections.emptyList();
        }
    }

    /*
        * This method that returns a list of all the EcentaNotifications for a Specific B2BCustomer
        * Also specific Type of the EcentaNotification
     */
    private List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificType(B2BCustomerModel b2BCustomerModel, String ecentaNotificationType) {

        List <EcentaNotificationModel> allNotificationsOfPassedB2BCustomer = findAllNotificationForSpecificB2bCustomer( b2BCustomerModel );         //todo: to be cleaned later

        final String queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType =
                "SELECT * FROM {" + EcentaNotificationModel._TYPECODE + "} " +
                "WHERE {" + EcentaNotificationModel.B2BCUSTOMER + "} = ?b2BCustomerModel AND {" +
                EcentaNotificationModel.TYPE + "} = ?typeSendThroughParams";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("typeSendThroughParams", ecentaNotificationType);
        params.put("b2BCustomerModel", b2BCustomerModel);

        return getEcentaNotificationModels(queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType, params);
    }

    /*
        * This method that returns a list of all the EcentaNotifications for a Specific B2BCustomer
        * Also specific Priority of the EcentaNotification
     */
    private List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificPriority(B2BCustomerModel b2BCustomerModel, String ecentaNotificationPriority) {
        this.b2BCustomerModel = b2BCustomerModel;
        this.ecentaNotificationPriority = ecentaNotificationPriority;

        final String queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndPriority =
                "SELECT * FROM {" + EcentaNotificationModel._TYPECODE + "} " +
                        "WHERE {" + EcentaNotificationModel.B2BCUSTOMER + "} = { " +
                        b2BCustomerModel.getOriginalUid() + " } AND {" +
                        EcentaNotificationModel.PRIORITY + "} = ?typeSendThroughParams";

        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("typeSendThroughParams", ecentaNotificationPriority);

        return getEcentaNotificationModels(queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndPriority, params);
    }

    /*  Method returning all the Notification for a B2BCustomer having Notifications marked as High Priority */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForHighPriority(B2BCustomerModel b2BCustomerModel)
    {
        List<EcentaNotificationModel> fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificPriority(b2BCustomerModel, "High");

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }

    /*  Method returning all the Notification for a B2BCustomer having Notifications Type OrderManagement */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForTypeOrderManagement(B2BCustomerModel b2BCustomerModel)
    {
        List<EcentaNotificationModel> fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomerModel, "OrderManagement");

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }

    /*  Method returning all the Notification for a B2BCustomer having Notifications Type News */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForTypeNews(B2BCustomerModel b2BCustomerModel)
    {
        List<EcentaNotificationModel> fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomerModel, "News");

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }

    /*  Method returning all the Notification for a B2BCustomer having Notifications Type ServiceTickets */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForTypeServiceTickets(B2BCustomerModel b2BCustomerModel)
    {
        List<EcentaNotificationModel> fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomerModel, "ServiceTickets");

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }

    /*  Method returning all the Notification for a B2BCustomer having Notifications Type WorkFlow */
    @Override
    public List<EcentaNotificationModel> findAllNotificationForTypeWorkFlow(B2BCustomerModel b2BCustomerModel)
    {
        List<EcentaNotificationModel> fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomerModel, "WorkFlow");

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }

//    /* Method returning all the EcentaNotifications for a B2BCustomer with specific Type/Priority */
    @Override
    public List<EcentaNotificationModel> findAllEcentaNotificationForAB2bCustomerWithSpecificOrPriority(B2BCustomerModel b2BCustomer, String filterForNotification )
    {
        final String PRIORITY_HIGH_NOTIFICATIONS = "High";
        final String TYPE_ORDER_MANAGEMENT_NOTIFICATIONS = "OrderManagement";
        final String TYPE_NEWS_NOTIFICATIONS = "News";
        final String TYPE_SERVICE_TICKETS_NOTIFICATIONS = "ServiceTickets";
        final String TYPE_WORK_FLOW_NOTIFICATIONS = "WorkFlow";
        List<EcentaNotificationModel> fetchedNotifications;

        switch (filterForNotification)
        {
            case PRIORITY_HIGH_NOTIFICATIONS: {
                fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificPriority(b2BCustomer, PRIORITY_HIGH_NOTIFICATIONS);
                break;
            }
            case TYPE_ORDER_MANAGEMENT_NOTIFICATIONS: {
                fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomer, TYPE_ORDER_MANAGEMENT_NOTIFICATIONS);
                break;
            }
            case TYPE_NEWS_NOTIFICATIONS:{
                fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomer, TYPE_NEWS_NOTIFICATIONS);
                break;
            }
            case TYPE_SERVICE_TICKETS_NOTIFICATIONS:{
                fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomer, TYPE_SERVICE_TICKETS_NOTIFICATIONS);
                break;
            }
            case TYPE_WORK_FLOW_NOTIFICATIONS:{
                fetchedNotifications = findAllNotificationForSpecificB2bCustomerAndSpecificType(b2BCustomer, TYPE_WORK_FLOW_NOTIFICATIONS);
                break;
            }
            default:{
                fetchedNotifications = Collections.emptyList();
                break;
            }
        }

        if( !(null == fetchedNotifications) )
            return fetchedNotifications;
        else
            return Collections.emptyList();
    }


    /*
        * Getters and Setters
     */
    public FlexibleSearchService getFlexibleSearchService(){
        return flexibleSearchService;
    }
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService){
        this.flexibleSearchService = flexibleSearchService;
    }
}