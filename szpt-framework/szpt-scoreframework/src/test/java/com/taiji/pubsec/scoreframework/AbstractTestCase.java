package com.taiji.pubsec.scoreframework;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml"})
@Transactional(transactionManager="transactionManager", rollbackFor=Exception.class)
@Rollback(true)
public abstract class AbstractTestCase {

}
