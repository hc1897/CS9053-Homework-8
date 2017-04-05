package edu.nyu.cs9053.homework8;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LambdaScheduler {
	
	public static void main(String[] args){
		
		  List<Job> jobList = new ArrayList<>();
		  jobList.add(new Job(1, 1, 10));
		  jobList.add(new Job(2, 3, 4));
		  jobList.add(new Job(3, 2, 3));
		  jobList.add(new Job(4, 5, 9));  
		  jobList.add(new Job(5, 2, 8));
		  jobList.add(new Job(6, 5, 6));
		  jobList.add(new Job(8, 6, 10));
		  jobList.add(new Job(9, 4, 7));
		  jobList.add(new Job(10, 8, 12));
		  jobList.add(new Job(11, 2, 14));
		  jobList.add(new Job(12, 12, 16));
		  
		  ComparatorJob comparator = new ComparatorJob();
		  Collections.sort(jobList, comparator);
		   
		  List<Job> list = new ArrayList<>();
		  int last = 0;
		  
		  for (int i = 0 ; i < jobList.size() ; i++) {
			  Job jobTemp = (Job)jobList.get(i);
			  if (last <= jobTemp.getStartTime()) {
				  list.add(jobTemp);
				  last = jobTemp.getFinishTime();
			  }
		  }
		  
		  for (int i = 0; i < list.size(); i++){
			  Job jobTemp = (Job)jobList.get(i);
			  System.out.println(jobTemp.getNum());
		  }
	}

}

class ComparatorJob implements Comparator<Object> {

	 public int compare(Object job1, Object job2) {
		 
	  Job userFirst = (Job)job1;
	  Job userSecond = (Job)job2;

	  if (userFirst.getFinishTime() == userSecond.getFinishTime())
			return 0;
	  else
		  	return userFirst.getFinishTime() > userSecond.getFinishTime() ? 1 : -1;
	  
	 }

}

class Job {
	
	private final int num;
	private final int startTime;
	private final int finishTime;
	
	public Job(int num, int startTime, int finishTime) {
		this.num = num;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public int getStartTime() {
		return this.startTime;
	}
	
	public int getFinishTime() {
		return this.finishTime;
	}

}