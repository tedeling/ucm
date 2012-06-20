package nl.tecon.cme.client

import java.lang.annotation.*

/**
 * @author thies (Thies Edeling - thies@te-con.nl)
 * Created on: 12/29/10 - 1:45 PM
 */
@Target([ElementType.TYPE])
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface StubRepository {
	String value() default "";
}
