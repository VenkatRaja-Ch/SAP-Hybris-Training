/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package de.hybris.platform.selectivecartsplitlistaddon.controllers.misc;

import de.hybris.platform.acceleratorcms.model.components.MiniCartComponentModel;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.AbstractController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.servicelayer.services.CMSComponentService;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.selectivecartfacades.SelectiveCartFacade;
import de.hybris.platform.selectivecartfacades.data.Wishlist2Data;
import de.hybris.platform.selectivecartfacades.data.Wishlist2EntryData;
import de.hybris.platform.selectivecartsplitlistaddon.controllers.SelectivecartsplitlistaddonControllerConstants;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Controller to Override the MiniCart Controller to add wish list quantity into checked list total items add wish list
 * entry into checked list.
 */
@Controller
public class MiniCartControllerForSelectiveCart extends AbstractController
{
	/**
	 * We use this suffix pattern because of an issue with Spring 3.1 where a Uri value is incorrectly extracted if it
	 * contains on or more '.' characters. Please see https://jira.springsource.org/browse/SPR-6164 for a discussion on
	 * the issue and future resolution.
	 */
	private static final String TOTAL_DISPLAY_PATH_VARIABLE_PATTERN = "{totalDisplay:.*}";
	private static final String COMPONENT_UID_PATH_VARIABLE_PATTERN = "{componentUid:.*}";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "cmsComponentService")
	private CMSComponentService cmsComponentService;

	@Resource
	private SelectiveCartFacade selectiveCartFacade;

	@RequestMapping(value = "/cart/miniCart/" + TOTAL_DISPLAY_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String getMiniCart(@PathVariable final String totalDisplay, final Model model)
	{
		final CartData cartData = cartFacade.getMiniCart();
		model.addAttribute("totalPrice", cartData.getTotalPrice());
		model.addAttribute("subTotal", cartData.getSubTotal());
		if (cartData.getDeliveryCost() != null)
		{
			final PriceData withoutDelivery = cartData.getDeliveryCost();
			withoutDelivery.setValue(cartData.getTotalPrice().getValue().subtract(cartData.getDeliveryCost().getValue()));
			model.addAttribute("totalNoDelivery", withoutDelivery);
		}
		else
		{
			model.addAttribute("totalNoDelivery", cartData.getTotalPrice());
		}
		model.addAttribute("totalDisplay", totalDisplay);

		final Integer counts = calculateTotalQuantityNumber();
		model.addAttribute("totalItems", counts);

		return SelectivecartsplitlistaddonControllerConstants.Views.Fragments.Cart.MiniCartPanel;
	}

	@RequestMapping(value = "/cart/rollover/" + COMPONENT_UID_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String rolloverMiniCartPopup(@PathVariable final String componentUid, final Model model) throws CMSItemNotFoundException
	{
		final CartData cartData = cartFacade.getSessionCart();
		model.addAttribute("cartData", cartData);

		final MiniCartComponentModel component = cmsComponentService.getSimpleCMSComponent(componentUid);

		final List entries = cartData.getEntries();
		if (entries != null)
		{
			Collections.reverse(entries);
			model.addAttribute("entries", entries);

			model.addAttribute("numberItemsInCart", Integer.valueOf(entries.size()));
			model.addAttribute("forceEnableCheckout", CollectionUtils.isNotEmpty(cartData.getRootGroups()));
			if (entries.size() < component.getShownProductCount())
			{
				model.addAttribute("numberShowing", Integer.valueOf(entries.size()));
			}
			else
			{
				model.addAttribute("numberShowing", Integer.valueOf(component.getShownProductCount()));
			}
		}
		model.addAttribute("lightboxBannerComponent", component.getLightboxBannerComponent());

		final Integer counts = calculateTotalItemNumber();
		model.addAttribute("numberItemsInCart", counts);

		return SelectivecartsplitlistaddonControllerConstants.Views.Fragments.Cart.CartPopup;
	}

	protected Integer calculateTotalItemNumber()
	{
		final CartData cartData = getCartFacade().getSessionCart();

		final Wishlist2Data wishList = getSelectiveCartFacade().getWishlistForSelectiveCart();
		int counts = 0;
		if (wishList != null && CollectionUtils.isNotEmpty(wishList.getEntries()))
		{
			counts = wishList.getEntries().size();

		}
		final List entries = cartData.getEntries();
		if (entries != null)
		{
			counts = counts + entries.size();
		}
		return Integer.valueOf(counts);
	}

	protected Integer calculateTotalQuantityNumber()
	{
		final CartData cartData = getCartFacade().getSessionCart();

		final Wishlist2Data wishList = getSelectiveCartFacade().getWishlistForSelectiveCart();
		int counts = 0;
		if (wishList != null)
		{
			for (final Wishlist2EntryData entry : wishList.getEntries())
			{
				counts += entry.getQuantity().intValue();
			}
		}
		counts = counts + cartData.getTotalUnitCount().intValue();
		return Integer.valueOf(counts);
	}



	protected SelectiveCartFacade getSelectiveCartFacade()
	{
		return selectiveCartFacade;
	}

	protected CartFacade getCartFacade()
	{
		return cartFacade;
	}

	protected CMSComponentService getCmsComponentService()
	{
		return cmsComponentService;
	}
}
