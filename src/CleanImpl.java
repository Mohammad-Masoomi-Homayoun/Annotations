import java.lang.annotation.Annotation;

public class CleanImpl implements Clean {

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public String doIt() {

        return "Test";
    }
}
