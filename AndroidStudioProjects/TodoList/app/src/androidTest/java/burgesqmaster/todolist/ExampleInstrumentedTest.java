//package burgesqmaster.todolist;
//
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.util.Log;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static android.app.PendingIntent.getActivity;
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.core.deps.guava.base.CharMatcher.is;
//import static android.support.test.espresso.core.deps.guava.base.Predicates.not;
//import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.junit.Assert.*;
//
//@RunWith(AndroidJUnit4.class)
//public class ExampleInstrumentedTest {
//
//    String stringTest;
//
//    @Rule
//    public ActivityTestRule<CategoryActivity> mActivityRule = new ActivityTestRule<>(
//            CategoryActivity.class);
//
//    @Before
//    public void initStrings() {
//        stringTest = "Test";
//    }
//
//    @Test
//    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("burgesqmaster.todolist", appContext.getPackageName());
//    }
//
//    @Test
//    public void changeText_sameActivity() {
//        // Type text and then press the button.
//        onView(withId(R.id.fab))
//                .perform(click());
//
//        onView(withText("Name of the new category"))
//                .perform(typeText(stringTest), closeSoftKeyboard());
//
////        onView(withText("Name of the new category"))
////                .inRoot(withDecorView(not(is(getActivity().getWindow().getDecorView()))))
////                .perform(click());
//
//        // Check that the text was changed.
////        onView(withId(R.id.c))
////                .check(matches(withText(mStringToBetyped)));
//    }
//
//}
