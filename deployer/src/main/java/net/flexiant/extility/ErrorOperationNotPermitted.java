
package net.flexiant.extility;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for errorOperationNotPermitted.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="errorOperationNotPermitted">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="MASTER_BILLING_ENTITY_ONLY"/>
 *     &lt;enumeration value="PRODUCT_OFFER_NOT_ALLOWED_FOR_THIS_CUSTOMER"/>
 *     &lt;enumeration value="PRODUCTS_OF_THIS_TYPE_CANNOT_BE_PURCHASED"/>
 *     &lt;enumeration value="UNAUTHORISED_ACCESS"/>
 *     &lt;enumeration value="PRODUCT_NOT_ACTIVE"/>
 *     &lt;enumeration value="PROMOTION_CANNOT_BE_USED"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_BILLING_ENTITIES"/>
 *     &lt;enumeration value="EMAIL_SERVICE_NOT_ALLOWED"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_START_SERVER"/>
 *     &lt;enumeration value="CANNOT_CHANGE_SYSTEM_OR_BILLING_KEYS"/>
 *     &lt;enumeration value="CANNOT_CHANGE_SYSTEM_KEYS"/>
 *     &lt;enumeration value="INVALID_SERVER_STATUS"/>
 *     &lt;enumeration value="NIC_ALREADY_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="DISK_ALREADY_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="DISK_NOT_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="SERVER_NOT_STOPPED"/>
 *     &lt;enumeration value="DISK_CANNOT_BE_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="DISK_AND_SERVER_NOT_IN_SAME_VDC"/>
 *     &lt;enumeration value="NO_ADDITIONAL_DISKS_CAN_BE_ADDED_TO_SERVER"/>
 *     &lt;enumeration value="NIC_NOT_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_DISKS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_STORAGE_SIZE"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_SERVER_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_IPV4_SUBNETS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_NETWORKS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_DISK_PRODUCT_OFFER_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_RAM_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_CPU_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_SUBNETS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_IMAGES_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_SNAPSHOTS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_VDC_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_CREDIT_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_USERS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_LIMIT_ON_SERVER"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_CREATE_DISK"/>
 *     &lt;enumeration value="NOT_AUTHORISED_TO_USE_THIS_IMAGE"/>
 *     &lt;enumeration value="INVALID_SERVER_FOR_CUSTOMER"/>
 *     &lt;enumeration value="INSUFFICIENT_UNITS"/>
 *     &lt;enumeration value="JOB_CANNOT_BE_CANCELLED"/>
 *     &lt;enumeration value="SERVER_IS_IN_RUNNING_STATE"/>
 *     &lt;enumeration value="CANNOT_DELETE_IPV6_SUBNET"/>
 *     &lt;enumeration value="CANNOT_DELETE_SSHKEY"/>
 *     &lt;enumeration value="NETWORK_CANNOT_BE_DELETED"/>
 *     &lt;enumeration value="NETWORK_CANNOT_BE_ADDED"/>
 *     &lt;enumeration value="RESOURCE_BELONGS_TO_A_DIFFERENT_CUSTOMER"/>
 *     &lt;enumeration value="INVALID_CUSTOMER"/>
 *     &lt;enumeration value="REVERT_POSSIBLE_ONLY_FOR_SNAPSHOT"/>
 *     &lt;enumeration value="NEWER_SNAPSHOTS_EXISTS"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_CREATE_SERVER"/>
 *     &lt;enumeration value="INVALID_PRODUCT_OFFER_FOR_CUSTOMER"/>
 *     &lt;enumeration value="PRODUCT_COMPONENT_SET_UP_IS_INCORRECT"/>
 *     &lt;enumeration value="SERVER_VDC_DOES_NOT_MATCH_WITH_NIC"/>
 *     &lt;enumeration value="SSHKEY_IS_NOT_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="SSHKEY_ALREADY_ATTACHED_TO_SERVER"/>
 *     &lt;enumeration value="SERVER_NOT_RUNNING"/>
 *     &lt;enumeration value="SNAPSHOTS_EXISTS_FOR_DISK"/>
 *     &lt;enumeration value="NO_ACCESS_TO_VDC"/>
 *     &lt;enumeration value="NO_ACCESS_TO_NETWORK"/>
 *     &lt;enumeration value="DO_NOT_SUPPORT_THIS_RESOURCE_TYPE"/>
 *     &lt;enumeration value="CANNOT_DELETE_CLUSTER"/>
 *     &lt;enumeration value="NO_ACCESS_TO_SERVER"/>
 *     &lt;enumeration value="NO_ACCESS_TO_RESOURCE"/>
 *     &lt;enumeration value="EXISTING_RESOURCES_FOR_FIREWALL_TEMPLATE"/>
 *     &lt;enumeration value="DISK_CANNOT_BE_DETACHED"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_CREATE_SNAPSHOT"/>
 *     &lt;enumeration value="CANNOT_ATTACH_SUBNET_TO_THIS_NETWORK"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_CLONE_DISK"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_DETACH"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_SNAPSHOT"/>
 *     &lt;enumeration value="INSUFFICIENT_PERMISSION_TO_CREATE_IMAGE"/>
 *     &lt;enumeration value="CANNOT_DELETE_RESOURCE"/>
 *     &lt;enumeration value="PROMOTION_CANNOT_BE_DELETED"/>
 *     &lt;enumeration value="ADMIN_GROUP_CANNOT_BE_EMPTY"/>
 *     &lt;enumeration value="CANNOT_DELETE_CUSTOMER"/>
 *     &lt;enumeration value="SUBNET_HAS_ACTIVE_IPS"/>
 *     &lt;enumeration value="CANNOT_DELETE_PRODUCTOFFER"/>
 *     &lt;enumeration value="VDC_NOT_EMPTY"/>
 *     &lt;enumeration value="CANNOT_MODIFY_OTHER_USER"/>
 *     &lt;enumeration value="INVALID_PRODUCT_TYPE"/>
 *     &lt;enumeration value="VDC_BELONGS_TO_A_DIFFERENT_CLUSTER"/>
 *     &lt;enumeration value="IP_ADDRESS_IN_USE"/>
 *     &lt;enumeration value="CANNOT_CREATE_IPV6_SUBNET"/>
 *     &lt;enumeration value="CANNOT_CREATE_NETWORK"/>
 *     &lt;enumeration value="CAPABILITY_NOT_PRESENT_FOR_RESOURCE"/>
 *     &lt;enumeration value="CANNOT_MOVE_RESOURCE_BETWEEN_CLUSTERS"/>
 *     &lt;enumeration value="CANNOT_MOVE_RESOURCE_BETWEEN_VDCS"/>
 *     &lt;enumeration value="IP_ADDRESS_NOT_IN_USE"/>
 *     &lt;enumeration value="INVALID_DISK_SIZE"/>
 *     &lt;enumeration value="CANNOT_CREATE_IMAGE"/>
 *     &lt;enumeration value="NO_ACCESS_TO_BILLING_ENTITY"/>
 *     &lt;enumeration value="CANNOT_ADD_USER_TO_GROUP"/>
 *     &lt;enumeration value="CANNOT_CREATE_SPECIAL_GROUP"/>
 *     &lt;enumeration value="CANNOT_CHANGE_GROUP_CUSTOMER"/>
 *     &lt;enumeration value="CANNOT_CHANGE_GROUP_TYPE"/>
 *     &lt;enumeration value="CANNOT_DELETE_SPECIAL_GROUP"/>
 *     &lt;enumeration value="SNAPSHOTS_EXIST_FOR_SERVER"/>
 *     &lt;enumeration value="DISKS_EXIST_FOR_SERVER"/>
 *     &lt;enumeration value="NICS_EXIST_FOR_SERVER"/>
 *     &lt;enumeration value="INVALID_GROUP_FOR_CUSTOMER"/>
 *     &lt;enumeration value="PRODUCT_PURCHASES_EXISTS"/>
 *     &lt;enumeration value="CANNOT_SET_PERMISSIONS_ON_USER"/>
 *     &lt;enumeration value="CANNOT_DELETE_USER"/>
 *     &lt;enumeration value="PAYMENT_METHOD_EXISTS_IN_CARD"/>
 *     &lt;enumeration value="SERVER_CONTAINS_SNAPSHOTS"/>
 *     &lt;enumeration value="INVALID_USE_OF_AGGREGATION_FUNCTION"/>
 *     &lt;enumeration value="PAYMENT_METHOD_NOT_ALLOWED"/>
 *     &lt;enumeration value="INVALID_PRODUCT_OFFER_FOR_IMAGE"/>
 *     &lt;enumeration value="CANNOT_LOCK_ONLY_ADMIN_USER"/>
 *     &lt;enumeration value="EXCEEDS_MAX_OUTSTANDING_INVITATIONS_LIMIT"/>
 *     &lt;enumeration value="NOT_ENOUGH_REFERRAL_UNITS_TO_SEND_INVITATIONS"/>
 *     &lt;enumeration value="REFERRAL_PROMOCODE_CANNOT_BE_DELETED"/>
 *     &lt;enumeration value="NO_NETWORK_DEFINED"/>
 *     &lt;enumeration value="PROMOCODE_CANNOT_BE_DELETED"/>
 *     &lt;enumeration value="PROMOCODE_CANNOT_BE_USED"/>
 *     &lt;enumeration value="NO_SERVERS_FOUND"/>
 *     &lt;enumeration value="FAILED_TO_MARSHAL"/>
 *     &lt;enumeration value="CANNOT_DELETE_DEPLOYMENT_TEMPLATE"/>
 *     &lt;enumeration value="EXISTING_RESOURCES_FOR_DEPLOYMENT_INSTANCE"/>
 *     &lt;enumeration value="CANNOT_DELETE_TEMPLATE_INSTANCE"/>
 *     &lt;enumeration value="INSTANCE_MISMATCH_WITH_TEMPLATE"/>
 *     &lt;enumeration value="VNC_HANDLER_NOT_SUPPORTED"/>
 *     &lt;enumeration value="CANNOT_CHANGE_DEPLOYMENT_INSTANCE_UUID"/>
 *     &lt;enumeration value="CANNOT_CREATE_IMAGE_FOR_ISO_DISK"/>
 *     &lt;enumeration value="INVALID_DEPLOYMENT_INSTANCE_STATUS"/>
 *     &lt;enumeration value="INVALID_VALUE_FOR_OUTER_TAG"/>
 *     &lt;enumeration value="EXISTING_BILLING_ENTITY_FOR_CURRENCY"/>
 *     &lt;enumeration value="CANNOT_CREATE_ISO_IMAGE"/>
 *     &lt;enumeration value="CANNOT_IMPORT_IP"/>
 *     &lt;enumeration value="CANNOT_IMPORT_SUBNET"/>
 *     &lt;enumeration value="CANNOT_DELETE_IMPORTED_IP"/>
 *     &lt;enumeration value="CANNOT_DELETE_IMPORTED_SUBNET"/>
 *     &lt;enumeration value="CANNOT_DELETE_PRODUCT"/>
 *     &lt;enumeration value="CANNOT_DELETE_PRODUCT_OFFER"/>
 *     &lt;enumeration value="PRODUCT_OFFER_IS_DELETED"/>
 *     &lt;enumeration value="NETWORK_IS_NOT_SERVICE_NETWORK"/>
 *     &lt;enumeration value="CANNOT_MODIFY_FDL"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_IPv4_ADDRESS_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_IPv6_SUBNET_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_PUBLIC_NETWORK_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_IP_NETWORK_LIMIT"/>
 *     &lt;enumeration value="CANNOT_EXCEED_MAX_PRIVATE_NETWORK_LIMIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "errorOperationNotPermitted")
