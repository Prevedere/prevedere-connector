package org.mule.modules.prevedere.model;

public enum ForecastModelType {
	Rocet(1),

    Multivariate(2),

    SegmentedMultivariate(3);
    
	private int value;    

    private ForecastModelType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
