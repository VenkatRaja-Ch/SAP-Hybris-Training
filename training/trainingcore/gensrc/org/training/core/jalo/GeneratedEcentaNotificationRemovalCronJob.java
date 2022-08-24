/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 24 Aug 2022, 16:16:49                       ---
 * ----------------------------------------------------------------
 */
package org.training.core.jalo;

import de.hybris.platform.cronjob.jalo.CronJob;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.training.core.constants.TrainingCoreConstants;

/**
 * Generated class for type {@link de.hybris.platform.cronjob.jalo.CronJob EcentaNotificationRemovalCronJob}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedEcentaNotificationRemovalCronJob extends CronJob
{
	/** Qualifier of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute **/
	public static final String XDAYSOLD = "xDaysOld";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(CronJob.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(XDAYSOLD, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute.
	 * @return the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public Integer getXDaysOld(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, XDAYSOLD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute.
	 * @return the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public Integer getXDaysOld()
	{
		return getXDaysOld( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @return the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public int getXDaysOldAsPrimitive(final SessionContext ctx)
	{
		Integer value = getXDaysOld( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @return the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public int getXDaysOldAsPrimitive()
	{
		return getXDaysOldAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @param value the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public void setXDaysOld(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, XDAYSOLD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @param value the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public void setXDaysOld(final Integer value)
	{
		setXDaysOld( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @param value the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public void setXDaysOld(final SessionContext ctx, final int value)
	{
		setXDaysOld( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>EcentaNotificationRemovalCronJob.xDaysOld</code> attribute. 
	 * @param value the xDaysOld - All EcentaNotification Object is older than 1 year will be marked as read and deleted.
	 */
	public void setXDaysOld(final int value)
	{
		setXDaysOld( getSession().getSessionContext(), value );
	}
	
}
