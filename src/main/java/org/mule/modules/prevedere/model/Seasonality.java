/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.model;

public enum Seasonality {

	Default(-1),
	
	SeasonallyAdjusted(0),

    NotSeasonallyAdjusted(1),

    SeasonallyAdjustedAnnualRate(2),

    SmoothedSeasonallyAdjusted(3),

    NotApplicable(4);
    
    private int value;    

    private Seasonality(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
