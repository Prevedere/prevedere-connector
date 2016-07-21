package org.mule.modules.prevedere.model;

public enum ProviderAcces {

	Unknown(0),
	
	Inaccessible(1),
	        
	Accessible(2);
	
	private int value;    

    private ProviderAcces(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
}
