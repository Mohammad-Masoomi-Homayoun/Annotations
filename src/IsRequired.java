import java.lang.annotation.*;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface IsRequired {

    boolean isFilled() default false;
}
