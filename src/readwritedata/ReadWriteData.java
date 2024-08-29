package readwritedata;

import model.UserInfo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteData {
    File file = new File("data/contacts.csv");
    public void writeUserInfo(List<UserInfo> userInfoList) {
        StringBuilder data = new StringBuilder();
        for (UserInfo user : userInfoList){
            data.append(user.getId()).append(",").append(user.getName()).append(",").append(user.getGender()).append(",").append(user.getPhone()).append(",").append(user.getAddress()).append(",").append(user.getBirth()).append(",").append(user.getEmail()).append(",").append(user.getFacebook()).append("\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data.toString());
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<UserInfo> readUserInfo() {
        List<UserInfo> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine())!=null) {
                String[] data = line.split(",");
                if(data.length==8){
                    UserInfo userInfo = new UserInfo(Integer.parseInt(data[0]),data[1], data[2], data[3], data[4], LocalDate.parse(data[5]), data[6], data[7]);
                    list.add(userInfo);
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
