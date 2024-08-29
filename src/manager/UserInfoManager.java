package manager;

import model.UserInfo;
import readwritedata.ReadWriteData;

import java.util.Collections;
import java.util.List;

public class UserInfoManager {
    List<UserInfo> infoList;
    ReadWriteData readWriteData = new ReadWriteData();
    public UserInfoManager(){
        this.infoList = readWriteData.readUserInfo();
    }

    public void add(UserInfo userInfo) {
        infoList.add(userInfo);
        //readWriteData.writeUserInfo(infoList);
    }


    public void edit(String phone, UserInfo userInfo) {
        int index = findIndexByPhone(phone);
        infoList.set(index,userInfo);
    }


    public void remove(String phone) {
        int index = findIndexByPhone(phone);
        infoList.remove(index);
    }


    public int findIndexById(int id) {
        for (int i = 0; i < infoList.size(); i++) {
            if (infoList.get(i).getId()==id){
                return i;
            }
        }
        return -1;
    }


    public List<UserInfo> getAll() {
        //this.infoList = readWriteData.readUserInfo();
        return this.infoList;
    }

    public UserInfo getUserInfoById(int id) {
        for (UserInfo user : infoList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }

    public UserInfo findUserByPhone(String phone){
        for (UserInfo user : infoList){
            if (user.getPhone().equals(phone));
            return user;
        }
        return null;
    }

    public int findIndexByPhone(String phone){
        for (int i = 0; i < infoList.size(); i++) {
            if (infoList.get(i).getPhone().equals(phone)){
                return i;
            }
        }
        return -1;
    }

    public List<UserInfo> searchUserByPhone(String phone) {
        List<UserInfo> list = readWriteData.readUserInfo();
        for (UserInfo userInfo : infoList){
            if(userInfo.getPhone().contains(phone)){
                list.add(userInfo);
            }
        }
        return list;
    }

    public List<UserInfo> searchUserByName(String name) {
        List<UserInfo> list = readWriteData.readUserInfo();
        for (UserInfo userInfo : infoList){
            if(userInfo.getName().toLowerCase().contains(name.toLowerCase())){
                list.add(userInfo);
            }
        }
        return list;
    }
    public void clearAllContacts() {
        this.infoList.clear();
    }

    public void addAllContacts(List<UserInfo> users) {
        this.infoList.addAll(users);
        readWriteData.writeUserInfo(users);
    }
}
