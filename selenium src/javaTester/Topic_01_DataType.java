package javaTester;

public class Topic_01_DataType {
//    java có 2 kiểu dữ liệu
//    1. kiểu dữ liêệu nguyên thủy (primitive type)
//        có 8 kiểu, chia ra làm 4 nhóm
//        - kiểu kí tự (đại diện cho 1 kí tự)
        char c = 'B'; //kiểu ký tự trong nháy đơn, nhưng chuooix string trong nháy kép ""
//        - kiểu số nguyên
        byte bNumber = 100;
        short sNumber = -250;
        int iNumber = 50000;
        long lNumber = 10000000;
//        - kiểu số thực
        float fNumber = 10.5f;
        double dNumber = -15.3459d;
//        - kiểu logic
        boolean sex = true;

//    2. kiểu dữ liệu tham chiếu: còn lại thuộc kiêu dữ liệu này
//        - kiểu mảng (Array)
//        + mảng kiểu string: chứa được nhiều record cùng 1 kiểu dữ liệu
        String[] studentName = {"Nguyễn Văn A", "Bùi Thị Thanh Hằng"};
        int[] studentAge = {11, 22};

//        - kiểu object (chuyển đổi qua các kiểu dữ liệu khác)
        Object studentAdress ="123 ABC street"; //có thể dùng string
        Object employeeAge = 33;        //có thể dùng int
        Object employeeSex = false;     //có thể dùng boolean

//        - kiểu chuỗi (string): chỉ chứa được 1 record
        String name = "Hằng";
        String employeeNumber = "01010";

//        - kểu class
//        - kiểu interface: web driver
//        - kiểu collection: là phần khung để tìm element



        int x; //biến thuộc phạm vi global

        public static void main(String[] args) {
                int x = 99;
                int y = x;
                System.out.println("x = " + x);
                System.out.println("y = " + y);

                x = 55;
                System.out.println("x = " + x);
                System.out.println("y = " + y);
        // cách này giá trị của y không biến đổi theo x

                //kiểu class
                // instance 1
                Topic_01_DataType a = new Topic_01_DataType();
                a.x = 11;
                System.out.println(a.x);

                // instance 2
                Topic_01_DataType b = a;
                System.out.println(b.x);

                a.x = 33;
                System.out.println(a.x);
                System.out.println(b.x);
        // giá trị b biến đổi theo a

                // instance 3
                Topic_01_DataType c = new Topic_01_DataType();
                System.out.println(c.x); //khi chưa gán giá trị c.x bằng bao nhiêu thì in ra lấy giá trị mặc định = 0
        }

}
