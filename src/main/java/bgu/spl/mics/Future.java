package bgu.spl.mics;

import java.util.concurrent.TimeUnit;

/**
 * A Future object represents a promised result - an object that will
 * eventually be resolved to hold a result of some operation. The class allows
 * Retrieving the result once it is available.
 * 
 * Only private methods may be added to this class.
 * No public constructor is allowed except for the empty constructor.
 */
public class Future<T> {
	
	private T result;
	private Boolean isDone;

	/**
	 * This should be the the only public constructor in this class.
	 */
	public Future() {
		this.result = null;
		this.isDone = false;
	}
	
	/**
     * retrieves the result the Future object holds if it has been resolved.
     * This is a blocking method! It waits for the computation in case it has
     * not been completed.
     * <p>
     * @return return the result of type T if it is available, if not wait until it is available.
     * 	       
     */
	public synchronized T get() {
		synchronized((Object)isDone){
			while(!isDone) { 
				try {
					wait();
				} catch (InterruptedException e) {}
			} 
			return result;
		}
	}
	
	/**
     * Resolves the result of this Future object.
     */
	public synchronized void resolve (T result) {
		synchronized((Object)isDone){
			this.result = result;
			this.isDone = true;
			notifyAll();
		}
	}
	
	/**
     * @return true if this object has been resolved, false otherwise
     */
	public boolean isDone() {
		synchronized((Object)isDone){
			return isDone;
		}
	}
	
	/**
     * retrieves the result the Future object holds if it has been resolved,
     * This method is non-blocking, it has a limited amount of time determined
     * by {@code timeout}
     * <p>
     * @param timout 	the maximal amount of time units to wait for the result.
     * @param unit		the {@link TimeUnit} time units to wait.
     * @return return the result of type T if it is available, if not, 
     * 	       wait for {@code timeout} TimeUnits {@code unit}. If time has
     *         elapsed, return null.
     */
	// This method ensures safe and precise waiting for the result, handling timeouts and spurious wakeups effectively.
	 public T get(long timeout, TimeUnit unit) {
		long remainingTime = unit.toMillis(timeout);
        long start = System.currentTimeMillis();
		synchronized((Object)isDone){
			while (!isDone && remainingTime > 0) {
				try {
					this.wait(remainingTime);
				} catch (InterruptedException e) {}
				long elapsedTime = System.currentTimeMillis() - start;
				remainingTime = unit.toMillis(timeout) - elapsedTime;
			}
		}
		return result; // Will be null if not resolved
	}

}
