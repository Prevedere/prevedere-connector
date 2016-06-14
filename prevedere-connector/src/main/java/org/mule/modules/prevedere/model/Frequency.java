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
