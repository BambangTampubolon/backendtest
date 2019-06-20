package spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import spring.config.Initializer;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Initializer.class}, loader = AnnotationConfigContextLoader.class)
public class WebServiceTest {
	@Test
	public void contextLoads() throws Exception{
		
	}
}
