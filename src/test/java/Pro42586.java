import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Pro42586 {

    /**
     * 각 배열에는 작업 진도가 적혀있고, speeds 가 적혀있으므로
     * 1 초 마다 스피드가 추가될 것이고 그때마다 100이 된 아이들이 있으면 추출한다.
     * 무조건 앞선 작업이 완료되야 가능하므로, 작업이 끝난 시간을 기록하고, 앞선 작업시간보다 작다면 같이 배포하는 식으로 한다.
     */

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> timeStamp = new ArrayList();
        Queue<Integer> workReportQueue = new LinkedList<>();
        int[] doneTime = new int[progresses.length];
        int time = 1;

        writeDonTimeReport(progresses, speeds, time, doneTime);


        int addCount = 0;

//        forLoopSolution(doneTime, addCount, timeStamp);

        queueSolution(doneTime, workReportQueue, timeStamp);

        int[] answer = new int[timeStamp.size()];

        for(int i = 0; i < timeStamp.size(); i++) {
            answer[i] = timeStamp.get(i);
        }

        return answer;
    }

    public void queueSolution(int[] doneTime, Queue<Integer> workReportQueue, List<Integer> timeStamp) {


        for(int i = 0; i < doneTime.length; i++) {
            workReportQueue.add(doneTime[i]);
        }


        while (!workReportQueue.isEmpty()) {

            int beforeWorkTime = workReportQueue.poll();
            int deployCount = 1;

            while (!workReportQueue.isEmpty()) {
                if(workReportQueue.peek() <= beforeWorkTime) {
                    deployCount++;
                    beforeWorkTime = Math.max(workReportQueue.poll(), beforeWorkTime);
                }else {
                    break;
                }
            }

            timeStamp.add(deployCount);
        }

    }

    public void forLoopSolution(int[] doneTime, int addCount, List<Integer> timeStamp) {
        for(int i = 0; i < doneTime.length; i++) {
            int minDeployTime = doneTime[i];
            int deployCountPerTime = 1;
            for(int j = i+1; j < doneTime.length; j++) {
                if(minDeployTime >= doneTime[j]) {
                    deployCountPerTime++;
                }
                if(minDeployTime < doneTime[j]) {
                    i = j-1;
                    break;
                }
            }

            addCount += deployCountPerTime;

            if(addCount > doneTime.length) {
                break;
            }

            timeStamp.add(deployCountPerTime);

        }
    }

    public void writeDonTimeReport(int[] progresses, int[] speeds, int time, int[] doneTime) {

        while (true) {

            int doneCount = 0;

            for(int i = 0; i < progresses.length; i++) {
                progresses[i] += speeds[i];
            }

            for(int i = 0; i < progresses.length; i++) {
                if(progresses[i] >= 100 && doneTime[i] == 0) {
                    doneTime[i] = time;
                }
                if(progresses[i] >= 100) {
                    doneCount++;
                }
            }

            if(doneCount == progresses.length) {
                break;
            }

            time = time + 1;

        }
    }

    @Test
    public void caseOne() {
        Assert.assertArrayEquals(solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}), new int[]{2,1});
    }

    @Test
    public void caseTwo() {
        Assert.assertArrayEquals(solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1}), new int[]{1, 3, 2});
    }

    @Test
    public void caseThr() {
        Assert.assertArrayEquals(solution(new int[]{2, 2, 1, 2}, new int[]{1, 1, 1, 1}), new int[]{2, 2});
    }

    @Test
    public void caseFour() {
        Assert.assertArrayEquals(solution(new int[]{40, 93, 30, 55, 60, 65}, new int[]{60, 1, 30, 5 , 10, 7}), new int[]{1,2,3});
    }


}
