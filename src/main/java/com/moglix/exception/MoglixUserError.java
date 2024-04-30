/*
 *  @copyright MogliLabs
 */
package com.moglix.exception;

/**
 * 
 * @author Mohit Garg<mohit.garg@moglix.com>
 * @created_on 01-Oct-2015
 */
public enum MoglixUserError {

    UNKOWN_ERROR(500, "Unknown error occurred"),
    DATABASE(0, "A database error has occurred."),
    INVALID_ZIPCODE(1, "Zipcode given is invalid."),
    INVALID_EMAIL_PASSWORD_COMBINATION(200, "Invalid username/password."), 
    USER_SESSION_EXPIRED(300, "User session has expired/invalid. Login again to continue."), 
    UNAUTHORIZED_ACCESS(400, "Unauthorized access."),
    EMAIL_ALREADY_REGISTERED(500, "Email ID/Employee ID already registered."),
    INVALID_USER_ID(600, "Invalid user details."),
	INVALID_NOTIFICATION_ID(700, "Invalid notification details."), 
	COMPANY_ID_IS_MANDATORY(800, "Company ID is mandatory. PLease provide company ID"), 
	INVALID_ENTITY_TYPE(900, "Entity type given is not valid"), 
	ATTACHMENT_NOT_FOUND(1000, "Attachment file not found"),
	INVALID_BRANCH_ID(1100, "Branch Id given is not valid"), 
	INVALID_ROLE_ID(1200, "Invalid role id"),
	INVALID_ADDRESS_ID(1200, "Invalid Address id"),
	MISSING_ADDRESS_ID(1200, "Missing Address id"), 
	MISSING_COMPANY_ID(1200, "Missing Company id"), 
	MISSING_ADDRESS_INFO(1300 , "Missing mandatory Address information"),
	MISSING_REQUIRED_PARAMS(1400, "Required parameters are missing"),
	INVALID_EMAIL_APPLICATION_COMBINATION(1500, "Invalid username/application."),
	INACTIVE_OR_DELETED_USER(1600, "User is inactive or deleted."),
	INVALID_KEY(1700, "Invalid key"),
	KEY_EXPIRED(1800, "Key has been expired"),
	OLD_PASSWORD_MISMATCHED(1900, "Old Password Mismatched"),
	DUPLICATE_TIN_NO(2000, "Plant already exist with same tin no."),
	DUPLICATE_GSTIN(2000, "Plant already exist with same GSTIN"),
	INVALID_MODULE(2100, "Module Name is Invalid");

    private final int code;
    private final String description;

    private MoglixUserError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

}
