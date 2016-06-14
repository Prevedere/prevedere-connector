package org.mule.modules.prevedere.model;

public enum Calculation {
	Default(-1),
	
	None(1),
	        
    PeriodOverPeriod(2),
    
    YearOverYear(3),
    
    ThreePeriodMoving(4),
    
    FivePeriodMoving(5),
    
    ThreePeriodYearOverYear(6);
    
    private int value;    

    private Calculation(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
