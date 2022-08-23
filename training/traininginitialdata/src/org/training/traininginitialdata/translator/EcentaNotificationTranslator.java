package org.training.traininginitialdata.translator;

import de.hybris.platform.impex.jalo.translators.AbstractValueTranslator;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloInvalidParameterException;
import org.apache.commons.lang.StringUtils;
import org.training.core.jalo.EcentaNotification;
import java.util.Objects;

public class EcentaNotificationTranslator extends AbstractValueTranslator {

    // import value method
    @Override
    public Object importValue(final String valueExp  , final Item toItem) throws JaloInvalidParameterException {

        clearStatus();
        final int messageLength = (((EcentaNotification)toItem).getMessage()).length();

        if(valueExp.isEmpty()){

            //returns entire message if message length is less than 100 else returns first 100 characters of the message
            return (messageLength <= 100) ? (((EcentaNotification)toItem).getMessage()) : (((EcentaNotification)toItem).getMessage()).substring(0,100);
        } else {

            return valueExp;    // returns the title
        }
    }

    // export value method
    @Override
    public String exportValue(Object value) throws JaloInvalidParameterException {
        return value == null ? "" : value.toString();
    }
}
