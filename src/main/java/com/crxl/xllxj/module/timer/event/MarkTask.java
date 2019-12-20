package com.crxl.xllxj.module.timer.event; 
import com.crxl.xllxj.module.timer.manager.TimerManager;
/**
 * 有标记的任务. 依赖 timertask 执行定时任务
 * @author zxd
 *
 */
public abstract class MarkTask implements Runnable{
	
	private boolean cancelled;

    /**
     * 返回标记
     * @return
     */
	public abstract String getMark();

    @Override
    public final int hashCode() {
    	return this.getMark().hashCode();
    }
    
	public final void run() {
		if (!cancelled) {
			execute();
		}
		TimerManager.instance.cancelMarkTask(this);
	}
	
	public final void cancel(){
		this.cancelled=true;
	}
	
	protected abstract void execute();
	 
}
