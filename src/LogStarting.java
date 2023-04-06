
// java standard annotations used for creating other annotations like target/retention/inherited/documented

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogStarting {

    String logTime() default "not_set";
    int timeToStart() default 3;
}
