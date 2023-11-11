package example.mysterious_name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

public class OrderManagement {

    private final Set<String> usernames = new HashSet<>();
    private final Set<String> orderNumbers = new HashSet<>();

    /**
     * 주어진 사용자 주문 리스트에 대해 각 사용자 이름과 주문번호를 읽어온다.
     */
    private void loadOrders() {
        UserInfos data = getUserInfosFromAPI();

        List<UserInfo> userInfos = data.getUserInfos();
        for (UserInfo userInfo : userInfos) {
            usernames.add(userInfo.getUsername());
            orderNumbers.add(userInfo.getOrderNumber());
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
        orderManagement.loadOrders();
        orderManagement.getUsernames().forEach(System.out::println);
        orderManagement.getOrderNumbers().forEach(System.out::println);
    }

    private static UserInfos getUserInfosFromAPI() {
        UserInfos userInfos = new UserInfos();
        userInfos.add(new UserInfo("A", "A001"));
        userInfos.add(new UserInfo("B", "B001"));
        userInfos.add(new UserInfo("C", "C001"));
        return userInfos;
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserInfos {
    private List<UserInfo> userInfos = new LinkedList<>();

    public void add(UserInfo userInfo) {
        this.userInfos.add(userInfo);
    }
}

@Getter
@NoArgsConstructor
@AllArgsConstructor
class UserInfo {
    private String username;
    private String orderNumber;
}