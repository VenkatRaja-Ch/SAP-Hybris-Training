package org.training.core.dao.impl;

/*
    * Implementation of the CustomEcentaNotificationDAO class
*/

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.b2b.model.B2BCustomerModel;

import org.training.core.model.EcentaNotificationModel;
import org.training.core.dao.CustomEcentaNotificationsDAO;

import java.util.*;

public class CustomEcentaNotificationsDAOImpl implements CustomEcentaNotificationsDAO{

    private FlexibleSearchService flexibleSearchService;

    final String B2BCUSTOMER_THROUGH_PARAMS = "b2BCustomerSentThroughParams";
    final String B2BCUSTOMER_SEARCH_QUERY = "SELECT {PK} FROM {B2BCustomer} WHERE {uid}=?uid";
    final String ECENTA_NOTIFICATION_FOR_B2BCUSTOMER_SEARCH_QUERY =
            "SELECT {" + EcentaNotificationModel.PK + "} " +
            "FROM   {" + EcentaNotificationModel._TYPECODE + "} " +
            "WHERE  {" + EcentaNotificationModel.B2BCUSTOMER + "} = ?" +
            B2BCUSTOMER_THROUGH_PARAMS;

    // Task: 4 -> CronJobs
    // Return the EcentaNotification Entities which are older than 365 days (1 year) using the FlexibleSearchService
    @Override
    public List<EcentaNotificationModel> findAllNotificationOlderThanSpecificDays(Date oldDate)
    {

        final String query = "SELECT {PK} FROM {EcentaNotification} WHERE {creationtime}>=?oldDate";
        final Map<String, Object> params = new HashMap<>();
        params.put("oldDate", oldDate);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(EcentaNotificationModel.class));
        final SearchResult<EcentaNotificationModel> searchResult = getFlexibleSearchService().search(searchQuery);

        return searchResult.getResult();
    }

    // Task: 6 -> DAO
    // Fetching B2BCustomer using UID
    @Override
    public List<B2BCustomerModel> findB2BCustomerUsingUID(final String b2bCustomerUid)
    {
        final Map<String, Object> params = new HashMap<>();
        params.put("uid",b2bCustomerUid);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery( B2BCUSTOMER_SEARCH_QUERY );
        searchQuery.addQueryParameters(params);
        searchQuery.setResultClassList(Collections.singletonList(B2BCustomerModel.class));
        final SearchResult<B2BCustomerModel> searchResult = getFlexibleSearchService().search(searchQuery);

        if(!(null == searchResult.getResult()))
            return searchResult.getResult();
        else
            return Collections.emptyList();
    }

    // Fetching EcentaNotifications
    private List<EcentaNotificationModel> getEcentaNotification(String passedQuery, Map<String, Object> params) {

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(passedQuery);
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

    // Filtering the query to fetch EcentaNotification for a specific B2BCustomer.
    @Override
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomer(B2BCustomerModel b2BCustomerModel) {

        final Map<String, Object> params = new HashMap<>();
        params.put(B2BCUSTOMER_THROUGH_PARAMS, b2BCustomerModel);

        return getEcentaNotification(ECENTA_NOTIFICATION_FOR_B2BCUSTOMER_SEARCH_QUERY,params);
    }

    // Filtering the query to fetch EcentaNotification for Specific B2BCustomer with Specific Type
    @Override
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificType(B2BCustomerModel b2BCustomerModel, String ecentaNotificationType) {

        final String queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType =
                ECENTA_NOTIFICATION_FOR_B2BCUSTOMER_SEARCH_QUERY +
                " AND {" + EcentaNotificationModel.TYPE + "}" +
                " = ?typeSentThroughParams";

        final Map<String, Object> params = new HashMap<>();
        params.put(B2BCUSTOMER_THROUGH_PARAMS, b2BCustomerModel);
        params.put("typeSentThroughParams", ecentaNotificationType);

        return getEcentaNotification(queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndType, params);
    }

    // Filtering the query to fetch EcentaNotification for Specific B2BCustomer with Specific Priority
    @Override
    public List<EcentaNotificationModel> findAllNotificationForSpecificB2bCustomerAndSpecificPriority(B2BCustomerModel b2BCustomerModel, String ecentaNotificationPriority) {

        final String queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndPriority =
                ECENTA_NOTIFICATION_FOR_B2BCUSTOMER_SEARCH_QUERY +
                " AND {" + EcentaNotificationModel.PRIORITY + "}" +
                " = ?prioritySentThroughParams";

        final Map<String, Object> params = new HashMap<>();
        params.put(B2BCUSTOMER_THROUGH_PARAMS, b2BCustomerModel);
        params.put("prioritySentThroughParams", ecentaNotificationPriority);

        return getEcentaNotification(queryToGetAllEcentaNotificationsForSpecificB2bCustomerAndPriority, params);
    }

    // GETTERS & SETTERS
    public FlexibleSearchService getFlexibleSearchService(){
        return flexibleSearchService;
    }
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService){
        this.flexibleSearchService = flexibleSearchService;
    }
}