import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Pro12973 {

    /**
     * 어차피 두개씩 뛰어내야 하는 거니깐, 스택으로 충분히 풀이가 가능하다고 본다.
     * 스택에 넣고 같으면 빼고, 다시 같으면 보고 반복하면 쉬울듯?
     */

    public int solution(String s)
    {

        if(s.length() == 1) {
            return 0;
        }

        int answer = 1;

        Stack<Character> stack = new Stack<>();

        char[] arr = s.toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if(stack.isEmpty()) {
                stack.add(arr[i]);
            } else {
                if (stack.peek() == arr[i]) {
                    stack.pop();
                } else {
                    stack.add(arr[i]);
                }
            }

        }

        if(stack.size() > 0) {
            answer = 0;
        }

        return answer;
    }

    @Test
    public void caseOne() {
        Assert.assertEquals(solution("baabaa"), 1);
    }

    @Test
    public void caseTwo() {
        Assert.assertEquals(solution("cdcd"), 0);
    }

    @Test
    public void caseThr() {
        Assert.assertEquals(solution("abcccba"), 0);
    }

}
