package etc.simulation;

import java.util.*;

// 오픈 채팅방
public class Programmers_42888 {
    public static void main(String[] args) {
        new Programmers_42888().solution(
                new String[]{"Enter uid1234 Muzi",
                        "Enter uid4567 Prodo",
                        "Leave uid1234",
                        "Enter uid1234 Prodo",
                        "Change uid4567 Ryan"}
        );
    }
    public String[] solution(String[] record) {
        ChatManager manager = new ChatManager();

        for (int i = 0; i < record.length; i++) {
            StringTokenizer stk = new StringTokenizer(record[i], " ");

            System.out.println("i = " + i);
            String command = stk.nextToken();
            String id = stk.nextToken();
            String name = "";
            if (!command.equals("Leave")) {
                name = stk.nextToken();
            }
            User user = manager.userMap.get(id);

            if ( user == null) {
                user = new User(name);
                manager.userMap.put(id, user);
            } else if (command.equals("Enter")) {
                user.name = name;
            }

            switch (command) {
                case "Enter":
                    Client client = new Client();
                    client.user = user;
                    manager.commandMap.put(client, "님이 들어왔습니다.");
                    break;
                case "Leave":
                    Client client2 = new Client();
                    client2.user = user;
                    manager.commandMap.put(client2, "님이 나갔습니다.");
                    break;
                case "Change":
                    user.name = name;
                    break;
            }
        }

        ArrayList<String> list = new ArrayList<>();
        Iterator<Map.Entry<Client, String>> iterator = manager.commandMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Client,String> entry = iterator.next();
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getKey().user.name);
            sb.append(entry.getValue());
            list.add(sb.toString());
        }
        return list.toArray(new String[list.size()]);
    }

    class ChatManager{
        HashMap<String, User> userMap = new HashMap<>();
        LinkedHashMap<Client, String> commandMap = new LinkedHashMap<>();
    }
    class Client{
        User user;
    }
    class User{
        String name;
        User(String name) {
            this.name = name;
        }
    }
}