@XmlEnum
public enum ErrorOperationNotPermitted {

    MASTER_BILLING_ENTITY_ONLY("MASTER_BILLING_ENTITY_ONLY"),
    PRODUCT_OFFER_NOT_ALLOWED_FOR_THIS_CUSTOMER("PRODUCT_OFFER_NOT_ALLOWED_FOR_THIS_CUSTOMER"),
    PRODUCTS_OF_THIS_TYPE_CANNOT_BE_PURCHASED("PRODUCTS_OF_THIS_TYPE_CANNOT_BE_PURCHASED"),
    UNAUTHORISED_ACCESS("UNAUTHORISED_ACCESS"),
    PRODUCT_NOT_ACTIVE("PRODUCT_NOT_ACTIVE"),
    PROMOTION_CANNOT_BE_USED("PROMOTION_CANNOT_BE_USED"),
    CANNOT_EXCEED_MAX_BILLING_ENTITIES("CANNOT_EXCEED_MAX_BILLING_ENTITIES"),
    EMAIL_SERVICE_NOT_ALLOWED("EMAIL_SERVICE_NOT_ALLOWED"),
    INSUFFICIENT_PERMISSION_TO_START_SERVER("INSUFFICIENT_PERMISSION_TO_START_SERVER"),
    CANNOT_CHANGE_SYSTEM_OR_BILLING_KEYS("CANNOT_CHANGE_SYSTEM_OR_BILLING_KEYS"),
    CANNOT_CHANGE_SYSTEM_KEYS("CANNOT_CHANGE_SYSTEM_KEYS"),
    INVALID_SERVER_STATUS("INVALID_SERVER_STATUS"),
    NIC_ALREADY_ATTACHED_TO_SERVER("NIC_ALREADY_ATTACHED_TO_SERVER"),
    DISK_ALREADY_ATTACHED_TO_SERVER("DISK_ALREADY_ATTACHED_TO_SERVER"),
    DISK_NOT_ATTACHED_TO_SERVER("DISK_NOT_ATTACHED_TO_SERVER"),
    SERVER_NOT_STOPPED("SERVER_NOT_STOPPED"),
    DISK_CANNOT_BE_ATTACHED_TO_SERVER("DISK_CANNOT_BE_ATTACHED_TO_SERVER"),
    DISK_AND_SERVER_NOT_IN_SAME_VDC("DISK_AND_SERVER_NOT_IN_SAME_VDC"),
    NO_ADDITIONAL_DISKS_CAN_BE_ADDED_TO_SERVER("NO_ADDITIONAL_DISKS_CAN_BE_ADDED_TO_SERVER"),
    NIC_NOT_ATTACHED_TO_SERVER("NIC_NOT_ATTACHED_TO_SERVER"),
    CANNOT_EXCEED_MAX_DISKS_LIMIT("CANNOT_EXCEED_MAX_DISKS_LIMIT"),
    CANNOT_EXCEED_MAX_STORAGE_SIZE("CANNOT_EXCEED_MAX_STORAGE_SIZE"),
    CANNOT_EXCEED_MAX_SERVER_LIMIT("CANNOT_EXCEED_MAX_SERVER_LIMIT"),
    @XmlEnumValue("CANNOT_EXCEED_MAX_IPV4_SUBNETS_LIMIT")
    CANNOT_EXCEED_MAX_IPV_4_SUBNETS_LIMIT("CANNOT_EXCEED_MAX_IPV4_SUBNETS_LIMIT"),
    CANNOT_EXCEED_MAX_NETWORKS_LIMIT("CANNOT_EXCEED_MAX_NETWORKS_LIMIT"),
    CANNOT_EXCEED_MAX_DISK_PRODUCT_OFFER_LIMIT("CANNOT_EXCEED_MAX_DISK_PRODUCT_OFFER_LIMIT"),
    CANNOT_EXCEED_MAX_RAM_LIMIT("CANNOT_EXCEED_MAX_RAM_LIMIT"),
    CANNOT_EXCEED_MAX_CPU_LIMIT("CANNOT_EXCEED_MAX_CPU_LIMIT"),
    CANNOT_EXCEED_MAX_SUBNETS_LIMIT("CANNOT_EXCEED_MAX_SUBNETS_LIMIT"),
    CANNOT_EXCEED_MAX_IMAGES_LIMIT("CANNOT_EXCEED_MAX_IMAGES_LIMIT"),
    CANNOT_EXCEED_MAX_SNAPSHOTS_LIMIT("CANNOT_EXCEED_MAX_SNAPSHOTS_LIMIT"),
    CANNOT_EXCEED_MAX_VDC_LIMIT("CANNOT_EXCEED_MAX_VDC_LIMIT"),
    CANNOT_EXCEED_MAX_CREDIT_LIMIT("CANNOT_EXCEED_MAX_CREDIT_LIMIT"),
    CANNOT_EXCEED_MAX_USERS_LIMIT("CANNOT_EXCEED_MAX_USERS_LIMIT"),
    CANNOT_EXCEED_MAX_LIMIT_ON_SERVER("CANNOT_EXCEED_MAX_LIMIT_ON_SERVER"),
    INSUFFICIENT_PERMISSION_TO_CREATE_DISK("INSUFFICIENT_PERMISSION_TO_CREATE_DISK"),
    NOT_AUTHORISED_TO_USE_THIS_IMAGE("NOT_AUTHORISED_TO_USE_THIS_IMAGE"),
    INVALID_SERVER_FOR_CUSTOMER("INVALID_SERVER_FOR_CUSTOMER"),
    INSUFFICIENT_UNITS("INSUFFICIENT_UNITS"),
    JOB_CANNOT_BE_CANCELLED("JOB_CANNOT_BE_CANCELLED"),
    SERVER_IS_IN_RUNNING_STATE("SERVER_IS_IN_RUNNING_STATE"),
    @XmlEnumValue("CANNOT_DELETE_IPV6_SUBNET")
    CANNOT_DELETE_IPV_6_SUBNET("CANNOT_DELETE_IPV6_SUBNET"),
    CANNOT_DELETE_SSHKEY("CANNOT_DELETE_SSHKEY"),
    NETWORK_CANNOT_BE_DELETED("NETWORK_CANNOT_BE_DELETED"),
    NETWORK_CANNOT_BE_ADDED("NETWORK_CANNOT_BE_ADDED"),
    RESOURCE_BELONGS_TO_A_DIFFERENT_CUSTOMER("RESOURCE_BELONGS_TO_A_DIFFERENT_CUSTOMER"),
    INVALID_CUSTOMER("INVALID_CUSTOMER"),
    REVERT_POSSIBLE_ONLY_FOR_SNAPSHOT("REVERT_POSSIBLE_ONLY_FOR_SNAPSHOT"),
    NEWER_SNAPSHOTS_EXISTS("NEWER_SNAPSHOTS_EXISTS"),
    INSUFFICIENT_PERMISSION_TO_CREATE_SERVER("INSUFFICIENT_PERMISSION_TO_CREATE_SERVER"),
    INVALID_PRODUCT_OFFER_FOR_CUSTOMER("INVALID_PRODUCT_OFFER_FOR_CUSTOMER"),
    PRODUCT_COMPONENT_SET_UP_IS_INCORRECT("PRODUCT_COMPONENT_SET_UP_IS_INCORRECT"),
    SERVER_VDC_DOES_NOT_MATCH_WITH_NIC("SERVER_VDC_DOES_NOT_MATCH_WITH_NIC"),
    SSHKEY_IS_NOT_ATTACHED_TO_SERVER("SSHKEY_IS_NOT_ATTACHED_TO_SERVER"),
    SSHKEY_ALREADY_ATTACHED_TO_SERVER("SSHKEY_ALREADY_ATTACHED_TO_SERVER"),
    SERVER_NOT_RUNNING("SERVER_NOT_RUNNING"),
    SNAPSHOTS_EXISTS_FOR_DISK("SNAPSHOTS_EXISTS_FOR_DISK"),
    NO_ACCESS_TO_VDC("NO_ACCESS_TO_VDC"),
    NO_ACCESS_TO_NETWORK("NO_ACCESS_TO_NETWORK"),
    DO_NOT_SUPPORT_THIS_RESOURCE_TYPE("DO_NOT_SUPPORT_THIS_RESOURCE_TYPE"),
    CANNOT_DELETE_CLUSTER("CANNOT_DELETE_CLUSTER"),
    NO_ACCESS_TO_SERVER("NO_ACCESS_TO_SERVER"),
    NO_ACCESS_TO_RESOURCE("NO_ACCESS_TO_RESOURCE"),
    EXISTING_RESOURCES_FOR_FIREWALL_TEMPLATE("EXISTING_RESOURCES_FOR_FIREWALL_TEMPLATE"),
    DISK_CANNOT_BE_DETACHED("DISK_CANNOT_BE_DETACHED"),
    INSUFFICIENT_PERMISSION_TO_CREATE_SNAPSHOT("INSUFFICIENT_PERMISSION_TO_CREATE_SNAPSHOT"),
    CANNOT_ATTACH_SUBNET_TO_THIS_NETWORK("CANNOT_ATTACH_SUBNET_TO_THIS_NETWORK"),
    INSUFFICIENT_PERMISSION_TO_CLONE_DISK("INSUFFICIENT_PERMISSION_TO_CLONE_DISK"),
    INSUFFICIENT_PERMISSION_TO_DETACH("INSUFFICIENT_PERMISSION_TO_DETACH"),
    INSUFFICIENT_PERMISSION_TO_SNAPSHOT("INSUFFICIENT_PERMISSION_TO_SNAPSHOT"),
    INSUFFICIENT_PERMISSION_TO_CREATE_IMAGE("INSUFFICIENT_PERMISSION_TO_CREATE_IMAGE"),
    CANNOT_DELETE_RESOURCE("CANNOT_DELETE_RESOURCE"),
    PROMOTION_CANNOT_BE_DELETED("PROMOTION_CANNOT_BE_DELETED"),
    ADMIN_GROUP_CANNOT_BE_EMPTY("ADMIN_GROUP_CANNOT_BE_EMPTY"),
    CANNOT_DELETE_CUSTOMER("CANNOT_DELETE_CUSTOMER"),
    SUBNET_HAS_ACTIVE_IPS("SUBNET_HAS_ACTIVE_IPS"),
    CANNOT_DELETE_PRODUCTOFFER("CANNOT_DELETE_PRODUCTOFFER"),
    VDC_NOT_EMPTY("VDC_NOT_EMPTY"),
    CANNOT_MODIFY_OTHER_USER("CANNOT_MODIFY_OTHER_USER"),
    INVALID_PRODUCT_TYPE("INVALID_PRODUCT_TYPE"),
    VDC_BELONGS_TO_A_DIFFERENT_CLUSTER("VDC_BELONGS_TO_A_DIFFERENT_CLUSTER"),
    IP_ADDRESS_IN_USE("IP_ADDRESS_IN_USE"),
    @XmlEnumValue("CANNOT_CREATE_IPV6_SUBNET")
    CANNOT_CREATE_IPV_6_SUBNET("CANNOT_CREATE_IPV6_SUBNET"),
    CANNOT_CREATE_NETWORK("CANNOT_CREATE_NETWORK"),
    CAPABILITY_NOT_PRESENT_FOR_RESOURCE("CAPABILITY_NOT_PRESENT_FOR_RESOURCE"),
    CANNOT_MOVE_RESOURCE_BETWEEN_CLUSTERS("CANNOT_MOVE_RESOURCE_BETWEEN_CLUSTERS"),
    CANNOT_MOVE_RESOURCE_BETWEEN_VDCS("CANNOT_MOVE_RESOURCE_BETWEEN_VDCS"),
    IP_ADDRESS_NOT_IN_USE("IP_ADDRESS_NOT_IN_USE"),
    INVALID_DISK_SIZE("INVALID_DISK_SIZE"),
    CANNOT_CREATE_IMAGE("CANNOT_CREATE_IMAGE"),
    NO_ACCESS_TO_BILLING_ENTITY("NO_ACCESS_TO_BILLING_ENTITY"),
    CANNOT_ADD_USER_TO_GROUP("CANNOT_ADD_USER_TO_GROUP"),
    CANNOT_CREATE_SPECIAL_GROUP("CANNOT_CREATE_SPECIAL_GROUP"),
    CANNOT_CHANGE_GROUP_CUSTOMER("CANNOT_CHANGE_GROUP_CUSTOMER"),
    CANNOT_CHANGE_GROUP_TYPE("CANNOT_CHANGE_GROUP_TYPE"),
    CANNOT_DELETE_SPECIAL_GROUP("CANNOT_DELETE_SPECIAL_GROUP"),
    SNAPSHOTS_EXIST_FOR_SERVER("SNAPSHOTS_EXIST_FOR_SERVER"),
    DISKS_EXIST_FOR_SERVER("DISKS_EXIST_FOR_SERVER"),
    NICS_EXIST_FOR_SERVER("NICS_EXIST_FOR_SERVER"),
    INVALID_GROUP_FOR_CUSTOMER("INVALID_GROUP_FOR_CUSTOMER"),
    PRODUCT_PURCHASES_EXISTS("PRODUCT_PURCHASES_EXISTS"),
    CANNOT_SET_PERMISSIONS_ON_USER("CANNOT_SET_PERMISSIONS_ON_USER"),
    CANNOT_DELETE_USER("CANNOT_DELETE_USER"),
    PAYMENT_METHOD_EXISTS_IN_CARD("PAYMENT_METHOD_EXISTS_IN_CARD"),
    SERVER_CONTAINS_SNAPSHOTS("SERVER_CONTAINS_SNAPSHOTS"),
    INVALID_USE_OF_AGGREGATION_FUNCTION("INVALID_USE_OF_AGGREGATION_FUNCTION"),
    PAYMENT_METHOD_NOT_ALLOWED("PAYMENT_METHOD_NOT_ALLOWED"),
    INVALID_PRODUCT_OFFER_FOR_IMAGE("INVALID_PRODUCT_OFFER_FOR_IMAGE"),
    CANNOT_LOCK_ONLY_ADMIN_USER("CANNOT_LOCK_ONLY_ADMIN_USER"),
    EXCEEDS_MAX_OUTSTANDING_INVITATIONS_LIMIT("EXCEEDS_MAX_OUTSTANDING_INVITATIONS_LIMIT"),
    NOT_ENOUGH_REFERRAL_UNITS_TO_SEND_INVITATIONS("NOT_ENOUGH_REFERRAL_UNITS_TO_SEND_INVITATIONS"),
    REFERRAL_PROMOCODE_CANNOT_BE_DELETED("REFERRAL_PROMOCODE_CANNOT_BE_DELETED"),
    NO_NETWORK_DEFINED("NO_NETWORK_DEFINED"),
    PROMOCODE_CANNOT_BE_DELETED("PROMOCODE_CANNOT_BE_DELETED"),
    PROMOCODE_CANNOT_BE_USED("PROMOCODE_CANNOT_BE_USED"),
    NO_SERVERS_FOUND("NO_SERVERS_FOUND"),
    FAILED_TO_MARSHAL("FAILED_TO_MARSHAL"),
    CANNOT_DELETE_DEPLOYMENT_TEMPLATE("CANNOT_DELETE_DEPLOYMENT_TEMPLATE"),
    EXISTING_RESOURCES_FOR_DEPLOYMENT_INSTANCE("EXISTING_RESOURCES_FOR_DEPLOYMENT_INSTANCE"),
    CANNOT_DELETE_TEMPLATE_INSTANCE("CANNOT_DELETE_TEMPLATE_INSTANCE"),
    INSTANCE_MISMATCH_WITH_TEMPLATE("INSTANCE_MISMATCH_WITH_TEMPLATE"),
    VNC_HANDLER_NOT_SUPPORTED("VNC_HANDLER_NOT_SUPPORTED"),
    CANNOT_CHANGE_DEPLOYMENT_INSTANCE_UUID("CANNOT_CHANGE_DEPLOYMENT_INSTANCE_UUID"),
    CANNOT_CREATE_IMAGE_FOR_ISO_DISK("CANNOT_CREATE_IMAGE_FOR_ISO_DISK"),
    INVALID_DEPLOYMENT_INSTANCE_STATUS("INVALID_DEPLOYMENT_INSTANCE_STATUS"),
    INVALID_VALUE_FOR_OUTER_TAG("INVALID_VALUE_FOR_OUTER_TAG"),
    EXISTING_BILLING_ENTITY_FOR_CURRENCY("EXISTING_BILLING_ENTITY_FOR_CURRENCY"),
    CANNOT_CREATE_ISO_IMAGE("CANNOT_CREATE_ISO_IMAGE"),
    CANNOT_IMPORT_IP("CANNOT_IMPORT_IP"),
    CANNOT_IMPORT_SUBNET("CANNOT_IMPORT_SUBNET"),
    CANNOT_DELETE_IMPORTED_IP("CANNOT_DELETE_IMPORTED_IP"),
    CANNOT_DELETE_IMPORTED_SUBNET("CANNOT_DELETE_IMPORTED_SUBNET"),
    CANNOT_DELETE_PRODUCT("CANNOT_DELETE_PRODUCT"),
    CANNOT_DELETE_PRODUCT_OFFER("CANNOT_DELETE_PRODUCT_OFFER"),
    PRODUCT_OFFER_IS_DELETED("PRODUCT_OFFER_IS_DELETED"),
    NETWORK_IS_NOT_SERVICE_NETWORK("NETWORK_IS_NOT_SERVICE_NETWORK"),
    CANNOT_MODIFY_FDL("CANNOT_MODIFY_FDL"),
    @XmlEnumValue("CANNOT_EXCEED_MAX_IPv4_ADDRESS_LIMIT")
    CANNOT_EXCEED_MAX_I_PV_4_ADDRESS_LIMIT("CANNOT_EXCEED_MAX_IPv4_ADDRESS_LIMIT"),
    @XmlEnumValue("CANNOT_EXCEED_MAX_IPv6_SUBNET_LIMIT")
    CANNOT_EXCEED_MAX_I_PV_6_SUBNET_LIMIT("CANNOT_EXCEED_MAX_IPv6_SUBNET_LIMIT"),
    CANNOT_EXCEED_MAX_PUBLIC_NETWORK_LIMIT("CANNOT_EXCEED_MAX_PUBLIC_NETWORK_LIMIT"),
    CANNOT_EXCEED_MAX_IP_NETWORK_LIMIT("CANNOT_EXCEED_MAX_IP_NETWORK_LIMIT"),
    CANNOT_EXCEED_MAX_PRIVATE_NETWORK_LIMIT("CANNOT_EXCEED_MAX_PRIVATE_NETWORK_LIMIT");
    private final String value;

    ErrorOperationNotPermitted(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorOperationNotPermitted fromValue(String v) {
        for (ErrorOperationNotPermitted c: ErrorOperationNotPermitted.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}