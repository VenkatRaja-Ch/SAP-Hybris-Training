package org.training.traininginitialdata.decorator;

import de.hybris.platform.util.CSVCellDecorator;
import java.util.Map;

public class EcentaNotificationDecorator implements CSVCellDecorator {

    @Override
    public String decorate(final int position, final Map<Integer, String> impexSrcLine) {

        final String csvCell = impexSrcLine.get(position);
        final String suffixMessage = "Sample Data";

        if(csvCell == null || csvCell.length() == 0){

            return csvCell;
        }
        else{

            return (csvCell + suffixMessage);
        }
    }
}
