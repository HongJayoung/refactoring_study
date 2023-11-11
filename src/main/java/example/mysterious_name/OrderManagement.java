package example.mysterious_name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderManagement {

    private Set<String> usernames = new HashSet<>();
    private Set<String> orderNumbers = new HashSet<>();

    private void refactorOrder(List<UserOrder> userOrders) {
        for (UserOrder userOrder : userOrders) {
            usernames.add(userOrder.getUsername());
            orderNumbers.add(userOrder.getOrderNumber());
        }
    }

    public Set<String> getUsernames() {
        return usernames;
    }

    public Set<String> getOrderNumbers() {
        return orderNumbers;
    }

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.refactorOrder(getUserOrderFromAPI());
        orderManagement.getUsernames().forEach(System.out::println);
        orderManagement.getOrderNumbers().forEach(System.out::println);
    }

    private static List<UserOrder> getUserOrderFromAPI() {
        ArrayList<UserOrder> userOrders = new ArrayList<>();
        userOrders.add(new UserOrder("A", "A001"));
        userOrders.add(new UserOrder("B", "B001"));
        userOrders.add(new UserOrder("C", "C001"));
        return userOrders;
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserOrder {
    private String username;
    private String orderNumber;
}