package nl.tecon.cme.client

import nl.tecon.cme.client.common.ParseContext
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.context.support.AbstractApplicationContext

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/21/10 - 12:53 AM
 */
abstract class AbstractSpringMockContextTest
{
  @Mock
  AbstractApplicationContext context

  @Before
  final void initContext()
  {
    MockitoAnnotations.initMocks(this)

    ParseContext.setSpringContext(context)
  }
}
