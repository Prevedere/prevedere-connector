/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.model;

public enum Frequency {
	Default(-1),
	
	Daily(365),
	        
    Weekly(52),
    
    BiWeekly(26),
    
    Monthly(12),
    
    Quarterly(4),
    
    SemiAnnual(2),
    
    Annual(1),

    Unknown(0);
	
	private int value;    

    private Frequency(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
