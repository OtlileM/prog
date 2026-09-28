public abstract class Order {
    private String orderId;
    private String customerName;
    private double orderTotal;
    private OrderStatus status;

    public Order(String orderId, String customerName, double orderTotal, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderTotal = orderTotal;
        this.status = status;
    }
    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public double getOrderTotal() { return orderTotal; }
    public OrderStatus getStatus() { return status; }

    public abstract double calculateLoyaltyPoints();
}
