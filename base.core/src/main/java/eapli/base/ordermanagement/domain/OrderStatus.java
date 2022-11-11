package eapli.base.ordermanagement.domain;

public enum OrderStatus {
    REGISTERED,
    PAYMENTPENDING,
    PAID,
    TOBEPREPARED,
    PREPARED,
    DISPATCHED,
    BEINGDELIVERED,
    DELIVEREDBYCARRIER,
    DELIVERED
}
