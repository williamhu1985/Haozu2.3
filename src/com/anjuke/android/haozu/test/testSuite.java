
package com.anjuke.android.haozu.test;

import junit.framework.Test;  
import junit.framework.TestCase;  
import junit.framework.TestSuite;  
import android.test.suitebuilder.TestSuiteBuilder;

import com.anjuke.android.haozu.test.*;
import com.anjuke.android.util.Report;

import junit.runner.*;
  
/** 
 * 當存在多個測試類是，用junit的自動測試方法來完成對項目的多個測試類的同時測試 
 */  
  
public class testSuite extends TestCase{  
  
    public static Test suite() {  
        TestSuite suite = new TestSuite();      
        
        suite.addTestSuite(BrowseHistoryHouseTitleTest.class);  
        suite.addTestSuite(CollectHouseTest.class); 
        suite.addTestSuite(FiltersTest.class);  
        suite.addTestSuite(NearBySearchConditionFilterTest.class);
        suite.addTestSuite(SearchConditionFilterTest.class);
        suite.addTestSuite(SearchFilterConfirmTest.class);
        suite.addTestSuite(SearchFilterNotRelated.class);
        suite.addTestSuite(SearchFilterRelated.class);
        suite.addTestSuite(SwitchCityTest.class);
        suite.addTestSuite(SendReportTest.class);
        // 設計模式：典型的組合模式  
        // suite.addTest(test);    
        return suite;  
  
    }
    
//    @Override
//    public void setUp(){
//      	Report.writeHTMLLog("所有测试用例", "所有测试用例报告如下", Report.DONE, "");
//    }
//    
//    @Override
//    public void tearDown(){
//    	Report.seleniumReport();
//    }
    
    public void testAllCase(){
    	junit.textui.TestRunner.run(suite());     	
    }

  
}  