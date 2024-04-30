package com.moglix.constants;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {

	public static final String VENDOR_PLANT_MAPPING_EXISTS = "Vendor plant mapping already exists.";
	public static final String VENDOR_PLANT_MAPPING_CREATED = "Vendor plant mapping created successfully.";
	public static final String EMPLOYEE_EXISTS = "Employee already exists.";
	public static final String EMPLOYEE_CREATED = "Employee created successfully.";
	public static final String VENDOR_EXISTS = "Vendor already exists.";
	public static final String VENDOR_CREATED = "Vendor created successfully.";
	
	// Exception Codes
	public static final String NO_CONNECTION = "500";
	
	// Exception Messages
	public static final String NO_CONNECTION_MSG = "Error in connecting to the server.";
	
	// Exception Messages
	public static final String NO_CONNECTION_DATA = "";
	
	//RabbitMQ
    public static final boolean REQUEUE = false;
    public static final int PREFETCH_COUNT = 5;
    public static final int DELIVERY_MODE = 2;
	
    public static final String TOPIC_AGREEMENT_CREATE = "Agreement.Create";
    public static final String TOPIC_SCHEDULE_CREATE = "Schedule.Create";
    public static final String TOPIC_GRN_CREATE = "Grn.Create";
    public static final String TOPIC_PAYMENT_CREATE = "Payment.Create";
    public static final String TOPIC_ORGANISATION_STRUCTURE_CREATE = "Organisation.Create";
    public static final String TOPIC_EMPLOYEE_CREATE = "Employee.Create";
    public static final String TOPIC_VENDOR_CREATE = "Vendor.Create";
    public static final String TOPIC_VPM_CREATE = "Vpm.Create";
    public static final String TOPIC_TRIPOUT_CREATE = "Trip.Create";
    public static final String TOPIC_TRIPOUT_DELETE = "Trip.Delete";
    public static final String TOPIC_MATERIAL_CREATE= "Material.Create";
    public static final String TOPIC_MATERIAL_DELETE= "Material.Delete";
    public static final String TOPIC_STORAGE_LOCATION_CREATE= "SL.Create";
    public static final String TOPIC_VENDOR_RATING_CREATE= "VR.Create";
    public static final String TOPIC_RFQ_MATERIAL_CREATE= "RfqMaterial.Create";
    public static final String TOPIC_RFQ_MATERIAL_DELETE= "RfqMaterial.Delete";

    public static final String QUEUE_AGREEMENT_CREATE = "Agreement-Create-Queue";
    public static final String QUEUE_SCHEDULE_CREATE = "Schedule-Create-Queue";
    public static final String QUEUE_GRN_CREATE = "Grn-Create-Queue";
    public static final String QUEUE_PAYMENT_CREATE = "Payment-Create-Queue";
    public static final String QUEUE_ORGANISATION_STRUCTURE_CREATE = "Organisation-Create-Queue";
    public static final String QUEUE_EMPLOYEE_CREATE = "Employee-Create-Queue";
    public static final String QUEUE_VENDOR_CREATE = "Vendor-Create-Queue";
    public static final String QUEUE_VPM_CREATE = "Vpm-Create-Queue";
    public static final String QUEUE_TRIPOUT_CREATE = "Trip-Create-Queue";
    public static final String QUEUE_TRIPOUT_DELETE = "Trip-Delete-Queue";
    public static final String QUEUE_MATERIAL_CREATE= "Material-Create-Queue";
    public static final String QUEUE_MATERIAL_DELETE= "Material-Create-Queue";
    public static final String QUEUE_STORAGE_LOCATION_CREATE= "SL-Create-Queue";
    public static final String QUEUE_VENDOR_RATING_CREATE= "VR-Create-Queue";
    public static final String QUEUE_RFQMATERIAL_CREATE= "RfqMaterial-Create-Queue";

    public static final String TAG_AGREEMENT_CREATE = "Tag-Agreement-Create";
    public static final String TAG_SCHEDULE_CREATE = "Tag-Schedule-Create";
    public static final String TAG_GRN_CREATE = "Tag-Grn-Create";
    public static final String TAG_PAYMENT_CREATE = "Tag-Payment-Create";
    public static final String TAG_ORGANISATION_STRUCTURE_CREATE = "Tag-Organisation-Create";
    public static final String TAG_EMPLOYEE_CREATE = "Tag-Employee-Create";
    public static final String TAG_VENDOR_CREATE = "Tag-Vendor-Create";
    public static final String TAG_VPM_CREATE = "Tag-Vpm-Create";
    public static final String TAG_TRIPOUT_CREATE = "Tag-Trip-Create";
    public static final String TAG_TRIPOUT_DELETE = "Tag-Trip-Delete";
    public static final String TAG_MATERIAL_CREATE = "Tag-Material-Create";
    public static final String TAG_MATERIAL_DELETE = "Tag-Material-DELETE";
    public static final String TAG_STORAGE_LOCATION_CREATE= "Tag-SL-Create";
    public static final String TAG_VENDOR_RATING_CREATE= "Tag-VR-Create";
    public static final String TAG_RFQMATERIAL_CREATE= "Tag-RfqMaterial-Create";

    // Data Types For Transaction Status Management
    public static final String DATA_TYPE_AGREEMENT = "agreement";
    public static final String DATA_TYPE_SCHEDULE = "schedule";
    public static final String DATA_TYPE_GRN = "grn";
    public static final String DATA_TYPE_PAYMENT = "payment";
    public static final String DATA_TYPE_ORGANISATION_STRUCTURE = "organisation";
    public static final String DATA_TYPE_VENDOR = "vendor";
    public static final String DATA_TYPE_EMPLOYEE = "employee";
    public static final String DATA_TYPE_VPM = "vpm";
    public static final String DATA_TYPE_TRIP = "tripout";
    public static final String DATA_TYPE_MATERIAL = "material";
    public static final String DATA_TYPE_STORAGE_LOCATION = "sl";
    public static final String DATA_TYPE_RFQMATERIAL= "rfqMaterial";

    // Statuses For Transaction Status Management
    public static final String STATUS_NEW = "new";
    public static final String STATUS_FAILED = "failed";
    public static final String STATUS_PASSED = "passed";
    
    // Empty string For Transaction Status Management
    public static final String NA = "";
    
}
