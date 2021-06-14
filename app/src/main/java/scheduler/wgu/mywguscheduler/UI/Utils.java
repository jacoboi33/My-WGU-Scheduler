package scheduler.wgu.mywguscheduler.UI;

import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public abstract class Utils {
    public static <T extends View> List<T> findViewsWithType(View root, Class<T> type) {
        List<T> views = new ArrayList<>();
        findViewsWithType(root, type, views);
        return views;
    }

    private static <T extends View> void findViewsWithType(View view, Class<T> type, List<T> views) {
        if (type.isInstance(view)) {
            views.add(type.cast(view));
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                findViewsWithType(viewGroup.getChildAt(i), type, views);
            }
        }
    }

    public static String dateFormatConverter(Long selection) {
        TimeZone timeZoneUTC = TimeZone.getDefault();
        // It will be negative, so that's the -1
        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

        // Create a date format, then a date object with our offset
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date date = new Date(selection + offsetFromUTC);
        return simpleFormat.format(date);



//                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//                        calendar.setTimeInMillis(selection);
    }
}
