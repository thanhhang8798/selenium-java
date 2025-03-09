package tips;

public class Topic_04_ProjectPath {
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");
        String projectPath = System.getProperty("user.dir");
        String uploadFilePath = projectPath + "\\uploadFiles\\"; // dùng \ chỉ chạy được trên window. không chạy được trên MAC
        String screenshot = "chup man hinh.png";
        String koreaImg = "ảnh hàn quốc.jpg";

        System.out.println(osName);
        System.out.println(javaVersion);
        System.out.println(projectPath);
        System.out.println(uploadFilePath);
        System.out.println(uploadFilePath + screenshot);
        System.out.println(uploadFilePath + koreaImg);
    }
}
