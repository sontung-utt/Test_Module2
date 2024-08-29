package view;

import input.Input;
import manager.UserInfoManager;
import model.UserInfo;
import readwritedata.ReadWriteData;
import validate.Validate;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MainMenu {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    UserInfoManager userInfoManager = new UserInfoManager();
    public void showMainMenu() {
        int choice;
        do {
            System.out.println("===== CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ =====");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát chương trình");
            System.out.print("Nhập lựa chọn: ");
            choice = Input.inputInteger();
            switch (choice){
                case 1:
                    showAll();
                    break;
                case 2:
                    showMenuAdd();
                    break;
                case 3:
                    showMenuEdit();
                    break;
                case 4:
                    showMenuRemove();
                    break;
                case 5:
                    searchUser();
                    break;
                case 6:
                    readFromFile();
                    break;
                case 7:
                    writeToFile();
                    break;
                case 8:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Không có lựa chọn phù hợp!");
                    break;
            }
        } while (choice != 8);
    }

    private void showMenuAdd() {
        System.out.println("===== THÊM MỚI =====");
        String name = Validate.validateName();
        System.out.print("Nhập giới tính: ");
        String gender = Input.inputString();
        String phone = Validate.validatePhone();
        System.out.print("Nhập địa chỉ: ");
        String address = Input.inputString();
        LocalDate birth = null;
        while (birth == null){
            System.out.print("Nhập ngày sinh: ");
            String date = Input.inputString();
            try{
                birth = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e){
                System.out.println("Ngày không hợp lệ! Yêu cầu nhập lại ngày theo định dạng dd/MM/yyyy");
            }
        }
        String email = Validate.validateEmail();
        System.out.print("Nhập facebook: ");
        String facebook = Input.inputString();
        UserInfo userInfo = new UserInfo(name,gender,phone,address,birth,email,facebook);
        userInfoManager.add(userInfo);
        System.out.println("Thêm mới thành công!");
    }

    private void showMenuEdit() {
        System.out.println("===== CẬP NHẬT =====");
        System.out.print("Nhập số điện thoại muốn sửa: ");
        String phone = Input.inputString();
        int idEdit = userInfoManager.findIndexByPhone(phone);
        if (idEdit != -1) {
            String name = Validate.validateName();
            System.out.print("Nhập giới tính: ");
            String gender = Input.inputString();
            System.out.print("Nhập địa chỉ: ");
            String address = Input.inputString();
            LocalDate birth = null;
            while (birth == null) {
                System.out.print("Nhập ngày sinh: ");
                String date = Input.inputString();
                try {
                    birth = LocalDate.parse(date, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Ngày không hợp lệ! Yêu cầu nhập lại ngày theo định dạng dd/MM/yyyy");
                }
            }
            String email = Validate.validateEmail();
            System.out.print("Nhập facebook: ");
            String facebook = Input.inputString();
            UserInfo userInfo = new UserInfo(name, gender, phone, address, birth, email, facebook);
            userInfoManager.edit(phone, userInfo);
            System.out.println("Sửa thông tin thành công!");
        } else {
            System.out.println("Không tìm thấy người dùng!");
        }
    }

    private void showMenuRemove() {
        System.out.println("===== XÓA =====");
        System.out.print("Nhập số điện thoại muốn xóa: ");
        String phone = Input.inputString();
        int idRemove = userInfoManager.findIndexByPhone(phone);
        if (idRemove!=-1){
            System.out.println("Bạn có chắc chắn muốn xóa không? (Y/N):");
            String confirmation = Input.inputString();
            if (confirmation.equalsIgnoreCase("Y")) {
                userInfoManager.remove(phone);
                System.out.println("Danh bạ đã được xóa.");
            } else {
                System.out.println("Xóa danh bạ đã bị hủy.");
            }

        } else {
            System.out.println("Không tìm thấy người dùng!");
        }
    }

    private void showAll() {
        System.out.println("===== DANH SÁCH =====");
        List<UserInfo> contacts = userInfoManager.getAll();
        int pageSize = 5;
        int totalPages = (int) Math.ceil((double) contacts.size() / pageSize);
        int currentPage = 0;

        while (currentPage < totalPages) {
            int start = currentPage * pageSize;
            int end = Math.min(start + pageSize, contacts.size());
            System.out.println("Danh sách số điện thoại (Trang " + (currentPage + 1) + " / " + totalPages + "):");
            for (int i = start; i < end; i++) {
                System.out.println(contacts.get(i));
            }
            currentPage++;
            if (currentPage < totalPages) {
                System.out.println("Nhấn Enter để xem trang tiếp theo hoặc nhập '0' để quay lại menu.");
                String input = Input.inputString();
                if ("0".equals(input)) {
                    break;
                }
            }
        }
    }

    private void searchUser() {
        System.out.println("===== TÌM KIẾM =====");
        System.out.println("1. Tìm kiếm theo tên");
        System.out.println("2. Tìm kiếm theo số điện thoại");
        System.out.print("Nhập lựa chọn: ");
        int id = Input.inputInteger();

        if(id == 1){
            System.out.println("Tìm kiếm theo tên");
            System.out.print("Nhập tên: ");
            String name = Input.inputString();
            List<UserInfo> list = userInfoManager.searchUserByName(name);
            if (list.isEmpty()){
                System.out.println("Không có tên " + name);
            } else {
                System.out.println("Danh sách");
                for(UserInfo user: list){
                    System.out.println(user);
                }
            }
        } else if (id == 2){
            System.out.println("Tìm kiếm theo số điện thoại");
            System.out.print("Nhập số điện thoại: ");
            String phone = Input.inputString();
            List<UserInfo> list = userInfoManager.searchUserByPhone(phone);
            if (list.isEmpty()){
                System.out.println("Không có số " + phone);
            } else {
                System.out.println("Danh sách");
                for(UserInfo user: list){
                    System.out.println(user);
                }
            }
        } else {
            System.out.println("Không có lựa chọn phù hợp!");
        }
    }

    public void readFromFile() {
        ReadWriteData readAndWrite = new ReadWriteData();
        System.out.println("Bạn có chắc chắn muốn tải dữ liệu từ file? (Y/N): ");
        String confirm = Input.inputString();
        if (confirm.equalsIgnoreCase("Y")) {
            List<UserInfo> list = readAndWrite.readUserInfo();
            userInfoManager.clearAllContacts();
            userInfoManager.addAllContacts(list);
            System.out.println("Dữ liệu đã được tải từ file thành công.");
        } else {
            System.out.println("Hủy bỏ thao tác.");
        }
    }

    public void writeToFile() {
        ReadWriteData readAndWrite = new ReadWriteData();
        System.out.println("Bạn có chắc chắn muốn lưu dữ liệu vào file? (Y/N): ");
        String confirm = Input.inputString();
        if (confirm.equalsIgnoreCase("Y")) {
            List<UserInfo> list = userInfoManager.getAll();
            readAndWrite.writeUserInfo(list);
            System.out.println("Dữ liệu đã được lưu vào file thành công.");
        } else {
            System.out.println("Hủy bỏ thao tác.");
        }
    }
}
