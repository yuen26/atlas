package org.atlas.framework.event.contract;

public enum EventType {

    // User
    // -----------------------------------------------------------------------------------------------------------------

    CUSTOMER_CREATED,

    // Product
    // -----------------------------------------------------------------------------------------------------------------

    PRODUCT_CREATED,
    PRODUCT_UPDATED,
    PRODUCT_DELETED,

    // Order
    // -----------------------------------------------------------------------------------------------------------------

    // Choreography
    ORDER_CREATED,
    QUANTITY_RESERVED,
    RESERVE_QUANTITY_FAILED,
    CREDIT_RESERVED,
    RESERVE_CREDIT_FAILED,

    // Orchestration
    RESERVE_QUANTITY_REQUEST,
    RESERVE_QUANTITY_REPLY,
    RESERVE_CREDIT_REQUEST,
    RESERVE_CREDIT_REPLY,

    ORDER_CONFIRMED,
}
