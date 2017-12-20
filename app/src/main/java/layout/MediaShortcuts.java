package layout;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.denie.mediashortcuts2.R;

import io.paperdb.Paper;

/**
 * Implementation of App Widget functionality.
 */
public class MediaShortcuts extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);

        Paper.init(context);
        String title = Paper.book().read("title");
        String note = Paper.book().read("note");

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.media_shortcuts);
        views.setTextViewText(R.id.textView, title);
        views.setTextViewText(R.id.textView2, note);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);

        int minWidth = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH);
        int minHeight = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT);

        appWidgetManager.updateAppWidget(appWidgetId, getRemoteViews(context, minWidth, minHeight));

    }

    private RemoteViews getRemoteViews(Context context, int minWidth, int minHeight){
        //int rows = getCellsForSize(minHeight);
        int columns = getCellsForSize(minWidth);

        if(columns == 4){
            return new RemoteViews(context.getPackageName(), R.layout.xperia_logo);
        }
        else{
            return new RemoteViews(context.getPackageName(), R.layout.media_shortcuts);
        }


    }


    private static int getCellsForSize(int size) {
        int n = 2;
        while (70 * n - 30 < size) {
            ++n;
        }

        return n - 4;
    }
}

