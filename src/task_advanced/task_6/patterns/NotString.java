package task_advanced.task_6.patterns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// using in iterator -> IntArray(Two/Three/Five)TimesIterator
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotString {

}
