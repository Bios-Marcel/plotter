package com.plotter.computer;

import java.lang.reflect.Array;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.plotter.core.IComputer;
import com.plotter.core.IPlotter;

/**
 * Multi threaded computer class
 * 
 * @author Ethem Kurt
 *
 * @param <TA>
 *            Result type
 * @param <TB>
 *            Value type
 */
public class MultiThreadedComputer<TA, TB> implements IComputer<TA, TB> {

	/**
	 * Thread pool
	 */
	private ExecutorService pool;

	/**
	 * Constructor
	 * 
	 * @param threads
	 *            Threads
	 */
	public MultiThreadedComputer(int threads) {
		pool = Executors.newFixedThreadPool(threads);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plotter.core.IComputer#compute(java.lang.Object[],
	 * com.plotter.core.IPlotter)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TA[] compute(TB[] values, Class<TA> clazz, IPlotter<TA, TB> plotter) {
		TA[] ret = null;
		int i;
		if ((values != null) && (plotter != null)) {
			ret = (TA[]) Array.newInstance(clazz, values.length);
			for (i = 0; i < values.length; i++)
				pool.submit(new AMultiThreadCall<TA>(i) {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.util.concurrent.Callable#call()
					 */
					@Override
					public TA call() throws Exception {
						return plotter.plotElement(values[index]);
					}
				});
		}
		return ret;
	}

}
