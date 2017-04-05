package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;

public class LambdaWeightedScheduler {
	
	public static void main(String[] args){
		
        List<JobWithPriority> jobList = new ArrayList<>();
        jobList.add(new JobWithPriority(1, 1, 10, 2));
        jobList.add(new JobWithPriority(2, 2, 8, 4));
        jobList.add(new JobWithPriority(3, 3, 4, 6));
        jobList.add(new JobWithPriority(4, 2, 3, 3));
        jobList.add(new JobWithPriority(5, 9, 15, 9));
        jobList.add(new JobWithPriority(6, 5, 9, 12));
        jobList.add(new JobWithPriority(7, 8, 12, 3));
        jobList.add(new JobWithPriority(8, 4, 17, 7));
        jobList.add(new JobWithPriority(9, 10, 12, 9));
        jobList.add(new JobWithPriority(10, 7, 8, 4));
        jobList.add(new JobWithPriority(11, 8, 11, 6));

        Comparator comparator = new ComparatorJobByFinishTime();
        Collections.sort(jobList, comparator);
//        System.out.println(ComputeOpt(jobList.size(), jobList));
//        System.out.println(ComputeP(jobList.size(), jobList));
        FindSolution(jobList.size(), jobList);
	}
	
	public static int ComputeP(int j, List<JobWithPriority> jobList) {
		int i = j-1, p = 0;
		while(i >= 1) {
			JobWithPriority jobJ = jobList.get(j-1);
            JobWithPriority jobI = jobList.get(i-1);
            if (jobJ.getStartTime() >= jobI.getFinishTime()) {
                p = i;
                break;
			}
			i--;
		}
		return p;
	}
	
	public static int ComputeOpt(int j, List<JobWithPriority> jobList) {
		if (j == 0)
			return 0;
		else{
			JobWithPriority jobTemp = jobList.get(j-1);
			return (Math.max(jobTemp.getPriority() + ComputeOpt(ComputeP(j, jobList), jobList),ComputeOpt(j - 1, jobList)));
		}
	}


	public static void FindSolution(int j, List<JobWithPriority> jobList) {
        if (j == 0) {
            return;
        } else {
            JobWithPriority jobTem = jobList.get(j-1);
            if (jobTem.getPriority() + ComputeOpt(j, jobList) > ComputeOpt(j-1, jobList)) {
                System.out.println(jobTem.getNum());
                FindSolution(ComputeP(j, jobList), jobList);
            }
            else {
                FindSolution(j-1, jobList);
            }
        }
    }

}


class ComparatorJobByFinishTime implements Comparator<Object> {

	 public int compare(Object job1, Object job2) {

         JobWithPriority userFirst = (JobWithPriority)job1;
         JobWithPriority userSecond = (JobWithPriority)job2;

	    if (userFirst.getFinishTime() == userSecond.getFinishTime())
			return 0;
	    else
		  	return userFirst.getFinishTime() > userSecond.getFinishTime() ? 1 : -1;
	  
	 }

}

class ComparatorJobByStartTime implements Comparator<Object> {

	 public int compare(Object job1, Object job2) {

         JobWithPriority user1 = (JobWithPriority)job1;
         JobWithPriority user2 = (JobWithPriority)job2;

	    if (user1.getStartTime() == user2.getStartTime())
			return 0;
	    else
		  	return user1.getStartTime() > user2.getStartTime() ? 1 : -1;
	  
	 }

}

class JobWithPriority {

	private final int num;
	private final int startTime;
	private final int finishTime;
	private final int priority;
	
	public JobWithPriority(int num, int startTime, int finishTime, int priority) {
		this.num = num;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.priority = priority;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
}