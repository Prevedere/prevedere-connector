/**
 *     PREVEDERE CONFIDENTIAL
 *
 *     2012 Prevedere Incorporated
 *     All Rights Reserved.
 *
 *     NOTICE:  All information contained herein is, and remains the property of Prevedere Incorporated and its suppliers, if any.  The intellectual and technical concepts contained herein are proprietary to Prevedere Incorporated and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from Prevedere Incorporated.
 */
package org.mule.modules.prevedere.model;

import java.util.Date;
import java.util.UUID;

import com.google.gson.annotations.SerializedName;

public class User {
	
	@SerializedName("Id")
	public UUID id;

	@SerializedName("Username")
	public String username;

	@SerializedName("FirstName")
	public String firstName;

	@SerializedName("LastName")
	public String lastName;

	@SerializedName("Created")
    public Date created;

	@SerializedName("Modified")
	public Date modified;

	@SerializedName("LastLogin")
	public Date lastLogin;

	@SerializedName("IsLocked")
	public boolean isLocked;
}
