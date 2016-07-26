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
