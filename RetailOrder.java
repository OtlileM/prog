public class RetailOrder extends Order {
    public RetailOrder(String orderId, String customerName, double orderTotal, OrderStatus status) throws InvalidOrderException {
        super(orderId, customerName, orderTotal, status);
        if (orderTotal <= 0 || customerName == null || customerName.trim().isEmpty()) {
            throw new InvalidOrderException("Invalid order. Order total must be greater than 0 and customer name cannot be empty. Please try again.");
        }
    }
    @Override
    public double calculateLoyaltyPoints() {
        return Math.floor(getOrderTotal() / 100);
    }
    public void printOrderDetails() {
        System.out.printf("ORDER ID: %s CUSTOMER: %s TOTAL: R%.2f STATUS: %s PTS: %.0f\n",
                getOrderId(), getCustomerName(), getOrderTotal(), getStatus(), calculateLoyaltyPoints());
    }
}