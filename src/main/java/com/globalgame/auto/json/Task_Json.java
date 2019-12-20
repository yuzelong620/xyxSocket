package com.globalgame.auto.json;
import java.util.List;
import com.mind.core.util.StringIntTuple;
import com.mind.core.util.IntDoubleTuple;
import com.mind.core.util.IntTuple;
import com.mind.core.util.ThreeTuple;
import com.mind.core.util.StringFloatTuple;

/**
*自动生成类
*/
public class Task_Json{
	/** 编号::*/
	private Integer	id;
	/** 任务类型::*/
	private Integer	taskType;
	/** 达成条件/总进度::*/
	private Integer	total;
	/** 任务奖励::*/
	private List<StringIntTuple>	rewardList;
	/** 任务分类::*/
	private Integer	taskClassIfy;
	/** 大标题::*/
	private String	title;
	/** 描述::*/
	private String	describe;
	/** 展示类型::*/
	private Integer	showType;

	/** 编号::*/
	public Integer getId(){
		return this.id;
	}
	/** 任务类型::*/
	public Integer getTaskType(){
		return this.taskType;
	}
	/** 达成条件/总进度::*/
	public Integer getTotal(){
		return this.total;
	}
	/** 任务奖励::*/
	public List<StringIntTuple> getRewardList(){
		return this.rewardList;
	}
	/** 任务分类::*/
	public Integer getTaskClassIfy(){
		return this.taskClassIfy;
	}
	/** 大标题::*/
	public String getTitle(){
		return this.title;
	}
	/** 描述::*/
	public String getDescribe(){
		return this.describe;
	}
	/** 展示类型::*/
	public Integer getShowType(){
		return this.showType;
	}
	/**编号::*/
	public void setId(Integer id){
		this.id = id;
	}
	/**任务类型::*/
	public void setTaskType(Integer taskType){
		this.taskType = taskType;
	}
	/**达成条件/总进度::*/
	public void setTotal(Integer total){
		this.total = total;
	}
	/**任务奖励::*/
	public void setRewardList(List<StringIntTuple> rewardList){
		this.rewardList = rewardList;
	}
	/**任务分类::*/
	public void setTaskClassIfy(Integer taskClassIfy){
		this.taskClassIfy = taskClassIfy;
	}
	/**大标题::*/
	public void setTitle(String title){
		this.title = title;
	}
	/**描述::*/
	public void setDescribe(String describe){
		this.describe = describe;
	}
	/**展示类型::*/
	public void setShowType(Integer showType){
		this.showType = showType;
	}
}