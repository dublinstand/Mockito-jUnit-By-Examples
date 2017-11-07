package com.training.junit.suite;

import com.training.junit.helper.ArraysCompareTest;
import com.training.junit.helper.StringHelperTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ArraysCompareTest.class,StringHelperTest.class})
public class DummyTestSuite {

}
