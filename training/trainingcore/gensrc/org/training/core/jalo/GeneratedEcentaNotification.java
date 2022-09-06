/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 6 Sep 2022, 16:10:02                        ---
 * ----------------------------------------------------------------
 */
package org.training.core.jalo;

import de.hybris.platform.b2b.jalo.B2BCustomer;
import de.hybris.platform.constants.CoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.jalo.order.Order;
import de.hybris.platform.jalo.type.CollectionType;
import de.hybris.platform.util.OneToManyHandler;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem EcentaNotification}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedEcentaNotification extends GenericItem
{
	/** Qualifier of the <code>EcentaNotification.id</code> attribute **/
	public static final String ID = "id";
	/** Qualifier of the <code>EcentaNotification.b2bCustomer</code> attribute **/
	public static final String B2BCUSTOMER = "b2bCustomer";
	/** Qualifier of the <code>EcentaNotification.date</code> attribute **/
	public static final String DATE = "date";
	/** Qualifier of the <code>EcentaNotification.type</code> attribute **/
	public static final String TYPE = "type";
	/** Qualifier of the <code>EcentaNotification.message</code> attribute **/
	public static final String MESSAGE = "message";
	/** Qualifier of the <code>EcentaNotification.priority</code> attribute **/
	public static final String PRIORITY = "priority";
	/** Qualifier of the <code>EcentaNotification.read</code> attribute **/
	public static final String READ = "read";
	/** Qualifier of the <code>EcentaNotification.deleted</code> attribute **/
	public static final String DELETED = "deleted";
	/** Qualifier of the <code>EcentaNotification.title</code> attribute **/
	public static final String TITLE = "title";
	/** Qualifier of the <code>EcentaNotification.order</code> attribute **/
	public static final String ORDER = "order";
	/**
	* {@link OneToManyHandler} for handling 1:n ORDER's relation attributes from 'many' side.
	**/
	protected static final OneToManyHandler<Order> ORDERHANDLER = new OneToManyHandler<Order>(
	CoreConstants.TC.ORDER,
	false,
	"ecentaNotification",
	null,
	false,
	true,
	CollectionType.COLLECTION
	);
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(ID, AttributeMode.INITIAL);
		tmp.put(B2BCUSTOMER, AttributeMode.INITIAL);
		tmp.put(DATE, AttributeMode.INITIAL);
		tmp.put(TYPE, AttributeMode.INITIAL);
		tmp.put(MESSAGE, AttributeMode.INITIAL);
		tmp.put(PRIORITY, AttributeMode.INITIAL);
		tmp.put(READ, AttributeMode.INITIAL);
		tmp.put(DELETED, AttributeMode.INITIAL);
		tmp.put(TITLE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.b2bCustomer</code> attribute.
	 * @return the b2bCustomer - Notification's linkage to b2bCustomer's Id
	 */
	public B2BCustomer getB2bCustomer(final SessionContext ctx)
	{
		return (B2BCustomer)getProperty( ctx, B2BCUSTOMER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.b2bCustomer</code> attribute.
	 * @return the b2bCustomer - Notification's linkage to b2bCustomer's Id
	 */
	public B2BCustomer getB2bCustomer()
	{
		return getB2bCustomer( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.b2bCustomer</code> attribute. 
	 * @param value the b2bCustomer - Notification's linkage to b2bCustomer's Id
	 */
	public void setB2bCustomer(final SessionContext ctx, final B2BCustomer value)
	{
		setProperty(ctx, B2BCUSTOMER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.b2bCustomer</code> attribute. 
	 * @param value the b2bCustomer - Notification's linkage to b2bCustomer's Id
	 */
	public void setB2bCustomer(final B2BCustomer value)
	{
		setB2bCustomer( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.date</code> attribute.
	 * @return the date - Date of the notification
	 */
	public Date getDate(final SessionContext ctx)
	{
		return (Date)getProperty( ctx, DATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.date</code> attribute.
	 * @return the date - Date of the notification
	 */
	public Date getDate()
	{
		return getDate( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.date</code> attribute. 
	 * @param value the date - Date of the notification
	 */
	public void setDate(final SessionContext ctx, final Date value)
	{
		setProperty(ctx, DATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.date</code> attribute. 
	 * @param value the date - Date of the notification
	 */
	public void setDate(final Date value)
	{
		setDate( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.deleted</code> attribute.
	 * @return the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public Boolean isDeleted(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, DELETED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.deleted</code> attribute.
	 * @return the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public Boolean isDeleted()
	{
		return isDeleted( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @return the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public boolean isDeletedAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isDeleted( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @return the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public boolean isDeletedAsPrimitive()
	{
		return isDeletedAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @param value the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public void setDeleted(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, DELETED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @param value the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public void setDeleted(final Boolean value)
	{
		setDeleted( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @param value the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public void setDeleted(final SessionContext ctx, final boolean value)
	{
		setDeleted( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.deleted</code> attribute. 
	 * @param value the deleted - Boolean variable indicating whether the notification has been deleted or not
	 */
	public void setDeleted(final boolean value)
	{
		setDeleted( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.id</code> attribute.
	 * @return the id - Unique Id for each notification
	 */
	public String getId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, ID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.id</code> attribute.
	 * @return the id - Unique Id for each notification
	 */
	public String getId()
	{
		return getId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.id</code> attribute. 
	 * @param value the id - Unique Id for each notification
	 */
	public void setId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, ID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.id</code> attribute. 
	 * @param value the id - Unique Id for each notification
	 */
	public void setId(final String value)
	{
		setId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.message</code> attribute.
	 * @return the message - Message property for the messages in the notification
	 */
	public String getMessage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.message</code> attribute.
	 * @return the message - Message property for the messages in the notification
	 */
	public String getMessage()
	{
		return getMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.message</code> attribute. 
	 * @param value the message - Message property for the messages in the notification
	 */
	public void setMessage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.message</code> attribute. 
	 * @param value the message - Message property for the messages in the notification
	 */
	public void setMessage(final String value)
	{
		setMessage( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.order</code> attribute.
	 * @return the order - Order
	 */
	public Collection<Order> getOrder(final SessionContext ctx)
	{
		return ORDERHANDLER.getValues( ctx, this );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.order</code> attribute.
	 * @return the order - Order
	 */
	public Collection<Order> getOrder()
	{
		return getOrder( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.order</code> attribute. 
	 * @param value the order - Order
	 */
	public void setOrder(final SessionContext ctx, final Collection<Order> value)
	{
		ORDERHANDLER.setValues( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.order</code> attribute. 
	 * @param value the order - Order
	 */
	public void setOrder(final Collection<Order> value)
	{
		setOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to order. 
	 * @param value the item to add to order - Order
	 */
	public void addToOrder(final SessionContext ctx, final Order value)
	{
		ORDERHANDLER.addValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to order. 
	 * @param value the item to add to order - Order
	 */
	public void addToOrder(final Order value)
	{
		addToOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from order. 
	 * @param value the item to remove from order - Order
	 */
	public void removeFromOrder(final SessionContext ctx, final Order value)
	{
		ORDERHANDLER.removeValue( ctx, this, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from order. 
	 * @param value the item to remove from order - Order
	 */
	public void removeFromOrder(final Order value)
	{
		removeFromOrder( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.priority</code> attribute.
	 * @return the priority - Enum Notification priority attribute for EcentaNotification
	 */
	public EnumerationValue getPriority(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, PRIORITY);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.priority</code> attribute.
	 * @return the priority - Enum Notification priority attribute for EcentaNotification
	 */
	public EnumerationValue getPriority()
	{
		return getPriority( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.priority</code> attribute. 
	 * @param value the priority - Enum Notification priority attribute for EcentaNotification
	 */
	public void setPriority(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, PRIORITY,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.priority</code> attribute. 
	 * @param value the priority - Enum Notification priority attribute for EcentaNotification
	 */
	public void setPriority(final EnumerationValue value)
	{
		setPriority( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.read</code> attribute.
	 * @return the read - Boolean variable indicating whether the notification has been read or not
	 */
	public Boolean isRead(final SessionContext ctx)
	{
		return (Boolean)getProperty( ctx, READ);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.read</code> attribute.
	 * @return the read - Boolean variable indicating whether the notification has been read or not
	 */
	public Boolean isRead()
	{
		return isRead( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.read</code> attribute. 
	 * @return the read - Boolean variable indicating whether the notification has been read or not
	 */
	public boolean isReadAsPrimitive(final SessionContext ctx)
	{
		Boolean value = isRead( ctx );
		return value != null ? value.booleanValue() : false;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.read</code> attribute. 
	 * @return the read - Boolean variable indicating whether the notification has been read or not
	 */
	public boolean isReadAsPrimitive()
	{
		return isReadAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.read</code> attribute. 
	 * @param value the read - Boolean variable indicating whether the notification has been read or not
	 */
	public void setRead(final SessionContext ctx, final Boolean value)
	{
		setProperty(ctx, READ,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.read</code> attribute. 
	 * @param value the read - Boolean variable indicating whether the notification has been read or not
	 */
	public void setRead(final Boolean value)
	{
		setRead( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.read</code> attribute. 
	 * @param value the read - Boolean variable indicating whether the notification has been read or not
	 */
	public void setRead(final SessionContext ctx, final boolean value)
	{
		setRead( ctx,Boolean.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.read</code> attribute. 
	 * @param value the read - Boolean variable indicating whether the notification has been read or not
	 */
	public void setRead(final boolean value)
	{
		setRead( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.title</code> attribute.
	 * @return the title - title variable for the title of EcentaNotification
	 */
	public String getTitle(final SessionContext ctx)
	{
		return (String)getProperty( ctx, TITLE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.title</code> attribute.
	 * @return the title - title variable for the title of EcentaNotification
	 */
	public String getTitle()
	{
		return getTitle( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.title</code> attribute. 
	 * @param value the title - title variable for the title of EcentaNotification
	 */
	public void setTitle(final SessionContext ctx, final String value)
	{
		setProperty(ctx, TITLE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.title</code> attribute. 
	 * @param value the title - title variable for the title of EcentaNotification
	 */
	public void setTitle(final String value)
	{
		setTitle( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.type</code> attribute.
	 * @return the type - Enum Notification type attribute for EcentaNotification
	 */
	public EnumerationValue getType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, TYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotification.type</code> attribute.
	 * @return the type - Enum Notification type attribute for EcentaNotification
	 */
	public EnumerationValue getType()
	{
		return getType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.type</code> attribute. 
	 * @param value the type - Enum Notification type attribute for EcentaNotification
	 */
	public void setType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, TYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotification.type</code> attribute. 
	 * @param value the type - Enum Notification type attribute for EcentaNotification
	 */
	public void setType(final EnumerationValue value)
	{
		setType( getSession().getSessionContext(), value );
	}
	
}
