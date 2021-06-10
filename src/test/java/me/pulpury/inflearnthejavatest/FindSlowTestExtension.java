package me.pulpury.inflearnthejavatest;

import java.lang.reflect.Method;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
	
	private long THRESHOLD = 1000L;
	
	public FindSlowTestExtension(long tHRESHOLD) {
//		super();
		THRESHOLD = tHRESHOLD;
	}

	/* @Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		Store store = context.getStore(Namespace.create(testClassName, testMethodName));
		
		store.put("START_TIME", System.currentTimeMillis());
	} */
	
	/* @Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		
		Store store = context.getStore(Namespace.create(testClassName, testMethodName));
		
		long start_time = store.remove("START_TIME", long.class);
		long duration = System.currentTimeMillis() - start_time;
		if (duration > THRESHOLD) {
			System.out.printf("Please consider mark method [%s] with @SlowTest. \n", testMethodName);
		}
	} */

	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		Store store = getStore(context);
		
		store.put("START_TIME", System.currentTimeMillis());
	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		// TODO Auto-generated method stub
		Method requiredTestMethod = context.getRequiredTestMethod();
		SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
		
		String testMethodName = requiredTestMethod.getName();
		Store store = getStore(context);
		
		long start_time = store.remove("START_TIME", long.class);
		long duration = System.currentTimeMillis() - start_time;
		if (duration > THRESHOLD && annotation == null) {
			System.out.printf("Please consider mark method [%s] with @SlowTest. \n", testMethodName);
		}
		
	}
	
	private Store getStore(ExtensionContext context) {
		String testClassName = context.getRequiredTestClass().getName();
		String testMethodName = context.getRequiredTestMethod().getName();
		Store store = context.getStore(Namespace.create(testClassName, testMethodName));
		return store;
	}


}
