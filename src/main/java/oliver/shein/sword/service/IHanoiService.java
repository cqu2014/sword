package oliver.shein.sword.service;

/**
 * @Author Oliver Wang
 * @Description
 * @Created by IntelliJ IDEA 2018.
 * @Date Create at 2018/10/17 10:13
 */
public interface IHanoiService {
    void move(int n, char start, char temp, char target);
    void hanoi(String lock,int no);
}
