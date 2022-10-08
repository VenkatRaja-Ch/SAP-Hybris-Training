/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Oct 6, 2022, 2:58:04 PM                     ---
 * ----------------------------------------------------------------
 */
package org.training.core.jalo;

import de.hybris.platform.catalog.jalo.CatalogVersion;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem ServiceProduct}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedServiceProduct extends GenericItem
{
	/** Qualifier of the <code>ServiceProduct.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>ServiceProduct.catalogVersion</code> attribute **/
	public static final String CATALOGVERSION = "catalogVersion";
	/** Qualifier of the <code>ServiceProduct.name</code> attribute **/
	public static final String NAME = "name";
	/** Qualifier of the <code>ServiceProduct.creationDate</code> attribute **/
	public static final String CREATIONDATE = "creationDate";
	/** Qualifier of the <code>ServiceProduct.description</code> attribute **/
	public static final String DESCRIPTION = "description";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(CATALOGVERSION, AttributeMode.INITIAL);
		tmp.put(NAME, AttributeMode.INITIAL);
		tmp.put(CREATIONDATE, AttributeMode.INITIAL);
		tmp.put(DESCRIPTION, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.catalogVersion</code> attribute.
	 * @return the catalogVersion - Catalog Version of Service Product
	 */
	public CatalogVersion getCatalogVersion(final SessionContext ctx)
	{
		return (CatalogVersion)getProperty( ctx, CATALOGVERSION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.catalogVersion</code> attribute.
	 * @return the catalogVersion - Catalog Version of Service Product
	 */
	public CatalogVersion getCatalogVersion()
	{
		return getCatalogVersion( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.catalogVersion</code> attribute. 
	 * @param value the catalogVersion - Catalog Version of Service Product
	 */
	public void setCatalogVersion(final SessionContext ctx, final CatalogVersion value)
	{
		setProperty(ctx, CATALOGVERSION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.catalogVersion</code> attribute. 
	 * @param value the catalogVersion - Catalog Version of Service Product
	 */
	public void setCatalogVersion(final CatalogVersion value)
	{
		setCatalogVersion( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.creationDate</code> attribute.
	 * @return the creationDate - Date of the Service Product
	 */
	public Date getCreationDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, CREATIONDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.creationDate</code> attribute.
	 * @return the creationDate - Date of the Service Product
	 */
	public Date getCreationDate()
	{
		return getCreationDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.creationDate</code> attribute. 
	 * @param value the creationDate - Date of the Service Product
	 */
	public void setCreationDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, CREATIONDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.creationDate</code> attribute. 
	 * @param value the creationDate - Date of the Service Product
	 */
	public void setCreationDate(final Date value)
	{
		setCreationDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.description</code> attribute.
	 * @return the description - Description of the Service Product
	 */
	public String getDescription(final SessionContext ctx)
	{
		return (String)getProperty( ctx, DESCRIPTION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.description</code> attribute.
	 * @return the description - Description of the Service Product
	 */
	public String getDescription()
	{
		return getDescription( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.description</code> attribute. 
	 * @param value the description - Description of the Service Product
	 */
	public void setDescription(final SessionContext ctx, final String value)
	{
		setProperty(ctx, DESCRIPTION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.description</code> attribute. 
	 * @param value the description - Description of the Service Product
	 */
	public void setDescription(final String value)
	{
		setDescription( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.id</code> attribute.
	 * @return the id - Unique identifier for ServiceProduct Catalog Aware item
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.id</code> attribute.
	 * @return the id - Unique identifier for ServiceProduct Catalog Aware item
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.id</code> attribute. 
	 * @param value the id - Unique identifier for ServiceProduct Catalog Aware item
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.id</code> attribute. 
	 * @param value the id - Unique identifier for ServiceProduct Catalog Aware item
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.name</code> attribute.
	 * @return the name - Name of the Service Product
	 */
	public String getName(final SessionContext ctx)
	{
		return (String)getProperty( ctx, NAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>ServiceProduct.name</code> attribute.
	 * @return the name - Name of the Service Product
	 */
	public String getName()
	{
		return getName( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.name</code> attribute. 
	 * @param value the name - Name of the Service Product
	 */
	public void setName(final SessionContext ctx, final String value)
	{
		setProperty(ctx, NAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>ServiceProduct.name</code> attribute. 
	 * @param value the name - Name of the Service Product
	 */
	public void setName(final String value)
	{
		setName( getSession().getSessionContext(), value );
	}
	
}
