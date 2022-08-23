/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.selectivecartsplitlistaddon.interceptors.beforecontroller;

import de.hybris.platform.acceleratorstorefrontcommons.interceptors.BeforeControllerHandler;
import de.hybris.platform.selectivecartfacades.strategies.SelectiveCartUpdateStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.method.HandlerMethod;


/**
 * Intercepts incoming requests to check if the current site will require authentication or not. The site can be secured
 * through the HMC by accessing the list of web sites and modifying the attribute requiresAuthentication.
 */
public class CartPageBeforeControllerHandler implements BeforeControllerHandler
{
	private SelectiveCartUpdateStrategy selectiveCartUpdateStrategy;



	@Override
	public boolean beforeController(final HttpServletRequest request, final HttpServletResponse response,
			final HandlerMethod handler) throws Exception
	{
		final String url = request.getRequestURI();
		if (url.contains("/cart") || url.endsWith("/electronics/en/"))
		{
			getSelectiveCartUpdateStrategy().update();
		}
		return true;
	}

	protected SelectiveCartUpdateStrategy getSelectiveCartUpdateStrategy()
	{
		return selectiveCartUpdateStrategy;
	}

	@Required
	public void setSelectiveCartUpdateStrategy(final SelectiveCartUpdateStrategy selectiveCartUpdateStrategy)
	{
		this.selectiveCartUpdateStrategy = selectiveCartUpdateStrategy;
	}
}