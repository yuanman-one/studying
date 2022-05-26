public class test {
    //测试可执行任务
    public static void main(String[] args) {
        Runnable[] rs = new Runnable[]{
                () -> System.out.println("1111111111111"),
                () -> System.out.println("222222222222")
        };
        for (Runnable r : rs) {
            try {
                r.run();
            } catch (Exception ignored) {
            }
        }
    }
}
