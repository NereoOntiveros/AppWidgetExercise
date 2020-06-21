package com.example.appwidgetsample;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        //update each of the app widgets with the remote adapter
        for (int appWidgetId : appWidgetIds) {


            //Set up the intent that starts the ListViewService which will
            //provide the views for this collection
            Intent intent = new Intent(context, ListWidgetService.class);

            //Add the app widget ID to the intent extras
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            //Instantiate the RemoteViews object for the app widget layout
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            //Set up the RemoteViews object to use a RemoteViews adapter.
            //This adapter connects to a RemoteViewsService
            //through the specified intent.
            //This is how data are populated.
            rv.setRemoteAdapter(R.id.list_view, intent);

            //The empty view is displayed when the collection has no items.
            //It should be in the same layout used to instantiate the RemoteViews
            //object above.
            rv.setEmptyView(R.id.list_view, R.id.empty_view);





            appWidgetManager.updateAppWidget(appWidgetId, rv);

        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        final String action = intent.getAction();

        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
            //refresh all widgets

            onUpdate(context,AppWidgetManager.getInstance(context),AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context,NewAppWidget.class)));
        }super.onReceive(context, intent);
    }
}

